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

import com.pms.model.MaterialChemicalInfo;
import com.pms.model.MaterialInfo;
import com.pms.model.MaterialMicrobiologicalInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class MaterialRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<MaterialInfo> findAll(String type,String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		
		return jdbcTemplate.query(
				" select a.id, material_name, material_code, material_type_id, fnc_material_type_nm(a.material_type_id) material_type_nm,b.type_code, is_chemical, is_microbiological,"
				+ " chemical_sample_amt, chemical_retention_amt, chemical_total, micro_sample_amt, micro_retention_amt, "
				+ " micro_total, total_amt,unit_id,fnc_unit_nm(unit_id) unit_name, a.status, storage_con_id, storage_condition, mat_sample_procedure, mat_method_id, "
				+ " fnc_method_name(mat_method_id) sample_method_name "
				+ " from lims_material_infos a, lims_material_type_infos b"
				+ " where a.material_type_id=b.id "
				+ " and b.type_code=" + (type != null ? "'" + type + "'" : "b.type_code") + " "
				+ " and a.company_id = ?   order by material_name ",
				new Object[] { user.getCompanyId() }, new MaterialInfoRowMapper());
	}
	public List<MaterialInfo> findAllRawPackaging(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select a.id, material_name, material_code, material_type_id, fnc_material_type_nm(a.material_type_id) material_type_nm,b.type_code, is_chemical, is_microbiological,"
				+ " chemical_sample_amt, chemical_retention_amt, chemical_total, micro_sample_amt, micro_retention_amt, "
				+ " micro_total, total_amt,unit_id,fnc_unit_nm(unit_id) unit_name, a.status, storage_con_id, storage_condition, mat_sample_procedure, mat_method_id, "
				+ " fnc_method_name(mat_method_id) sample_method_name "
				+ " from lims_material_infos a, lims_material_type_infos b"
				+ " where a.material_type_id=b.id "
				+ " and b.type_code in ('R','P') "
				+ " and a.company_id = ?   order by material_name ",
				new Object[] { user.getCompanyId() }, new MaterialInfoRowMapper());
	}
	public List<MaterialInfo> findAllWSRawMaterials(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select a.id, material_name, material_code, material_type_id, fnc_material_type_nm(a.material_type_id) material_type_nm,b.type_code, is_chemical, is_microbiological,"
				+ " chemical_sample_amt, chemical_retention_amt, chemical_total, micro_sample_amt, micro_retention_amt, "
				+ " micro_total, total_amt,unit_id,fnc_unit_nm(unit_id) unit_name, a.status, storage_con_id, storage_condition, mat_sample_procedure, mat_method_id, "
				+ " fnc_method_name(mat_method_id) sample_method_name "
				+ " from lims_material_infos a, lims_material_type_infos b"
				+ " where a.material_type_id=b.id "
				+ " and b.type_code='W'"
				+ " and a.company_id = ?   order by material_name ",
				new Object[] { user.getCompanyId() }, new MaterialInfoRowMapper());
	}
	
	
	public void saveMaterialInfos(MaterialInfo info, UUID idRandom) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
				" insert into lims_material_infos (id, material_name, material_code, material_type_id, is_chemical,"
				+ " is_microbiological, chemical_sample_amt, chemical_retention_amt, chemical_total, micro_sample_amt,"
				+ " micro_retention_amt, micro_total, total_amt,unit_id, status, storage_con_id, storage_condition, mat_sample_procedure, mat_method_id, "
				+ " company_id,created_by,created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				idRandom,info.getMaterialName(), info.getMaterialCode(), info.getMaterialTypeId(),info.getIsChemical(), 
				info.getIsMicrobiological(),info.getChemicalSampleAmt(),info.getChemicalRetentionAmt(),info.getChemicalTotal(),
				info.getMicroSampleAmt(),info.getMicroRetentionAmt(),info.getMicroTotal(),info.getTotalAmt(),info.getUnitId(), info.getStatus(), 
				info.getStorageConId(), info.getStorageCondition(), info.getMatSamProcedure(), info.getMatMethodId(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"update lims_material_infos SET material_name=?, material_code=?, material_type_id=?, is_chemical=?,"
					+ " is_microbiological=?, chemical_sample_amt=?, chemical_retention_amt=?, chemical_total=?, micro_sample_amt=?,"
					+ " micro_retention_amt=?, micro_total=?, total_amt=?, unit_id=?, status= ?, storage_con_id=?, "
					+ " storage_condition = ?, mat_sample_procedure = ?, mat_method_id = ?, updated_by=?, updated_at=? where id = ?",
					info.getMaterialName(), info.getMaterialCode(), info.getMaterialTypeId(),info.getIsChemical(),
					info.getIsMicrobiological(),info.getChemicalSampleAmt(),info.getChemicalRetentionAmt(),info.getChemicalTotal(),
					info.getMicroSampleAmt(),info.getMicroRetentionAmt(),info.getMicroTotal(),info.getTotalAmt(),info.getUnitId(), info.getStatus(), 
					info.getStorageConId(), info.getStorageCondition(), info.getMatSamProcedure(), info.getMatMethodId(), user.getId(), time, info.getId());
		}

	}
	public MaterialInfo getMaterialInfoById(UUID id){

		jdbcTemplate = new JdbcTemplate(datasource);
		return (MaterialInfo)jdbcTemplate.queryForObject("select a.id,material_name,material_code,material_type_id, fnc_material_type_nm(material_type_id) material_type_nm,b.type_code, is_chemical,is_microbiological,"
				+ " chemical_sample_amt,chemical_retention_amt, chemical_total, micro_sample_amt, micro_retention_amt, micro_total, total_amt,"
				+ " unit_id,fnc_unit_nm(unit_id) unit_name, a.status, storage_con_id, storage_condition, mat_sample_procedure,"
				+ " mat_method_id, fnc_method_name(mat_method_id) sample_method_name "
				+ " from lims_material_infos a, lims_material_type_infos b  "
				+ " where a.material_type_id=b.id and a.id = ?",
				 new Object[] { id }, new MaterialInfoRowMapper());
	}
	public void saveMaterialChemicalInfo(MaterialChemicalInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		int slNo=Integer.parseInt(getSlNo(info.getChemiMaterialId()).trim());
		jdbcTemplate.update(
					"insert into lims_material_chemical_infos (material_id,parameter_no,parameter_id,specification,"
					+ " reference_id,method_id,test_unit_id,sample_amount,unit_id,sl_no,lod,company_id,created_by,created_at) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					 info.getChemiMaterialId(), info.getChemiParameterNo(), info.getChemiParameterId(), info.getChemiSpecification(),
					 info.getChemiReferenceId(),info.getChemiMethodId(),info.getChemiTestUnitId(),info.getChemiSampleAmount(),info.getChemiUnitId(),slNo,
					  info.getChemiLod(),user.getCompanyId(), user.getId(), time);
		
		
	}
	
	public void saveMicrobiologicallDetailInfo(MaterialMicrobiologicalInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		int slNo=Integer.parseInt(getSlNo( info.getMicroMaterialId()).trim());
		jdbcTemplate.update(
				"insert into lims_material_microbiological_infos(material_id,parameter_no,parameter_id,specification,"
				+ " reference_id,method_id,test_unit_id,sample_amount,unit_id,sl_no,lod,company_id,created_by,created_at) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				 info.getMicroMaterialId(), info.getMicroParameterNo(), info.getMicroParameterId(), info.getMicroSpecification(),
				 info.getMicroReferenceId(),info.getMicroMethodId(),info.getMicroTestUnitId(),info.getMicroSampleAmount(),info.getMicroUnitId(),slNo,
				 info.getMicroLod(),user.getCompanyId(), user.getId(), time);
		
	}
	public String getSlNo(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
	    String sql = "SELECT COALESCE(MAX(sl_no),0)+1 max_id "
	    		+ " from view_material_param_info where material_id=?";

	    String slNo = (String) jdbcTemplate.queryForObject(
	            sql, new Object[] { id }, String.class);

	    return slNo;
	}
	public List<MaterialChemicalInfo> getMaterialChemicalInfos(UUID materialId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();

		return jdbcTemplate.query("select id, material_id, parameter_no, parameter_id,fnc_param_name(parameter_id) param_name, specification,"
				+ " reference_id,fnc_ref_name(reference_id) ref_name, method_id,fnc_test_method_name(method_id) method_name, test_unit_id,"
				+ " fnc_unit_nm(test_unit_id) test_unit_name, sample_amount,unit_id,fnc_unit_nm(unit_id) unit_name,lod "
				+ " from lims_material_chemical_infos where material_id= ? and company_id = ?",
				new Object[] { materialId, user.getCompanyId() }, new MaterialChemicalInfoRowMapper());
	}
	
	public List<MaterialMicrobiologicalInfo> getMaterialMicrobiologicalInfos(UUID materialId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();

		return jdbcTemplate.query("select id, material_id, parameter_no, parameter_id,fnc_param_name(parameter_id) param_name, specification,"
				+ " reference_id,fnc_ref_name(reference_id) ref_name, method_id,fnc_test_method_name(method_id) method_name, test_unit_id," 
				+ " fnc_unit_nm(test_unit_id) test_unit_name, sample_amount,unit_id,fnc_unit_nm(unit_id) unit_name,lod  "
				+ " from lims_material_microbiological_infos where material_id= ? and company_id = ?",
				new Object[] { materialId, user.getCompanyId() }, new MaterialMicrobiologicalInfoRowMapper());
	}
	
	public void deleteChemicalInfos(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM lims_material_chemical_infos WHERE material_id = ?", new Object[] { id });
	  }
	
	public void deleteMicrobiologicalInfos(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM lims_material_microbiological_infos WHERE material_id = ?", new Object[] { id });
	  }
	
	class MaterialInfoRowMapper implements RowMapper<MaterialInfo> {

		@Override
		public MaterialInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			MaterialInfo info = new MaterialInfo();
			
			info.setId((UUID)rs.getObject("id"));
			info.setMaterialName(rs.getString("material_name"));
			info.setMaterialCode(rs.getString("material_code"));
			info.setMaterialTypeId((UUID)rs.getObject("material_type_id"));
			info.setTypeCode(rs.getString("type_code"));
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
	
	class MaterialChemicalInfoRowMapper implements RowMapper<MaterialChemicalInfo> {

		@Override
		public MaterialChemicalInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			MaterialChemicalInfo info = new MaterialChemicalInfo();
			
			info.setChemiChildId((UUID)rs.getObject("id"));
			info.setChemiMaterialId((UUID)rs.getObject("material_id"));
			info.setChemiParameterNo(rs.getString("parameter_no"));
			info.setChemiParameterId((UUID)rs.getObject("parameter_id"));
			info.setChemiParameterName(rs.getString("param_name"));
			info.setChemiSpecification(rs.getString("specification"));
			info.setChemiReferenceId((UUID)rs.getObject("reference_id"));
			info.setChemiReferenceName(rs.getString("ref_name"));
			info.setChemiMethodId((UUID)rs.getObject("method_id"));
			info.setChemiMethodName(rs.getString("method_name"));
			info.setChemiTestUnitId((UUID)rs.getObject("test_unit_id"));
			info.setTestUnitName(rs.getString("test_unit_name"));
			info.setChemiSampleAmount(rs.getString("sample_amount"));
			info.setChemiUnitId((UUID)rs.getObject("unit_id"));
			info.setUnitName(rs.getString("unit_name"));
			info.setChemiLod(rs.getString("lod"));
			
			return info;
		}
	}
	
	
	class MaterialMicrobiologicalInfoRowMapper implements RowMapper<MaterialMicrobiologicalInfo> {

		@Override
		public MaterialMicrobiologicalInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			MaterialMicrobiologicalInfo info = new MaterialMicrobiologicalInfo();
			
			info.setMicroChildId((UUID)rs.getObject("id"));
			info.setMicroMaterialId((UUID)rs.getObject("material_id"));
			info.setMicroParameterNo(rs.getString("parameter_no"));
			info.setMicroParameterId((UUID)rs.getObject("parameter_id"));
			info.setMicroParameterName(rs.getString("param_name"));
			info.setMicroSpecification(rs.getString("specification"));
			info.setMicroReferenceId((UUID)rs.getObject("reference_id"));
			info.setMicroReferenceName(rs.getString("ref_name"));
			info.setMicroMethodId((UUID)rs.getObject("method_id"));
			info.setMicroMethodName(rs.getString("method_name"));
			info.setMicroTestUnitId((UUID)rs.getObject("test_unit_id"));
			info.setTestUnitName(rs.getString("test_unit_name"));
			info.setMicroSampleAmount(rs.getString("sample_amount"));
			info.setMicroUnitId((UUID)rs.getObject("unit_id"));
			info.setUnitName(rs.getString("unit_name"));
			info.setMicroLod(rs.getString("lod"));
			return info;
		}
	}
	
}
