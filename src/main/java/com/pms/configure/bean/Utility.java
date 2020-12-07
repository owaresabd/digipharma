package com.pms.configure.bean;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Utility {
	
	private static Date stringDate;

	/**
	 * @Arefin This method is used to convert StringDate into java.util.date
	 */

	public static Date stringToDate(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy"); /* This stringDate is come from UI */

		if (strDate != null && !strDate.trim().isEmpty()) {
			try {
				stringDate = sdf.parse(strDate);
			//	System.out.println(strDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return stringDate;
	}

	/**
	 * @Arefin This method is used to convert java.util.date to java.sql.date
	 */

	public static Date fromUtiltoSql(Date date) {
		java.sql.Date sqlDate = null;
		if (date != null) {
			sqlDate = new java.sql.Date(date.getTime());
		}
		return sqlDate;
	}

	/**
	 * @Arefin This method is used to convert stringDate to java.sql.date
	 */
	public static Date toSqlDate(String strDate) {
		stringDate = stringToDate(strDate);
		return fromUtiltoSql(stringDate);
	}

	/**
	 * @Arefin This method is used to convert StringDate into java.util.date in
	 *         yyyy-mm-dd format
	 */

	public static Date stringToDateyyyyMMdd(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); /* This stringDate is come from UI */
		if (strDate != null && !strDate.trim().isEmpty()) {
			try {
				stringDate = sdf.parse(strDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return stringDate;
	}

	/**
	 * @Arefin This method is used to convert stringDate to java.sql.date
	 */
	public static Date toSqlDateyyyyMMdd(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlDate;

		if (strDate != null && !strDate.trim().isEmpty()) {
			try {
				stringDate = (Date) sdf.parse(strDate);
			} catch (ParseException e) {
				System.out.println("Please enter a valid date! Format is yyyy-MM-dd");
			}
		}
		sqlDate = new java.sql.Date(stringDate.getTime());
		return sqlDate;
	}

	/**
	 * @Arefin This method is used to get the difference between two String days by
	 *         converting then strDate to java.util.date
	 */
	public static long daysBetween(String start, String end) {
		Date one = stringToDateyyyyMMdd(start);
		Date two = stringToDateyyyyMMdd(end);
		int difference = (int) ((one.getTime() - two.getTime()) / 86400000);
		return Math.abs(difference);
	}

	/**
	 * @abdurRahim This method is used to add day with given strDate
	 */

	public static Date addDays(String date, int days) {
		Calendar cal = Calendar.getInstance();
		Date utilDate = stringToDateyyyyMMdd(date);
		cal.setTime(utilDate);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
		return sqlDate;
	}
	
	/**
	 * @abdurRahim This method is used to add day with given strDate
	 */
	public static Date addMonths(String date, int nbMonths) {
		Calendar cal = Calendar.getInstance();
		Date utilDate = stringToDateyyyyMMdd(date);
		cal.setTime(utilDate);
		cal.add(Calendar.MONTH, nbMonths); // minus number would decrement the days
		java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
		return sqlDate;
	}
	
	/**
	 * @abdurRahim This method is used to add day with given strDate
	 */
	public static Date addYears(String date, int years) {
		Calendar cal = Calendar.getInstance();
		Date utilDate = stringToDateyyyyMMdd(date);
		cal.setTime(utilDate);
		cal.add(Calendar.YEAR, years); // minus number would decrement the days
		java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());
		return sqlDate;
	}

	public static List<UUID> toUUID(String[] str) {
		List<UUID> uuids = new ArrayList<UUID>();
		if (str != null) {
			for (int i = 0; i < str.length; i++) {
				if (toUUID(str[i]) != null) {
					uuids.add(i, toUUID(str[i]));
				}
			}
		}
		return uuids;
	}

	public static UUID toUUID(String str) {
		if (str != null && !str.trim().isEmpty()) {
			try {
				return UUID.fromString(str);
			} catch (Exception e) {
				return null;
			}
		}

		return null;
	}

	public static java.sql.Timestamp getCurrentTimeStamp() {
		// 1) create a java calendar instance
		Calendar calendar = Calendar.getInstance();

		// 2) get a java.util.Date from the calendar instance.
		// this date will represent the current instant, or "now".
		Date now = calendar.getTime();

		// 3) a java current time (now) instance
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		return currentTimestamp;
	}

	public static java.sql.Date getSqlDate(String date) {
		String sDate = date;
		Date dateUtil = Utility.stringToDate(sDate);
		java.sql.Date dateSql = (java.sql.Date) fromUtiltoSql(dateUtil);
		return dateSql;
	}

	

	public static String convertToMoney(double amount, String currency) {
		NumToWords w = new NumToWords();
		String amt = Double.toString(amount);
		String str2 = "";
		String beforeDecimal = " Taka";
		String afterDecimal = " Paisa";

		if (currency.equals("BDT")) {
			beforeDecimal = " Taka";
			afterDecimal = " Paisa";
		} else if (currency.equals("USD")) {
			beforeDecimal = " Dollars";
			afterDecimal = " Cents";
		} else if (currency.equals("GBP")) {
			beforeDecimal = " Pounds";
			afterDecimal = " Pence";
		}

		int taka = Integer.parseInt(amt.split("\\.")[0]);
		String str1 = w.convert(taka);
		if (!str1.equals("")) {
			str1 += beforeDecimal;
		}

		int paisa = Integer.parseInt(amt.split("\\.")[1]);
		if (paisa != 0) {
			str2 += " and";
			str2 = w.convert(paisa);
			str2 += afterDecimal;
		}

		String str3 = str1 + str2 + " Only";
		return str3;
	}

	/**
	 * @param list
	 * @param keyName
	 * @param valueName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> ListToMap(@SuppressWarnings("rawtypes") List list, String keyName,
			String valueName) {
		Map<K, V> result = new HashMap<K, V>();

		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			Class<?> c = o.getClass();
			Field k;
			Field v;
			try {
				k = c.getDeclaredField(keyName);
				v = c.getDeclaredField(valueName);
				k.setAccessible(true);
				v.setAccessible(true);
				result.put((K) k.get(o), (V) v.get(o));
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;
	}

	public static <T> ArrayList<T> uniquefy(ArrayList<T> myList) {

		ArrayList<T> uniqueArrayList = new ArrayList<T>();
		for (int i = 0; i < myList.size(); i++) {
			if (!uniqueArrayList.contains(myList.get(i))) {
				uniqueArrayList.add(myList.get(i));
			}
		}

		return uniqueArrayList;
	}

	/* get number of days in a month according to month and year */
	public static int numberOfDaysInMonth(int month, int year) {
		Calendar monthStart = new GregorianCalendar(year, month, 0);
		return monthStart.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static String getRandomUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	public static String generateRandomString() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
		SecureRandom rnd = new SecureRandom();
		StringBuilder str = new StringBuilder(characters.length());
		for (int i = 0; i < characters.length(); i++)
			str.append(characters.charAt(rnd.nextInt(characters.length())));
		return str.toString();
	}

	public static String utilDateToStr(Date date) {
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		Date today = Calendar.getInstance().getTime();
		String strDate = df.format(today);
		return strDate;
	}

	public static String convertToCurrency(String num) {
		BigDecimal bd = new BigDecimal(num);
		long number = bd.longValue();
		long no = bd.longValue();
		int decimal = (int) (bd.remainder(BigDecimal.ONE).doubleValue() * 100);
		int digits_length = String.valueOf(no).length();
		int i = 0;
		ArrayList<String> str = new ArrayList<>();
		HashMap<Integer, String> words = new HashMap<>();
		words.put(0, "");
		words.put(1, "One");
		words.put(2, "Two");
		words.put(3, "Three");
		words.put(4, "Four");
		words.put(5, "Five");
		words.put(6, "Six");
		words.put(7, "Seven");
		words.put(8, "Eight");
		words.put(9, "Nine");
		words.put(10, "Ten");
		words.put(11, "Eleven");
		words.put(12, "Twelve");
		words.put(13, "Thirteen");
		words.put(14, "Fourteen");
		words.put(15, "Fifteen");
		words.put(16, "Sixteen");
		words.put(17, "Seventeen");
		words.put(18, "Eighteen");
		words.put(19, "Nineteen");
		words.put(20, "Twenty");
		words.put(30, "Thirty");
		words.put(40, "Forty");
		words.put(50, "Fifty");
		words.put(60, "Sixty");
		words.put(70, "Seventy");
		words.put(80, "Eighty");
		words.put(90, "Ninety");
		String digits[] = { "", "Hundred", "Thousand", "Lakh", "Crore" };
		while (i < digits_length) {
			int divider = (i == 2) ? 10 : 100;
			number = no % divider;
			no = no / divider;
			i += divider == 10 ? 1 : 2;
			if (number > 0) {
				int counter = str.size();
				String plural = (counter > 0 && number > 9) ? "s" : "";
				String tmp = (number < 21) ? words.get(Integer.valueOf((int) number)) + " " + digits[counter] + plural
						: words.get(Integer.valueOf((int) Math.floor(number / 10) * 10)) + " "
								+ words.get(Integer.valueOf((int) (number % 10))) + " " + digits[counter] + plural;
				str.add(tmp);
			} else {
				str.add("");
			}
		}

		Collections.reverse(str);
		String Taka = String.join(" ", str).trim();

		String paisa = (decimal) > 0
				? " And " + words.get(Integer.valueOf((int) (decimal - decimal % 10))) + " "
						+ words.get(Integer.valueOf((int) (decimal % 10))) + " Paisa"
				: "";
		return "Taka " + Taka + paisa + " Only";
	}

	public static String getCurrentMonth() {
		String[] monthName = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };

		Calendar cal = Calendar.getInstance();
		String month = monthName[cal.get(Calendar.MONTH)];

		return month;
	}

	public static String getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		int ye = cal.get(Calendar.YEAR);
		String year = "" + ye;

		return year;
	}

	public static LocalDate getPreviousDate(String input) throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");

		LocalDate localDate = LocalDate.parse(input, formatter);
		java.util.Date dat = java.sql.Date.valueOf(localDate);

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(dat);
		cal1.add(Calendar.DAY_OF_YEAR, -1);
		Date previousDate = cal1.getTime();

		LocalDate date = previousDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		return date;
	}

	public static String lastDayofPreviousMonth() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.set(Calendar.DATE, 1);
		aCalendar.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDateOfPreviousMonth = aCalendar.getTime();
		String lastday = dateFormat.format(lastDateOfPreviousMonth).toString();

		return lastday;
	}

	public static String to_utf8(String fieldvalue) throws UnsupportedEncodingException {

		String fieldvalue_utf8 = new String(fieldvalue.getBytes("ISO-8859-1"), "UTF-8");
		return fieldvalue_utf8;
	}

	
	public static String computeTimeDiff(String start, String end) {
		
		System.out.println(start+"/"+end);
		Date date1 = new Date(start);
		Date date2 = new Date(end);

		long diffInMillies = date2.getTime() - date1.getTime();
		System.out.println("inside method"+date2.getTime() +"/"+ date1.getTime());
		// create the list
		List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
		Collections.reverse(units);

		// create the result map of TimeUnit and difference
		long milliesRest = diffInMillies;
		int DAYS = 0;
		int HOURS = 0;
		int MINUTES = 0;
		for (int i = 0; i < units.size(); i++) {
			// calculate difference in millisecond
			long diff = units.get(i).convert(milliesRest, TimeUnit.MILLISECONDS);
			long diffInMilliesForUnit = units.get(i).toMillis(diff);
			milliesRest = milliesRest - diffInMilliesForUnit;

			if (i == 0) {
				DAYS = (int) diff;
			} else if (i == 1) {
				HOURS = (int) diff;
			} else if (i == 2) {
				MINUTES = (int) diff;
			}
		}

		if (DAYS > 0 && HOURS <= 0 && MINUTES <= 0) {
			return DAYS + " Day";
		} else if (DAYS <= 0 && HOURS >= 0 && MINUTES <= 0) {
			return HOURS + " Hour";
		} else if (DAYS <= 0 && HOURS <= 0) {
			return MINUTES + " Minute";
		} else if (DAYS > 0 && HOURS >= 0 && MINUTES <= 0) {
			return DAYS + " Day " + HOURS + " Hour";
		} else if (DAYS > 0 && HOURS <= 0 && MINUTES >= 0) {
			return DAYS + " Day " + MINUTES + " Minute";
		} else if (DAYS <= 0 && HOURS >= 0 && MINUTES >= 0) {
			return HOURS + " Hour " + MINUTES + " Minute";
		} else {
			return DAYS + " Day " + HOURS + " Hour " + MINUTES + " Minute";
		}

	
		
	}
