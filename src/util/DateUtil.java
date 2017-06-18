package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * ʱ�乤����
 *
 * @author xiaoleilu
 */
public class DateUtil {

    /**
     * ����
     */
    public final static long MS = 1;
    /**
     * ÿ���ӵĺ�����
     */
    public final static long SECOND_MS = MS * 1000;
    /**
     * ÿ���ӵĺ�����
     */
    public final static long MINUTE_MS = SECOND_MS * 60;
    /**
     * ÿСʱ�ĺ�����
     */
    public final static long HOUR_MS = MINUTE_MS * 60;
    /**
     * ÿ��ĺ�����
     */
    public final static long DAY_MS = HOUR_MS * 24;

    /**
     * ��׼���ڸ�ʽ
     */
    public final static String NORM_DATE_PATTERN = "yyyy-MM-dd";
    /**
     * ��׼ʱ���ʽ
     */
    public final static String NORM_TIME_PATTERN = "HH:mm:ss";
    /**
     * ��׼����ʱ���ʽ
     */
    public final static String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * HTTPͷ������ʱ���ʽ
     */
    public final static String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";

    /**
     * ��׼���ڣ�����ʱ�䣩��ʽ����
     */
    private final static SimpleDateFormat NORM_DATE_FORMAT = new SimpleDateFormat(NORM_DATE_PATTERN);
    /**
     * ��׼ʱ���ʽ����
     */
    private final static SimpleDateFormat NORM_TIME_FORMAT = new SimpleDateFormat(NORM_TIME_PATTERN);
    /**
     * ��׼����ʱ���ʽ����
     */
    private final static SimpleDateFormat NORM_DATETIME_FORMAT = new SimpleDateFormat(NORM_DATETIME_PATTERN);
    /**
     * HTTP����ʱ���ʽ����
     */
    private final static SimpleDateFormat HTTP_DATETIME_FORMAT = new SimpleDateFormat(HTTP_DATETIME_PATTERN, Locale.US);

    /**
     * ��ǰʱ�䣬��ʽ yyyy-MM-dd HH:mm:ss
     *
     * @return ��ǰʱ��ı�׼��ʽ�ַ���
     */
    public static String now() {
        return formatDateTime(new Date());
    }

    /**
     * ��ǰ���ڣ���ʽ yyyy-MM-dd
     *
     * @return ��ǰ���ڵı�׼��ʽ�ַ���
     */
    public static String today() {
        return formatDate(new Date());
    }

    // ------------------------------------ Format start ----------------------------------------------

    /**
     * �����ض���ʽ��ʽ������
     *
     * @param date   ����ʽ��������
     * @param format ��ʽ
     * @return ��ʽ������ַ���
     */
    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * ��ʽ yyyy-MM-dd HH:mm:ss
     *
     * @param date ����ʽ��������
     * @return ��ʽ���������
     */
    public static String formatDateTime(Date date) {
//		return format(d, "yyyy-MM-dd HH:mm:ss");
        return NORM_DATETIME_FORMAT.format(date);
    }

    /**
     * ��ʽ��ΪHttp�ı�׼���ڸ�ʽ
     *
     * @param date ����ʽ��������
     * @return HTTP��׼��ʽ�����ַ���
     */
    public static String formatHttpDate(Date date) {
//		return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US).format(date);
        return HTTP_DATETIME_FORMAT.format(date);
    }

    /**
     * ��ʽ yyyy-MM-dd
     *
     * @param date ����ʽ��������
     * @return ��ʽ������ַ���
     */
    public static String formatDate(Date date) {
//		return format(d, "yyyy-MM-dd");
        return NORM_DATE_FORMAT.format(date);
    }
    // ------------------------------------ Format end ----------------------------------------------

    // ------------------------------------ Parse start ----------------------------------------------

    /**
     * ���ض���ʽ������ת��ΪDate����
     *
     * @param dateString �ض���ʽ������
     * @param format     ��ʽ������yyyy-MM-dd
     * @return ���ڶ���
     */
    public static Date parse(String dateString, String format) {
        try {
            return (new SimpleDateFormat(format)).parse(dateString);
        } catch (ParseException e) {
        	e.printStackTrace();
        }
        return null;
    }

