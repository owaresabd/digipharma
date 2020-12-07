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

import com.pms.configure.bean.Utility;
import com.pms.model.CommonInfo;
import com.pms.model.ResultInfo;
import com.pms.model.User;
import com.pms.service.IUserService;

@Repository
@Transactional
public class ResultRepository {

	@Autowired
	private DataSource datasource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private IUserService userService;

	public List<CommonInfo> findAll() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		
		return jdbcTemplate.query(
				"SELECT id,sample_rcv_id,ud_css_no,wh_request_id,req_by,req_at,css_status,req_type,req_type_name,"
				+ " effective_date, revision_no, form_no, invoice_no, chalan_no, lc_no, from_dept_no, from_dept_nm,"
				+ "  to_dept_no, to_dept_nm, gr_no, material_id, material_name, material_code, material_type_id, "
				+ "  material_type_nm, rcv_qty, manufacture_name, country_id, country_nm, sample_details, wh_status,"
				+ "  is_chemical, is_microbiological, total_amt, ud_sample_no, client_name, client_id, method_name,"
				+ "  storage_condition, sample_procedure, booth_name, equipment_name, inst_name, precaution_taken,"
				+ "  sampling_date, sampling_name, status, remarks, ud_qc_no, sample_id, sample_desc, uncertinity,"
				+ "  qc_req_dt, is_decision, qc_status, company_id, qc_req_id, arn_no, sample_desc_rcv, st_qty, st_unit_id,"
				+ "  st_unit_name, st_equ_id, st_equ_name, st_room_id, st_room_name, st_rack_id, st_rack_name, ret_qty, ret_unit_id,"
				+ "  ret_unit_name, ret_equ_id, ret_equ_name, ret_room_id, ret_room_name, ret_rack_id, ret_rack_name, dist_status,"
				+ "  receive_at, test_parameter_no, test_parameter_id, test_parameter_name, specification, reference_id, reference_name,"
				+ "  test_method_id, test_method_name,test_unit_id,fnc_unit_nm(test_unit_id) test_unit_name, ecuipment_id, analyst_id, analyst_name, analyst_emp_id, result_status,dist_by,distribution_at "
				+ "	FROM view_distribution_infos where  company_id = ? and analyst_id=" + (user.getEmployeeId() != null ? "'" + user.getEmployeeId() +"'" : "analyst_id") + " ORDER BY receive_at DESC",
				new Object[] { user.getCompanyId() }, new ReceiveInfoRowMapper());
	}
	
	public List<CommonInfo> findAllSingle() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		
		return jdbcTemplate.query(
				"Select DISTINCT ON (analyst_id,sample_id) arn_no,analyst_id, id, analyst_name,sample_id, material_name,"
				+ " receive_at, distribution_at, result_status, chemi_dist_at, micro_dist_at,"
				+ " (select count(*) from lims_distribution_infos where is_analyst_recv='N' and sample_id=a.sample_id  and analyst_id="+(user.getEmployeeId() != null ?"'" + user.getEmployeeId() +"'" : "analyst_id")+") is_analyst_rcv  "
				+ " from view_distribution_infos a where company_id = ? "
				+ " and result_status='P' "
				+ " and analyst_id=" + (user.getEmployeeId() != null ?"'" + user.getEmployeeId() +"'" : "analyst_id") + ""
				+ " order by analyst_id ", new Object[] { user.getCompanyId() }, new AnalystRcvPendingInfoRowMapper());
	}
	public void receiveByAnalyst(UUID sampleId, UUID analystId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update("update  lims_distribution_infos set is_analyst_recv='Y',analyst_received_at='"+time+"' "
				+ " where sample_id = ? AND analyst_id = ?", sampleId, analystId);
	}
	public List<CommonInfo> findAllSingleback() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		
		return jdbcTemplate.query(
				"Select DISTINCT ON (analyst_id) analyst_id, id, analyst_name, arn_no, sample_id, material_name,"
				+ " receive_at, distribution_at, result_status, chemi_dist_at, micro_dist_at "
				+ " from view_distribution_infos where company_id = ? and result_status='P' and analyst_id=" + (user.getEmployeeId() != null ?"'" + user.getEmployeeId() +"'" : "analyst_id") + ""
				+ " group by analyst_id, id, analyst_name, arn_no, sample_id, material_name, receive_at, distribution_at, result_status, chemi_dist_at, micro_dist_at "
				+ " order by analyst_id", new Object[] { user.getCompanyId() }, new SingleRcvInfoRowMapper());
	}
	
	public List<CommonInfo> findAllResultInputedSingle() {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				"Select DISTINCT ON (analyst_id,sample_id) analyst_id, id, analyst_name, arn_no, sample_id, material_name,"
				+ " receive_at, distribution_at, result_status, chemi_dist_at, micro_dist_at "
				+ " from view_distribution_infos p where exists "
				+ "	( select distribution_id from view_result_status_info c  where c.distribution_id = p.id) "
				+ " and  company_id = ?  and analyst_id=" + (user.getEmployeeId() != null ?"'" + user.getEmployeeId() +"'" : "analyst_id") + ""
				+ " group by analyst_id, id, analyst_name, arn_no, sample_id, material_name, receive_at, distribution_at, result_status, chemi_dist_at, micro_dist_at "
				+ " order by analyst_id", new Object[] { user.getCompanyId() }, new SingleRcvInfoRowMapper());
	}
	
	public List<CommonInfo> findSubmittedList(String paramType) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT  DISTINCT ON (sample_id)  sample_id, id,sample_rcv_id,ud_css_no,wh_request_id,req_by,req_at,css_status,req_type,req_type_name,"
				+ " effective_date, revision_no, form_no, invoice_no, chalan_no, lc_no, from_dept_no, from_dept_nm,"
				+ "  to_dept_no, to_dept_nm, gr_no, material_id, material_name, material_code, material_type_id, "
				+ "  material_type_nm, rcv_qty, manufacture_name, country_id, country_nm, sample_details, wh_status,"
				+ "  is_chemical, is_microbiological, total_amt, ud_sample_no, client_name, client_id, method_name,"
				+ "  storage_condition, sample_procedure, booth_name, equipment_name, inst_name, precaution_taken,"
				+ "  sampling_date, sampling_name, status, remarks, ud_qc_no,sample_desc, uncertinity,"
				+ "  qc_req_dt, is_decision, qc_status, company_id, qc_req_id, arn_no, sample_desc_rcv, st_qty, st_unit_id,"
				+ "  st_unit_name, st_equ_id, st_equ_name, st_room_id, st_room_name, st_rack_id, st_rack_name, ret_qty, ret_unit_id,"
				+ "  ret_unit_name, ret_equ_id, ret_equ_name, ret_room_id, ret_room_name, ret_rack_id, ret_rack_name, dist_status,"
				+ "  receive_at, test_parameter_no, test_parameter_id, test_parameter_name, specification, reference_id, reference_name,"
				+ "  test_method_id, test_method_name,test_unit_id,fnc_unit_nm(test_unit_id) test_unit_name, ecuipment_id, analyst_id, analyst_name, analyst_emp_id, result_status,dist_by,distribution_at "
				+ " FROM view_distribution_infos  p where  exists "
				+ " ( select * from view_result_status_info c  where c.sample_rcv_id = p.sample_rcv_id and c.is_accept = 'P') "
				+ "  and p.param_type='"+paramType+"' and company_id = ? ORDER BY sample_id",
				
				new Object[] { user.getCompanyId() }, new ReceiveInfoRowMapper());
	}
	
	public List<CommonInfo> findSubmittedChdList(UUID sampleRcvId, String paramType) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(
				" SELECT b.id result_id,a.test_parameter_name,a.specification,b.test_result, b.doc_location,a.sample_id, " + 
				"fnc_unit_nm(b.unit_id) unit_name,a. reference_name,a.result_status, b.status is_accept, " + 
				"a.analyst_name, a.analyst_emp_id, coalesce(to_char( a.receive_at, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as receive_at,"
				+ " coalesce(to_char( b.created_at, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as test_date , receive_at as start_date,b.created_at as end_date  " + 
				" FROM view_distribution_infos a " + 
				" FULL OUTER JOIN lims_result_infos b  " + 
				"ON b.distribution_id = a.id where a.param_type='"+paramType+"' and a.sample_rcv_id = ? ",
				new Object[] { sampleRcvId }, new ChildInfoRowMapper());
	}
	
	public List<CommonInfo> findAnalystSubmittedChdList(UUID sampleRcvId, UUID analystId) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(
				" SELECT b.id result_id,a.test_parameter_name,a.specification,b.test_result, b.doc_location,a.sample_id, " + 
				"fnc_unit_nm(b.unit_id) unit_name,a. reference_name,a.result_status, b.status is_accept, " + 
				"a.analyst_name, a.analyst_emp_id, coalesce(to_char( a.receive_at, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as receive_at,"
				+ " coalesce(to_char( b.created_at, 'DD-Mon-YYYY HH12:MI:SS AM'), '') as test_date , receive_at as start_date,b.created_at as end_date  " + 
				" FROM view_distribution_infos a  ,lims_result_infos b  " + 
				" WHERE b.distribution_id = a.id and   a.sample_id = ?  and a.analyst_id=?",
				new Object[] { sampleRcvId , analystId }, new ChildInfoRowMapper());
	}

	
	public CommonInfo getDistributionInfo(UUID id){
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject(
						" SELECT distinct on (sample_id) sample_id,id,sample_rcv_id,ud_css_no,wh_request_id,req_by,req_at,css_status,req_type,req_type_name,"
						+ " effective_date, revision_no, form_no, invoice_no, chalan_no, lc_no, from_dept_no, from_dept_nm,"
						+ "  to_dept_no, to_dept_nm, gr_no, material_id, material_name, material_code, material_type_id, "
						+ "  material_type_nm, rcv_qty, manufacture_name, country_id, country_nm, sample_details, wh_status,"
						+ "  is_chemical, is_microbiological, total_amt, ud_sample_no, client_name, client_id, method_name,"
						+ "  storage_condition, sample_procedure, booth_name, inst_name, precaution_taken,"
						+ "  sampling_date, sampling_name, status, remarks, ud_qc_no,  sample_desc, uncertinity,"
						+ "  qc_req_dt, is_decision, qc_status, company_id, qc_req_id, arn_no, sample_desc_rcv, st_qty, st_unit_id,"
						+ "  st_unit_name, st_equ_id, st_equ_name, st_room_id, st_room_name, st_rack_id, st_rack_name, ret_qty, ret_unit_id,"
						+ "  ret_unit_name, ret_equ_id, ret_equ_name, ret_room_id, ret_room_name, ret_rack_id, ret_rack_name, dist_status,"
						+ "  receive_at, test_parameter_no, test_parameter_id, test_parameter_name, specification, reference_id, reference_name,"
						+ "  test_method_id,fnc_test_method_name(test_method_id) test_method_name,test_unit_id,fnc_unit_nm(test_unit_id) test_unit_name,"
						+ " ecuipment_id,fnc_multi_equipment_name (ecuipment_id) equipment_name, analyst_id, analyst_name, analyst_emp_id, result_status,dist_by,distribution_at " + 
						"	FROM view_distribution_infos where  sample_id = ?",
				 new Object[] { id}, new ReceiveInfoRowMapper());
	}
	public CommonInfo getDistributionSingleInfo(UUID id,UUID anId){
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject(
						" SELECT distinct on (sample_id) sample_id,id,sample_rcv_id,ud_css_no,wh_request_id,req_by,req_at,css_status,req_type,req_type_name,"
						+ " effective_date, revision_no, form_no, invoice_no, chalan_no, lc_no, from_dept_no, from_dept_nm,"
						+ "  to_dept_no, to_dept_nm, gr_no, material_id, material_name, material_code, material_type_id, "
						+ "  material_type_nm, rcv_qty, manufacture_name, country_id, country_nm, sample_details, wh_status,"
						+ "  is_chemical, is_microbiological, total_amt, ud_sample_no, client_name, client_id, method_name,"
						+ "  storage_condition, sample_procedure, booth_name, inst_name, precaution_taken,"
						+ "  sampling_date, sampling_name, status, remarks, ud_qc_no,  sample_desc, uncertinity,"
						+ "  qc_req_dt, is_decision, qc_status, company_id, qc_req_id, arn_no, sample_desc_rcv, st_qty, st_unit_id,"
						+ "  st_unit_name, st_equ_id, st_equ_name, st_room_id, st_room_name, st_rack_id, st_rack_name, ret_qty, ret_unit_id,"
						+ "  ret_unit_name, ret_equ_id, ret_equ_name, ret_room_id, ret_room_name, ret_rack_id, ret_rack_name, dist_status,"
						+ "  receive_at, test_parameter_no, test_parameter_id, test_parameter_name, specification, reference_id, reference_name,"
						+ "  test_method_id,fnc_test_method_name(test_method_id) test_method_name,test_unit_id,fnc_unit_nm(test_unit_id) test_unit_name,"
						+ " ecuipment_id,fnc_multi_equipment_name (ecuipment_id) equipment_name, analyst_id, analyst_name, analyst_emp_id, result_status,dist_by,distribution_at " + 
						"	FROM view_distribution_infos where analyst_id=(SELECT id FROM lims_employee_infos WHERE id='"+anId+"') and   sample_id = ?",
				 new Object[] { id}, new ReceiveInfoRowMapper());
	}
	public List<CommonInfo> getDistributionInfosSingle(UUID sampleId,UUID analystId){
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(
						" SELECT id,sample_rcv_id,ud_css_no,wh_request_id,req_by,req_at,css_status,req_type,req_type_name,"
						+ " effective_date, revision_no, form_no, invoice_no, chalan_no, lc_no, from_dept_no, from_dept_nm,"
						+ "  to_dept_no, to_dept_nm, gr_no, material_id, material_name, material_code, material_type_id, "
						+ "  material_type_nm, rcv_qty, manufacture_name, country_id, country_nm, sample_details, wh_status,"
						+ "  is_chemical, is_microbiological, total_amt, ud_sample_no, client_name, client_id, method_name,"
						+ "  storage_condition, sample_procedure, booth_name, inst_name, precaution_taken,"
						+ "  sampling_date, sampling_name, status, remarks, ud_qc_no, sample_id, sample_desc, uncertinity,"
						+ "  qc_req_dt, is_decision, qc_status, company_id, qc_req_id, arn_no, sample_desc_rcv, st_qty, st_unit_id,"
						+ "  st_unit_name, st_equ_id, st_equ_name, st_room_id, st_room_name, st_rack_id, st_rack_name, ret_qty, ret_unit_id,"
						+ "  ret_unit_name, ret_equ_id, ret_equ_name, ret_room_id, ret_room_name, ret_rack_id, ret_rack_name, dist_status,"
						+ "  receive_at, test_parameter_no, test_parameter_id, test_parameter_name, specification, reference_id, reference_name,"
						+ "  test_method_id,fnc_test_method_name(test_method_id) test_method_name,test_unit_id,fnc_unit_nm(test_unit_id) test_unit_name,"
						+ " ecuipment_id,fnc_multi_equipment_name (ecuipment_id) equipment_name, analyst_id, analyst_name, analyst_emp_id, result_status,dist_by,distribution_at "
						+ "	FROM view_distribution_infos where analyst_id=(SELECT id FROM lims_employee_infos WHERE id='"+analystId+"') and  sample_id = ? ",
				 new Object[] {sampleId}, new ReceiveInfoRowMapper());
	}
	public List<CommonInfo> findDistributedChdList(UUID sampleRcvId,String paramType) {
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.query(
				" SELECT id,sample_rcv_id,ud_css_no,wh_request_id,req_by,req_at,css_status,req_type,req_type_name,"
						+ " effective_date, revision_no, form_no, invoice_no, chalan_no, lc_no, from_dept_no, from_dept_nm,"
						+ "  to_dept_no, to_dept_nm, gr_no, material_id, material_name, material_code, material_type_id, "
						+ "  material_type_nm, rcv_qty, manufacture_name, country_id, country_nm, sample_details, wh_status,"
						+ "  is_chemical, is_microbiological, total_amt, ud_sample_no, client_name, client_id, method_name,"
						+ "  storage_condition, sample_procedure, booth_name, inst_name, precaution_taken,"
						+ "  sampling_date, sampling_name, status, remarks, ud_qc_no, sample_id, sample_desc, uncertinity,"
						+ "  qc_req_dt, is_decision, qc_status, company_id, qc_req_id, arn_no, sample_desc_rcv, st_qty, st_unit_id,"
						+ "  st_unit_name, st_equ_id, st_equ_name, st_room_id, st_room_name, st_rack_id, st_rack_name, ret_qty, ret_unit_id,"
						+ "  ret_unit_name, ret_equ_id, ret_equ_name, ret_room_id, ret_room_name, ret_rack_id, ret_rack_name, dist_status,"
						+ "  receive_at, test_parameter_no, test_parameter_id, test_parameter_name, specification, reference_id, reference_name,"
						+ "  test_method_id,fnc_test_method_name(test_method_id) test_method_name,test_unit_id,fnc_unit_nm(test_unit_id) test_unit_name,"
						+ " ecuipment_id,fnc_multi_equipment_name (ecuipment_id) equipment_name, analyst_id, analyst_name, analyst_emp_id, result_status,dist_by,distribution_at " + 
						"	FROM view_distribution_infos where param_type='"+paramType+"' and  sample_rcv_id = ? ",
				new Object[] { sampleRcvId }, new ReceiveInfoRowMapper());
	}
	
	public List<CommonInfo> findAcceptedList(String paramType) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		return jdbcTemplate.query(
				" SELECT  DISTINCT ON (sample_id)  sample_id, id,sample_rcv_id,ud_css_no,wh_request_id,req_by,req_at,css_status,req_type,req_type_name,"
				+ " effective_date, revision_no, form_no, invoice_no, chalan_no, lc_no, from_dept_no, from_dept_nm,"
				+ "  to_dept_no, to_dept_nm, gr_no, material_id, material_name, material_code, material_type_id, "
				+ "  material_type_nm, rcv_qty, manufacture_name, country_id, country_nm, sample_details, wh_status,"
				+ "  is_chemical, is_microbiological, total_amt, ud_sample_no, client_name, client_id, method_name,"
				+ "  storage_condition, sample_procedure, booth_name, equipment_name, inst_name, precaution_taken,"
				+ "  sampling_date, sampling_name, status, remarks, ud_qc_no,sample_desc, uncertinity,"
				+ "  qc_req_dt, is_decision, qc_status, company_id, qc_req_id, arn_no, sample_desc_rcv, st_qty, st_unit_id,"
				+ "  st_unit_name, st_equ_id, st_equ_name, st_room_id, st_room_name, st_rack_id, st_rack_name, ret_qty, ret_unit_id,"
				+ "  ret_unit_name, ret_equ_id, ret_equ_name, ret_room_id, ret_room_name, ret_rack_id, ret_rack_name, dist_status,"
				+ "  receive_at, test_parameter_no, test_parameter_id, test_parameter_name, specification, reference_id, reference_name,"
				+ "  test_method_id, test_method_name,test_unit_id,fnc_unit_nm(test_unit_id) test_unit_name, ecuipment_id, analyst_id, analyst_name, analyst_emp_id, result_status,dist_by,distribution_at "
				+ " FROM view_distribution_infos  p where  exists "
				+ " ( select * from view_result_status_info c  where c.sample_rcv_id = p.sample_rcv_id and c.is_accept = 'A') "
				+ "  and p.param_type='"+paramType+"' and company_id = ? ORDER BY sample_id",
				
				new Object[] { user.getCompanyId() }, new ReceiveInfoRowMapper());
	}
	
	public CommonInfo getTestResultDetailsInfo(UUID id){
		//User user = userService.getCurrentUser();
		jdbcTemplate = new JdbcTemplate(datasource);
		return jdbcTemplate.queryForObject("SELECT b.ud_sample_no ,a.test_report_no," + 
				" b.material_name,b.sample_details, b.from_dept_nm,b.receive_at,b.sample_id, b.id sample_rcv_id," + 
				" (CAST(b.st_qty as float) + CAST((CASE WHEN b.ret_qty='' THEN '0' ELSE  b.ret_qty END)  as float)) rcv_qty,b.st_unit_name,b.manufacture_name," + 
				" (Select remarks from lims_tm_verify_infos where sample_rcv_id::text=b.id::text) remarks," + 
				" (Select opinion from lims_lm_verify_infos where sample_rcv_id::text=b.id::text) opinion," + 
				" string_agg(c.batch_no, ', ') AS batch," + 
				" string_agg(TO_CHAR( c.mfg_date, 'dd-Mon-yyyy' ), ',')  AS mfg_date," + 
				" string_agg(TO_CHAR( c.exp_date, 'dd-Mon-yyyy' ), ',') AS exp_date " + 
				" from view_sample_infos a " + 
				" LEFT JOIN view_sample_rcv_infos b " + 
				" ON a.sample_id=b.sample_id " + 
				" FULL OUTER JOIN lims_wh_request_details_infos c " + 
				" on c.wh_request_id=b.wh_request_id " + 
				" WHERE   a.sample_id::text='"+id+"'::text " + 
				" group by a.company_name,a.address,a.phone,a.mobile,a.email,a.fax,a.website,a.wh_req_id, b.ud_sample_no ," + 
				" b.material_name,b.sample_details, b.from_dept_nm,b.receive_at,b.sample_id," + 
				" b.id,b.st_qty,b.ret_qty,b.st_unit_name,b.manufacture_name,a.test_report_no ",
				  new ResultInfoRowMapper());
	}
	
	
	public void saveResultInfo(ResultInfo info) {
		jdbcTemplate = new JdbcTemplate(datasource);
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
		
			jdbcTemplate.update(
					"INSERT INTO lims_result_infos (distribution_id, test_result, unit_id, doc_location, remarks,status, company_id, created_by, created_at) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)",info.getDistributionId(), info.getTestResult(), info.getUnitId(),info.getDocLocation(), info.getRemarks(), 
					"P", user.getCompanyId(), user.getId(), time);
			jdbcTemplate.update("UPDATE lims_distribution_infos SET status ='R' where id = ?", info.getDistributionId());
			
		
	}
	public void saveResultAcceptInfo(UUID id) {
		jdbcTemplate = new JdbcTemplate(datasource);		
		User user = userService.getCurrentUser();
		Timestamp time = new Timestamp(System.currentTimeMillis());
			jdbcTemplate.update("UPDATE lims_result_infos "
					+ "			SET status ='A',"
					+ "			updated_by='"+user.getId()+"',"
					+ "			updated_at='"+time+"' where id = ?", id);
			
			jdbcTemplate.update("UPDATE lims_sampling_infos SET verify_by ='"+user.getId()+"' where id =fnc_sample_id('"+id+"')::uuid ");
	}
	
	
	class ResultInfoRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CommonInfo info = new CommonInfo();
			 info.setUdSampleNo(rs.getString("ud_sample_no"));
			 info.setMaterialName(rs.getString("material_name"));
			 info.setSampleDetails(rs.getString("sample_details"));
			 info.setFromDeptName(rs.getString("from_dept_nm"));
			 info.setReceiveDate(rs.getDate("receive_at"));
			 info.setSampleId((UUID)rs.getObject("sample_id"));
			 info.setSampleRcvId((UUID)rs.getObject("sample_rcv_id"));
			 info.setRcvQty(rs.getString("rcv_qty"));
			 info.setStUnitName(rs.getString("st_unit_name"));
			 info.setManufactureName(rs.getString("manufacture_name"));
			 info.setMfgResultDate(rs.getString("mfg_date"));
			 info.setExpResultDate(rs.getString("exp_date"));
			 info.setBatchNo(rs.getString("batch"));
			 info.setRemarks(rs.getString("remarks"));
			 info.setOpinion(rs.getString("opinion"));
			 info.setTestReportNo(rs.getString("test_report_no"));
			 
			return info;
		}
	}
	
	class ReceiveInfoRowMapper implements RowMapper<CommonInfo> {
		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CommonInfo info = new CommonInfo();
			
			  info.setId((UUID)rs.getObject("id"));
			  info.setSampleId((UUID)rs.getObject("sample_id"));
			  info.setUdCssNo(rs.getString("ud_css_no"));
			  info.setWhRequestId((UUID)rs.getObject("wh_request_id"));
			  info.setReqDate(rs.getString("qc_req_dt"));
			  info.setStatus(rs.getString("dist_status"));
			  info.setReqType((UUID)rs.getObject("req_type"));
			  info.setReqTypeName(rs.getString("req_type_name"));
			  info.setEffectivDate(rs.getDate("effective_date"));
			  info.setRevisionNo(rs.getString("revision_no"));
			  info.setFormNo(rs.getString("form_no"));
			  info.setInvoiceNo(rs.getString("invoice_no"));
			  info.setChalanNo(rs.getString("chalan_no"));
			  info.setLcNo(rs.getString("lc_no"));
			  info.setFromDeptNo((UUID)rs.getObject("from_dept_no"));
			  info.setFromDeptName(rs.getString("from_dept_nm"));
			  info.setToDeptNo((UUID)rs.getObject("to_dept_no"));
			  info.setToDeptName(rs.getString("to_dept_nm"));
			  info.setGrNo(rs.getString("gr_no"));
			  info.setMaterialId((UUID)rs.getObject("material_id"));
			  info.setMaterialName(rs.getString("material_name"));
			  info.setMaterialCode(rs.getString("material_code"));
			  info.setMaterialTypeId((UUID)rs.getObject("material_type_id"));
			  info.setMaterialTypeName(rs.getString("material_type_nm"));
			  info.setRcvQty(rs.getString("rcv_qty"));
			  info.setManufactureName(rs.getString("manufacture_name"));
			/*
			 * info.setMfgDate(rs.getDate("mfg_date"));
			 * info.setExpDate(rs.getDate("exp_date"));
			 * info.setBatchNo(rs.getString("batch"));
			 */
			  info.setCountryId((UUID)rs.getObject("country_id"));
			  info.setCountryName(rs.getString("country_nm"));
			  info.setSampleDetails(rs.getString("sample_details"));
			  info.setIsChemical(rs.getString("is_chemical"));
			  info.setIsMicrobiological(rs.getString("is_microbiological"));
			  info.setTotalAmount(rs.getString("total_amt"));
			  info.setUdSampleNo(rs.getString("ud_sample_no"));
			  info.setClientName(rs.getString("client_name"));
			  info.setClientId(rs.getString("client_id"));
			  info.setMethodName(rs.getString("method_name"));
			  info.setStorageCondition(rs.getString("storage_condition"));
			  info.setSampleProcedure(rs.getString("sample_procedure"));
			  info.setBoothName(rs.getString("booth_name"));
			  info.setWorkInstName(rs.getString("inst_name"));
			  info.setPrecautionTaken(rs.getString("precaution_taken"));
			  info.setSamplingDate(rs.getDate("sampling_date"));
			  info.setSamplingByName(rs.getString("sampling_name"));
			  info.setRemarks(rs.getString("sample_desc"));
			  info.setReceiveDate(rs.getDate("receive_at"));
			  info.setSampleRcvDesc(rs.getString("sample_desc_rcv"));
			  info.setArnNo(rs.getString("arn_no"));
			  info.setStqty(rs.getString("st_qty"));
			  info.setStUnitName(rs.getString("st_unit_name"));
			  info.setStRoomName(rs.getString("st_room_name"));
			  info.setStEquipmentName(rs.getString("st_equ_name"));
			  info.setStRackName(rs.getString("st_rack_name"));
			  info.setRetqty(rs.getString("ret_qty"));
			  info.setRetUnitName(rs.getString("ret_unit_name"));
			  info.setRetEquipmentName(rs.getString("ret_equ_name"));
			  info.setRetRoomName(rs.getString("ret_room_name"));
			  info.setRetRackName(rs.getString("ret_rack_name"));
			  info.setSampleRcvId((UUID)rs.getObject("sample_rcv_id"));
			  info.setTestParameterNo(rs.getString("test_parameter_no"));
			  info.setTestParameterId((UUID)rs.getObject("test_parameter_id"));
			  info.setTestParameterName(rs.getString("test_parameter_name"));
			  info.setSpecification(rs.getString("specification"));
			  info.setReferenceId((UUID)rs.getObject("reference_id"));
			  info.setReferenceName(rs.getString("reference_name"));
			  info.setTestMethodId((UUID)rs.getObject("test_method_id"));
			  info.setTestMethodName(rs.getString("test_method_name"));
			  info.setTestUnitId((UUID)rs.getObject("test_unit_id"));
			  info.setTestUnitName(rs.getString("test_unit_name"));
			  info.setEquipmentName(rs.getString("equipment_name"));
			  info.setAnalystId((UUID)rs.getObject("analyst_id"));
			  info.setAnalystName(rs.getString("analyst_name"));
			  info.setAnalystEmpId(rs.getString("analyst_emp_id"));
			  info.setResultStatus(rs.getString("result_status"));
			  info.setDistributionBy(rs.getString("dist_by"));
			  info.setDistributionAt(rs.getTimestamp("distribution_at"));
			 
			return info;
		}
	}
	
	class ChildInfoRowMapper implements RowMapper<CommonInfo> {

		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CommonInfo info = new CommonInfo();
			
			  info.setId((UUID)rs.getObject("result_id"));
			  info.setSampleId((UUID)rs.getObject("sample_id"));
			  info.setTestParameterName(rs.getString("test_parameter_name"));
			  info.setSpecification(rs.getString("specification"));
			  info.setTestResult(rs.getString("test_result"));
			  info.setUnitName(rs.getString("unit_name"));
			  info.setReferenceName(rs.getString("reference_name"));
			  info.setResultStatus(rs.getString("result_status"));
			  info.setIsAccept(rs.getString("is_accept"));
			  info.setAnalystName(rs.getString("analyst_name"));
			  info.setAnalystEmpId(rs.getString("analyst_emp_id"));
			  info.setReceiveAt(rs.getString("receive_at"));
			  info.setTestDate(rs.getString("test_date"));
			  info.setDocLocation(rs.getString("doc_location"));
			  if(rs.getTimestamp("end_date") !=null) { 
				  info.setTakenTime(Utility.computeTimeDiffNew(rs.getTimestamp("start_date"), rs.getTimestamp("end_date")));
				  }else {
					  info.setTakenTime(""); 
					  
				  }
			return info;
		}
		
	}
	class SingleRcvInfoRowMapper implements RowMapper<CommonInfo> {
		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CommonInfo info = new CommonInfo();
			
			  info.setId((UUID)rs.getObject("id"));
			  info.setAnalystId((UUID)rs.getObject("analyst_id"));
			  info.setAnalystName(rs.getString("analyst_name"));
			  info.setArnNo(rs.getString("arn_no"));
			  info.setSampleId((UUID)rs.getObject("sample_id"));
			  info.setMaterialName(rs.getString("material_name"));
			  info.setReceiveDate(rs.getDate("receive_at"));
			  info.setChemiDistDate(rs.getDate("chemi_dist_at"));
			  info.setMicroDistDate(rs.getDate("micro_dist_at"));
			  info.setDistributionAt(rs.getTimestamp("distribution_at"));
			  info.setResultStatus(rs.getString("result_status"));
			 
			return info;
		}
	}
	class AnalystRcvPendingInfoRowMapper implements RowMapper<CommonInfo> {
		@Override
		public CommonInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			CommonInfo info = new CommonInfo();
			
			  info.setId((UUID)rs.getObject("id"));
			  info.setAnalystId((UUID)rs.getObject("analyst_id"));
			  info.setAnalystName(rs.getString("analyst_name"));
			  info.setArnNo(rs.getString("arn_no"));
			  info.setSampleId((UUID)rs.getObject("sample_id"));
			  info.setMaterialName(rs.getString("material_name"));
			  info.setReceiveDate(rs.getDate("receive_at"));
			  info.setChemiDistDate(rs.getDate("chemi_dist_at"));
			  info.setMicroDistDate(rs.getDate("micro_dist_at"));
			  info.setDistributionAt(rs.getTimestamp("distribution_at"));
			  info.setResultStatus(rs.getString("result_status"));
			  info.setIsAnalystRcv(rs.getString("is_analyst_rcv"));
			  System.out.println("ANL Pending : "+rs.getString("is_analyst_rcv"));
			 
			return info;
		}
	}
	
	
}
