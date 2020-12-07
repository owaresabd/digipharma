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

import com.pms.model.EquipmentInfo;
import com.pms.model.IncubatorSetupInfo;
import com.pms.model.RefrigeratorTempChemiSetupInfo;
import com.pms.model.TempAndHumiditySetupInfo;
import com.pms.model.User;
import com.pms.repository.EquipmentRepository.EquipmentInfoRowMapper;
import com.pms.service.IUserService;

@Repository
@Transactional
public class AllSetupRepository {

	@Autowired
	private DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private IUserService userService;
	
	public List<TempAndHumiditySetupInfo> getTempHmdtyStupInfosByStatus(String status, String equipmentId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT id, correction_val_fr_humidity, correction_val_fr_temp, created_by, department_id, equipment_id, locaton_id, status, update_by, create_at, update_at,"
				+ " fnc_dept_nm(department_id) deptByName, fnc_location_name(locaton_id) locationByName, fnc_user_nm(created_by) createByName, fnc_user_nm(update_by) updateByName, fnc_equipment_name(equipment_id) equipmentByName "
				+ " FROM lims_temp_humidity_setup_infos WHERE equipment_id =" + ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id") +" and status ='"+ ( (status == "Y") ? status : "N") +"' and company_id = ? ",
				new Object[] { user.getCompanyId() }, new tempHumiditySetupInfosRowMapper());
	}
	public List<IncubatorSetupInfo> getIncubetarSetupInfosByStatus(String status, String equipmentId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT id, temperature, tolerance, created_by, equipment_id, status, update_by, create_at, update_at,"
				+ "  fnc_user_nm(created_by) createByName, fnc_user_nm(update_by) updateByName, fnc_equipment_name(equipment_id) equipmentByName "
				+ " FROM lims_incubator_setup_infos WHERE equipment_id =" + ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id") +" and status ='"+ ( (status == "Y") ? status : "N") +"' and company_id = ? ",
				new Object[] { user.getCompanyId() }, new incubatorSetupInfosRowMapper());
	}
	public List<IncubatorSetupInfo> getRefrigeratorSetupInfosByStatus(String status, String equipmentId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT id, temperature, tolerance, created_by, equipment_id, status, update_by, create_at, update_at,"
				+ "  fnc_user_nm(created_by) createByName, fnc_user_nm(update_by) updateByName, fnc_equipment_name(equipment_id) equipmentByName "
				+ " FROM lims_refrigerator_setup_infos WHERE equipment_id =" + ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id") +" and status ='"+ ( (status == "Y") ? status : "N") +"' and company_id = ? ",
				new Object[] { user.getCompanyId() }, new refrigeratorSetupInfosRowMapper());
	}
	
	public TempAndHumiditySetupInfo getTempHmdtyStupInfosByStatusAndEquId(String status, String equipmentId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.queryForObject("SELECT  id, correction_val_fr_humidity, correction_val_fr_temp, created_by, department_id, equipment_id, locaton_id, status, update_by, create_at, update_at,"
				+ " fnc_dept_nm(department_id) deptByName, fnc_location_name(locaton_id) locationByName, fnc_user_nm(created_by) createByName, fnc_user_nm(update_by) updateByName, fnc_equipment_name(equipment_id) equipmentByName "
				+ " FROM lims_temp_humidity_setup_infos WHERE equipment_id =" + ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id") +" and status ='"+ ( (status == "Y") ? status : "N") +"' and company_id = ? ",
				new Object[] { user.getCompanyId() }, new tempHumiditySetupInfosRowMapper());
	}

	public List<TempAndHumiditySetupInfo> getTempHumiditySetupInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT  id, correction_val_fr_humidity, correction_val_fr_temp, created_by, department_id, equipment_id, locaton_id, status, update_by, create_at, update_at,"
				+ " fnc_dept_nm(department_id) deptByName, fnc_location_name(locaton_id) locationByName, fnc_user_nm(created_by) createByName, fnc_user_nm(update_by) updateByName, fnc_equipment_name(equipment_id) equipmentByName "
				+ " FROM lims_temp_humidity_setup_infos WHERE company_id = ? ",
				new Object[] { user.getCompanyId() }, new tempHumiditySetupInfosRowMapper());
	}
	public IncubatorSetupInfo incubatorSetupInfo(String status, String equipmentId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.queryForObject("SELECT id, temperature, tolerance, created_by, equipment_id, status, update_by, create_at, update_at,"
				+ "   fnc_user_nm(created_by) createByName, fnc_user_nm(update_by) updateByName, fnc_equipment_name(equipment_id) equipmentByName  "
				+ " FROM lims_incubator_setup_infos WHERE equipment_id =" + ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id") +" and status ='"+ ( (status == "Y") ? status : "N") +"' and company_id = ? ",
				new Object[] { user.getCompanyId() }, new incubatorSetupInfosRowMapper());
	}
	public List<IncubatorSetupInfo> getIncubatorSetupInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT id, temperature, tolerance, created_by, equipment_id, status, update_by, create_at, update_at,"
				+ "  fnc_user_nm(created_by) createByName, fnc_user_nm(update_by) updateByName, fnc_equipment_name(equipment_id) equipmentByName "
				+ " FROM lims_incubator_setup_infos WHERE company_id = ? ",
				new Object[] { user.getCompanyId() }, new incubatorSetupInfosRowMapper());
	}
	public List<IncubatorSetupInfo> getRefrigeratorSetupInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT id, temperature, tolerance, created_by, equipment_id, status, update_by, create_at, update_at,"
				+ "  fnc_user_nm(created_by) createByName, fnc_user_nm(update_by) updateByName, fnc_equipment_name(equipment_id) equipmentByName "
				+ " FROM lims_refrigerator_setup_infos WHERE company_id = ? ",
				new Object[] { user.getCompanyId() }, new incubatorSetupInfosRowMapper());
	}
	public List<RefrigeratorTempChemiSetupInfo> getRefrigeratorTempChemiSetupInfos(String status, String equipmentId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT id, temperature,  created_by, equipment_id, status, update_by, create_at, update_at,"
				+ "  fnc_user_nm(created_by) createByName, fnc_user_nm(update_by) updateByName, fnc_equipment_name(equipment_id) equipmentByName "
				+ " FROM lims_refrigerator_temp_chemi_setup_infos"
				+ " WHERE equipment_id =" + ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id") +" and"
				+ " status="+ ((status != null && !status.isEmpty()) ? "'" + status + "'"	: "status")+" and  "
				+ "company_id = ? ",
				new Object[] { user.getCompanyId() }, new refrigeratorTempChemiSetupInfosRowMapper());
	}
	public RefrigeratorTempChemiSetupInfo getRefrigeratorTempChemiSetupInfo(String status, String equipmentId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.queryForObject("SELECT id, temperature,  created_by, equipment_id, status, update_by, create_at, update_at,"
				+ "  fnc_user_nm(created_by) createByName, fnc_user_nm(update_by) updateByName, fnc_equipment_name(equipment_id) equipmentByName "
				+ " FROM lims_refrigerator_temp_chemi_setup_infos"
				+ " WHERE equipment_id =" + ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id") +" and"
				+ " status="+ ((status != null && !status.isEmpty()) ? "'" + status + "'"	: "status")+" and  "
				+ "company_id = ? ",
				new Object[] { user.getCompanyId() }, new refrigeratorTempChemiSetupInfosRowMapper()); 
	}
	public List<EquipmentInfo> findEquipments(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select id, equipment_name, equipment_type, model_no, capacity, source_manufacturer, local_agent_name, curr_location,"
						+ "  lc_model_no, serial_no, ms_model_no,ms_serial_no, manufacturing_date, pump_model_no, detector_model_no,"
						+ "  software_name, software_version_no, software_firmware_no, electric_power, nitrogen_consumption,"
						+ "  calibration_date, next_calibration_date, install_qualific_date, operation_qualific_date, "
						+ " performance_qualific_date,quali_by, equipment_id, brand, accept_criteria, calibration_interval, "
						+ "	evidence_verification, adjustment, reference_material, result_calibration "
						+ " from lims_equipment_infos a "
						+ " where company_id = ? and equipment_type <>'0' "
						+ " and id not in (SELECT equipment_id  FROM lims_temp_humidity_setup_infos WHERE equipment_id = a.id) "
						+ " order by equipment_name",
				new Object[] { user.getCompanyId() }, new EquipmentInfoRowMapper());
	}
	public void saveTempHumiditySetupInfo(TempAndHumiditySetupInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_temp_humidity_setup_infos (correction_val_fr_humidity, correction_val_fr_temp, created_by, department_id, equipment_id, locaton_id, status, create_at, company_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)",
						info.getCorrectionValFrHumidity(), info.getCorrectionValFrTemp(), user.getId(), info.getDepartmentId(), info.getEquipmentId(), info.getLocationId(), info.getStatus(), time, user.getCompanyId());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_temp_humidity_setup_infos SET correction_val_fr_humidity =?, correction_val_fr_temp =?, department_id =?, equipment_id =?, locaton_id=?, status = ?, update_by = ?, update_at = ? where id = ?",
					info.getCorrectionValFrHumidity(), info.getCorrectionValFrTemp(), info.getDepartmentId(), info.getEquipmentId(), info.getLocationId(), info.getStatus(), user.getId(), time, info.getId());
				}
	}
	public void saveIncubatorTempSetupInfo(IncubatorSetupInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_incubator_setup_infos (temperature, tolerance, created_by,  equipment_id, status, create_at, company_id) VALUES(?, ?, ?, ?, ?, ?, ?)",
						info.getTemperature(), info.getTolerance(), user.getId(), info.getEquipmentId(), info.getStatus(), time, user.getCompanyId());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_incubator_setup_infos SET temperature =?, tolerance =?, equipment_id =?, status = ?, update_by = ?, update_at = ? where id = ?",
					info.getTemperature(), info.getTolerance(), info.getEquipmentId(),  info.getStatus(), user.getId(), time, info.getId());
				}
	}
	public void saveRefrigeratorTempSetupInfo(IncubatorSetupInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_refrigerator_setup_infos (temperature, tolerance, created_by,  equipment_id, status, create_at, company_id) VALUES(?, ?, ?, ?, ?, ?, ?)",
						info.getTemperature(), info.getTolerance(), user.getId(), info.getEquipmentId(), info.getStatus(), time, user.getCompanyId());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_refrigerator_setup_infos SET temperature =?, tolerance =?, equipment_id =?, status = ?, update_by = ?, update_at = ? where id = ?",
					info.getTemperature(), info.getTolerance(), info.getEquipmentId(),  info.getStatus(), user.getId(), time, info.getId());
				}
	}
	public void saveRefrigeratorTempChemiSetupInfo(RefrigeratorTempChemiSetupInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_refrigerator_temp_chemi_setup_infos (temperature,  created_by,  equipment_id, status, create_at, company_id) VALUES(?, ?, ?, ?, ?, ?)",
						info.getTemperature(), user.getId(), info.getEquipmentId(), info.getStatus(), time, user.getCompanyId());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_refrigerator_temp_chemi_setup_infos SET temperature =?,  equipment_id =?, status = ?, update_by = ?, update_at = ? where id = ?",
					info.getTemperature(), info.getEquipmentId(),  info.getStatus(), user.getId(), time, info.getId());
				}
	}
	
	class tempHumiditySetupInfosRowMapper implements RowMapper<TempAndHumiditySetupInfo> {
		@Override
		public TempAndHumiditySetupInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			TempAndHumiditySetupInfo info = new TempAndHumiditySetupInfo();
			
			info.setId((UUID) rs.getObject("id"));
			info.setCorrectionValFrHumidity(rs.getString("correction_val_fr_humidity"));
			info.setCorrectionValFrTemp(rs.getString("correction_val_fr_temp"));
			info.setCreateBy((UUID) rs.getObject("created_by"));
			info.setDepartmentId((UUID) rs.getObject("department_id"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setLocationId((UUID) rs.getObject("locaton_id"));
			info.setUpdateAt(rs.getTimestamp("update_at"));
			info.setCreateAt(rs.getTimestamp("create_at"));
			info.setDepartmentByName(rs.getString("deptByName"));
			info.setLocationByName(rs.getString("locationByName"));
			info.setCreateByName(rs.getString("createByName"));
			info.setUpdateByName(rs.getString("updateByName"));
			info.setEquipmentByName(rs.getString("equipmentByName"));//equipmentByName
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	class incubatorSetupInfosRowMapper implements RowMapper<IncubatorSetupInfo> {
		@Override
		public IncubatorSetupInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			IncubatorSetupInfo info = new IncubatorSetupInfo();
			
			info.setId((UUID) rs.getObject("id"));
			info.setTemperature(rs.getString("temperature"));
			info.setTolerance(rs.getString("tolerance"));
			info.setCreateBy((UUID) rs.getObject("created_by"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setUpdateAt(rs.getTimestamp("update_at"));
			info.setCreateAt(rs.getTimestamp("create_at"));
			info.setCreateByName(rs.getString("createByName"));
			info.setUpdateByName(rs.getString("updateByName"));
			info.setEquipmentByName(rs.getString("equipmentByName"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	class refrigeratorTempChemiSetupInfosRowMapper implements RowMapper<RefrigeratorTempChemiSetupInfo> {
		@Override
		public RefrigeratorTempChemiSetupInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			RefrigeratorTempChemiSetupInfo info = new RefrigeratorTempChemiSetupInfo();
			
			info.setId((UUID) rs.getObject("id"));
			info.setTemperature(rs.getString("temperature"));
			info.setCreateBy((UUID) rs.getObject("created_by"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setUpdateAt(rs.getTimestamp("update_at"));
			info.setCreateAt(rs.getTimestamp("create_at"));
			info.setCreateByName(rs.getString("createByName"));
			info.setUpdateByName(rs.getString("updateByName"));
			info.setEquipmentByName(rs.getString("equipmentByName"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	
	class refrigeratorSetupInfosRowMapper implements RowMapper<IncubatorSetupInfo> {
		@Override
		public IncubatorSetupInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			IncubatorSetupInfo info = new IncubatorSetupInfo();
			
			info.setId((UUID) rs.getObject("id"));
			info.setTemperature(rs.getString("temperature"));
			info.setTolerance(rs.getString("tolerance"));
			info.setCreateBy((UUID) rs.getObject("created_by"));
			//info.setDepartmentId((UUID) rs.getObject("department_id"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
		//	info.setLocationId((UUID) rs.getObject("locaton_id"));
			info.setUpdateAt(rs.getTimestamp("update_at"));
			info.setCreateAt(rs.getTimestamp("create_at"));
		//	info.setDepartmentByName(rs.getString("deptByName"));
			//info.setLocationByName(rs.getString("locationByName"));
			info.setCreateByName(rs.getString("createByName"));
			info.setUpdateByName(rs.getString("updateByName"));
			info.setEquipmentByName(rs.getString("equipmentByName"));
			info.setStatus(rs.getString("status"));
			return info;
		}
	}
	class EquipmentInfoRowMapper implements RowMapper<EquipmentInfo> {

		@Override
		public EquipmentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			EquipmentInfo info = new EquipmentInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setEquipmentType(rs.getString("equipment_type"));
			info.setModelNo(rs.getString("model_no"));
			info.setCapacity(rs.getString("capacity"));
			info.setSourceManufac(rs.getString("source_manufacturer"));
			info.setLocalAgentName(rs.getString("local_agent_name"));
			info.setCurrLocation(rs.getString("curr_location"));
			info.setLcModelNo(rs.getString("lc_model_no"));
			info.setSerialNo(rs.getString("serial_no"));
			info.setMsModelNo(rs.getString("ms_model_no"));
			info.setMsSerialNo(rs.getString("ms_serial_no"));
			info.setManufacturingDate(rs.getDate("manufacturing_date"));
			info.setPumpModelNo(rs.getString("pump_model_no"));
			info.setDetectorModelNo(rs.getString("detector_model_no"));
			info.setSoftwareName(rs.getString("software_name"));
			info.setSoftwareVersionNo(rs.getString("software_version_no"));
			info.setSoftwareFirmwareNo(rs.getString("software_firmware_no"));
			info.setElectricPower(rs.getString("electric_power"));
			info.setNitrogenConsumption(rs.getString("nitrogen_consumption"));
			info.setCalibrationDate(rs.getDate("calibration_date"));
			info.setNextCalibrationDate(rs.getDate("next_calibration_date"));
			info.setInstallQualificDate(rs.getDate("install_qualific_date"));
			info.setOperationQualificDate(rs.getDate("operation_qualific_date"));
			info.setPerformanceQualificDate(rs.getDate("performance_qualific_date"));
			info.setQualiBy(rs.getString("quali_by"));
			info.setEquipmentId(rs.getString("equipment_id"));
			info.setBrand(rs.getString("brand"));
			info.setAcceptCriteria(rs.getString("accept_criteria"));
			info.setCalibrationInterval(rs.getString("calibration_interval"));
			info.setEvidenceVerification(rs.getString("evidence_verification"));
			info.setAdjustment(rs.getString("adjustment"));
			info.setReferenceMaterial(rs.getString("reference_material"));
			info.setResultCalibration(rs.getString("result_calibration"));

			return info;
		}
	}
	
}
