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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.pms.model.ItemTypeInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class ItemTypeRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<ItemTypeInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"select * from vms_itemtype_infos where company_id = ? " + " and status="
						+ (status != null ? "'" + status + "'" : "status") + " " + " order by ud_item_type_no ",
				new Object[] { user.getCompanyId() }, new ItemTypeRowMapper());
	}

	public ItemTypeInfo findById(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject("select * from vms_itemtype_infos where id = ?", new Object[] { id },
				new ItemTypeRowMapper());
	}

	public void saveItemTypeInfos(ItemTypeInfo itemTypeInfo) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (itemTypeInfo.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO vms_itemtype_infos(ud_item_type_no, item_type_name, status,company_id, created_by_name, created_by_username, created_at) "
							+ "VALUES (?,?,?,?,?,?,?,?,?)",
					itemTypeInfo.getUdItemTypeNo().toUpperCase(), itemTypeInfo.getItemTypeName(),
					itemTypeInfo.getStatus(),
					user.getCompanyId(), user.getFullName(), user.getUserName(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE vms_itemtype_infos SET ud_item_type_no = ?, item_type_name = ?, status = ? where id = ?",
					itemTypeInfo.getUdItemTypeNo().toUpperCase(), itemTypeInfo.getItemTypeName(),
					itemTypeInfo.getStatus(),
					itemTypeInfo.getId());
			// updateServiceInfoById(serviceType);
		}
	}

	public UUID delete(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM vms_itemtype_infos WHERE id = ?", id);
		return id;
	}

	public List<ItemTypeInfo> validateUdItemTypeNo(String udItemTypeNo) {
		List<ItemTypeInfo> entityList = jdbcTemplate.query("select * from vms_itemtype_infos where ud_item_type_no = ?",
				new Object[] { udItemTypeNo }, new ItemTypeRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class ItemTypeRowMapper implements RowMapper<ItemTypeInfo> {

		@Override
		public ItemTypeInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			ItemTypeInfo itemTypeInfo = new ItemTypeInfo();
			UUID id = (UUID) rs.getObject("id");

			itemTypeInfo.setId(id);
			itemTypeInfo.setUdItemTypeNo(rs.getString("ud_item_type_no"));
			itemTypeInfo.setItemTypeName(rs.getString("item_type_name"));
			
			itemTypeInfo.setStatus(rs.getString("status"));
			return itemTypeInfo;
		}
	}
}
