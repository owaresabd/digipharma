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
import com.pms.model.MaterialInfo;
import com.pms.model.PtSampleInfo;
import com.pms.model.User;
import com.pms.model.WhRequestDetailsInfo;
import com.pms.model.WhRequestInfo;
import com.pms.service.IUserService;

@Repository
@Transactional
public class PtSampleRepository {

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
				+ " b.rcv_qty, b.manufacture_name,  b.country_id,fnc_country_nm(b.country_id) country_nm, b.sample_details, b.req_status wh_status,"
				+ " c.is_chemical,c.is_microbiological, c.chemical_sample_amt, c.micro_sample_amt, c.total_amt, c.unit_id,fnc_unit_nm(c.unit_id) mat_unit_name,"
				+ " d.ud_sample_no,d.client_name, d.client_id, fnc_method_name(d.method_id) method_name,d.storage_con_id, d.storage_condition, "
				+ " d.sample_procedure, fnc_booth_name(d.area_booth_id) booth_name, fnc_equipment_name(d.equipment_id) equipment_name,"
				+ " fnc_inst_name(d.work_ins_id) inst_name, d.precaution_taken, d.sampling_date, fnc_emp_name(d.sampling_by) sampling_name,d.status , d.remarks,"
				+ " e.ud_qc_no, e.sample_id, e.sample_desc, e.uncertinity, coalesce(to_char( e.qc_req_dt, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as qc_req_dt, e.is_decision, e.status qc_status, "
				+ " (CAST((CASE WHEN c.chemical_sample_amt ='' THEN '0' ELSE  c.chemical_sample_amt END)  as float) + CAST((CASE WHEN c.micro_sample_amt ='' THEN '0' ELSE  c.micro_sample_amt END)  as float)) total_sample_amt, "
				+ " (CAST((CASE WHEN c.chemical_retention_amt ='' THEN '0' ELSE  c.chemical_retention_amt END)  as float) + CAST((CASE WHEN c.micro_retention_amt ='' THEN '0' ELSE  c.micro_retention_amt END)  as float)) total_retention_amt "
				+ " from lims_css_request_infos a,"
				+ " lims_wh_request_infos b,"
				+ " lims_material_infos c,"
				+ " lims_sampling_infos d,"
				+ " lims_qc_req_infos e "
				+ "  where  a.wh_request_id=b.id "
				+ " and b.material_id=c.id "
				+ " and a.id=d.css_request_id "
				+ " and d.id=e.sample_id "
				+ " and b.provider_type in ('PT','AV')"
				+ " and e.company_id = ? ORDER BY e.qc_req_dt DESC",
				new Object[] { user.getCompanyId() }, new QcInfoRowMapper());
	}

	

	public List<MaterialInfo> findAllPtProduct(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select a.id, material_name, material_code, material_type_id, fnc_material_type_nm(a.material_type_id) material_type_nm, is_chemical, is_microbiological,"
				+ " chemical_sample_amt, chemical_retention_amt, chemical_total, micro_sample_amt, micro_retention_amt, "
				+ " micro_total, total_amt,unit_id,fnc_unit_nm(unit_id) unit_name, a.status, storage_con_id, storage_condition, mat_sample_procedure, mat_method_id, "
				+ " fnc_method_name(mat_method_id) sample_method_name "
				+ " from lims_material_infos a, lims_material_type_infos b"
				+ " where a.material_type_id=b.id  "
				+ " and a.status=" + (status != null ? "'" + status + "'" : "a.status") + " "
				+ " and b.type_code in ('PT','AV') "
				+ " and a.company_id = ?   order by material_name ",
				new Object[] { user.getCompanyId() }, new MaterialInfoRowMapper());
	}
	class MaterialInfoRowMapper implements RowMapper<MaterialInfo> {

		@Override
		public MaterialInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			MaterialInfo info = new MaterialInfo();
			
			info.setId((UUID)rs.getObject("id"));
			info.setMaterialName(rs.getString("material_name"));
			info.setMaterialCode(rs.getString("material_code"));
			info.setMaterialTypeId((UUID)rs.getObject("material_type_id"));
			info.setMaterialTypeName(rs.getString("material_type_nm"));
			info.setIsChemical(rs.getString("is_chemical"));
			info.setIsMicrobiological(rs.getString("is_microbiological"));
			info.setChemicalSampleAmt(rs.getString("chemical_sample_amt"));
			info.setChemicalRetentionAmt(rs.getString("chemical_retention_amt"));
			info.setChemicalTotal(rs.getString("chemical_total"));
			info.setMicroSampleAmt(rs.getString("micro_sample_amt"));
			info.setMicroRetentionAmt(rs.getString("micro_retention_amt"));
			info.setMicroTotal(rs.getString("micro_total"));
			info.setTotalAmt(rs.getString("total_amt"));
			info.setUnitId((UUID)rs.getObject("unit_id"));
			info.setUnitName(rs.getString("unit_name"));
			info.setStatus(rs.getString("status"));
			info.setStorageConId((UUID)rs.getObject("storage_con_id"));
			info.setStorageCondition(rs.getString("storage_condition"));
			info.setMatSamProcedure(rs.getString("mat_sample_procedure"));
			info.setMatMethodId((UUID)rs.getObject("mat_method_id"));
			info.setSamplePlanName(rs.getString("sample_method_name"));
			return info;
		}
	}
	public void savePtSampleInfo(PtSampleInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		jdbcTemplate.update(
				" insert into lims_wh_request_infos (id,req_type,provider_type,sche_from_date,sche_to_date,material_id,material_code,material_type_id,rcv_qty,unit_id,"
				+ " sample_details,req_status,company_id,created_by,created_at)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				info.getWhRequestId(),info.getReqTypeId(), info.getTypeCode(),info.getFromDate(),info.getToDate(),info.getMaterialId(),info.getMaterialCode(),
				info.getMaterialTypeId(),info.getQuantity(),info.getUnitId(),info.getSampleDetails(),info.getStatus(),user.getCompanyId(),user.getId(), time);
			 jdbcTemplate.update("UPDATE lims_wh_request_infos SET ud_wh_req_no ='"+reqIdGeneration(info.getWhRequestId())+"' where id = ?", info.getWhRequestId());
			    
		

	}
	public   String  reqIdGeneration(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		StringBuilder udReqId  = new StringBuilder(); 
		DateFormat df = new SimpleDateFormat("yyMM"); // Just the year, with 2 digits
		String year = df.format(Calendar.getInstance().getTime());
		
		CommonInfo info = jdbcTemplate.queryForObject("SELECT COALESCE(CAST(MAX(RIGHT(ud_wh_req_no,4)) as integer),0)+1 max_id  "
				+ " from lims_wh_request_infos where provider_type in ('PT','AV')  ", new IdInfoRowMapper());
			
		 udReqId.append("PT").append(year).append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
		return udReqId.toString();
	}
	public void savePtSampleDetailsInfo(PtSampleInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
					"INSERT INTO lims_wh_request_details_infos (id,wh_request_id,mfg_date,exp_date,batch_lot,"
					+ " batch_no,quantity,unit_id, pack_size,company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
					 info.getWhRequestChdId(),info.getWhRequestId(), info.getMfgDate(), info.getExpDate(), info.getBatchLot(), info.getBatchNo(), 
					 info.getQuantity(),info.getUnitId(), info.getPackSize(), user.getCompanyId(), user.getId(), time);
		
	}
	public void updatePtSampleInfo(PtSampleInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
		jdbcTemplate.update(
				" update  lims_wh_request_infos set req_type=?,provider_type=?,sche_from_date=?,sche_to_date=?,material_id=?,"
				+ " material_code=?,material_type_id=?,rcv_qty=?,unit_id=?,"
				+ " sample_details=?,updated_by=?, updated_at=? WHERE id=?",
				info.getReqTypeId(), info.getTypeCode(),info.getFromDate(),info.getToDate(),info.getMaterialId(),info.getMaterialCode(),
				info.getMaterialTypeId(),info.getQuantity(),info.getUnitId(),info.getSampleDetails(),user.getId(), time,info.getWhRequestId());
		jdbcTemplate.update(
					" update lims_wh_request_details_infos set mfg_date=?,exp_date=?,batch_lot=?,batch_no=?,"
					+ " quantity=?,unit_id=?, pack_size=?,updated_by=?, updated_at=? where id=?",
					  info.getMfgDate(), info.getExpDate(), info.getBatchLot(), info.getBatchNo(), 
					  info.getQuantity(),info.getUnitId(), info.getPackSize(), user.getId(), time,
					  info.getWhRequestChdId());
		jdbcTemplate.update(
				" update  lims_sampling_infos set client_name=? ,storage_con_id=?,remarks=?,updated_by=?, updated_at=? where id=?",
				info.getSupplierName(), info.getStorageConId(),info.getRemarks(),user.getId(), time,info.getSampleId());
		jdbcTemplate.update(
				" update lims_qc_req_infos set sample_desc=?,updated_by=?, updated_at=? where id=?",
				info.getSampleDetails(),user.getId(), time,info.getQcReqId());
		
		
	}
	
	public void saveCssRequestInfos(PtSampleInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				" insert into lims_css_request_infos (id,wh_request_id, req_by, req_status,req_at, company_id, created_by, created_at) "
				+ " VALUES (?,?,?,?,?,?,?,?)",
				info.getCssReqId(), info.getWhRequestId(),user.getId(), info.getStatus(),time, user.getCompanyId(), user.getId(), time);
		jdbcTemplate.update("UPDATE lims_css_request_infos SET ud_css_no ='"+serviceIdGen()+"' where wh_request_id = ?", info.getWhRequestId());
		
		

	}
	public   String  serviceIdGen() {
		jdbcTemplate = new JdbcTemplate(datasource);
		StringBuilder udReqId  = new StringBuilder(); 
		DateFormat df = new SimpleDateFormat("yyMM"); // Just the year, with 2 digits
		String year = df.format(Calendar.getInstance().getTime());
		
		CommonInfo info = jdbcTemplate.queryForObject("SELECT COALESCE(CAST(MAX(RIGHT(ud_css_no,4)) as integer),0)+1 max_id  "
				+ " from lims_css_request_infos", new IdInfoRowMapper());
			
		 udReqId.append("PT").append(year).append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
		
		
		
		return udReqId.toString();
	}
	
	public void saveSampleInfos(PtSampleInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				" insert into lims_sampling_infos(id,css_request_id,client_name ,storage_con_id,remarks,status, company_id,created_by,created_at) "
				+ " VALUES (?,?,?,?,?,?,?,?,?)",
				info.getSampleId(),info.getCssReqId(),info.getSupplierName(), info.getStorageConId(),info.getRemarks(),info.getStatus(), user.getCompanyId(), user.getId(), time);
				jdbcTemplate.update("UPDATE lims_sampling_infos SET ud_sample_no ='"+sampleIdGeneration(info.getSampleId())+"' where id = ?", info.getSampleId());
				
				
		
	}
	public   String  sampleIdGeneration(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		StringBuilder udSampleId  = new StringBuilder(); 
		DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
		String year = df.format(Calendar.getInstance().getTime());
		
		CommonInfo info = jdbcTemplate.queryForObject("SELECT  a.type_code,b.is_chemical,b.is_microbiological,b.sample_id , " + 
				"(SELECT COALESCE(CAST(MAX(RIGHT(ud_sample_no,4)) as integer),0)+1 from view_sample_infos where type_code in ('PT','AV') )  max_id ,b.material_name " + 
				"from lims_material_type_infos a ,view_sample_infos b " + 
				"where a.id=b.material_type_id and b.sample_id=?",new Object[] { id}, new SampleIdInfoRowMapper());
		if(info.getIsChemical().equals("Y") && info.getIsMicrobiological().equals("Y")) {
			udSampleId.append("CM").append(year).append(info.getMaterialTypeName()).append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
		}else if(info.getIsChemical().equals("Y") && info.getIsMicrobiological().equals("N")) {
			
			udSampleId.append("C").append(year).append(info.getMaterialTypeName()).append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
			
		}else if(info.getIsChemical().equals("N") && info.getIsMicrobiological().equals("Y")) {
			
			udSampleId.append("M").append(year).append(info.getMaterialTypeName()).append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
			
		}
		
		
		return udSampleId.toString();
	}
	public void saveQCReqInfos(PtSampleInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				" insert into lims_qc_req_infos(id,sample_id,sample_desc,qc_req_dt,is_decision,status,company_id,created_by,created_at) "
				+ " VALUES (?,?,?,?,?,?,?,?,?)",
				info.getQcReqId(),info.getSampleId(),info.getSampleDetails(),time,info.getIsDecision(),info.getStatus(), user.getCompanyId(), user.getId(), time);
		
		

	}
	public void sendQCReqInfos(PtSampleInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("UPDATE lims_qc_req_infos SET status ='P' where id = '"+info.getQcReqId()+"'");
	}
	public List<WhRequestDetailsInfo> getWhRequestDetailsById(UUID whRequestId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();

		return jdbcTemplate.query("select id, wh_request_id, gr_no, mfg_date, exp_date, batch_lot, batch_no, quantity,unit_id,fnc_unit_nm(unit_id) unit_name, number_of_drums, pack_size "
				+ " from lims_wh_request_details_infos where wh_request_id= ? and company_id = ?",
				new Object[] { whRequestId, user.getCompanyId() }, new WhRequestDetailsInfoRowMapper());
	}
	
	public void deleteWhRequestDetailInfos(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM lims_wh_request_details_infos WHERE wh_request_id = ?", new Object[] { id });
	  }
	
	public WhRequestInfo getWhRequestInfoById(UUID id){

		jdbcTemplate = new JdbcTemplate(datasource);
		return (WhRequestInfo)jdbcTemplate.queryForObject("select id,ud_wh_req_no,req_type,fnc_req_type_nm(req_type) req_type_name, effective_date, revision_no,"
				+ "  form_no, invoice_no, chalan_no, lc_no, from_dept_no, fnc_dept_nm(from_dept_no) from_dept_nm, to_dept_no,fnc_dept_nm(to_dept_no) to_dept_nm,"
				+ "  gr_no, material_id, fnc_material_nm(material_id) material_name, material_code, material_type_id, fnc_material_type_nm(material_type_id) material_type_nm,"
				+ " rcv_qty,unit_id,fnc_unit_nm(unit_id) unit_name, manufacture_name,"
				+ "  country_id,fnc_country_nm(country_id) country_nm, sample_details, req_status from lims_wh_request_infos where id = ?",
				 new Object[] { id }, new WhRequestInfoRowMapper());
	}
	
	class ProductInfoRowMapper implements RowMapper<CommonInfo> {
		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CommonInfo info = new CommonInfo();
			
			info.setMaterialCode(rs.getString("material_code"));
			info.setMaterialTypeId((UUID)rs.getObject("material_type_id"));
			info.setBatchNo(rs.getString("batch_no"));
			info.setLotNo(rs.getString("lot_no"));
			info.setMfgDate(rs.getDate("mfg_date"));
			info.setExpDate(rs.getDate("exp_date"));
			info.setBatchSize(rs.getString("batch_size"));
			info.setUnitId((UUID)rs.getObject("unit_id"));
			info.setUnitName(rs.getString("unit_name"));
			info.setPackSize(rs.getString("pack_size"));
			
			return info;
		}
	}
	class SampleIdInfoRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CommonInfo info = new CommonInfo();
			info.setMaterialTypeName(rs.getString("type_code"));
			info.setIsChemical(rs.getString("is_chemical"));
			info.setIsMicrobiological(rs.getString("is_microbiological"));
			info.setMaxId(rs.getString("max_id"));
			
			return info;
			
		}
		
	}
	class IdInfoRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CommonInfo info = new CommonInfo();
			info.setMaxId(rs.getString("max_id"));
			
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
			  info.setRemarks(rs.getString("sample_desc"));
			  info.setSampleId((UUID)rs.getObject("sample_id"));
			  info.setTypeCode(rs.getString("provider_type"));
			  info.setSchFromDate(rs.getDate("sche_from_date"));
			  info.setSchToDate(rs.getDate("sche_to_date"));
			return info;
		}
		}
	class WhRequestInfoRowMapper implements RowMapper<WhRequestInfo> {

		@Override
		public WhRequestInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			WhRequestInfo info = new WhRequestInfo();
			
			info.setId((UUID)rs.getObject("id"));
			info.setUdWhReqNo(rs.getString("ud_wh_req_no"));
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
			info.setRcvUnitId((UUID)rs.getObject("unit_id"));
			info.setRcvUnitName(rs.getString("unit_name"));
			info.setManufactureName(rs.getString("manufacture_name"));
			info.setCountryId((UUID)rs.getObject("country_id"));
			info.setCountryName(rs.getString("country_nm"));
			info.setSampleDetails(rs.getString("sample_details"));
			info.setStatus(rs.getString("req_status"));
			return info;
		}
	}
	
	class WhRequestDetailsInfoRowMapper implements RowMapper<WhRequestDetailsInfo> {

		@Override
		public WhRequestDetailsInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			WhRequestDetailsInfo detailsInfo = new WhRequestDetailsInfo();
			detailsInfo.setChildId((UUID)rs.getObject("id"));
			detailsInfo.setWhRequestId((UUID)rs.getObject("wh_request_id"));
			detailsInfo.setMfgDate(rs.getDate("mfg_date"));
			detailsInfo.setExpDate(rs.getDate("exp_date"));
			detailsInfo.setBatchLot(rs.getString("batch_lot"));
			detailsInfo.setBatchNo(rs.getString("batch_no"));
			detailsInfo.setQuantity(rs.getString("quantity"));
			detailsInfo.setUnitId((UUID)rs.getObject("unit_id"));
			detailsInfo.setUnitName(rs.getString("unit_name"));
			detailsInfo.setNoOfDrums(rs.getString("number_of_drums"));
			detailsInfo.setPackSize(rs.getString("pack_size"));
			return detailsInfo;
		}
	}
	
	
}
