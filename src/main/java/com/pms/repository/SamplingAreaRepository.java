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

import com.pms.model.SamplingAreaInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class SamplingAreaRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<SamplingAreaInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"select * from lims_sampling_area_infos where company_id = ? and status="
						+ (status != null ? "'" + status + "'" : "status") + " order by area_name ",
				new Object[] { user.getCompanyId() }, new SamplingAreaInfoRowMapper());
	}

	

	public void saveSamplingAreaInfos(SamplingAreaInfo samplingAreaInfo) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (samplingAreaInfo.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_sampling_area_infos(area_name,remarks, status,company_id, created_by,created_at) "
							+ "VALUES (?,?,?,?,?,?)",samplingAreaInfo.getAreaName(), samplingAreaInfo.getRemarks(),
					samplingAreaInfo.getStatus(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_sampling_area_infos SET  area_name = ?,remarks = ?, status = ?,updated_by=?,updated_at=? where id = ?", samplingAreaInfo.getAreaName(), samplingAreaInfo.getRemarks(),
					samplingAreaInfo.getStatus(),user.getId(), time, samplingAreaInfo.getId());
		}
	}

	public List<SamplingAreaInfo> validateAreaName(String deptNo) {
		List<SamplingAreaInfo> entityList = jdbcTemplate.query("select * from lims_sampling_area_infos where area_name = ?",
				new Object[] { deptNo }, new SamplingAreaInfoRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	class SamplingAreaInfoRowMapper implements RowMapper<SamplingAreaInfo> {

		@Override
		public SamplingAreaInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			SamplingAreaInfo samplingAreaInfo = new SamplingAreaInfo();

			samplingAreaInfo.setId((UUID) rs.getObject("id"));
			samplingAreaInfo.setAreaName(rs.getString("area_name"));
			samplingAreaInfo.setRemarks(rs.getString("remarks"));
			samplingAreaInfo.setStatus(rs.getString("status"));
			return samplingAreaInfo;
		}
	}
}
