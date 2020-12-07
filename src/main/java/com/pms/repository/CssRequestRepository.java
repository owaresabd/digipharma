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
import com.pms.model.SamplingInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class CssRequestRepository {

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
				" select a.id,a.ud_css_no, a.wh_request_id,a.req_by,coalesce(to_char( a.req_at, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as req_at,a.req_status css_status,"
				+ " b.req_type,fnc_req_type_nm(b.req_type) req_type_name, b.effective_date, b.revision_no, b.form_no, b.invoice_no, b.chalan_no, b.lc_no,"
				+ "  b.from_dept_no, fnc_dept_nm(b.from_dept_no) from_dept_nm, b.to_dept_no,fnc_dept_nm(b.to_dept_no) to_dept_nm,b.kept_amount,"
				+ " b.gr_no, b.material_id, fnc_material_nm(b.material_id) material_name, b.material_code, b.material_type_id, fnc_material_type_nm(b.material_type_id) material_type_nm,"
				+ " b.rcv_qty,fnc_unit_nm(b.unit_id) rcv_unit_name, b.manufacture_name,  b.country_id,fnc_country_nm(b.country_id) country_nm, b.sample_details, b.req_status wh_status, "
				+ " c.is_chemical,c.is_microbiological ,c.total_amt,fnc_unit_nm(c.unit_id) mat_unit_name,d.type_code, c.storage_con_id, c.storage_condition, c.mat_sample_procedure, c.mat_method_id, "
				+ " (CAST((CASE WHEN c.chemical_sample_amt ='' THEN '0' ELSE  c.chemical_sample_amt END)  as float) + CAST((CASE WHEN c.micro_sample_amt ='' THEN '0' ELSE  c.micro_sample_amt END)  as float)) total_sample_amt, "
				+ " (CAST((CASE WHEN c.chemical_retention_amt ='' THEN '0' ELSE  c.chemical_retention_amt END)  as float) + CAST((CASE WHEN c.micro_retention_amt ='' THEN '0' ELSE  c.micro_retention_amt END)  as float)) total_retention_amt "
				+ " from lims_css_request_infos a, lims_wh_request_infos b, lims_material_infos c,lims_material_type_infos d"
				+ "  where a.req_status='P' and a.wh_request_id=b.id "
				+ " and b.material_id=c.id and c.material_type_id=d.id "
				+ " and b.provider_type not in ('PT','AV') "
				+ " and a.company_id = ? ORDER BY a.req_at DESC",
				new Object[] { user.getCompanyId() }, new CssInfoRowMapper());
	}

	
	public CommonInfo getCssReqDetailsInfo(UUID id){
		User user = userService.getCurrentUser();
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject("select a.id,a.ud_css_no, a.wh_request_id,a.req_by,coalesce(to_char( a.req_at, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as req_at,a.req_status css_status, b.req_type,fnc_req_type_nm(b.req_type) req_type_name, b.effective_date, b.revision_no,"
				+ " b.form_no, b.invoice_no, b.chalan_no, b.lc_no, b.from_dept_no, fnc_dept_nm(b.from_dept_no) from_dept_nm, b.to_dept_no,fnc_dept_nm(b.to_dept_no) to_dept_nm,b.kept_amount,"
				+ " b.gr_no, b.material_id, fnc_material_nm(b.material_id) material_name, b.material_code, b.material_type_id, fnc_material_type_nm(b.material_type_id) material_type_nm,b.rcv_qty,fnc_unit_nm(b.unit_id) rcv_unit_name, b.manufacture_name,"
				+ " b.country_id,fnc_country_nm(b.country_id) country_nm, b.sample_details, b.req_status wh_status,d.type_code, c.is_chemical,c.is_microbiological ,c.total_amt,fnc_unit_nm(c.unit_id) mat_unit_name, c.storage_con_id, c.storage_condition, c.mat_sample_procedure, c.mat_method_id, "
				+ " (CAST((CASE WHEN c.chemical_sample_amt ='' THEN '0' ELSE  c.chemical_sample_amt END)  as float) + CAST((CASE WHEN c.micro_sample_amt ='' THEN '0' ELSE  c.micro_sample_amt END)  as float)) total_sample_amt, "
				+ " (CAST((CASE WHEN c.chemical_retention_amt ='' THEN '0' ELSE  c.chemical_retention_amt END)  as float) + CAST((CASE WHEN c.micro_retention_amt ='' THEN '0' ELSE  c.micro_retention_amt END)  as float)) total_retention_amt "
				+ " from lims_css_request_infos a, lims_wh_request_infos b, lims_material_infos c,lims_material_type_infos d "
				+ " where a.wh_request_id=b.id and b.material_id=c.id and c.material_type_id=d.id and a.id=? and a.company_id = ?",
				 new Object[] { id,user.getCompanyId() }, new CssInfoRowMapper());
	}
	public void updateTestDate(CommonInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		Timestamp time = new Timestamp(info.getReportDate().getTime());
		jdbcTemplate.update("UPDATE lims_result_infos SET created_at =? where id = ?",time, info.getResultId());
				
				
		
	}
	
	public void saveSampleInfos(SamplingInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		UUID idRandom=UUID.randomUUID();
		jdbcTemplate.update(
				" insert into lims_sampling_infos(id,css_request_id,client_name,client_id,method_id,sample_procedure,equipment_id,sampling_by,storage_condition,"
				+ " storage_con_id, area_booth_id, work_ins_id, precaution_taken, sampling_date, remarks, company_id,created_by,created_at) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				idRandom,info.getCssRequestId(),info.getClientName(), info.getClientId(),info.getMethodId(),info.getSampleProcedure(),info.getEquipmentId(),info.getSamplingBy(),info.getStorageCondition(),
				info.getStorageConId(),info.getAreaBoothId(),info.getWorkInsId(),info.getPrecautionTaken(),info.getSamplingDate(),info.getRemarks(), user.getCompanyId(), user.getId(), time);
				jdbcTemplate.update("UPDATE lims_sampling_infos SET ud_sample_no ='"+sampleIdGeneration(idRandom)+"' where id = ?", idRandom);
				jdbcTemplate.update("UPDATE lims_css_request_infos SET req_status ='S' where id = ?", info.getCssRequestId());
				
		
	}
	public   String  sampleIdGeneration(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		StringBuilder udSampleId  = new StringBuilder(); 
		DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
		String year = df.format(Calendar.getInstance().getTime());
		
		CommonInfo info = jdbcTemplate.queryForObject("SELECT a.type_code,b.is_chemical,b.is_microbiological,b.sample_id , " + 
				"(SELECT COALESCE(CAST(MAX(RIGHT(ud_sample_no,4)) as integer),0)+1 from view_sample_infos where type_code in ('R','F','P','W'))  max_id ,b.material_name " + 
				"from lims_material_type_infos a ,view_sample_infos b " + 
				"where a.id=b.material_type_id and b.sample_id=?",new Object[] { id}, new IdInfoRowMapper());
		if(info.getIsChemical().equals("Y") && info.getIsMicrobiological().equals("Y")) {
			udSampleId.append("CM").append(year).append(info.getMaterialTypeName()).append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
		}else if(info.getIsChemical().equals("Y") && info.getIsMicrobiological().equals("N")) {
			
			udSampleId.append("C").append(year).append(info.getMaterialTypeName()).append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
			
		}else if(info.getIsChemical().equals("N") && info.getIsMicrobiological().equals("Y")) {
			
			udSampleId.append("M").append(year).append(info.getMaterialTypeName()).append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
			
		}
		
		
		return udSampleId.toString();
	}
	public List<CommonInfo> findReports(String isWorkingStandard) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				  "Select * from (select distinct on (sample_rcv_id)  sample_rcv_id,sample_id,ud_sample_no,RIGHT(ud_sample_no, 4) ud_sl_no, material_name,is_decision,is_working_standard,NOW() as report_date "
				  + " from view_distribution_infos p "
				  + "	where   sample_rcv_id in (CASE  "
				  + "		WHEN is_decision  = 'N' THEN  "
				  + "		(select sample_rcv_id from lims_tm_verify_infos c  where c.sample_rcv_id = p.sample_rcv_id)  " + 
				  "     ELSE ( select distinct sample_rcv_id from lims_lm_verify_infos c  where c.sample_rcv_id = p.sample_rcv_id) " + 
				  "  END) and is_working_standard ='"+isWorkingStandard+"' and company_id=?  order by sample_rcv_id)  a   order by ud_sl_no::numeric ", new Object[] { user.getCompanyId() }, new ResultInfoRowMapper());
	}
	public List<CommonInfo> findWsReports() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				  "Select * from (select distinct on (sample_rcv_id)  sample_rcv_id,sample_id,ud_sample_no,RIGHT(ud_sample_no, 4) ud_sl_no, material_name,"
				  + " is_decision,is_working_standard,NOW() as report_date,ws_request_id "
				  + " from view_distribution_infos p "
				  + "	where   sample_rcv_id in (CASE  "
				  + "		WHEN is_decision  = 'N' THEN  "
				  + "		(select sample_rcv_id from lims_tm_verify_infos c  where c.sample_rcv_id = p.sample_rcv_id)  " + 
				  "     ELSE ( select distinct sample_rcv_id from lims_lm_verify_infos c  where c.sample_rcv_id = p.sample_rcv_id) " + 
				  "  END) and is_working_standard ='Y' and company_id=?  order by sample_rcv_id)  a "
				  + " where  exists  (select ws_request_id  from lims_work_standard_infos where ws_request_id=a.ws_request_id )   "
				  + " order by ud_sl_no::numeric ", new Object[] { user.getCompanyId() }, new ResultInfoRowMapper());
	}
	public List<CommonInfo> findWsInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				  "Select * from (select distinct on (sample_rcv_id)  sample_rcv_id,sample_id,ud_sample_no,RIGHT(ud_sample_no, 4) ud_sl_no,arn_no, "
				  + " material_name,is_decision,is_working_standard,NOW() as report_date,ws_request_id "
				  + " from view_distribution_infos p "
				  + "	where   sample_rcv_id in (CASE  "
				  + "		WHEN is_decision  = 'N' THEN  "
				  + "		(select sample_rcv_id from lims_tm_verify_infos c  where c.sample_rcv_id = p.sample_rcv_id)  " + 
				  "     ELSE ( select distinct sample_rcv_id from lims_lm_verify_infos c  where c.sample_rcv_id = p.sample_rcv_id) " + 
				  "  END) and is_working_standard ='Y' "
				  + " and   company_id=?  order by sample_rcv_id)  a "
				  + " where not exists  (select ws_request_id  from lims_work_standard_infos where ws_request_id=a.ws_request_id )"
				  + "    order by ud_sl_no::numeric ",
				   new Object[] { user.getCompanyId() }, new ResultWsInfoRowMapper());
	}
	public CommonInfo findWsDetailInfo(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return (CommonInfo)jdbcTemplate.queryForObject(
				  "Select * from (select distinct on (sample_rcv_id)  sample_rcv_id,sample_id,ud_sample_no,RIGHT(ud_sample_no, 4) ud_sl_no,arn_no, "
				  + " material_name,is_decision,is_working_standard,NOW() as report_date,ws_request_id "
				  + " from view_distribution_infos p "
				  + "	where   sample_rcv_id in (CASE  "
				  + "		WHEN is_decision  = 'N' THEN  "
				  + "		(select sample_rcv_id from lims_tm_verify_infos c  where c.sample_rcv_id = p.sample_rcv_id)  " + 
				  "     ELSE ( select distinct sample_rcv_id from lims_lm_verify_infos c  where c.sample_rcv_id = p.sample_rcv_id) " + 
				  "  END) and is_working_standard ='Y' "
				  + " and   company_id=?  order by sample_rcv_id)  a "
				  + " where not exists  (select ws_request_id  from lims_work_standard_infos where ws_request_id=a.ws_request_id )"
				  + "  and ws_request_id=?  order by ud_sl_no::numeric ",
				   new Object[] { user.getCompanyId(),id }, new ResultWsInfoRowMapper());
	}
	class IdInfoRowMapper implements RowMapper<CommonInfo> {

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
	class ResultInfoRowMapper implements RowMapper<CommonInfo> {
		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {			
			CommonInfo info = new CommonInfo();
			
			  info.setId((UUID)rs.getObject("sample_rcv_id"));
			  info.setSampleId((UUID)rs.getObject("sample_id"));
			  info.setUdCssNo(rs.getString("ud_sample_no"));
			  info.setMaterialName(rs.getString("material_name"));
			  info.setIsDecision(rs.getString("is_decision"));
			  info.setReportDate(rs.getDate("report_date"));
			return info;
		}
	}
	class ResultWsInfoRowMapper implements RowMapper<CommonInfo> {
		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {			
			CommonInfo info = new CommonInfo();
			
			  info.setId((UUID)rs.getObject("sample_rcv_id"));
			  info.setSampleId((UUID)rs.getObject("sample_id"));
			  info.setUdCssNo(rs.getString("ud_sample_no"));
			  info.setMaterialName(rs.getString("material_name"));
			  info.setIsDecision(rs.getString("is_decision"));
			  info.setReportDate(rs.getDate("report_date"));
			  info.setWsRequestId((UUID)rs.getObject("ws_request_id"));
			  info.setArnNo(rs.getString("arn_no"));
			return info;
		}
	}
	class CssInfoRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CommonInfo info = new CommonInfo();
			info.setId((UUID)rs.getObject("id"));
			info.setUdCssNo(rs.getString("ud_css_no"));
			info.setWhRequestId((UUID)rs.getObject("wh_request_id"));
			info.setReqDate(rs.getString("req_at"));
			info.setStatus(rs.getString("css_status"));
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
			info.setTypeCode(rs.getString("type_code"));
			info.setMaterialId((UUID)rs.getObject("material_id"));
			info.setMaterialName(rs.getString("material_name"));
			info.setMaterialCode(rs.getString("material_code"));
			info.setMaterialTypeId((UUID)rs.getObject("material_type_id"));
			info.setMaterialTypeName(rs.getString("material_type_nm"));
			info.setRcvQty(rs.getString("rcv_qty"));
			info.setWhUnitName(rs.getString("rcv_unit_name"));
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
			info.setMatStorageConId((UUID)rs.getObject("storage_con_id"));
			info.setMatStorageCon(rs.getString("storage_condition"));
			info.setMatSamProcedure(rs.getString("mat_sample_procedure"));
			info.setMatMethodId((UUID)rs.getObject("mat_method_id"));
			info.setKeptAmount(rs.getString("kept_amount"));
			System.out.println("kept amount "+rs.getString("kept_amount"));
			return info;
		}
	}
	
	
	
	
}
