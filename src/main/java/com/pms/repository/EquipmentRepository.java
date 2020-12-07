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

import com.pms.model.CommonInfo;
import com.pms.model.EquipMaintenInfo;
import com.pms.model.EquipmentInfo;
import com.pms.model.FrequencyInfo;
import com.pms.model.LogBenchtopReciprocalShakerInfo;
import com.pms.model.LogColumnRcvDistInfo;
import com.pms.model.LogElectricOvenInfo;
import com.pms.model.LogNoteBookControlInfo;
import com.pms.model.LogRefrigeratorInfo;
import com.pms.model.LogSpectrophotometerInfo;
import com.pms.model.LogTempeHumidityRecordInfo;
import com.pms.model.LogVacuumPumpInfo;
import com.pms.model.LogWaterPurificationInfo;
import com.pms.model.User;
import com.pms.service.IUserService;


@Repository
@Transactional
public class EquipmentRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<EquipmentInfo> findAll(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select id, equipment_name, equipment_type, model_no, capacity, source_manufacturer, local_agent_name, curr_location,"
						+ "  lc_model_no, serial_no, ms_model_no,ms_serial_no, manufacturing_date, pump_model_no, detector_model_no,"
						+ "  software_name, software_version_no, software_firmware_no, electric_power, nitrogen_consumption,"
						+ "  calibration_date, next_calibration_date, install_qualific_date, operation_qualific_date, "
						+ " performance_qualific_date,quali_by, equipment_id, brand, accept_criteria, calibration_interval, "
						+ "	evidence_verification, adjustment, reference_material, result_calibration "
						+ " from lims_equipment_infos a where company_id = ? and equipment_type <>'0' "
						+ " and id not in (SELECT equipment_id  FROM lims_refrigerator_temp_chemi_setup_infos WHERE equipment_id = a.id) "
						+ " order by equipment_name",
				new Object[] { user.getCompanyId() }, new EquipmentInfoRowMapper());
	}

	public List<EquipmentInfo> equipmentType(String type) {
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
						+ " where company_id = ? "
						+ " and (equipment_type ='"+ type + "' OR equipment_type ='0') order by equipment_name",
				new Object[] { user.getCompanyId() }, new EquipmentInfoRowMapper());
	}
	public List<EquipmentInfo> equipmentForDistribution(String type,String deptCode) {
		
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		String query=" select id, equipment_name, equipment_type, model_no, capacity, source_manufacturer, local_agent_name, curr_location,"
				+ "  lc_model_no, serial_no, ms_model_no,ms_serial_no, manufacturing_date, pump_model_no, detector_model_no,"
				+ "  software_name, software_version_no, software_firmware_no, electric_power, nitrogen_consumption,"
				+ "  calibration_date, next_calibration_date, install_qualific_date, operation_qualific_date, "
				+ " performance_qualific_date,quali_by, equipment_id, brand, accept_criteria, calibration_interval, "
				+ "	evidence_verification, adjustment, reference_material, result_calibration "
				+ " from lims_equipment_infos a "
				+ " where company_id = '"+user.getCompanyId()+"' "
				+ " and equipment_type ='"+ type + "' "
				+ " and id in (SELECT equipment_id FROM lims_department_equipment_mapping_infos WHERE  company_id = a.company_id and department_id='"+deptCode+"' ) "
				+ " UNION ALL "
				+ " select id, equipment_name, equipment_type, model_no, capacity, source_manufacturer, local_agent_name, curr_location,"
				+ "  lc_model_no, serial_no, ms_model_no,ms_serial_no, manufacturing_date, pump_model_no, detector_model_no,"
				+ "  software_name, software_version_no, software_firmware_no, electric_power, nitrogen_consumption,"
				+ "  calibration_date, next_calibration_date, install_qualific_date, operation_qualific_date, "
				+ " performance_qualific_date,quali_by, equipment_id, brand, accept_criteria, calibration_interval, "
				+ "	evidence_verification, adjustment, reference_material, result_calibration "
				+ " from lims_equipment_infos  "
				+ " where company_id = '"+user.getCompanyId()+"' AND equipment_type ='0' "
				+ " order by equipment_name";
		System.out.println(query);
		return jdbcTemplate.query( 
				query, new EquipmentInfoRowMapper());
	}

	public List<EquipMaintenInfo> findMaintenanceScheduleDetails(String id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		
	    String sql = "SELECT  b.equipment_id, a.equipment_id AS equipment_name, a.equipment_name AS equip_full_name, a.curr_location,"
				+ " b.type_id, fnc_maintenance_type_nm(b.type_id) type_name, b.freq_type, b.freq_duration,"
				+ " (select string_agg(TO_CHAR(last_schedule, 'dd.MM.YYYY'), ', ') from lims_maintenance_detail_infos where equipment_id=a.id and type_id=b.type_id) date_val,"
				+ " (select count(*) from lims_maintenance_detail_infos where equipment_id=a.id and type_id=b.type_id) max_val,"
				+ " (select remarks from lims_maintenance_detail_infos where id=b.last_schedule_id and equipment_id=a.id and type_id=b.type_id) remarks"
				+ " FROM lims_equipment_infos a,"
				+ " lims_frequency_infos b"
				+ " WHERE a.id=b.equipment_id AND a.company_id = ? "
				+ " and  b.equipment_id=" + ((id != null && !id.isEmpty())? "'" + UUID.fromString(id) + "'" : " b.equipment_id") + " "
				+ " AND (CASE WHEN (b.freq_type='M' or b.freq_type='Y') THEN  b.type_id in (select type_id from lims_maintenance_detail_infos where equipment_id=a.id and type_id=b.type_id) else b.type_id=b.type_id  END) "
				+ "   ORDER BY equipment_name, b.freq_type ";
		
		System.out.println(sql);
		return jdbcTemplate.query(sql,new Object[] { user.getCompanyId() }, new RowMapper<EquipMaintenInfo>() {
			
			
			
			public EquipMaintenInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				EquipMaintenInfo info = new EquipMaintenInfo();
				info.setEquipmentId((UUID) rs.getObject("equipment_id"));
				info.setEquipmentName(rs.getString("equipment_name"));
				info.setEquipFullName(rs.getString("equip_full_name"));
				info.setCurrLocation(rs.getString("curr_location"));
				info.setTypeId((UUID) rs.getObject("type_id"));
				info.setTypeName(rs.getString("type_name"));
				info.setFreqType(rs.getString("freq_type"));
				info.setFreqDuration(rs.getString("freq_duration"));
				info.setMaxValue(rs.getInt("max_val"));
				info.setDateArray(rs.getString("date_val"));
				info.setRemarks(rs.getString("remarks"));
				return info;
			}
			
		
			
		});
	}
	
	public void saveEquipmentInfos(EquipmentInfo info, UUID idRandom) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_equipment_infos(id, equipment_name, equipment_type, model_no, capacity, source_manufacturer, local_agent_name,"
							+ " curr_location, lc_model_no, serial_no, ms_model_no,ms_serial_no, manufacturing_date, pump_model_no, detector_model_no,"
							+ "  software_name, software_version_no, software_firmware_no, electric_power, nitrogen_consumption, calibration_date, "
							+ " next_calibration_date, install_qualific_date, operation_qualific_date, performance_qualific_date, quali_by, equipment_id, brand, accept_criteria, "
							+ "	calibration_interval, evidence_verification, adjustment, reference_material, result_calibration,"
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					idRandom, info.getEquipmentName(), info.getEquipmentType(), info.getModelNo(), info.getCapacity(),
					info.getSourceManufac(), info.getLocalAgentName(), info.getCurrLocation(), info.getLcModelNo(),
					info.getSerialNo(), info.getMsModelNo(), info.getMsSerialNo(), info.getManufacturingDate(),
					info.getPumpModelNo(), info.getDetectorModelNo(), info.getSoftwareName(),
					info.getSoftwareVersionNo(), info.getSoftwareFirmwareNo(), info.getElectricPower(),
					info.getNitrogenConsumption(), info.getCalibrationDate(), info.getNextCalibrationDate(),
					info.getInstallQualificDate(), info.getOperationQualificDate(), info.getPerformanceQualificDate(),
					info.getQualiBy(), info.getEquipmentId(), info.getBrand(), info.getAcceptCriteria(),
					info.getCalibrationInterval(), info.getEvidenceVerification(), info.getAdjustment(),
					info.getReferenceMaterial(), info.getResultCalibration(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_equipment_infos SET equipment_name=?, equipment_type=?, model_no=?, capacity=?, source_manufacturer=?, local_agent_name=?,"
							+ " curr_location=?, lc_model_no=?, serial_no=?, ms_model_no=?,ms_serial_no=?, manufacturing_date=?, pump_model_no=?, detector_model_no=?,"
							+ " software_name=?, software_version_no=?, software_firmware_no=?, electric_power=?, nitrogen_consumption=?, calibration_date=?,"
							+ " next_calibration_date=?, install_qualific_date=?, operation_qualific_date=?, performance_qualific_date=?, quali_by=?, equipment_id=?, brand=?, "
							+ " accept_criteria=?, calibration_interval=?, evidence_verification=?, adjustment=?, reference_material=?, result_calibration=?, updated_by= ?, updated_at = ? where id = ?",
					info.getEquipmentName(), info.getEquipmentType(), info.getModelNo(), info.getCapacity(),
					info.getSourceManufac(), info.getLocalAgentName(), info.getCurrLocation(), info.getLcModelNo(),
					info.getSerialNo(), info.getMsModelNo(), info.getMsSerialNo(), info.getManufacturingDate(),
					info.getPumpModelNo(), info.getDetectorModelNo(), info.getSoftwareName(),
					info.getSoftwareVersionNo(), info.getSoftwareFirmwareNo(), info.getElectricPower(),
					info.getNitrogenConsumption(), info.getCalibrationDate(), info.getNextCalibrationDate(),
					info.getInstallQualificDate(), info.getOperationQualificDate(), info.getPerformanceQualificDate(),
					info.getQualiBy(), info.getEquipmentId(), info.getBrand(), info.getAcceptCriteria(),
					info.getCalibrationInterval(), info.getEvidenceVerification(), info.getAdjustment(),
					info.getReferenceMaterial(), info.getResultCalibration(), user.getId(), time, info.getId());

		}
	}

	public void saveEquipMaintenInfo(EquipMaintenInfo info, UUID idRandom) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_maintenance_detail_infos (id, equipment_id, type_id, freq_type, freq_duration, last_schedule, next_schedule,"
							+ " attachment_nm, remarks, company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ",
					idRandom, info.getEquipmentId(), info.getTypeId(), info.getFreqType(), info.getFreqDuration(),
					info.getLastSchedule(), info.getNextSchedule(), info.getAttachmentNm(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time);
			jdbcTemplate.update(
					"update  lims_frequency_infos " + " set last_schedule=?, next_schedule=?, last_schedule_id=? "
							+ " where equipment_id=? and type_id=?",
					info.getLastSchedule(), info.getNextSchedule(), idRandom, info.getEquipmentId(), info.getTypeId());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_maintenance_detail_infos SET equipment_id =?, type_id =?, freq_type =?, freq_duration =?, last_schedule = ?, next_schedule = ?, "
							+ " attachment_nm = ?, remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getEquipmentId(), info.getTypeId(), info.getFreqType(), info.getFreqDuration(),
					info.getLastSchedule(), info.getNextSchedule(), info.getAttachmentNm(), info.getRemarks(),
					user.getId(), time, info.getId());
			jdbcTemplate.update(
					"update  lims_frequency_infos " + " set last_schedule=?, next_schedule=?, last_schedule_id=? "
							+ " where equipment_id=? and type_id=?",
					info.getLastSchedule(), info.getNextSchedule(), info.getId(), info.getEquipmentId(),
					info.getTypeId());
		}
	}

	public EquipmentInfo geEquipmentInfoById(UUID id) {

		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject(
				" select id, equipment_name, equipment_type, model_no, capacity, source_manufacturer, local_agent_name,"
						+ " curr_location, lc_model_no, serial_no, ms_model_no,ms_serial_no, manufacturing_date, pump_model_no, detector_model_no,"
						+ " software_name, software_version_no, software_firmware_no, electric_power, nitrogen_consumption, "
						+ " calibration_date, next_calibration_date, install_qualific_date, operation_qualific_date, "
						+ " performance_qualific_date,quali_by, equipment_id, brand, accept_criteria, calibration_interval, evidence_verification,"
						+ " adjustment, reference_material, result_calibration from lims_equipment_infos  where id = ?",
				new Object[] { id }, new EquipmentInfoRowMapper());
	}

	public List<FrequencyInfo> findEquipment(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate
				.query("SELECT equipment_id, fnc_equipment_name(equipment_id) equipment_name from lims_frequency_infos "
						+ " WHERE company_id = ? and status=" + (status != null ? "'" + status + "'" : "status")
						+ " GROUP BY equipment_id ", new Object[] { user.getCompanyId() }, new EquipmentRowMapper());
	}

	public List<FrequencyInfo> maintenanceByEquipment(UUID equipmentId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select id, equipment_id, fnc_equipment_name(equipment_id) equipment_name, type_id, "
				+ " fnc_maintenance_type_nm(type_id) type_name,freq_type, freq_duration from lims_frequency_infos "
				+ " where company_id = ? and equipment_id = ? ", new Object[] { user.getCompanyId(), equipmentId },
				new FrequencyInfoRowMapper());
	}

	public FrequencyInfo findFrequenceTypeById(UUID equpId, UUID typeId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.queryForObject(
				"SELECT id, equipment_id, fnc_equipment_name(equipment_id) equipment_name, type_id, "
						+ " fnc_maintenance_type_nm(type_id) type_name, freq_type, freq_duration FROM lims_frequency_infos "
						+ " WHERE company_id = ? and equipment_id = ? and type_id = ? ",
				new Object[] { user.getCompanyId(), equpId, typeId }, new FrequencyInfoRowMapper());
	}

	public List<EquipMaintenInfo> findEquipMaintenInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT a.id, a.equipment_id, fnc_equipment_name(a.equipment_id) equipment_name,"
				+ " (SELECT equipment_name FROM lims_equipment_infos WHERE id = a.equipment_id) equip_full_name,"
				+ " (SELECT curr_location FROM lims_equipment_infos WHERE id = a.equipment_id) curr_location,"
				+ " a.type_id, fnc_maintenance_type_nm(a.type_id) type_name, a.freq_type, a.freq_duration,"
				+ " a.last_schedule, a.next_schedule, a.attachment_nm, a.remarks,"
				+ " abs(a.next_schedule :: date - now() :: date) as diff_days,COALESCE(b.notify_time,'0') notify_time "
				+ " FROM lims_maintenance_detail_infos a, lims_frequency_infos b "
				+ " WHERE a.id=b.last_schedule_id and a.company_id = ? ORDER BY equipment_name",
				new Object[] { user.getCompanyId() }, new EquipMaintenInfoRowMapper());
	}
	public List<EquipMaintenInfo> getEquipNotificationInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT a.id, a.equipment_id, fnc_equipment_name(a.equipment_id) equipment_name,"
				+ " (SELECT equipment_name FROM lims_equipment_infos WHERE id = a.equipment_id) equip_full_name,"
				+ " (SELECT curr_location FROM lims_equipment_infos WHERE id = a.equipment_id) curr_location,"
				+ " a.type_id, fnc_maintenance_type_nm(a.type_id) type_name, a.freq_type, a.freq_duration,"
				+ " a.last_schedule, a.next_schedule, a.attachment_nm, a.remarks,"
				+ " abs(a.next_schedule :: date - now() :: date) as diff_days,COALESCE(b.notify_time,'0') notify_time "
				+ " FROM lims_maintenance_detail_infos a, lims_frequency_infos b "
				+ " WHERE a.id=b.last_schedule_id "
				+ " and abs(a.next_schedule :: date - now() :: date) <=COALESCE(b.notify_time,'0')::int "
				+ " and a.company_id = ? ORDER BY equipment_name",
				new Object[] { user.getCompanyId() }, new EquipMaintenInfoRowMapper());
	}

	public List<EquipMaintenInfo> findEquipMaintenLogInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT b.id, b.equipment_id, a.equipment_id AS equipment_name, a.equipment_name AS equip_full_name, a.curr_location,"
						+ " b.type_id, fnc_maintenance_type_nm(b.type_id) type_name, b.freq_type, b.freq_duration, b.last_schedule, b.next_schedule,"
						+ " b.attachment_nm, b.remarks "
						+ " FROM lims_equipment_infos a,"
						+ " lims_maintenance_detail_infos b"
						+ " WHERE a.id=b.equipment_id AND a.company_id = ? ORDER BY equipment_name, type_name",
				new Object[] { user.getCompanyId() }, new EquipMainLogInfoRowMapper());
		
	}
	public List<EquipmentInfo> findUnassignedEquipment() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id,equipment_id, equipment_name FROM lims_equipment_infos "
				+ " WHERE id NOT IN(SELECT equipment_id FROM lims_department_equipment_mapping_infos WHERE  company_id = ?) "
				+ " and equipment_type='1'",
				new Object[] {user.getCompanyId() }, new EquipmentMappingRowMapper());
	}
	public List<EquipmentInfo> findAssignedEquipment(String id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id,equipment_id, equipment_name FROM lims_equipment_infos "
				+ " WHERE id  IN(SELECT equipment_id FROM lims_department_equipment_mapping_infos WHERE department_id=? and  company_id = ?)",
				new Object[] { id,user.getCompanyId() }, new EquipmentMappingRowMapper());
	}
	public void saveEquipmentMapping(UUID equipmentId, String departmentId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		jdbcTemplate.update(
				"insert into lims_department_equipment_mapping_infos(department_id, equipment_id, company_id, created_by, created_at) "
				+ " VALUES (?,?,?,?,?)",
				departmentId,equipmentId,user.getCompanyId(),user.getId(),time);
	}

	public void deleteEquipmentMapping(UUID equipmentId, String departmentId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		System.out.println();
		jdbcTemplate.update("DELETE FROM lims_department_equipment_mapping_infos WHERE equipment_id = ? "
				+ " AND department_id = ? AND  company_id = ?", equipmentId, departmentId,user.getCompanyId());
		
	}
	class EquipmentMappingRowMapper implements RowMapper<EquipmentInfo> {

		@Override
		public EquipmentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			EquipmentInfo info = new EquipmentInfo();
			
			info.setId((UUID) rs.getObject("id"));
			info.setEquipmentId(rs.getString("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			
			return info;
		}
	}

	public List<LogVacuumPumpInfo> getLogDistinctVaccumEquipment(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_vacuum_pump_infos where company_id = ? and is_verify=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctEquipmentRowMapperForVaccum());
	}
	
	class LogDistinctEquipmentRowMapperForVaccum implements RowMapper<LogVacuumPumpInfo> {

		@Override
		public LogVacuumPumpInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogVacuumPumpInfo info = new LogVacuumPumpInfo();
			info.setId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));

			return info;
		}

	}
	
	
	public List<LogSpectrophotometerInfo> findLogDistinctEquipmentForUv(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_spectrophotometer_infos where company_id = ? and is_verify=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctUvEquipmentRowMapper());
	}
	
	
	class LogDistinctUvEquipmentRowMapper implements RowMapper<LogSpectrophotometerInfo> {

		@Override
		public LogSpectrophotometerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogSpectrophotometerInfo info = new LogSpectrophotometerInfo();
			info.setId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));

			return info;
		}

	}

	public List<LogBenchtopReciprocalShakerInfo> findLogDistinctEquipmentForBeanchtop(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_benchtop_reciprocal_shaker_infos where company_id = ? and is_verify=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctBenchtopEquipmentRowMapper());
	}
	
	class LogDistinctBenchtopEquipmentRowMapper implements RowMapper<LogBenchtopReciprocalShakerInfo> {

		@Override
		public LogBenchtopReciprocalShakerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogBenchtopReciprocalShakerInfo info = new LogBenchtopReciprocalShakerInfo();
			info.setId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));

			return info;
		}

	}
	
	public List<LogColumnRcvDistInfo> findLogDistinctColRcvEquipment(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_column_rcv_dist_infos where company_id = ? and is_verify=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctColRcvEquipmentRowMapper());
	}

	class LogDistinctColRcvEquipmentRowMapper implements RowMapper<LogColumnRcvDistInfo> {

		@Override
		public LogColumnRcvDistInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogColumnRcvDistInfo info = new LogColumnRcvDistInfo();
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentByName(rs.getString("equipment_name"));

			return info;
		}

	}
	
	public List<LogRefrigeratorInfo> findLogDistinctRefrigeratorEquipment(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_refrigerator_infos where company_id = ? and is_verify=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctRefrigeratorEquipmentRowMapper());
	}

	class LogDistinctRefrigeratorEquipmentRowMapper implements RowMapper<LogRefrigeratorInfo> {

		@Override
		public LogRefrigeratorInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogRefrigeratorInfo info = new LogRefrigeratorInfo();
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));

			return info;
		}

	}
	
	
	public List<LogElectricOvenInfo> findLogDistinctEquipmentForOven(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_electric_oven_infos where company_id = ? and is_verify=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctOvenEquipmentRowMapper());
	}
	
	public List<LogTempeHumidityRecordInfo> findLogDistinctEquipmentTemHumidity(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_temp_humidity_record_infos where company_id = ? and is_verify=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctTempEquipmentRowMapper());
	}
	public List<LogRefrigeratorInfo> findLogDistinctEquipmentRefrigeratorTemp(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_refrigerator_infos where company_id = ? and is_verify=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctRefrigeratorTempRowMapper());
	}
	

	class LogDistinctTempEquipmentRowMapper implements RowMapper<LogTempeHumidityRecordInfo> {

		@Override
		public LogTempeHumidityRecordInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogTempeHumidityRecordInfo info = new LogTempeHumidityRecordInfo();
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));

			return info;
		}

	}
	class LogDistinctRefrigeratorTempRowMapper implements RowMapper<LogRefrigeratorInfo> {

		@Override
		public LogRefrigeratorInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogRefrigeratorInfo info = new LogRefrigeratorInfo();
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));

			return info;
		}

	}
	

	class LogDistinctOvenEquipmentRowMapper implements RowMapper<LogElectricOvenInfo> {

		@Override
		public LogElectricOvenInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogElectricOvenInfo info = new LogElectricOvenInfo();
			info.setId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));

			return info;
		}

	}
	
	
	public List<LogWaterPurificationInfo> findLogDistinctEquipment(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_water_purification_infos where company_id = ? and is_verify=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctEquipmentRowMapper());
	}


	class LogDistinctEquipmentSpectrophotometerInfoRowMapper implements RowMapper<LogSpectrophotometerInfo> {

		@Override
		public LogSpectrophotometerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogSpectrophotometerInfo info = new LogSpectrophotometerInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));

			return info;
		}

	}

