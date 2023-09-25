package com.nansk.smartcity.utils;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/12 15:45
 * @description 用户输入匹配
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputMatchUtils {

    /**
     * @Create 2021/10/12 15:47
     * @Role
     * @Param regex
     */
    public static boolean isMatches(String regex,String value){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
