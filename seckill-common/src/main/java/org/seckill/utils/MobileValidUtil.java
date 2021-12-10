package org.seckill.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述: 校验电话号码
 * 〈〉
 * @Param:
 * @Return:
 * @Author: lang
 */
public class MobileValidUtil {
    private static final String REGEX = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
    private static final Pattern P = Pattern.compile(REGEX);

    public static boolean checkNumber(String phone) {
        if (phone == null || phone.length() != 11) {
            return false;
        }
        Matcher m = P.matcher(phone);
        return m.matches();
    }
}
