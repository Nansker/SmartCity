package com.nansk.smartcity.activity.movie;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/13 21:12
 * @description 电影获取类型工具类
 */

public class MovieGetType {
    public static String getPlayType(String type){
        if (type.equals("1")){
            return "2D";
        }else if (type.equals("2")){
            return "3D";
        }else if (type.equals("3")){
            return "IMAX";
        }else if (type.equals("4")){
            return "4DX";
        }
        return "未知";
    }

    public static String getMovieCategory(String category){
        if (category.equals("1")){
            return "故事";
        }else if (category.equals("2")){
            return "爱情";
        }else if (category.equals("3")){
            return "动作";
        }else if (category.equals("4")){
            return "喜剧";
        }else if (category.equals("5")){
            return "恐怖";
        }else if (category.equals("6")){
            return "惊悚";
        }else if (category.equals("7")){
            return "战争";
        }else if (category.equals("8")){
            return "科幻";
        }
        return "未知";
    }

}
