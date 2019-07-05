package cn.dozyx.zerofate.java;


import android.annotation.TargetApi;
import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.dozyx.core.utli.gson.IntDefaultZeroAdapter;

/**
 * 测试 java 相关代码
 * 约定：将测试代码放在单独方法中，main 中只执行该方法，这样就可以在测试下一个项目时保留上一次的代码
 *
 * @author dozeboy
 * @date 2017/12/11
 */

public class Test {
    private static final Boolean lock = Boolean.TRUE;

    public static void main(String[] args) throws ParseException {
        testGson();
    }

    private static void testType() {
        System.out.println(Integer.TYPE);
        System.out.println(Integer.TYPE == Integer.class);
        System.out.println(Integer.class instanceof Class);
    }

    private static void testDate() {
        String payTime = "2019-06-01";
        int year = Integer.parseInt(payTime.substring(0, 4));
        int month = Integer.parseInt(payTime.substring(5, 7));
        int day = Integer.parseInt(payTime.substring(8, 10));
        Date date = new Date(year, month, day);
        System.out.println(date.getTime());
        String payTime1 = "2019-05-31";
        int year1 = Integer.parseInt(payTime1.substring(0, 4));
        int month1 = Integer.parseInt(payTime1.substring(5, 7));
        int day1 = Integer.parseInt(payTime1.substring(8, 10));
        Date date1 = new Date(year1, month1, day1);
        System.out.println(date1.getTime());
    }

    private static Random rnd = new Random();

    private static long random(long n) {
        //(0 ~ Integer.MAX_VALUE)  =  Integer.MAX_VALUE / 2;

        return Math.abs(rnd.nextLong()) % n;
    }

    private static void randomTest() {
        for (int i = 0; i < 100; i++) {
            System.out.printf(random(2) + " ");
        }
    }


