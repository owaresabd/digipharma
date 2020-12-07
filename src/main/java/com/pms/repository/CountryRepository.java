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

import com.pms.model.CountryInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class CountryRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<CountryInfo> findAllCountry(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(
				"select * from lims_country_infos where  status=" + (status != null ? "'" + status + "'" : "status") + " ORDER BY country_name",
				 new CountryInfoRowMapper());
	}

	public void saveCountryInfos(CountryInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_country_infos(ud_country_code, country_name, main_curruncy, main_langiage, total_pepole, govt_code, nationality, remarks, "
							+ "status, company_id, created_by_username, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
					info.getUdCountryCode(), info.getCountryName(), info.getMainCurruncy(), info.getMainLangiage(),
					info.getTotalPepole(), info.getGovtCode(), info.getNationality(), info.getRemarks(),
					info.getStatus(), user.getCompanyId(), user.getUserName(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_country_infos SET ud_country_code = ?, country_name = ?, main_curruncy = ?, main_langiage = ?, total_pepole = ?, govt_code = ?, nationality = ?, "
							+ "remarks = ?, status = ?, updated_by_username = ?, updated_at = ? where id = ?",
					info.getUdCountryCode(), info.getCountryName(), info.getMainCurruncy(), info.getMainLangiage(),
					info.getTotalPepole(), info.getGovtCode(), info.getNationality(), info.getRemarks(),
					info.getStatus(), user.getUserName(), time, info.getId());

		}
	}

	class CountryInfoRowMapper implements RowMapper<CountryInfo> {

		@Override
		public CountryInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CountryInfo info = new CountryInfo();
			UUID id = (UUID) rs.getObject("id");

			info.setId(id);
			info.setUdCountryCode(rs.getString("ud_country_code"));
			info.setCountryName(rs.getString("country_name"));
			info.setMainCurruncy(rs.getString("main_curruncy"));
			info.setMainLangiage(rs.getString("main_langiage"));
			info.setTotalPepole(rs.getString("total_pepole"));
			info.setGovtCode(rs.getString("govt_code"));
			info.setNationality(rs.getString("nationality"));
			info.setRemarks(rs.getString("remarks"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}

}
