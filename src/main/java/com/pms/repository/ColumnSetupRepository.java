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

import com.pms.model.ColumnSetupInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class ColumnSetupRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IUserService userService;

	public List<ColumnSetupInfo> findColumnSetupInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT id, column_id, column_name, column_size, composition, status, company_id, created_by, created_at, updated_by, updated_at, remarks FROM public.lims_column_setup_infos WHERE company_id = ? ",
				new Object[] { user.getCompanyId() }, new columnSetupInfosRowMapper());
	}
//	
//	public List<LogBookSetupInfo> validateDocumentNo(String documentNo) {
//		List<LogBookSetupInfo> entityList = jdbcTemplate.query("SELECT id, logbook_name, document_no, revision_no, effective_date, logbook_type, remarks, status "
//				+ " FROM lims_log_book_infos  where document_no = ? ",
//				new Object[] { documentNo }, new LogBookSetupInfosRowMapper());
//		if (entityList == null) {
//			throw new UsernameNotFoundException("does not exist.");
//		}
//		return entityList;
//	}
//	
	public void saveColumnSetupInfo(ColumnSetupInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_column_setup_infos (column_id, column_name, column_size, composition, status, company_id, created_by, created_at, updated_by, updated_at, remarks) VALUES(?,?,?,?,?,?,?,?,?,?,?) ",
					info.getColumnId(), info.getColumnName(), info.getColumnSize(), info.getComposition(), info.getStatus(), user.getCompanyId(), user.getId(), time, user.getId(), time, info.getRemarks());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_column_setup_infos SET column_name =?, column_id =?, column_size =?, composition =?, status = ?, remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getColumnName(), info.getColumnId(), info.getColumnSize(), info.getComposition(), info.getStatus(), info.getRemarks(), user.getId(), time, info.getId());
				}
	}
	
	class columnSetupInfosRowMapper implements RowMapper<ColumnSetupInfo> {
		@Override
		public ColumnSetupInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			ColumnSetupInfo info = new ColumnSetupInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setColumnId(rs.getString("column_id"));
			info.setColumnName(rs.getString("column_name"));
			info.setColumnSize(rs.getString("column_size"));
			info.setComposition(rs.getString("composition"));
			info.setStatus(rs.getString("status"));
			info.setRemarks(rs.getString("remarks"));
			return info;
		}
	}
	
	
}
