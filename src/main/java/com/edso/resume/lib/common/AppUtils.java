package com.edso.resume.lib.common;

import com.google.common.base.Strings;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {

    public static final SimpleDateFormat PART_FORMAT = new SimpleDateFormat("yyyyMMdd");

    private static final Random rand = new Random();

    public static int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    public static Date stringToDate(String date, String pattern) {
        if (Strings.isNullOrEmpty(date)) {
            return new Date();
        }
        Date date1;
        try {
            date1 = new SimpleDateFormat(pattern).parse(date);
        } catch (Exception e) {
            date1 = new Date();
        }
        return date1;
    }

    public static Date stringToDate2(String date, String pattern) {
        if (Strings.isNullOrEmpty(date)) {
            return null;
        }
        Date date1;
        try {
            date1 = new SimpleDateFormat(pattern).parse(date);
        } catch (Exception e) {
            date1 = null;
        }
        return date1;
    }

    public static int formatDate(Date date) {
        return formatDate(date, "yyyyMMdd");
    }

    public static int formatDate(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return parseInt(simpleDateFormat.format(date));
    }

    public static String formatDateToString(Date date) {
        return formatDateToString(date, "dd/MM/yyyy HH:mm:ss");
    }

    public static String formatDateToString(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static String parseString(Object obj) {
        if (obj == null) {
            return "";
        }
        try {
            return String.valueOf(obj);
        } catch (Exception ex) {
        }
        return "";
    }

    public static long parseLong(Object o) {
        if (o == null) {
            return 0;
        }
        if (o instanceof Double) {
            return ((Double) o).longValue();
        }
        if (o instanceof Float) {
            return ((Float) o).longValue();
        }
        try {
            return Long.parseLong(String.valueOf(o));
        } catch (Exception e) {
        }
        return 0;
    }

    public static int parseInt(Object o) {
        if (o == null) {
            return 0;
        }
        if (o instanceof Double) {
            return ((Double) o).intValue();
        }
        if (o instanceof Float) {
            return ((Float) o).intValue();
        }
        try {
            return Integer.parseInt(String.valueOf(o));
        } catch (Exception e) {
        }
        return 0;
    }

    public static double parseDouble(Object o) {
        if (o == null) {
            return 0;
        }
        try {
            return Double.parseDouble(String.valueOf(o));
        } catch (Exception e) {
        }
        return 0;
    }

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    private static final String USERNAME_PATTERN = "^[A-Za-z0-9_]{4,30}$";

    public static boolean validateUsername(final String username) {
        if (Strings.isNullOrEmpty(username)) {
            return false;
        }
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    private static final String FULLNAME_PATTERN =
            "^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶ" +
                    "ẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợ" +
                    "ụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$";

    public static boolean validateFullName(String fullName) {
        if (Strings.isNullOrEmpty(fullName))
            return false;
        return fullName.matches(FULLNAME_PATTERN);
    }

    public static boolean validatePhone(String phone) {
        String regex = "(09|03|05|08|07)[0-9]{8}";
        if (Strings.isNullOrEmpty(phone)) {
            return false;
        }
        return phone.matches(regex);
    }

    static String regexURL = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public static boolean validateUrl(String url) {
        if (Strings.isNullOrEmpty(url)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regexURL);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    static final String regexEmail = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static boolean validateEmail(String email) {
        if (Strings.isNullOrEmpty(email)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regexEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static double roundDouble(Double d) {
        if (d == null) {
            return 0;
        }
        return Math.round(d * 100) / 100.0d;
    }

}