    /**
     * ��ʽyyyy-MM-dd HH:mm:ss
     *
     * @param dateString ��׼��ʽ��ʱ���ַ���
     * @return ���ڶ���
     */
    public static Date parseDateTime(String dateString) {
//		return parse(s, "yyyy-MM-dd HH:mm:ss");
        try {
            return NORM_DATETIME_FORMAT.parse(dateString);
        } catch (ParseException e) {
        	e.printStackTrace();
        }
        return null;
    }

    /**
     * ��ʽyyyy-MM-dd
     *
     * @param dateString ��׼��ʽ�������ַ���
     * @return ���ڶ���
     */
    public static Date parseDate(String dateString) {
        try {
            return NORM_DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
        	e.printStackTrace();
        }
        return null;
    }

    /**
     * ��ʽHH:mm:ss
     *
     * @param dateString ��׼��ʽ�������ַ���
     * @return ���ڶ���
     */
    public static Date parseTime(String timeString) {
        try {
            return NORM_TIME_FORMAT.parse(timeString);
        } catch (ParseException e) {
        	e.printStackTrace();
        }
        return null;
    }

    /**
     * ��ʽ��<br>
     * 1��yyyy-MM-dd HH:mm:ss<br>
     * 2��yyyy-MM-dd<br>
     * 3��HH:mm:ss>
     *
     * @param dateStr �����ַ���
     * @return ����
     */
    public static Date parse(String dateStr) {
        int length = dateStr.length();
        try {
            if (length == DateUtil.NORM_DATETIME_PATTERN.length()) {
                return parseDateTime(dateStr);
            } else if (length == DateUtil.NORM_DATE_PATTERN.length()) {
                return parseDate(dateStr);
            } else if (length == DateUtil.NORM_TIME_PATTERN.length()) {
                return parseTime(dateStr);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    // ------------------------------------ Parse end ----------------------------------------------

    // ------------------------------------ Offset start ----------------------------------------------

    /**
     * ����
     *
     * @return ����
     */
    public static Date yesterday() {
        return offsiteDate(new Date(), Calendar.DAY_OF_YEAR, -1);
    }

    /**
     * ����
     *
     * @return ����
     */
    public static Date lastWeek() {
        return offsiteDate(new Date(), Calendar.WEEK_OF_YEAR, -1);
    }

    /**
     * �ϸ���
     *
     * @return �ϸ���
     */
    public static Date lastMouth() {
        return offsiteDate(new Date(), Calendar.MONTH, -1);
    }

    /**
     * ��ȡָ������ƫ��ָ��ʱ����ʱ��
     *
     * @param date          ��׼����
     * @param calendarField ƫ�Ƶ����ȴ�С��Сʱ���졢�µȣ�ʹ��Calendar�еĳ���
     * @param offsite       ƫ����������Ϊ���ƫ�ƣ�����Ϊ��ǰƫ��
     * @return ƫ�ƺ������
     */
    public static Date offsiteDate(Date date, int calendarField, int offsite) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(calendarField, offsite);
        return cal.getTime();
    }
    // ------------------------------------ Offset end ----------------------------------------------

    /**
     * �ж�������������ʱ��<br/>
     * ���� minuend - subtrahend �Ĳ�
     *
     * @param subtrahend ��������
     * @param minuend    ����������
     * @param diffField  ����ѡ������졢Сʱ
     * @return ���ڲ�
     */
    public static long diff(Date subtrahend, Date minuend, long diffField) {
        long diff = minuend.getTime() - subtrahend.getTime();
        return diff / diffField;
    }

    /**
     * ��ʱ�������ڼ�¼ĳ�δ����ִ��ʱ�䣬��λ������
     *
     * @param preTime ֮ǰ��¼��ʱ��
     * @return ʱ������
     */
    public static long spendNt(long preTime) {
        return System.nanoTime() - preTime;
    }

    /**
     * ��ʱ�������ڼ�¼ĳ�δ����ִ��ʱ�䣬��λ������
     *
     * @param preTime ֮ǰ��¼��ʱ��
     * @return ʱ������
     */
    public static long spendMs(long preTime) {
        return System.currentTimeMillis() - preTime;
    }
}
