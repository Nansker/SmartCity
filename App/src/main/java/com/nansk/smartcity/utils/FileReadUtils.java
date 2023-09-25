package com.nansk.smartcity.utils;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 11:44
 * @description 文件读取工具类
 */

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileReadUtils {
    public static String getData(Context context, int id) {
        InputStream inputStream = context.getResources().openRawResource(id);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            inputStream.close();
            return sb.toString();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
