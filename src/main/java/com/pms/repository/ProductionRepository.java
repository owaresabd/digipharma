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
import com.pms.model.User;
import com.pms.model.WhCssInfo;
import com.pms.model.WhRequestDetailsInfo;
import com.pms.model.WhRequestInfo;
import com.pms.service.IUserService;

@Repository
@Transactional
public class ProductionRepository {

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
				+ " rcv_qty,unit_id,fnc_unit_nm(unit_id) unit_name, manufacture_name,"
				+ " country_id,fnc_country_nm(country_id) country_nm, sample_details, req_status "
				+ " from lims_wh_request_infos where provider_type = 'PD' "
				+ " and req_status=" + (status != null ? "'" + status + "'" : "req_status") +" and company_id = ?   order by created_at desc ",
				new Object[] { user.getCompanyId() }, new WhRequestInfoRowMapper());
	}

	public CommonInfo getBatchInfoById(UUID id){

		jdbcTemplate = new JdbcTemplate(datasource);
		return (CommonInfo)jdbcTemplate.queryForObject("SELECT a.material_type_id,a.material_code,b.batch_no,b.lot_no,b.batch_size, b.unit_id, fnc_unit_nm(b.unit_id) unit_name, b.batch_date, b.mfg_date, b.exp_date, c.pack_size " + 
				"	FROM lims_material_infos a,lims_batch_infos b, lims_product_infos c where a.id=b.product_id and a.id=c.product_id and b.status='P' and b.product_id=?",
				new Object[] { id }, new ProductInfoRowMapper());
	}

	public List<MaterialInfo> findAllProduct(String type,String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select a.id, material_name, material_code, material_type_id, fnc_material_type_nm(a.material_type_id) material_type_nm, is_chemical, is_microbiological,"
				+ " chemical_sample_amt, chemical_retention_amt, chemical_total, micro_sample_amt, micro_retention_amt, "
				+ " micro_total, total_amt,unit_id,fnc_unit_nm(unit_id) unit_name, a.status, storage_con_id, storage_condition, mat_sample_procedure, mat_method_id, "
				+ " fnc_method_name(mat_method_id) sample_method_name "
				+ " from lims_material_infos a, lims_material_type_infos b"
				+ " where a.material_type_id=b.id  "
				+ " and a.id in(Select product_id from lims_batch_infos where product_id=a.id and status='P')"
				+ " and b.type_code=" + (type != null ? "'" + type + "'" : "b.type_code") + " "
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
	public void saveProductionInfos(WhRequestInfo info, UUID idRandom) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
				" insert into lims_wh_request_infos (id, req_type,"
				+ " from_dept_no,to_dept_no, material_id,material_code,material_type_id,"
				+ " sample_details,req_status, provider_type, company_id,created_by,created_at)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
				idRandom,info.getReqType(), info.getFromDeptNo(),info.getToDeptNo(),info.getMaterialId(),info.getMaterialCode(),info.getMaterialTypeId(),
				info.getSampleDetails(), info.getStatus(), info.getProviderType(), user.getCompanyId(), user.getId(), time);
			 jdbcTemplate.update("UPDATE lims_wh_request_infos SET ud_wh_req_no ='"+reqIdGeneration(idRandom)+"' where id = ?", idRandom);
			    
		} else {
			jdbcTemplate.update(
					"update lims_wh_request_infos SET req_type=?, "
					+ " from_dept_no=?,to_dept_no=?, material_id=?,material_code=?,material_type_id=?, "
					+ " sample_details=?,req_status = ?, provider_type =?, updated_by=?, updated_at=? where id = ?",
					info.getReqType(), info.getFromDeptNo(),info.getToDeptNo(), info.getMaterialId(),info.getMaterialCode(),info.getMaterialTypeId(),
					info.getSampleDetails(), info.getStatus(), info.getProviderType(),user.getId(), time, info.getId());
		}

	}
	public   String  reqIdGeneration(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		StringBuilder udReqId  = new StringBuilder(); 
		DateFormat df = new SimpleDateFormat("yyMM"); // Just the year, with 2 digits
		String year = df.format(Calendar.getInstance().getTime());
		
		CommonInfo info = jdbcTemplate.queryForObject("SELECT COALESCE(CAST(MAX(RIGHT(ud_wh_req_no,4)) as integer),0)+1 max_id  "
				+ " from lims_wh_request_infos ", new IdInfoRowMapper());
			
		 udReqId.append("PD").append(year).append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
		
		
		
		return udReqId.toString();
	}
	public void saveCssRequestInfos(WhCssInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				" insert into lims_css_request_infos (wh_request_id, req_by, req_status,req_at, company_id, created_by, created_at) "
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
			
		 udReqId.append("PR").append(year).append(StringUtils.leftPad(info.getMaxId(), 4, "0"));
		
		
		
		return udReqId.toString();
	}
	public void saveWhRequestDetail(WhRequestDetailsInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
					"INSERT INTO lims_wh_request_details_infos (wh_request_id,mfg_date,exp_date,batch_lot,"
					+ " batch_no,quantity,unit_id, pack_size,company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?)",
					 info.getWhRequestId(), info.getMfgDate(), info.getExpDate(), info.getBatchLot(), info.getBatchNo(), 
					 info.getQuantity(),info.getUnitId(), info.getPackSize(), user.getCompanyId(), user.getId(), time);
		
	}
	public void updateBatchInfo(String batchNo,UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("update lims_batch_infos set status='R' where batch_no = '"+batchNo+"' and product_id=? ",new Object[] { id });
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
			
			//info.setId((UUID)rs.getObject("id"));
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
	class IdInfoRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CommonInfo info = new CommonInfo();
			info.setMaxId(rs.getString("max_id"));
			
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
