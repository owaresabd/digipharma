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

import com.pms.model.SamplingBoothInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class SamplingBoothRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<SamplingBoothInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"select * from lims_sampling_booth_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by booth_name ",
				new Object[] { user.getCompanyId() }, new SamplingBoothInfoRowMapper());
	}

	

	public void saveSamplingBoothInfos(SamplingBoothInfo samplingBoothInfo) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (samplingBoothInfo.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_sampling_booth_infos(booth_name,remarks, status,company_id, created_by,created_at) "
							+ "VALUES (?,?,?,?,?,?)",samplingBoothInfo.getBoothName(), samplingBoothInfo.getRemarks(),
					samplingBoothInfo.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_sampling_booth_infos SET  booth_name = ?,remarks = ?, status = ?,updated_by=?,updated_at=? where id = ?", samplingBoothInfo.getBoothName(), samplingBoothInfo.getRemarks(),
					samplingBoothInfo.getStatus(),user.getId(), time, samplingBoothInfo.getId());
		}
	}

	public List<SamplingBoothInfo> validateBoothName(String deptNo) {
		List<SamplingBoothInfo> entityList = jdbcTemplate.query("select * from lims_sampling_booth_infos where booth_name = ?",
				new Object[] { deptNo }, new SamplingBoothInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class SamplingBoothInfoRowMapper implements RowMapper<SamplingBoothInfo> {

		@Override
		public SamplingBoothInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			SamplingBoothInfo samplingBoothInfo = new SamplingBoothInfo();

			samplingBoothInfo.setId((UUID) rs.getObject("id"));
			samplingBoothInfo.setBoothName(rs.getString("booth_name"));
			samplingBoothInfo.setRemarks(rs.getString("remarks"));
			samplingBoothInfo.setStatus(rs.getString("status"));
			return samplingBoothInfo;
		}
	}
}
