package com.pms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pms.model.CommonInfo;
import com.pms.model.LmInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class LmRepository {

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
		  " select distinct on (sample_rcv_id)  sample_rcv_id, ud_sample_no, material_name,is_decision, arn_no "
		  + " from view_distribution_infos p  " + " where is_decision='Y' and sample_rcv_id in " +
		  "	( select sample_rcv_id from lims_tm_verify_infos c  where c.sample_rcv_id = p.sample_rcv_id) "
		  + " and sample_rcv_id NOT IN (Select sample_rcv_id from lims_lm_verify_infos where sample_rcv_id=p.sample_rcv_id ) "
		  + " and company_id =? " + " order by sample_rcv_id", new Object[] {
		  user.getCompanyId() }, new TmInfoRowMapper());
		 
	}
	public List<CommonInfo> findVerifiedChdList(UUID sampleRcvId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(
				" SELECT b.id result_id,a.test_parameter_name,a.specification,b.test_result, b.doc_location, " + 
				"fnc_unit_nm(b.unit_id) unit_name,a. reference_name,a.result_status, b.status is_accept, " + 
				"a.analyst_name,coalesce(to_char( a.receive_at, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as receive_at,"
				+ " coalesce(to_char( b.created_at, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as test_date   " + 
				"FROM view_distribution_infos a " + 
				"FULL OUTER JOIN lims_result_infos b  " + 
				"ON b.distribution_id = a.id where a.sample_rcv_id = ? ",
				new Object[] { sampleRcvId }, new ChildInfoRowMapper());
	}
	public void saveResultVerifyByLmInfo(UUID idRandom,LmInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		if (info.getId() == null) {
			
			jdbcTemplate.update(
					"INSERT INTO lims_lm_verify_infos(id,sample_id, sample_rcv_id, opinion, remarks, company_id, created_by, created_at)" + 
					"	VALUES (?, ?, ?, ?, ?, ?,?,?)",idRandom,info.getSampleId(), info.getSampleRcvId(),info.getOpinion(), info.getRemarks(), 
					    user.getCompanyId(), user.getId(), time);
			
		}
	}
	public void updateResultVerifyByLmChdInfo(UUID resultId,String isDecision) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
	
				jdbcTemplate.update("UPDATE lims_result_infos SET decision_status =?,updated_by =?,updated_at =? where id = ?",
						isDecision,user.getId(), time,resultId);
			
		
	}
	
	class TmInfoRowMapper implements RowMapper<CommonInfo> {
		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {			
			CommonInfo info = new CommonInfo();
			
			  info.setId((UUID)rs.getObject("sample_rcv_id"));
			  info.setUdSampleNo(rs.getString("ud_sample_no"));
			  info.setMaterialName(rs.getString("material_name"));
			  info.setIsDecision(rs.getString("is_decision"));
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
	
	
	
}
