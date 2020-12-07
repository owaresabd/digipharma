package com.pms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pms.model.CommonInfo;
import com.pms.model.TmInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class TmRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;
	


	public List<CommonInfo> findAll() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" Select  distinct (sample_rcv_id),ud_css_no,ud_sample_no, material_name, arn_no ,result_status " + 
				"	from view_distribution_infos  a  " + 
				"	where not exists (Select sample_rcv_id from view_distribution_infos  where  sample_rcv_id =a.sample_rcv_id and result_status = 'P') " + 
				"	and  not exists (Select sample_rcv_id from lims_tm_verify_infos where sample_rcv_id=a.sample_rcv_id ) " + 
				"	and CASE WHEN is_chemical='Y' THEN not exists (Select id from lims_qc_sample_rcv_infos where id=a.sample_rcv_id and status ='P') else exists (Select id from lims_qc_sample_rcv_infos where id=a.sample_rcv_id and status ='P') END   " + 
				"	and CASE WHEN is_microbiological='Y' THEN not exists (Select id from lims_qc_sample_rcv_infos where id=a.sample_rcv_id and micro_dist_status = 'P') else  exists (Select id from lims_qc_sample_rcv_infos where id=a.sample_rcv_id and micro_dist_status = 'P') END \r\n" + 
				"	and sample_rcv_id not in ( select sample_rcv_id from lims_distribution_infos  c,lims_result_infos d where c.sample_rcv_id = a.sample_rcv_id and c.id=d.distribution_id and d.status != 'A')  " + 
				"	and company_id =? " + 
				"	order by sample_rcv_id",
				new Object[] { user.getCompanyId() }, new TmInfoRowMapper());
	}
	public List<CommonInfo> findAllBack31032020() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" Select  distinct (sample_rcv_id),ud_css_no,ud_sample_no, material_name, arn_no ,result_status "
				+ " from view_distribution_infos  a  "
				+ " where not exists (Select sample_rcv_id from view_distribution_infos where  sample_rcv_id =a.sample_rcv_id and result_status = 'P') "
				+ " and not exists ( select sample_rcv_id from lims_distribution_infos  c,lims_result_infos d  where c.id=d.distribution_id and c.sample_rcv_id = a.sample_rcv_id and d.status != 'A') "
				+ " and  not exists (Select sample_rcv_id from lims_tm_verify_infos where sample_rcv_id=a.sample_rcv_id ) "
				+"  and CASE WHEN is_chemical='Y' THEN not exists (Select id from lims_qc_sample_rcv_infos where id=a.sample_rcv_id and status ='P') else exists (Select id from lims_qc_sample_rcv_infos where id=a.sample_rcv_id and status ='P') END  and CASE WHEN is_microbiological='Y' THEN not exists (Select id from lims_qc_sample_rcv_infos where id=a.sample_rcv_id and micro_dist_status = 'P') else  exists (Select id from lims_qc_sample_rcv_infos where id=a.sample_rcv_id and micro_dist_status = 'P') END "
				+ " and company_id =? "
				+ " order by sample_rcv_id",
				new Object[] { user.getCompanyId() }, new TmInfoRowMapper());
	}
	public List<CommonInfo> findAllback() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select distinct on (sample_rcv_id)  sample_rcv_id,ud_css_no,ud_sample_no, material_name, arn_no "
				+ " from view_distribution_infos p  "
				+ " where  not exists "
				+ "	( select * from view_result_status_info c  where c.sample_rcv_id = p.sample_rcv_id and c.is_accept != 'A') "
				+ " and sample_rcv_id NOT IN (Select sample_rcv_id from lims_tm_verify_infos where sample_rcv_id=p.sample_rcv_id )"
				+ " and STATUS NOT IN (Select STATUS from view_distribution_infos where sample_rcv_id=p.sample_rcv_id and status ='P' ) "
				+ " and company_id =? "
				+ " order by sample_rcv_id",
				new Object[] { user.getCompanyId() }, new TmInfoRowMapper());
	}
	
	public List<CommonInfo> findApprovedList() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				  " Select  distinct (sample_rcv_id),ud_css_no,ud_sample_no, material_name, arn_no ,result_status "
				+ " from view_distribution_infos p "
				+ "	where sample_rcv_id in (CASE WHEN is_decision = 'N' THEN "
				+ "	(select sample_rcv_id from lims_tm_verify_infos c where c.sample_rcv_id = p.sample_rcv_id)  "
				+ " ELSE (select distinct sample_rcv_id from lims_lm_verify_infos c where c.sample_rcv_id = p.sample_rcv_id) "
				+ "  END) and company_id=?  order by sample_rcv_id", new Object[] { user.getCompanyId() }, new TmInfoRowMapper());
				
	}
	
	public List<CommonInfo> findVerifiedChdList(UUID sampleRcvId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(
				"  SELECT b.id result_id, b.doc_location, a.test_parameter_name,a.specification,b.test_result, " 
				+ " fnc_unit_nm(b.unit_id) unit_name,a. reference_name,a.result_status, b.status is_accept, " 
				+ " a.analyst_name,coalesce(to_char( a.receive_at, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as receive_at,"
				+ " coalesce(to_char( b.created_at, 'DD-Mon-YYYY'), '') as test_date "
				+ " FROM view_distribution_infos a "
				+ " INNER join view_material_param_info c "
				+ " on (c.parameter_id= a.test_parameter_id  and a.material_id=c.material_id) "
				+ " FULL OUTER JOIN lims_result_infos b "
				+ " ON b.distribution_id = a.id "
				+ " where a.sample_rcv_id::text = '"+sampleRcvId+"'::text order by  c.sl_no ",
				 new ChildInfoRowMapper());
	}
	public void saveResultVerifyByTmInfo(TmInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
			jdbcTemplate.update(
					"INSERT INTO lims_tm_verify_infos(sample_id, sample_rcv_id, remarks, note, company_id, created_by, created_at)" + 
					"	VALUES (?, ?, ?, ?, ?, ?, ?)",info.getSampleId(), info.getSampleRcvId(), info.getRemarks(), info.getNote(), 
					    user.getCompanyId(), user.getId(), time);
			jdbcTemplate.update("update lims_sampling_infos set test_report_no ='"+testReportNoGen(info.getSampleId())+"' where id = ?", info.getSampleId());
		 
	}
	
	public   String  testReportNoGen(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		StringBuilder udSampleId  = new StringBuilder(); 
		DateFormat df = new SimpleDateFormat("yyMM"); // Just the year, with 2 digits
		String year = df.format(Calendar.getInstance().getTime());
		
		CommonInfo info = jdbcTemplate.queryForObject("SELECT a.type_no,c.company_code,b.is_chemical,b.is_microbiological,b.sample_id , " + 
				"(SELECT COALESCE(CAST(MAX(RIGHT(test_report_no,4)) as integer),0)+1 from lims_sampling_infos)  max_id ,b.material_name " + 
				"from lims_material_type_infos a ,view_sample_infos b , lims_companies c " + 
				"where a.id=b.material_type_id and c.id=b.company_id and b.sample_id=?",new Object[] { id}, new IdInfoRowMapper());
		if(info.getIsChemical().equals("Y") && info.getIsMicrobiological().equals("Y")) {
			udSampleId.append("RP-").append("GN-").append(year).append("-").append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
		}else if(info.getIsChemical().equals("Y") && info.getIsMicrobiological().equals("N")) {
			
			udSampleId.append("RP-").append("CM-").append(year).append("-").append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
			
		}else if(info.getIsChemical().equals("N") && info.getIsMicrobiological().equals("Y")) {
			
			udSampleId.append("RP-").append("MC-").append(year).append("-").append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
			
		}
		
		
		return udSampleId.toString();
	}
	
	public CommonInfo findSampleApprovedInfo(UUID id){
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject(
				 " SELECT id, ud_css_no, wh_request_id, arn_no, material_id, material_name, material_code, material_type_id, "
				 + "material_type_nm, ud_sample_no, is_chemical, is_microbiological, req_type, req_type_name, receive_at, "
				 + "chemi_dist_at, micro_dist_at, sample_desc_rcv, sample_id, is_decision, "
				 + "(Select remarks from lims_tm_verify_infos where sample_rcv_id::text='"+id+"'::text) remarks, "
				 + "(Select note from lims_tm_verify_infos where sample_rcv_id::text='"+id+"'::text) note "
				 + " FROM view_sample_rcv_infos where id = ?",
				 new Object[] { id}, new TmApproveInfoRowMapper());
	}
	
	class IdInfoRowMapper implements RowMapper<CommonInfo> {
		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CommonInfo info = new CommonInfo();
			info.setCompanyCode(rs.getString("company_code"));
			info.setMaterialTypeName(rs.getString("type_no"));
			info.setIsChemical(rs.getString("is_chemical"));
			info.setIsMicrobiological(rs.getString("is_microbiological"));
			info.setMaxId(rs.getString("max_id"));
			
			return info;
		}
		
	}
	
	class TmInfoRowMapper implements RowMapper<CommonInfo> {
		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {			
			CommonInfo info = new CommonInfo();
			
			  info.setId((UUID)rs.getObject("sample_rcv_id"));
			  info.setUdCssNo(rs.getString("ud_css_no"));
			  info.setMaterialName(rs.getString("material_name"));
			  info.setUdSampleNo(rs.getString("ud_sample_no"));
			  info.setArnNo(rs.getString("arn_no"));
			  
			return info;
		}
	}
	
	class ChildInfoRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CommonInfo info = new CommonInfo();
			
			  info.setId((UUID)rs.getObject("result_id"));
			  info.setTestParameterName(rs.getString("test_parameter_name"));
			  info.setSpecification(rs.getString("specification"));
			  info.setTestResult(rs.getString("test_result"));
			  info.setUnitName(rs.getString("unit_name"));
			  info.setReferenceName(rs.getString("reference_name"));
			  info.setResultStatus(rs.getString("result_status"));
			  info.setIsAccept(rs.getString("is_accept"));
			  info.setAnalystName(rs.getString("analyst_name"));
			  info.setReceiveAt(rs.getString("receive_at"));
			  info.setTestDate(rs.getString("test_date"));
			  info.setTakenTime("");
			  info.setDocLocation(rs.getString("doc_location"));
			  
			return info;
		}
	}
	
	class TmApproveInfoRowMapper implements RowMapper<CommonInfo> {
		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {			
			CommonInfo info = new CommonInfo();
			
			  info.setId((UUID)rs.getObject("id"));
			  info.setUdCssNo(rs.getString("ud_css_no"));
			  info.setWhRequestId((UUID)rs.getObject("wh_request_id"));
			  info.setArnNo(rs.getString("arn_no"));
			  info.setMaterialId((UUID)rs.getObject("material_id"));
			  info.setMaterialName(rs.getString("material_name"));
			  info.setMaterialCode(rs.getString("material_code"));
			  info.setMaterialTypeId((UUID)rs.getObject("material_type_id"));
			  info.setMaterialTypeName(rs.getString("material_type_nm"));
			  info.setUdSampleNo(rs.getString("ud_sample_no"));
			  info.setIsChemical(rs.getString("is_chemical"));
			  info.setIsMicrobiological(rs.getString("is_microbiological"));
			  info.setReqType((UUID)rs.getObject("req_type"));
			  info.setReqTypeName(rs.getString("req_type_name"));
			  info.setReceiveDate(rs.getTimestamp("receive_at"));
			  info.setChemiDistDate(rs.getTimestamp("chemi_dist_at"));
			  info.setMicroDistDate(rs.getTimestamp("micro_dist_at"));
			  info.setSampleRcvDesc(rs.getString("sample_desc_rcv"));
			  info.setSampleId((UUID)rs.getObject("sample_id"));
			  info.setIsDecision(rs.getString("is_decision"));
			  info.setRemarks(rs.getString("remarks"));
			  info.setNote(rs.getString("note"));
			  			  
			return info;
		}
	}
	
	
}
