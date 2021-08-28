package com.pms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pms.model.CountryInfo;

@Repository
@Transactional
public class CountryRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	public List<CountryInfo> findAllCountry() {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(
				"select * from pms_country_infos order by country_name",
				 new CountryInfoRowMapper());
	}

	

	class CountryInfoRowMapper implements RowMapper<CountryInfo> {

		@Override
		public CountryInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CountryInfo info = new CountryInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setCountryName(rs.getString("country_name"));
			
			return info;
		}
	}

}
