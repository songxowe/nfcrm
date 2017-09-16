package com.sd.farmework.common.util;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <p>
 * Description: This class provide a set of utility method for processing
 * java.sql.Date.
 * </p>
 * 
 * @author <a href="mailto:sunyi4j@gmail.com">Roy</a> on Apr 5, 2012
 */
public class SqlDateUtil {
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	public static final String DEFAULT_TIEMSTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Default format: yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String currentDateString() {
		return currentDateString(DEFAULT_DATE_PATTERN);
	}

	/**
	 * Return the date string by specified pattern
	 * 
	 * @param pattern
	 *            Specified date pattern
	 * @return String
	 */
	public static String currentDateString(String pattern) {
		SimpleDateFormat simpleFormat = new SimpleDateFormat(pattern);
		String result = simpleFormat.format(new Date(System.currentTimeMillis()));
		return result;
	}

	/**
	 * Get the current Date object
	 * 
	 * @return Date
	 */
	public static Date currentDate() {
		return new Date(System.currentTimeMillis());
	}
	
	/**
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date) {
		return getDateString(date, DEFAULT_DATE_PATTERN);
	}
	
	/**
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getDateString(Date date, String pattern) {
		SimpleDateFormat simpleFormat = new SimpleDateFormat(pattern);
		String result = simpleFormat.format(date);
		return result;
	}

	/**
	 * Parse specified year, month and day to Date object.
	 * 
	 * @param year
	 *            int
	 * @param month
	 *            int
	 * @param day
	 *            int
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parseDate(int year, int month, int day) throws ParseException {
		validateDate(year, month, day);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		Date date = new Date(calendar.getTimeInMillis());
		return date;
	}

	/**
	 * Parse specified dataStr to Date object use specified date pattern.
	 * 
	 * @param dateStr
	 *            Date string
	 * @param pattern
	 *            Specified date pattern
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parseDate(String dateStr, String pattern) throws ParseException {
		SimpleDateFormat simpleFormat = new SimpleDateFormat(pattern);
		return new Date(simpleFormat.parse(dateStr).getTime());
	}

	/**
	 * Parse specified dataStr to Date object use default date pattern.
	 * 
	 * @param dateStr
	 *            Date string
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parseDate(String dateStr) throws ParseException {
		return parseDate(dateStr, DEFAULT_DATE_PATTERN);
	}

	/**
	 * Get current Timestamp string. Default format: yyyy-MM-dd HH:mm:ss
	 * 
	 * @return String
	 */
	public static String currentTimestampString() {
		return currentTimestampString(DEFAULT_TIEMSTAMP_PATTERN);
	}

	/**
	 * Return the Timestamp string by specific pattern
	 * 
	 * @param pattern
	 *            Specified timestamp pattern
	 * @return String
	 */
	public static String currentTimestampString(String pattern) {
		SimpleDateFormat simpleFormat = new SimpleDateFormat(pattern);
		String result = simpleFormat.format(new Timestamp(System.currentTimeMillis()));
		return result;
	}
	
	/**
	 * Get current Timestamp object.
	 * 
	 * @return Timestamp
	 */
	public static Timestamp currentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * @param timestamp
	 * @return
	 */
	public static String getTimestampString(Timestamp timestamp) {
		return getTimestampString(timestamp, DEFAULT_TIEMSTAMP_PATTERN);
	}
	
	/**
	 * @param timestamp
	 * @param pattern
	 * @return
	 */
	public static String getTimestampString(Timestamp timestamp, String pattern) {
		SimpleDateFormat simpleFormat = new SimpleDateFormat(pattern);
		String result = simpleFormat.format(timestamp);
		return result;
	}

	/**
	 * Get Timestamp object base on specified information.
	 * 
	 * @param year
	 *            int
	 * @param month
	 *            int
	 * @param day
	 *            int
	 * @param hour
	 *            int
	 * @param minute
	 *            int
	 * @param second
	 *            int
	 * @return Timestamp
	 * @throws ParseException
	 */
	public static Timestamp parseTimestamp(int year, int month, int day, int hour, int minute, int second) throws ParseException {
		validateTimestamp(year, month, day, hour, minute, second);
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, hour, minute, second);
		Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
		return timestamp;
	}

	/**
	 * Parse timestamp string to Timestamp object by specified timestamp
	 * pattern.
	 * 
	 * @param timestampStr
	 *            Original timestamp string
	 * @param pattern
	 *            Specified timestamp string
	 * @return Timestamp
	 * @throws ParseException
	 */
	public static Timestamp parseTimestamp(String timestampStr, String pattern) throws ParseException {
		SimpleDateFormat simpleFormat = new SimpleDateFormat(pattern);
		return new Timestamp(simpleFormat.parse(timestampStr).getTime());
	}

	/**
	 * Parse timestamp string to Timestamp object by specified timestamp
	 * pattern.
	 * 
	 * @param timestampStr
	 *            Original timestamp string
	 * @param pattern
	 *            Specified timestamp string
	 * @return Timestamp
	 * @throws ParseException
	 */
	public static Timestamp parseTimestamp(String timestampStr) throws ParseException {
		return parseTimestamp(timestampStr, DEFAULT_DATE_PATTERN);
	}

	/**
	 * Validate timestamp.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @throws ParseException
	 */
	private static void validateTimestamp(int year, int month, int day, int hour, int minute, int second) throws ParseException {
		if (year < 1900 || year > 2400) {
			throw new ParseException("Date parse error: the year is invalid.", 0);
		}
		if (month < 1 || month > 12) {
			throw new ParseException("Date parse error: the month is invalid.", 0);
		}
		if (day < 1 || day > 31) {
			throw new ParseException("Date parse error: the day is invalid.", 0);
		}
		if (hour < 0 || hour > 23) {
			throw new ParseException("Date parse error: the day is invalid.", 0);
		}
		if (minute < 0 || minute > 59) {
			throw new ParseException("Date parse error: the day is invalid.", 0);
		}
		if (second < 0 || second > 59) {
			throw new ParseException("Date parse error: the day is invalid.", 0);
		}
	}

	/**
	 * Valite date.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @throws ParseException
	 */
	private static void validateDate(int year, int month, int day) throws ParseException {
		if (year < 1900 || year > 2400)
			throw new ParseException("Date parse error: the year is invalid.", 0);
		if (month < 1 || month > 12)
			throw new ParseException("Date parse error: the month is invalid.", 0);
		if (day < 1 || day > 31)
			throw new ParseException("Date parse error: the day is invalid.", 0);
	}
	/**
	 * Judge whether the specified object is null or empty
	 * 
	 * @param obj
	 * @return boolean
	 */
	public static boolean isBlank(Object obj) {
		 
		if(obj==null){
			return true;
		}else{
			return false;
		}
	}
}
