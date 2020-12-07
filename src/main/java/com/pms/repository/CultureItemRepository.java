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

import com.pms.model.CultureItemInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class CultureItemRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<CultureItemInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select id, culture_item_name, remarks, status,"
				+ " culture_item_type_id, atcc_no, "
				+ "(select culture_item_type_name from lims_culture_item_type_infos b where culture_item_type_id=b.id ) culture_item_type_name "
                + "  from lims_culture_item_infos a where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by culture_item_name ",
						new Object[] { user.getCompanyId() }, new CulturalItemTypeRowMapper());
	}

	

	public void saveCultureItemInfos(CultureItemInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
        System.out.println(info.getCultureItemName()+": ======== :"+info.getCultureItemTypeId());
		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_culture_item_infos ( culture_item_name,culture_item_type_id,atcc_no, remarks, status, company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?,?,?)",
					info.getCultureItemName(), info.getCultureItemTypeId(),  info.getAtccNo(),  info.getRemarks(), info.getStatus(),
					user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_culture_item_infos SET culture_item_name = ?, culture_item_type_id = ?,atcc_no = ?,"
					+ " remarks = ?, status = ? where id = ?",
					info.getCultureItemName(),info.getCultureItemTypeId(),info.getAtccNo(), info.getRemarks(), 
					info.getStatus(), info.getId());
		}
	}

	public List<CultureItemInfo> validateCultureItemName(String roomName) {
		List<CultureItemInfo> entityList = jdbcTemplate.query("select id, culture_item_name,"
				+ " culture_item_type_id,atcc_no, "
				+ "(select culture_item_type_name from lims_culture_item_type_infos b where culture_item_type_id=b.id ) culture_item_type_name, "
				+ "remarks, status "
				+ "  from lims_culture_item_infos a where culture_item_name = ?",
				new Object[] { roomName }, new CulturalItemTypeRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class CulturalItemTypeRowMapper implements RowMapper<CultureItemInfo> {
		@Override
		public CultureItemInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CultureItemInfo info = new CultureItemInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setCultureItemTypeId((UUID) rs.getObject("culture_item_type_id"));
			info.setCultureItemName(rs.getString("culture_item_name"));
			info.setRemarks(rs.getString("remarks"));
			info.setCultureItemTypeName(rs.getString("culture_item_type_name"));
			info.setStatus(rs.getString("status"));
			info.setAtccNo(rs.getString("atcc_no"));
			return info;
		}
	}
	
	
}