public static String computeTimeDiffNew(Timestamp start, Timestamp end) {
		
		
		long diffInMillies = end.getTime() - start.getTime();
		//System.out.println("inside method"+date2.getTime() +"/"+ date1.getTime());
		// create the list
		List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
		Collections.reverse(units);

		// create the result map of TimeUnit and difference
		long milliesRest = diffInMillies;
		int DAYS = 0;
		int HOURS = 0;
		int MINUTES = 0;
		for (int i = 0; i < units.size(); i++) {
			// calculate difference in millisecond
			long diff = units.get(i).convert(milliesRest, TimeUnit.MILLISECONDS);
			long diffInMilliesForUnit = units.get(i).toMillis(diff);
			milliesRest = milliesRest - diffInMilliesForUnit;

			if (i == 0) {
				DAYS = (int) diff;
			} else if (i == 1) {
				HOURS = (int) diff;
			} else if (i == 2) {
				MINUTES = (int) diff;
			}
		}

		if (DAYS > 0 && HOURS <= 0 && MINUTES <= 0) {
			return DAYS + " Day";
		} else if (DAYS <= 0 && HOURS >= 0 && MINUTES <= 0) {
			return HOURS + " Hour";
		} else if (DAYS <= 0 && HOURS <= 0) {
			return MINUTES + " Minute";
		} else if (DAYS > 0 && HOURS >= 0 && MINUTES <= 0) {
			return DAYS + " Day " + HOURS + " Hour";
		} else if (DAYS > 0 && HOURS <= 0 && MINUTES >= 0) {
			return DAYS + " Day " + MINUTES + " Minute";
		} else if (DAYS <= 0 && HOURS >= 0 && MINUTES >= 0) {
			return HOURS + " Hour " + MINUTES + " Minute";
		} else {
			return DAYS + " Day " + HOURS + " Hour " + MINUTES + " Minute";
		}

	
		
	}
	
	

}
