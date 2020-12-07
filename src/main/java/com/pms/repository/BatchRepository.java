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

import com.pms.model.BatchInfo;
import com.pms.model.MaterialInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class BatchRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<BatchInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(
				"select id, product_id,fnc_material_nm(product_id) product_name, batch_no, lot_no, batch_size,"
				+ "  unit_id,fnc_unit_nm(unit_id) unit_name, mfg_date, exp_date, shelf_life, status "
				+ " from lims_batch_infos where  status=" + (status != null ? "'" + status + "'" : "status") + " "
				+ " order by batch_no",
				 new BatchInfoRowMapper());
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
				+ " and a.id in(Select product_id from lims_product_infos)"
				+ " and b.type_code=" + (type != null ? "'" + type + "'" : "b.type_code") + " "
				+ " and a.company_id = ?   order by material_name ",
				new Object[] { user.getCompanyId() }, new MaterialInfoRowMapper());
	}
	public void saveBatchInfos(BatchInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_batch_infos(product_id,batch_no,lot_no, batch_size, unit_id, mfg_date, exp_date, shelf_life, status, "
					+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
					info.getProductId(),info.getBatchNo(), info.getLotNo(), info.getBatchSize(),info.getUnitId(), 
					info.getMfgDate(), info.getExpDate(), info.getShelfLife(), "P", user.getCompanyId(), user.getId(), time);
			jdbcTemplate.update(
					"INSERT INTO lims_batch_infos_log(product_id,batch_no,lot_no,batch_size,unit_id,mfg_date,exp_date,shelf_life,status,operation_type, "
					+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
					info.getProductId(),info.getBatchNo(), info.getLotNo(), info.getBatchSize(),info.getUnitId(), 
			info.getMfgDate(), info.getExpDate(), info.getShelfLife(), "P","I", user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_batch_infos SET batch_no = ?, lot_no = ?, batch_size = ?, mfg_date = ?, exp_date = ?, shelf_life = ?, "
							+ " status = ?, updated_by = ?, updated_at = ? where id = ?",
							info.getBatchNo(), info.getLotNo(), info.getBatchSize(), 
							info.getMfgDate(), info.getExpDate(), info.getShelfLife(), "P",
							user.getId(), time, info.getId());
			jdbcTemplate.update(
					"INSERT INTO lims_batch_infos_log(product_id,batch_no,lot_no,batch_size,unit_id, mfg_date,exp_date,shelf_life,status,operation_type, "
					+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
					info.getProductId(),info.getBatchNo(), info.getLotNo(), info.getBatchSize(),info.getUnitId(), 
			info.getMfgDate(), info.getExpDate(), info.getShelfLife(), "P","U", user.getCompanyId(), user.getId(), time);

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
	class BatchInfoRowMapper implements RowMapper<BatchInfo> {

		@Override
		public BatchInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			BatchInfo info = new BatchInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setProductId((UUID) rs.getObject("product_id"));
			info.setProductName(rs.getString("product_name"));
			info.setBatchNo(rs.getString("batch_no"));
			info.setLotNo(rs.getString("lot_no"));
			info.setBatchSize(rs.getString("batch_size"));
			info.setUnitId((UUID) rs.getObject("unit_id"));
			info.setUnitName(rs.getString("unit_name"));
			info.setMfgDate(rs.getDate("mfg_date"));
			info.setExpDate(rs.getDate("exp_date"));
			info.setShelfLife(rs.getString("shelf_life"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}

}
