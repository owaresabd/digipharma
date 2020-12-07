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

import com.pms.model.QcInfo;
import com.pms.model.SamplingInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class SamplingRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<SamplingInfo> findAll() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select d.id,a.ud_css_no, a.wh_request_id,a.req_by,coalesce(to_char( a.req_at, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as req_at,a.req_status css_status,"
				+ " b.req_type,fnc_req_type_nm(b.req_type) req_type_name, b.effective_date, b.revision_no, b.form_no, b.invoice_no, b.chalan_no, b.lc_no,"
				+ "  b.from_dept_no, fnc_dept_nm(b.from_dept_no) from_dept_nm, b.to_dept_no,fnc_dept_nm(b.to_dept_no) to_dept_nm,"
				+ " b.gr_no, b.material_id, fnc_material_nm(b.material_id) material_name, b.material_code, b.material_type_id,e.type_code, fnc_material_type_nm(b.material_type_id) material_type_nm,"
				+ " b.rcv_qty, b.manufacture_name,  b.country_id,fnc_country_nm(b.country_id) country_nm, b.sample_details, b.req_status wh_status, c.is_chemical,c.is_microbiological ,c.total_amt,fnc_unit_nm(c.unit_id) mat_unit_name,"
				+ " d.ud_sample_no,d.client_name, d.client_id, fnc_method_name(d.method_id) method_name, d.storage_condition, "
				+ " d.sample_procedure, (CASE WHEN d.sample_procedure='N'  THEN  fnc_booth_name(d.area_booth_id)  ELSE fnc_area_name(d.area_booth_id)  END) booth_name, fnc_equipment_name(d.equipment_id) equipment_name,"
				+ " fnc_inst_name(d.work_ins_id) inst_name, d.precaution_taken, d.sampling_date, fnc_emp_name(d.sampling_by) sampling_name,d.status , d.remarks, "
				+ " (CAST((CASE WHEN c.chemical_sample_amt ='' THEN '0' ELSE  c.chemical_sample_amt END)  as float) + CAST((CASE WHEN c.micro_sample_amt ='' THEN '0' ELSE  c.micro_sample_amt END)  as float)) total_sample_amt, "
				+ " (CAST((CASE WHEN c.chemical_retention_amt ='' THEN '0' ELSE  c.chemical_retention_amt END)  as float) + CAST((CASE WHEN c.micro_retention_amt ='' THEN '0' ELSE  c.micro_retention_amt END)  as float)) total_retention_amt "
				+ " from lims_css_request_infos a, lims_wh_request_infos b, lims_material_infos c, lims_sampling_infos d,lims_material_type_infos e "
				+ "  where a.wh_request_id=b.id and b.material_id=c.id "
				+ " and c.material_type_id=e.id "
				+ " and b.provider_type not in ('PT','AV') "
				+ " and a.id=d.css_request_id and d.status='P' and a.company_id = ? ORDER BY a.req_at DESC",
				new Object[] { user.getCompanyId() }, new SamplingInfoRowMapper());
	}
	
	public List<SamplingInfo> sentSampleList() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select d.id,a.ud_css_no, a.wh_request_id,a.req_by,coalesce(to_char( a.req_at, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as req_at,a.req_status css_status,"
				+ " b.req_type,fnc_req_type_nm(b.req_type) req_type_name, b.effective_date, b.revision_no, b.form_no, b.invoice_no, b.chalan_no, b.lc_no,"
				+ "  b.from_dept_no, fnc_dept_nm(b.from_dept_no) from_dept_nm, b.to_dept_no,fnc_dept_nm(b.to_dept_no) to_dept_nm,"
				+ " b.gr_no, b.material_id, fnc_material_nm(b.material_id) material_name, b.material_code, b.material_type_id,e.type_code, fnc_material_type_nm(b.material_type_id) material_type_nm,"
				+ " b.rcv_qty, b.manufacture_name,  b.country_id,fnc_country_nm(b.country_id) country_nm, b.sample_details, b.req_status wh_status, c.is_chemical,c.is_microbiological ,c.total_amt,fnc_unit_nm(c.unit_id) mat_unit_name,"
				+ " d.ud_sample_no,d.client_name, d.client_id, fnc_method_name(d.method_id) method_name, d.storage_condition, "
				+ " d.sample_procedure, (CASE WHEN d.sample_procedure='N'  THEN  fnc_booth_name(d.area_booth_id)  ELSE fnc_area_name(d.area_booth_id)  END) booth_name, fnc_equipment_name(d.equipment_id) equipment_name,"
				+ " fnc_inst_name(d.work_ins_id) inst_name, d.precaution_taken, d.sampling_date, fnc_emp_name(d.sampling_by) sampling_name,d.status , d.remarks, "
				+ " (CAST((CASE WHEN c.chemical_sample_amt ='' THEN '0' ELSE  c.chemical_sample_amt END)  as float) + CAST((CASE WHEN c.micro_sample_amt ='' THEN '0' ELSE  c.micro_sample_amt END)  as float)) total_sample_amt, "
				+ " (CAST((CASE WHEN c.chemical_retention_amt ='' THEN '0' ELSE  c.chemical_retention_amt END)  as float) + CAST((CASE WHEN c.micro_retention_amt ='' THEN '0' ELSE  c.micro_retention_amt END)  as float)) total_retention_amt "
				+ " from lims_css_request_infos a, lims_wh_request_infos b, lims_material_infos c, lims_sampling_infos d,lims_material_type_infos e "
				+ "  where a.wh_request_id=b.id and b.material_id=c.id "
				+ " and c.material_type_id=e.id "
				+ " and b.provider_type not in ('PT','AV') "
				+ " and a.id=d.css_request_id and d.status='Q' and a.company_id = ? ORDER BY a.req_at DESC",
				new Object[] { user.getCompanyId() }, new SamplingInfoRowMapper());
	}
	
	public SamplingInfo getSampleDetailsInfo(UUID id){
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject(" select d.id,a.ud_css_no, a.wh_request_id,a.req_by,coalesce(to_char( a.req_at, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as req_at,a.req_status css_status,"
				+ " b.req_type,fnc_req_type_nm(b.req_type) req_type_name, b.effective_date, b.revision_no, b.form_no, b.invoice_no, b.chalan_no, b.lc_no,"
				+ "  b.from_dept_no, fnc_dept_nm(b.from_dept_no) from_dept_nm, b.to_dept_no,fnc_dept_nm(b.to_dept_no) to_dept_nm,"
				+ " b.gr_no, b.material_id, fnc_material_nm(b.material_id) material_name, b.material_code, b.material_type_id,e.type_code, fnc_material_type_nm(b.material_type_id) material_type_nm,"
				+ " b.rcv_qty, b.manufacture_name,  b.country_id,fnc_country_nm(b.country_id) country_nm, b.sample_details, b.req_status wh_status, c.is_chemical,c.is_microbiological ,c.total_amt,fnc_unit_nm(c.unit_id) mat_unit_name,"
				+ " d.ud_sample_no,d.client_name, d.client_id, fnc_method_name(d.method_id) method_name, d.storage_condition, "
				+ " d.sample_procedure,  (CASE WHEN d.sample_procedure='N'  THEN  fnc_booth_name(d.area_booth_id)  ELSE fnc_area_name(d.area_booth_id)  END) booth_name, fnc_equipment_name(d.equipment_id) equipment_name,"
				+ " fnc_inst_name(d.work_ins_id) inst_name, d.precaution_taken, d.sampling_date, fnc_emp_name(d.sampling_by) sampling_name,d.status, d.remarks, "
				+ " (CAST((CASE WHEN c.chemical_sample_amt ='' THEN '0' ELSE  c.chemical_sample_amt END)  as float) + CAST((CASE WHEN c.micro_sample_amt ='' THEN '0' ELSE  c.micro_sample_amt END)  as float)) total_sample_amt, "
				+ " (CAST((CASE WHEN c.chemical_retention_amt ='' THEN '0' ELSE  c.chemical_retention_amt END)  as float) + CAST((CASE WHEN c.micro_retention_amt ='' THEN '0' ELSE  c.micro_retention_amt END)  as float)) total_retention_amt "
				+ " from lims_css_request_infos a, lims_wh_request_infos b, lims_material_infos c, lims_sampling_infos d,lims_material_type_infos e "
				+ "  where a.wh_request_id=b.id "
				+ " and b.material_id=c.id "
				+ " and a.id=d.css_request_id "
				+ " and c.material_type_id=e.id "
				+ " and d.id = ?",
				 new Object[] { id}, new SamplingInfoRowMapper());
	}
	
	public void saveQCReqInfos(QcInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				" insert into lims_qc_req_infos(sample_id,condition_type, abnormal_type, abnormal_desc, customer_desc,sample_desc,uncertinity,qc_req_dt,is_decision,status,company_id,created_by,created_at) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
				info.getSampleId(),info.getConditionType(),info.getAbnormalType(),info.getAbnormalDesc(),info.getCustomerDesc(),info.getSampleDesc(),info.getUncertinity(),time,info.getIsDecision(),"P", user.getCompanyId(), user.getId(), time);
		jdbcTemplate.update(
				" insert into lims_retention_req_infos(sample_id,sample_desc,uncertinity, qc_req_dt, status,company_id,created_by,created_at) "
				+ " VALUES (?,?,?,?,?,?,?,?)",
				info.getSampleId(),info.getSampleDesc(),info.getUncertinity(), time, "P", user.getCompanyId(), user.getId(), time);
		jdbcTemplate.update("update lims_sampling_infos SET status ='Q' where id = ?", info.getSampleId());
		

	}
	
	class SamplingInfoRowMapper implements RowMapper<SamplingInfo> {

		@Override
		public SamplingInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			SamplingInfo info = new SamplingInfo();
			
			  info.setId((UUID)rs.getObject("id"));
			  info.setUdCssNo(rs.getString("ud_css_no"));
			  info.setWhRequestId((UUID)rs.getObject("wh_request_id"));
			  info.setReqDate(rs.getString("req_at"));
			  info.setStatus(rs.getString("status"));
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
			  info.setRemarks(rs.getString("remarks"));
			  info.setTypeCode(rs.getString("type_code"));
			return info;
		}
	}
	
	
	
	
}
