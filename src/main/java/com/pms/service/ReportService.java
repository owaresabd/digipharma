package com.pms.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class ReportService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ServletContext servletContext;
	private static String OS = System.getProperty("os.name").toLowerCase();

	private static String separetor = null;

	public String generateReport(String fileName, String udId, Map<String, Object> parameters)
			throws SQLException, JRException, IOException {
		Connection connection = null;
		try {
			if (isWindows()) {
				separetor = "\\";
			} else {
				separetor = "//";
			}
			String path = servletContext.getRealPath("/WEB-INF/view/report");
			String filePathPdf = servletContext.getRealPath("/WEB-INF/assets/report");
			JasperReport jasperReport = JasperCompileManager.compileReport(path + separetor + fileName + ".jrxml");
			connection = jdbcTemplate.getDataSource().getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,connection);
			JasperExportManager.exportReportToPdfFile(jasperPrint, filePathPdf + separetor + udId + ".pdf");
			return udId + ".pdf";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		finally {
			
			if (connection != null) {
				connection.close();
				connection = null;
			}

		}
		 
			 
	}

	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public String equipmentReport(String fileName, String udId, Map<String, Object> parameters)
			throws SQLException, JRException, IOException {
		Connection connection = null;
		try {
			if (isWindows()) {
				separetor = "\\";
			} else {
				separetor = "//";
			}
			String path = servletContext.getRealPath("/WEB-INF/view/report");
			String filePathPdf = servletContext.getRealPath("/WEB-INF/assets/report");
			JasperReport jasperReport = JasperCompileManager.compileReport(path + separetor + fileName + ".jrxml");
			connection = jdbcTemplate.getDataSource().getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,connection);
			JasperExportManager.exportReportToPdfFile(jasperPrint, filePathPdf + separetor + udId + ".pdf");
			return udId + ".pdf";

		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {

			if (connection != null) {
				connection.close();
				connection = null;
			}

		}

	}

	public String productionReport(String fileName, String udId, Map<String, Object> parameters)
			throws SQLException, JRException, IOException {
		Connection connection = null;
		try {
			String path = servletContext.getRealPath("/WEB-INF/view/report");
			String filePathPdf = servletContext.getRealPath("/WEB-INF/assets/report");
			JasperReport jasperReport = JasperCompileManager.compileReport(path + "\\" + fileName + ".jrxml");
			connection = jdbcTemplate.getDataSource().getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,connection);
			JasperExportManager.exportReportToPdfFile(jasperPrint, filePathPdf + "\\" + udId + ".pdf");
			return udId + ".pdf";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {

			if (connection != null) {
				connection.close();
				connection = null;
			}

		}
	}

	public String qcReceiveReport(String fileName, String sampleNo, Map<String, Object> parameters)
			throws SQLException, JRException, IOException {
		Connection connection = null;
		try {
			String path = servletContext.getRealPath("/WEB-INF/view/report");
			String filePathPdf = servletContext.getRealPath("/WEB-INF/assets/report");
			JasperReport jasperReport = JasperCompileManager.compileReport(path + "\\" + fileName + ".jrxml");
			connection = jdbcTemplate.getDataSource().getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,connection);
			JasperExportManager.exportReportToPdfFile(jasperPrint, filePathPdf + "\\" + sampleNo + ".pdf");
			return sampleNo + ".pdf";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {

			if (connection != null) {
				connection.close();
				connection = null;
			}

		}
	}

	public String dsChemiReport(String fileName, String sampleNo, Map<String, Object> parameters)
			throws SQLException, JRException, IOException {
		Connection connection = null;
		try {
			String path = servletContext.getRealPath("/WEB-INF/view/report");
			String filePathPdf = servletContext.getRealPath("/WEB-INF/assets/report");
			JasperReport jasperReport = JasperCompileManager.compileReport(path + "\\" + fileName + ".jrxml");
			connection = jdbcTemplate.getDataSource().getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,connection);
			JasperExportManager.exportReportToPdfFile(jasperPrint, filePathPdf + "\\" + sampleNo + ".pdf");
			return sampleNo + ".pdf";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}finally {

			if (connection != null) {
				connection.close();
				connection = null;
			}

		}
	}

	public String dsMicroReport(String fileName, String sampleNo, Map<String, Object> parameters)
			throws SQLException, JRException, IOException {
		Connection connection = null;
		try {
			String path = servletContext.getRealPath("/WEB-INF/view/report");
			String filePathPdf = servletContext.getRealPath("/WEB-INF/assets/report");
			JasperReport jasperReport = JasperCompileManager.compileReport(path + "\\" + fileName + ".jrxml");
			connection = jdbcTemplate.getDataSource().getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
			JasperExportManager.exportReportToPdfFile(jasperPrint, filePathPdf + "\\" + sampleNo + ".pdf");
			return sampleNo + ".pdf";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {

			if (connection != null) {
				connection.close();
				connection = null;
			}

		}
	}

	public String materialReport(String fileName, String sampleNo, Map<String, Object> parameters)
			throws SQLException, JRException, IOException {
		Connection connection = null;
		try {
			String path = servletContext.getRealPath("/WEB-INF/view/report");
			String filePathPdf = servletContext.getRealPath("/WEB-INF/assets/report");
			JasperReport jasperReport = JasperCompileManager.compileReport(path + "\\" + fileName + ".jrxml");
			connection = jdbcTemplate.getDataSource().getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,connection);
			JasperExportManager.exportReportToPdfFile(jasperPrint, filePathPdf + "\\" + sampleNo + ".pdf");
			return sampleNo + ".pdf";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {

			if (connection != null) {
				connection.close();
				connection = null;
			}

		}
	}

}
