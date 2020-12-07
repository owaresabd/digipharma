package com.pms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pms.model.DashboardInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class DashboardRepository {
	@Autowired
	private IUserService userService;
	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public DashboardInfo getDashboardInfo() { 
		  jdbcTemplate = new JdbcTemplate(datasource); 
		  return jdbcTemplate.queryForObject(" SELECT SUM(pending_css) pending_css, SUM(pending_sample) pending_sample,SUM(pending_rcv) pending_rcv,SUM(pending_dist) pending_dist,SUM(pending_result) pending_result,"
		  		+ " SUM(pending_verify) pending_verify,SUM(pending_tm) pending_tm,SUM(lm_pending)  pending_lm FROM ("
		  		+ "	Select count(*) as pending_css,0 pending_sample,0 pending_rcv,0 pending_dist, 0 pending_result,0 pending_verify, 0 pending_tm,0 lm_pending   from lims_css_request_infos where req_status='P' " + 
		  		"	UNION ALL	" + 
		  		"	Select 0 pending_css, count(*) pending_sample,0 pending_rcv,0 pending_dist, 0 pending_result,0 pending_verify, 0 pending_tm,0 lm_pending from lims_sampling_infos where  status='P' " + 
		  		"	UNION ALL	" + 
		  		"	Select 0 pending_css, 0 pending_sample,count(*) pending_rcv,0 pending_dist, 0 pending_result,0 pending_verify, 0 pending_tm,0 lm_pending from lims_qc_req_infos where  status='P' " + 
		  		"	UNION ALL 	" + 
		  		"	Select 0 pending_css, 0 pending_sample,0 pending_rcv,count(*) pending_dist, 0 pending_result,0 pending_verify, 0 pending_tm,0 lm_pending from lims_qc_req_infos where  status='R'  " + 
		  		"	UNION ALL	" + 
		  		"	Select 0 pending_css, 0 pending_sample,0 pending_rcv,0 pending_dist,count(DISTINCT sample_rcv_id) pending_result,0 pending_verify, 0 pending_tm,0 lm_pending from lims_distribution_infos   where   status = 'P' " + 
		  		"	UNION ALL	" + 
		  		"	SELECT 0 pending_css, 0 pending_sample,0 pending_rcv,0 pending_dist,0 pending_result, count(distinct sample_id) pending_verify, 0 pending_tm,0 lm_pending FROM view_distribution_infos p where  exists  ( select * from view_result_status_info c  where c.sample_rcv_id = p.sample_rcv_id and c.is_accept = 'P') " + 
		  		"	UNION ALL \r\n" + 
		  		"	select 0 pending_css, 0 pending_sample,0 pending_rcv,0 pending_dist,0 pending_result, 0 pending_verify,count (distinct sample_rcv_id) pending_tm ,0 lm_pending from view_distribution_infos p  where  not exists " + 
		  		"	( select * from view_result_status_info c  where c.sample_rcv_id = p.sample_rcv_id and c.is_accept != 'A')  and sample_rcv_id NOT IN (Select sample_rcv_id from lims_tm_verify_infos where sample_rcv_id=p.sample_rcv_id ) " + 
		  		"	UNION ALL	" + 
		  		"	select  0 pending_css, 0 pending_sample,0 pending_rcv,0 pending_dist,0 pending_result, 0 pending_verify,0 pending_tm,count (distinct sample_rcv_id)  lm_pending from view_distribution_infos p   where is_decision='Y' and sample_rcv_id in " + 
		  		"	( select sample_rcv_id from lims_tm_verify_infos c  where c.sample_rcv_id = p.sample_rcv_id)  and sample_rcv_id NOT IN (Select sample_rcv_id from lims_lm_verify_infos where sample_rcv_id=p.sample_rcv_id )) a",
		  		new InfoRowMapper());
		  
	}
	public DashboardInfo getAccessInfo() { 
		  jdbcTemplate = new JdbcTemplate(datasource);
		  User user = userService.getCurrentUser();
		  return jdbcTemplate.queryForObject("SELECT id,"
		  		+ " (SELECT count(*) from lims_role_privileges where privilege_id=m.equipment_menu_id and type_id='"+user.getTypeId()+"') is_equipment,"
		  		+ " (SELECT   count(*) FROM lims_maintenance_detail_infos a, lims_frequency_infos b   "
		  		+ " WHERE a.id=b.last_schedule_id and a.company_id='"+user.getCompanyId()+"' and b.notify_time is not null and abs(a.next_schedule :: date - now() :: date) <=b.notify_time::int) total_notification, "
		  		+ " (SELECT count(*) from lims_role_privileges where privilege_id=m.ref_standard_menu_id and type_id='"+user.getTypeId()+"') is_reference,"
				+ " (SELECT   count(*) FROM lims_ref_standard_infos a  WHERE  a.company_id='"+user.getCompanyId()+"' and a.valid_to_date is not null and abs(a.valid_to_date :: date - now() :: date) <=180) total_ref_msg "
				+ " FROM lims_access_infos m ;",
		  		new AccessRowMapper());
		  
	}
	public List<DashboardInfo> getMonthlyTestInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		
		return jdbcTemplate.query(
				"select to_char(created_at,'Month') as month_name,EXTRACT(MONTH FROM  created_at) mon_sl, count(id) as total_test"
				+ " from lims_wh_request_infos where created_at is not null "
				+ " and extract(year from created_at)= extract(year from now()) "
				+ " and company_id=? "
				+ " group by month_name,mon_sl order by mon_sl",
				new Object[] { user.getCompanyId()}, new MonthTestInfoRowMapper());
	}
	
	public List<DashboardInfo> getTypeWiseTestInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		
		return jdbcTemplate.query(
				"	select fnc_material_type_nm(material_type_id) type_name,count(id) as total_req "
				+ " from lims_wh_request_infos where material_type_id is not null and company_id =? "
				+ " and extract(year from created_at)= extract(year from now()) "
				+ " group by type_name order by type_name",
				new Object[] { user.getCompanyId()}, new TypeWiseTestInfoRowMapper());
	}
	
	class MonthTestInfoRowMapper implements RowMapper<DashboardInfo> {

		@Override
		public DashboardInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DashboardInfo info = new DashboardInfo();

			info.setMonthName(rs.getString("month_name"));
			info.setNoOfTest(rs.getString("total_test"));

			return info;
		}
	}
	class TypeWiseTestInfoRowMapper implements RowMapper<DashboardInfo> {

		@Override
		public DashboardInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DashboardInfo info = new DashboardInfo();

			info.setTypeName(rs.getString("type_name"));
			info.setNoOfTest(rs.getString("total_req"));

			return info;
		}
	}

	class AccessRowMapper implements RowMapper<DashboardInfo> {
		@Override
		public DashboardInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DashboardInfo info = new DashboardInfo();
			info.setIsEquipment(rs.getString("is_equipment"));
			info.setTotalNotification(rs.getString("total_notification"));
			info.setIsReference(rs.getString("is_reference"));
			info.setTotalRefMsg(rs.getString("total_ref_msg"));
			
			
			return info;
		}
	}
	
	class InfoRowMapper implements RowMapper<DashboardInfo> {
		@Override
		public DashboardInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DashboardInfo info = new DashboardInfo();
			info.setTotalPendingCss(rs.getInt("pending_css"));
			info.setTotalPendingSampling(rs.getInt("pending_sample"));
			info.setTotalPendingRcv(rs.getInt("pending_rcv"));
			info.setTotalPendingDist(rs.getInt("pending_dist"));
			info.setTotalPendingResult(rs.getInt("pending_result"));
			info.setTotalPendingAcceptance(rs.getInt("pending_verify"));
			info.setTotalPendingTm(rs.getInt("pending_tm"));
			info.setTotalPendingLm(rs.getInt("pending_lm"));
			
			
			return info;
		}
	}
	
}