    private static void testFianl() {
        Integer integer = new Integer(1);
        System.out.println(integer);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(integer);
            }
        }).start();
    }


    @TargetApi(Build.VERSION_CODES.O)
    private static void testLocalDateTime() {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM")));
    }

    public static String formatComma(String amount) {
        double doubleAmount;
        try {
            doubleAmount = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            return amount;
        }
        return new DecimalFormat("#,##0.00").format(doubleAmount);
    }

    private static void testStringToHex() {
        String str = "A20670100203020180000250323600064710300633522704008C7B641250500881025007";
        String interger =
                "413230363730313030323033303230313830303030323530333233363030303634373130333030363333353232373034303038433742363431323530353030383831303235303037";
        System.out.println(new BigInteger(str, 16));
        System.out.println(String.format("%040x", new BigInteger(1, str.getBytes())));
        System.out.println(String.format("%x", new BigInteger(1, str.getBytes())));
        System.out.println(String.format("%x", 10));
        System.out.println(new BigInteger(1, str.getBytes()));
        System.out.println(String.format("%040x", 10));
        System.out.println(String.format("%03d", "11111".length()));
    }

    private static <T> boolean isAssignableFrom(T type) {
        return String.class.isAssignableFrom(type.getClass());
    }

    private static void parseStringDate() throws ParseException {
        String date = "2018-10-08";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(date));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-(\\d{2})");
        Matcher matcher = pattern.matcher(date);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

    private static void testTime() {
        Calendar now = Calendar.getInstance();
        System.out.println(formatDate(now.getTime()));

        now.add(Calendar.WEEK_OF_YEAR, -1);
        System.out.println(formatDate(now.getTime()));

        now = Calendar.getInstance();
        now.set(Calendar.WEEK_OF_MONTH, 1);
        now.add(Calendar.WEEK_OF_MONTH, -1);
        System.out.println(formatDate(now.getTime()));

        now = Calendar.getInstance();
        now.set(Calendar.DAY_OF_MONTH, 3);
        now.add(Calendar.WEEK_OF_MONTH, -1);
        System.out.println(formatDate(now.getTime()));

        now = Calendar.getInstance();
        now.set(Calendar.DAY_OF_YEAR, 3);
        now.add(Calendar.WEEK_OF_MONTH, -1);
        System.out.println(formatDate(now.getTime()));

        now = Calendar.getInstance();
        now.add(Calendar.MONTH, -1);
        System.out.println(formatDate(now.getTime()));

    }

    private static String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(date);
    }


    public static class Student extends User {
        private static final int a = A.a;
        public static final String NAME = "jijiji";

        static {
            System.out.println("static");
        }
    }

    private void foo() {
        System.out.print(new A().str);
    }

    private static class A {
        private String str;
        public static int a = 10;
    }

    private static void testThreadPool() {
        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());
        for (int i = 0; i < 10; i++) {
            int num = i;
            executorService.execute(() -> {

                System.out.println(new Date() + " & i == " + num + " & thread == "
                        + Thread.currentThread());
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static String format(String num, int reserved, int divide) {
        BigDecimal b1 = new BigDecimal(num);
        BigDecimal b2 = new BigDecimal(divide);
        String format;
        if (reserved == 0) {
            format = "#";
        } else {
            StringBuilder builder = new StringBuilder("#.#");
            for (int i = 1; i < reserved; i++) {
                builder.append("#");
            }
            format = builder.toString();
        }
        return new DecimalFormat(format).format(
                b1.divide(b2, reserved, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public static String formatHundredth(String str) {
        if (str == null || str.length() == 0) {
            return "0.00";
        }
        Double tempInput = Double.parseDouble(str);
        BigDecimal b1 = new BigDecimal(tempInput.toString());
        BigDecimal b2 = new BigDecimal("100");
        DecimalFormat decimalFormat = new DecimalFormat("#");
        return decimalFormat.format(b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    private static void testGson() {
        Gson customGson = new GsonBuilder().registerTypeAdapter(Integer.TYPE, new IntDefaultZeroAdapter()).create();
        String personJsonWithoutAge = "{\"name\":\"张三\"}";
        Person person = customGson.fromJson(personJsonWithoutAge, Person.class);
        System.out.println(person);
        String personJsonWrongType = "{\"name\":\"张三\",\"age\":\"\"}";
        person = customGson.fromJson(personJsonWrongType, Person.class);
        System.out.println(person);
        User user = new User();
        user.setName("张三");
        System.out.println("缺少 field：" + new Gson().toJson(user));

        user.setPhone("110");
        System.out.println("缺少对象 field：" + new Gson().toJson(user));
        User.Address address = new User.Address();

        address.setProvince("江苏");
        address.setCity("常州");
        user.setAddress(address);
        System.out.println("完整：" + new Gson().toJson(user));

        String jsonStr1 = "{\n"
                + "    \"name\": \"张三\",\n"
                + "    \"phone\": \"110\",\n"
                + "    \"age\": 20,\n"
                + "    \"address\": {\n"
                + "        \"province\": \"江苏\",\n"
                + "        \"city\": \"常州\"\n"
                + "    }\n"
                + "}";
        System.out.println("jsonStr 包含多余字段：" + new Gson().fromJson(jsonStr1, User.class));
        String jsonStr2 = "{\n"
                + "    \"name\": \"张三\",\n"
                + "    \"address\": {\n"
                + "        \"province\": \"江苏\",\n"
                + "        \"city\": \"常州\"\n"
                + "    }\n"
                + "}";
        System.out.println("jsonStr 缺少字段：" + new Gson().fromJson(jsonStr2, User.class));

        String jsonStr3 = "{\"data\": {\n"
                + "    \"name\": \"张三\",\n"
                + "    \"phone\": \"110\",\n"
                + "    \"address\": {\n"
                + "        \"province\": \"江苏\",\n"
                + "        \"city\": \"常州\"\n"
                + "    }\n"
                + "}}";
        System.out.println("jsonStr 增加一层data：" + new Gson().fromJson(jsonStr3, User.class));

        String jsonStr4 = "{\"data\": {\n"
                + "    \"name\": \"张三\",\n"
                + "    \"phone\": \"110\",\n"
                + "    \"address\": \"\"\n"
                + "}}";
        System.out.println("对象类型错误：" + new Gson().fromJson(jsonStr4, User.class));
    }

    public static class G2<S> extends GenericTest<S> {

    }

    public static byte[] HexString2bytes(String hexString) {
        hexString = hexString.toUpperCase();
        byte[] b = new byte[hexString.length() / 2];
        char[] hexStringByte = hexString.toCharArray();
        for (int i = 0; i < hexString.length() / 2; i++) {
            byte byteTemp = (byte) ((toByte(hexStringByte[i * 2]) << 4) | toByte(
                    hexStringByte[i * 2 + 1]));
            b[i] = byteTemp;
        }
        return b;
    }

    private static byte toByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    private static void testCalculate() {
        // float 计算
        BigDecimal bigDecimal1 = new BigDecimal(0.58f);
        BigDecimal bigDecimal2 = new BigDecimal(0.01f);
        System.out.println(bigDecimal1 + "\n" + bigDecimal2 + "\n" + bigDecimal1.add(bigDecimal2));
        System.out.println((int) ((0.58f + 0.01f) * 100));

        // double 计算
        BigDecimal bigDecimal3 = new BigDecimal(0.58);
        BigDecimal bigDecimal4 = new BigDecimal(0.01);
        System.out.println(bigDecimal3 + "\n" + bigDecimal4 + "\n" + bigDecimal3.add(bigDecimal4));
        System.out.println((int) ((0.58 + 0.01) * 100));
    }

    public static String removeSecond(String date) {

        final int colonCount = date.length() - date.replace(":", "").length();
        if (colonCount > 1) {
            return date.substring(0, date.lastIndexOf(":"));
        }
        return date;
    }

    public static boolean isLetterOrNumberWithLengthLimit(String text, int min, int max) {
        String reg = "^[a-zA-Z0-9]{" + min + "," + max + "}$";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(text);
        return mat.matches();
    }

}