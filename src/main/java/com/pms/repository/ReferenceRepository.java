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

import com.pms.model.ReferenceInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class ReferenceRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<ReferenceInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"select * from lims_reference_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by reference_name ",
				new Object[] { user.getCompanyId() }, new ReferenceInfoRowMapper());
	}

	

	public void saveReferenceInfos(ReferenceInfo referenceInfo) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (referenceInfo.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_reference_infos(reference_no, reference_name,remarks, status,company_id, created_by, created_at) "
							+ "VALUES (?,?,?,?,?,?,?)",
					referenceInfo.getReferenceNo().toUpperCase(), referenceInfo.getReferenceName(), referenceInfo.getRemarks(),
					referenceInfo.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_reference_infos SET reference_no = ?, reference_name = ?,remarks = ?, status = ?,updated_by=?,updated_at=? where id = ?",
					referenceInfo.getReferenceNo().toUpperCase(), referenceInfo.getReferenceName(), referenceInfo.getRemarks(),
					referenceInfo.getStatus(), user.getId(), time, referenceInfo.getId());
		}
	}

	public List<ReferenceInfo> validateReferenceNo(String referenceNo) {
		List<ReferenceInfo> entityList = jdbcTemplate.query("select * from lims_reference_infos where reference_no = ?",
				new Object[] { referenceNo }, new ReferenceInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class ReferenceInfoRowMapper implements RowMapper<ReferenceInfo> {

		@Override
		public ReferenceInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			ReferenceInfo referenceInfo = new ReferenceInfo();

			referenceInfo.setId((UUID) rs.getObject("id"));
			referenceInfo.setReferenceNo(rs.getString("reference_no"));
			referenceInfo.setReferenceName(rs.getString("reference_name"));
			referenceInfo.setRemarks(rs.getString("remarks"));
			referenceInfo.setStatus(rs.getString("status"));
			return referenceInfo;
		}
	}
}
