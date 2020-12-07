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

import com.pms.model.EmployeeInfo;
import com.pms.model.User;
import com.pms.service.IUserService;



@Repository
@Transactional
public class EmployeeRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<EmployeeInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"select id, ud_emp_no, emp_name, desig_id,fnc_desig_nm(desig_id) desig_nm, father_name, mother_name, dob, gender_id, blood_group, marital_status, address, nid, mobile_no, email, qualification, join_date, status "
				+ " from vms_employee_infos where company_id = ? "
				+ " and status=" + (status != null ? "'" + status + "'" : "status") + " order by ud_emp_no ",
				new Object[] { user.getCompanyId() }, new EmployeeInfoRowMapper());
	}
	
	
	
	
	
	

	public void saveEmployeeInfos(EmployeeInfo employeeInfo) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (employeeInfo.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO vms_employee_infos(ud_emp_no, emp_name, desig_id, father_name, mother_name, dob, gender_id,"
					+ " blood_group, marital_status, address, nid, mobile_no, email, qualification, join_date, status,"
					+ "  company_id, created_by, created_at) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					 employeeInfo.getUdEmpNo().toUpperCase(), employeeInfo.getEmpName().toUpperCase(), employeeInfo.getDesigId(),employeeInfo.getFatherName(),employeeInfo.getMotherName(),
					employeeInfo.getDob(),employeeInfo.getGenderId(),employeeInfo.getBloodGroup(),employeeInfo.getMaritalStatus(),employeeInfo.getAddress(),employeeInfo.getNid(),
					employeeInfo.getMobileNo(),employeeInfo.getEmail(),employeeInfo.getQualification(),employeeInfo.getJoiningDate(), employeeInfo.getStatus(), user.getCompanyId(), user.getId(),  time);
		} else {
			jdbcTemplate.update(
					"UPDATE vms_employee_infos SET ud_emp_no=?, emp_name=?, desig_id=?, father_name=?, mother_name=?, "
					+ " dob=?, gender_id=?, blood_group=?, marital_status=?, address=?, nid=?, mobile_no=?, email=?, qualification=?, join_date=?, status=?, "
					+ " updated_by=?, updated_at=? where id = ?",
					employeeInfo.getUdEmpNo().toUpperCase(), employeeInfo.getEmpName().toUpperCase(), employeeInfo.getDesigId(),employeeInfo.getFatherName(),employeeInfo.getMotherName(),
					employeeInfo.getDob(),employeeInfo.getGenderId(),employeeInfo.getBloodGroup(),employeeInfo.getMaritalStatus(),employeeInfo.getAddress(),employeeInfo.getNid(),
					employeeInfo.getMobileNo(),employeeInfo.getEmail(),employeeInfo.getQualification(),employeeInfo.getJoiningDate(), employeeInfo.getStatus(), user.getId(),  time, employeeInfo.getId());
		}
	}
	
	
	

	public List<EmployeeInfo> validateUdEmpNo (String udEmpNo) {
		List<EmployeeInfo> entityList = jdbcTemplate.query("select id, ud_emp_no, emp_name, desig_id,fnc_desig_nm(desig_id) desig_nm, father_name, mother_name, dob, gender_id,"
										+ "  blood_group, marital_status, address, nid, mobile_no, email, qualification, join_date, status  "
										+ " from vms_employee_infos where ud_emp_no = ?",
				new Object[] { udEmpNo }, new EmployeeInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}
	

	
	class EmployeeInfoRowMapper implements RowMapper<EmployeeInfo> {

		@Override
		public EmployeeInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmployeeInfo info = new EmployeeInfo();
			
			info.setId((UUID)rs.getObject("id"));
			info.setUdEmpNo(rs.getString("ud_emp_no"));
			info.setEmpName(rs.getString("emp_name"));
			info.setDesigId((UUID)rs.getObject("desig_id"));
			info.setDesigName(rs.getString("desig_nm"));
			info.setFatherName(rs.getString("father_name"));
			info.setMotherName(rs.getString("mother_name"));
			info.setDob(rs.getDate("dob"));
			info.setGenderId(rs.getString("gender_id"));
			info.setBloodGroup(rs.getString("blood_group"));
			info.setMaritalStatus(rs.getString("marital_status"));
			info.setAddress(rs.getString("address"));
			info.setNid(rs.getString("nid"));
			info.setMobileNo(rs.getString("mobile_no"));
			info.setEmail(rs.getString("email"));
			info.setQualification(rs.getString("qualification"));
			info.setJoiningDate(rs.getDate("join_date"));
			info.setStatus(rs.getString("status"));
			
			
			return info;
		}
	}
	

	
	
}
