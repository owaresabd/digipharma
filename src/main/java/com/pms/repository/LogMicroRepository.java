package com.pms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pms.model.CommonInfo;
import com.pms.model.LogDisinfectantSolInfo;
import com.pms.model.LogFumigationInfo;
import com.pms.model.LogMCAllottedSampleInfo;
import com.pms.model.LogMCAnalystValidationInfo;
import com.pms.model.LogMCAnalyticalBalanceInfo;
import com.pms.model.LogMCAutoClaveInfo;
import com.pms.model.LogMCColonyCounterInfo;
import com.pms.model.LogMCHotAirOvenInfo;
import com.pms.model.LogMCLaminarAirFlowInfo;
import com.pms.model.LogMCLbFumigationInfo;
import com.pms.model.LogMCLbPreparationInfo;
import com.pms.model.LogMCLbReferenceCultureInfo;
import com.pms.model.LogMCLbTestStrainInfo;
import com.pms.model.LogMCMediaReagentMaterialsInfo;
import com.pms.model.LogMCMicroscopeInfo;
import com.pms.model.LogMCPhMeterInfo;
import com.pms.model.LogMCTempHumPressRecordInfo;
import com.pms.model.LogMCTempIncubatorRecordInfo;
import com.pms.model.LogMCTempRefrigeratorRecordInfo;
import com.pms.model.LogMCWashDryerInfo;
import com.pms.model.LogMCWaterBathInfo;
import com.pms.model.LogMcMediumReagentInfo;
import com.pms.model.LogMcWaterSamplingInfo;
import com.pms.model.LogMcWeightingChdInfo;
import com.pms.model.LogMcWeightingMstInfo;
import com.pms.model.LogRefCultureInfo;
import com.pms.model.LogSterilePreparedInfo;
import com.pms.model.LogWaterBathInfo;
import com.pms.model.MaterialInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class LogMicroRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogMicroRepository.class);
	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<LogWaterBathInfo> findLogWaterBathInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id,operation_date,switch_on_time,material_id,temperature,switch_off_time,operated_by,checked_by,remarks "
						+ " from lims_log_waterbath_infos  where company_id = ?  order by operation_date desc ",
				new Object[] { user.getCompanyId() }, new LogWaterBathRowMapper());
	}

	public List<LogSterilePreparedInfo> findLogSterilePrepareInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, medium_name, storage_type, refrigerator_id, storage_condition, storage_date, storage_qty, lab_batch_no, exp_date, pack_size, storage_by,fnc_emp_name(storage_by) storage_by_name , issue_date, issued_qty , purpose, issued_by,fnc_emp_name(issued_by) issued_by_name , stock_qty, checked_by,fnc_emp_name(checked_by) checked_by_name, remarks, company_id, created_by, created_at, updated_by, updated_at\r\n"
						+ "	FROM public.lims_log_sterile_prepared_infos  where company_id = ?  order by storage_date desc ",
				new Object[] { user.getCompanyId() }, new LogSterilePreparedRowMapper());
	}

	public void saveWaterBathInfos(LogWaterBathInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_waterbath_infos (operation_date, switch_on_time, material_id, temperature,"
							+ " switch_off_time, operated_by, checked_by, remarks, company_id, created_by, created_at) "
							+ "" + "VALUES (?,?,?,?,?,?,?,?,?,,?,?)",
					info.getOperationDate(), info.getSwitchOnTime(), info.getMaterialId(), info.getTemperature(),
					info.getSwitchOffTime(), info.getOperatedBy(), info.getCheckedBy(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_waterbath_infos SET operation_date=?, switch_on_time=?, material_id=?, temperature=?,switch_off_time=?, operated_by=?, checked_by=?, remarks=?,"
							+ " updated_by = ?, updated_at = ? where id = ?",
					info.getOperationDate(), info.getSwitchOnTime(), info.getMaterialId(), info.getTemperature(),
					info.getSwitchOffTime(), info.getOperatedBy(), info.getCheckedBy(), info.getRemarks(), user.getId(),
					time, info.getId());

		}
	}

	public void saveSterilePreparedMediumInfo(LogSterilePreparedInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_sterile_prepared_infos (medium_name, storage_type, refrigerator_id, storage_condition, storage_date, storage_qty, lab_batch_no, exp_date, pack_size, "
							+ " storage_by, issue_date, issued_qty, purpose, issued_by, stock_qty, checked_by, remarks, company_id, created_by, created_at) "
							+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ",
					info.getMediumName(), info.getStorageType(), info.getRefrigeratorId(), info.getStorageCondition(),
					info.getStorageDate(), info.getStorageQty(), info.getLabBatcNo(), info.getExpDate(),
					info.getPackSize(), info.getStorageBy(), info.getIssueDate(), info.getIssuedQty(),
					info.getPurpose(), info.getIssuedBy(), info.getStockQty(), info.getCheckedBy(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_sterile_prepared_infos SET medium_name =?, storage_type =?, refrigerator_id = ?, storage_condition =?, storage_date = ?, storage_qty = ?, lab_batch_no = ?, exp_date = ?, pack_size = ?, "
							+ " storage_by =?, issue_date = ?, issued_qty = ?, purpose =?, issued_by = ?, stock_qty = ?,checked_by = ?, remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getMediumName(), info.getStorageType(), info.getRefrigeratorId(), info.getStorageCondition(),
					info.getStorageDate(), info.getStorageQty(), info.getLabBatcNo(), info.getExpDate(),
					info.getPackSize(), info.getStorageBy(), info.getIssueDate(), info.getIssuedQty(),
					info.getPurpose(), info.getIssuedBy(), info.getStockQty(), info.getCheckedBy(), info.getRemarks(),
					user.getId(), time, info.getId());
		}
	}

	public List<LogDisinfectantSolInfo> findDisinfectantSolInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, disinfec_name, amt_disinfec, amt_distilled, total_amt, start_time, end_time,"
						+ " store_temp, record_date, exp_date, prepare_by, check_by, remarks, "
						+ " fnc_emp_name(prepare_by) prepare_by_name, fnc_emp_name(check_by) check_by_name "
						+ " FROM lims_log_disinfec_solution_infos where company_id = ? ",
				new Object[] { user.getCompanyId() }, new DisinfectantSolInfoRowMapper());
	}

	public void saveDisinfectantSolInfo(LogDisinfectantSolInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_disinfec_solution_infos ( disinfec_name, amt_disinfec, amt_distilled, total_amt, start_time, end_time,"
							+ " store_temp, record_date, exp_date, prepare_by, check_by, remarks,"
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getDisinfecName(), info.getAmtDisinfec(), info.getAmtDistilled(), info.getTotalAmt(),
					info.getStartTime(), info.getEndTime(), info.getStoreTemp(), info.getRecordDate(),
					info.getExpDate(), info.getPrepareBy(), info.getCheckBy(), info.getRemarks(), user.getCompanyId(),
					user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_disinfec_solution_infos SET disinfec_name =?, amt_disinfec =?, amt_distilled = ?, total_amt =?, start_time = ?, end_time = ?, "
							+ " store_temp =?, record_date = ?, exp_date = ?, prepare_by =?, check_by = ?, remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getDisinfecName(), info.getAmtDisinfec(), info.getAmtDistilled(), info.getTotalAmt(),
					info.getStartTime(), info.getEndTime(), info.getStoreTemp(), info.getRecordDate(),
					info.getExpDate(), info.getPrepareBy(), info.getCheckBy(), info.getRemarks(), user.getId(), time,
					info.getId());
		}
	}

	public List<LogFumigationInfo> findFumigationInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, location, room_no, potassium, formal, start_time, end_time,"
						+ " record_date, done_by, check_by, remarks, "
						+ " fnc_emp_name(done_by) done_by_name, fnc_emp_name(check_by) check_by_name "
						+ " FROM lims_log_fumigation_infos where company_id = ? ",
				new Object[] { user.getCompanyId() }, new FumigationInfoRowMapper());
	}

	public void saveFumigationInfo(LogFumigationInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_fumigation_infos ( location, room_no, potassium, formal, start_time, end_time,"
							+ " record_date, done_by, check_by, remarks,"
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getLocation(), info.getRoomNo(), info.getPotassium(), info.getFormal(), info.getStartTime(),
					info.getEndTime(), info.getRecordDate(), info.getDoneBy(), info.getCheckBy(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_fumigation_infos SET location =?, room_no =?, potassium = ?, formal =?, start_time = ?, end_time = ?, "
							+ " record_date = ?, done_by =?, check_by = ?, remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getLocation(), info.getRoomNo(), info.getPotassium(), info.getFormal(), info.getStartTime(),
					info.getEndTime(), info.getRecordDate(), info.getDoneBy(), info.getCheckBy(), info.getRemarks(),
					user.getId(), time, info.getId());
		}
	}

	public List<LogRefCultureInfo> findRefCultureInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, mfg_supplier_id, ref_seed_name, atcc_no, batch_lot_no,"
						+ " certi_avail, receive_date, exp_date, verify_date, receive_by, check_by, remarks, "
						+ " fnc_manufac_name(mfg_supplier_id) mfg_supplier_name, "
						+ " fnc_emp_name(receive_by) receive_by_name, fnc_emp_name(check_by) check_by_name "
						+ " FROM lims_log_ref_culture_infos where company_id = ? ",
				new Object[] { user.getCompanyId() }, new RefCultureInfoRowMapper());
	}

	public void saveRefCultureInfo(LogRefCultureInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_ref_culture_infos ( mfg_supplier_id, ref_seed_name, atcc_no, batch_lot_no, certi_avail,"
							+ " receive_date, exp_date, verify_date, receive_by, check_by, remarks,"
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getMfgSupplierId(), info.getRefSeedName(), info.getAtccNo(), info.getBatchLotNo(),
					info.getCertiAvail(), info.getReceiveDate(), info.getExpDate(), info.getVerifyDate(),
					info.getReceiveBy(), info.getCheckBy(), info.getRemarks(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_ref_culture_infos SET mfg_supplier_id =?, ref_seed_name =?, atcc_no = ?, batch_lot_no =?, certi_avail = ?, "
							+ " receive_date = ?, exp_date = ?, verify_date = ?, receive_by =?, check_by = ?, remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getMfgSupplierId(), info.getRefSeedName(), info.getAtccNo(), info.getBatchLotNo(),
					info.getCertiAvail(), info.getReceiveDate(), info.getExpDate(), info.getVerifyDate(),
					info.getReceiveBy(), info.getCheckBy(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	class LogWaterBathRowMapper implements RowMapper<LogWaterBathInfo> {

		@Override
		public LogWaterBathInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogWaterBathInfo info = new LogWaterBathInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setOperationDate(rs.getDate("operation_date"));
			info.setSwitchOnTime(rs.getString("switch_on_time"));
			info.setMaterialId((UUID) rs.getObject("material_id"));
			info.setTemperature(rs.getString("temperature"));
			info.setSwitchOffTime(rs.getString("switch_off_time"));
			info.setOperatedBy((UUID) rs.getObject("operated_by"));
			info.setCheckedBy((UUID) rs.getObject("checked_by"));
			info.setRemarks(rs.getString("remarks"));
			return info;
		}
	}

	class LogSterilePreparedRowMapper implements RowMapper<LogSterilePreparedInfo> {

		@Override
		public LogSterilePreparedInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogSterilePreparedInfo info = new LogSterilePreparedInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setMediumName(rs.getString("medium_name"));
			info.setStorageType(rs.getString("storage_type"));
			info.setRefrigeratorId(rs.getString("refrigerator_id"));
			info.setStorageCondition(rs.getString("storage_condition"));
			info.setStorageDate(rs.getDate("storage_date"));
			info.setStorageQty(rs.getString("storage_qty"));
			info.setLabBatcNo(rs.getString("lab_batch_no"));
			info.setExpDate(rs.getDate("exp_date"));
			info.setPackSize(rs.getString("pack_size"));
			info.setStorageBy((UUID) rs.getObject("storage_by"));
			info.setIssueDate(rs.getDate("issue_date"));
			info.setIssuedQty(rs.getString("issued_qty"));
			info.setPurpose(rs.getString("purpose"));
			info.setIssuedBy((UUID) rs.getObject("issued_by"));
			info.setStockQty(rs.getString("stock_qty"));
			info.setCheckedBy((UUID) rs.getObject("checked_by"));
			info.setIssuedByName(rs.getString("issued_by_name"));
			info.setStorageByName(rs.getString("storage_by_name"));
			info.setCheckedByName(rs.getString("checked_by_name"));
			info.setRemarks(rs.getString("remarks"));

			return info;
		}
	}

	class DisinfectantSolInfoRowMapper implements RowMapper<LogDisinfectantSolInfo> {

		@Override
		public LogDisinfectantSolInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogDisinfectantSolInfo info = new LogDisinfectantSolInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setDisinfecName(rs.getString("disinfec_name"));
			info.setAmtDisinfec(rs.getString("amt_disinfec"));
			info.setAmtDistilled(rs.getString("amt_distilled"));
			info.setTotalAmt(rs.getString("total_amt"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setStoreTemp(rs.getString("store_temp"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setExpDate(rs.getDate("exp_date"));
			info.setPrepareBy((UUID) rs.getObject("prepare_by"));
			info.setCheckBy((UUID) rs.getObject("check_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setPrepareByName(rs.getString("prepare_by_name"));
			info.setCheckByName(rs.getString("check_by_name"));
			return info;
		}
	}

	class FumigationInfoRowMapper implements RowMapper<LogFumigationInfo> {

		@Override
		public LogFumigationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogFumigationInfo info = new LogFumigationInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setLocation(rs.getString("location"));
			info.setRoomNo(rs.getString("room_no"));
			info.setPotassium(rs.getString("potassium"));
			info.setFormal(rs.getString("formal"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setDoneBy((UUID) rs.getObject("done_by"));
			info.setCheckBy((UUID) rs.getObject("check_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setDoneByName(rs.getString("done_by_name"));
			info.setCheckByName(rs.getString("check_by_name"));
			return info;
		}
	}

	class RefCultureInfoRowMapper implements RowMapper<LogRefCultureInfo> {

		@Override
		public LogRefCultureInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogRefCultureInfo info = new LogRefCultureInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setMfgSupplierId((UUID) rs.getObject("mfg_supplier_id"));
			info.setRefSeedName(rs.getString("ref_seed_name"));
			info.setAtccNo(rs.getString("atcc_no"));
			info.setBatchLotNo(rs.getString("batch_lot_no"));
			info.setCertiAvail(rs.getString("certi_avail"));
			info.setReceiveDate(rs.getDate("receive_date"));
			info.setExpDate(rs.getDate("exp_date"));
			info.setVerifyDate(rs.getDate("verify_date"));
			info.setReceiveBy((UUID) rs.getObject("receive_by"));
			info.setCheckBy((UUID) rs.getObject("check_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setMfgSupplierName(rs.getString("mfg_supplier_name"));
			info.setReceiveByName(rs.getString("receive_by_name"));
			info.setCheckByName(rs.getString("check_by_name"));
			return info;
		}
	}

	// Start
	// 20200722============================================================================
	public List<LogMCColonyCounterInfo> colonyCounterRowMapperInfos(String equipmentId, String checkedStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, record_date, start_time,end_time, operate_by, checked_by, remarks,	checked_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name,		"
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(checked_by) checked_by_name	 from log_mc_colony_counter_infos a where "
						+ " checked_status="
						+ ((checkedStatus != null && !checkedStatus.isEmpty()) ? "'" + checkedStatus + "'"
								: "checked_status")
						+ " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new ColonyCounterRowMapper());
	}

	class ColonyCounterRowMapper implements RowMapper<LogMCColonyCounterInfo> {

		@Override
		public LogMCColonyCounterInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCColonyCounterInfo info = new LogMCColonyCounterInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCheckedBy((UUID) rs.getObject("checked_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCheckedByName(rs.getString("checked_by_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setCheckedStatus(rs.getString("checked_status"));

			return info;
		}
	}

	public void saveColonyCounter(LogMCColonyCounterInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_colony_counter_infos ( equipment_id, record_date, start_time, end_time,  operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?) ",
					info.getEquipmentId(), date, info.getStartTime(), info.getEndTime(), user.getId(),
					info.getRemarks(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_colony_counter_infos SET  equipment_id =?, start_time =?,end_time =?,"
							+ "remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getEquipmentId(), info.getStartTime(), info.getEndTime(), info.getRemarks(), user.getId(),
					time, info.getId());
		}
	}

	public void saveCheckedDynamicTable(CommonInfo info, String tableName) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE " + tableName
						+ " SET  checked_status =?,remarks =?, checked_by = ?, checked_at = ? where id = ?",
				'Y', info.getRemarks(), user.getId(), time, info.getId());
	}

	public void saveVerifiedDynamicTable(CommonInfo info, String tableName) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE " + tableName
						+ " SET  verified_status =?,remarks =?, verified_by = ?, verified_at = ? where id = ?",
				'Y', info.getRemarks(), user.getId(), time, info.getId());
	}

	public void saveOpenedDynamicTable(CommonInfo info, String tableName) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE " + tableName + " SET  opening_status =?, opening_by = ?, opening_at = ? where id = ?", 'Y',
				user.getId(), time, info.getId());
	}
	public void saveFinishedDynamicTable(CommonInfo info, String tableName) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE " + tableName + " SET  finish_status =?, finished_by = ?, finished_at = ? where id = ?", 'Y',
				user.getId(), time, info.getId());
	}

	public void saveIssuedDynamicTable(CommonInfo info, String tableName) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update("UPDATE " + tableName + " SET  issued_status =?, issued_by = ?, issued_at = ? where id = ?",
				'Y', user.getId(), time, info.getId());
	}

	public void saveVerifyDynamicTable(CommonInfo info, String tableName) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE " + tableName + " SET  verify_status =?, checked_by = ?, checked_at = ? where id = ?", 'Y',
				user.getId(), time, info.getId());
	}

	public void saveCleanedDynamicTable(CommonInfo info, String tableName) {
		jdbcTemplate = new JdbcTemplate(datasource);
		// User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		jdbcTemplate.update(
				"UPDATE " + tableName + " SET  cleaned_status =?, cleaned_by = ?, cleaned_at = ? where id = ?", 'Y',
				info.getCleanBy(), time, info.getId());
		
	}

	public void saveCleanedDynamicSelfTable(CommonInfo info, String tableName) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		jdbcTemplate.update(
				"UPDATE " + tableName
						+ " SET  cleaned_status =?,remarks =?, cleaned_by = ?, cleaned_at = ? where id = ?",
				'Y', info.getRemarks(), user.getId(), time, info.getId());
	}

	// Microscope
	public List<LogMCMicroscopeInfo> microscopeInfoRowMapperInfos(String equipmentId, String checkedStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, record_date, start_time,end_time, operate_by, checked_by, remarks,	checked_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name,"
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(checked_by) checked_by_name	 from log_mc_microscope_infos a where "
						+ " checked_status="
						+ ((checkedStatus != null && !checkedStatus.isEmpty()) ? "'" + checkedStatus + "'"
								: "checked_status")
						+ " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new MicroscopeRowMapper());
	}

	public List<MaterialInfo> findMaterialForSterilized(String type, String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select a.id, material_name, material_code, material_type_id, fnc_material_type_nm(a.material_type_id) material_type_nm,b.type_code, is_chemical, is_microbiological,"
						+ " chemical_sample_amt, chemical_retention_amt, chemical_total, micro_sample_amt, micro_retention_amt, "
						+ " micro_total, total_amt,unit_id,fnc_unit_nm(unit_id) unit_name, a.status, storage_con_id, storage_condition, mat_sample_procedure, mat_method_id, "
						+ " fnc_method_name(mat_method_id) sample_method_name "
						+ " from lims_material_infos a, lims_material_type_infos b" + " where a.material_type_id=b.id "
						+ " and is_microbiological='Y'  " + " and b.type_code="
						+ (type != null ? "'" + type + "'" : "b.type_code") + " "
						+ " and a.company_id = ?   order by material_name ",
				new Object[] { user.getCompanyId() }, new MaterialInfoRowMapper());
	}

	class MaterialInfoRowMapper implements RowMapper<MaterialInfo> {

		@Override
		public MaterialInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			MaterialInfo info = new MaterialInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setMaterialName(rs.getString("material_name"));
			info.setMaterialCode(rs.getString("material_code"));
			info.setMaterialTypeId((UUID) rs.getObject("material_type_id"));
			info.setTypeCode(rs.getString("type_code"));
			info.setMaterialTypeName(rs.getString("material_type_nm"));
			info.setIsChemical(rs.getString("is_chemical"));
			info.setIsMicrobiological(rs.getString("is_microbiological"));
			info.setChemicalSampleAmt(rs.getString("chemical_sample_amt"));
			info.setChemicalRetentionAmt(rs.getString("chemical_retention_amt"));
			info.setChemicalTotal(rs.getString("chemical_total"));
			info.setMicroSampleAmt(rs.getString("micro_sample_amt"));
			info.setMicroRetentionAmt(rs.getString("micro_retention_amt"));
			info.setMicroTotal(rs.getString("micro_total"));
			info.setTotalAmt(rs.getString("total_amt"));
			info.setUnitId((UUID) rs.getObject("unit_id"));
			info.setUnitName(rs.getString("unit_name"));
			info.setStatus(rs.getString("status"));
			info.setStorageConId((UUID) rs.getObject("storage_con_id"));
			info.setStorageCondition(rs.getString("storage_condition"));
			info.setMatSamProcedure(rs.getString("mat_sample_procedure"));
			info.setMatMethodId((UUID) rs.getObject("mat_method_id"));
			info.setSamplePlanName(rs.getString("sample_method_name"));
			return info;
		}
	}

	class MicroscopeRowMapper implements RowMapper<LogMCMicroscopeInfo> {

		@Override
		public LogMCMicroscopeInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCMicroscopeInfo info = new LogMCMicroscopeInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCheckedBy((UUID) rs.getObject("checked_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCheckedByName(rs.getString("checked_by_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setCheckedStatus(rs.getString("checked_status"));

			return info;
		}
	}

	public void saveMicroscope(LogMCMicroscopeInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_microscope_infos ( equipment_id, record_date, start_time, end_time,  operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?) ",
					info.getEquipmentId(), date, info.getStartTime(), info.getEndTime(), user.getId(),
					info.getRemarks(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_microscope_infos SET  equipment_id =?, start_time =?,end_time =?,"
							+ "remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getEquipmentId(), info.getStartTime(), info.getEndTime(), info.getRemarks(), user.getId(),
					time, info.getId());
		}
	}
	// Water Bath=============================

	public List<LogMCWaterBathInfo> waterBathInfoRowMapperInfos(String equipmentId, String checkedStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(" SELECT id, record_date, switch_on_time start_time,switch_off_time end_time,"
				+ " temperature, materials_id, fnc_material_nm (materials_id)  material_name,"
				+ " operate_by, checked_by, remarks,	checked_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name,		"
				+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(checked_by) checked_by_name	 from log_mc_water_bath_infos a where "
				+ " checked_status="
				+ ((checkedStatus != null && !checkedStatus.isEmpty()) ? "'" + checkedStatus + "'" : "checked_status")
				+ " and equipment_id="
				+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
				+ " and company_id = ?  order by record_date desc ", new Object[] { user.getCompanyId() },
				new WaterBathMapper());
	}

	class WaterBathMapper implements RowMapper<LogMCWaterBathInfo> {

		@Override
		public LogMCWaterBathInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCWaterBathInfo info = new LogMCWaterBathInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCheckedBy((UUID) rs.getObject("checked_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCheckedByName(rs.getString("checked_by_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setCheckedStatus(rs.getString("checked_status"));

			info.setTemperature(rs.getString("temperature"));
			info.setMaterialId((UUID) rs.getObject("materials_id"));
			info.setMaterialName(rs.getString("material_name"));
			return info;
		}
	}

	public void saveWaterBath(LogMCWaterBathInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_water_bath_infos ( equipment_id,materials_id,temperature, record_date, switch_on_time, switch_off_time,  operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?) ",
					info.getEquipmentId(), info.getMaterialId(), info.getTemperature(), date, info.getStartTime(),
					info.getEndTime(), user.getId(), info.getRemarks(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_water_bath_infos SET  equipment_id =?,materials_id =?,temperature =?, switch_on_time =?,switch_off_time =?,"
							+ "remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getEquipmentId(), info.getMaterialId(), info.getTemperature(), info.getStartTime(),
					info.getEndTime(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	// Laminar Air Flow=============================

	public List<LogMCLaminarAirFlowInfo> laminarAirFlowInfoRowMapperInfos(String equipmentId, String checkedStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(" SELECT id, record_date, laf_on_time , uv_on_time, laf_off_time, uv_off_time,"
				+ " purpose, cleaning_agent_id,"
				+ "(SELECT agent_name FROM lims_cleaning_agent_infos b WHERE b.id=a.cleaning_agent_id) cleaning_agent_name   ,"
				+ " operate_by, checked_by, remarks,	checked_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name,		"
				+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(checked_by) checked_by_name	 from log_mc_laminar_air_flow_infos a where "
				+ " checked_status="
				+ ((checkedStatus != null && !checkedStatus.isEmpty()) ? "'" + checkedStatus + "'" : "checked_status")
				+ " and equipment_id="
				+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
				+ " and company_id = ?  order by record_date desc ", new Object[] { user.getCompanyId() },
				new LaminarAirFlowMapper());
	}

	class LaminarAirFlowMapper implements RowMapper<LogMCLaminarAirFlowInfo> {

		@Override
		public LogMCLaminarAirFlowInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCLaminarAirFlowInfo info = new LogMCLaminarAirFlowInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setLafOnTime(rs.getString("laf_on_time"));
			info.setLafOffTime(rs.getString("laf_off_time"));
			info.setUvOnTime(rs.getString("uv_on_time"));
			info.setUvOffTime(rs.getString("uv_off_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCheckedBy((UUID) rs.getObject("checked_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCheckedByName(rs.getString("checked_by_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setCheckedStatus(rs.getString("checked_status"));

			info.setPurpose(rs.getString("purpose"));
			info.setAgentId((UUID) rs.getObject("cleaning_agent_id"));
			info.setAgentName(rs.getString("cleaning_agent_name"));
			return info;
		}
	}

	public void saveLaminarAirFlow(LogMCLaminarAirFlowInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_laminar_air_flow_infos ( equipment_id,cleaning_agent_id,purpose, record_date, laf_on_time, laf_off_time,"
							+ "uv_on_time,uv_off_time,  operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getEquipmentId(), info.getAgentId(), info.getPurpose(), date, info.getLafOnTime(),
					info.getLafOffTime(), info.getUvOnTime(), info.getUvOffTime(), user.getId(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_laminar_air_flow_infos SET  equipment_id =?,cleaning_agent_id =?,purpose =?, laf_on_time =?,laf_off_time =?,"
							+ "uv_on_time =?,uv_off_time =?,"
							+ "remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getEquipmentId(), info.getAgentId(), info.getPurpose(), info.getLafOnTime(),
					info.getLafOffTime(), info.getUvOnTime(), info.getUvOffTime(), info.getRemarks(), user.getId(),
					time, info.getId());
		}
	}

	// Ph Meter
	public List<LogMCPhMeterInfo> findPhMeterInfos(String equipmentId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, equipment_id, fnc_equipment_name(equipment_id) equipment_name,  record_date,"
						+ " record_time, ph_result, operate_by, verified_by,verified_status, remarks, temperature,batch_no,"
						+ " fnc_user_nm(operate_by) operateBy_name,  fnc_user_nm(verified_by) verified_by_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name "
						+ " from log_mc_ph_meter_infos a where" + " verified_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verified_status")
						+ " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new PhMeterInfoRowMapper());
	}

	public void savePhMeterInfo(LogMCPhMeterInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);
		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_ph_meter_infos ( qc_reference_id,batch_no, temperature, equipment_id,record_date, record_time, ph_result, operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcReferenceId(), info.getBatchNo(), info.getTemperature(), info.getEquipmentId(), date,
					recordTime, info.getPhResult(), user.getId(), info.getRemarks(), user.getCompanyId(), user.getId(),
					time);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_ph_meter_infos SET qc_reference_id =?,batch_no =?, temperature =?,   equipment_id =?, ph_result =?, "
							+ "  remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getQcReferenceId(), info.getBatchNo(), info.getTemperature(), info.getEquipmentId(),
					info.getPhResult(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	class PhMeterInfoRowMapper implements RowMapper<LogMCPhMeterInfo> {

		@Override
		public LogMCPhMeterInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCPhMeterInfo info = new LogMCPhMeterInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setPhResult(rs.getString("ph_result"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setVerifiedBy((UUID) rs.getObject("verified_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setVerifiedByName(rs.getString("verified_by_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifiedStatus(rs.getString("verified_status"));
			info.setTemperature(rs.getString("temperature"));
			info.setBatchNo(rs.getString("batch_no"));

			return info;
		}
	}

	public List<LogMCAnalyticalBalanceInfo> findAnalyticalBalanceInfos(String equipmentId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, qc_reference_id, sample_type_id, record_date, record_time, lot_no, qty, check_type,"
						+ " done_by, verified_by, remarks, fnc_user_nm(done_by) doneBy_name,fnc_equipment_name(equipment_id) equipment_name, "
						+ " equipment_id,verified_status,  fnc_user_nm(verified_by) verifyBy_name,"
						+ "  fnc_user_nm(verified_by) verifyBy_name, fnc_material_type_nm(sample_type_id) sample_type,  "
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from log_mc_analytical_balance_infos a where" + " verified_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verified_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",

				new Object[] { user.getCompanyId() }, new AnalyticalBalanceMCInfoRowMapper());
	}

	class AnalyticalBalanceMCInfoRowMapper implements RowMapper<LogMCAnalyticalBalanceInfo> {

		@Override
		public LogMCAnalyticalBalanceInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCAnalyticalBalanceInfo info = new LogMCAnalyticalBalanceInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcRefId((UUID) rs.getObject("qc_reference_id"));
			info.setQcRefName(rs.getString("reference_name"));
			info.setSampleTypeId((UUID) rs.getObject("sample_type_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setLotNo(rs.getString("lot_no"));
			info.setQty(rs.getString("qty"));
			info.setCheckType(rs.getString("check_type"));
			info.setDoneBy((UUID) rs.getObject("done_by"));
			info.setVerifyBy((UUID) rs.getObject("verified_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setDoneByName(rs.getString("doneBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verified_status"));
			info.setSampleTypeName(rs.getString("sample_type"));

			return info;
		}
	}

	public void saveAnalyticalBalanceMCInfo(LogMCAnalyticalBalanceInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_analytical_balance_infos ( qc_reference_id, sample_type_id, record_date, record_time, lot_no, qty, check_type,"
							+ " equipment_id, done_by, remarks, company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcRefId(), info.getSampleTypeId(), date, recordTime, info.getLotNo(), info.getQty(),
					info.getCheckType(), info.getEquipmentId(), user.getId(), info.getRemarks(), user.getCompanyId(),
					user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_analytical_balance_infos SET qc_reference_id =?, sample_type_id =?,  lot_no = ?, qty = ?, "
							+ " check_type =?,  equipment_id = ?, remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getQcRefId(), info.getSampleTypeId(), info.getLotNo(), info.getQty(), info.getCheckType(),
					info.getEquipmentId(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	public List<LogMCAnalystValidationInfo> findLogAnalystValidation(String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, analyst_id,ud_emp_id, area_of_competency,date_of_competency ,next_date_of_competency, status, fnc_emp_name(analyst_id) analyst_name , "
						+ " product_id, (SELECT material_name FROM lims_material_infos b WHERE b.id=a.product_id) product_name ,"
						+ " remarks, fnc_user_nm (dispensed_by) dispensed_by, fnc_user_nm (verified_by) verify_by,verified_at,verified_status, record_date,record_time "
						+ " from log_mc_analyst_validation_infos a " + " where verified_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verified_status")
						+ " and  company_id = ? ORDER BY verified_status, record_date desc",
				new Object[] { user.getCompanyId() }, new LogAnalystValidationRowMapper());
	}

	class LogAnalystValidationRowMapper implements RowMapper<LogMCAnalystValidationInfo> {

		@Override
		public LogMCAnalystValidationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCAnalystValidationInfo info = new LogMCAnalystValidationInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRemarks(rs.getString("remarks"));
			info.setDispensedByName(rs.getString("dispensed_by"));
			info.setVerifyName(rs.getString("verify_by"));
			info.setVerifiedAt(rs.getTimestamp("verified_at"));
			info.setIsVerify(rs.getString("verified_status"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setAnalystId((UUID) rs.getObject("analyst_id"));
			info.setAnalystName(rs.getString("analyst_name"));
			info.setAreaOfCompetency(rs.getString("area_of_competency"));
			info.setDateOfCompetency(rs.getDate("date_of_competency"));
			info.setNextDateOfCompetency(rs.getDate("next_date_of_competency"));
			info.setStatus(rs.getString("status"));
			info.setUdAnalystId(rs.getString("ud_emp_id"));
			info.setProductId((UUID) rs.getObject("product_id"));
			info.setProductName(rs.getString("product_name"));

			return info;
		}
	}

	public void saveMCAnalystValidation(LogMCAnalystValidationInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm aa");
		String recordTime = formatterTime.format(date);
		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_analyst_validation_infos (analyst_id,ud_emp_id, area_of_competency, date_of_competency, next_date_of_competency, status,"
							+ " dispensed_by, remarks, company_id, created_by, created_at, record_date,record_time) "
							+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
					info.getAnalystId(), info.getUdAnalystId(), info.getAreaOfCompetency(), info.getDateOfCompetency(),
					info.getNextDateOfCompetency(), info.getStatus(), user.getId(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time, date, recordTime);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_analyst_validation_infos SET  analyst_id =?, ud_emp_id =?, area_of_competency=?, date_of_competency=?,next_date_of_competency=?,status=?,"
							+ "   dispensed_by =?, remarks =?, updated_by = ?, updated_at = ? where id = ?",
					info.getAnalystId(), info.getUdAnalystId(), info.getAreaOfCompetency(), info.getDateOfCompetency(),
					info.getNextDateOfCompetency(), info.getStatus(), user.getId(), info.getRemarks(), user.getId(),
					time, info.getId());
		}
	}

	// Allotted SAmple for Analyst Validation
	public List<LogMCAllottedSampleInfo> findLogAllottedSampleInfo(String eqipId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id,  remarks, fnc_user_nm (dispensed_by) dispensed_by, record_date,record_time, "
						+ " analyst_id,  product_id,  document_code, batch_no, test_method_id , "
						+ " qc_reference_id, (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name, "
						+ " fnc_emp_name(analyst_id) analyst_name ,"
						+ " (SELECT method_name FROM lims_test_method_infos b WHERE b.id=a.test_method_id) test_method_name ,"
						+ " (SELECT material_name FROM lims_material_infos b WHERE b.id=a.product_id) product_name "
						+ " from log_mc_allotted_sample_infos a " + " where "
						+ "  company_id = ? ORDER BY  record_date desc",
				new Object[] { user.getCompanyId() }, new LogAllottedSampleRowMapper());
	}

	class LogAllottedSampleRowMapper implements RowMapper<LogMCAllottedSampleInfo> {

		@Override
		public LogMCAllottedSampleInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCAllottedSampleInfo info = new LogMCAllottedSampleInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRemarks(rs.getString("remarks"));
			info.setDispensedByName(rs.getString("dispensed_by"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setAnalystId((UUID) rs.getObject("analyst_id"));
			info.setAnalystName(rs.getString("analyst_name"));
			info.setProductId((UUID) rs.getObject("product_id"));
			info.setProductName(rs.getString("product_name"));
			info.setDocumentCode(rs.getString("document_code"));
			info.setBatchNo(rs.getString("batch_no"));
			info.setTestMethodId((UUID) rs.getObject("test_method_id"));
			info.setTestMethodName(rs.getString("test_method_name"));
			info.setQcRefId((UUID) rs.getObject("qc_reference_id"));
			info.setQcRefName(rs.getString("reference_name"));

			return info;
		}

	}

	public void saveAlloteeSample(LogMCAllottedSampleInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm aa");
		String recordTime = formatterTime.format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_allotted_sample_infos (qc_reference_id,analyst_id,product_id, test_method_id,document_code,batch_no, "
							+ " dispensed_by, remarks, company_id, created_by, created_at, record_date,record_time) "
							+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
					info.getQcRefId(), info.getAnalystId(), info.getProductId(), info.getTestMethodId(),
					info.getDocumentCode(), info.getBatchNo(), user.getId(), info.getRemarks(), user.getCompanyId(),
					user.getId(), time, date, recordTime);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_allotted_sample_infos SET qc_reference_id =?, analyst_id =?, product_id =?, test_method_id=?, "
							+ "   document_code =?, batch_no =?, "
							+ "   remarks =?, updated_by = ?, updated_at = ? where id = ?",
					info.getQcRefId(), info.getAnalystId(), info.getProductId(), info.getTestMethodId(),
					info.getDocumentCode(), info.getBatchNo(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	// Incubetor Info
	public List<LogMCTempIncubatorRecordInfo> getTempIncubatorRecordInfos(String equipmentId, String eveningStatus,
			String cleanedStatus, String checkedStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT a.id,agent_id, evening_time, morning_time , morning_temp, evening_time, evening_temp, done_by, a.equipment_id,  record_date, record_time, remarks,"
						+ " evening_status, cleaned_status, checked_status, "
						+ " fnc_user_nm(done_by) doneByName,fnc_user_nm(evening_done_by) eveningDoneByName ,"
						+ " cleaned_by,fnc_emp_name(cleaned_by) cleaned_by_name , "
						+ " fnc_user_nm(checked_by) checked_by_name , "
						+ "(SELECT agent_name FROM lims_cleaning_agent_infos b WHERE b.id=a.agent_id) agent_name, "
						+ " fnc_equipment_name(a.equipment_id) equipmentBy_Name  "
						+ " from log_mc_temp_incubator_record_infos a JOIN lims_incubator_setup_infos st ON a.equipment_id=st.equipment_id "
						+ " where a.equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "a.equipment_id")
						+ " and evening_status="
						+ ((eveningStatus != null && !eveningStatus.isEmpty()) ? "'" + eveningStatus + "'"
								: "evening_status")
						+ " and cleaned_status="
						+ ((cleanedStatus != null && !cleanedStatus.isEmpty()) ? "'" + cleanedStatus + "'"
								: "cleaned_status")
						+ " and checked_status="
						+ ((checkedStatus != null && !checkedStatus.isEmpty()) ? "'" + checkedStatus + "'"
								: "checked_status")
						+ " and  a.company_id = ? ORDER BY  record_date desc",
				new Object[] { user.getCompanyId() }, new incubatorRecordInfoRowMapper());
	}

	class incubatorRecordInfoRowMapper implements RowMapper<LogMCTempIncubatorRecordInfo> {

		@Override
		public LogMCTempIncubatorRecordInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCTempIncubatorRecordInfo info = new LogMCTempIncubatorRecordInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setRemarks(rs.getString("remarks"));
			info.setDoneBy((UUID) rs.getObject("done_by"));
			info.setDoneByName(rs.getString("doneByName"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipmentBy_Name"));
			info.setMorningTime(rs.getString("morning_time"));
			info.setMorningTemp(rs.getString("morning_temp"));
			info.setEveningTime(rs.getString("evening_time"));
			info.setEveningTemp(rs.getString("evening_temp"));
			info.setEveningDoneByName(rs.getString("eveningDoneByName"));
			info.setAgentId((UUID) rs.getObject("agent_id"));
			info.setEveningStatus(rs.getString("evening_status"));
			info.setCleanedStatus(rs.getString("cleaned_status"));
			info.setCheckedStatus(rs.getString("checked_status"));
			info.setAgentName(rs.getString("agent_name"));
			info.setCleanedByName(rs.getString("cleaned_by_name"));
			info.setCleanedBy((UUID) rs.getObject("cleaned_by"));
			info.setCheckedByName(rs.getString("checked_by_name"));

			return info;
		}
	}

	public void saveTempIncubatorMorningRecord(LogMCTempIncubatorRecordInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_temp_incubator_record_infos (morning_time, morning_temp,  done_by, equipment_id, record_date, record_time,  company_id) VALUES (?,?,?,?,?,?,?) ",
					info.getMorningTime(), info.getMorningTemp(), user.getId(), info.getEquipmentId(), date, recordTime,
					user.getCompanyId());
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_temp_incubator_record_infos SET equipment_id =?, morning_time =?, morning_temp =?, "
							+ "updated_at = ?, updated_by = ? where id = ?",
					info.getEquipmentId(), info.getMorningTime(), info.getMorningTemp(), date, user.getId(),
					info.getId());
		}
	}

	public void saveTempIncubatorEveningRecord(LogMCTempIncubatorRecordInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		jdbcTemplate.update(
				"UPDATE log_mc_temp_incubator_record_infos SET agent_id =?, evening_time =?, evening_temp =?, evening_status=?,remarks=?,"
						+ "updated_at = ?, evening_done_by = ? where id = ?",
				info.getAgentId(), info.getEveningTime(), info.getEveningTemp(), 'Y', info.getRemarks(), date,
				user.getId(), info.getId());

	}

	// Refrigeratio
	public List<LogMCTempRefrigeratorRecordInfo> getTempRefrigeratorRecordInfos(String equipmentId,
			String eveningStatus, String cleanedStatus, String checkedStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT a.id,agent_id, morning_time, morning_temp, evening_time, evening_temp, done_by, a.equipment_id,  record_date, record_time, remarks,"
						+ " evening_status, cleaned_status, checked_status, "
						+ " fnc_user_nm(done_by) doneByName,fnc_user_nm(evening_done_by) eveningDoneByName ,"
						+ " cleaned_by, fnc_emp_name(cleaned_by) cleaned_by_name , "
						+ " fnc_user_nm(checked_by) checked_by_name , "
						+ "(SELECT agent_name FROM lims_cleaning_agent_infos b WHERE b.id=a.agent_id) agent_name, "
						+ " fnc_equipment_name(a.equipment_id) equipmentBy_Name  "
						+ " from log_mc_temp_refrigerator_record_infos a JOIN lims_refrigerator_setup_infos st ON a.equipment_id=st.equipment_id "
						+ " where a.equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "a.equipment_id")
						+ " and evening_status="
						+ ((eveningStatus != null && !eveningStatus.isEmpty()) ? "'" + eveningStatus + "'"
								: "evening_status")
						+ " and cleaned_status="
						+ ((cleanedStatus != null && !cleanedStatus.isEmpty()) ? "'" + cleanedStatus + "'"
								: "cleaned_status")
						+ " and checked_status="
						+ ((checkedStatus != null && !checkedStatus.isEmpty()) ? "'" + checkedStatus + "'"
								: "checked_status")
						+ " and  a.company_id = ? ORDER BY  record_date desc",
				new Object[] { user.getCompanyId() }, new refrigeratorRecordInfoRowMapper());
	}

	class refrigeratorRecordInfoRowMapper implements RowMapper<LogMCTempRefrigeratorRecordInfo> {

		@Override
		public LogMCTempRefrigeratorRecordInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCTempRefrigeratorRecordInfo info = new LogMCTempRefrigeratorRecordInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setRemarks(rs.getString("remarks"));
			info.setDoneBy((UUID) rs.getObject("done_by"));
			info.setDoneByName(rs.getString("doneByName"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipmentBy_Name"));
			info.setMorningTime(rs.getString("morning_time"));
			info.setMorningTemp(rs.getString("morning_temp"));
			info.setEveningTime(rs.getString("evening_time"));
			info.setEveningTemp(rs.getString("evening_temp"));
			info.setEveningDoneByName(rs.getString("eveningDoneByName"));
			info.setAgentId((UUID) rs.getObject("agent_id"));
			info.setEveningStatus(rs.getString("evening_status"));
			info.setCleanedStatus(rs.getString("cleaned_status"));
			info.setCheckedStatus(rs.getString("checked_status"));
			info.setAgentName(rs.getString("agent_name"));
			info.setCleanedByName(rs.getString("cleaned_by_name"));
			info.setCheckedByName(rs.getString("checked_by_name"));
			info.setCleanedBy((UUID) rs.getObject("cleaned_by"));

			return info;
		}
	}

	public void saveTempRefrigeratorMorningRecord(LogMCTempRefrigeratorRecordInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_temp_refrigerator_record_infos (morning_time, morning_temp,  done_by, equipment_id, record_date, record_time,  company_id) VALUES (?,?,?,?,?,?,?) ",
					info.getMorningTime(), info.getMorningTemp(), user.getId(), info.getEquipmentId(), date, recordTime,
					user.getCompanyId());
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_temp_refrigerator_record_infos SET equipment_id =?, morning_time =?, morning_temp =?, "
							+ "updated_at = ?, updated_by = ? where id = ?",
					info.getEquipmentId(), info.getMorningTime(), info.getMorningTemp(), date, user.getId(),
					info.getId());
		}
	}

	public void saveTempRefrigeratorEveningRecord(LogMCTempRefrigeratorRecordInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		jdbcTemplate.update(
				"UPDATE log_mc_temp_refrigerator_record_infos SET agent_id =?, evening_time =?, evening_temp =?, evening_status=?,remarks=?,"
						+ "updated_at = ?, evening_done_by = ? where id = ?",
				info.getAgentId(), info.getEveningTime(), info.getEveningTemp(), 'Y', info.getRemarks(), date,
				user.getId(), info.getId());

	}

	// ===============Wash Dryer
	// 20200722============================================================================
	public List<LogMCWashDryerInfo> washDryerRowMapperInfos(String equipmentId, String cleanedStatus,
			String checkedStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, record_date, start_time,end_time, operate_by, checked_by, remarks,	checked_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name,		"
						+ " item_id,(select item_name from lims_lab_item_setup_infos where id=a.item_id) item_name, "
						+ " cleaned_by,fnc_emp_name(cleaned_by) cleaned_by_name ,cleaned_status , "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(checked_by) checked_by_name	 from log_mc_wash_dry_infos a where "
						+ " cleaned_status="
						+ ((cleanedStatus != null && !cleanedStatus.isEmpty()) ? "'" + cleanedStatus + "'"
								: "cleaned_status")
						+ " and checked_status="
						+ ((checkedStatus != null && !checkedStatus.isEmpty()) ? "'" + checkedStatus + "'"
								: "checked_status")
						+ " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new washDryerRowMapper());
	}

	class washDryerRowMapper implements RowMapper<LogMCWashDryerInfo> {

		@Override
		public LogMCWashDryerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCWashDryerInfo info = new LogMCWashDryerInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCheckedBy((UUID) rs.getObject("checked_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCheckedByName(rs.getString("checked_by_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setCheckedStatus(rs.getString("checked_status"));
			info.setCleanedStatus(rs.getString("cleaned_status"));
			info.setCleanedBy((UUID) rs.getObject("cleaned_by"));
			info.setCleanedByName(rs.getString("cleaned_by_name"));
			info.setItemId((UUID) rs.getObject("item_id"));
			info.setItemName(rs.getString("item_name"));

			return info;
		}
	}

	public void saveWashDryer(LogMCWashDryerInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_wash_dry_infos ( equipment_id,item_id, record_date, start_time, end_time,  operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?) ",
					info.getEquipmentId(), info.getItemId(), date, info.getStartTime(), info.getEndTime(), user.getId(),
					info.getRemarks(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_wash_dry_infos SET  equipment_id =?,item_id =?, start_time =?,end_time =?,"
							+ "remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getEquipmentId(), info.getItemId(), info.getStartTime(), info.getEndTime(), info.getRemarks(),
					user.getId(), time, info.getId());
		}
	}

	// ===============AUTO CLAVE ===================================================
	public List<LogMCAutoClaveInfo> autoClaveRowMapperInfos(String equipmentId, String cleanedStatus,
			String checkedStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(" SELECT id,material_id, fnc_material_nm (material_id)  material_name, "// Will be
																											// Material
																											// Name
				+ " record_date, ster_start_time,ster_end_time,exha_start_time,exha_end_time,"
				+ " heating_cycle,total_cycle,batch_no,indicator_tape, cleaned_by,"
				+ " operate_by, checked_by, remarks,	checked_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name,		"
				+ "fnc_emp_name(cleaned_by) cleaned_by_name ,cleaned_status , "
				+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(checked_by) checked_by_name	 from log_mc_auto_clave_infos a where "
				+ " cleaned_status="
				+ ((cleanedStatus != null && !cleanedStatus.isEmpty()) ? "'" + cleanedStatus + "'" : "cleaned_status")
				+ " and checked_status="
				+ ((checkedStatus != null && !checkedStatus.isEmpty()) ? "'" + checkedStatus + "'" : "checked_status")
				+ " and equipment_id="
				+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
				+ " and company_id = ?  order by record_date desc ", new Object[] { user.getCompanyId() },
				new autoClaveRowMapper());
	}

	class autoClaveRowMapper implements RowMapper<LogMCAutoClaveInfo> {

		@Override
		public LogMCAutoClaveInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCAutoClaveInfo info = new LogMCAutoClaveInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setSterStartTime(rs.getString("ster_start_time"));
			info.setSterEndTime(rs.getString("ster_end_time"));
			info.setExhaStartTime(rs.getString("exha_start_time"));
			info.setExhaEndTime(rs.getString("exha_end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCheckedBy((UUID) rs.getObject("checked_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCheckedByName(rs.getString("checked_by_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setCheckedStatus(rs.getString("checked_status"));
			info.setCleanedStatus(rs.getString("cleaned_status"));
			info.setCleanedByName(rs.getString("cleaned_by_name"));
			info.setMaterialId((UUID) rs.getObject("material_id"));
			info.setMaterialName(rs.getString("material_name"));
			info.setHeatingCycle(rs.getString("heating_cycle"));
			info.setTotalCycle(rs.getString("total_cycle"));
			info.setBatchNo(rs.getString("batch_no"));
			info.setIndicatorTape(rs.getString("indicator_tape"));
			info.setCleanedBy((UUID) rs.getObject("cleaned_by"));
			return info;
		}
	}

	public void saveAutoClave(LogMCAutoClaveInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_auto_clave_infos ( equipment_id,material_id, record_date, ster_start_time, ster_end_time,"
							+ "heating_cycle,total_cycle,batch_no,indicator_tape,"
							+ " exha_start_time, exha_end_time, operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getEquipmentId(), info.getMaterialId(), date, info.getSterStartTime(), info.getSterEndTime(),
					info.getHeatingCycle(), info.getTotalCycle(), info.getBatchNo(), info.getIndicatorTape(),
					info.getExhaStartTime(), info.getExhaEndTime(), user.getId(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_auto_clave_infos SET  equipment_id =?,material_id =?, ster_start_time =?,ster_end_time =?,"
							+ "exha_start_time =?,exha_end_time =?,"

							+ "heating_cycle =?,total_cycle =?,batch_no =?,indicator_tape =?,"

							+ "remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getEquipmentId(), info.getMaterialId(), info.getSterStartTime(), info.getSterEndTime(),
					info.getExhaStartTime(), info.getExhaEndTime(),

					info.getHeatingCycle(), info.getTotalCycle(), info.getBatchNo(), info.getIndicatorTape(),

					info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	// =============== Hot Air Oven
	// ===================================================
	public List<LogMCHotAirOvenInfo> hotAirOvenRowMapperInfos(String equipmentId, String cleanedStatus,
			String checkedStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(" SELECT id,material_id, fnc_material_nm (material_id)  material_name, "
				+ " record_date, ster_start_time,ster_end_time," + " heating_cycle,total_cycle,batch_no,indicator_tape,"
				+ " operate_by, checked_by, remarks,	checked_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name,		"
				+ " fnc_emp_name(cleaned_by) cleaned_by_name ,cleaned_status ,cleaned_by, "
				+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(checked_by) checked_by_name	 from log_mc_hot_air_oven_infos a where "
				+ " cleaned_status="
				+ ((cleanedStatus != null && !cleanedStatus.isEmpty()) ? "'" + cleanedStatus + "'" : "cleaned_status")
				+ " and checked_status="
				+ ((checkedStatus != null && !checkedStatus.isEmpty()) ? "'" + checkedStatus + "'" : "checked_status")
				+ " and equipment_id="
				+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
				+ " and company_id = ?  order by record_date desc ", new Object[] { user.getCompanyId() },
				new hotAirOvenRowMapper());
	}

	class hotAirOvenRowMapper implements RowMapper<LogMCHotAirOvenInfo> {

		@Override
		public LogMCHotAirOvenInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCHotAirOvenInfo info = new LogMCHotAirOvenInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setSterStartTime(rs.getString("ster_start_time"));
			info.setSterEndTime(rs.getString("ster_end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCheckedBy((UUID) rs.getObject("checked_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCheckedByName(rs.getString("checked_by_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setCheckedStatus(rs.getString("checked_status"));
			info.setCleanedStatus(rs.getString("cleaned_status"));
			info.setCleanedBy((UUID) rs.getObject("cleaned_by"));
			info.setCleanedByName(rs.getString("cleaned_by_name"));
			info.setMaterialId((UUID) rs.getObject("material_id"));
			info.setMaterialName(rs.getString("material_name"));
			info.setHeatingCycle(rs.getString("heating_cycle"));
			info.setTotalCycle(rs.getString("total_cycle"));
			info.setBatchNo(rs.getString("batch_no"));
			info.setIndicatorTape(rs.getString("indicator_tape"));

			return info;
		}
	}

	public void saveHotAirOven(LogMCHotAirOvenInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_hot_air_oven_infos ( equipment_id,material_id, record_date, ster_start_time, ster_end_time,"
							+ "heating_cycle,total_cycle,batch_no,indicator_tape," + " operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getEquipmentId(), info.getMaterialId(), date, info.getSterStartTime(), info.getSterEndTime(),
					info.getHeatingCycle(), info.getTotalCycle(), info.getBatchNo(), info.getIndicatorTape(),
					user.getId(), info.getRemarks(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_hot_air_oven_infos SET  equipment_id =?,material_id =?, ster_start_time =?,ster_end_time =?,"
							+ "heating_cycle =?,total_cycle =?,batch_no =?,indicator_tape =?,"
							+ "remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getEquipmentId(), info.getMaterialId(), info.getSterStartTime(), info.getSterEndTime(),
					info.getHeatingCycle(), info.getTotalCycle(), info.getBatchNo(), info.getIndicatorTape(),
					info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	// Start LB
	// PRPARATION============================================================================
	public List<LogMCLbPreparationInfo> lbPreparationRowMapperInfos(String checkedStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, record_date, start_time,end_time, operate_by, checked_by, remarks,	checked_status,	"
						+ "	amount_used_water, total_amount_preparation, amount_used_disinfectant,expiry_date, "
						+ " disinfectant_id, (select disinfectant_name from lims_disinfectant_infos where id=a.disinfectant_id) disinfectant_name, "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(checked_by) checked_by_name	 from log_mc_lb_preparation_infos a where "
						+ " checked_status="
						+ ((checkedStatus != null && !checkedStatus.isEmpty()) ? "'" + checkedStatus + "'"
								: "checked_status")
						+ " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new LbPreparationRowMapper());
	}

	class LbPreparationRowMapper implements RowMapper<LogMCLbPreparationInfo> {

		@Override
		public LogMCLbPreparationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCLbPreparationInfo info = new LogMCLbPreparationInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCheckedBy((UUID) rs.getObject("checked_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCheckedByName(rs.getString("checked_by_name"));
			info.setDisinfectantId((UUID) rs.getObject("disinfectant_id"));
			info.setDisinfectantName(rs.getString("disinfectant_name"));
			info.setCheckedStatus(rs.getString("checked_status"));
			info.setAmountUsedWater(rs.getString("amount_used_water"));
			info.setTotalAmountPreparation(rs.getString("total_amount_preparation"));
			info.setAmountUsedDisinfectant(rs.getString("amount_used_disinfectant"));
			info.setExpiryDate(rs.getDate("expiry_date"));

			return info;
		}
	}

	public void saveLbPreparation(LogMCLbPreparationInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		// amountUsedWater totalAmountPreparation amountUsedDisinfectant disinfectantId

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_lb_preparation_infos ( disinfectant_id,expiry_date, "
							+ "amount_used_water,total_amount_preparation,amount_used_disinfectant, "
							+ "record_date, start_time, end_time,  operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getDisinfectantId(), info.getExpiryDate(), info.getAmountUsedWater(),
					info.getTotalAmountPreparation(), info.getAmountUsedDisinfectant(), date, info.getStartTime(),
					info.getEndTime(), user.getId(), info.getRemarks(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_lb_preparation_infos SET  disinfectant_id =?,"
							+ "expiry_date =?,amount_used_water =?,total_amount_preparation =?, "
							+ " amount_used_disinfectant =?, start_time =?,end_time =?,"
							+ "remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getDisinfectantId(), info.getExpiryDate(), info.getAmountUsedWater(),
					info.getTotalAmountPreparation(), info.getAmountUsedDisinfectant(), info.getStartTime(),
					info.getEndTime(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	// Start LB
	// Fumigation============================================================================
	public List<LogMCLbFumigationInfo> lbFumigationRowMapperInfos(String checkedStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, record_date, start_time,end_time, operate_by, checked_by, remarks,	checked_status,	"
						+ "potassium_permanganate,formaldehyde,"
						+ " location_id, (select location_name from lims_location_setup_infos where id=a.location_id) location_name, "
						+ " room_id, (select room_name from lims_room_infos where id=a.room_id) room_name, "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(checked_by) checked_by_name	 from log_mc_lb_fumigation_infos a where "
						+ " checked_status="
						+ ((checkedStatus != null && !checkedStatus.isEmpty()) ? "'" + checkedStatus + "'"
								: "checked_status")
						+ " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new LbFumigationRowMapper());
	}

	class LbFumigationRowMapper implements RowMapper<LogMCLbFumigationInfo> {

		@Override
		public LogMCLbFumigationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCLbFumigationInfo info = new LogMCLbFumigationInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCheckedBy((UUID) rs.getObject("checked_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCheckedByName(rs.getString("checked_by_name"));
			info.setLocationId((UUID) rs.getObject("location_id"));
			info.setLocationName(rs.getString("location_name"));
			info.setRoomId((UUID) rs.getObject("room_id"));
			info.setRoomName(rs.getString("room_name"));
			info.setCheckedStatus(rs.getString("checked_status"));
			info.setPotassiumPermanganate(rs.getString("potassium_permanganate"));
			info.setFormaldehyde(rs.getString("formaldehyde"));

			return info;
		}
	}

	public void saveLbFumigation(LogMCLbFumigationInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		// amountUsedWater totalAmountPreparation amountUsedDisinfectant disinfectantId

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_lb_fumigation_infos ( room_id,location_id, "
							+ "potassium_permanganate,formaldehyde, "
							+ "record_date, start_time, end_time,  operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getRoomId(), info.getLocationId(), info.getPotassiumPermanganate(), info.getFormaldehyde(),
					date, info.getStartTime(), info.getEndTime(), user.getId(), info.getRemarks(), user.getCompanyId(),
					user.getId(), time);
		} else {
			jdbcTemplate.update("UPDATE log_mc_lb_fumigation_infos SET  room_id =?,"
					+ "location_id =?,potassium_permanganate =?,formaldehyde =?, " + " start_time =?,end_time =?,"
					+ "remarks = ?, updated_by = ?, updated_at = ? where id = ?", info.getRoomId(),
					info.getLocationId(), info.getPotassiumPermanganate(), info.getFormaldehyde(), info.getStartTime(),
					info.getEndTime(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	// ========================== Start LB REFERENCE
	// CULTURE============================================================================
	public List<LogMCLbReferenceCultureInfo> lbReferenceCultureRowMapperInfos(String checkedStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, record_date, start_time,end_time, operate_by, checked_by, remarks,	checked_status,	"
						+ "expiry_date,batch_no,certificate_available,verification_date,atcc_no,"
						+ " supplier_id, (select source_name from lims_manufacturer_infos where id=a.supplier_id) supplier_name, "
						+ " culture_id, (select culture_item_name from lims_culture_item_infos where id=a.culture_id) culture_name, "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(checked_by) checked_by_name	 from log_mc_lb_reference_culture_infos a where "
						+ " checked_status="
						+ ((checkedStatus != null && !checkedStatus.isEmpty()) ? "'" + checkedStatus + "'"
								: "checked_status")
						+ " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new LbReferenceCultureRowMapper());
	}

	class LbReferenceCultureRowMapper implements RowMapper<LogMCLbReferenceCultureInfo> {

		@Override
		public LogMCLbReferenceCultureInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCLbReferenceCultureInfo info = new LogMCLbReferenceCultureInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCheckedBy((UUID) rs.getObject("checked_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCheckedByName(rs.getString("checked_by_name"));
			info.setSupplierId((UUID) rs.getObject("supplier_id"));
			info.setSupplierName(rs.getString("supplier_name"));
			info.setCultureId((UUID) rs.getObject("culture_id"));
			info.setCultureName(rs.getString("culture_name"));
			info.setAtccNo(rs.getString("atcc_no"));
			info.setCheckedStatus(rs.getString("checked_status"));
			info.setBatchNo(rs.getString("batch_no"));
			info.setCertificateAvailable(rs.getString("certificate_available"));
			info.setExpiryDate(rs.getDate("expiry_date"));
			info.setVerificationDate(rs.getDate("verification_date"));

			return info;
		}
	}

	public void saveLbReferenceCulture(LogMCLbReferenceCultureInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_lb_reference_culture_infos ( supplier_id,culture_id,expiry_date, "
							+ "batch_no,certificate_available,verification_date, atcc_no,"
							+ " record_date, start_time, end_time,  operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getSupplierId(), info.getCultureId(), info.getExpiryDate(), info.getBatchNo(),
					info.getCertificateAvailable(), info.getVerificationDate(), info.getAtccNo(), date,
					info.getStartTime(), info.getEndTime(), user.getId(), info.getRemarks(), user.getCompanyId(),
					user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_lb_reference_culture_infos SET  supplier_id =?, culture_id =?,"
							+ "expiry_date =?,batch_no =?,certificate_available =?, "
							+ " verification_date =?, atcc_no =?, start_time =?,end_time =?,"
							+ "remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getSupplierId(), info.getCultureId(), info.getExpiryDate(), info.getBatchNo(),
					info.getCertificateAvailable(), info.getVerificationDate(), info.getAtccNo(), info.getStartTime(),
					info.getEndTime(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	// =========================LB STRAIN==================================
	// ========================== Start LB REFERENCE
	// CULTURE============================================================================
	public List<LogMCLbTestStrainInfo> lbTestStrainRowMapperInfos(String checkedStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, record_date, start_time,end_time, operate_by, checked_by, remarks,	checked_status,	"
						+ " total_suspension_volue,result_p1,result_p2,average_result,storage_condition, atcc_no,"
						+ " culture_id, (select culture_item_name from lims_culture_item_infos where id=a.culture_id) culture_name, "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(checked_by) checked_by_name	 from log_mc_lb_test_strain_infos a where "
						+ " checked_status="
						+ ((checkedStatus != null && !checkedStatus.isEmpty()) ? "'" + checkedStatus + "'"
								: "checked_status")
						+ " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new LbTestStrainRowMapper());
	}

	class LbTestStrainRowMapper implements RowMapper<LogMCLbTestStrainInfo> {

		@Override
		public LogMCLbTestStrainInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCLbTestStrainInfo info = new LogMCLbTestStrainInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCheckedBy((UUID) rs.getObject("checked_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCheckedByName(rs.getString("checked_by_name"));
			info.setTotalSuspensionVolue(rs.getString("total_suspension_volue"));
			info.setCultureId((UUID) rs.getObject("culture_id"));
			info.setCultureName(rs.getString("culture_name"));
			info.setAtccNo(rs.getString("atcc_no"));
			info.setCheckedStatus(rs.getString("checked_status"));
			info.setResultP1(rs.getString("result_p1"));
			info.setResultP2(rs.getString("result_p2"));
			info.setAverageResult(rs.getString("average_result"));
			info.setStorageCondition(rs.getString("storage_condition"));
			return info;
		}
	}

	public void saveLbTestStrain(LogMCLbTestStrainInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		if (info.getId() == null) {// total_suspension_volue,result_p1,result_p2,average_result,storage_condition,
			jdbcTemplate.update(
					"INSERT INTO log_mc_lb_test_strain_infos ( total_suspension_volue,culture_id,result_p1, "
							+ "result_p2,average_result,storage_condition, atcc_no,"
							+ " record_date, start_time, end_time,  operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getTotalSuspensionVolue(), info.getCultureId(), info.getResultP1(), info.getResultP2(),
					info.getAverageResult(), info.getStorageCondition(), info.getAtccNo(), date, info.getStartTime(),
					info.getEndTime(), user.getId(), info.getRemarks(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_lb_test_strain_infos SET  total_suspension_volue =?, culture_id =?,"
							+ "result_p1 =?,result_p2 =?,average_result =?, "
							+ " storage_condition =?, atcc_no =?, start_time =?,end_time =?,"
							+ "remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getTotalSuspensionVolue(), info.getCultureId(), info.getResultP1(), info.getResultP2(),
					info.getAverageResult(), info.getStorageCondition(), info.getAtccNo(), info.getStartTime(),
					info.getEndTime(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	// water Sampling
	public List<LogMcWaterSamplingInfo> findLogWaterSamplingInfo(String eqipId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, sample_id, remarks, fnc_user_nm (dispensed_by) dispensed_by, fnc_user_nm (verify_by) verify_by,verified_at,is_verify, record_date,record_time, "
						+ " sample_id,location_id, water_type_id, "
						+ " (SELECT location_name FROM lims_location_setup_infos b WHERE b.id=a.location_id) location_name ,"
						+ " (SELECT type_name FROM lims_water_type_infos b WHERE b.id=a.water_type_id) water_type_Name "
						+ " from lims_mc_log_water_sampling_infos a   "
						+ " where is_verify="+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "is_verify")+ " "
						+ " and  company_id = ? ORDER BY is_verify, record_date desc",
				new Object[] { user.getCompanyId() }, new LogMcWaterSamplingRowMapper());
	}

	class LogMcWaterSamplingRowMapper implements RowMapper<LogMcWaterSamplingInfo> {

		@Override
		public LogMcWaterSamplingInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMcWaterSamplingInfo info = new LogMcWaterSamplingInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRemarks(rs.getString("remarks"));
			info.setDispensedByName(rs.getString("dispensed_by"));
			info.setVerifyName(rs.getString("verify_by"));
			info.setVerifiedAt(rs.getTimestamp("verified_at"));
			info.setIsVerify(rs.getString("is_verify"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setLocationId((UUID) rs.getObject("location_id"));
			info.setLocationName(rs.getString("location_name"));
			info.setWaterTypeId((UUID) rs.getObject("water_type_id"));
			info.setWaterTypeName(rs.getString("water_type_Name"));
			info.setSampleId(rs.getString("sample_id"));

			return info;
		}

	}

	public void saveWaterSampling(LogMcWaterSamplingInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm aa");
		String recordTime = formatterTime.format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_mc_log_water_sampling_infos ( location_id,water_type_id, sample_id, "
							+ " dispensed_by, remarks, company_id, created_by, created_at, record_date,record_time) "
							+ " VALUES (?,?,?,?,?,?,?,?,?,?)",
					info.getLocationId(), info.getWaterTypeId(), info.getSampleId(), user.getId(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time, date, recordTime);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_mc_log_water_sampling_infos SET location_id =?, water_type_id =?, sample_id=?, "
							+ "   dispensed_by =?, remarks =?, updated_by = ?, updated_at = ? where id = ?",
					info.getLocationId(), info.getWaterTypeId(), info.getSampleId(), user.getId(), info.getRemarks(),
					user.getId(), time, info.getId());
		}
	}

	public void saveMediumReagentInfo(LogMcMediumReagentInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_medium_reagent_infos (reagent_id, batch_no,batch_seq_no, batch_size, prep_date, exp_date, company_id, created_by, created_at) "
							+ " VALUES (?,?,?,?,?,?,?,?,?)",
					info.getReagentId(), info.getBatchNo(), info.getBatchSeqNo(), info.getBatchSize(),
					info.getPrepDate(), info.getExpDate(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_medium_reagent_infos SET   batch_size=?, prep_date=?, exp_date=?, updated_by = ?, updated_at = ? where id = ?",
					info.getBatchSize(), info.getPrepDate(), info.getExpDate(), user.getId(), time, info.getId());
		}
	}

	public void saveWeightingMstInfos(LogMcWeightingMstInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_weighting_mst_infos (preparation_id, balacne_id, company_id, created_by, created_at) "
							+ " VALUES (?,?,?,?,?)",
					info.getPreparationId(), info.getBalanceId(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_weighting_mst_infos SET   balacne_id=?,updated_by = ?, updated_at = ? where id = ?",
					info.getBalanceId(), user.getId(), time, info.getId());
		}
		jdbcTemplate.update(
				"UPDATE  log_mc_medium_reagent_infos  SET  is_weight =?  where id = ?", 'Y', info.getPreparationId());
	}
	
	public void saveWeightingChdInfos(LogMcWeightingChdInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
			jdbcTemplate.update(
					"INSERT INTO log_mc_weighting_chd_infos (preparation_id, code_no, material_id, batch_no, exp_date, net_weight, unit_id, company_id, created_by, created_at) "
							+ " VALUES (?,?,?,?,?,?,?,?,?,?)",
					info.getPreparationId(), info.getCodeNo(),info.getMaterialId(),info.getBatchNo(),info.getExpDate(),info.getNetWeight(),info.getUnitId(), user.getCompanyId(), user.getId(), time);
		
	}

	public void verifyWaterSamplingInfo(LogMcWaterSamplingInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_mc_log_water_sampling_infos SET  is_verify =?, remarks =?, verify_by = ?, verified_at = ? where id = ?",
				'Y', info.getRemarks(), user.getId(), time, info.getId());

	}

	//////////////////////
	public List<LogMcMediumReagentInfo> findMediumReagentInfos(String isWeight) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		
		try{
		return jdbcTemplate.query(
				" SELECT a.id, a.reagent_id, a.batch_no, a.batch_size, a.prep_date, a.exp_date,b.reagent_name, a.is_weight "
				+ " from log_mc_medium_reagent_infos a "
				+ " inner join lims_medium_reagent_infos b  "
				+ " on a.reagent_id = b.id   "
				+ " where  is_weight= "+ ((isWeight != null && !isWeight.equals(""))  ? "'" + isWeight + "'" : "is_weight") + " "
				+ " and a.company_id = ?",
				new Object[] { user.getCompanyId() }, new LogMcMediumReagentRowMapper());
		
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			throw ex;
	    }
	}

	class LogMcMediumReagentRowMapper implements RowMapper<LogMcMediumReagentInfo> {

		@Override
		public LogMcMediumReagentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMcMediumReagentInfo info = new LogMcMediumReagentInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setReagentId((UUID) rs.getObject("reagent_id"));
			info.setReagentName(rs.getString("reagent_name"));
			info.setBatchNo(rs.getString("batch_no"));
			info.setBatchSize(rs.getString("batch_size"));
			info.setPrepDate(rs.getDate("prep_date"));
			info.setExpDate(rs.getDate("exp_date"));
			info.setIsWeight(rs.getString("is_weight"));

			return info;
		}

	}

//==========================================Temp Humidity Pressure==========================
	public List<LogMCTempHumPressRecordInfo> getTempHumPressRecordInfos(String eveningStatus, String cleanedStatus,
			String checkedStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT a.id, morning_time, morning_temp_bef_cor,morning_temp_aft_cor,morning_hum_bef_cor,morning_hum_aft_cor,morning_pressure,"
						+ " evening_time, evening_temp_bef_cor,evening_temp_aft_cor,evening_hum_bef_cor,evening_hum_aft_cor,evening_pressure, "
						+ " done_by, record_date, record_time, remarks,"
						+ " evening_status, cleaned_status, checked_status, "
						+ " fnc_user_nm(done_by) doneByName,fnc_user_nm(evening_done_by) eveningDoneByName ,"
						+ " fnc_user_nm(cleaned_by) cleaned_by_name , " + " fnc_user_nm(checked_by) checked_by_name  "
						// + " from log_mc_temp_hum_press_record_infos a JOIN lims_incubator_setup_infos
						// st ON a.equipment_id=st.equipment_id "
						+ " from log_mc_temp_hum_press_record_infos a  " + " where evening_status="
						+ ((eveningStatus != null && !eveningStatus.isEmpty()) ? "'" + eveningStatus + "'"
								: "evening_status")
						+ " and cleaned_status="
						+ ((cleanedStatus != null && !cleanedStatus.isEmpty()) ? "'" + cleanedStatus + "'"
								: "cleaned_status")
						+ " and checked_status="
						+ ((checkedStatus != null && !checkedStatus.isEmpty()) ? "'" + checkedStatus + "'"
								: "checked_status")
						+ " and  a.company_id = ? ORDER BY  record_date desc",
				new Object[] { user.getCompanyId() }, new TempHumPressRecordInfoRowMapper());
	}

	class TempHumPressRecordInfoRowMapper implements RowMapper<LogMCTempHumPressRecordInfo> {

		@Override
		public LogMCTempHumPressRecordInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCTempHumPressRecordInfo info = new LogMCTempHumPressRecordInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setRemarks(rs.getString("remarks"));
			info.setDoneBy((UUID) rs.getObject("done_by"));
			info.setDoneByName(rs.getString("doneByName"));
			info.setMorningTime(rs.getString("morning_time"));
			info.setEveningTime(rs.getString("evening_time"));
			info.setEveningDoneByName(rs.getString("eveningDoneByName"));
			info.setEveningStatus(rs.getString("evening_status"));
			info.setCleanedStatus(rs.getString("cleaned_status"));
			info.setCheckedStatus(rs.getString("checked_status"));
			info.setCleanedByName(rs.getString("cleaned_by_name"));
			info.setCheckedByName(rs.getString("checked_by_name"));

			info.setMorningTempBefCor(rs.getString("morning_temp_bef_cor"));
			info.setMorningTempAftCor(rs.getString("morning_temp_aft_cor"));
			info.setMorningHumBefCor(rs.getString("morning_hum_bef_cor"));
			info.setMorningHumAftCor(rs.getString("morning_hum_aft_cor"));
			info.setMorningPressure(rs.getString("morning_pressure"));
			info.setEveningTempBefCor(rs.getString("evening_temp_bef_cor"));
			info.setEveningTempAftCor(rs.getString("evening_temp_aft_cor"));
			info.setEveningHumBefCor(rs.getString("evening_hum_bef_cor"));
			info.setEveningHumAftCor(rs.getString("evening_hum_aft_cor"));
			info.setEveningPressure(rs.getString("evening_pressure"));

			return info;
		}
	}

	public void saveTempHumPressMorningRecord(LogMCTempHumPressRecordInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		System.out.println("===============================================================================");
		User user = userService.getCurrentUser();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_temp_hum_press_record_infos (morning_time, morning_temp_bef_cor, morning_temp_aft_cor, "
							+ " morning_hum_bef_cor,morning_hum_aft_cor,morning_pressure,"
							+ " done_by, record_date, record_time,  company_id) VALUES (?,?,?,?,?,?,?,?,?,?) ",
					info.getMorningTime(), info.getMorningTempBefCor(), info.getMorningTempAftCor(),
					info.getMorningHumBefCor(), info.getMorningHumAftCor(), info.getMorningPressure(), user.getId(),
					date, recordTime, user.getCompanyId());
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_temp_hum_press_record_infos SET morning_temp_bef_cor =?, morning_time =?, morning_temp_aft_cor =?,"
							+ " morning_hum_bef_cor =?, morning_hum_aft_cor =?, morning_pressure =?, "
							+ " updated_at = ?, updated_by = ? where id = ?",
					info.getMorningTempBefCor(), info.getMorningTime(), info.getMorningTempAftCor(),
					info.getMorningHumBefCor(), info.getMorningHumAftCor(), info.getMorningPressure(), date,
					user.getId(), info.getId());
		}
	}

	public void saveTempHumPressEveningRecord(LogMCTempHumPressRecordInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		jdbcTemplate.update(
				"UPDATE log_mc_temp_hum_press_record_infos SET evening_temp_bef_cor =?, evening_time =?, evening_temp_aft_cor =?,"
						+ " evening_hum_bef_cor =?, evening_hum_aft_cor =?, evening_pressure=?, evening_status=?,"
						+ "remarks=?,  updated_at = ?, evening_done_by = ? where id = ? ",
				info.getEveningTempBefCor(), info.getEveningTime(), info.getEveningTempAftCor(),
				info.getEveningHumBefCor(), info.getEveningHumAftCor(), info.getEveningPressure(), 'Y',
				info.getRemarks(), date, user.getId(), info.getId());

	}

	// =======================Media Reagent
	// Materials===================================================
	public List<LogMCMediaReagentMaterialsInfo> findLogMediaReagentMaterials(String issuedStatus, String openingStatus,
			String finishStatus,String checkedStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate
				.query(" SELECT id, code_no, materials_id,specification,batch_no,exp_date,mfg_date,supplier_id, finish_status,"
						+ " opening_status, country_origin_id,quantity,unit_id,fnc_unit_nm(unit_id) unit_name, checked_by, checked_status,issued_status,  "

						// + " (SELECT material_name FROM lims_material_infos b WHERE
						// b.id=a.materials_id) materials_name ,"
						+ " (SELECT culture_item_name FROM lims_culture_item_infos b WHERE b.id=a.materials_id) materials_name ,"

						+ " fnc_manufac_name(supplier_id) supplier_name ,"
						+ " (select country_name  from lims_country_infos where id=a.country_origin_id) country_name ,"
						+ " remarks, fnc_user_nm (dispensed_by) dispensed_by, " + " record_date,record_time "
						+ " from log_mc_media_reagent_materials_infos a " + " where " + " opening_status="
						+ ((openingStatus != null && !openingStatus.isEmpty()) ? "'" + openingStatus + "'"
								: "opening_status")
						+ " and issued_status="
						+ ((issuedStatus != null && !issuedStatus.isEmpty()) ? "'" + issuedStatus + "'"
								: "issued_status")
						+ " and finish_status="
						+ ((finishStatus != null && !finishStatus.isEmpty()) ? "'" + finishStatus + "'"
								: "finish_status")
						+ " and checked_status="
						+ ((checkedStatus != null && !checkedStatus.isEmpty()) ? "'" + checkedStatus + "'"
								: "checked_status")
						+ " and  company_id = ? ORDER BY  record_date desc",//,checked_status
						new Object[] { user.getCompanyId() }, new LogMediaReagentMaterials());
	}

	class LogMediaReagentMaterials implements RowMapper<LogMCMediaReagentMaterialsInfo> {

		@Override
		public LogMCMediaReagentMaterialsInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMCMediaReagentMaterialsInfo info = new LogMCMediaReagentMaterialsInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRemarks(rs.getString("remarks"));
			info.setDispensedByName(rs.getString("dispensed_by"));
			info.setCodeNo(rs.getString("code_no"));
			info.setMaterialsId((UUID) rs.getObject("materials_id"));
			info.setMaterialsName(rs.getString("materials_name"));
			info.setSpecification(rs.getString("specification"));
			info.setBatchNo(rs.getString("batch_no"));
			info.setExpDate(rs.getDate("exp_date"));
			info.setMfgDate(rs.getDate("mfg_date"));
			info.setSupplierId((UUID) rs.getObject("supplier_id"));
			info.setSupplierName(rs.getString("supplier_name"));
			info.setOpeningStatus(rs.getString("opening_status"));
			info.setIssuedStatus(rs.getString("issued_status"));
			info.setCheckedStatus(rs.getString("checked_status"));
			info.setFinishedStatus(rs.getString("finish_status"));
			
			info.setCountryOriginId((UUID) rs.getObject("country_origin_id"));
			info.setCountryOrigenName(rs.getString("country_name"));
			info.setQuantity(rs.getString("quantity"));
			info.setUnitId((UUID) rs.getObject("unit_id"));
			info.setUnitName(rs.getString("unit_name"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));

			return info;
		}
	}

	public void saveMCMediaReagentMaterials(LogMCMediaReagentMaterialsInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm aa");
		String recordTime = formatterTime.format(date);
		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO log_mc_media_reagent_materials_infos (code_no,materials_id, specification, batch_no,"
							+ " exp_date, mfg_date,supplier_id,country_origin_id,quantity,unit_id,"
							+ " dispensed_by, remarks, company_id, created_by, created_at, record_date,record_time) "
							+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					info.getCodeNo(), info.getMaterialsId(), info.getSpecification(), info.getBatchNo(),
					info.getExpDate(), info.getMfgDate(), info.getSupplierId(), info.getCountryOriginId(),
					info.getQuantity(), info.getUnitId(),

					user.getId(), info.getRemarks(), user.getCompanyId(), user.getId(), time, date, recordTime);
		} else {
			jdbcTemplate.update(
					"UPDATE log_mc_media_reagent_materials_infos SET  code_no =?, materials_id =?, specification=?, batch_no=?,exp_date=?,mfg_date=?,"
							+ " supplier_id=?,country_origin_id=?,quantity=?, unit_id=? ,"
							+ "    remarks =?, updated_by = ?, updated_at = ? where id = ?",
					info.getCodeNo(), info.getMaterialsId(), info.getSpecification(), info.getBatchNo(),
					info.getExpDate(), info.getMfgDate(), info.getSupplierId(), info.getCountryOriginId(),
					info.getQuantity(), info.getUnitId(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}
}
