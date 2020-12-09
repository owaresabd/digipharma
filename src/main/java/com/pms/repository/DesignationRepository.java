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

import com.pms.model.DesignationInfo;
import com.pms.model.User;
import com.pms.service.IUserService;


@Repository
@Transactional
public class DesignationRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<DesignationInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"select * from dms_designation_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by designation_name ",
				new Object[] { user.getCompanyId() }, new DesignationInfoRowMapper());
	}

	public void saveDesignationInfos(DesignationInfo designationInfo) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (designationInfo.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO dms_designation_infos(designation_no, designation_name,remarks, status,company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?,?)",
					designationInfo.getDesignationNo().toUpperCase(), designationInfo.getDesignationName(),
					designationInfo.getRemarks(), designationInfo.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE dms_designation_infos SET designation_no = ?, designation_name = ?,remarks = ?, status = ?,updated_by=?,updated_at=? where id = ?",
					designationInfo.getDesignationNo().toUpperCase(), designationInfo.getDesignationName(),
					designationInfo.getRemarks(), designationInfo.getStatus(), user.getId(), time,
					designationInfo.getId());
		}
	}

	public List<DesignationInfo> validateDesignationNo(String designationNo) {
		List<DesignationInfo> entityList = jdbcTemplate.query(
				"select * from dms_designation_infos where designation_no = ?", new Object[] { designationNo },
				new DesignationInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class DesignationInfoRowMapper implements RowMapper<DesignationInfo> {

		@Override
		public DesignationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DesignationInfo designationInfo = new DesignationInfo();

			designationInfo.setId((UUID) rs.getObject("id"));
			designationInfo.setDesignationNo(rs.getString("designation_no"));
			designationInfo.setDesignationName(rs.getString("designation_name"));
			designationInfo.setRemarks(rs.getString("remarks"));
			designationInfo.setStatus(rs.getString("status"));
			return designationInfo;
		}
	}
}
