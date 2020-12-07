package com.pms.configure.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

	private static Pattern dateFrmtPtrn = Pattern.compile("[0-9]{2}-[a-zA-Z]{3}-[0-9]{4}");

	/*------------------empty----------------------*/

	public static boolean isStrEmpty(String str) {
		if (str == null || str.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/*------------------length----------------------*/

	public static boolean isValidLength(String str, int length) {
		if (str != null && !str.isEmpty()) {
			if (str.length() > length) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	/*------------------integer ----------------------*/

	public static boolean isInteger(String str, int value) {
		if (str.matches("[0-9]+")) {
			int a = Integer.parseInt(str);
			if (a >= value) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}

	}

	/**
	 * @Arefin
	 * 
	 */
	public static boolean isIntegerRange(String str, int lowestValue, int heightValue) {
		if (str.matches("[0-9]+")) {
			int a = Integer.parseInt(str);
			if (a >= lowestValue && a <= heightValue) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}

	}

	public static boolean isDobleRange(String str, double lowestValue, double heightValue) {
		if (str.matches("[0-9]{1,13}(\\.[0-9]*)?")) {
			double a = Double.parseDouble(str);
			System.out.println(a + " ::::::::::::::::");
			if (a >= lowestValue && a <= heightValue) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/*------------------float----------------------*/

	public static boolean isFloat(String str, int ngValue, int poValue) {
		if (str.matches("[0-9]+.[0-9]+")) {
			float a = Float.parseFloat(str);
			if (a >= poValue && a <= ngValue) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	/*------------------double----------------------*/

	public static boolean isDouble(String str, int ngValue, int poValue) {
		if (str.matches("[0-9]+.[0-9]+")) {
			double a = Double.parseDouble(str);
			if (a >= poValue && a <= ngValue) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	/*
	 * check given string date is valid or not.example:2017-Feb-29 is invalid date
	 */

	public static boolean isValidDate(String dateStr) {
		if (dateStr.equals("") || dateStr == null) {
			dateStr = "01-Jan-1900";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

		dateFormat.setLenient(false);
		try {
			dateFormat.parse(dateStr.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;

	}

	/* validate date format with regular expression */
	public static boolean isValidDateFormat(String dateStr) {
		if (dateStr.equals("") || dateStr == null) {
			dateStr = "01-Jan-1900";
		}
		Matcher mtch = dateFrmtPtrn.matcher(dateStr);
		if (mtch.matches()) {
			return true;
		}
		return false;
	}

	public static boolean dateValidation(String dateStr) {

		boolean isValidaDateFormate = isValidDateFormat(dateStr);
		boolean isValidDate;

		if (isValidaDateFormate == true) {
			isValidDate = isValidDate(dateStr);
			if (isValidDate == true) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	/**
	 * @Arefin compares two dates (one is user given another is current Date)
	 */
	public static boolean compareDateWithCurrentDate(String start) {
		if (start.equals("") || start == null) {
			start = "01-Jan-1900";
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		String end = sdf.format(new Date());

		Date date1;
		Date date2;
		try {
			date1 = sdf.parse(start);
			date2 = sdf.parse(end);
			if (date1.compareTo(date2) > 0) { // Date1 is after Date2
				return false;
			}
		} catch (ParseException e) {
			return true;
		}

		return true;

	}

	/**
	 * @Arefin add year with Date and return String
	 */

	public static String addYears(String date, int years) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar cal = Calendar.getInstance();

		if (date.equals("") || date == null) {
			date = dateFormat.format(new Date());
		}

		try {
			cal.setTime(dateFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(Calendar.YEAR, years);
		String convertedDate = dateFormat.format(cal.getTime());
		return convertedDate;
	}

	/**
	 * @Arefin compares two dates
	 */
	public static boolean compareDates(String start, String end) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date1;
		Date date2;
		try {
			date1 = sdf.parse(start);
			date2 = sdf.parse(end);
			if (date1.compareTo(date2) > 0) { // Date1 is after Date2
				return false;
			}
		} catch (ParseException e) {
			return true;
		}

		return true;

	}

	/**
	 * @Arefin check employee's age according to company policy
	 */
	public static boolean isValidBirthDay(String birthDate, String currentDate, int years) {
		if (birthDate.equals("") || birthDate == null) {
			birthDate = "01-Jan-1900";
		}
		String originalDate = addYears(currentDate, years);
		boolean isValidBithDate = compareDates(birthDate, originalDate);

		if (!isValidBithDate) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * ^[a-zA-Z]([\\.]?)[\\sa-zA-Z]*[\\.]?+$"
	 * 
	 * @Arefin ensure String is not allowed to have number and special character
	 */
	public static boolean isCharacter(String str) {
		Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z]*(\\.[\\s]?[a-zA-Z]+)*[\\.]?+$");

		if (str != null && !str.trim().isEmpty()) {
			if (pattern.matcher(str).matches()) {
				return true;
			}
		}

		return false;

	}

	/**
	 * @Arefin ensure String is allowed to have number and/or character
	 */
	public static boolean isAlphanumeric(String str) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+[\\.]?[a-zA-Z0-9]*+$");
		if (str != null && !str.trim().isEmpty()) {
			if (pattern.matcher(str).matches()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @Arefin ensure String is allowed to have only number
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("^[0-9][0-9]*([\\.][0-9]{1,15}+)?+$");
		if (str != null && !str.trim().isEmpty()) {
			if (pattern.matcher(str).matches()) {
				return true;
			}
		}

		return false;

	}

	public static boolean isAlphanumericSpace(String str) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9]*(\\.[\\s]?[a-zA-Z0-9]+)*[\\.]?+$");

		if (str != null && !str.trim().isEmpty()) {
			if (pattern.matcher(str).matches()) {
				return true;
			}
		}

		return false;

	}

	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern pattern = java.util.regex.Pattern.compile(ePattern);
		Matcher matcher = pattern.matcher(email);
		boolean isMatched = matcher.matches();
		if (isMatched == true) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidWebSite(String website) {
		String ePattern = "^[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		Pattern pattern = java.util.regex.Pattern.compile(ePattern);
		Matcher matcher = pattern.matcher(website);
		boolean isMatched = matcher.matches();
		if (isMatched == true) {
			return true;
		} else {
			return false;
		}
	}

	// Dropdown validation
	@SuppressWarnings("null")
	public static boolean isValidSelection(Map<String, String> requestMap, String str) {
		if (str != null || !str.trim().isEmpty()) {
			if (requestMap.containsKey(str)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("null")
	public static boolean isValidSelection(Map<String, String> requestMap, String strCode, String srtName) {
		if (strCode != null || !strCode.trim().isEmpty() && srtName != null || !srtName.trim().isEmpty()) {
			for (Map.Entry<String, String> entry : requestMap.entrySet()) {
				if (entry.getKey().equals(strCode) && entry.getValue().equals(srtName)) {
					return true;
				}
			}
		}
		return false;
	}

	public static List<String> duplicateCodeOrName(final String[] arrayValue, String module, String propertyName,
			String className) {
		List<String> errors = new ArrayList<>();
		Set<String> lump = new HashSet<String>();
		if (arrayValue != null) {
			for (String i : arrayValue) {
				if (lump.contains(i)) {
					errors.add(Resource.loadProperty(module, propertyName) + "###" + i + "###" + className);
					return errors;
				}
				lump.add(i);
			}
		}

		return errors;
	}

	/**
	 * @Arefin compares two dates (one is user given another is current Date)
	 */
	public static boolean compareDateWithPreviousDate(String start) {
		if (start.equals("") || start == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		String cur = sdf.format(new Date());

		Date date1;
		Date date2;
		try {
			date1 = sdf.parse(start);
			date2 = sdf.parse(cur);
			if (date1.compareTo(date2) < 0) { // Date1 is before Date2
				return false;
			}
		} catch (ParseException e) {
			return true;
		}

		return true;

	}

}
