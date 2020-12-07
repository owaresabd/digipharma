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
import com.pms.model.ProductInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class ProductRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<ProductInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(
				"select id, product_id,fnc_material_nm(product_id) product_name, shelf_life, batch_size, unit_id,fnc_unit_nm(unit_id) unit_name, pack_size, status "
				+ " from lims_product_infos where  status=" + (status != null ? "'" + status + "'" : "status") + " ",
				 new ProductInfoRowMapper());
	}

	public void saveProductInfos(ProductInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_product_infos(product_id,shelf_life,batch_size,unit_id,pack_size,status,company_id,created_by, created_at) "
					+ " VALUES (?,?,?,?,?,?,?,?,?)",
					info.getProductId(), info.getShelfLife(), info.getBatchSize(), info.getUnitId(),
					info.getPackSize(), info.getStatus(), user.getCompanyId(), user.getId(), time);
			jdbcTemplate.update(
					"INSERT INTO lims_product_infos_logs(product_id,shelf_life,batch_size,unit_id,pack_size,status,operation_type,company_id,created_by, created_at) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?)",
					info.getProductId(), info.getShelfLife(), info.getBatchSize(), info.getUnitId(),
					info.getPackSize(), info.getStatus(),"I", user.getCompanyId(), user.getId(), time);
			
		} else {
			jdbcTemplate.update(
					"UPDATE lims_product_infos SET product_id = ?, shelf_life = ?, batch_size = ?, unit_id = ?, pack_size = ?,status = ? ,"
					+ " updated_by = ?, updated_at = ? where id = ?",
							info.getProductId(), info.getShelfLife(), info.getBatchSize(), info.getUnitId(),
							info.getPackSize(), info.getStatus(), user.getId(), time, info.getId());
			jdbcTemplate.update(
					"INSERT INTO lims_product_infos_logs(product_id,shelf_life,batch_size,unit_id,pack_size,status,operation_type,company_id,created_by, created_at) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?)",
					info.getProductId(), info.getShelfLife(), info.getBatchSize(), info.getUnitId(),
					info.getPackSize(), info.getStatus(),"U", user.getCompanyId(), user.getId(), time);

		}
	}
	public CommonInfo getProductInfoById(UUID id){

		jdbcTemplate = new JdbcTemplate(datasource);
		return (CommonInfo)jdbcTemplate.queryForObject("select id, product_id,fnc_material_nm(product_id) product_name,"
				+ " shelf_life, batch_size, unit_id,fnc_unit_nm(unit_id) unit_name, pack_size, status,now()::date sys_date,"
				+ " (now()::date + (shelf_life::text || ' month')::interval)::date as exp_date,"
				+ " (SELECT COALESCE(MAX(CAST(LEFT(batch_no, LENGTH(batch_no) - 2)as integer)),0)+1 from lims_batch_infos where product_id=a.product_id "
				+ " and TO_CHAR(created_at :: DATE, 'yyyy')=TO_CHAR(NOW() :: DATE, 'yyyy'))  batch_no,"
			    + "(select count(*) from lims_batch_infos where product_id=a.product_id and status='P') batch_status  "
				+ " from lims_product_infos a where product_id = ?",
				 new Object[] { id }, new ProductInfoByIDRowMapper());
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
				+ " and a.id not in(Select product_id from lims_product_infos )"
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
	class ProductInfoByIDRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CommonInfo info = new CommonInfo();
				DateFormat df = new SimpleDateFormat("yy"); 
				String year = df.format(Calendar.getInstance().getTime());
				if(rs.getString("batch_no").length()<=2) {
					info.setBatchNo(StringUtils.leftPad(rs.getString("batch_no"), 2, "0")+year);
					}else {
					info.setBatchNo(StringUtils.leftPad(rs.getString("batch_no"),rs.getString("batch_no").length(), "0")+year);	
				}
			    
				info.setBatchDate(rs.getDate("sys_date"));
				info.setBatchSize(rs.getString("batch_size"));
				info.setUnitId((UUID) rs.getObject("unit_id"));
				info.setUnitName(rs.getString("unit_name"));
				info.setLotNo("");
				info.setShelfLife(rs.getString("shelf_life"));
				info.setMfgDate(rs.getDate("sys_date"));
				info.setExpDate(rs.getDate("exp_date"));
				info.setStatus(rs.getString("batch_status"));
			return info;
		}
	}
	class ProductInfoRowMapper implements RowMapper<ProductInfo> {

		@Override
		public ProductInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductInfo info = new ProductInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setProductId((UUID) rs.getObject("product_id"));
			info.setProductName(rs.getString("product_name"));
			info.setShelfLife(rs.getString("shelf_life"));
			info.setBatchSize(rs.getString("batch_size"));
			info.setUnitId((UUID) rs.getObject("unit_id"));
			info.setUnitName(rs.getString("unit_name"));
			info.setPackSize(rs.getString("pack_size"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}

}
