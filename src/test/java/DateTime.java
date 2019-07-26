import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public final class DateTime {

    /**
     * Don't let anyone instantiate this class
     */
    private DateTime() {}

    /**
     * check date string validation with the default format "yyyyMMdd".
     * @param s date string you want to check with default format "yyyyMMdd".
     * @return date java.lang3.Date
     */
    public static java.util.Date check(String s) throws java.text.ParseException {
        return check(s, "yyyyMMdd");
    }

    /**
     * check date string validation with an user defined format.
     * @param s date string you want to check.
     * @param format string representation of the date format. For example, "yyyy-MM-dd".
     * @return date java.lang3.Date
     */
    public static java.util.Date check(String s, String format) throws java.text.ParseException {
        if ( s == null )
            throw new java.text.ParseException("date string to check is null", 0);
        if ( format == null )
            throw new java.text.ParseException("format string to check date is null", 0);

        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = null;
        try {
            date = formatter.parse(s);
        }
        catch(java.text.ParseException e) {
            /*
            throw new java.text.ParseException(
                e.getMessage() + " with format \"" + format + "\"",
                e.getErrorOffset()
            );
            */
            throw new java.text.ParseException(" wrong date:\"" + s +
                    "\" with format \"" + format + "\"", 0);
        }

        if ( ! formatter.format(date).equals(s) )
            throw new java.text.ParseException(
                    "Out of bound date:\"" + s + "\" with format \"" + format + "\"",
                    0
            );
        return date;
    }

    /**
     * check date string validation with the default format "yyyyMMdd".
     * @param s date string you want to check with default format "yyyyMMdd"
     * @return boolean true ��¥ ������ �°�, �����ϴ� ��¥�� ��
     *                 false ��¥ ������ ���� �ʰų�, �������� �ʴ� ��¥�� ��
     */
    public static boolean isValid(String s) throws Exception {
        return DateTime.isValid(s, "yyyyMMdd");
    }

    /**
     * check date string validation with an user defined format.
     * @param s date string you want to check.
     * @param format string representation of the date format. For example, "yyyy-MM-dd".
     * @return boolean true ��¥ ������ �°�, �����ϴ� ��¥�� ��
     *                 false ��¥ ������ ���� �ʰų�, �������� �ʴ� ��¥�� ��
     */
    public static boolean isValid(String s, String format) {
/*
        if ( s == null )
            throw new NullPointerException("date string to check is null");
        if ( format == null )
            throw new NullPointerException("format string to check date is null");
*/
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = null;
        try {
            date = formatter.parse(s);
        }
        catch(java.text.ParseException e) {
            return false;
        }

        if ( ! formatter.format(date).equals(s) )
            return false;

        return true;
    }

    /**
     * @return formatted string representation of current day with  "yyyy-MM-dd".
     */
    public static String getDateString() {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat("yyyyMMdd", java.util.Locale.KOREA);
        return formatter.format(new java.util.Date());
    }

    /**
     *
     * For example, String time = DateTime.getFormatString("yyyy-MM-dd HH:mm:ss");
     *
     * @param java.lang.String pattern  "yyyy, MM, dd, HH, mm, ss and more"
     * @return formatted string representation of current day and time with  your pattern.
     */
    public static int getDay() {
        return getNumberByPattern("dd");
    }

    /**
     *
     * For example, String time = DateTime.getFormatString("yyyy-MM-dd HH:mm:ss");
     *
     * @param java.lang.String pattern  "yyyy, MM, dd, HH, mm, ss and more"
     * @return formatted string representation of current day and time with  your pattern.
     */
    public static int getYear() {
        return getNumberByPattern("yyyy");
    }

    /**
     *
     * For example, String time = DateTime.getFormatString("yyyy-MM-dd HH:mm:ss");
     *
     * @param java.lang.String pattern  "yyyy, MM, dd, HH, mm, ss and more"
     * @return formatted string representation of current day and time with  your pattern.
     */
    public static int getMonth() {
        return getNumberByPattern("MM");
    }

    /**
     *
     * For example, String time = DateTime.getFormatString("yyyy-MM-dd HH:mm:ss");
     *
     * @param java.lang.String pattern  "yyyy, MM, dd, HH, mm, ss and more"
     * @return formatted string representation of current day and time with  your pattern.
     */
    public static int getNumberByPattern(String pattern) {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat (pattern, java.util.Locale.KOREA);
        String dateString = formatter.format(new java.util.Date());
        return Integer.parseInt(dateString);
    }

    /**
     *
     * For example, String time = DateTime.getFormatString("yyyy-MM-dd HH:mm:ss");
     *
     * @param java.lang.String pattern  "yyyy, MM, dd, HH, mm, ss and more"
     * @return formatted string representation of current day and time with  your pattern.
     */
    public static String getFormatString(String pattern) {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat (pattern, java.util.Locale.KOREA);
        String dateString = formatter.format(new java.util.Date());
        return dateString;
    }

    /**
     * @return formatted string representation of current day with  "yyyyMMdd".
     */
    public static String getShortDateString() {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat ("yyyyMMdd", java.util.Locale.KOREA);
        return formatter.format(new java.util.Date());
    }

    /**
     * @return formatted string representation of current time with  "HHmmss".
     */
    public static String getShortTimeString() {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat ("HHmmss", java.util.Locale.KOREA);
        return formatter.format(new java.util.Date());
    }

    /**
     * @return formatted string representation of current time with  "yyyy-MM-dd-HH:mm:ss".
     */
    public static String getTimeStampString() {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat ("yyyy-MM-dd-HH:mm:ss:SSS", java.util.Locale.KOREA);
        return formatter.format(new java.util.Date());
    }

    /**
     * @return formatted string representation of current time with  "HH:mm:ss".
     */
    public static String getTimeString() {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat ("HH:mm:ss", java.util.Locale.KOREA);
        return formatter.format(new java.util.Date());
    }


    /**
     * return days between two date strings with default defined format.(yyyyMMdd)
     * @param s date string you want to check.
     * @return int ��¥ ������ �°�, �����ϴ� ��¥�� �� ������ ����
     *           ������ �߸� �Ǿ��ų� �������� �ʴ� ��¥: java.text.ParseException �߻�
     *          0: �Ͽ��� (java.lang3.Calendar.SUNDAY �� ��)
     *          1: ������ (java.lang3.Calendar.MONDAY �� ��)
     *          2: ȭ���� (java.lang3.Calendar.TUESDAY �� ��)
     *          3: ������ (java.lang3.Calendar.WENDESDAY �� ��)
     *          4: ����� (java.lang3.Calendar.THURSDAY �� ��)
     *          5: �ݿ��� (java.lang3.Calendar.FRIDAY �� ��)
     *          6: ����� (java.lang3.Calendar.SATURDAY �� ��)
     * ��) String s = "20000529";
     *  int dayOfWeek = whichDay(s, format);
     *  if (dayOfWeek == java.lang3.Calendar.MONDAY)
     *      System.out.println(" ������: " + dayOfWeek);
     *  if (dayOfWeek == java.lang3.Calendar.TUESDAY)
     *      System.out.println(" ȭ����: " + dayOfWeek);
     */
    public static int whichDay(String s) throws java.text.ParseException {
        return whichDay(s, "yyyyMMdd");
    }
    /**
     *
     * �������ڿ� �������ڻ��̿� �����ϰ� �Ͽ����� List�� �ݾ� return�Ѵ�.
     * <P/>
     * �������ڿ� �������ڻ��̿��� �� �ְ��� �����ϰ� �Ͽ����� ��¥�� List�� ��Ƽ� return�մϴ�.
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static List getMondayAndSundayDate(String startDate, String endDate) throws ParseException {


        int dayCount = daysBetween(startDate,endDate);
        String sDay = startDate;
        int whichDay = 0;
        //�Ⱓ������ �� ���ϱ�
        int weekCnt = dayCount/7;
        int weekCnt1 = dayCount % 7;
        if(weekCnt1 > 0) weekCnt++;
        String[][] saveDay = new String[weekCnt][2];
        int saveDayPos = 0;
        boolean monday = false;
        boolean sunday = false;
        List al = new ArrayList();

        for(int i=0;i<dayCount;i++){
            //sDay�� ���������� Ȯ���Ѵ�.
            whichDay = whichDay(sDay);
            //out.println("saveDayPos"+saveDayPos);
            //out.println("sDay"+sDay);
            //out.println("whichDay"+whichDay+"<br>");
            if(whichDay == 2){

                //�������̸� insertStartDate�� ����
                saveDay[saveDayPos][0] = sDay;
                //�Ͽ����� �����Ҽ� �ֵ��� monday mode�� true�� ����
                monday = true;
                //out.println(sDay +"is monday <br>");

            }

            if(whichDay == 1 && i > 0 && monday == true){

                //�Ͽ����̸� insertStartDate�� ����
                saveDay[saveDayPos][1] = sDay;
                //�Ͽ����� �����Ҽ� �ֵ��� monday mode�� true�� ����
                monday = false;
                HashMap hm = new HashMap();

                hm.put("sDate",saveDay[saveDayPos][0]);
                hm.put("eDate",saveDay[saveDayPos][1]);
                al.add( hm );

                //�Ͽ����� ã�����Ƿ� �����Ϸ� �����ϱ� ���� �����Ѵ�.
                saveDayPos++;

            }

            sDay = addDays(sDay,1);
        }
        return al;
    }
    /**
     * return days between two date strings with user defined format.
     * @param s date string you want to check.
     * @param format string representation of the date format. For example, "yyyy-MM-dd".
     * @return int ��¥ ������ �°�, �����ϴ� ��¥�� �� ������ ����
     *           ������ �߸� �Ǿ��ų� �������� �ʴ� ��¥: java.text.ParseException �߻�
     *          0: �Ͽ��� (java.lang3.Calendar.SUNDAY �� ��)
     *          1: ������ (java.lang3.Calendar.MONDAY �� ��)
     *          2: ȭ���� (java.lang3.Calendar.TUESDAY �� ��)
     *          3: ������ (java.lang3.Calendar.WENDESDAY �� ��)
     *          4: ����� (java.lang3.Calendar.THURSDAY �� ��)
     *          5: �ݿ��� (java.lang3.Calendar.FRIDAY �� ��)
     *          6: ����� (java.lang3.Calendar.SATURDAY �� ��)
     * ��) String s = "2000-05-29";
     *  int dayOfWeek = whichDay(s, "yyyy-MM-dd");
     *  if (dayOfWeek == java.lang3.Calendar.MONDAY)
     *      System.out.println(" ������: " + dayOfWeek);
     *  if (dayOfWeek == java.lang3.Calendar.TUESDAY)
     *      System.out.println(" ȭ����: " + dayOfWeek);
     */

    public static int whichDay(String s, String format) throws java.text.ParseException {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = check(s, format);

        java.util.Calendar calendar = formatter.getCalendar();
        calendar.setTime(date);
        return calendar.get(java.util.Calendar.DAY_OF_WEEK);
    }

    /**
     * return days between two date strings with default defined format.("yyyyMMdd")
     * @param String from date string
     * @param String to date string
     * @return int ��¥ ������ �°�, �����ϴ� ��¥�� �� 2�� ���� ������ ���� ����
     *           ������ �߸� �Ǿ��ų� �������� �ʴ� ��¥: java.text.ParseException �߻�
     */
    public static int daysBetween(String from, String to) throws java.text.ParseException {
        return daysBetween(from, to, "yyyyMMdd");
    }

    /**
     * return days between two date strings with user defined format.
     * @param String from date string
     * @param String to date string
     * @return int ��¥ ������ �°�, �����ϴ� ��¥�� �� 2�� ���� ������ ���� ����
     *           ������ �߸� �Ǿ��ų� �������� �ʴ� ��¥: java.text.ParseException �߻�
     */
    public static int daysBetween(String from, String to, String format) throws java.text.ParseException {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date d1 = check(from, format);
        java.util.Date d2 = check(to, format);

        long duration = d2.getTime() - d1.getTime();

        return (int)( duration/(1000 * 60 * 60 * 24) );
        // seconds in 1 day
    }

    /**
     * return age between two date strings with default defined format.("yyyyMMdd")
     * @param String from date string
     * @param String to date string
     * @return int ��¥ ������ �°�, �����ϴ� ��¥�� �� 2�� ���� ������ ���� ����
     *           ������ �߸� �Ǿ��ų� �������� �ʴ� ��¥: java.text.ParseException �߻�
     */
    public static int ageBetween(String from, String to) throws java.text.ParseException {
        return ageBetween(from, to, "yyyyMMdd");
    }

    /**
     * return age between two date strings with user defined format.
     * @param String from date string
     * @param String to date string
     * @param format string representation of the date format. For example, "yyyy-MM-dd".
     * @return int ��¥ ������ �°�, �����ϴ� ��¥�� �� 2�� ���� ������ ���� ����
     *           ������ �߸� �Ǿ��ų� �������� �ʴ� ��¥: java.text.ParseException �߻�
     */
    public static int ageBetween(String from, String to, String format) throws java.text.ParseException {
        return (int)(daysBetween(from, to, format) / 365 );
    }

    /**
     * return add day to date strings
     * @param String date string
     * @param int ���� �ϼ�
     * @return int ��¥ ������ �°�, �����ϴ� ��¥�� �� �ϼ� ���ϱ�
     *           ������ �߸� �Ǿ��ų� �������� �ʴ� ��¥: java.text.ParseException �߻�
     */
    public static String addDays(String s, int day) throws java.text.ParseException {
        return addDays(s, day, "yyyyMMdd");
    }
    /**
     * return add day to date strings
     * @param String date string
     * @param int ���� �ϼ�
     * @return int ��¥ ������ �°�, �����ϴ� ��¥�� �� �ϼ� �����ϱ�
     *           ������ �߸� �Ǿ��ų� �������� �ʴ� ��¥: java.text.ParseException �߻�
     */
    public static String minusDays(String s, int day) throws java.text.ParseException {
        return minusDays(s, day, "yyyyMMdd");
    }

    /**
     * return add day to date strings with user defined format.
     * @param String date string
     * @param int ���� ��
     * @param format string representation of the date format. For example, "yyyy-MM-dd".
     * @return int ��¥ ������ �°�, �����ϴ� ��¥�� �� �ϼ� ���ϱ�
     *           ������ �߸� �Ǿ��ų� �������� �ʴ� ��¥: java.text.ParseException �߻�
     */
    public static String addSeconds(String s, int day, String format) throws java.text.ParseException{
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = check(s, format);

        date.setTime(date.getTime() + ((long)day * 1000));
        return formatter.format(date);
    }

    /**
     * return add day to date strings with user defined format.
     * @param String date string
     * @param int ���� �ϼ�
     * @param format string representation of the date format. For example, "yyyy-MM-dd".
     * @return int ��¥ ������ �°�, �����ϴ� ��¥�� �� �ϼ� ���ϱ�
     *           ������ �߸� �Ǿ��ų� �������� �ʴ� ��¥: java.text.ParseException �߻�
     */
    public static String addDays(String s, int day, String format) throws java.text.ParseException{
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = check(s, format);

        date.setTime(date.getTime() + ((long)day * 1000 * 60 * 60 * 24));
        return formatter.format(date);
    }
    /**
     * return add day to date strings with user defined format.
     * @param String date string
     * @param int ���� �ϼ�
     * @param format string representation of the date format. For example, "yyyy-MM-dd".
     * @return int ��¥ ������ �°�, �����ϴ� ��¥�� �� �ϼ� �����ϱ�
     *           ������ �߸� �Ǿ��ų� �������� �ʴ� ��¥: java.text.ParseException �߻�
     */
    public static String minusDays(String s, int day, String format) throws java.text.ParseException{
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = check(s, format);

        date.setTime(date.getTime() - ((long)day * 1000 * 60 * 60 * 24));
        return formatter.format(date);
    }

    /**
     * return add month to date strings
     * @param String date string
     * @param int ���� ����
     * @return int ��¥ ������ �°�, �����ϴ� ��¥�� �� ���� ���ϱ�
     *           ������ �߸� �Ǿ��ų� �������� �ʴ� ��¥: java.text.ParseException �߻�
     */
    public static String addMonths(String s, int month) throws Exception {
        return addMonths(s, month, "yyyyMMdd");
    }

    /**
     * return add month to date strings with user defined format.
     * @param String date string
     * @param int ���� ����
     * @param format string representation of the date format. For example, "yyyy-MM-dd".
     * @return int ��¥ ������ �°�, �����ϴ� ��¥�� �� ���� ���ϱ�
     *           ������ �߸� �Ǿ��ų� �������� �ʴ� ��¥: java.text.ParseException �߻�
     */
    public static String addMonths(String s, int addMonth, String format) throws Exception {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = check(s, format);

        java.text.SimpleDateFormat yearFormat =
                new java.text.SimpleDateFormat("yyyy", java.util.Locale.KOREA);
        java.text.SimpleDateFormat monthFormat =
                new java.text.SimpleDateFormat("MM", java.util.Locale.KOREA);
        java.text.SimpleDateFormat dayFormat =
                new java.text.SimpleDateFormat("dd", java.util.Locale.KOREA);
        int year = Integer.parseInt(yearFormat.format(date));
        int month = Integer.parseInt(monthFormat.format(date));
        int day = Integer.parseInt(dayFormat.format(date));

        month += addMonth;
        if (addMonth > 0) {
            while (month > 12) {
                month -= 12;
                year += 1;
            }
        } else {
            while (month <= 0) {
                month += 12;
                year -= 1;
            }
        }
        java.text.DecimalFormat fourDf = new java.text.DecimalFormat("0000");
        java.text.DecimalFormat twoDf = new java.text.DecimalFormat("00");
        String tempDate = String.valueOf(fourDf.format(year))
                + String.valueOf(twoDf.format(month))
                + String.valueOf(twoDf.format(day));
        java.util.Date targetDate = null;

        try {
            targetDate = check(tempDate, "yyyyMMdd");
        } catch(java.text.ParseException pe) {
            day = lastDay(year, month);
            tempDate = String.valueOf(fourDf.format(year))
                    + String.valueOf(twoDf.format(month))
                    + String.valueOf(twoDf.format(day));
            targetDate = check(tempDate, "yyyyMMdd");
        }

        return formatter.format(targetDate);
    }

    public static String addYears(String s, int year) throws java.text.ParseException {
        return addYears(s, year, "yyyyMMdd");
    }

    public static String addYears(String s, int year, String format) throws java.text.ParseException {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = check(s, format);
        date.setTime(date.getTime() + ((long)year * 1000 * 60 * 60 * 24 * (365 + 1)));
        return formatter.format(date);
    }

    public static int monthsBetween(String from, String to) throws java.text.ParseException {
        return monthsBetween(from, to, "yyyyMMdd");
    }

    public static int monthsBetween(String from, String to, String format) throws java.text.ParseException {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date fromDate = check(from, format);
        java.util.Date toDate = check(to, format);

        // if two date are same, return 0.
        if (fromDate.compareTo(toDate) == 0) return 0;

        java.text.SimpleDateFormat yearFormat =
                new java.text.SimpleDateFormat("yyyy", java.util.Locale.KOREA);
        java.text.SimpleDateFormat monthFormat =
                new java.text.SimpleDateFormat("MM", java.util.Locale.KOREA);
        java.text.SimpleDateFormat dayFormat =
                new java.text.SimpleDateFormat("dd", java.util.Locale.KOREA);

        int fromYear = Integer.parseInt(yearFormat.format(fromDate));
        int toYear = Integer.parseInt(yearFormat.format(toDate));
        int fromMonth = Integer.parseInt(monthFormat.format(fromDate));
        int toMonth = Integer.parseInt(monthFormat.format(toDate));
        int fromDay = Integer.parseInt(dayFormat.format(fromDate));
        int toDay = Integer.parseInt(dayFormat.format(toDate));

        int result = 0;
        result += ((toYear - fromYear) * 12);
        result += (toMonth - fromMonth);

//        if (((toDay - fromDay) < 0) ) result += fromDate.compareTo(toDate);
        // ceil�� floor�� ȿ��
        if (((toDay - fromDay) > 0) ) result += toDate.compareTo(fromDate);

        return result;
    }

    public static String lastDayOfMonth(String src) throws java.text.ParseException {
        return lastDayOfMonth(src, "yyyyMMdd");
    }

    public static String lastDayOfMonth(String src, String format) throws java.text.ParseException {
        java.text.SimpleDateFormat formatter =
                new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
        java.util.Date date = check(src, format);

        java.text.SimpleDateFormat yearFormat =
                new java.text.SimpleDateFormat("yyyy", java.util.Locale.KOREA);
        java.text.SimpleDateFormat monthFormat =
                new java.text.SimpleDateFormat("MM", java.util.Locale.KOREA);

        int year = Integer.parseInt(yearFormat.format(date));
        int month = Integer.parseInt(monthFormat.format(date));
        int day = lastDay(year, month);

        java.text.DecimalFormat fourDf = new java.text.DecimalFormat("0000");
        java.text.DecimalFormat twoDf = new java.text.DecimalFormat("00");
        String tempDate = String.valueOf(fourDf.format(year))
                + String.valueOf(twoDf.format(month))
                + String.valueOf(twoDf.format(day));
        date = check(tempDate, format);

        return formatter.format(date);
    }

    public static int lastDay(int year, int month) throws java.text.ParseException {
        int day = 0;
        switch(month)
        {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: day = 31;
                break;
            case 2: if ((year % 4) == 0) {
                if ((year % 100) == 0 && (year % 400) != 0) { day = 28; }
                else { day = 29; }
            } else { day = 28; }
                break;
            default: day = 30;
        }
        return day;
    }
    /**
     * ���ڿ��� �迭�� ����
     * @param txt �迭�� ������ ���ڿ� ex) a,b,c
     * @param deli ������ ex) ,
     * @return �迭 ex) a b c ���� �迭
     */
    public static String[] getArrayFromString(String txt, String deli) {
        String str = txt;
        StringTokenizer st = new StringTokenizer(str, deli);
        String arrayStr[] = new String[st.countTokens()];
        for (int i = 0; st.hasMoreTokens(); i++)
            arrayStr[i] = st.nextToken();
        return arrayStr;
    }
    /**
     *
     * ���ֿ� �ش��ϴ� ������ ���ϱ�
     * <P/>
     * ���ֿ� �ش��ϴ� ������ ���ϱ�
     *
     * @return
     * @throws ParseException
     */
    public static String getThisWeekMondayDate() throws ParseException {
        //���ϳ�¥���ϱ�
        String curDate = getDateString();
        //���ϳ�¥�� �������̸� ���ϳ�¥�� ����, ���ϳ�¥�� ȭ�����̸� -1, ������-2, ��-3, ��-4, ��-5, ��-6
        String mondayDate = curDate;
        //sDay�� ���������� Ȯ���Ѵ�.
        int whichDay = whichDay(curDate);
        //ȭ�����̸�
        if(whichDay == 3){
            mondayDate = minusDays(curDate,1);
        }
        //�������̸�
        if(whichDay == 4){
            mondayDate = minusDays(curDate,2);
        }
        //������̸�
        if(whichDay == 5){
            mondayDate = minusDays(curDate,3);
        }
        //�ݿ����̸�
        if(whichDay == 6){
            mondayDate = minusDays(curDate,4);
        }
        //������̸�
        if(whichDay == 7){
            mondayDate = minusDays(curDate,5);
        }
        //�Ͽ����̸�
        if(whichDay == 1){
            mondayDate = minusDays(curDate,6);
        }
        //mondayDate = "20071224";
        return mondayDate;
    }
    /**
     *
     * ���ֿ� �ش��ϴ� ������ ���ϱ�
     * <P/>
     * ���ֿ� �ش��ϴ� ������ ���ϱ�
     *
     * @return
     * @throws ParseException
     */
    public static String getThisWeekSundayDate() throws ParseException {
        //���ϳ�¥���ϱ�
        String curDate = getDateString();
        //���ϳ�¥�� �Ͽ����̸� ���ϳ�¥�� ����, ���ϳ�¥�� �������̸� +6, ȭ+5,��+4, ��+3, ��+2, ��+1
        String returnDate = curDate;
        //sDay�� �Ͽ������� Ȯ���Ѵ�.
        int whichDay = whichDay(curDate);
        //System.out.println(whichDay);
        //�������̸�
        if(whichDay == 2){
            returnDate = addDays(curDate,6);
        }
        //ȭ�����̸�
        if(whichDay == 3){
            returnDate = addDays(curDate,5);
        }
        //�������̸�
        if(whichDay == 4){
            returnDate = addDays(curDate,4);
        }
        //������̸�
        if(whichDay == 5){
            returnDate = addDays(curDate,3);
        }
        //�ݿ����̸�
        if(whichDay == 6){
            returnDate = addDays(curDate,2);
        }
        //������̸�
        if(whichDay == 7){
            returnDate = addDays(curDate,1);
        }
        //returnDate = "20071230";
        return returnDate;
    }


    /**
     * �־��� Date�� patternȭ �� ���ڿ��� ��ȯ�Ѵ�.
     *
     * @param date
     *            ����ȭ�� ��¥
     * @param pattern
     *            string ����
     * @return string ����ȭ�� ��¥ ���ڿ�
     */
    public static String format(Date date, String pattern) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            return formatter.format(date);
        } catch (Exception ex)  {
            return "";
        }
    }

    /**
     * java.lang3.Date Ŭ������ yyyy-MM-dd ������ String���� ��ȯ�Ѵ�. ����Ÿ���̽� �÷��� NULL�ΰ�쿡��
     * ""���� �����Ѵ�.(with SqlMap.xml)
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        String str = format(date, "yyyy/MM/dd");
        if (str.equals("0001/01/01")) {
            str = "";
        }
        return str;
    }

    /** ���ڿ��� ��¥��
     *
     * @param value
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date StringToDate(String value, String pattern) throws ParseException
    {
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(value);
        } catch (ParseException e) {
            return null;
        }
    }







    /**
     * ������ �ش���� �������ڿ� ������ ���ڸ� Date ������ �����Ѵ�.
     * @param date Date
     * @param calendarField ������ �ش��
     * @param amount +- �� ��
     * @param flg 1 : �ش���� ���� ����, 2 : �ش���� ������ ����, 3 : �ش���� ������ ����
     * @return Date
     */
    public static Date add(Date date, int calendarField, int amount, int flg) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);

        if (flg == 1) {
            c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
        } else if (flg == 2) {
            c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
        } else if (flg == 3) {
            c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.getActualMaximum(Calendar.DATE));
        } else {
            c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
        }

        return c.getTime();
    }

    /**
     * ������ �ش���� �������ڿ� ������ ���ڸ� Date ������ �����Ѵ�.
     * @param date Date
     * @param amount +- �� ��
     * @param flg 1 : �ش���� ���� ����, 2 : �ش���� ������ ����, 3 : �ش���� ������ ����
     * @return Date
     */
    public static Date addMonths(Date date, int amount, int flg) {
        return add(date, Calendar.MONTH, amount, flg);
    }



}
