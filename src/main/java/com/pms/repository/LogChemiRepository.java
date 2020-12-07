package com.pms.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.pms.model.CommonInfo;
import com.pms.model.EquipmentInfo;
import com.pms.model.LogAllottedSampleInfo;
import com.pms.model.LogAnalystValidationInfo;
import com.pms.model.LogAnalyticalBalanceInfo;
import com.pms.model.LogAreaCleanInfo;
import com.pms.model.LogAtomicAbsorptionInfo;
import com.pms.model.LogAutomaticPolarimeterInfo;
import com.pms.model.LogBenchtopReciprocalShakerInfo;
import com.pms.model.LogBiochemicalOxygenInfo;
import com.pms.model.LogBiologicalSafetyInfo;
import com.pms.model.LogColumnInfo;
import com.pms.model.LogColumnPerformanceRecordInfo;
import com.pms.model.LogColumnRcvDistInfo;
import com.pms.model.LogComparativeDissolutionDutyInfo;
import com.pms.model.LogDataBackupInfo;
import com.pms.model.LogDeHumidifierInfo;
import com.pms.model.LogDesiccatorInfo;
import com.pms.model.LogDisintegrationTestInfo;
import com.pms.model.LogDissolutionInfo;
import com.pms.model.LogDryCabinetInfo;
import com.pms.model.LogEhdInfo;
import com.pms.model.LogElectricOvenInfo;
import com.pms.model.LogFilterChangeInfo;
import com.pms.model.LogFtirInfo;
import com.pms.model.LogFumeHoodInfo;
import com.pms.model.LogGasChromatographyInfo;
import com.pms.model.LogHplcInfo;
import com.pms.model.LogKarlFischerInfo;
import com.pms.model.LogLaboratoryHeaterInfo;
import com.pms.model.LogMeltingPointInfo;
import com.pms.model.LogMoistureAnalyzerInfo;
import com.pms.model.LogMuffleFurnaceInfo;
import com.pms.model.LogMultiParameterInfo;
import com.pms.model.LogNoteBookControlInfo;
import com.pms.model.LogOutofTrend;
import com.pms.model.LogPhMeterInfo;
import com.pms.model.LogRefractometerInfo;
import com.pms.model.LogRefrigeratorInfo;
import com.pms.model.LogSampleLabelInfo;
import com.pms.model.LogSonicatorBathInfo;
import com.pms.model.LogSpectrophotometerInfo;
import com.pms.model.LogTOCInfo;
import com.pms.model.LogTempHumidityInfo;
import com.pms.model.LogTempRefrigeratorInfo;
import com.pms.model.LogTempeHumidityRecordInfo;
import com.pms.model.LogTmControlInfo;
import com.pms.model.LogVacuumPumpInfo;
import com.pms.model.LogVerificationDissolutionChdInfo;
import com.pms.model.LogVerificationDissolutionInfo;
import com.pms.model.LogWaterPurificationInfo;
import com.pms.model.LogWaterSamplingInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class LogChemiRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;
	
	public List<EquipmentInfo> findAllLogEquipment(String logbookCode) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" Select equipment_id,fnc_equipment_name(equipment_id) equipment_name "
				+ " from lims_logbook_equipment_mapping_infos " + 
				"   where company_id = ? "
				+ " and logbook_id =(Select id from lims_log_book_infos where ud_logbook_id='"+logbookCode.trim()+"')",
				new Object[] { user.getCompanyId() }, new EquipmentInfoRowMapper());
	}

	public List<CommonInfo> findQcRefInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select id, arn_no from lims_qc_sample_rcv_infos  where company_id = ? ",
				new Object[] { user.getCompanyId() }, new QcInfoRowMapper());
	}

	public List<LogDataBackupInfo> findDataBackupInfos( String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, instrument_name, record_date, record_time, fnc_equipment_name(instrument_id) instrument_name, backup_date, backup_by, verify_by, remarks, "
						+ " fnc_user_nm(backup_by) backup_name, fnc_user_nm(verify_by) verify_name, verify_status "
						+ " from lims_log_databackup_infos  where  " + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new LogDataBackupRowMapper());
	}

	public void saveDataBackupLogInfo(LogDataBackupInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_databackup_infos( instrument_name, backup_date, backup_by, record_date, record_time, remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?)",
					info.getInstrumentName(), info.getBackupDate(), user.getId(), date, recordTime, info.getRemarks(),
					user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_databackup_infos SET instrument_name = ?, backup_date = ?,  "
							+ " remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getInstrumentName(), info.getBackupDate(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	public void saveTmControlLogInfo(LogTmControlInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_tmcontrol_infos( qc_reference_id, record_date, used_by, return_by, return_date,"
							+ " controlled_by, remarks, company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?)",
					info.getQcReferenceId(), info.getRecordDate(), info.getUsedBy(), info.getReturnBy(),
					info.getReturnDate(), info.getControlledBy(), info.getRemarks(), user.getCompanyId(), user.getId(),
					time);
		} else {

			jdbcTemplate.update(
					"UPDATE lims_log_tmcontrol_infos SET qc_reference_id=?, record_date=?, used_by=?, return_by=?, return_date=?,"
							+ " controlled_by=?, remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getQcReferenceId(), info.getRecordDate(), info.getUsedBy(), info.getReturnBy(),
					info.getReturnDate(), info.getControlledBy(), info.getRemarks(), user.getId(), time, info.getId());

		}
	}

	public List<LogNoteBookControlInfo> findLogNoteBookControlInfos(String receiveStatus, String returnStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		System.out.println(" User ID : " + user.getEmployeeId());
		return jdbcTemplate.query(
				" SELECT id,employee_id,fnc_emp_name(employee_id) emp_name,designation_id,fnc_desig_nm(designation_id) desig_name,note_book_no,issue_by,issue_date,"
						+ "fnc_user_nm (issue_by) issue_by_nm,fnc_user_nm (receive_by) receive_by_nm,fnc_user_nm (return_by) return_by_nm, record_time,"
						+ " receive_by,receive_date, return_by,return_date,remarks," + "receive_status, return_status "
						+ " from lims_log_notebook_infos a where" + " receive_status="
						+ ((receiveStatus != null && !receiveStatus.isEmpty()) ? "'" + receiveStatus + "'"
								: "receive_status")
						+ "  " + " and return_status="
						+ ((returnStatus != null && !returnStatus.isEmpty()) ? "'" + returnStatus + "'"
								: "return_status")
						+ "  " + "and employee_id="
						+ (user.getEmployeeId() != null ? "'" + user.getEmployeeId() + "'" : "employee_id") + ""
						+ " and company_id = ?  order by issue_date desc ",
				new Object[] { user.getCompanyId() }, new LogNoteBookRowMapper());
	}

	public void saveNoteBookControl(LogNoteBookControlInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_notebook_infos ( employee_id, designation_id, note_book_no, issue_by, issue_date,record_time,"
							+ " remarks, company_id, created_by, created_at) " + "" + "VALUES (?,?,?,?,?,?,?,?,?,?)",
					info.getEmployeeId(), info.getDesignationId(), info.getNoteBookNo(), user.getId(), date, recordTime,
					info.getRemarks(), user.getCompanyId(), user.getId(), time);
		} else {

			jdbcTemplate.update(
					"UPDATE lims_log_notebook_infos SET employee_id=?,designation_id=?,note_book_no=?,"
							+ " remarks=?, updated_by = ?, updated_at = ? where id = ?",
					info.getEmployeeId(), info.getDesignationId(), info.getNoteBookNo(), info.getRemarks(),
					user.getId(), time, info.getId());
		}
	}

	
	public List<LogEhdInfo> findEhdInfos(String  receiveStatus, String returnStatus, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, ehd_id_no, issue_date, issue_time, return_time, receive_by, receive_status, receive_at, return_by, verify_by,return_status,verify_status, remarks, "
						+ " fnc_user_nm(receive_by) receiveBy_name, fnc_user_nm(return_by) returnBy_name, fnc_user_nm(verify_by) verifyBy_name"
						+ " from lims_log_ehd_infos a "
						+ "where" + " verify_status=" + ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"	: "verify_status")
						+ " and receive_status="	+ ((receiveStatus != null && !receiveStatus.isEmpty()) ? "'" + receiveStatus + "'"	: "receive_status")
						+ " and return_status="	+ ((returnStatus != null && !returnStatus.isEmpty()) ? "'" + returnStatus + "'"	: "return_status")
						+ "  " + " and company_id = ?  order by record_date desc",

				new Object[] { user.getCompanyId() }, new EhdInfosRowMapper());
	}

	public List<LogEhdInfo> validateEhdNo(String ehdNo) {
		List<LogEhdInfo> entityList = jdbcTemplate.query(
				"SELECT id, ehd_id_no, issue_date, issue_time, return_time, receive_by, return_by, verify_by, remarks, "
						+ " fnc_emp_name(receive_by) receiveBy_name, fnc_emp_name(return_by) returnBy_name, fnc_emp_name(verify_by) verifyBy_name"
						+ " from lims_log_ehd_infos  where ehd_id_no = ? ",
				new Object[] { ehdNo }, new EhdInfosRowMapper());
		if (entityList == null) {
			throw new UsernameNotFoundException("does not exist.");
		}
		return entityList;
	}

	public void saveEhdInfo(LogEhdInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_ehd_infos ( ehd_id_no, issue_date, issue_time,record_date,record_time,   remarks, "//receive_by,
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?) ",//,?
					info.getEhdNo(), date, recordTime, date, recordTime,  info.getRemarks(),//user.getId(),
					user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_ehd_infos SET ehd_id_no =?, "
							+ " remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getEhdNo(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	public List<LogAreaCleanInfo> findAreaCleanInfos(String cleanStatus, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, agent_id, start_time, end_time, cleaning_date, exp_date, clean_by, verify_by, remarks,"
						+ " verified_at,record_time,record_date, is_verify, cleaning_equipment, "
						+ "(SELECT agent_name FROM lims_cleaning_agent_infos b WHERE b.id=a.agent_id) agent_name, "
						+ " fnc_emp_name(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name "
						+ " from lims_log_area_cleaning_infos a "
						+ "  where is_clean="	+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "is_clean")
						+ " and is_verify="	+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "is_verify")
						+ " and  company_id = ? ORDER BY is_verify, record_date desc",

				new Object[] { user.getCompanyId() }, new AreaCleanInfosRowMapper());
	}

	public void saveAreaCleanInfo(LogAreaCleanInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_area_cleaning_infos ( agent_id, cleaning_equipment, start_time, end_time,record_time, record_date, cleaning_date, exp_date, remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getAgentId(), info.getCleaningEquipment(), info.getStartTime(), info.getEndTime(), recordTime,
					date, info.getCleaningDate(), info.getCleaningExpDate(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_area_cleaning_infos SET agent_id =?, cleaning_equipment =?, start_time =?, end_time =?, cleaning_date = ?, exp_date = ?, "
							+ "  remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getAgentId(), info.getCleaningEquipment(), info.getStartTime(), info.getEndTime(),
					info.getCleaningDate(), info.getCleaningExpDate(), info.getRemarks(), user.getId(), time,
					info.getId());
		}
	}

	public List<LogMultiParameterInfo> findMultiParamInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT qc_reference_id, sample_type_id,fnc_material_type_nm (sample_type_id)  sample_type_name,"
				+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) qc_ref_name,"
						+ " id, operate_date, batch_no, ph, conductivity, tds,  operate_by, clean_by, verify_by, remarks,"
						+ " material_id, fnc_material_nm (material_id)  material_name, "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name, "
						+ " equipment_id,verify_status,clean_status, fnc_equipment_name(equipment_id) equipment_name, record_time "
						+ "  from lims_log_multi_parameter_infos a where"
						+ " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "   and company_id = ?  order by operate_date desc ",

				new Object[] { user.getCompanyId() }, new MultiParamInfosRowMapper());
	}

	public List<LogMoistureAnalyzerInfo> findMoistureParamInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();

		return jdbcTemplate.query(
				" SELECT id, qc_reference_id, record_date, record_time, ref_result, operate_by, clean_by, verify_by, remarks,"
						+ " verify_status, clean_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name, lot_no ,"
						+ " fnc_material_type_nm(sample_type_id) sample_type, sample_type_id,"
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_moisture_analyzer_infos a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",

				new Object[] { user.getCompanyId() }, new MoistureParamInfosRowMapper());
	}

	public List<LogDisintegrationTestInfo> findDisintegrationIfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();

		return jdbcTemplate.query(
				" SELECT id, qc_reference_id, record_date, record_time, dis_result, operate_by, clean_by, verify_by, remarks,"
						+ " verify_status, clean_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name, lot_no ,"
						+ " fnc_material_type_nm(sample_type_id) sample_type, sample_type_id,"
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_emp_name(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_disintegration_test_infos a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",

				new Object[] { user.getCompanyId() }, new DisInfectionInfoRowMapper());
	}

	public void saveMultiParameterInfo(LogMultiParameterInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {   //SampleType
			jdbcTemplate.update(
					"INSERT INTO lims_log_multi_parameter_infos ( qc_reference_id, material_id,sample_type_id,record_time, operate_date, conductivity, tds, operate_by,"
							+ " equipment_id,  remarks, company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcRefId(), info.getMaterialId(), info.getSampleTypeId(), recordTime, date, 
					info.getConductivity(), info.getTds(),  user.getId(),
					info.getEquipmentId(), info.getRemarks(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_multi_parameter_infos SET qc_reference_id =?,  sample_type_id =?, material_id =?, equipment_id =?, conductivity = ?, tds = ?,  "
							+ " remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getQcRefId(),info.getSampleTypeId(), info.getMaterialId(), info.getEquipmentId(), info.getConductivity(),
					info.getTds(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	public void saveMoistureAnalyzerInfo(LogMoistureAnalyzerInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_moisture_analyzer_infos ( qc_reference_id, sample_type_id, equipment_id, record_date, record_time, ref_result, operate_by,  remarks, "
							+ " company_id, created_by, created_at, lot_no) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcRefId(), info.getSampleTypeId(), info.getEquipmentId(), date, recordTime,
					info.getResult(), user.getId(), info.getRemarks(), user.getCompanyId(), user.getId(), time,
					info.getLotNo());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_moisture_analyzer_infos SET qc_reference_id =?, sample_type_id =?, equipment_id =?, ref_result =?,"
							+ "  remarks = ?, updated_by = ?, updated_at = ?, lot_no = ? where id = ?",
					info.getQcRefId(), info.getSampleTypeId(), info.getEquipmentId(), info.getResult(),
					info.getRemarks(), user.getId(), time, info.getLotNo(), info.getId());
		}
	}

	public void saveDisIntegrationInfo(LogDisintegrationTestInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_disintegration_test_infos ( qc_reference_id, sample_type_id, equipment_id, record_date, record_time, dis_result, operate_by,  remarks, "
							+ " company_id, created_by, created_at, lot_no) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcRefId(), info.getSampleTypeId(), info.getEquipmentId(), date, recordTime,
					info.getResult(), user.getId(), info.getRemarks(), user.getCompanyId(), user.getId(), time,
					info.getLotNo());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_disintegration_test_infos SET qc_reference_id =?, sample_type_id =?, equipment_id =?, dis_result =?,"
							+ "  remarks = ?, updated_by = ?, updated_at = ?, lot_no = ? where id = ?",
					info.getQcRefId(), info.getSampleTypeId(), info.getEquipmentId(), info.getResult(),
					info.getRemarks(), user.getId(), time, info.getLotNo(), info.getId());
		}
	}

	public List<LogHplcInfo> findHplcInfos(String eqipId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate
				.query(" SELECT id, record_date, equipment_id, fnc_equipment_name(equipment_id) equipment_name, "
						+ " qc_ref_id, lot_no, run_start_time, run_end_time, column_id, verify_status, "
						+ " first_mp_ratio, first_start_time, first_end_time, second_mp_ratio, second_start_time, second_end_time,"
						+ " total_time, operate_by_id, verified_by_id, remarks, verify_status, "
						 
				    	+ " (SELECT column_id_new FROM lims_log_column_rcv_dist_infos b WHERE b.id=a.column_id) column_name, "
							//	+ " (SELECT col_name FROM lims_log_column_infos b WHERE b.id=a.column_id) column_name, "
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_ref_id) qc_ref_name,"
						+ " fnc_user_nm(operate_by_id) operate_by_name, fnc_user_nm(verified_by_id) verify_by_name "
						+ " FROM lims_log_hplc_infos a "
						+ " where verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ " and equipment_id="
						+ ((eqipId != null && !eqipId.isEmpty()) ? "'" + eqipId + "'" : "equipment_id") + ""
						+ " and  company_id = ? ORDER BY record_date DESC",
						new Object[] { user.getCompanyId() }, new HplcInfosRowMapper());
	}

	public List<CommonInfo> findSampleReceivingInfos(String id, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate
				.query("select id, receive_at, material_name,ud_sample_no,sample_desc,arn_no, st_qty,rcv_qty, st_unit_id, st_unit_name,	"
						+ " storage_condition, sample_desc_rcv remarks,	fnc_user_nm(c.receive_By_id) receivedBy_name, "
						+ "	receive_By_id, st_room_name, st_rack_name from view_sample_rcv_infos  c	" 
						+ " where is_verify="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" 	: "is_verify")
						+ "  " + " and id="
						+ ((id != null && !id.isEmpty()) ? "'" + id + "'" : "id") + ""
						+ " and  company_id = ? ORDER BY receive_at desc ",
						new Object[] { user.getCompanyId() }, new SampleReceivingInfosRowMapper());
	}

	public void saveHplcInfo(LogHplcInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_hplc_infos ( equipment_id, record_date, qc_ref_id, lot_no, run_start_time, run_end_time, column_id,"
							+ " first_mp_ratio, first_start_time, first_end_time, second_mp_ratio, second_start_time, second_end_time, total_time,"
							+ " operate_by_id, remarks, company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getEquipmentId(), info.getRecordDate(), info.getQcRefId(), info.getLotNo(),
					info.getRunStartTime(), info.getRunEndTime(), info.getColumnId(), info.getFirstMpRatio(),
					info.getFirstStartTime(), info.getFirstEndTime(), info.getSecondMpRatio(),
					info.getSecondStartTime(), info.getSecondEndTime(), info.getTotalTime(), user.getId(),
					info.getRemarks(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_hplc_infos SET equipment_id =?, qc_ref_id =?, lot_no =?, run_start_time =?, run_end_time = ?, column_id = ?,"
							+ " first_mp_ratio =?, first_start_time =?, first_end_time =?, second_mp_ratio =?, second_start_time =?, second_end_time =?,"
							+ " total_time = ?, remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getEquipmentId(), info.getQcRefId(), info.getLotNo(),
					info.getRunStartTime(), info.getRunEndTime(), info.getColumnId(), info.getFirstMpRatio(),
					info.getFirstStartTime(), info.getFirstEndTime(), info.getSecondMpRatio(),
					info.getSecondStartTime(), info.getSecondEndTime(), info.getTotalTime(), info.getRemarks(),
					user.getId(), time, info.getId());
		}
	}

	public List<LogVacuumPumpInfo> findVacuumPumpInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, record_date, record_time, purpose, operate_by, verify_by, remarks, equipment_id, is_verify, "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(verify_by) verifyBy_name, fnc_equipment_name(equipment_id) equipmentBy_Name"
						+ " from lims_log_vacuum_pump_infos  where company_id = ? ",
				new Object[] { user.getCompanyId() }, new VacuumPumpInfoRowMapper());
	}

	public void saveVacuumPumpInfo(LogVacuumPumpInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_vacuum_pump_infos ( record_date, record_time, purpose, operate_by,remarks, "
							+ " company_id, created_by, created_at, equipment_id, is_verify) VALUES (?,?,?,?,?,?,?,?,?,?) ",
					date, recordTime, info.getPurpose(), user.getId(), info.getRemarks(), user.getCompanyId(),
					user.getId(), time, info.getEquipmentId(), "N");
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_vacuum_pump_infos SET purpose =?, operate_by =?, "
							+ " remarks = ?, updated_by = ?, updated_at = ?, equipment_id = ? where id = ?",
					info.getPurpose(), user.getId(), info.getRemarks(), user.getId(), time, info.getEquipmentId(),
					info.getId());
		}
	}

	public void verifyVaccumPump(LogVacuumPumpInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_vacuum_pump_infos SET is_verify= ?, verify_by= ?, verified_at= ?,remarks =? where id = ?", 'Y',
				user.getId(), time,info.getRemarks(), info.getId());
	}

	public List<LogVacuumPumpInfo> findAllVacuumPumpVerifyList(String eqipId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, equipment_id, fnc_equipment_name(equipment_id) equipmentBy_Name, remarks, fnc_user_nm (verify_by) verifyBy_name, fnc_user_nm(operate_by) operateBy_name,"
				+ " verify_by, verified_at, is_verify, record_date, record_time, purpose "
						+ "from lims_log_vacuum_pump_infos" + " where is_verify="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "is_verify")
						+ "  " + " and equipment_id="
						+ ((eqipId != null && !eqipId.isEmpty()) ? "'" + eqipId + "'" : "equipment_id") + ""
						+ " and  company_id = ? ORDER BY is_verify, record_date desc",
				new Object[] { user.getCompanyId() }, new VacuumPumpVerifyInfoRowMapper());
	}

	class VacuumPumpVerifyInfoRowMapper implements RowMapper<LogVacuumPumpInfo> {

		@Override
		public LogVacuumPumpInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogVacuumPumpInfo info = new LogVacuumPumpInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipmentBy_Name"));
			info.setPurpose(rs.getString("purpose"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setVerifiedAt(rs.getTimestamp("verified_at"));
			info.setIsVerify(rs.getString("is_verify"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			return info;
		}
	}

	public List<LogFilterChangeInfo> findFilterChangeInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, mainten_type, record_date, record_time, operate_by, verify_by, remarks, "
						+ " fnc_emp_name(operate_by) operateBy_name, fnc_emp_name(verify_by) verifyBy_name,"
						+ " fnc_maintenance_type_nm(mainten_type) mainten_name"
						+ " from lims_log_filter_change_infos where company_id = ? ",
				new Object[] { user.getCompanyId() }, new FilterChangeInfoRowMapper());
	}

	public void saveFilterChangeInfo(LogFilterChangeInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_filter_change_infos ( mainten_type, record_date, record_time, operate_by, verify_by, remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?) ",
					info.getMaintenType(), info.getRecordDate(), info.getRecordTime(), info.getOperateBy(),
					info.getVerifyBy(), info.getRemarks(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_filter_change_infos SET mainten_type =?, record_date =?, record_time =?, operate_by =?, verify_by = ?, "
							+ " remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getMaintenType(), info.getRecordDate(), info.getRecordTime(), info.getOperateBy(),
					info.getVerifyBy(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	public List<LogPhMeterInfo> findPhMeterInfos(String equipmentId, String cleanStatus, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, equipment_id, fnc_equipment_name(equipment_id) equipment_name,  record_date, record_time, ph_result, "
				+ " lot_no, operate_by, clean_by, clean_status, verify_by,verify_status, remarks, "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name "
						+ " from lims_log_ph_meter_infos a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new PhMeterInfoRowMapper());
	}

	public List<LogKarlFischerInfo> findKarlFischerTitratorInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, record_date, record_time, kft_result, operate_by, clean_by, verify_by, remarks,"
						+ " verify_status, clean_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name, lot_no , "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_karl_fischer_infos a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new kftInfoRowMapper());
	}

	public List<LogRefractometerInfo> findRefractometerInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, qc_reference_id, record_date, record_time, ref_result, operate_by, clean_by, verify_by, remarks,"
						+ " verify_status, clean_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name, lot_no , "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_refractometer_infos a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new refractometerInfoRowMapper());
	}

	public List<LogBiologicalSafetyInfo> findBiologicalSafetyInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, record_date, start_time,end_time, operate_by, clean_by, verify_by, remarks,"
						+ " verify_status, clean_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name, lot_no , "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_biological_safety_infos a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new biologicalSafelyRowMapper());
	}

	public List<LogMuffleFurnaceInfo> findMuffleFuranceInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, record_date, start_time,end_time, operate_by, clean_by, verify_by, remarks,"
						+ " verify_status, clean_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name, lot_no ,temperature,muf_result, "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_muffle_furnace_infos a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new muffleFurnaceRowMapper());
	}

	public List<LogMeltingPointInfo> findMeltingPointInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, record_date, start_time,end_time, operate_by, clean_by, verify_by, remarks,"
						+ " verify_status, clean_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name, lot_no ,mel_result, "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_melting_point_infos a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new meltingPointRowMapper());
	}

	public List<LogAutomaticPolarimeterInfo> findAutomaticPolarimeterInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, record_date, start_time,end_time, operate_by, clean_by, verify_by, remarks,"
						+ " verify_status, clean_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name, lot_no ,pol_result, "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_automatic_polarimeter_infos a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new automaticPolarizationRowMapper());
	}

	public List<LogBiochemicalOxygenInfo> findBiochemicalOxygenInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, sample_name_id, fnc_material_nm(sample_name_id) sample_name, start_date,end_date, start_time,end_time, operate_by, clean_by, verify_by, remarks,"
						+ " verify_status, clean_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name, bio_result, "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name"
						+ " from lims_log_biochemical_oxygen_infos a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by start_date ",
				new Object[] { user.getCompanyId() }, new biochemicalOxygenRowMapper());
	}

	public List<LogFumeHoodInfo> findFumeHoodInfos(String equipmentId, String cleanStatus, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, record_date, start_time,end_time, operate_by, clean_by, verify_by, remarks,"
						+ " verify_status, clean_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name,  purpose, "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name "
						+ " from lims_log_fume_hood_infos a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new fumeHoodRowMapper());
	}

	public List<LogLaboratoryHeaterInfo> findLaboratoryHeaterInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, record_date, start_time,end_time, operate_by, clean_by, verify_by, remarks,"
						+ " verify_status, clean_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name, lot_no ,temperature, "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_laboratory_heater_infos a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new laboratoryHeaterRowMapper());
	}

	public List<LogGasChromatographyInfo> findGasChromatographyInfos(String equipmentId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, qc_reference_id, record_date, start_time,end_time, operate_by, verify_by, remarks,total_analyst_time, "
						+ " verify_status, equipment_id, fnc_equipment_name(equipment_id) equipment_name, lot_no ,column_id, "
						
						+ " (SELECT column_id_new FROM lims_log_column_rcv_dist_infos b WHERE b.id=a.column_id) column_name, "
						//+ " (SELECT col_name FROM lims_log_column_infos b WHERE b.id=a.column_id) column_name, "
						+ " fnc_user_nm(operate_by) operateBy_name,  fnc_user_nm(verify_by) verifyBy_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_gas_chromatography_infos a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",
				new Object[] { user.getCompanyId() }, new gasChromatographyInfoRowMapper());
	}

	public List<LogTmControlInfo> findTmControllInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		;
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, record_date, used_by,fnc_emp_name(used_by) used_by_nm, return_by,fnc_emp_name(return_by) return_by_nm,"
						+ " return_date, controlled_by,fnc_emp_name(controlled_by) controlled_by_nm, remarks ,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name "
						+ " FROM lims_log_tmcontrol_infos a where company_id = ?",
				new Object[] { user.getCompanyId() }, new TmControlInfoRowMapper());
	}

	public void saveBenchtopReciprocalShaker(LogBenchtopReciprocalShakerInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_benchtop_reciprocal_shaker_infos ( qc_reference_id, record_date, record_time, done_by, remarks, company_id, is_verify, equipment_id) VALUES (?,?,?,?,?,?,?,?) ",
					info.getQcReferenceId(), date, recordTime, user.getId(), info.getRemarks(), user.getCompanyId(),
					'N', info.getEquipmentId());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_benchtop_reciprocal_shaker_infos SET qc_reference_id =?, remarks = ?, update_by = ?, update_at = ?, equipment_id = ? where id = ?",
					info.getQcReferenceId(), info.getRemarks(), user.getId(), time, info.getEquipmentId(),
					info.getId());
		}
	}

	public List<LogBenchtopReciprocalShakerInfo> getBenchtopReciprocalShakerVerifiedPendingInfos(String eqipId,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, equipment_id, fnc_equipment_name(equipment_id) equipmentBy_Name, remarks, fnc_user_nm (verify_by) verifyBy_name, fnc_user_nm(done_by) operateBy_name, verify_by, verified_at, is_verify, record_date, record_time, qc_reference_id, done_by, "
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name "
						+ "from lims_log_benchtop_reciprocal_shaker_infos a" + " where is_verify="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "is_verify")
						+ "  " + " and equipment_id="
						+ ((eqipId != null && !eqipId.isEmpty()) ? "'" + eqipId + "'" : "equipment_id") + ""
						+ " and  company_id = ? ORDER BY is_verify, record_date desc",
				new Object[] { user.getCompanyId() }, new BenchtopReciprocalShakerInfoRowMapper());
	}

	public List<LogBenchtopReciprocalShakerInfo> findBenchtopReciprocalShakerInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, qc_reference_id, record_date, record_time,  done_by, verify_by, remarks, equipment_id, is_verify,"
						+ " fnc_user_nm(done_by) operateBy_name, fnc_equipment_name(equipment_id) equipmentBy_Name, fnc_user_nm(verify_by) verifyBy_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_benchtop_reciprocal_shaker_infos a where company_id = ? order by record_date desc ",
				new Object[] { user.getCompanyId() }, new BenchtopReciprocalShakerInfoRowMapper());
	}
	
	public void verifyColRcvDistribution(LogColumnRcvDistInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_column_rcv_dist_infos SET is_verify=?, verify_by=?, verified_at=? WHERE id=? ", 'Y',
				user.getId(), time, info.getId());
	}

	public void verifyRefrigerator(LogRefrigeratorInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_refrigerator_infos SET is_verify=?,remarks =?, verify_by=?, verified_at=? WHERE id=? ", 'Y',
				info.getRemarks(),user.getId(), time, info.getId());
	}

	public void verifyTempHumidityRecord(LogTempeHumidityRecordInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_temp_humidity_record_infos SET is_verify=?,remarks =?, verify_by=?, verified_at=? WHERE id=? ",
				'Y',info.getRemarks(), user.getId(), time, info.getId());
	}

	public void verifyElectricOven(LogSpectrophotometerInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_electric_oven_infos SET is_verify=?,remarks =?,  verify_by=?, verified_at=? WHERE id=? ", 'Y',
				info.getRemarks(),user.getId(), time, info.getId());
	}

	class BenchtopReciprocalShakerInfoRowMapper implements RowMapper<LogBenchtopReciprocalShakerInfo> {

		@Override
		public LogBenchtopReciprocalShakerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogBenchtopReciprocalShakerInfo info = new LogBenchtopReciprocalShakerInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setOperateBy((UUID) rs.getObject("done_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setEquipmentName(rs.getString("equipmentBy_Name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setIsVerify(rs.getString("is_verify"));
			return info;
		}
	}

	public void verifyBenchtopReciprocalShaker(LogSpectrophotometerInfo info) {
		 
		
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_benchtop_reciprocal_shaker_infos SET is_verify=?,remarks =?, verify_by=?, verified_at=? WHERE id=? ",
				'Y',info.getRemarks(), user.getId(), time, info.getId());
	}

	public List<LogSpectrophotometerInfo> getUvVerifiedPendingInfos(String eqipId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, equipment_id, fnc_equipment_name(equipment_id) equipmentBy_Name, remarks, fnc_user_nm (verify_by) verifyBy_name, fnc_user_nm (clean_by) cleanBy_name, fnc_user_nm(created_by) operateBy_name, verify_by, verified_at, is_verify, record_date, record_time, qc_reference_id, done_by,clean_by, "
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name "
						+ "from lims_log_spectrophotometer_infos a" + " where is_verify="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "is_verify")
						+ "  " + " and equipment_id="
						+ ((eqipId != null && !eqipId.isEmpty()) ? "'" + eqipId + "'" : "equipment_id") + ""
						+ " and  company_id = ? ORDER BY is_verify, record_date desc",
				new Object[] { user.getCompanyId() }, new SpectrophotometerRowMapper());
	}

	class SpectrophotometerRowMapper implements RowMapper<LogSpectrophotometerInfo> {

		@Override
		public LogSpectrophotometerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogSpectrophotometerInfo info = new LogSpectrophotometerInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setOperateBy((UUID) rs.getObject("done_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setEquipmentName(rs.getString("equipmentBy_Name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			return info;
		}
	}

	public List<LogSpectrophotometerInfo> findSpectrophotometerInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, record_date, record_time,  done_by, clean_by, verify_by, remarks, equipment_id,is_verify,"
						+ " fnc_user_nm(done_by) operateBy_name, fnc_equipment_name(equipment_id) equipmentBy_Name, fnc_user_nm(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_spectrophotometer_infos a where company_id = ? order by record_date desc ",
				new Object[] { user.getCompanyId() }, new SpectrophotometerInfoRowMapper());
	}

	public void savePhMeterInfo(LogPhMeterInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);
		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_ph_meter_infos ( qc_reference_id, equipment_id,record_date, record_time, ph_result,lot_no, operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcReferenceId(), info.getEquipmentId(), date, recordTime, info.getPhResult(),info.getLotNo(), user.getId(),
					info.getRemarks(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_ph_meter_infos SET qc_reference_id =?,  equipment_id =?, ph_result =?,lot_no=?, "
							+ "  remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getQcReferenceId(), info.getEquipmentId(), info.getPhResult(), info.getLotNo(), info.getRemarks(), user.getId(),
					time, info.getId());
		}
	}

	public void verifyPhMeter(LogPhMeterInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_ph_meter_infos SET  verify_status =?,remarks = ?, verify_by = ?, verify_at = ? where id = ?", 'Y',
				info.getRemarks(),user.getId(),  time, info.getId());

	}

	public void cleanPhMeter(LogPhMeterInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_ph_meter_infos SET  clean_status =?, clean_by = ?, clean_at = ? where id = ?", 'Y',
				 user.getId(), time, info.getId());//info.getRemarks(),

	}

	public void saveKarlFischerInfo(LogKarlFischerInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_karl_fischer_infos ( qc_reference_id, equipment_id, record_date, record_time, kft_result, operate_by,  remarks, "
							+ " company_id, created_by, created_at, lot_no) VALUES (?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcReferenceId(), info.getEquipmentId(), date, recordTime, info.getKftResult(), user.getId(),
					info.getRemarks(), user.getCompanyId(), user.getId(), time, info.getLotNo());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_karl_fischer_infos SET qc_reference_id =?, equipment_id =?, kft_result =?,"
							+ "  remarks = ?, updated_by = ?, updated_at = ?, lot_no = ? where id = ?",
					info.getQcReferenceId(), info.getEquipmentId(), info.getKftResult(), info.getRemarks(),
					user.getId(), time, info.getLotNo(), info.getId());
		}
	}

	public void saveBiologicalSafetyInfo(LogBiologicalSafetyInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_biological_safety_infos ( qc_reference_id, equipment_id, record_date, start_time, end_time, operate_by,  remarks, "
							+ " company_id, created_by, created_at, lot_no) VALUES (?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcReferenceId(), info.getEquipmentId(), date, info.getStartTime(), info.getEndTime(),
					user.getId(), info.getRemarks(), user.getCompanyId(), user.getId(), time, info.getLotNo());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_biological_safety_infos SET qc_reference_id =?, equipment_id =?, start_time =?,end_time =?,"
							+ "  remarks = ?, updated_by = ?, updated_at = ?, lot_no = ? where id = ?",
					info.getQcReferenceId(), info.getEquipmentId(), info.getStartTime(), info.getEndTime(),
					info.getRemarks(), user.getId(), time, info.getLotNo(), info.getId());
		}
	}

	public void saveMuffleFurnaceInfo(LogMuffleFurnaceInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_muffle_furnace_infos ( qc_reference_id, equipment_id, record_date, start_time, end_time, temperature,muf_result, operate_by,  remarks, "
							+ " company_id, created_by, created_at, lot_no) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcReferenceId(), info.getEquipmentId(), date, info.getStartTime(), info.getEndTime(),
					info.getTemperature(), info.getResult(), user.getId(), info.getRemarks(), user.getCompanyId(),
					user.getId(), time, info.getLotNo());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_muffle_furnace_infos SET qc_reference_id =?, equipment_id =?, start_time =?,end_time =?,"
							+ "  temperature = ?,muf_result = ?,remarks = ?, updated_by = ?, updated_at = ?, lot_no = ? where id = ?",
					info.getQcReferenceId(), info.getEquipmentId(), info.getStartTime(), info.getEndTime(),
					info.getTemperature(), info.getResult(), info.getRemarks(), user.getId(), time, info.getLotNo(),
					info.getId());
		}
	}

	public void saveMeltingPoint(LogMeltingPointInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_melting_point_infos ( qc_reference_id, equipment_id, record_date, start_time, end_time, mel_result, operate_by,  remarks, "
							+ " company_id, created_by, created_at, lot_no) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcReferenceId(), info.getEquipmentId(), date, info.getStartTime(), info.getEndTime(),
					info.getResult(), user.getId(), info.getRemarks(), user.getCompanyId(), user.getId(), time,
					info.getLotNo());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_melting_point_infos SET qc_reference_id =?, equipment_id =?, start_time =?,end_time =?,"
							+ " mel_result = ?,remarks = ?, updated_by = ?, updated_at = ?, lot_no = ? where id = ?",
					info.getQcReferenceId(), info.getEquipmentId(), info.getStartTime(), info.getEndTime(),
					info.getResult(), info.getRemarks(), user.getId(), time, info.getLotNo(), info.getId());
		}
	}

	public void saveAutomaticPolarization(LogAutomaticPolarimeterInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_automatic_polarimeter_infos ( qc_reference_id, equipment_id, record_date, start_time, end_time, pol_result, operate_by,  remarks, "
							+ " company_id, created_by, created_at, lot_no) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcReferenceId(), info.getEquipmentId(), date, info.getStartTime(), info.getEndTime(),
					info.getResult(), user.getId(), info.getRemarks(), user.getCompanyId(), user.getId(), time,
					info.getLotNo());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_automatic_polarimeter_infos SET qc_reference_id =?, equipment_id =?, start_time =?,end_time =?,"
							+ " pol_result = ?,remarks = ?, updated_by = ?, updated_at = ?, lot_no = ? where id = ?",
					info.getQcReferenceId(), info.getEquipmentId(), info.getStartTime(), info.getEndTime(),
					info.getResult(), info.getRemarks(), user.getId(), time, info.getLotNo(), info.getId());
		}
	}

	public void saveBiochemicalOxyzen(LogBiochemicalOxygenInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_biochemical_oxygen_infos ( sample_name_id, equipment_id, start_date,end_date, start_time, end_time,record_date, bio_result, operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getSampleNameId(), info.getEquipmentId(), info.getStartDate(), info.getEndDate(),
					info.getStartTime(), info.getEndTime(), date, info.getResult(), user.getId(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_biochemical_oxygen_infos SET sample_name_id =?, equipment_id =?, start_date =?,end_date =?,start_time =?,end_time =?,"
							+ " bio_result = ?,remarks = ?, updated_by = ?, updated_at = ?  where id = ?",
					info.getSampleNameId(), info.getEquipmentId(), info.getStartDate(), info.getEndDate(),
					info.getStartTime(), info.getEndTime(), info.getResult(), info.getRemarks(), user.getId(), time,
					info.getId());
		}
	}

	public void saveFumeHood(LogFumeHoodInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_fume_hood_infos ( equipment_id, record_date, start_time, end_time,  operate_by,  remarks, "
							+ " company_id, created_by, created_at, purpose) VALUES (?,?,?,?,?,?,?,?,?,?) ",
					info.getEquipmentId(), date, info.getStartTime(), info.getEndTime(), user.getId(),
					info.getRemarks(), user.getCompanyId(), user.getId(), time, info.getPurpose());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_fume_hood_infos SET  equipment_id =?, start_time =?,end_time =?,"
							+ "remarks = ?, updated_by = ?, updated_at = ?, purpose = ? where id = ?",
					info.getEquipmentId(), info.getStartTime(), info.getEndTime(), info.getRemarks(), user.getId(),
					time, info.getPurpose(), info.getId());
		}
	}

	public void saveLaboratoryHeaterInfo(LogLaboratoryHeaterInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_laboratory_heater_infos ( qc_reference_id, equipment_id, record_date, start_time, end_time, temperature, operate_by,  remarks, "
							+ " company_id, created_by, created_at, lot_no) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcReferenceId(), info.getEquipmentId(), date, info.getStartTime(), info.getEndTime(),
					info.getTemperature(), user.getId(), info.getRemarks(), user.getCompanyId(), user.getId(), time,
					info.getLotNo());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_laboratory_heater_infos SET qc_reference_id =?, equipment_id =?, start_time =?,end_time =?,"
							+ "  temperature = ?,remarks = ?, updated_by = ?, updated_at = ?, lot_no = ? where id = ?",
					info.getQcReferenceId(), info.getEquipmentId(), info.getStartTime(), info.getEndTime(),
					info.getTemperature(), info.getRemarks(), user.getId(), time, info.getLotNo(), info.getId());
		}
	}

	public void saveGasChromatographyInfo(LogGasChromatographyInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_gas_chromatography_infos ( qc_reference_id, equipment_id, record_date, start_time, end_time,"
							+ " total_analyst_time, column_id, operate_by,  remarks, "
							+ " company_id, created_by, created_at, lot_no) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcReferenceId(), info.getEquipmentId(), date, info.getStartTime(), info.getEndTime(),
					info.getTotalAnalystTime(), info.getColumnId(), user.getId(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time, info.getLotNo());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_gas_chromatography_infos SET qc_reference_id =?, equipment_id =?, start_time =?,end_time =?,"
							+ " total_analyst_time= ?, column_id = ?,remarks = ?, updated_by = ?, updated_at = ?, lot_no = ? where id = ?",
					info.getQcReferenceId(), info.getEquipmentId(), info.getStartTime(), info.getEndTime(),
					info.getTotalAnalystTime(), info.getColumnId(), info.getRemarks(), user.getId(), time,
					info.getLotNo(), info.getId());
		}
	}

	public void saveRefractometerInfo(LogRefractometerInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_refractometer_infos ( qc_reference_id, equipment_id, record_date, record_time, ref_result, operate_by,  remarks, "
							+ " company_id, created_by, created_at, lot_no) VALUES (?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcReferenceId(), info.getEquipmentId(), date, recordTime, info.getKftResult(), user.getId(),
					info.getRemarks(), user.getCompanyId(), user.getId(), time, info.getLotNo());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_refractometer_infos SET qc_reference_id =?, equipment_id =?, ref_result =?,"
							+ "  remarks = ?, updated_by = ?, updated_at = ?, lot_no = ? where id = ?",
					info.getQcReferenceId(), info.getEquipmentId(), info.getKftResult(), info.getRemarks(),
					user.getId(), time, info.getLotNo(), info.getId());
		}
	}

	public void cleanCarlFischer(LogKarlFischerInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_karl_fischer_infos SET  clean_status =?, clean_by = ?, clean_at = ? where id = ?", 'Y',
				user.getId(), time, info.getId());

	}

	public void verifyKarlFischer(LogKarlFischerInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_karl_fischer_infos SET  verify_status =?,remarks =?, verify_by = ?, verify_at = ? where id = ?",
				'Y',info.getRemarks(), user.getId(), time, info.getId());

	}

	public void cleanRefractometer(LogRefractometerInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_refractometer_infos SET  clean_status =?, clean_by = ?, clean_at = ? where id = ?",
				'Y', user.getId(), time, info.getId());

	}

	public void verifyBiologicalSafety(LogBiologicalSafetyInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_biological_safety_infos SET  verify_status =?, remarks =?, verify_by = ?, verify_at = ? where id = ?",
				'Y', info.getRemarks(), user.getId(), time, info.getId());

	}

	public void cleanBiologicalSafety(LogBiologicalSafetyInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_biological_safety_infos SET  clean_status =?, clean_by = ?, clean_at = ? where id = ?",
				'Y', user.getId(), time, info.getId());

	}

	public void verifyRefractometer(LogRefractometerInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_refractometer_infos SET  verify_status =?,remarks =?,  verify_by = ?, verify_at = ? where id = ?",
				'Y', info.getRemarks(), user.getId(), time, info.getId());

	}

	public void cleanFtir(LogFtirInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update("UPDATE lims_log_ftir_infos SET  clean_status =?, clean_by = ?, clean_at = ? where id = ?",
				'Y', user.getId(), time, info.getId());
	}

	public void verifyFtir(LogFtirInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_ftir_infos SET  verify_status =?,remarks =?, verify_by = ?, verify_at = ? where id = ?", 'Y',
				info.getRemarks(),user.getId(), time, info.getId());
	}

	public void cleanConductivityMeter(LogMultiParameterInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_multi_parameter_infos SET  clean_status =?, clean_by = ?, clean_at = ? where id = ?",
				'Y', user.getId(), time, info.getId());
	}

	public void verifyConductivityMeter(LogMultiParameterInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_multi_parameter_infos SET  verify_status =?,remarks =?, verify_by = ?, verify_at = ? where id = ?",
				'Y',info.getRemarks(), user.getId(), time, info.getId());
	}

	public void cleanMoistureAnalyzer(LogMoistureAnalyzerInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_moisture_analyzer_infos SET  clean_status =?, clean_by = ?, clean_at = ? where id = ?",
				'Y', user.getId(), time, info.getId());
	}

	public void verifyMoistureAnalyzer(LogMoistureAnalyzerInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_moisture_analyzer_infos SET  verify_status =? ,remarks =?,  verify_by = ?, verify_at = ? where id = ?",
				'Y',info.getRemarks(), user.getId(), time, info.getId());
	}

	public void cleanDisIntegrationInfo(LogDisintegrationTestInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_disintegration_test_infos SET  clean_status =?, clean_by = ?, clean_at = ? where id = ?",
				'Y', info.getCleanBy(), time, info.getId());
	}

	public void verifyDisIntegrationInfo(LogDisintegrationTestInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_disintegration_test_infos SET  verify_status =?, remarks =?,  verify_by = ?, verify_at = ? where id = ?",
				'Y',info.getRemarks(),	 user.getId(), time, info.getId());
	}

	public void receiveNoteBook(LogNoteBookControlInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		jdbcTemplate.update(
				"UPDATE lims_log_notebook_infos SET  receive_status =?, receive_by = ?, receive_date = ?, receive_at = ? where id = ?",
				'Y', user.getId(), date, time, info.getId());
	}

	public void returnNoteBook(LogNoteBookControlInfo info) {
		
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		jdbcTemplate.update(
				"UPDATE lims_log_notebook_infos SET  return_status =?,remarks =?, return_by = ?,return_date = ?, return_at = ? where id = ?",
				'Y',info.getRemarks(), user.getId(), date, time, info.getId());
	}

	public void cleanAnalyticalBalance(LogAnalyticalBalanceInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_analytical_balance_infos SET  clean_status =?, clean_by = ?, clean_at = ? where id = ?",
				'Y', user.getId(), time, info.getId());
	}

	public void verifyAnalyticalBalance(LogAnalyticalBalanceInfo info) {
		
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_analytical_balance_infos SET  verify_status =?,remarks =?, verify_by = ?, verify_at = ? where id = ?",
				'Y',info.getRemarks(), user.getId(), time, info.getId());
	}

	public void cleanDissolution(LogDissolutionInfo info) {
System.out.println("======================ddddd================");
jdbcTemplate = new JdbcTemplate(datasource);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_dissolution_infos SET  clean_status =?, clean_by = ?, clean_at = ? where id = ?", 'Y',
				info.getCleanBy(), time, info.getId());
	}

	public void cleanAreaClean(LogAreaCleanInfo info) {
         jdbcTemplate = new JdbcTemplate(datasource);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_area_cleaning_infos SET  is_clean =?, clean_by = ?, cleaned_at = ? where id = ?", 'Y',
				info.getCleanBy(), time, info.getId());
	}
	public void verifyDissolution(LogDissolutionInfo info) {
		
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_dissolution_infos SET  verify_status =?,remarks =?, verify_by = ?, verify_at = ? where id = ?",
				'Y',info.getRemarks(),	user.getId(), time, info.getId());
	}

	public void verifySonicatorBath(LogSonicatorBathInfo info) {

       jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_sonicator_bath_infos1 SET  verify_status =?, remarks =?, verify_by = ?, verify_at = ? where id = ?",
				'Y',info.getRemarks(), user.getId(), time, info.getId());
	}

	public void verifyTempRefrigerator(LogTempRefrigeratorInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_temp_refrigerator_infos1 SET  verify_status =?,remarks =?,  verify_by = ?, verify_at = ? where id = ?",
				'Y',info.getRemarks(), user.getId(), time, info.getId());
	}

	public void verifyDataBackup(LogDataBackupInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_databackup_infos SET  verify_status =?,remarks =?, verify_by = ?, verify_at = ? where id = ?",
				'Y',info.getRemarks(), user.getId(), time, info.getId());
	}

	public void cleanMuffleFurnace(LogMuffleFurnaceInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_muffle_furnace_infos SET  clean_status =?, clean_by = ?, clean_at = ? where id = ?",
				'Y', user.getId(), time, info.getId());
	}

	public void verifyMuffleFurnace(LogMuffleFurnaceInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_muffle_furnace_infos SET  verify_status =?,remarks =?, verify_by = ?, verify_at = ? where id = ?",
				'Y',info.getRemarks(), user.getId(), time, info.getId());
	}

	public void cleanMeltingPoint(LogMeltingPointInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_melting_point_infos SET  clean_status =?, clean_by = ?, clean_at = ? where id = ?",
				'Y', user.getId(), time, info.getId());
	}

	public void verifyMeltingPoint(LogMeltingPointInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_melting_point_infos SET  verify_status =?, remarks =?,  verify_by = ?, verify_at = ? where id = ?",
				'Y', info.getRemarks(),user.getId(), time, info.getId());
	}

	public void cleanAutomaticPolarimeter(LogAutomaticPolarimeterInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_automatic_polarimeter_infos SET  clean_status =?, clean_by = ?, clean_at = ? where id = ?",
				'Y', user.getId(), time, info.getId());
	}

	public void verifyAutomaticPolarimeter(LogAutomaticPolarimeterInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_automatic_polarimeter_infos SET  verify_status =?,remarks =?,  verify_by = ?, verify_at = ? where id = ?",
				'Y',info.getRemarks(), user.getId(), time, info.getId());
	}

	public void cleanBiochemicalOxygen(LogBiologicalSafetyInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_biochemical_oxygen_infos SET  clean_status =?, clean_by = ?, clean_at = ? where id = ?",
				'Y', user.getId(), time, info.getId());
	}

	public void verifyBiochemicalOxygen(LogBiologicalSafetyInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_biochemical_oxygen_infos SET  verify_status =?,remarks =?,  verify_by = ?, verify_at = ? where id = ?",
				'Y',info.getRemarks(), user.getId(), time, info.getId());
	}

	public void receiveEHD(LogEhdInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_ehd_infos SET  receive_status =?, receive_by = ?, receive_at = ? where id = ?",
				'Y', user.getId(),  time, info.getId());
	}
	
	public void returnEHD(LogEhdInfo info) {
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_ehd_infos SET  return_status =?, return_by = ?,return_time = ?, return_at = ? where id = ?",
				'Y', user.getId(), recordTime, time, info.getId());
	}

	public void verifyEHD(LogEhdInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_ehd_infos SET  verify_status =?,remarks =?,  verify_by = ?, verify_at = ? where id = ?", 'Y',
				info.getRemarks(),user.getId(), time, info.getId());
	}

	public void cleanFumeHood(LogFumeHoodInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_fume_hood_infos SET  clean_status =?, clean_by = ?, clean_at = ? where id = ?", 'Y',
				user.getId(), time, info.getId());
	}

	public void verifyFumeHood(LogFumeHoodInfo info) {	 
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_fume_hood_infos SET  verify_status =?,remarks =?, verify_by = ?, verify_at = ? where id = ?", 'Y',
				info.getRemarks(),	user.getId(), time, info.getId());
	}

	public void cleanLaboratoryHeater(LogLaboratoryHeaterInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_laboratory_heater_infos SET  clean_status =?, clean_by = ?, clean_at = ? where id = ?",
				'Y', user.getId(), time, info.getId());
	}

	public void verifyLaboratoryHeater(LogLaboratoryHeaterInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_laboratory_heater_infos SET  verify_status =?,remarks =?, verify_by = ?, verify_at = ? where id = ?",
				'Y',info.getRemarks(), user.getId(), time, info.getId());
	}

	public void verifyGasChromatography(LogGasChromatographyInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_gas_chromatography_infos SET  verify_status =?,remarks =?, verify_by = ?, verify_at = ? where id = ?",
				'Y', info.getRemarks(),user.getId(), time, info.getId());
	}

	public void saveSpectrophotometerInfo(LogSpectrophotometerInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_spectrophotometer_infos ( qc_reference_id, record_date, record_time, done_by, clean_by, remarks, "
							+ " company_id, created_by, created_at, is_verify, equipment_id) VALUES (?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcReferenceId(), date, recordTime, user.getId(), user.getId(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time, 'N', info.getEquipmentId());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_spectrophotometer_infos SET qc_reference_id =?, remarks = ?, updated_by = ?, updated_at = ?, equipment_id = ? where id = ?",
					info.getQcReferenceId(), info.getRemarks(), user.getId(), time, info.getEquipmentId(),
					info.getId());
		}
	}

	public List<LogDissolutionInfo> findDissolutionInfos(String equipmentId, String cleanStatus, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, record_date, record_time,  operate_by, verify_by, remarks, "
						+ " equipment_id, fnc_equipment_name(equipment_id) equipment_name, verify_status, clean_by,fnc_emp_name(clean_by) cleanBy_name, clean_status,"
						+ " fnc_user_nm(operate_by) tested_name, fnc_user_nm(verify_by) verify_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_dissolution_infos a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",

				new Object[] { user.getCompanyId() }, new DissolutionInfoRowMapper());
	}

	public List<LogSonicatorBathInfo> findSonicatorBathInfos(String equipmentId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, record_date, record_time,  operate_by, verify_by, remarks, "
						+ " equipment_id, fnc_equipment_name(equipment_id) equipment_name, verify_status,"
						+ " fnc_user_nm(operate_by) tested_name, fnc_user_nm(verify_by) verify_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_sonicator_bath_infos1 a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",

				new Object[] { user.getCompanyId() }, new SonicatorBathRowMapper());
	}

	public List<LogTempRefrigeratorInfo> findTempRefrigeratorInfos(String equipmentId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, temp_before,temp_after,  record_date, record_time,  operate_by, verify_by, remarks, "
						+ " fnc_user_nm(operate_by) tested_name, fnc_user_nm(verify_by) verify_name,"
						+ " equipment_id, fnc_equipment_name(equipment_id) equipment_name, verify_status "
						+ " from lims_log_temp_refrigerator_infos1 a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",

				new Object[] { user.getCompanyId() }, new TempRefrigeratorRowMapper());
	}

	public void saveDissolutionInfo(LogDissolutionInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_dissolution_infos ( qc_reference_id, equipment_id ,record_date, record_time, operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?) ",
					info.getQcReferenceId(), info.getEquipmentId(), date, recordTime, user.getId(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_dissolution_infos SET qc_reference_id =?, equipment_id =?, "
							+ "  remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getQcReferenceId(), info.getEquipmentId(), info.getRemarks(), user.getId(), time,
					info.getId());
		}
	}

	public void saveSonicatorBathInfo(LogSonicatorBathInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_sonicator_bath_infos1 ( qc_reference_id, equipment_id ,record_date, record_time, operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?) ",
					info.getQcReferenceId(), info.getEquipmentId(), date, recordTime, user.getId(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_sonicator_bath_infos1 SET qc_reference_id =?, equipment_id =?, "
							+ "  remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getQcReferenceId(), info.getEquipmentId(), info.getRemarks(), user.getId(), time,
					info.getId());
		}
	}

	public void saveTempRefrigeratorInfo(LogTempRefrigeratorInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);
		System.out.println("=======================333========");

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_temp_refrigerator_infos1 ( temp_after,temp_before, equipment_id ,record_date, record_time, operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?) ",
					info.getTempAfter(), info.getTempBefore(), info.getEquipmentId(), date, recordTime, user.getId(),
					info.getRemarks(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_temp_refrigerator_infos1 SET temp_after =?,temp_before =?, equipment_id =?, "
							+ "  remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getTempAfter(), info.getTempBefore(), info.getEquipmentId(), info.getRemarks(), user.getId(),
					time, info.getId());
		}
	}

	public List<LogFtirInfo> findFtirInfos(String equipmentId, String cleanStatus, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, record_date, record_time, operate_by, clean_by, verify_by, remarks,"
						+ " equipment_id,lot_no,  fnc_equipment_name(equipment_id) equipment_name, verify_status, clean_status, "
						+ " fnc_user_nm(operate_by) operateBy_name, fnc_user_nm(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_ftir_infos a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",

				new Object[] { user.getCompanyId() }, new FtirInfoRowMapper());
	}

	public void saveFtirInfo(LogFtirInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_ftir_infos ( qc_reference_id, equipment_id,lot_no, record_date, record_time, operate_by,  remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?) ",
					info.getQcReferenceId(), info.getEquipmentId(), info.getLotNo(), date, recordTime, user.getId(),
					info.getRemarks(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_ftir_infos SET qc_reference_id =?, equipment_id=?, lot_no=?, "
							+ "  remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getQcReferenceId(), info.getEquipmentId(), info.getLotNo(), info.getRemarks(), user.getId(),
					time, info.getId());
		}
	}

	public List<LogTempeHumidityRecordInfo> tempHumidityRecordVerifyList() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, record_date, record_time,  done_by, verify_by, remarks, equipment_id, is_verify, temperature ,start_time, end_time, "
						+ " fnc_user_nm(done_by) doneByName, fnc_equipment_name(equipment_id) equipmentBy_Name, fnc_user_nm(verify_by) verifyBy_name"
						+ " from lims_log_temp_humidity_record_infos a where company_id = ? ",

				new Object[] { user.getCompanyId() }, new tempeHumidityRecordInfoRowMapper());
	}

	public List<LogTempeHumidityRecordInfo> getTemperatureHumidityRecordInfos() { 
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT a.id, after_relative_humidity, after_temp, before_relative_humidity, before_temp, done_by, a.equipment_id, st.department_id, st.locaton_id, is_verify, record_date, record_time, remarks, verified_at, verify_by, "
						+ " fnc_user_nm(done_by) doneByName, fnc_dept_nm(st.department_id) departmentByName, fnc_location_name(st.locaton_id) locationByName, fnc_equipment_name(a.equipment_id) equipmentBy_Name, fnc_user_nm(verify_by) verifyBy_name "
						+ " from lims_log_temp_humidity_record_infos a JOIN lims_temp_humidity_setup_infos st ON a.equipment_id=st.equipment_id where a.company_id = ? order by record_date desc",
				new Object[] { user.getCompanyId() }, new tempeHumidityRecordInfoRowMapper());
	}

	class tempeHumidityRecordInfoRowMapper implements RowMapper<LogTempeHumidityRecordInfo> {

		@Override
		public LogTempeHumidityRecordInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogTempeHumidityRecordInfo info = new LogTempeHumidityRecordInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setIsVerify(rs.getString("is_verify"));
			info.setVerifiedAt(rs.getTimestamp("verified_at"));
			info.setRemarks(rs.getString("remarks"));
			info.setDoneBy((UUID) rs.getObject("done_by"));
			info.setDoneByName(rs.getString("doneByName"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipmentBy_Name"));
			info.setDepartmentByName(rs.getString("departmentByName"));
			info.setLocationByName(rs.getString("locationByName"));
			info.setBeforeTemp(rs.getString("before_temp"));
			info.setAfterTemp(rs.getString("after_temp"));
			info.setAfterRelativeHumidity(rs.getString("after_relative_humidity"));
			info.setBeforeRelativeHumidity(rs.getString("before_relative_humidity"));
			return info;
		}
	}

	public List<LogElectricOvenInfo> findElectricOvenInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, equipment_id,temperature, record_date, start_time, end_time, verify_by, remarks, done_by, is_verify, "
						+ " fnc_user_nm(verify_by) verifyBy_name, fnc_user_nm(done_by) doneByName, fnc_equipment_name(equipment_id) equipmentBy_Name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_electric_oven_infos a where company_id = ? order by record_date desc",
				new Object[] { user.getCompanyId() }, new ElectricOvenInfoRowMapper());
	}

	public void saveTempHumidityRecord(LogTempeHumidityRecordInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_temp_humidity_record_infos (after_relative_humidity, before_relative_humidity,"
					+ " after_temp, before_temp, done_by, equipment_id, is_verify, record_date, record_time, remarks, company_id)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?) ",
					info.getAfterRelativeHumidity(), info.getBeforeRelativeHumidity(), info.getAfterTemp(),
					info.getBeforeTemp(), user.getId(), info.getEquipmentId(), 'N', date, recordTime, info.getRemarks(),
					user.getCompanyId());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_temp_humidity_record_infos SET equipment_id =?, after_relative_humidity =?, before_relative_humidity =?, after_temp =?, "
							+ "remarks = ?, before_temp = ?, update_at = ?, update_by = ? where id = ?",
					info.getEquipmentId(), info.getAfterRelativeHumidity(), info.getBeforeRelativeHumidity(),
					info.getAfterTemp(), info.getRemarks(), info.getBeforeTemp(), date, user.getId(), info.getId());
		}
	}

	public void saveElectricOvenInfo(LogElectricOvenInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_electric_oven_infos ( qc_reference_id, temperature, record_date, start_time, end_time, remarks, "
							+ "company_id, created_by, created_at, record_time, equipment_id, is_verify, done_by) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcReferenceId(), info.getTemperature(), date, info.getStartTime(), info.getEndTime(),
					info.getRemarks(), user.getCompanyId(), user.getId(), time, recordTime, info.getEquipmentId(), 'N',
					user.getId());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_electric_oven_infos SET qc_reference_id =?, equipment_id =?, temperature =?, start_time =?, end_time =?, "
							+ "remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getQcReferenceId(), info.getEquipmentId(), info.getTemperature(), info.getStartTime(),
					info.getEndTime(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	public CommonInfo findSampleInfoById(UUID id) {

		jdbcTemplate = new JdbcTemplate(datasource);
		return (CommonInfo) jdbcTemplate.queryForObject("SELECT id, material_name, material_code, material_type_id "
				+ " FROM lims_material_infos where id = ? ", new Object[] { id }, new SampleInfoRowMapper());
	}

	public CommonInfo findMaterialInfoByArn(UUID arnNo) {

		jdbcTemplate = new JdbcTemplate(datasource);

		return (CommonInfo) jdbcTemplate.queryForObject(
				"SELECT id, arn_no, material_name, material_code, material_type_id, material_type_nm,"
				+ " material_id,material_name,  st_qty,st_unit_id,st_unit_name "
						+ " FROM view_sample_rcv_infos where id = ? ",
				new Object[] { arnNo }, new MaterialInfoRowMapper());
	}

	public List<LogRefrigeratorInfo> findRefrigeratorInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, qc_reference_id, sample_name_id, sample_type_id, qty, in_date, out_date, discard_date, equipment_id, is_verify, "
				        + " material_id, fnc_material_nm (material_id)  material_name,"
                    //  + "fnc_material_type_nm (sample_type_id)  sample_type_name, "
						+ " unit_id, fnc_unit_nm(unit_id) unit_name, dis_unit_id, fnc_unit_nm(dis_unit_id) dis_unit_name,"
						+ " discard_qty, discard_by, verify_by, remarks, fnc_emp_name(discard_by) discardBy_name, "
						+ " fnc_user_nm(verify_by) verifyBy_name,"
						+ " fnc_surgical_name(sample_name_id) sample_name, "
						+ " fnc_material_type_nm(sample_type_id) sample_type, fnc_equipment_name(equipment_id) equipmentByName, "
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_refrigerator_infos a where company_id = ? order by  created_at desc ",
				new Object[] { user.getCompanyId() }, new RefrigeratorInfoRowMapper());
	}

	public void discardedQuantityRefrigeratorInfos(LogRefrigeratorInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		
		jdbcTemplate.update(
				"UPDATE lims_log_refrigerator_infos SET qty = ?, discard_qty = ?, dis_unit_id = ?, discard_by = ?, discard_date = ?, out_date= ? where id = ?",
				info.getQty(), info.getDiscardQty(), info.getDisUnitId(), user.getId(), date, date, info.getId());

	}

	public void saveRefrigeratorInfo(LogRefrigeratorInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		if (info.getId() == null) {
			jdbcTemplate.update(//sample_name_id,  info.getSampleNameId(),
					"INSERT INTO lims_log_refrigerator_infos ( material_id,qc_reference_id, sample_type_id, equipment_id, qty, in_date, remarks, company_id, created_by, created_at, is_verify, discard_qty, unit_id)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
					info.getMaterialId(),info.getQcRefId(),  info.getSampleTypeId(), info.getEquipmentId(),
					info.getQty(), date, info.getRemarks(), user.getCompanyId(), user.getId(), time, 'N', '0',info.getUnitId());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_refrigerator_infos SET material_id =?, qc_reference_id =?,sample_type_id =?, equipment_id =?, qty =?,unit_id = ?, remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getMaterialId(),info.getQcRefId(),  info.getSampleTypeId(), info.getEquipmentId(),
					info.getQty(), info.getUnitId(),  info.getRemarks(), user.getId(), time, info.getId());
		}// sample_name_id =?,   info.getSampleNameId(),
	}

	public List<LogDesiccatorInfo> findDesiccatorInfos(String eqipId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, keep_date, out_date, operate_by, fnc_user_nm(operate_by) operateBy_name,"
						+ " verify_by ,fnc_user_nm(verify_by) verify_name, remarks,"
						+ " record_date,record_time, qc_reference_id, is_verify,equipment_id,fnc_equipment_name(equipment_id) equipment_name, "
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos  WHERE id=a.qc_reference_id) arn_no "
						+ " from lims_log_desiccator_infos a  " + " where is_verify="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "is_verify")
						+ "  " + " and equipment_id="
						+ ((eqipId != null && !eqipId.isEmpty()) ? "'" + eqipId + "'" : "equipment_id") + ""
						+ " and  company_id = ? ORDER BY is_verify, record_date desc",
				new Object[] { user.getCompanyId() }, new DesiccatorInfosRowMapper());
	}

	public List<LogDryCabinetInfo> findDryCabinetInfos(String eqipId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, keep_date, out_date, operate_by, fnc_user_nm(operate_by) operateBy_name,"
						+ " verify_by ,fnc_user_nm(verify_by) verify_name, remarks, batch_no, "
						+ " record_date,record_time, qc_reference_id, is_verify,equipment_id,fnc_equipment_name(equipment_id) equipment_name, "
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos  WHERE id=a.qc_reference_id) arn_no "
						+ " from lims_log_dry_cabinet_infos a  " + " where is_verify="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "is_verify")
						+ "  " + " and equipment_id="
						+ ((eqipId != null && !eqipId.isEmpty()) ? "'" + eqipId + "'" : "equipment_id") + ""
						+ " and  company_id = ? ORDER BY is_verify, record_date desc",
				new Object[] { user.getCompanyId() }, new DryCabinetRowMapper());
	}

	public List<LogVerificationDissolutionInfo> findVerificationDissolutionInfos(String eqipId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(" SELECT id, qc_reference_id,  operate_by, fnc_user_nm(operate_by) operateBy_name,"
				+ " verify_by ,fnc_user_nm(verify_by) verify_name,remarks,  "
				+ " record_date,record_time, qc_reference_id, verify_status,equipment_id,fnc_equipment_name(equipment_id) equipment_name, "
				+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos  WHERE id=a.qc_reference_id) arn_no "
				+ " from lims_log_verification_dissolution_infos a  " + " where verify_status="
				+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "verify_status")
				+ "  " + " and equipment_id="
				+ ((eqipId != null && !eqipId.isEmpty()) ? "'" + eqipId + "'" : "equipment_id") + ""
				+ " and  company_id = ? ORDER BY verify_status, record_date desc", new Object[] { user.getCompanyId() },
				new VerificationDissolutionRowMapper());
	}
	
	public List<LogVerificationDissolutionChdInfo> getVerificationDissolutionChildInfos(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query("select id, master_id, vessel_no, temp_after_test, temp_before_test, sample_input_time, sample_withdrawal_time,  remarks "
				+ " from lims_log_verification_dissolution_chd_infos where master_id=? and company_id = ? ", new Object[] {id, user.getCompanyId() },
				new VerificationDissolutionChildRowMapper());
	}
	public void deleteVerificationDissolutionChdInfo(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM lims_log_verification_dissolution_chd_infos WHERE master_id = ?", new Object[] { id });
	  }
	public void saveDesiccatorInfo(LogDesiccatorInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_desiccator_infos (equipment_id, qc_reference_id, keep_date, out_date,record_date, record_time,  operate_by, remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?) ",
					info.getEquipmentId(), info.getQcReferenceId(), info.getKeepDate(), info.getOutDate(), date,
					recordTime, user.getId(), info.getRemarks(), user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_desiccator_infos SET equipment_id = ?,qc_reference_id = ?, keep_date = ?, out_date = ?, "
							+ " remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getEquipmentId(), info.getQcReferenceId(), info.getKeepDate(), info.getOutDate(),
					info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	public void saveDryCabinet(LogDryCabinetInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_dry_cabinet_infos (equipment_id, qc_reference_id, keep_date, out_date,record_date, record_time,  operate_by, remarks,batch_no, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getEquipmentId(), info.getQcReferenceId(), info.getKeepDate(), info.getOutDate(), date,
					recordTime, user.getId(), info.getRemarks(), info.getBatchNo(), user.getCompanyId(), user.getId(),
					time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_dry_cabinet_infos SET equipment_id = ?,qc_reference_id = ?, keep_date = ?, out_date = ?, "
							+ " remarks = ?, batch_no = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getEquipmentId(), info.getQcReferenceId(), info.getKeepDate(), info.getOutDate(),
					info.getRemarks(), info.getBatchNo(), user.getId(), time, info.getId());
		}
	}

	public void saveVerificationDissolution(LogVerificationDissolutionInfo info, UUID idRandom) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_verification_dissolution_infos (id,equipment_id, qc_reference_id, record_date, record_time,  operate_by, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?) ",
					idRandom, info.getEquipmentId(), info.getQcReferenceId(), date, recordTime, user.getId(),
					user.getCompanyId(), user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_verification_dissolution_infos SET equipment_id = ?,qc_reference_id = ?,  "
							+ "  updated_by = ?, updated_at = ? where id = ?",
					info.getEquipmentId(), info.getQcReferenceId(), user.getId(), time, info.getId());
		}
	}

	public List<LogAnalyticalBalanceInfo> findAnalyticalBalanceInfos(String equipmentId, String cleanStatus,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, sample_type_id, record_date, record_time, lot_no, qty, check_type,"
						+ " done_by, clean_by, verify_by, remarks, fnc_user_nm(done_by) doneBy_name,fnc_equipment_name(equipment_id) equipment_name, "
						+ " equipment_id,verify_status,clean_status, fnc_user_nm(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name,"
						+ " fnc_user_nm(clean_by) cleanBy_name, fnc_user_nm(verify_by) verifyBy_name, fnc_material_type_nm(sample_type_id) sample_type,  "
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_analytical_balance_infos a where" + " verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ "  " + " and clean_status="
						+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "clean_status")
						+ "  " + " and equipment_id="
						+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "equipment_id")
						+ "  " + " and company_id = ?  order by record_date desc ",

				new Object[] { user.getCompanyId() }, new AnalyticalBalanceInfoRowMapper());
	}

	public void saveAnalyticalBalanceInfo(LogAnalyticalBalanceInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		String recordTime = new SimpleDateFormat("hh:mm aa").format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_analytical_balance_infos ( qc_reference_id, sample_type_id, record_date, record_time, lot_no, qty, check_type,"
							+ " equipment_id, done_by, remarks, company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getQcRefId(), info.getSampleTypeId(), date, recordTime, info.getLotNo(), info.getQty(),
					info.getCheckType(), info.getEquipmentId(), user.getId(), info.getRemarks(), user.getCompanyId(),
					user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_analytical_balance_infos SET qc_reference_id =?, sample_type_id =?,  lot_no = ?, qty = ?, "
							+ " check_type =?,  equipment_id = ?, remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getQcRefId(), info.getSampleTypeId(), info.getLotNo(), info.getQty(), info.getCheckType(),
					info.getEquipmentId(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	public List<LogColumnRcvDistInfo> findColumnRcvDistInfos(String equipmentId, String isVerify,String isIssued, String isRecord, String isRecordVerify) {
		jdbcTemplate = new JdbcTemplate(datasource);

		User user = userService.getCurrentUser();
		return jdbcTemplate.query( 
				" SELECT cs.column_id, cs.column_name, cs.column_size, cs.composition, a.id, col_id, col_rcv_by, col_rcv_date, a.company_id, a.equipment_id, is_verify, issue_by, "
				+ " issue_date, part_no, rack_no, a.remarks, room_no, serial_no, update_at, update_by, verified_at, verify_by, is_issue, "
				+ " location_id, is_receive, expire_date, open_date, receive_by, receive_date,"
				+ "	theoritical_plate, fnc_user_nm(column_record_by) columnRecordByName ,column_record_status, column_record_at, "//New
				+ " is_record_verify , fnc_user_nm(record_verify_by) record_verify_by_name, "  //New
				+ " fnc_location_name(location_id) locationBy_name,fnc_user_nm(col_rcv_by) colRcvBy_name, fnc_user_nm(issue_by) issueBy_name, fnc_user_nm(receive_by) receiveBy_name, "
				+ " fnc_user_nm(verify_by) checkBy_name, fnc_equipment_name(a.equipment_id) equipmentByName,fnc_room_name(a.room_no) roomByName, fnc_rack_name(a.rack_no) rackNoByName ,column_id_new"
				+ " from lims_log_column_rcv_dist_infos a JOIN lims_column_setup_infos cs ON a.col_id=cs.id where "
				+ " equipment_id="+ ((equipmentId != null && !equipmentId.isEmpty()) ? "'" + equipmentId + "'" : "a.equipment_id") 
				+ " and is_verify="+ ((isVerify != null && !isVerify.isEmpty()) ? "'" + isVerify + "'" : " a.is_verify ") 
				+ " and is_issue="+ ((isIssued != null && !isIssued.isEmpty()) ? "'" + isIssued + "'" : " a.is_issue ") 
				+ " and column_record_status="+ ((isRecord != null && !isRecord.isEmpty()) ? "'" + isRecord + "'" : " a.column_record_status ") 
				+ " and is_record_verify="+ ((isRecordVerify != null && !isRecordVerify.isEmpty()) ? "'" + isRecordVerify + "'" : " a.is_record_verify ") 
				+ " and a.company_id = ? ",
				new Object[] { user.getCompanyId() }, new ColumnRcvDistInfosRowMapper());
	}
	
	public List<LogColumnPerformanceRecordInfo> findColumnPerformancePendingInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query( 
				"  select id,column_id, equipmentBy_Name , arn_name, column_name_new, record_date, equipment_id,"
				+ " qc_ref_id from view_column_performance_pending a  where not exists"
				+ " (select performance_id from lims_log_column_performance_record_infos where a.id=performance_id) "
				+ " order by record_date desc ",// and  is_verify='Y' 
				new Object[] {}, new ColumnPerformancePendingRowMapper());
	}
	public List<LogColumnPerformanceRecordInfo> findColumnPerformanceRecordsInfos(String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query( 
				 " select b.id,a.column_id, equipmentBy_Name , arn_name, column_name_new, record_date, equipment_id, qc_ref_id,performance_id, tp, tf, rsd, is_verify,"
				 + "  remarks from view_column_performance_pending a,lims_log_column_performance_record_infos b where a.id=b.performance_id  "
				 + " and is_verify=" + ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"	: " is_verify "),
				 new Object[] {}, new ColumnPerformanceRecordRowMapper());
	}

	public List<LogElectricOvenInfo> electricOvenVerifyList() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, record_date, record_time,  done_by, verify_by, remarks, equipment_id, is_verify, temperature ,start_time, end_time, "
						+ " fnc_user_nm(done_by) doneByName, fnc_equipment_name(equipment_id) equipmentBy_Name, fnc_user_nm(verify_by) verifyBy_name,"
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name"
						+ " from lims_log_electric_oven_infos a where company_id = ? ",

				new Object[] { user.getCompanyId() }, new ElectricOvenInfoRowMapper());
	}
	
	public void addColumnOpenExpire(LogColumnRcvDistInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
			jdbcTemplate.update(
					"UPDATE lims_log_column_rcv_dist_infos SET open_date = ?, expire_date = ? where id = ?",
						info.getOpenDate(), info.getExpireDate(), info.getId());
		
	}
	
	
	public void issueColumnReceiveDistribute(LogColumnRcvDistInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

			jdbcTemplate.update(
					"UPDATE lims_log_column_rcv_dist_infos SET location_id =?, issue_by =?, issue_date =?, is_issue =?, is_receive =? where id = ?",
						info.getLocationId(), user.getId(), time, 'Y', 'N', info.getId());
		
	}
	
	public void columnRecord(LogColumnRcvDistInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
			jdbcTemplate.update(
					"UPDATE lims_log_column_rcv_dist_infos SET theoritical_plate =?, column_record_by =?,column_record_status =?, column_record_at =? where id = ?",
					info.getTheoriticalPlate(), user.getId(), 'Y', time, info.getId());
	}
	
	public void saveColumnPerformancePending(LogColumnPerformanceRecordInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
			jdbcTemplate.update(
					"INSERT INTO lims_log_column_performance_record_infos (performance_id,  tp, tf, rsd,"
							+ "   remarks, company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?) ",
					info.getId(), info.getTp(),
					info.getTf(), info.getRsd(), info.getRemarks(), user.getCompanyId(),
					user.getId(), time);
	      }
	public void saveComparativeDissolutionStudyPending(LogComparativeDissolutionDutyInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		if(info.getFlag().equals("V")) {
			jdbcTemplate.update(
					"UPDATE lims_log_comparative_dissolution_study_infos SET analyst_by =?,"
							+ "   distributed_by = ?, distributed_at = ?, distribute_status = ? where id = ?",
						info.getAnalystBy(),user.getId(), time,'Y', info.getId());
		}else {
			jdbcTemplate.update(
					"UPDATE lims_log_comparative_dissolution_study_infos SET analyst_by =?,"
							+ "   updated_by = ?, updated_at = ?  where id = ?",
						info.getAnalystBy(),user.getId(), time, info.getId());
			
		}
		
	      }
	
	public void saveComparativeDissolutionStudyResultEntry(LogComparativeDissolutionDutyInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		if(info.getFlag().equals("V")) {
		jdbcTemplate.update(
				"UPDATE lims_log_comparative_dissolution_study_infos SET result_comp =?, remarks =?,"
						+ "   result_by = ?, result_at = ?, result_status = ? where id = ?",
					info.getResultComp(),info.getRemarks(), user.getId(), time,'Y', info.getId());
		}else {
			jdbcTemplate.update(
					"UPDATE lims_log_comparative_dissolution_study_infos SET result_comp =?, remarks =?,"
							+ "   updated_by = ?, updated_at = ?  where id = ?",
						info.getResultComp(),info.getRemarks(),user.getId(), time, info.getId());
			
		}
	      }
	public void SaveComparativeDissolutionStudy(LogComparativeDissolutionDutyInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
			if (info.getId() == null) {
						jdbcTemplate.update(
								"INSERT INTO lims_log_comparative_dissolution_study_infos (product_name,  batch_no, manufacturer_id, "
										+ "    company_id, created_by, created_at) VALUES (?,?,?,?,?,?) ",
								info.getProductName(), info.getBatchNo(),
								info.getManufacturerId(), user.getCompanyId(),
								user.getId(), time);
						} else {
				jdbcTemplate.update(
						"UPDATE lims_log_comparative_dissolution_study_infos SET product_name =?, batch_no =?, manufacturer_id =?, "
								+ "  company_id = ?, updated_by = ?, updated_at = ? where id = ?",
							info.getProductName(), info.getBatchNo(), info.getManufacturerId(),  user.getCompanyId(), user.getId(), time, info.getId());
			}
	      }
	
	
	
	public void updateColumnRecord(LogColumnRcvDistInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
			jdbcTemplate.update(
					"UPDATE lims_log_column_rcv_dist_infos SET theoritical_plate =?, update_by =?, update_at =? where id = ?",
					info.getTheoriticalPlate(), user.getId(), time, info.getId());
	}
	public void updateColumnPerformanceRecord(LogColumnPerformanceRecordInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
			jdbcTemplate.update(
					"UPDATE lims_log_column_performance_record_infos SET tp =?,tf =?,rsd =?,remarks =?, update_by =?, update_at =? where id = ?",
					info.getTp(),info.getTf(),info.getRsd(),info.getRemarks(), user.getId(), time, info.getId());
	}
	
	
	public void issueColumnReceiveDistributeAdd(LogColumnRcvDistInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
			jdbcTemplate.update(
					"UPDATE lims_log_column_rcv_dist_infos SET is_receive =?, col_rcv_by =?, col_rcv_date =? where id = ?",
						'Y', user.getId(), time, info.getId());
		
	}

	public void saveColumnRcvDistInfo(LogColumnRcvDistInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			
			jdbcTemplate.update(
					"INSERT INTO lims_log_column_rcv_dist_infos (is_receive,col_id, equipment_id, is_issue, part_no, rack_no, remarks, room_no, serial_no, is_verify,column_id_new, company_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ",
									'N', info.getColId(), info.getEquipmentId(), 'N', info.getPartNo(), info.getRackNo(), info.getRemarks(), info.getRommNo(), info.getSerialNo(),'N' , info.getColumnIdNew(), user.getCompanyId());
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_column_rcv_dist_infos SET col_id =?, equipment_id =?, part_no =?, rack_no =?, remarks = ?,"
							+ "column_id_new = ?, room_no = ?, serial_no = ?, company_id = ?, update_by = ?, update_at = ? where id = ?",
						info.getColId(), info.getEquipmentId(), info.getPartNo(), info.getRackNo(), info.getRemarks(), 
						info.getColumnIdNew(), info.getRommNo(), info.getSerialNo(), user.getCompanyId(), user.getId(), time, info.getId());
		}
	}

	public List<LogColumnInfo> findColumnInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, col_id, col_name, col_size, composition, part_no, serial_no, location, theore_plate,"
						+ " open_date, expire_date, done_by, verify_by, remarks, "
						+ " fnc_emp_name(done_by) doneBy_name, fnc_emp_name(verify_by) verifyBy_name "
						+ " from lims_log_column_infos where company_id = ? ",
				new Object[] { user.getCompanyId() }, new ColumnInfosRowMapper());
	}

	public void saveColumnInfo(LogColumnInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_column_infos ( col_id, col_name, col_size, composition, part_no, serial_no, location, theore_plate,"
							+ " open_date, expire_date, done_by, verify_by, remarks, "
							+ " company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					info.getColId(), info.getColName(), info.getColSize(), info.getComposition(), info.getPartNo(),
					info.getSerialNo(), info.getLocation(), info.getTheorePlate(), info.getOpenDate(),
					info.getExpireDate(), info.getDoneBy(), info.getVerifyBy(), info.getRemarks(), user.getCompanyId(),
					user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_column_infos SET col_id =?, col_name =?, col_size =?, composition =?, part_no = ?,"
							+ " serial_no = ?, location = ?, theore_plate = ?, open_date = ?, expire_date = ?, "
							+ " done_by = ?, verify_by = ?, remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getColId(), info.getColName(), info.getColSize(), info.getComposition(), info.getPartNo(),
					info.getSerialNo(), info.getLocation(), info.getTheorePlate(), info.getOpenDate(),
					info.getExpireDate(), info.getDoneBy(), info.getVerifyBy(), info.getRemarks(), user.getId(), time,
					info.getId());
		}
	}

	public List<LogTempHumidityInfo> findTempHumidityInfos() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, before_temp, after_temp, record_date, record_time, rel_humidity, done_by, verify_by, remarks, "
						+ " fnc_emp_name(done_by) doneBy_name, fnc_emp_name(verify_by) verifyBy_name"
						+ " from lims_log_temp_humidity_infos  where company_id = ? ",
				new Object[] { user.getCompanyId() }, new TempHumidityInfoRowMapper());
	}

	public void saveTempHumidityInfo(LogTempHumidityInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_temp_humidity_infos ( before_temp, after_temp, record_date, record_time, rel_humidity, "
							+ " done_by, verify_by, remarks, company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?) ",
					info.getBeforeTemp(), info.getAfterTemp(), info.getRecordDate(), info.getRecordTime(),
					info.getRelHumidity(), info.getDoneBy(), info.getVerifyBy(), info.getRemarks(), user.getCompanyId(),
					user.getId(), time);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_temp_humidity_infos SET before_temp =?, after_temp = ?, record_date =?, record_time =?, rel_humidity =?, "
							+ " done_by =?, verify_by = ?, remarks = ?, updated_by = ?, updated_at = ? where id = ?",
					info.getBeforeTemp(), info.getAfterTemp(), info.getRecordDate(), info.getRecordTime(),
					info.getRelHumidity(), info.getDoneBy(), info.getVerifyBy(), info.getRemarks(), user.getId(), time,
					info.getId());
		}
	}

	public List<LogTempeHumidityRecordInfo> getTempHumidityRecordVerifiedPendingInfos(String eqipId,
			String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT a.id, after_relative_humidity, after_temp, before_relative_humidity, before_temp, done_by, a.equipment_id, st.department_id, st.locaton_id, is_verify, record_date, record_time, remarks, verified_at, verify_by,"
						+ " fnc_user_nm(done_by) doneByName, fnc_dept_nm(st.department_id) departmentByName, fnc_location_name(st.locaton_id) locationByName, fnc_equipment_name(a.equipment_id) equipmentBy_Name, fnc_user_nm(verify_by) verifyBy_name "
						+ "from lims_log_temp_humidity_record_infos a JOIN lims_temp_humidity_setup_infos st ON a.equipment_id=st.equipment_id where is_verify="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "is_verify")
						+ "  " + " and a.equipment_id="
						+ ((eqipId != null && !eqipId.isEmpty()) ? "'" + eqipId + "'" : "a.equipment_id") + ""
						+ " and  a.company_id = ? ORDER BY is_verify, record_date desc",
				new Object[] { user.getCompanyId() }, new tempeHumidityRecordInfoRowMapper());
	}

	public List<LogRefrigeratorInfo> getRefrigeratorVerifiedPendingInfos(String eqipId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, qc_reference_id, sample_name_id, sample_type_id, qty, in_date, out_date, discard_date, equipment_id, discard_qty, discard_by, verify_by, remarks,"
						+ " unit_id, fnc_unit_nm(unit_id) unit_name, dis_unit_id, fnc_unit_nm(dis_unit_id) dis_unit_name,"
				        + " material_id, fnc_material_nm (material_id)  material_name,"
		                + " fnc_user_nm (verify_by) verifyBy_name, fnc_user_nm(created_by) operateBy_name, fnc_user_nm(discard_by) discardBy_name, fnc_surgical_name(sample_name_id) sample_name, fnc_material_type_nm(sample_type_id) sample_type, fnc_equipment_name(equipment_id) equipmentByName, verified_at, is_verify, qc_reference_id, "
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name "
						+ "from lims_log_refrigerator_infos a" + " where is_verify="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "is_verify")
						+ "  " + " and equipment_id="
						+ ((eqipId != null && !eqipId.isEmpty()) ? "'" + eqipId + "'" : "equipment_id") + ""
						+ " and  company_id = ? ORDER BY is_verify, in_date desc",
				new Object[] { user.getCompanyId() }, new RefrigeratorInfoRowMapper());
	}

	public List<LogElectricOvenInfo> getElectricOvenVerifiedPendingInfos(String eqipId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, equipment_id, fnc_equipment_name(equipment_id) equipmentBy_Name, remarks, fnc_user_nm (verify_by) verifyBy_name, fnc_user_nm(done_by) doneByName, fnc_user_nm(created_by) operateBy_name, verify_by, verified_at, is_verify, record_date, record_time, qc_reference_id, done_by, temperature, start_time, end_time, "
						+ " (SELECT arn_no FROM lims_qc_sample_rcv_infos b WHERE b.id=a.qc_reference_id) reference_name "
						+ "from lims_log_electric_oven_infos a" + " where is_verify="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "is_verify")
						+ "  " + " and equipment_id="
						+ ((eqipId != null && !eqipId.isEmpty()) ? "'" + eqipId + "'" : "equipment_id") + ""
						+ " and  company_id = ? ORDER BY is_verify, record_date desc",
				new Object[] { user.getCompanyId() }, new ElectricOvenInfoRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogWaterPurification(String eqipId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, equipment_id, fnc_equipment_name(equipment_id) equipment_name, quantity, unit_id, fnc_unit_nm(unit_id) unit_name, remarks, "
				+ "fnc_user_nm (dispensed_by) dispensed_by, fnc_user_nm (verify_by) verify_by,verified_at,is_verify, record_date,record_time "
						+ "from lims_log_water_purification_infos " + " where is_verify="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "is_verify")
						+ "  " + " and equipment_id="
						+ ((eqipId != null && !eqipId.isEmpty()) ? "'" + eqipId + "'" : "equipment_id") + ""
						+ " and  company_id = ? ORDER BY is_verify, record_date desc",
				new Object[] { user.getCompanyId() }, new LogWaterPurificationRowMapper());
	}

	public List<LogAnalystValidationInfo> findLogAnalystValidation(String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, analyst_id,ud_emp_id, area_of_competency,date_of_competency ,next_date_of_competency, status, fnc_emp_name(analyst_id) analyst_name , "
						+ " product_id, (SELECT material_name FROM lims_material_infos b WHERE b.id=a.product_id) product_name ,"
						+ " remarks, fnc_user_nm (dispensed_by) dispensed_by, fnc_user_nm (verify_by) verify_by,verified_at,is_verify, record_date,record_time "
						+ " from lims_log_analyst_validation_infos a " + " where is_verify="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "is_verify")
						+ " and  company_id = ? ORDER BY is_verify, record_date desc",
				new Object[] { user.getCompanyId() }, new LogAnalystValidationRowMapper());
	}

	public List<LogWaterSamplingInfo> findLogWaterSamplingInfo(String eqipId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id, sample_id, remarks, fnc_user_nm (dispensed_by) dispensed_by, fnc_user_nm (verify_by) verify_by,verified_at,"
				        + " is_verify, record_date,record_time,  sample_id,location_id, water_type_id, "
						+ " (SELECT location_name FROM lims_location_setup_infos b WHERE b.id=a.location_id) location_name ,"
						+ " (SELECT type_name FROM lims_water_type_infos b WHERE b.id=a.water_type_id) water_type_Name "
						+ " from lims_log_water_sampling_infos a " + " where is_verify="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "is_verify")
						+ " and  company_id = ? ORDER BY is_verify, record_date desc",
				new Object[] { user.getCompanyId() }, new LogWaterSamplingRowMapper());
	}
	public List<LogComparativeDissolutionDutyInfo> findLogComparativeDissolutionDutyInfo( String distribute_status ,String resulStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select id,product_name,batch_no,manufacturer_id, remarks, analyst_by, "
			    + " fnc_manufac_name(manufacturer_id) manufacturer_Name ,"
			    + " fnc_user_nm(result_by) result_by_name ,result_status, result_comp,"
			    + " analyst_by, fnc_emp_name(analyst_by) analyst_name ,"
				+ " distributed_by, fnc_user_nm(distributed_by) distributed_by_nm,distributed_at, distribute_status ,"
				+ " fnc_user_nm(created_by) createdByName "
				+ " from lims_log_comparative_dissolution_study_infos a where "  
				+ "  distribute_status="+ ((distribute_status != null && !distribute_status.isEmpty()) ? "'" + distribute_status + "'" : "distribute_status")
				+ "  and result_status="+ ((resulStatus != null && !resulStatus.isEmpty()) ? "'" + resulStatus + "'" : "result_status ")
				+ " and  company_id = ? ORDER BY distribute_status, created_at desc",
				new Object[] { user.getCompanyId() }, new LogComparativeDissolutionDutyRowMapper());
	}

	public List<LogAllottedSampleInfo> findLogAllottedSampleInfos(String eqipId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id,  remarks, fnc_user_nm (dispensed_by) dispensed_by, record_date,record_time, analyst_id,"
				+ "  document_code, batch_no,  fnc_emp_name(analyst_id) analyst_name , test_method_name , product_name "
				+ " from lims_log_allotted_sample_infos a "
				+ " where company_id = ? ORDER BY  record_date desc",
				new Object[] { user.getCompanyId() }, new LogAllottedSampleRowMapper());
	}
	public List<LogOutofTrend> findLogOutofTrend(String eqipId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT id,  record_date,record_time, product_id,  batch_no,oot_observation_date , "
						+ " investigation_date ,class_out_date ,oot_no ,test,  "
						+ " (SELECT material_name FROM lims_material_infos b WHERE b.id=a.product_id) product_name "
						+ " from lims_log_out_of_trend_infos a " + " where "
						+ "  company_id = ? ORDER BY  record_date desc",
				new Object[] { user.getCompanyId() }, new LogOutofTrendRowMapper());
	}

	public List<LogDeHumidifierInfo> findLogDeHumidifier(String eqipId,String cleanStatus, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, equipment_id, fnc_equipment_name(equipment_id) equipment_name, remarks,dispensed_by,"
				+ " fnc_emp_name (dispensed_by) dispensed_by_name, fnc_user_nm (verify_by) verify_by,"
				+ " is_clean,clean_by, fnc_emp_name (clean_by) clean_by_name, verified_at,is_verify, record_date,record_time "
						+ " from lims_log_de_humidifier_infos " + " where "
						+ " is_clean="	+ ((cleanStatus != null && !cleanStatus.isEmpty()) ? "'" + cleanStatus + "'" : "is_clean")
						+ " and is_verify="	+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "is_verify")
						+ " and equipment_id="
						+ ((eqipId != null && !eqipId.isEmpty()) ? "'" + eqipId + "'" : "equipment_id") + ""
						+ " and  company_id = ? ORDER BY is_verify, record_date desc",
				new Object[] { user.getCompanyId() }, new LogDeHumidifierRowMapper());
	}

	public List<LogTOCInfo> findLogTOCInfos(String eqipId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, equipment_id, fnc_equipment_name(equipment_id) equipment_name, remarks, fnc_user_nm (dispensed_by) dispensed_by, fnc_user_nm (verify_by) verify_by,verified_at,is_verify, record_date,record_time,"
						+ " batch_no ,toc_result, sample_name  "
						+ " from lims_log_toc_infos " + " where is_verify="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "is_verify")
						+ "  " + " and equipment_id="
						+ ((eqipId != null && !eqipId.isEmpty()) ? "'" + eqipId + "'" : "equipment_id") + ""
						+ " and  company_id = ? ORDER BY is_verify, record_date desc",
				new Object[] { user.getCompanyId() }, new LogTOCRowMapper());
	}

	public List<LogSampleLabelInfo> findLogSampleLabelInfos( String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, remarks, fnc_user_nm (dispensed_by) dispensed_by, fnc_user_nm (verify_by) verify_by,verified_at,is_verify,"
				       + " record_date,record_time, unit_id, fnc_unit_nm(unit_id) unit_name,"
						+ " batch_no ,qty, sample_name_id, fnc_material_nm(sample_name_id) sample_name "
						+ " from lims_log_Sample_label_control_infos where is_verify="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'" : "is_verify")
					//	+ "  " + " and equipment_id="		+ ((eqipId != null && !eqipId.isEmpty()) ? "'" + eqipId + "'" : "equipment_id") + ""
						+ " and  company_id = ? ORDER BY is_verify, record_date desc",
				new Object[] { user.getCompanyId() }, new LogSampleLabelRowMapper());
	}

	public List<LogAtomicAbsorptionInfo> findLogAtomicAbsorptionInfos(String eqipId, String verifyStatus) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" select id, equipment_id, fnc_equipment_name(equipment_id) equipment_name, sample_name_id, fnc_material_nm(sample_name_id) sample_name,"
						+ " record_date, record_time, lot_no,batch_no,hno3_no,h2o_no,first_time,second_time,total_analyst_time,verify_status ,fnc_user_nm(operate_by) operate_by_name,"
						+ " remarks, fnc_user_nm (verify_by) verify_by from lims_log_absorption_spectrometer_infos where verify_status="
						+ ((verifyStatus != null && !verifyStatus.isEmpty()) ? "'" + verifyStatus + "'"
								: "verify_status")
						+ " and equipment_id="
						+ ((eqipId != null && !eqipId.isEmpty()) ? "'" + eqipId + "'" : "equipment_id") + ""
						+ " and  company_id = ? ORDER BY verify_status, record_date desc",
				new Object[] { user.getCompanyId() }, new LogAtomicAbsorptionRowMapper());
	}

	public List<LogWaterPurificationInfo> findLogWaterPurificationWithParam(String equipmentId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		System.out.println("=== :" + equipmentId);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"SELECT id, equipment_id, fnc_equipment_name(equipment_id) equipment_name, quantity, unit_id, fnc_unit_nm(unit_id) unit_name, remarks, fnc_user_nm (dispensed_by) dispensed_by, fnc_user_nm (verify_by) verify_by,verified_at,is_verify, record_date,record_time "
						+ "from lims_log_water_purification_infos where company_id = ? ORDER BY is_verify, record_date desc",
				new Object[] { user.getCompanyId() }, new LogWaterPurificationRowMapper());
	}

	public void saveWaterPurification(LogWaterPurificationInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm aa");
		String recordTime = formatterTime.format(date);
		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_water_purification_infos ( equipment_id, quantity, unit_id,"
							+ " dispensed_by, remarks, company_id, created_by, created_at, record_date,record_time) "
							+ " VALUES (?,?,?,?,?,?,?,?,?,?)",
					info.getEquipmentId(), info.getQuantity(), info.getUnitId(), user.getId(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time, date, recordTime);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_water_purification_infos SET equipment_id =?, quantity=?, unit_id=?,"
							+ "   dispensed_by =?, remarks =?, updated_by = ?, updated_at = ? where id = ?",
					info.getEquipmentId(), info.getQuantity(), info.getUnitId(), user.getId(), info.getRemarks(),
					user.getId(), time, info.getId());
		}
	}

	public void saveAnalystValidation(LogAnalystValidationInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm aa");
		String recordTime = formatterTime.format(date);
		if (info.getId() == null) {
			jdbcTemplate.update("INSERT INTO lims_log_analyst_validation_infos (analyst_id,ud_emp_id, area_of_competency, date_of_competency, next_date_of_competency, status,"
							+ " dispensed_by, remarks, company_id, created_by, created_at, record_date,record_time) "
							+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
					info.getAnalystId(), info.getUdAnalystId(), info.getAreaOfCompetency(), info.getDateOfCompetency(),
					info.getNextDateOfCompetency(), info.getStatus(), user.getId(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time, date, recordTime);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_analyst_validation_infos SET  analyst_id =?, ud_emp_id =?, area_of_competency=?, date_of_competency=?,next_date_of_competency=?,status=?,"
							+ "   dispensed_by =?, remarks =?, updated_by = ?, updated_at = ? where id = ?",
					info.getAnalystId(), info.getUdAnalystId(), info.getAreaOfCompetency(), info.getDateOfCompetency(),
					info.getNextDateOfCompetency(), info.getStatus(), user.getId(), info.getRemarks(), user.getId(),
					time, info.getId());
		}
	}

	public void saveWaterSampling(LogWaterSamplingInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm aa");
		String recordTime = formatterTime.format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_water_sampling_infos ( location_id,water_type_id, sample_id, "
							+ " dispensed_by, remarks, company_id, created_by, created_at, record_date,record_time) "
							+ " VALUES (?,?,?,?,?,?,?,?,?,?)",
					info.getLocationId(), info.getWaterTypeId(), info.getSampleId(), user.getId(), info.getRemarks(),
					user.getCompanyId(), user.getId(), time, date, recordTime);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_water_sampling_infos SET location_id =?, water_type_id =?, sample_id=?, "
							+ "   dispensed_by =?, remarks =?, updated_by = ?, updated_at = ? where id = ?",
					info.getLocationId(), info.getWaterTypeId(), info.getSampleId(), user.getId(), info.getRemarks(),
					user.getId(), time, info.getId());
		}
	}

	public void saveAlloteeSample(LogAllottedSampleInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm aa");
		String recordTime = formatterTime.format(date);

		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_allotted_sample_infos (analyst_id,product_name, test_method_name,document_code,batch_no, "
							+ " dispensed_by, remarks, company_id, created_by, created_at, record_date,record_time) "
							+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
					info.getAnalystId(), info.getProductName(), info.getTestMethodName(), info.getDocumentCode(),
					info.getBatchNo(), user.getId(), info.getRemarks(), user.getCompanyId(), user.getId(), time, date,
					recordTime);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_allotted_sample_infos SET analyst_id =?, product_name =?, test_method_name=?, "
							+ "   document_code =?, batch_no =?, "
							+ "   remarks =?, updated_by = ?, updated_at = ? where id = ?",
					info.getAnalystId(),info.getProductName(), info.getTestMethodName(), info.getDocumentCode(),
					info.getBatchNo(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}
	
	public void saveOutOfTrend(LogOutofTrend info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm aa");
		String recordTime = formatterTime.format(date);

		if (info.getId() == null) {jdbcTemplate.update(
					"INSERT INTO lims_log_out_of_trend_infos (product_id, batch_no, oot_observation_date ,"
					+ "investigation_date ,class_out_date ,oot_no ,test,  "
					+ "  company_id, created_by, created_at, record_date,record_time) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)",
					info.getProductId(), info.getBatchNo(), info.getOotObservationDate(), info.getInvestigationDate(),
					info.getClassOutDate(), info.getOotNo(), info.getTest(), user.getCompanyId(), user.getId(), time, date,
					recordTime);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_out_of_trend_infos SET batch_no =?, product_id =?, oot_observation_date=?, "
							+ "   investigation_date =?, oot_no =?, test =?, "
							+ "   class_out_date =?, updated_by = ?, updated_at = ? where id = ?",
					info.getBatchNo(), info.getProductId(), info.getOotObservationDate(), info.getInvestigationDate(),
					info.getOotNo(),info.getTest(), info.getClassOutDate(), user.getId(), time, info.getId());
		}
	}

	public void saveDeHumidifier(LogDeHumidifierInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm aa");
		String recordTime = formatterTime.format(date);
		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_de_humidifier_infos ( equipment_id,  "
							+ " dispensed_by, remarks, company_id, created_by, created_at, record_date,record_time) "
							+ " VALUES (?,?,?,?,?,?,?,?)",
					info.getEquipmentId(), user.getId(), info.getRemarks(), user.getCompanyId(), user.getId(), time,
					date, recordTime);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_de_humidifier_infos SET equipment_id =?,"
							+ "   dispensed_by =?, remarks =?, updated_by = ?, updated_at = ? where id = ?",
					info.getEquipmentId(), user.getId(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}
	public void cleanDeHumidifierInfo(LogDeHumidifierInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_de_humidifier_infos SET  is_clean =?, clean_by = ?, cleaned_at = ? where id = ?",
				'Y', info.getCleanBy(), time, info.getId());
	}

	public void saveTOC(LogTOCInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm aa");
		String recordTime = formatterTime.format(date);
		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_toc_infos ( equipment_id,sample_name, toc_result,batch_no, "
							+ " dispensed_by, remarks, company_id, created_by, created_at, record_date,record_time) "
							+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)",
					info.getEquipmentId(), info.getSampleName(), info.getResult(), info.getBatchNo(), user.getId(),
					info.getRemarks(), user.getCompanyId(), user.getId(), time, date, recordTime);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_toc_infos SET equipment_id =?,sample_name =?,toc_result =?,batch_no =?,"
							+ "   dispensed_by =?, remarks =?, updated_by = ?, updated_at = ? where id = ?",
					info.getEquipmentId(), info.getSampleName(), info.getResult(), info.getBatchNo(), user.getId(),
					info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	public void saveSampleLabel(LogSampleLabelInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm aa");
		String recordTime = formatterTime.format(date);
		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_Sample_label_control_infos ( sample_name_id, qty, unit_id , batch_no, "
							+ " dispensed_by, remarks, company_id, created_by, created_at, record_date,record_time) "
							+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)",
					info.getSampleNameId(), info.getQty(),info.getUnitId(), info.getBatchNo(), user.getId(),
					info.getRemarks(), user.getCompanyId(), user.getId(), time, date, recordTime);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_Sample_label_control_infos SET sample_name_id =?,qty =?,unit_id =?, batch_no =?,"
							+ "   dispensed_by =?, remarks =?, updated_by = ?, updated_at = ? where id = ?",
					 info.getSampleNameId(), info.getQty(),info.getUnitId(), info.getBatchNo(), user.getId(),
					info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	public void verifyUvSpectroPhotoMeter(LogSpectrophotometerInfo info) {

		 
					
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_spectrophotometer_infos SET is_verify=?,remarks =?, verify_by=?, verified_at=? WHERE id=? ", 'Y',
				info.getRemarks(),user.getId(), time, info.getId());
	}

	public void saveAtomicAbsorption(LogAtomicAbsorptionInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		SimpleDateFormat formatterTime = new SimpleDateFormat("hh:mm aa");
		String recordTime = formatterTime.format(date);
		if (info.getId() == null) {
			jdbcTemplate.update(
					"INSERT INTO lims_log_absorption_spectrometer_infos ( equipment_id, sample_name_id, lot_no,batch_no,"
							+ " hno3_no, h2o_no, first_time, second_time, total_analyst_time ,"
							+ " operate_by, remarks, company_id, created_by, created_at, record_date,record_time) "
							+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					info.getEquipmentId(), info.getSampleNameId(), info.getLotNo(), info.getBatchNo(), info.getHno3No(),
					info.getH2oNo(), info.getFirstTime(), info.getSecondTime(), info.getTotalAnalystTime(),
					user.getId(), info.getRemarks(), user.getCompanyId(), user.getId(), time, date, recordTime);
		} else {
			jdbcTemplate.update(
					"UPDATE lims_log_absorption_spectrometer_infos SET equipment_id =?,sample_name_id =?,lot_no =?,batch_no =?,"
							+ "  hno3_no =?, h2o_no =?, first_time = ?, second_time = ?,total_analyst_time = ?, "
							+ "   operate_by =?, remarks =?, updated_by = ?, updated_at = ? where id = ?",
					info.getEquipmentId(), info.getSampleNameId(), info.getLotNo(), info.getBatchNo(), info.getHno3No(),
					info.getH2oNo(), info.getFirstTime(), info.getSecondTime(), info.getTotalAnalystTime(),
					user.getId(), info.getRemarks(), user.getId(), time, info.getId());
		}
	}

	public void verifyWaterPurification(LogWaterPurificationInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_water_purification_infos SET  is_verify =?,remarks =?, verify_by = ?, verified_at = ? where id = ?",
				'Y',info.getRemarks(), user.getId(), time, info.getId());

	}

	public void verifyAanalystValidation(LogAnalystValidationInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_analyst_validation_infos SET  is_verify =?, verify_by = ?, verified_at = ?,remarks =? where id = ?",
				'Y', user.getId(), time,info.getRemarks(), info.getId());

	}
	public void verifyColumnRecord(LogColumnRcvDistInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_column_rcv_dist_infos SET  is_record_verify =?, record_verify_by = ?, record_verified_at = ? where id = ?",
				'Y', user.getId(), time, info.getId());

	}
	public void verifyColumnPerformanceRecord(LogColumnPerformanceRecordInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_column_performance_record_infos SET  is_verify =?, verify_by = ?, verified_at = ?,remarks =? where id = ?",
				'Y', user.getId(), time,info.getRemarks(), info.getId());

	}

	public void verifyWaterSamplingInfo(LogWaterSamplingInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_water_sampling_infos SET  is_verify =?, verify_by = ?, verified_at = ?,remarks =? where id = ?",
				'Y', user.getId(), time,info.getRemarks(), info.getId());

	}

	public void verifyDeHumidifierInfo(LogDeHumidifierInfo info) {

	    jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_de_humidifier_infos SET  is_verify =?,remarks =?, verify_by = ?, verified_at = ? where id = ?",
				'Y',info.getRemarks(), user.getId(), time, info.getId());

	}

	public void verifyTOCInfo(LogTOCInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update("UPDATE lims_log_toc_infos SET  is_verify =?,remarks =?, verify_by = ?, verified_at = ? where id = ?",
				'Y',info.getRemarks(), user.getId(), time, info.getId());

	}

	public void verifySampleLabelInfo(LogSampleLabelInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_Sample_label_control_infos SET  is_verify =?, verify_by = ?, verified_at = ?,remarks =? where id = ?",
				'Y', user.getId(), time,info.getRemarks(), info.getId());

	}

	public void verifyAbsorptionSpectrometerInfo(LogAtomicAbsorptionInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_absorption_spectrometer_infos SET  verify_status =?, verify_by = ?, verify_at = ?,remarks =? where id = ?",
				'Y', user.getId(), time,info.getRemarks(), info.getId());

	}

	public void verifyAreaCleaningInfo(LogAreaCleanInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_area_cleaning_infos SET  is_verify =?, verify_by = ?, verified_at = ?,remarks =? where id = ?",
				'Y', user.getId(), time,info.getRemarks(), info.getId());

	}

	public void verifyDesiccator(LogDesiccatorInfo info) {
		 
					
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_desiccator_infos SET  is_verify =?,remarks =?, verify_by = ?, verified_at = ? where id = ?", 'Y',
				info.getRemarks(),user.getId(), time, info.getId());

	}

	public void verifyDryCabinet(LogDryCabinetInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_dry_cabinet_infos SET  is_verify =?, remarks =?, verify_by = ?, verified_at = ? where id = ?", 'Y',
				info.getRemarks(),user.getId(), time, info.getId());

	}

	public void verifyVerificationDissolution(LogVerificationDissolutionInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_verification_dissolution_infos SET  verify_status =?, verify_by = ?, verify_at = ? where id = ?",
				'Y', user.getId(), time, info.getId());

	}

	public void verifyHPLC(LogHplcInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_log_hplc_infos SET  verify_status =?, verified_by_id = ?, verify_at = ?,remarks =? where id = ?", 'Y',
				user.getId(), time,info.getRemarks(), info.getId());

	}
	public void verifySampleReceiving(CommonInfo info) {

		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update(
				"UPDATE lims_qc_sample_rcv_infos SET  is_verify =?, verified_by = ?, verify_at = ? where id = ?", 'Y',
				user.getId(), time, info.getId());

	}

	public List<CommonInfo> findDateRangeList() {
		jdbcTemplate = new JdbcTemplate(datasource);
		String sqlo = "Select prm_id,prm_nm,TO_CHAR(fd, 'dd-MMM-yyyy') as fd,TO_CHAR(ld, 'dd-MMM-yyyy') as ld  from view_duration_infos order by prm_id ";

		return jdbcTemplate.query(sqlo, new RowMapper<CommonInfo>() {
			public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				CommonInfo info = new CommonInfo();
				info.setPrmId(rs.getString("prm_id"));
				info.setPrmNm(rs.getString("prm_nm"));
				info.setFd(rs.getString("fd"));
				info.setLd(rs.getString("ld"));
				return info;
			}
		});
	}

	class QcInfoRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CommonInfo info = new CommonInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setArnNo(rs.getString("arn_no"));
			return info;
		}
	}

	class LogDataBackupRowMapper implements RowMapper<LogDataBackupInfo> {

		@Override
		public LogDataBackupInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogDataBackupInfo info = new LogDataBackupInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setInstrumentName(rs.getString("instrument_name"));
			info.setBackupBy((UUID) rs.getObject("backup_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setBackupDate(rs.getDate("backup_date"));
			info.setRemarks(rs.getString("remarks"));
			info.setInstrumentName(rs.getString("instrument_name"));
			info.setBackupName(rs.getString("backup_name"));
			info.setVerifyName(rs.getString("verify_name"));
			info.setVerifyStatus(rs.getString("verify_status"));

			return info;
		}
	}

	class LogNoteBookRowMapper implements RowMapper<LogNoteBookControlInfo> {

		@Override
		public LogNoteBookControlInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogNoteBookControlInfo info = new LogNoteBookControlInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setEmployeeId((UUID) rs.getObject("employee_id"));
			info.setEmployeeName(rs.getString("emp_name"));
			info.setDesignationId((UUID) rs.getObject("designation_id"));
			info.setDesignationName(rs.getString("desig_name"));
			info.setNoteBookNo(rs.getString("note_book_no"));
			info.setIssueBy((UUID) rs.getObject("issue_by"));
			info.setIssueDate(rs.getDate("issue_date"));
			info.setReceiveBy((UUID) rs.getObject("receive_by"));
			info.setReceiveDate(rs.getDate("receive_date"));
			info.setReturnBy((UUID) rs.getObject("return_by"));
			info.setReturnDate(rs.getDate("return_date"));
			info.setRemarks(rs.getString("remarks"));
			info.setIssueByNm(rs.getString("issue_by_nm"));
			info.setReceiveByNm(rs.getString("receive_by_nm"));
			info.setReturnByNm(rs.getString("return_by_nm"));
			info.setReceiveStatus(rs.getString("receive_status"));
			info.setReturnStatus(rs.getString("return_status"));
			info.setRecordTime(rs.getString("record_time"));

			return info;
		}
	}

	class EhdInfosRowMapper implements RowMapper<LogEhdInfo> {

		@Override
		public LogEhdInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogEhdInfo info = new LogEhdInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setEhdNo(rs.getString("ehd_id_no"));
			info.setIssueDate(rs.getDate("issue_date"));
			info.setIssueTime(rs.getString("issue_time"));
			info.setReturnTime(rs.getString("return_time"));
			info.setReceiveBy((UUID) rs.getObject("receive_by"));
			info.setReturnBy((UUID) rs.getObject("return_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setReceiveByName(rs.getString("receiveBy_name"));
			info.setReturnByName(rs.getString("returnBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setReturnStatus(rs.getString("return_status"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setReceiveStatus(rs.getString("receive_status"));
		//	info.setVerifyStatus(rs.getString("verify_status"));
		//	receive_status, receive_at, 
			return info;
		}
	}

	class DesiccatorInfosRowMapper implements RowMapper<LogDesiccatorInfo> {

		@Override
		public LogDesiccatorInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogDesiccatorInfo info = new LogDesiccatorInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setArnNo(rs.getString("arn_no"));
			info.setKeepDate(rs.getDate("keep_date"));
			info.setOutDate(rs.getDate("out_date"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setVerifyByName(rs.getString("verify_name"));
			info.setRemarks(rs.getString("remarks"));
			info.setIsVerify(rs.getString("is_verify"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setOperateByName(rs.getString("operateBy_name"));

			return info;
		}
	}

	class DryCabinetRowMapper implements RowMapper<LogDryCabinetInfo> {

		@Override
		public LogDryCabinetInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogDryCabinetInfo info = new LogDryCabinetInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setArnNo(rs.getString("arn_no"));
			info.setKeepDate(rs.getDate("keep_date"));
			info.setOutDate(rs.getDate("out_date"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setVerifyByName(rs.getString("verify_name"));
			info.setRemarks(rs.getString("remarks"));
			info.setIsVerify(rs.getString("is_verify"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setBatchNo(rs.getString("batch_no"));

			return info;
		}
	}

	class VerificationDissolutionRowMapper implements RowMapper<LogVerificationDissolutionInfo> {

		@Override
		public LogVerificationDissolutionInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogVerificationDissolutionInfo info = new LogVerificationDissolutionInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setArnNo(rs.getString("arn_no"));
			// info.setKeepDate(rs.getDate("keep_date"));
			// info.setOutDate(rs.getDate("out_date"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setVerifyByName(rs.getString("verify_name"));
			info.setRemarks(rs.getString("remarks"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setOperateByName(rs.getString("operateBy_name"));
			// info.setBatchNo(rs.getString("batch_no"));

			return info;
		}
	}
	
	class VerificationDissolutionChildRowMapper implements RowMapper<LogVerificationDissolutionChdInfo> {

		@Override
		public LogVerificationDissolutionChdInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogVerificationDissolutionChdInfo info = new LogVerificationDissolutionChdInfo();
			
			info.setChdId((UUID) rs.getObject("id"));
			info.setMasterId((UUID) rs.getObject("master_id"));
			info.setVesselNo(rs.getString("vessel_no"));
			info.setTempBeforeTest(rs.getString("temp_before_test"));
			info.setTempAfterTest(rs.getString("temp_after_test"));
			info.setSampleInputTime(rs.getString("sample_input_time"));
			info.setSampleWithdrawalTime(rs.getString("sample_withdrawal_time"));
			info.setRemarks(rs.getString("remarks"));
			
			return info;
		}
	}

	class AreaCleanInfosRowMapper implements RowMapper<LogAreaCleanInfo> {

		@Override
		public LogAreaCleanInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogAreaCleanInfo info = new LogAreaCleanInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setAgentId((UUID) rs.getObject("agent_id"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setCleaningDate(rs.getDate("cleaning_date"));
			info.setCleaningExpDate(rs.getDate("exp_date"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setAgentName(rs.getString("agent_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setCleaningEquipment(rs.getString("cleaning_equipment"));
			info.setVerifiedAt(rs.getTimestamp("verified_at"));
			info.setIsVerify(rs.getString("is_verify"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			

			return info;
		}
	}

	class MultiParamInfosRowMapper implements RowMapper<LogMultiParameterInfo> {

		@Override
		public LogMultiParameterInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMultiParameterInfo info = new LogMultiParameterInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setMaterialId((UUID) rs.getObject("material_id"));
			info.setOperateDate(rs.getDate("operate_date"));
			info.setConductivity(rs.getString("conductivity"));
			info.setTds(rs.getString("tds"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setMaterialName(rs.getString("material_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setCleanStatus(rs.getString("clean_status"));
			info.setRecordTime(rs.getString("record_time"));
			info.setQcRefId((UUID) rs.getObject("qc_reference_id"));
			info.setSampleTypeId((UUID) rs.getObject("sample_type_id"));
			info.setSampleTypeName(rs.getString("sample_type_name"));
			info.setQcRefName(rs.getString("qc_ref_name"));
	
			return info;
		}
	}

	class MoistureParamInfosRowMapper implements RowMapper<LogMoistureAnalyzerInfo> {

		@Override
		public LogMoistureAnalyzerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMoistureAnalyzerInfo info = new LogMoistureAnalyzerInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setResult(rs.getString("ref_result"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setCleanStatus(rs.getString("clean_status"));
			info.setLotNo(rs.getString("lot_no"));
			info.setSampleTypeId((UUID) rs.getObject("sample_type_id"));
			info.setSampleTypeName(rs.getString("sample_type"));

			return info;
		}
	}

	class DisInfectionInfoRowMapper implements RowMapper<LogDisintegrationTestInfo> {

		@Override
		public LogDisintegrationTestInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogDisintegrationTestInfo info = new LogDisintegrationTestInfo();
 
			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setResult(rs.getString("dis_result"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setCleanStatus(rs.getString("clean_status"));
			info.setLotNo(rs.getString("lot_no"));
			info.setSampleTypeId((UUID) rs.getObject("sample_type_id"));
			info.setSampleTypeName(rs.getString("sample_type"));

			return info;
		}
	}

	class HplcInfosRowMapper implements RowMapper<LogHplcInfo> {

		@Override
		public LogHplcInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogHplcInfo info = new LogHplcInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setQcRefId((UUID) rs.getObject("qc_ref_id"));
			info.setLotNo(rs.getString("lot_no"));
			info.setRunStartTime(rs.getString("run_start_time"));
			info.setRunEndTime(rs.getString("run_end_time"));
			info.setColumnId((UUID) rs.getObject("column_id"));
			info.setColumnName(rs.getString("column_name"));
			info.setFirstMpRatio(rs.getString("first_mp_ratio"));
			info.setFirstStartTime(rs.getString("first_start_time"));
			info.setFirstEndTime(rs.getString("first_end_time"));
			info.setSecondMpRatio(rs.getString("second_mp_ratio"));
			info.setSecondStartTime(rs.getString("second_start_time"));
			info.setSecondEndTime(rs.getString("second_end_time"));
			info.setTotalTime(rs.getString("total_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by_id"));
			info.setVerifyBy((UUID) rs.getObject("verified_by_id"));
			info.setRemarks(rs.getString("remarks"));
			info.setQcRefName(rs.getString("qc_ref_name"));
			info.setOperateByName(rs.getString("operate_by_name"));
			info.setVerifyByName(rs.getString("verify_by_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			return info;
		}
	}
	class SampleReceivingInfosRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CommonInfo info = new CommonInfo();

			  info.setId((UUID)rs.getObject("id"));
			  info.setReceiveDate(rs.getTimestamp("receive_at"));
			  info.setMaterialName(rs.getString("material_name"));
			  info.setUdSampleNo(rs.getString("ud_sample_no"));
			  info.setRemarks(rs.getString("sample_desc"));
			  info.setArnNo(rs.getString("arn_no"));
			  info.setStqty(rs.getString("st_qty"));
			  info.setStUnitName(rs.getString("st_unit_name"));
			  info.setStorageCondition(rs.getString("storage_condition"));
			  info.setSampleRcvDesc(rs.getString("remarks"));
			  info.setStRoomName(rs.getString("st_room_name"));
			  info.setStRackName(rs.getString("st_rack_name"));
			  info.setStorageCondition(rs.getString("storage_condition"));
			  info.setRcvQty(rs.getString("rcv_qty"));
			  
	
			return info;
		}
	}
	class VacuumPumpInfoRowMapper implements RowMapper<LogVacuumPumpInfo> {

		@Override
		public LogVacuumPumpInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogVacuumPumpInfo info = new LogVacuumPumpInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipmentBy_Name"));
			info.setPurpose(rs.getString("purpose"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setIsVerify(rs.getString("is_verify"));
			return info;
		}
	}

	class FilterChangeInfoRowMapper implements RowMapper<LogFilterChangeInfo> {

		@Override
		public LogFilterChangeInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogFilterChangeInfo info = new LogFilterChangeInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setMaintenType((UUID) rs.getObject("mainten_type"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setMaintenName(rs.getString("mainten_name"));
			return info;
		}
	}

	class PhMeterInfoRowMapper implements RowMapper<LogPhMeterInfo> {

		@Override
		public LogPhMeterInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogPhMeterInfo info = new LogPhMeterInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setPhResult(rs.getString("ph_result"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setCleanStatus(rs.getString("clean_status"));
			info.setLotNo(rs.getString("lot_no"));
			

			return info;
		}
	}

	class kftInfoRowMapper implements RowMapper<LogKarlFischerInfo> {

		@Override
		public LogKarlFischerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogKarlFischerInfo info = new LogKarlFischerInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setKftResult(rs.getString("kft_result"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setCleanStatus(rs.getString("clean_status"));
			info.setLotNo(rs.getString("lot_no"));

			return info;
		}
	}

	class biologicalSafelyRowMapper implements RowMapper<LogBiologicalSafetyInfo> {

		@Override
		public LogBiologicalSafetyInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogBiologicalSafetyInfo info = new LogBiologicalSafetyInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setCleanStatus(rs.getString("clean_status"));
			info.setLotNo(rs.getString("lot_no"));

			return info;
		}
	}

	class muffleFurnaceRowMapper implements RowMapper<LogMuffleFurnaceInfo> {

		@Override
		public LogMuffleFurnaceInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMuffleFurnaceInfo info = new LogMuffleFurnaceInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setCleanStatus(rs.getString("clean_status"));
			info.setLotNo(rs.getString("lot_no"));
			info.setTemperature(rs.getString("temperature"));
			info.setResult(rs.getString("muf_result"));

			return info;
		}
	}

	class meltingPointRowMapper implements RowMapper<LogMeltingPointInfo> {

		@Override
		public LogMeltingPointInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMeltingPointInfo info = new LogMeltingPointInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setCleanStatus(rs.getString("clean_status"));
			info.setLotNo(rs.getString("lot_no"));
			info.setResult(rs.getString("mel_result"));

			return info;
		}
	}

	class automaticPolarizationRowMapper implements RowMapper<LogAutomaticPolarimeterInfo> {

		@Override
		public LogAutomaticPolarimeterInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogAutomaticPolarimeterInfo info = new LogAutomaticPolarimeterInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setCleanStatus(rs.getString("clean_status"));
			info.setLotNo(rs.getString("lot_no"));
			info.setResult(rs.getString("pol_result"));

			return info;
		}
	}

	class biochemicalOxygenRowMapper implements RowMapper<LogBiochemicalOxygenInfo> {

		@Override
		public LogBiochemicalOxygenInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogBiochemicalOxygenInfo info = new LogBiochemicalOxygenInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setStartDate(rs.getDate("start_date"));
			info.setEndDate(rs.getDate("end_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setCleanStatus(rs.getString("clean_status"));
			info.setResult(rs.getString("bio_result"));
			info.setSampleName(rs.getString("sample_name"));
			info.setSampleNameId((UUID) rs.getObject("sample_name_id"));

			return info;
		}
	}

	class fumeHoodRowMapper implements RowMapper<LogFumeHoodInfo> {

		@Override
		public LogFumeHoodInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogFumeHoodInfo info = new LogFumeHoodInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setCleanStatus(rs.getString("clean_status"));
			info.setPurpose(rs.getString("purpose"));

			return info;
		}
	}

	class laboratoryHeaterRowMapper implements RowMapper<LogLaboratoryHeaterInfo> {

		@Override
		public LogLaboratoryHeaterInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogLaboratoryHeaterInfo info = new LogLaboratoryHeaterInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setCleanStatus(rs.getString("clean_status"));
			info.setLotNo(rs.getString("lot_no"));
			info.setTemperature(rs.getString("temperature"));

			return info;
		}
	}

	class gasChromatographyInfoRowMapper implements RowMapper<LogGasChromatographyInfo> {

		@Override
		public LogGasChromatographyInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogGasChromatographyInfo info = new LogGasChromatographyInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setLotNo(rs.getString("lot_no"));
			info.setColumnId((UUID) rs.getObject("column_id"));
			info.setColumnName(rs.getString("column_name"));
			info.setTotalAnalystTime(rs.getString("total_analyst_time"));

			return info;
		}
	}

	class refractometerInfoRowMapper implements RowMapper<LogRefractometerInfo> {

		@Override
		public LogRefractometerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogRefractometerInfo info = new LogRefractometerInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setKftResult(rs.getString("ref_result"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setCleanStatus(rs.getString("clean_status"));
			info.setLotNo(rs.getString("lot_no"));

			return info;
		}
	}

	class TmControlInfoRowMapper implements RowMapper<LogTmControlInfo> {

		@Override
		public LogTmControlInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogTmControlInfo info = new LogTmControlInfo();

			info.setRecordDate(rs.getDate("record_date"));
			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setQcReference(rs.getString("reference_name"));
			info.setUsedBy((UUID) rs.getObject("used_by"));
			info.setUsedByName(rs.getString("used_by_nm"));
			info.setReturnBy((UUID) rs.getObject("return_by"));
			info.setReturnByName(rs.getString("return_by_nm"));
			info.setReturnDate(rs.getDate("return_date"));
			info.setControlledBy((UUID) rs.getObject("controlled_by"));
			info.setControlByName(rs.getString("controlled_by_nm"));
			info.setRemarks(rs.getString("remarks"));

			return info;
		}
	}

	class SpectrophotometerInfoRowMapper implements RowMapper<LogSpectrophotometerInfo> {

		@Override
		public LogSpectrophotometerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogSpectrophotometerInfo info = new LogSpectrophotometerInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setOperateBy((UUID) rs.getObject("done_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setEquipmentName(rs.getString("equipmentBy_Name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setIsVerify(rs.getString("is_verify"));
			return info;
		}
	}

	class DissolutionInfoRowMapper implements RowMapper<LogDissolutionInfo> {

		@Override
		public LogDissolutionInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogDissolutionInfo info = new LogDissolutionInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setTestedBy((UUID) rs.getObject("operate_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setTestedByName(rs.getString("tested_name"));
			info.setVerifyByName(rs.getString("verify_name"));
			info.setQcReferenceName(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setCleanStatus(rs.getString("clean_status"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setCleanByName(rs.getString("cleanBy_name"));

			return info;
		}
	}

	class SonicatorBathRowMapper implements RowMapper<LogSonicatorBathInfo> {

		@Override
		public LogSonicatorBathInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogSonicatorBathInfo info = new LogSonicatorBathInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setTestedBy((UUID) rs.getObject("operate_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setTestedByName(rs.getString("tested_name"));
			info.setVerifyByName(rs.getString("verify_name"));
			info.setQcReferenceName(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));

			return info;
		}
	}

	class TempRefrigeratorRowMapper implements RowMapper<LogTempRefrigeratorInfo> {

		@Override
		public LogTempRefrigeratorInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogTempRefrigeratorInfo info = new LogTempRefrigeratorInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setTestedBy((UUID) rs.getObject("operate_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setTestedByName(rs.getString("tested_name"));
			info.setVerifyByName(rs.getString("verify_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setTempBefore(rs.getString("temp_before"));
			info.setTempAfter(rs.getString("temp_after"));

			return info;
		}
	}

	class FtirInfoRowMapper implements RowMapper<LogFtirInfo> {

		@Override
		public LogFtirInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogFtirInfo info = new LogFtirInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setOperateBy((UUID) rs.getObject("operate_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setOperateByName(rs.getString("operateBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setLotNo(rs.getString("lot_no"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setCleanStatus(rs.getString("clean_status"));

			return info;
		}
	}


	class ElectricOvenInfoRowMapper implements RowMapper<LogElectricOvenInfo> {

		@Override
		public LogElectricOvenInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogElectricOvenInfo info = new LogElectricOvenInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcReferenceId((UUID) rs.getObject("qc_reference_id"));
			info.setTemperature(rs.getString("temperature"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setStartTime(rs.getString("start_time"));
			info.setEndTime(rs.getString("end_time"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setIsVerify(rs.getString("is_verify"));
			info.setRemarks(rs.getString("remarks"));
			info.setDoneBy((UUID) rs.getObject("done_by"));
			info.setDoneByName(rs.getString("doneByName"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcReference(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipmentBy_Name"));
			return info;
		}
	}

	class SampleInfoRowMapper implements RowMapper<CommonInfo> {
		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CommonInfo info = new CommonInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setMaterialName(rs.getString("material_name"));
			info.setMaterialCode(rs.getString("material_code"));
			info.setMaterialTypeId((UUID) rs.getObject("material_type_id"));

			return info;
		}
	}

	class MaterialInfoRowMapper implements RowMapper<CommonInfo> {
		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			CommonInfo info = new CommonInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setArnNo(rs.getString("arn_no"));
			info.setMaterialName(rs.getString("material_name"));
			info.setMaterialCode(rs.getString("material_code"));
			info.setMaterialTypeId((UUID) rs.getObject("material_type_id"));
			info.setMaterialTypeName(rs.getString("material_type_nm"));
			info.setStqty(rs.getString("st_qty"));
			info.setUnitId((UUID) rs.getObject("st_unit_id"));
			info.setStUnitName(rs.getString("st_unit_name"));
			info.setMaterialId((UUID) rs.getObject("material_id"));
			info.setMaterialName(rs.getString("material_name"));

			return info;
		}
	}

	class RefrigeratorInfoRowMapper implements RowMapper<LogRefrigeratorInfo> {

		@Override
		public LogRefrigeratorInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogRefrigeratorInfo info = new LogRefrigeratorInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcRefId((UUID) rs.getObject("qc_reference_id"));
			info.setSampleNameId((UUID) rs.getObject("sample_name_id"));
			info.setSampleTypeId((UUID) rs.getObject("sample_type_id"));
			info.setQty(rs.getString("qty"));
			info.setInDate(rs.getDate("in_date"));
			info.setOutDate(rs.getDate("out_date"));
			info.setDiscardDate(rs.getDate("discard_date"));
			info.setDiscardQty(rs.getString("discard_qty"));
			info.setDiscardBy((UUID) rs.getObject("discard_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setDiscardByName(rs.getString("discardBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setSampleName(rs.getString("sample_name"));
			info.setSampleTypeName(rs.getString("sample_type"));
			info.setQcRefName(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipmentByName"));
			info.setIsVerify(rs.getString("is_verify"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setUnitName(rs.getString("unit_name"));
			info.setUnitId((UUID) rs.getObject("unit_id"));
			
			info.setDisUnitName(rs.getString("dis_unit_name"));
			info.setDisUnitId((UUID) rs.getObject("dis_unit_id"));
			
			info.setMaterialName(rs.getString("material_name"));
			info.setMaterialId((UUID) rs.getObject("material_id"));
			
			return info;
		}
	}

	class AnalyticalBalanceInfoRowMapper implements RowMapper<LogAnalyticalBalanceInfo> {

		@Override
		public LogAnalyticalBalanceInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogAnalyticalBalanceInfo info = new LogAnalyticalBalanceInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setQcRefId((UUID) rs.getObject("qc_reference_id"));
			info.setSampleTypeId((UUID) rs.getObject("sample_type_id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setLotNo(rs.getString("lot_no"));
			info.setQty(rs.getString("qty"));
			info.setCheckType(rs.getString("check_type"));
			info.setDoneBy((UUID) rs.getObject("done_by"));
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setDoneByName(rs.getString("doneBy_name"));
			info.setCleanByName(rs.getString("cleanBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			info.setQcRefName(rs.getString("reference_name"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setCleanStatus(rs.getString("clean_status"));
			info.setSampleTypeName(rs.getString("sample_type"));

			return info;
		}
	}

	class ColumnRcvDistInfosRowMapper implements RowMapper<LogColumnRcvDistInfo> {

		@Override
		public LogColumnRcvDistInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogColumnRcvDistInfo info = new LogColumnRcvDistInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setColId((UUID) rs.getObject("col_id"));
			info.setColIdByName(rs.getString("column_id"));
			info.setColName(rs.getString("column_name"));
			info.setColSize(rs.getString("column_size"));
			info.setComposition(rs.getString("composition"));
			info.setIsVerify(rs.getString("is_verify"));
			info.setColRcvBy((UUID) rs.getObject("col_rcv_by"));
			info.setColRcvDate(rs.getDate("col_rcv_date"));
			info.setExpireDate(rs.getString("expire_date"));
			info.setIssueBy((UUID) rs.getObject("issue_by"));
			info.setIssueDate(rs.getDate("issue_date"));
			info.setLocationId((UUID) rs.getObject("location_id"));
			info.setOpenDate(rs.getString("open_date"));
			info.setPartNo(rs.getString("part_no"));
			info.setReceiveBy((UUID) rs.getObject("receive_by"));
			info.setReceiveDate(rs.getDate("receive_date"));
			info.setRemarks(rs.getString("remarks"));
			info.setSerialNo(rs.getString("serial_no"));
			info.setRommNo((UUID) rs.getObject("room_no"));
			info.setRackNo((UUID) rs.getObject("rack_no"));
			info.setColRcvByName(rs.getString("colRcvBy_name"));
			info.setIsIssue(rs.getString("is_issue"));
			info.setIssueByName(rs.getString("issueBy_name"));
			info.setReceiveByName(rs.getString("receiveBy_name"));
			info.setCheckByName(rs.getString("checkBy_name"));
			info.setEquipmentByName(rs.getString("equipmentByName"));
			info.setLocByName(rs.getString("locationBy_name"));
			info.setRackByName(rs.getString("rackNoByName"));
			info.setRoomByName(rs.getString("roomByName"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setIsReceive(rs.getString("is_receive"));
			info.setTheoriticalPlate(rs.getString("theoritical_plate"));
			info.setColumnRecordStatus(rs.getString("column_record_status"));
			info.setColumnRecordByName(rs.getString("columnRecordByName"));
			info.setIsRecordVerify(rs.getString("is_record_verify"));
			info.setRecordVerifyByName(rs.getString("record_verify_by_name"));
			info.setColumnIdNew(rs.getString("column_id_new"));
			
			return info;
		}
	}
	class ColumnPerformanceRecordRowMapper implements RowMapper<LogColumnPerformanceRecordInfo> {
	
		@Override
		public LogColumnPerformanceRecordInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogColumnPerformanceRecordInfo info = new LogColumnPerformanceRecordInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setPerformanceId((UUID) rs.getObject("performance_id"));
			info.setColId((UUID) rs.getObject("column_id"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setQcRefId((UUID) rs.getObject("qc_ref_id"));
			info.setEquipmentByName(rs.getString("equipmentBy_Name"));
			info.setArnName(rs.getString("arn_name"));
			info.setColName(rs.getString("column_name_new"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setTp(rs.getString("tp"));
			info.setTf(rs.getString("tf"));
			info.setRsd(rs.getString("rsd"));
			info.setRemarks(rs.getString("remarks"));
			info.setIsVerify(rs.getString("is_verify"));
			return info;
		}
	}
	class ColumnPerformancePendingRowMapper implements RowMapper<LogColumnPerformanceRecordInfo> {

		@Override
		public LogColumnPerformanceRecordInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogColumnPerformanceRecordInfo info = new LogColumnPerformanceRecordInfo();
			info.setId((UUID) rs.getObject("id"));
			info.setColId((UUID) rs.getObject("column_id"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setQcRefId((UUID) rs.getObject("qc_ref_id"));
			info.setEquipmentByName(rs.getString("equipmentBy_Name"));
			info.setArnName(rs.getString("arn_name"));
			info.setColName(rs.getString("column_name_new"));
			info.setRecordDate(rs.getDate("record_date"));
			return info;
		}
	}

	class ColumnInfosRowMapper implements RowMapper<LogColumnInfo> {

		@Override
		public LogColumnInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogColumnInfo info = new LogColumnInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setColId(rs.getString("col_id"));
			info.setColName(rs.getString("col_name"));
			info.setColSize(rs.getString("col_size"));
			info.setComposition(rs.getString("composition"));
			info.setPartNo(rs.getString("part_no"));
			info.setSerialNo(rs.getString("serial_no"));
			info.setLocation(rs.getString("location"));
			info.setTheorePlate(rs.getString("theore_plate"));
			info.setOpenDate(rs.getDate("open_date"));
			info.setExpireDate(rs.getDate("expire_date"));
			info.setDoneBy((UUID) rs.getObject("done_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setDoneByName(rs.getString("doneBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			return info;
		}
	}

	class TempHumidityInfoRowMapper implements RowMapper<LogTempHumidityInfo> {

		@Override
		public LogTempHumidityInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogTempHumidityInfo info = new LogTempHumidityInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setBeforeTemp(rs.getString("before_temp"));
			info.setAfterTemp(rs.getString("after_temp"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setRelHumidity(rs.getString("rel_humidity"));
			info.setDoneBy((UUID) rs.getObject("done_by"));
			info.setVerifyBy((UUID) rs.getObject("verify_by"));
			info.setRemarks(rs.getString("remarks"));
			info.setDoneByName(rs.getString("doneBy_name"));
			info.setVerifyByName(rs.getString("verifyBy_name"));
			return info;
		}
	}

	class LogWaterPurificationRowMapper implements RowMapper<LogWaterPurificationInfo> {

		@Override
		public LogWaterPurificationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogWaterPurificationInfo info = new LogWaterPurificationInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setQuantity(rs.getString("quantity"));
			info.setUnitId((UUID) rs.getObject("unit_id"));
			info.setUnitName(rs.getString("unit_name"));
			info.setRemarks(rs.getString("remarks"));
			info.setDispensedByName(rs.getString("dispensed_by"));
			info.setVerifyName(rs.getString("verify_by"));
			info.setVerifiedAt(rs.getTimestamp("verified_at"));
			info.setIsVerify(rs.getString("is_verify"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));

			return info;
		}

	}

	class LogAnalystValidationRowMapper implements RowMapper<LogAnalystValidationInfo> {

		@Override
		public LogAnalystValidationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogAnalystValidationInfo info = new LogAnalystValidationInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRemarks(rs.getString("remarks"));
			info.setDispensedByName(rs.getString("dispensed_by"));
			info.setVerifyName(rs.getString("verify_by"));
			info.setVerifiedAt(rs.getTimestamp("verified_at"));
			info.setIsVerify(rs.getString("is_verify"));
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

	class LogWaterSamplingRowMapper implements RowMapper<LogWaterSamplingInfo> {

		@Override
		public LogWaterSamplingInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogWaterSamplingInfo info = new LogWaterSamplingInfo();

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
	class LogComparativeDissolutionDutyRowMapper implements RowMapper<LogComparativeDissolutionDutyInfo> {

		@Override
		public LogComparativeDissolutionDutyInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogComparativeDissolutionDutyInfo info = new LogComparativeDissolutionDutyInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRemarks(rs.getString("remarks"));
			info.setManufacturerId((UUID) rs.getObject("manufacturer_id"));
			info.setManufacturerName(rs.getString("manufacturer_Name"));
			info.setProductName(rs.getString("product_name"));
			info.setBatchNo(rs.getString("batch_no"));
			info.setCreatedyByName(rs.getString("createdByName"));
			info.setDistributedName(rs.getString("distributed_by_nm"));
			info.setDistributedStatus(rs.getString("distribute_status"));
			info.setAnalystBy((UUID) rs.getObject("analyst_by"));
			info.setAnalystByName(rs.getString("analyst_name"));
			info.setResultByName(rs.getString("result_by_name"));
			info.setResultComp(rs.getString("result_comp"));
			info.setResultStatus(rs.getString("result_status"));
			
			return info;
		}

	}

	class LogAllottedSampleRowMapper implements RowMapper<LogAllottedSampleInfo> {

		@Override
		public LogAllottedSampleInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogAllottedSampleInfo info = new LogAllottedSampleInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRemarks(rs.getString("remarks"));
			info.setDispensedByName(rs.getString("dispensed_by"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setAnalystId((UUID) rs.getObject("analyst_id"));
			info.setAnalystName(rs.getString("analyst_name"));
			info.setProductName(rs.getString("product_name"));
			info.setDocumentCode(rs.getString("document_code"));
			info.setBatchNo(rs.getString("batch_no"));
			info.setTestMethodName(rs.getString("test_method_name"));

			return info;
		}

	}
	class LogOutofTrendRowMapper implements RowMapper<LogOutofTrend> {

		@Override
		public LogOutofTrend mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogOutofTrend info = new LogOutofTrend();

			info.setId((UUID) rs.getObject("id"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setProductId((UUID) rs.getObject("product_id"));
			info.setProductName(rs.getString("product_name"));
			info.setBatchNo(rs.getString("batch_no"));
			info.setOotObservationDate(rs.getDate("oot_observation_date"));
			info.setInvestigationDate(rs.getDate("investigation_date"));
			info.setClassOutDate(rs.getDate("class_out_date"));
			info.setOotNo(rs.getString("oot_no"));
			info.setTest(rs.getString("test"));
			
			return info;
		}

	}

	class LogDeHumidifierRowMapper implements RowMapper<LogDeHumidifierInfo> {

		@Override
		public LogDeHumidifierInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogDeHumidifierInfo info = new LogDeHumidifierInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setRemarks(rs.getString("remarks"));
			info.setDispensedByName(rs.getString("dispensed_by_name"));
			info.setVerifyName(rs.getString("verify_by"));
			info.setVerifiedAt(rs.getTimestamp("verified_at"));
			info.setIsVerify(rs.getString("is_verify"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));
			info.setDispensedBy((UUID) rs.getObject("dispensed_by"));			
			info.setCleanBy((UUID) rs.getObject("clean_by"));
			info.setCleanByName(rs.getString("clean_by_name"));
			info.setIsClean(rs.getString("is_clean"));
			
			return info;
		}

	}

	class LogTOCRowMapper implements RowMapper<LogTOCInfo> {

		@Override
		public LogTOCInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogTOCInfo info = new LogTOCInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setRemarks(rs.getString("remarks"));
			info.setDispensedByName(rs.getString("dispensed_by"));
			info.setVerifyName(rs.getString("verify_by"));
			info.setVerifiedAt(rs.getTimestamp("verified_at"));
			info.setIsVerify(rs.getString("is_verify"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));

			info.setResult(rs.getString("toc_result"));
			info.setBatchNo(rs.getString("batch_no"));
			info.setSampleName(rs.getString("sample_name"));
			

			return info;
		}

	}

	class LogSampleLabelRowMapper implements RowMapper<LogSampleLabelInfo> {

		@Override
		public LogSampleLabelInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogSampleLabelInfo info = new LogSampleLabelInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setRemarks(rs.getString("remarks"));
			info.setDispensedByName(rs.getString("dispensed_by"));
			info.setVerifyName(rs.getString("verify_by"));
			info.setVerifiedAt(rs.getTimestamp("verified_at"));
			info.setIsVerify(rs.getString("is_verify"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));

			info.setQty(rs.getString("qty"));
			info.setBatchNo(rs.getString("batch_no"));
			info.setSampleName(rs.getString("sample_name"));
			info.setSampleNameId((UUID) rs.getObject("sample_name_id"));
			
			info.setUnitId((UUID) rs.getObject("unit_id"));
			info.setUnitName(rs.getString("unit_name"));
			
			return info;
		}

	}

	class LogAtomicAbsorptionRowMapper implements RowMapper<LogAtomicAbsorptionInfo> {

		@Override
		public LogAtomicAbsorptionInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogAtomicAbsorptionInfo info = new LogAtomicAbsorptionInfo();

			info.setId((UUID) rs.getObject("id"));
			info.setEquipmentId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			info.setRemarks(rs.getString("remarks"));
			info.setDispensedByName(rs.getString("operate_by_name"));
			info.setVerifyStatus(rs.getString("verify_status"));
			info.setRecordDate(rs.getDate("record_date"));
			info.setRecordTime(rs.getString("record_time"));

			info.setLotNo(rs.getString("lot_no"));
			info.setH2oNo(rs.getString("h2o_no"));
			info.setHno3No(rs.getString("hno3_no"));
			info.setBatchNo(rs.getString("batch_no"));
			info.setSampleName(rs.getString("sample_name"));
			info.setSampleNameId((UUID) rs.getObject("sample_name_id"));
			info.setFirstTime(rs.getString("first_time"));
			info.setSecondTime(rs.getString("second_time"));
			info.setTotalAnalystTime(rs.getString("total_analyst_time"));

			return info;
		}

	}

	class DesiccatorRowMapper implements RowMapper<LogDesiccatorInfo> {

		@Override
		public LogDesiccatorInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogDesiccatorInfo info = new LogDesiccatorInfo();

			return info;
		}
	}

	// ===================For Get Child Data from ========================
	public List<LogVerificationDissolutionChdInfo> getVerificationDissolutionDetailsById(UUID whRequestId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();

		return jdbcTemplate.query(
				"select id, master_id, vessel_no,temp_after_test,temp_before_test,sample_input_time,sample_withdrawal_time,remarks from lims_log_verification_dissolution_chd_infos "
						+ " where master_id= ? and company_id = ?",
				new Object[] { whRequestId, user.getCompanyId() }, new WhRequestDetailsInfoRowMapper());
	}

	class WhRequestDetailsInfoRowMapper implements RowMapper<LogVerificationDissolutionChdInfo> {

		@Override
		public LogVerificationDissolutionChdInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogVerificationDissolutionChdInfo detailsInfo = new LogVerificationDissolutionChdInfo();
			detailsInfo.setChdId((UUID) rs.getObject("id"));
			detailsInfo.setMasterId((UUID) rs.getObject("master_id"));
			detailsInfo.setVesselNo(rs.getString("vessel_no"));
			detailsInfo.setTempAfterTest(rs.getString("temp_after_test"));
			detailsInfo.setTempBeforeTest(rs.getString("temp_before_test"));
			detailsInfo.setSampleInputTime(rs.getString("sample_input_time"));
			detailsInfo.setSampleWithdrawalTime(rs.getString("sample_withdrawal_time"));
			detailsInfo.setRemarks(rs.getString("remarks"));
			return detailsInfo;
		}
	}

	public void saveVerificationDissolutionDetails(LogVerificationDissolutionChdInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());

		
			jdbcTemplate.update(
					"INSERT INTO lims_log_verification_dissolution_chd_infos (master_id,vessel_no,temp_after_test,temp_before_test,sample_input_time,"
							+ " sample_withdrawal_time,remarks, company_id, created_by, created_at) VALUES (?,?,?,?,?,?,?,?,?,?)",
					info.getMasterId(), info.getVesselNo(), info.getTempAfterTest(), info.getTempBeforeTest(),
					info.getSampleInputTime(), info.getSampleWithdrawalTime(), info.getRemarks(), user.getCompanyId(),
					user.getId(), time);
		

	}

	public void deleteVerifiDissolutionDetailsInfos(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);
		jdbcTemplate.update("DELETE FROM lims_log_verification_dissolution_chd_infos WHERE id = ?",
				new Object[] { id });
	}
	


	class EquipmentInfoRowMapper implements RowMapper<EquipmentInfo> {

		@Override
		public EquipmentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			EquipmentInfo info = new EquipmentInfo();

			info.setId((UUID) rs.getObject("equipment_id"));
			info.setEquipmentName(rs.getString("equipment_name"));
			
			return info;
		}
	}


}
