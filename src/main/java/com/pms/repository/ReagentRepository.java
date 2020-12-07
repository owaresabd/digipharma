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

import com.pms.model.CommonInfo;
import com.pms.model.ReagentIssueInfo;
import com.pms.model.ReagentReceiveInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class ReagentRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	
	public List<ReagentReceiveInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		
		return jdbcTemplate.query( "select id, reagent_id, (select product_name from lims_surgical_product_infos a where a.id=b.reagent_id) reagent_name,"
				+ " receive_date, receive_qty,receive_unit_id, fnc_unit_nm(receive_unit_id) unit_name, receive_by, batch_no, lot_no,"
				+ " pack_size, mfg_date, exp_date, purity, manufacturer_id, country_id "
				+ " from lims_reagent_receive_infos b where company_id= ? ",
				new Object[] { user.getCompanyId() },new ReagentReceiveInfoRowMapper());
	}
	
	public List<CommonInfo> findStockInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query( "Select a.id, a.product_name ,fnc_unit_nm(unit_id) unit_name,SUM(CAST (receive_qty AS NUMERIC)) receive_qty,  SUM(CAST (issu_qty AS NUMERIC)) issue_qty , (SUM(CAST (receive_qty AS NUMERIC))-SUM(CAST (issu_qty AS NUMERIC))) stock_qty " + 
				"    from lims_surgical_product_infos a, view_reagent_stock_infos b  " + 
				"    where a.id=b.reagent_id  group by a.id, a.product_name ",
				new StockInfoRowMapper());
	}
	
	public CommonInfo findStockInfoById(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject( "Select a.id, a.product_name ,fnc_unit_nm(unit_id) unit_name,SUM(CAST (COALESCE(receive_qty,'0') AS NUMERIC)) receive_qty,  SUM(CAST (COALESCE(issu_qty,'0') AS NUMERIC)) issue_qty , (SUM(CAST (COALESCE(receive_qty,'0') AS NUMERIC))-SUM(CAST (COALESCE(issu_qty,'0') AS NUMERIC))) stock_qty " + 
				"    from lims_surgical_product_infos a "
				+ " FULL OUTER JOIN view_reagent_stock_infos b "
				+ "	ON a.id=b.reagent_id  "
				+ " WHERE a.id='"+id+"' "
				+ " group by a.id, a.product_name",
				new StockInfoRowMapper());
	}
	
	public List<ReagentIssueInfo> findAllIssue(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		
		return jdbcTemplate.query("select id, reagent_id, (select product_name from lims_surgical_product_infos a where a.id=b.reagent_id) reagent_name,"
				+ " stock_qty, issue_qty, issue_unit_id, fnc_unit_nm(issue_unit_id) unit_name, issue_by, lab_type, remarks"
				+ " from lims_reagent_issue_infos b where company_id= ? ",
				new Object[] { user.getCompanyId() },new ReagentIssueInfoRowMapper());
	}
	
	public CommonInfo findReagentInfoById(UUID id){
		jdbcTemplate = new JdbcTemplate(datasource);
		return (CommonInfo)jdbcTemplate.queryForObject("select id, unit_id, fnc_unit_nm(unit_id) unit_name,product_code "
				+ " from lims_surgical_product_infos a where id = ?", new Object[] { id }, new ReagentInfoByIDRowMapper());
	}
	
	public void saveReceiveInfo(ReagentReceiveInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_reagent_receive_infos( reagent_id, receive_date, receive_qty, receive_unit_id, receive_by, batch_no,"
					+ " lot_no, pack_size, mfg_date, exp_date, purity, manufacturer_id, country_id, "
					+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					info.getReagentId(),info.getReceiveDate(), info.getReceiveQty(), info.getReceiveUnitId(), user.getId(),info.getBatchNo(),
					info.getLotNo(), info.getPackSize(), info.getMfgDate(), info.getExpDate(), info.getPurity(), info.getManufacturerId(),
					info.getCountryId(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_reagent_receive_infos SET reagent_id = ?, receive_date = ?, receive_qty = ?, receive_unit_id = ?, receive_by= ?,"
					+ " batch_no= ?, lot_no =?, pack_size =?, mfg_date = ?, exp_date = ?, purity = ?, manufacturer_id =?, "
					+ " country_id = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getReagentId(),info.getReceiveDate(), info.getReceiveQty(), info.getReceiveUnitId(), user.getId(),info.getBatchNo(),
					info.getLotNo(), info.getPackSize(), info.getMfgDate(), info.getExpDate(), info.getPurity(), info.getManufacturerId(),
					info.getCountryId(),user.getId(), time, info.getId());

		}
	}

	public void saveIssueInfo(ReagentIssueInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_reagent_issue_infos( reagent_id, stock_qty, issue_qty, issue_unit_id, lab_type, remarks,"
					+ " issue_by, issue_date, company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?)",
					info.getReagentId(),info.getStockQty(), info.getIssueQty(), info.getIssueUnitId(), info.getLabType(), info.getRemarks(), 
					user.getId(), time, user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_reagent_issue_infos SET reagent_id = ?, stock_qty = ?, issue_qty = ?, issue_unit_id = ?, lab_type= ?,"
					+ " remarks= ?, issue_by =?, issue_date =?, updated_by = ?, updated_at = ? where id = ?",
					info.getReagentId(),info.getStockQty(), info.getIssueQty(), info.getIssueUnitId(), info.getLabType(), info.getRemarks(), 
					user.getId(), time, user.getId(), time, info.getId());

		}
	}
	
	class ReagentReceiveInfoRowMapper implements RowMapper<ReagentReceiveInfo> {

		@Override
		public ReagentReceiveInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReagentReceiveInfo info = new ReagentReceiveInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setReagentId((UUID) rs.getObject("reagent_id"));
			info.setReagentName(rs.getString("reagent_name"));
			info.setReceiveQty(rs.getString("receive_qty"));
			info.setReceiveUnitId((UUID) rs.getObject("receive_unit_id"));
			info.setReceiveUnitName(rs.getString("unit_name"));
			info.setReceiveBy((UUID) rs.getObject("receive_by"));
			info.setBatchNo(rs.getString("batch_no"));
			info.setLotNo(rs.getString("lot_no"));
			info.setPackSize(rs.getString("pack_size"));
			info.setReceiveDate(rs.getDate("receive_date"));
			info.setMfgDate(rs.getDate("mfg_date"));
			info.setExpDate(rs.getDate("exp_date"));
			info.setPurity(rs.getString("purity"));
			info.setManufacturerId((UUID) rs.getObject("manufacturer_id"));
			info.setCountryId((UUID) rs.getObject("country_id"));
			return info;
		}
	}
	
	class ReagentIssueInfoRowMapper implements RowMapper<ReagentIssueInfo> {

		@Override
		public ReagentIssueInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReagentIssueInfo info = new ReagentIssueInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setReagentId((UUID) rs.getObject("reagent_id"));
			info.setReagentName(rs.getString("reagent_name"));
			info.setStockQty(rs.getString("stock_qty"));
			info.setIssueQty(rs.getString("issue_qty"));
			info.setIssueUnitId((UUID) rs.getObject("issue_unit_id"));
			info.setIssueUnitName(rs.getString("unit_name"));
			info.setIssueBy((UUID) rs.getObject("issue_by"));
			info.setLabType(rs.getString("lab_type"));
			info.setRemarks(rs.getString("remarks")); 
			return info;
		}
	}
	
	class StockInfoRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CommonInfo info = new CommonInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setReagentName(rs.getString("product_name"));
			info.setRcvQty(rs.getString("receive_qty"));
			info.setIssueQty(rs.getString("issue_qty"));
			info.setUnitName(rs.getString("unit_name"));
			info.setStockQty(rs.getString("stock_qty"));
			
			
			return info;
		}
	}
	
	class ReagentInfoByIDRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CommonInfo info = new CommonInfo();
				
				info.setId((UUID) rs.getObject("id"));
				info.setUnitId((UUID) rs.getObject("unit_id"));
				info.setUnitName(rs.getString("unit_name"));
				info.setMaterialCode(rs.getString("product_code"));
				CommonInfo stockInfo = findStockInfoById((UUID) rs.getObject("id"));
				info.setStockQty(stockInfo.getStockQty());
				 
				
			return info;
		}
	}
	
	

	
}
