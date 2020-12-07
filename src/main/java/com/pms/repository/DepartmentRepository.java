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

import com.pms.model.DepartmentInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class DepartmentRepository {
	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<DepartmentInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"select * from lims_department_infos where company_id = ? and status="+ (status != null ? "'" + status + "'" : "status") + " order by dept_name ",
				new Object[] { user.getCompanyId() }, new DepartmentInfoRowMapper());
	}

	public List<DepartmentInfo> findAllItemsDeprtment() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		String query = "select distinct dept_no, dept_name from view_deptstock where company_id = '"
				+ user.getCompanyId() + "' order by dept_name";
		return jdbcTemplate.query(query, new RowMapper<DepartmentInfo>() {
			public DepartmentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				DepartmentInfo info = new DepartmentInfo();
				info.setId(UUID.fromString(rs.getString("dept_no")));
				info.setDeptName(rs.getString("dept_name"));
				return info;
			}
		});
	}

	public void saveDepartmentInfos(DepartmentInfo departmentInfo) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (departmentInfo.getId() == null) {
			
			
			jdbcTemplate.update(
					"INSERT INTO lims_department_infos(dept_no, dept_name,remarks, status,company_id, created_by_name, created_by_username, created_at) "
							+ "VALUES (?,?,?,?,?,?,?,?)",
					departmentInfo.getDeptNo().toUpperCase(), departmentInfo.getDeptName(), departmentInfo.getRemarks(),
					departmentInfo.getStatus(), user.getCompanyId(), user.getFullName(), user.getUserName(), time);
			
			
		} else {
			jdbcTemplate.update(
					"UPDATE lims_department_infos SET dept_no = ?, dept_name = ?,remarks = ?, status = ? where id = ?",
					departmentInfo.getDeptNo().toUpperCase(), departmentInfo.getDeptName(), departmentInfo.getRemarks(),
					departmentInfo.getStatus(), departmentInfo.getId());
		}
	}

	public List<DepartmentInfo> validateDeptNo(String deptNo) {
		List<DepartmentInfo> entityList = jdbcTemplate.query("select * from lims_department_infos where dept_no = ?",
				new Object[] { deptNo }, new DepartmentInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class DepartmentInfoRowMapper implements RowMapper<DepartmentInfo> {

		@Override
		public DepartmentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			DepartmentInfo departmentInfo = new DepartmentInfo();

			departmentInfo.setId((UUID) rs.getObject("id"));
			departmentInfo.setDeptNo(rs.getString("dept_no"));
			departmentInfo.setDeptName(rs.getString("dept_name"));
			departmentInfo.setRemarks(rs.getString("remarks"));
			departmentInfo.setStatus(rs.getString("status"));
			return departmentInfo;
		}
	}
}
