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
import com.pms.model.User;
import com.pms.model.WhCssInfo;
import com.pms.model.WhRequestDetailsInfo;
import com.pms.model.WhRequestInfo;
import com.pms.service.IUserService;

@Repository
@Transactional
public class WhRequestRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<WhRequestInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select id,ud_wh_req_no, req_type,fnc_req_type_nm(req_type) req_type_name, effective_date, revision_no, "
				+ " form_no, invoice_no, chalan_no, lc_no, from_dept_no, fnc_dept_nm(from_dept_no) from_dept_nm, to_dept_no,fnc_dept_nm(to_dept_no) to_dept_nm,"
				+ " gr_no,material_id, fnc_material_nm(material_id) material_name, material_code, material_type_id, fnc_material_type_nm(material_type_id) material_type_nm,"
				+ " rcv_qty,unit_id,fnc_unit_nm(unit_id) unit_name, manufacture_id, manufacture_name,"
				+ " country_id,fnc_country_nm(country_id) country_nm, sample_details, req_status,is_working_standard "
				+ " from lims_wh_request_infos where provider_type = 'WH' "
				+ " and req_status=" + (status != null ? "'" + status +"'" :"req_status") +" and company_id = ? "
				+ " ORDER BY created_at DESC ",
				new Object[] { user.getCompanyId() }, new WhRequestInfoRowMapper());
	}
	
	public void saveWhRequestInfos(WhRequestInfo info, UUID idRandom) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
				" insert into lims_wh_request_infos(id,req_type,invoice_no,chalan_no,lc_no,"
				+ " from_dept_no,to_dept_no,gr_no,material_id,material_code,material_type_id,rcv_qty,unit_id, manufacture_id, manufacture_name,"
				+ " country_id,sample_details,req_status, provider_type, company_id,created_by,created_at)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				idRandom,info.getReqType(), info.getInvoiceNo(),info.getChalanNo(),info.getLcNo(),
				info.getFromDeptNo(),info.getToDeptNo(),info.getGrNo(),info.getMaterialId(),info.getMaterialCode(),info.getMaterialTypeId(),info.getRcvQty(),info.getRcvUnitId(),
				info.getManufactureId(),info.getManufactureName(),info.getCountryId(),info.getSampleDetails(), info.getStatus(),info.getProviderType(), user.getCompanyId(), user.getId(), time);
			jdbcTemplate.update("UPDATE lims_wh_request_infos SET ud_wh_req_no ='"+reqIdGeneration(idRandom)+"' where id = ?", idRandom);
		} else {
			jdbcTemplate.update(
					"update lims_wh_request_infos SET req_type=?,invoice_no=?,chalan_no=?,lc_no=?, "
					+ " from_dept_no=?,to_dept_no=?,gr_no=?,material_id=?,material_code=?,material_type_id=?,rcv_qty=?,unit_id=?, manufacture_id=?, "
					+ " manufacture_name=?, country_id=?,sample_details=?,req_status = ?, provider_type =?, updated_by=?, updated_at=? where id = ?",
					info.getReqType(), info.getInvoiceNo(),info.getChalanNo(),info.getLcNo(),
					info.getFromDeptNo(),info.getToDeptNo(),info.getGrNo(),info.getMaterialId(),info.getMaterialCode(),info.getMaterialTypeId(),info.getRcvQty(),info.getRcvUnitId(),
					info.getManufactureId(),info.getManufactureName(),info.getCountryId(),info.getSampleDetails(), info.getStatus(), info.getProviderType(), user.getId(), time, info.getId());
		}

	}
	public void saveWsRequestInfos(WhRequestInfo info, UUID idRandom) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		
			jdbcTemplate.update(
				" insert into lims_wh_request_infos(id,ws_request_id,req_type,invoice_no,chalan_no,lc_no,"
				+ " from_dept_no,to_dept_no,gr_no,material_id,material_code,material_type_id,rcv_qty,unit_id, manufacture_id, manufacture_name,"
				+ " country_id,sample_details,req_status, provider_type,is_working_standard,kept_amount, company_id,created_by,created_at)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				idRandom,info.getWsRequestId(),info.getReqType(), info.getInvoiceNo(),info.getChalanNo(),info.getLcNo(),
				info.getFromDeptNo(),info.getToDeptNo(),info.getGrNo(),info.getMaterialId(),info.getMaterialCode(),info.getMaterialTypeId(),info.getRcvQty(),info.getRcvUnitId(),
				info.getManufactureId(),info.getManufactureName(),info.getCountryId(),info.getSampleDetails(), info.getStatus(),info.getProviderType(),"Y",info.getKeptAmount(), user.getCompanyId(), user.getId(), time);
			jdbcTemplate.update("UPDATE lims_wh_request_infos SET ud_wh_req_no ='"+reqIdGeneration(idRandom)+"' where id = ?", idRandom);
			jdbcTemplate.update("UPDATE lims_work_standard_req_infos SET status ='Y' where id = ?", info.getWsRequestId());
		

	}
	public   String  reqIdGeneration(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		StringBuilder udReqId  = new StringBuilder(); 
		DateFormat df = new SimpleDateFormat("yyMM"); // Just the year, with 2 digits
		String year = df.format(Calendar.getInstance().getTime());
		
		CommonInfo info = jdbcTemplate.queryForObject("SELECT COALESCE(CAST(MAX(RIGHT(ud_wh_req_no,4)) as integer),0)+1 max_id  "
				+ " from lims_wh_request_infos", new IdInfoRowMapper());
			
		 udReqId.append("WH").append(year).append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
		
		
		
		return udReqId.toString();
	}
	class IdInfoRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CommonInfo info = new CommonInfo();
			info.setMaxId(rs.getString("max_id"));
			
			return info;
			
		}
		
	}
	public void saveCssRequestInfos(WhCssInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				" insert into lims_css_request_infos(wh_request_id, req_by, req_status,req_at, company_id, created_by, created_at) "
				+ " VALUES (?,?,?,?,?,?,?)",
				info.getWhRequestId(),user.getId(), info.getStatus(),time, user.getCompanyId(), user.getId(), time);
		jdbcTemplate.update("UPDATE lims_css_request_infos SET ud_css_no ='"+serviceIdGen()+"' where wh_request_id = ?", info.getWhRequestId());
		jdbcTemplate.update("UPDATE lims_wh_request_infos SET req_status ='C' where id = ?", info.getWhRequestId());
		

	}
	public   String  serviceIdGen() {
		jdbcTemplate = new JdbcTemplate(datasource);
		StringBuilder udReqId  = new StringBuilder(); 
		DateFormat df = new SimpleDateFormat("yyMM"); // Just the year, with 2 digits
		String year = df.format(Calendar.getInstance().getTime());
		
		CommonInfo info = jdbcTemplate.queryForObject("SELECT COALESCE(CAST(MAX(RIGHT(ud_css_no,4)) as integer),0)+1 max_id  "
				+ " from lims_css_request_infos", new IdInfoRowMapper());
			
		 udReqId.append("WH").append(year).append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
		
		
		
		return udReqId.toString();
	}
	public void saveWhRequestDetail(WhRequestDetailsInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
					"INSERT INTO lims_wh_request_details_infos (wh_request_id,gr_no,mfg_date,exp_date,batch_lot,"
					+ " batch_no,quantity,unit_id,number_of_drums,company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
					 info.getWhRequestId(), info.getGrNo(), info.getMfgDate(), info.getExpDate(),
					 info.getBatchLot(), info.getBatchNo(), info.getQuantity(),info.getUnitId(),
					  info.getNoOfDrums(), user.getCompanyId(), user.getId(), time);
		
		
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
				+ " rcv_qty,unit_id,fnc_unit_nm(unit_id) unit_name, manufacture_id, manufacture_name,"
				+ "  country_id,fnc_country_nm(country_id) country_nm, sample_details, req_status,is_working_standard"
				+ " from lims_wh_request_infos where id = ?",
				 new Object[] { id }, new WhRequestInfoRowMapper());
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
			info.setManufactureId((UUID)rs.getObject("manufacture_id"));
			info.setManufactureName(rs.getString("manufacture_name"));
			info.setCountryId((UUID)rs.getObject("country_id"));
			info.setCountryName(rs.getString("country_nm"));
			info.setSampleDetails(rs.getString("sample_details"));
			info.setStatus(rs.getString("req_status"));
			info.setIsWorkingStandard(rs.getString("is_working_standard"));
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
