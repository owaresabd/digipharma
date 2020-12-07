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
import com.pms.model.QcSampleRecvInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class QcRepository {

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
				" select e.id,a.ud_css_no, a.wh_request_id,a.req_by,coalesce(to_char( a.req_at, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as req_at,a.req_status css_status,b.sche_from_date, b.sche_to_date ,"
				+ " b.req_type,fnc_req_type_nm(b.req_type) req_type_name, b.effective_date, b.revision_no, b.form_no, b.invoice_no, b.chalan_no, b.lc_no,"
				+ "  b.from_dept_no, fnc_dept_nm(b.from_dept_no) from_dept_nm, b.to_dept_no,fnc_dept_nm(b.to_dept_no) to_dept_nm,provider_type,"
				+ " b.gr_no, b.material_id, fnc_material_nm(b.material_id) material_name, b.material_code, b.material_type_id, fnc_material_type_nm(b.material_type_id) material_type_nm,"
				+ " b.rcv_qty, b.manufacture_name,  b.country_id,fnc_country_nm(b.country_id) country_nm, b.sample_details, b.req_status wh_status,b.kept_amount,"
				+ " c.is_chemical,c.is_microbiological, c.chemical_sample_amt, c.micro_sample_amt, c.total_amt, c.unit_id,fnc_unit_nm(c.unit_id) mat_unit_name,d.css_request_id,"
				+ " d.ud_sample_no,d.client_name, d.client_id, fnc_method_name(d.method_id) method_name,d.storage_con_id, d.storage_condition, "
				+ " d.sample_procedure, fnc_booth_name(d.area_booth_id) booth_name, fnc_equipment_name(d.equipment_id) equipment_name,"
				+ " fnc_inst_name(d.work_ins_id) inst_name, d.precaution_taken, d.sampling_date, fnc_emp_name(d.sampling_by) sampling_name,d.status , d.remarks,"
				+ " e.ud_qc_no, e.sample_id, e.sample_desc, e.uncertinity, coalesce(to_char( e.qc_req_dt, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as qc_req_dt, e.is_decision, e.status qc_status, "
				+ " (CAST((CASE WHEN c.chemical_sample_amt ='' THEN '0' ELSE  c.chemical_sample_amt END)  as float) + CAST((CASE WHEN c.micro_sample_amt ='' THEN '0' ELSE  c.micro_sample_amt END)  as float)) total_sample_amt, "
				+ " (CAST((CASE WHEN c.chemical_retention_amt ='' THEN '0' ELSE  c.chemical_retention_amt END)  as float) + CAST((CASE WHEN c.micro_retention_amt ='' THEN '0' ELSE  c.micro_retention_amt END)  as float)) total_retention_amt "
				+ " from lims_css_request_infos a,"
				+ " lims_wh_request_infos b, "
				+ " lims_material_infos c, "
				+ " lims_sampling_infos d,"
				+ " lims_qc_req_infos e "
				+ " where  a.wh_request_id=b.id and b.material_id=c.id "
				+ " and a.id=d.css_request_id and d.id=e.sample_id "
				+ " and e.status='P' and e.company_id = ? "
				+ " ORDER BY e.qc_req_dt DESC",
				new Object[] { user.getCompanyId() }, new QcInfoRowMapper());
	}

	
	public CommonInfo getQcInfo(UUID id){
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject(" select e.id,a.ud_css_no, a.wh_request_id,a.req_by,coalesce(to_char( a.req_at, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as req_at,a.req_status css_status,b.sche_from_date, b.sche_to_date ,"
				+ " b.req_type,fnc_req_type_nm(b.req_type) req_type_name, b.effective_date, b.revision_no, b.form_no, b.invoice_no, b.chalan_no, b.lc_no,"
				+ " b.from_dept_no, fnc_dept_nm(b.from_dept_no) from_dept_nm, b.to_dept_no,fnc_dept_nm(b.to_dept_no) to_dept_nm,b.provider_type ,"
				+ " b.gr_no, b.material_id, fnc_material_nm(b.material_id) material_name, b.material_code, b.material_type_id, fnc_material_type_nm(b.material_type_id) material_type_nm,"
				+ " b.rcv_qty, b.manufacture_name,  b.country_id,fnc_country_nm(b.country_id) country_nm, b.sample_details, b.req_status wh_status,b.kept_amount,"
				+ " c.is_chemical,c.is_microbiological, c.chemical_sample_amt, c.micro_sample_amt, c.total_amt,"
				+ " (CASE WHEN (b.provider_type ='PT' or b.provider_type ='AV') THEN b.unit_id  ELSE c.unit_id  END) as  unit_id,"
				+ " (CASE WHEN (b.provider_type ='PT' or b.provider_type ='AV') THEN fnc_unit_nm(b.unit_id)  ELSE fnc_unit_nm(c.unit_id)  END) as  mat_unit_name,d.css_request_id,"
				+ " d.ud_sample_no,d.client_name, d.client_id, fnc_method_name(d.method_id) method_name,d.storage_con_id, "
				+ " (CASE WHEN d.storage_condition IS NULL  THEN fnc_con_name(d.storage_con_id)  ELSE d.storage_condition  END) as storage_condition , "
				+ " d.sample_procedure, fnc_booth_name(d.area_booth_id) booth_name, fnc_equipment_name(d.equipment_id) equipment_name,"
				+ " fnc_inst_name(d.work_ins_id) inst_name, d.precaution_taken, d.sampling_date, fnc_emp_name(d.sampling_by) sampling_name,d.status , d.remarks,"
				+ " e.ud_qc_no, e.sample_id, e.sample_desc, e.uncertinity, coalesce(to_char( e.qc_req_dt, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as qc_req_dt, e.is_decision, e.status qc_status, "
				+ " (CAST((CASE WHEN c.chemical_sample_amt ='' THEN '0' ELSE  c.chemical_sample_amt END)  as float) + CAST((CASE WHEN c.micro_sample_amt ='' THEN '0' ELSE  c.micro_sample_amt END)  as float)) total_sample_amt, "
				+ " (CAST((CASE WHEN c.chemical_retention_amt ='' THEN '0' ELSE  c.chemical_retention_amt END)  as float) + CAST((CASE WHEN c.micro_retention_amt ='' THEN '0' ELSE  c.micro_retention_amt END)  as float)) total_retention_amt "
				+ " from lims_css_request_infos a, lims_wh_request_infos b, lims_material_infos c, lims_sampling_infos d, lims_qc_req_infos e "
				+ "  where  a.wh_request_id=b.id "
				+ " and b.material_id=c.id "
				+ " and a.id=d.css_request_id "
				+ " and d.id=e.sample_id "
				+ " and e.id = ?",
				 new Object[] { id}, new QcInfoRowMapper());
	}
	public List<CommonInfo> receivedList() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, ud_css_no, wh_request_id, req_by, req_at, css_status, req_type, req_type_name, effective_date,"
				+ " revision_no, form_no, invoice_no, chalan_no, lc_no, from_dept_no, from_dept_nm, to_dept_no, to_dept_nm,"
				+ " gr_no, material_id, material_name, material_code, material_type_id, material_type_nm, rcv_qty, manufacture_name,"
				+ " country_id, country_nm, sample_details, wh_status, is_chemical, is_microbiological, total_amt,mat_unit_name, ud_sample_no, client_name,"
				+ " client_id, method_name, storage_condition, sample_procedure, booth_name, equipment_name, inst_name, precaution_taken,"
				+ " sampling_date, sampling_name, status, remarks, ud_qc_no, sample_id, sample_desc, uncertinity, qc_req_dt, is_decision,"
				+ " qc_status, company_id, qc_req_id, arn_no, sample_desc_rcv, st_qty, st_unit_id, st_unit_name, st_equ_id, st_equ_name,"
				+ " st_room_id, st_room_name, st_rack_id, st_rack_name, ret_qty, ret_unit_id, ret_unit_name, ret_equ_id, ret_equ_name,"
				+ " ret_room_id, ret_room_name, ret_rack_id, ret_rack_name, dist_status, receive_at " + 
				"	FROM view_sample_rcv_infos where qc_status='R' and  company_id = ? ORDER BY receive_at DESC",
				new Object[] { user.getCompanyId() }, new ReceiveInfoRowMapper());
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
			  info.setTotalAmount(rs.getString("total_amt"));
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
			  info.setSampleRcvDesc(rs.getString("sample_desc_rcv"));
			  info.setArnNo(rs.getString("arn_no"));
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
			return info;
		}
	}
	public void saveSampleRcvInfo(QcSampleRecvInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
			jdbcTemplate.update(
					"INSERT INTO lims_qc_sample_rcv_infos (qc_req_id, sample_desc, st_chemi_qty, st_micro_qty, st_qty,st_unit_id, st_equ_id, st_room_id, "
					+ "st_rack_id, status,micro_dist_status, sample_id, company_id, created_by, created_at) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",info.getQcReqId(), info.getSampleDesc(), info.getStChemiQty(), info.getStMicroQty(), info.getStQty(),info.getStUnitId(), info.getStEquId(), 
					info.getStRoomId(), info.getStRackId(), "P","P", info.getSampleId(), user.getCompanyId(), user.getId(), time);
			jdbcTemplate.update("update lims_qc_sample_rcv_infos set arn_no ='"+arnNoGen(info.getSampleId())+"' where sample_id = ?", info.getSampleId());
			jdbcTemplate.update("UPDATE lims_qc_req_infos SET status ='R' where id = ?", info.getQcReqId());
			
			
		
	}
	public   String  arnNoGen(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		StringBuilder udSampleId  = new StringBuilder(); 
		DateFormat df = new SimpleDateFormat("yyMM"); // Just the year, with 2 digits
		String year = df.format(Calendar.getInstance().getTime());
		
		CommonInfo info = jdbcTemplate.queryForObject("SELECT a.type_no,c.company_code,b.is_chemical,b.is_microbiological,b.sample_id , " + 
				"(SELECT COALESCE(CAST(MAX(RIGHT(arn_no,4)) as integer),0)+1 from lims_qc_sample_rcv_infos)  max_id ,b.material_name,b.is_working_standard " + 
				"from lims_material_type_infos a ,view_sample_infos b , lims_companies c " + 
				"where a.id=b.material_type_id and c.id=b.company_id and b.sample_id=?",new Object[] { id}, new IdInfoRowMapper());
	
		if(info.getMaterialTypeName().equals("WS")) {
			udSampleId.append("ARN-").append("WS-") .append(year).append("-").append(StringUtils.leftPad(info.getMaxId(), 4, "0"));	
		}else {
		if(info.getIsChemical().equals("Y") && info.getIsMicrobiological().equals("Y")) {
			udSampleId.append("ARN-").append("GN-") .append(year).append("-").append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
		}else if(info.getIsChemical().equals("Y") && info.getIsMicrobiological().equals("N")) {
			
			udSampleId.append("ARN-").append("CM-") .append(year).append("-").append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
			
		}else if(info.getIsChemical().equals("N") && info.getIsMicrobiological().equals("Y")) {
			
			udSampleId.append("ARN-").append("MC-") .append(year).append("-").append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
			
		}
		
		}
		return udSampleId.toString();
	}
	class IdInfoRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CommonInfo info = new CommonInfo();
			info.setCompanyCode(rs.getString("company_code"));
			info.setMaterialTypeName(rs.getString("type_no"));
			info.setIsChemical(rs.getString("is_chemical"));
			info.setIsMicrobiological(rs.getString("is_microbiological"));
			info.setIsWorkingStandard(rs.getString("is_working_standard"));
			info.setMaxId(rs.getString("max_id"));
			System.out.println();
			return info;
			
		}
		
	}
	
	class QcInfoRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CommonInfo info = new CommonInfo();
			
			  info.setId((UUID)rs.getObject("id"));
			  info.setUdCssNo(rs.getString("ud_css_no"));
			  info.setWhRequestId((UUID)rs.getObject("wh_request_id"));
			  info.setReqDate(rs.getString("qc_req_dt"));
			  info.setStatus(rs.getString("qc_status"));
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
			  info.setUnitId((UUID)rs.getObject("unit_id"));
			  info.setMatUnitName(rs.getString("mat_unit_name"));
			  info.setUdSampleNo(rs.getString("ud_sample_no"));
			  info.setClientName(rs.getString("client_name"));
			  info.setClientId(rs.getString("client_id"));
			  info.setMethodName(rs.getString("method_name"));
			  info.setStorageConId((UUID)rs.getObject("storage_con_id"));
			  info.setStorageCondition(rs.getString("storage_condition"));
			  info.setSampleProcedure(rs.getString("sample_procedure"));
			  info.setBoothName(rs.getString("booth_name"));
			  info.setEquipmentName(rs.getString("equipment_name"));
			  info.setWorkInstName(rs.getString("inst_name"));
			  info.setPrecautionTaken(rs.getString("precaution_taken"));
			  info.setSamplingDate(rs.getDate("sampling_date"));
			  info.setSamplingByName(rs.getString("sampling_name"));
			  info.setCssRequestId((UUID)rs.getObject("css_request_id"));
			  if(rs.getString("provider_type").equals("PT") || rs.getString("provider_type").equals("AV")){
			  info.setRemarks(rs.getString("remarks"));
			  
			  }else {
				  
				  info.setRemarks(rs.getString("sample_desc")); 
				  
			  }
			  info.setSampleId((UUID)rs.getObject("sample_id"));
			  info.setTypeCode(rs.getString("provider_type"));
			  info.setSchFromDate(rs.getDate("sche_from_date"));
			  info.setSchToDate(rs.getDate("sche_to_date"));
			  info.setKeptAmount(rs.getString("kept_amount"));
			  
			return info;
		}
	}
	
	
	
}
