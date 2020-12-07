package com.pms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pms.model.CommonInfo;
import com.pms.model.DisposeMstInfo;
import com.pms.model.DisposeProductInfo;
import com.pms.model.DisposeSampleInfo;
import com.pms.model.MaterialInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class DisposeRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;
	
	
	public List<DisposeMstInfo> findAll() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select  id, dispose_code, dispose_at, employee_id,fnc_emp_name(employee_id) emp_name,is_sample,is_product,remarks "
			+   " from lims_dispose_mst_infos where company_id = ? ",
				new Object[] { user.getCompanyId() }, new DisposeInfosRowMapper());
	}
	
	public List<CommonInfo> findSampleInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select  id, ud_sample_no from lims_sampling_infos where company_id = ? ",
				new Object[] { user.getCompanyId() }, new SampleInfosRowMapper());
	}

	
	public CommonInfo findSampleInfoById(UUID id){

		jdbcTemplate = new JdbcTemplate(datasource);
		return (CommonInfo)jdbcTemplate.queryForObject("Select sample_id,material_id,mat_unit_id from view_sample_infos "
				+ " where sample_id = ?",
				 new Object[] { id }, new SampleDetailsInfoRowMapper());
	}
	public DisposeMstInfo getDisposeInfoByID(UUID id){

		jdbcTemplate = new JdbcTemplate(datasource);
		return (DisposeMstInfo)jdbcTemplate.queryForObject("select  id, dispose_code, dispose_at, employee_id,fnc_emp_name(employee_id) emp_name,"
												+ " is_sample,is_product,remarks  from lims_dispose_mst_infos where id = ?",
				 new Object[] { id }, new DisposeInfosRowMapper());
	}
	public void saveDisposeMstInfo(DisposeMstInfo info, UUID idRandom) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			String disposeCode = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date()).replaceAll("[\\s\\.\\-\\:]", "").trim();
			jdbcTemplate.update(
				" insert into lims_dispose_mst_infos (id, dispose_code, dispose_at, employee_id,is_sample,is_product, remarks, company_id, created_by, created_at) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?)",
				idRandom,disposeCode, time, info.getEmployeeId(),info.getIsSample(),info.getIsProduct(), info.getRemarks(),  user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"update lims_dispose_mst_infos SET employee_id=?,is_sample=?,is_product=?, remarks=? , updated_by=?, updated_at=? where id = ?",
					info.getEmployeeId(),info.getIsSample(),info.getIsProduct(),info.getRemarks(), user.getId(), time, info.getId());
		}

	}
	
	public void saveDisposeSampleInfo(DisposeSampleInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
					"insert into lims_dispose_sample_infos (dispose_id, sample_id, material_id, quantity, unit_id, company_id, created_by, created_at) "
					+ " VALUES (?,?,?,?,?,?,?,?)",
					 info.getDisposeId(), info.getSampleId(), info.getMaterialId(), info.getQuantity(), info.getSampleUnitId(),user.getCompanyId(), user.getId(), time);
		
		
	}
	
	public void saveDisposeProductInfo(DisposeProductInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"insert into lims_dispose_product_infos(dispose_id, product_id, product_code, quantity, unit_id, company_id, created_by, created_at) "
				+ " VALUES (?,?,?,?,?,?,?,?)",
				 info.getDisposeId(), info.getProductId(), info.getProductCode(), info.getQuantity(),info.getProductUnitId(), user.getCompanyId(), user.getId(), time);
		
	}
	public List<DisposeSampleInfo> getDisposeSampleInfos(UUID disposeId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();

		return jdbcTemplate.query("select id, dispose_id, sample_id,fnc_ud_sample_no(sample_id) ud_sample_no,  material_id,fnc_material_nm(material_id) material_name, quantity, unit_id,fnc_unit_nm(unit_id) unit_name  "
				+ " from lims_dispose_sample_infos where dispose_id= ? and company_id = ?",
				new Object[] { disposeId, user.getCompanyId() }, new DisposeSampleInfoRowMapper());
	}
	
	public List<DisposeProductInfo> getDisposeProductInfos(UUID disposeId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();

		return jdbcTemplate.query("select id, dispose_id, product_id,fnc_product_nm(product_id) product_name, "
				+ " product_code, quantity, unit_id,fnc_unit_nm(unit_id) unit_name  "
				+ " from lims_dispose_product_infos where dispose_id= ? and company_id = ?",
				new Object[] { disposeId, user.getCompanyId() }, new DisposeProductInfoRowMapper());
	}
	
	public void deleteDisposeSampleInfos(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM lims_dispose_sample_infos WHERE dispose_id = ?", new Object[] { id });
	  }
	
	public void deleteDisposeProductInfos(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM lims_dispose_product_infos WHERE dispose_id = ?", new Object[] { id });
	  }
	
	
	class DisposeInfosRowMapper implements RowMapper<DisposeMstInfo> {

		@Override
		public DisposeMstInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DisposeMstInfo info = new DisposeMstInfo();
			
			info.setId((UUID)rs.getObject("id"));
			info.setUdDisposeNo(rs.getString("dispose_code"));
			info.setDisposeTime(rs.getTimestamp("dispose_at"));
			info.setEmployeeId((UUID)rs.getObject("employee_id"));
			info.setEmployeeName(rs.getString("emp_name"));
			info.setIsSample(rs.getString("is_sample"));
			info.setIsProduct(rs.getString("is_product"));
			info.setRemarks(rs.getString("remarks"));
			
			
			
			return info;
		}
	}
	class SampleInfosRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CommonInfo info = new CommonInfo();
			
			info.setId((UUID)rs.getObject("id"));
			info.setUdSampleNo(rs.getString("ud_sample_no"));
			
			return info;
		}
	}
	
	class SampleDetailsInfoRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CommonInfo info = new CommonInfo();
			
			info.setId((UUID)rs.getObject("sample_id"));
			info.setMaterialId((UUID)rs.getObject("material_id"));
			info.setUnitId((UUID)rs.getObject("mat_unit_id"));
			
			return info;
		}
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
	
	class DisposeSampleInfoRowMapper implements RowMapper<DisposeSampleInfo> {

		@Override
		public DisposeSampleInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DisposeSampleInfo info = new DisposeSampleInfo();
			
			info.setId((UUID)rs.getObject("id"));
			info.setDisposeId((UUID)rs.getObject("dispose_id"));
			info.setSampleId((UUID)rs.getObject("sample_id"));
			info.setUdSampleNo(rs.getString("ud_sample_no"));
			info.setMaterialId((UUID)rs.getObject("material_id"));
			info.setMaterialName(rs.getString("material_name"));
			info.setQuantity(rs.getString("quantity"));
			info.setSampleUnitId((UUID)rs.getObject("unit_id"));
			info.setUnitName(rs.getString("unit_name"));
			
			return info;
		}
	}
	
	
	class DisposeProductInfoRowMapper implements RowMapper<DisposeProductInfo> {

		@Override
		public DisposeProductInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DisposeProductInfo info = new DisposeProductInfo();
			
			info.setId((UUID)rs.getObject("id"));
			info.setDisposeId((UUID)rs.getObject("dispose_id"));
			info.setProductId((UUID)rs.getObject("product_id"));
			info.setProductName(rs.getString("product_name"));
			info.setProductCode(rs.getString("product_code"));
			info.setQuantity(rs.getString("quantity"));
			info.setProductUnitId((UUID)rs.getObject("unit_id"));
			info.setUnitName(rs.getString("unit_name"));
			
			
			
			
			return info;
		}
	}
	
}
