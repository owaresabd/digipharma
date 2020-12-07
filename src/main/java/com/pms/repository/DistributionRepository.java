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
import com.pms.model.DistributionInfo;
import com.pms.model.QcSampleRecvInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class DistributionRepository {
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
				" SELECT id, ud_css_no, wh_request_id, req_by, req_at, css_status, req_type, req_type_name, effective_date,provider_type,sche_from_date,sche_to_date,"
				+ " revision_no, form_no, invoice_no, chalan_no, lc_no, from_dept_no, from_dept_nm, to_dept_no, to_dept_nm,kept_amount,"
				+ " gr_no, material_id, material_name, material_code, material_type_id, material_type_nm, rcv_qty, manufacture_name,"
				+ " country_id, country_nm, sample_details, wh_status, is_chemical, is_microbiological,"
				+ " chemical_sample_amt, micro_sample_amt, total_amt,mat_unit_name, ud_sample_no, client_name,"
				+ " client_id, method_name, storage_condition, sample_procedure, booth_name, equipment_name, inst_name, precaution_taken,"
				+ " sampling_date, sampling_name, status, remarks, ud_qc_no, sample_id, sample_desc, uncertinity, qc_req_dt, is_decision,"
				+ " qc_status, company_id, qc_req_id, arn_no, sample_desc_rcv,st_chemi_qty, st_micro_qty, st_qty, st_unit_id, st_unit_name, st_equ_id, st_equ_name,"
				+ " st_room_id, st_room_name, st_rack_id, st_rack_name, ret_qty, ret_unit_id, ret_unit_name, ret_equ_id, ret_equ_name,"
				+ " ret_room_id, ret_room_name, ret_rack_id, ret_rack_name, dist_status, receive_at, chemi_dist_at, micro_dist_at, "
				+ " (CAST((CASE WHEN chemical_sample_amt ='' THEN '0' ELSE chemical_sample_amt END)  as float) + CAST((CASE WHEN micro_sample_amt ='' THEN '0' ELSE  micro_sample_amt END)  as float)) total_sample_amt, "
				+ " (CAST((CASE WHEN chemical_retention_amt ='' THEN '0' ELSE chemical_retention_amt END)  as float) + CAST((CASE WHEN micro_retention_amt ='' THEN '0' ELSE  micro_retention_amt END)  as float)) total_retention_amt "
				+ "	FROM view_sample_rcv_infos where dist_status='P' and is_chemical='Y' and   company_id = ? ORDER BY receive_at DESC",
				new Object[] { user.getCompanyId() }, new ReceiveInfoRowMapper());
		
		
	   }
	public List<CommonInfo> findDistributedList() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		
			return jdbcTemplate.query(
				" SELECT id, ud_css_no, wh_request_id, req_by, req_at, css_status, req_type, req_type_name, effective_date,provider_type,sche_from_date, sche_to_date,"
				+ " revision_no, form_no, invoice_no, chalan_no, lc_no, from_dept_no, from_dept_nm, to_dept_no, to_dept_nm,kept_amount,"
				+ " gr_no, material_id, material_name, material_code, material_type_id, material_type_nm, rcv_qty, manufacture_name,"
				+ " country_id, country_nm, sample_details, wh_status, is_chemical, is_microbiological, "
				+ " chemical_sample_amt, micro_sample_amt, total_amt,mat_unit_name, ud_sample_no, client_name,"
				+ " client_id, method_name, storage_condition, sample_procedure, booth_name, equipment_name, inst_name, precaution_taken,"
				+ " sampling_date, sampling_name, status, remarks, ud_qc_no, sample_id, sample_desc, uncertinity, qc_req_dt, is_decision,"
				+ " qc_status, company_id, qc_req_id, arn_no, sample_desc_rcv,st_chemi_qty, st_micro_qty, st_qty, st_unit_id, st_unit_name, st_equ_id, st_equ_name,"
				+ " st_room_id, st_room_name, st_rack_id, st_rack_name, ret_qty, ret_unit_id, ret_unit_name, ret_equ_id, ret_equ_name,"
				+ " ret_room_id, ret_room_name, ret_rack_id, ret_rack_name, dist_status, receive_at, chemi_dist_at, micro_dist_at, " 
				+ " (CAST((CASE WHEN chemical_sample_amt ='' THEN '0' ELSE chemical_sample_amt END)  as float) + CAST((CASE WHEN micro_sample_amt ='' THEN '0' ELSE  micro_sample_amt END)  as float)) total_sample_amt, "
				+ " (CAST((CASE WHEN chemical_retention_amt ='' THEN '0' ELSE chemical_retention_amt END)  as float) + CAST((CASE WHEN micro_retention_amt ='' THEN '0' ELSE  micro_retention_amt END)  as float)) total_retention_amt "
				+ "	FROM view_sample_rcv_infos where dist_status='D' and is_chemical='Y' and  company_id = ? ORDER BY receive_at DESC",
				new Object[] { user.getCompanyId() }, new ReceiveInfoRowMapper());
	
		
	}

	
	public CommonInfo getSampleReceiveInfo(UUID id){
		jdbcTemplate = new JdbcTemplate(datasource);
		
			return jdbcTemplate.queryForObject(
				 " SELECT id, ud_css_no, wh_request_id, req_by, req_at, css_status, req_type, req_type_name, effective_date,provider_type,sche_from_date,sche_to_date,"
				 + "revision_no, form_no, invoice_no, chalan_no, lc_no, from_dept_no, from_dept_nm, to_dept_no, to_dept_nm,kept_amount,"
				 + "gr_no, material_id, material_name, material_code, material_type_id, material_type_nm, rcv_qty, "
				 + "manufacture_name, country_id, country_nm, sample_details, wh_status, is_chemical, is_microbiological, chemical_sample_amt, micro_sample_amt,"
				 + "total_amt,mat_unit_name,  ud_sample_no, client_name, client_id, method_name, storage_condition, sample_procedure, "
				 + "booth_name, equipment_name, inst_name, precaution_taken, sampling_date, sampling_name, status, remarks,"
				 + "ud_qc_no, sample_id, sample_desc, uncertinity, qc_req_dt, is_decision, qc_status, company_id, qc_req_id,"
				 + "arn_no, sample_desc_rcv,st_chemi_qty, st_micro_qty, st_qty, st_unit_id, st_unit_name, st_equ_id, st_equ_name, st_room_id, st_room_name,"
				 + "st_rack_id, st_rack_name, ret_qty, ret_unit_id, ret_unit_name, ret_equ_id, ret_equ_name, ret_room_id,"
				 + "ret_room_name, ret_rack_id, ret_rack_name, dist_status, receive_at, chemi_dist_at, micro_dist_at, "
				 + " (CAST((CASE WHEN chemical_sample_amt ='' THEN '0' ELSE chemical_sample_amt END)  as float) + CAST((CASE WHEN micro_sample_amt ='' THEN '0' ELSE  micro_sample_amt END)  as float)) total_sample_amt, "
				 + " (CAST((CASE WHEN chemical_retention_amt ='' THEN '0' ELSE chemical_retention_amt END)  as float) + CAST((CASE WHEN micro_retention_amt ='' THEN '0' ELSE  micro_retention_amt END)  as float)) total_retention_amt "
				 + " FROM view_sample_rcv_infos where   id = ?",
				 new Object[] { id}, new ReceiveInfoRowMapper());
		
	
		
	}
	
	
	
	public void saveSampleRcvInfo(QcSampleRecvInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_qc_sample_rcv_infos (qc_req_id, sample_desc, st_qty,st_unit_id, st_equ_id, st_room_id, st_rack_id, "
					+ "ret_qty,ret_unit_id, ret_equ_id, ret_room_id, ret_rack_id,status, company_id, created_by, created_at) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",info.getQcReqId(), info.getSampleDesc(), info.getStQty(),info.getStUnitId(), info.getStEquId(), 
					info.getStRoomId(), info.getStRackId(), info.getRetQty(),info.getRetUnitId(), info.getRetEquId(), info.getRetRoomId(), 
					info.getRetRackId(),"P", user.getCompanyId(), user.getId(), time);
			jdbcTemplate.update("UPDATE lims_qc_req_infos SET status ='R' where id = ?", info.getQcReqId());
		}
			
		
	}
	
	public void saveDistributionInfo(DistributionInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
			jdbcTemplate.update(
					"insert into lims_distribution_infos (sample_rcv_id, test_parameter_no, test_parameter_id, specification, "
					+ " reference_id, test_method_id,test_unit_id, ecuipment_id, analyst_id, status,param_type, sample_id,lod, company_id, created_by, created_at) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					 info.getSampleRcvId(), info.getDistParameterNo(), info.getDistParameterId(), info.getDistSpecification(),
					 info.getDistReferenceId(),info.getDistMethodId(),info.getDistTestUnitId(),info.getDistEcuipmentId().trim().replace("@",","), 
					 info.getDistAnalystId(),"P","C", info.getSampleId(),info.getDistLod(), user.getCompanyId(), user.getId(), time);
	
		
	}
	
	public void updateDistributionInfo(UUID sampleRcvId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
		jdbcTemplate.update("UPDATE lims_qc_sample_rcv_infos SET status ='D', chemi_dist_by =?, chemi_dist_at =? where id = ?", user.getId(), time, sampleRcvId);
		
	}
	
	class ReceiveInfoRowMapper implements RowMapper<CommonInfo> {
		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {			
			CommonInfo info = new CommonInfo();
			
			  info.setId((UUID)rs.getObject("id"));
			  info.setUdCssNo(rs.getString("ud_css_no"));
			  info.setWhRequestId((UUID)rs.getObject("wh_request_id"));
			  info.setReqDate(rs.getString("qc_req_dt"));
			  info.setStatus(rs.getString("dist_status"));
			  info.setReqType((UUID)rs.getObject("req_type"));
			  info.setReqTypeName(rs.getString("req_type_name"));
			  info.setEffectivDate(rs.getDate("effective_date"));
			  info.setRevisionNo(rs.getString("revision_no"));
			  info.setFormNo(rs.getString("form_no"));
			  info.setInvoiceNo(rs.getString("invoice_no"));
			  info.setChalanNo(rs.getString("chalan_no"));
			  info.setLcNo(rs.getString("lc_no"));
			  info.setFromDeptNo((UUID)rs.getObject("from_dept_no"));
			  info.setFromDeptName(rs.getString("from_dept_nm"));
			  info.setToDeptNo((UUID)rs.getObject("to_dept_no"));
			  info.setToDeptName(rs.getString("to_dept_nm"));
			  info.setGrNo(rs.getString("gr_no"));
			  info.setMaterialId((UUID)rs.getObject("material_id"));
			  info.setMaterialName(rs.getString("material_name"));
			  info.setMaterialCode(rs.getString("material_code"));
			  info.setMaterialTypeId((UUID)rs.getObject("material_type_id"));
			  info.setMaterialTypeName(rs.getString("material_type_nm"));
			  info.setRcvQty(rs.getString("rcv_qty"));
			  info.setManufactureName(rs.getString("manufacture_name"));
			  info.setCountryId((UUID)rs.getObject("country_id"));
			  info.setCountryName(rs.getString("country_nm"));
			  info.setSampleDetails(rs.getString("sample_details"));
			  info.setIsChemical(rs.getString("is_chemical"));
			  info.setIsMicrobiological(rs.getString("is_microbiological"));
			  info.setChemiSamAmt(rs.getString("chemical_sample_amt"));
			  info.setMicroSamAmt(rs.getString("micro_sample_amt"));
			  info.setTotalAmount(rs.getString("total_amt"));
			  info.setTotalSamAmt(rs.getString("total_sample_amt"));
			  info.setTotalRetAmt(rs.getString("total_retention_amt"));
			  info.setMatUnitName(rs.getString("mat_unit_name"));
			  info.setUdSampleNo(rs.getString("ud_sample_no"));
			  info.setClientName(rs.getString("client_name"));
			  info.setClientId(rs.getString("client_id"));
			  info.setMethodName(rs.getString("method_name"));
			  info.setStorageCondition(rs.getString("storage_condition"));
			  info.setSampleProcedure(rs.getString("sample_procedure"));
			  info.setBoothName(rs.getString("booth_name"));
			  info.setEquipmentName(rs.getString("equipment_name"));
			  info.setWorkInstName(rs.getString("inst_name"));
			  info.setPrecautionTaken(rs.getString("precaution_taken"));
			  info.setSamplingDate(rs.getDate("sampling_date"));
			  info.setSamplingByName(rs.getString("sampling_name"));
			  info.setRemarks(rs.getString("sample_desc"));
			  info.setReceiveDate(rs.getTimestamp("receive_at"));
			  info.setChemiDistDate(rs.getTimestamp("chemi_dist_at"));
			  info.setMicroDistDate(rs.getTimestamp("micro_dist_at"));
			  info.setSampleRcvDesc(rs.getString("sample_desc_rcv"));
			  info.setArnNo(rs.getString("arn_no"));
			  info.setStChemiQty(rs.getString("st_chemi_qty"));
			  info.setStMicroQty(rs.getString("st_micro_qty"));
			  info.setStqty(rs.getString("st_qty"));
			  info.setStUnitName(rs.getString("st_unit_name"));
			  info.setStRoomName(rs.getString("st_room_name"));
			  info.setStEquipmentName(rs.getString("st_equ_name"));
			  info.setStRackName(rs.getString("st_rack_name"));
			  info.setRetqty(rs.getString("ret_qty"));
			  info.setRetUnitName(rs.getString("ret_unit_name"));
			  info.setRetEquipmentName(rs.getString("ret_equ_name"));
			  info.setRetRoomName(rs.getString("ret_room_name"));
			  info.setRetRackName(rs.getString("ret_rack_name"));
			  info.setSampleId((UUID)rs.getObject("sample_id"));
			  info.setIsDecision(rs.getString("is_decision"));
			  info.setProviderType(rs.getString("provider_type"));
			  info.setKeptAmount(rs.getString("kept_amount"));
			  if(rs.getDate("sche_from_date") !=null) {
				info.setSchFromDate(rs.getDate("sche_from_date"));  
			  }else {
				  info.setSchFromDate(null); 
			  }
			  
			  if(rs.getDate("sche_to_date") !=null) {
					info.setSchToDate(rs.getDate("sche_to_date"));  
				  }else {
					  info.setSchToDate(null); 
				  }
			  
			return info;
		}
	}

	
	
	
	
}