/*	public List<LogWaterPurificationInfo> findLogDistinctEquipmentDeHumidifier(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_de_humidifier_infos where company_id = ? and is_verify=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctEquipmentRowMapper());
	}*/

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentTOC(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_toc_infos where company_id = ? and is_verify=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentSampleLabel(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_Sample_label_control_infos where company_id = ? and is_verify=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentAbsorption(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_absorption_spectrometer_infos where company_id = ? and verify_status=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentDesicator(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_desiccator_infos where company_id = ? and is_verify=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentDryCabinet(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_dry_cabinet_infos where company_id = ? and is_verify=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctEquipmentRowMapper());
	}
	
	public List<LogWaterPurificationInfo> findLogVerificationDissolution(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_verification_dissolution_infos where company_id = ? and verify_status=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctEquipmentRowMapper());
	}
	
	public List<LogWaterPurificationInfo> findLogHPLC(String status) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ "from lims_log_hplc_infos where company_id = ? and verify_status=? ",
				new Object[] { user.getCompanyId(), status }, new LogDistinctEquipmentHPLCRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentPhMeter(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_ph_meter_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentKarlFischer(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_karl_fischer_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentBiologicalSafety(String equipmentId,
			String cleanStatus, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_biological_safety_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentMuffleFurnace(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id, fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_muffle_furnace_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentMeltingPoint(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_melting_point_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentAutomaticPolarization(String equipmentId,
			String cleanStatus, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_automatic_polarimeter_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentBiochemicalOxyzen(String equipmentId,
			String cleanStatus, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_biochemical_oxygen_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentFumeHood(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_fume_hood_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentLaboratoryHeater(String equipmentId,
			String cleanStatus, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_laboratory_heater_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentGasChromatography(String equipmentId,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_gas_chromatography_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentRefractometer(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_refractometer_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentFtir(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_ftir_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentConductivity(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_multi_parameter_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentMoisture(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_moisture_analyzer_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentDisIntegration(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_disintegration_test_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}
	public List<LogWaterPurificationInfo> findLogDistinctEquipmentDeHumidifier(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_de_humidifier_infos a where " + " is_verify="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "is_verify")
						+ "  " + " and is_clean="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "is_clean")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogNoteBookControlInfo> findLogDistinctEmployeeNoteBook(String employeeId, String receiveStatus,
			String returnStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("SELECT distinct(employee_id) employee_id, fnc_emp_name(employee_id) emp_name "
				+ " from lims_log_notebook_infos a where " + " receive_status="
				+ ((receiveStatus != null && !receiveStatus.isEmpty()) ? "'" + receiveStatus + "'" : "receive_status")
				+ "  " + " and return_status="
				+ ((returnStatus != null && !returnStatus.isEmpty()) ? "'" + returnStatus + "'" : "return_status")
				+ "  " + " and  company_id = ?  ", new Object[] { user.getCompanyId() },
				new LogDistinctEmployeeRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentAnalytical(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_analytical_balance_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentDIssolution(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_dissolution_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentSonicator(String equipmentId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_sonicator_bath_infos1 a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentTempRefrigerator(String equipmentId,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from lims_log_temp_refrigerator_infos1 a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogDistinctEquipmentDAtaBackup(String equipmentId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(instrument_id) equipment_id, id,  fnc_equipment_name(instrument_id) equipment_name  "
						+ " from lims_log_databackup_infos a where " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and instrument_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "instrument_id")
						+ "  " + " and  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentRowMapper());
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

	class EquipmentRowMapper implements RowMapper<FrequencyInfo> {
		@Override
		public FrequencyInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			FrequencyInfo info = new FrequencyInfo();

			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			return info;
		}
	}

	class FrequencyInfoRowMapper implements RowMapper<FrequencyInfo> {
		@Override
		public FrequencyInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			FrequencyInfo info = new FrequencyInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setTypeId((UUID) rs.getObject("type_id"));
			info.setTypeName(rs.getString("type_name"));
			info.setFreqType(rs.getString("freq_type"));
			info.setFreqDuration(rs.getString("freq_duration"));
			return info;
		}
	}

	class EquipMaintenInfoRowMapper implements RowMapper<EquipMaintenInfo> {
		@Override
		public EquipMaintenInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			EquipMaintenInfo info = new EquipMaintenInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setEquipFullName(rs.getString("equip_full_name"));
			info.setCurrLocation(rs.getString("curr_location"));
			info.setTypeId((UUID) rs.getObject("type_id"));
			info.setTypeName(rs.getString("type_name"));
			info.setFreqType(rs.getString("freq_type"));
			info.setFreqDuration(rs.getString("freq_duration"));
			info.setLastSchedule(rs.getDate("last_schedule"));
			info.setNextSchedule(rs.getDate("next_schedule"));
			info.setAttachmentNm(rs.getString("attachment_nm"));
			info.setRemarks(rs.getString("remarks"));
			info.setDiffDays(rs.getInt("diff_days"));
			info.setNotifyTime(rs.getInt("notify_time"));
			return info;
		}
	}
	class EquipMainLogInfoRowMapper implements RowMapper<EquipMaintenInfo> {
		@Override
		public EquipMaintenInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			EquipMaintenInfo info = new EquipMaintenInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setEquipFullName(rs.getString("equip_full_name"));
			info.setCurrLocation(rs.getString("curr_location"));
			info.setTypeId((UUID) rs.getObject("type_id"));
			info.setTypeName(rs.getString("type_name"));
			info.setFreqType(rs.getString("freq_type"));
			info.setFreqDuration(rs.getString("freq_duration"));
			info.setLastSchedule(rs.getDate("last_schedule"));
			info.setNextSchedule(rs.getDate("next_schedule"));
			info.setAttachmentNm(rs.getString("attachment_nm"));
			info.setRemarks(rs.getString("remarks"));
			
			return info;
		}
	}

	class LogDistinctEquipmentRowMapper implements RowMapper<LogWaterPurificationInfo> {

		@Override
		public LogWaterPurificationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogWaterPurificationInfo info = new LogWaterPurificationInfo();
			info.setId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));

			return info;
		}

	}
	class LogDistinctEquipmentHPLCRowMapper implements RowMapper<LogWaterPurificationInfo> {

		@Override
		public LogWaterPurificationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogWaterPurificationInfo info = new LogWaterPurificationInfo();
			info.setId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));

			return info;
		}

	}
	class LogDistinctEmployeeRowMapper implements RowMapper<LogNoteBookControlInfo> {

		@Override
		public LogNoteBookControlInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogNoteBookControlInfo info = new LogNoteBookControlInfo();
			info.setEmployeeId((UUID) rs.getObject("employee_id"));
			info.setEmployeeName(rs.getString("emp_name"));

			return info;
		}

	}
	
	//For Micro
	public List<CommonInfo> findLogDistinctEquipmentMCInfos(String equipmentId, String checkedStatus, String verifiedStatus, String tableName) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from "+tableName+" a where " 
						+ (checkedStatus!=null?" checked_status='" + checkedStatus+ "' and" : "")
						+ (verifiedStatus!=null?" verified_status='" + verifiedStatus+ "' and" : "")//
						+ (equipmentId!=null?" equipment_id='" + equipmentId+ "' and" : "")
				//		+ (!equipmentId.isEmpty() && equipmentId!=null	? " and equipment_id='" + equipmentId.trim() + "'" : " ")
				//		+ (!equipmentId.isEmpty() && !equipmentId.equals("null")	? " AND UPPER(equipment_id) LIKE UPPER( '%" + equipmentId.trim() + "%') " : "")
						+ "  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentMCRowMapper());
	}
	public List<CommonInfo> findLogDistinctEquipmentMC5Infos(String equipmentId, String eveningStatus, String cleanedStatus,String checkedStatus , String tableName) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from "+tableName+" a where " 
						+ (eveningStatus!=null?" evening_status='" + eveningStatus+ "' and" : "")//
						+ (cleanedStatus!=null?" cleaned_status='" + cleanedStatus+ "' and" : "")
						+ (checkedStatus!=null?" checked_status='" + checkedStatus+ "' and" : "")
						+ (equipmentId!=null?" equipment_id='" + equipmentId+ "' and" : "")
						+ "  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentMCRowMapper());
	}
	public List<CommonInfo> findLogDistinctEquipmentMC4Infos(String equipmentId,  String cleanedStatus,String checkedStatus , String tableName) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT distinct(equipment_id) equipment_id,  fnc_equipment_name(equipment_id) equipment_name  "
						+ " from "+tableName+"  where " 
						+ (cleanedStatus!=null?" cleaned_status='" + cleanedStatus+ "' and" : "")
						+ (checkedStatus!=null?" checked_status='" + checkedStatus+ "' and" : "")
						+ (equipmentId!=null?" equipment_id='" + equipmentId+ "' and" : "")
						+ "  company_id = ?  ",
				new Object[] { user.getCompanyId() }, new LogDistinctEquipmentMCRowMapper());
	}
	
	class LogDistinctEquipmentMCRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CommonInfo info = new CommonInfo();
			info.setId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));

			return info;
		}

	}
	
}
