package com.edso.resume.lib.common;

import com.github.slugify.Slugify;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {

    private static final char[] SOURCE_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É',
            'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â',
            'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý',
            'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ',
            'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ',
            'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ',
            'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ',
            'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
            'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ',
            'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ',
            'ữ', 'Ự', 'ự',};
    private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E',
            'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a',
            'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u',
            'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u',
            'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
            'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
            'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
            'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
            'U', 'u', 'U', 'u',};

    public static final String KEYPOINT_PATTERN = "\\{(.+?)\\}";

    public static final String EMAIL_ENCODING = "UTF-8";

    public static final SimpleDateFormat PART_FORMAT = new SimpleDateFormat("yyyyMMdd");

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

    public static String removeAccent(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }

    public static String slugify(String name) {
        Slugify slugify = new Slugify();
        return slugify.slugify(removeAccent(name)) + new Random().nextInt(10000);
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

    public static boolean parseBoolean(Object o) {
        if (Strings.isNullOrEmpty(AppUtils.parseString(o))) {
            return false;
        }
        return (Boolean) o;
    }

    public static List<String> parseList(Object o) {
        if (o == null) {
            return new ArrayList<>();
        }
        String str = (String) o;
        return Arrays.asList(str.split(";"));

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
            "^[a-zA-Z_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêếìíòóôõùúăđĩũơƯẠẢẤẦẨẪẬẮẰẲẴẶ" +
                    "ẸẺẼỀỂưạảấầẩẫậắằẳẵặẹẻẽềểỄỆẾỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợ" +
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

    public static String parseVietnameseToEnglish(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return "";
        }
        String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll(" +", " ").trim().toLowerCase().replaceAll("đ", "d");
    }

    public static String mergeWhitespace(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return "";
        }
        return str.trim().replaceAll(" +", " ");
    }

    public static double roundDouble(Double d) {
        if (d == null) {
            return 0;
        }
        return Math.round(d * 100) / 100.0d;
    }

    public static long getTimeBeginningDay() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = f.parse(LocalDate.now().toString());
            return date.getTime();
        } catch (Throwable ex) {
            return 0L;
        }
    }

    public static String removeWhitespaceAndLowerCase(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return "";
        }
        return str.replaceAll(" ", "").toLowerCase();
    }

    public static String removeWhitespace(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return "";
        }
        return str.replaceAll(" ", "");
    }

}
