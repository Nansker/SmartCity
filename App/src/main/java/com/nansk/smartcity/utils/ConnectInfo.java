package com.nansk.smartcity.utils;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/08 16:32
 * @Description API接口地址
 */

public class ConnectInfo {
    //服务器IP地址
//    public final static String IP = "http://192.168.10.162:10001";
//    public final static String IP = "http://124.93.196.45:10001";
    //上传文件
    public final static String UPLOAD = "/prod-api/common/upload";
    public final static String POSITION_CURRENT = "/prod-api/api/common/gps/location";
    public final static String RESET_PASSWORD = "/prod-api/api/common/user/resetPwd";



    //用户登录
    public final static String USER_LOGIN = "/prod-api/api/login";
    //获取用户信息
    public final static String USER_INFO = "/prod-api/api/common/user/getInfo";
    //账号充值
    public final static String BALANCE = "/prod-api/api/common/balance/";
    //修改用户信息
    public final static String USER_COMMON_INFO = "/prod-api/api/common/user";


    //用户全部订单
    public final static String USER_ALL_ORDER = "/prod-api/api/allorder/list";
    //意见反馈
    public final static String FEEDBACK_COMMENT = "/prod-api/api/common/feedback";

    //首页轮播图
    public final static String HOME_BANNER = "/prod-api/api/rotation/list";

    //全部服务
    public final static String SERVICE_LIST = "/prod-api/api/service/list";

    /**
     * @Create 2021/9/17 20:47
     * @Role 新闻
     * @Param
     */
    //新闻列表
    public final static String PRESS_LIST = "/prod-api/press/press/list";
    public final static String PRESS = "/prod-api/press/";
    //新闻详情
    public final static String PRESS_DETAILS = "/prod-api/press/press/";
    //新闻评论列表
    public final static String PRESS_COMMENTS_LIST = "/prod-api/press/comments/list";
    //新闻评论
    public final static String PRESS_COMMENTS = "//prod-api/press/pressComment";


    /**
     * @Create 2021/9/16 11:05
     * @Role 智慧巴士
     * @Param
     */
    //公交站点信息
    public final static String BUS_STOP_LIST = "/prod-api/api/bus/stop/list";
    //线路详情
    public final static String BUS_LINE_LIST = "/prod-api/api/bus/line/list";
    //新增订单
    public final static String BUS_ORDERS = "/prod-api/api/bus/order";


    /**
     * @Create 2021/9/17 10:40
     * @Role 门诊预约
     * @Param
     */
    //医院列表
    public final static String HOSPITAL = "/prod-api/api/hospital/hospital/";
    //医院详情页轮播图
    public final static String HOSPITAL_BANNER = "/prod-api/api/hospital/banner/list";
    //查询当前用户下的就诊人卡片
    public final static String HOSPITAL_PATIENT = "/prod-api/api/hospital/patient/list";
    //添加就诊人
    public final static String HOSPITAL_ADD_PATIENT = "/prod-api/api/hospital/patient";
    //查询专家诊与普通诊的科室分类
    public final static String HOSPITAL_CATEGORY_LIST = "/prod-api/api/hospital/category/list";
    //生成预约单
    public final static String HOSPITAL_RESERVE = "/prod-api/api/hospital";
    //查询预约列表
    public final static String HOSPITAL_RESERVE_LIST = "/prod-api/api/hospital/reservation/list";


    /**
     * @Create 2021/9/18 11:13
     * @Role 停车场
     * @Param
     */
    public final static String PARK_LOT_LIST = "/prod-api/api/park/lot/list";
    //查询停车记录列表
    public final static String PARK_RECORD_LIST = "/prod-api/api/park/lot/record/list";


    /**
     * @Create 2021/9/18 19:58
     * @Role 外卖订餐
     * @Param
     */

    //查询广告列表
    public final static String TAKEOUT_ROTATION_LIST = "/prod-api/api/takeout/rotation/list";
    public final static String TAKEOUT_THEME_LIST = "/prod-api/api/takeout/theme/list";
    //查询店家列表
    public final static String TAKEOUT_SELLER_LIST = "/prod-api/api/takeout/seller/list";
    //获取店家详情
    public final static String TAKEOUT_SELLER_DETAILS = "/prod-api/api/takeout/seller/";
    public final static String TAKEOUT_SELLER_COMMENT = "/prod-api/api/takeout/comment";
    //查询附近商家
    public final static String TAKEOUT_SELLER_NEAR = "/prod-api/api/takeout/seller/near";
    //根据查询菜品类别
    public final static String TAKEOUT_CATEGORY = "/prod-api/api/takeout/category/list";
    //根据查询菜品列表
    public final static String TAKEOUT_PRODUCT = "/prod-api/api/takeout/product/list";
    //查询店家评论列表
    public final static String TAKEOUT_COMMENT = "/prod-api/api/takeout/comment/list";
    //获取当前用户收货地址列表
    public final static String TAKEOUT_ADDRESS_LIST = "/prod-api/api/takeout/address/list";
    //添加收货地址
    public final static String TAKEOUT_ADDRESS = "/prod-api/api/takeout/address";
    //创建订单
    public final static String TAKEOUT_ORDER_CREATE = "/prod-api/api/takeout/order/create";
    //支付订单
    public final static String TAKEOUT_ORDER_PAY = "/prod-api/api/takeout/pay";
    //查询当前用户订单列表
    public final static String TAKEOUT_ORDER_LIST = "/prod-api/api/takeout/order/list";
    //菜品查询店家信息
    public final static String TAKEOUT_SELLER_SEARCH = "/prod-api/api/takeout/search";
    //查询订单详情
    public final static String TAKEOUT_ORDER_DETAILS = "/prod-api/api/takeout/order/";
    //退款
    public final static String TAKEOUT_ORDER_REFOUND = "/prod-api/api/takeout/order/refound";
    //收藏列表
    public final static String TAKEOUT_COLLECT_LIST = "/prod-api/api/takeout/collect/list";

    //店家收藏
    public final static String TAKEOUT_SELLER_COLLECT = "/prod-api/api/takeout/collect";


    /**
     * @Create 2021/9/26 20:17
     * @Role 找房子
     * @Param
     */
    public final static String HOUSE_HOUSING = "/prod-api/api/house/housing/";

    /**
     * @Create 2021/9/27 14:35
     * @Role 活动
     * @Param
     */
    public final static String ACTIVITY_BANNER = "/prod-api/api/activity/rotation/list";
    public final static String ACTIVITY_CATEGORY = "/prod-api/api/activity/category/list";
    public final static String ACTIVITY_LIST= "/prod-api/api/activity/activity/list";
    public final static String ACTIVITY_DETAILS= "/prod-api/api/activity/activity/";
    public final static String ACTIVITY_COMMENT= "/prod-api/api/activity/comment";
    public final static String ACTIVITY_SIGNUP= "/prod-api/api/activity/signup";

    /**
     * @Create 2021/9/28 12:54
     * @Role 生活缴费
     * @Param
     */
    public final static String LIFEPAY_BANNER = "/prod-api/api/living/rotation/list";
    public final static String LIFEPAY_LIVING = "/prod-api/api/living/category/list";

    //根据缴费编号查询缴费账单
    public final static String LIFEPAY_BILL = "/prod-api/api/living/bill";
    public final static String LIFEPAY_RECHARGE_LIST = "/prod-api/api/living/recharge/record/list";
    //缴费接口
    public final static String LIFEPAY_RECHARGE = "/prod-api/api/living/recharge";
    //查询当前用户话费充值记录
    public final static String LIFEPAY_PHONE_RECORD = "/prod-api/api/living/phone/record/list";
//    查询手机话费余额信息
    public final static String LIFEPAY_PHONE_QUERY = "/prod-api/api/living/phone";

    //生活资讯
    public final static String LIVING_PRESS= "/prod-api/api/living/press/";
    //天气
    public final static String LIVING_WEATHER= "/prod-api/api/living/weather";

    /**
     * @Create 2021/9/30 19:55
     * @Role 找工作
     * @Param
     */
    //职位
    public final static String JOB_PROFESSION= "/prod-api/api/job/profession/list";
    //查询招聘列表
    public final static String JOB_POST= "/prod-api/api/job/post/";
    public final static String JOB_COMPANY= "/prod-api/api/job/company/";
    public final static String JOB_DELIVER= "/prod-api/api/job/deliver/list";

    /**
     * @Create 2021/10/4 15:00
     * @Role 智慧交管
     * @Param
     */
    public final static String TRAFFIC_ILLEGAL= "/prod-api/api/traffic/illegal/";
    //机动车
    public final static String TRAFFIC_CAR= "/prod-api/api/traffic/car/";
    //驾驶证
    public final static String TRAFFIC_LICENSE= "/prod-api/api/traffic/license/";
    public final static String TRAFFIC_NOTICE= "/prod-api/api/traffic/illegal/notice/";


    /**
     * @Create 2021/10/6 12:47
     * @Role 城市地铁
     * @Param
     */
    //广告轮播
    public final static String METRO_ROTATION= "/prod-api/api/metro/rotation/list";
    //站点列表
    public final static String METRO_LIST= "/prod-api/api/metro/list";
    //所有线路列表
    public final static String METRO_ALL_LINE= "/prod-api/api/metro/line/list";
    public final static String METRO_LINE= "/prod-api/api/metro/line";
    public final static String METRO_NOTICE= "/prod-api/api/metro/notice/list";
    public final static String METRO_FOUND= "/prod-api/api/metro/found/";
    public final static String METRO_STATEMENT= "/prod-api/api/metro/statement";
    //站点列表
    public final static String METRO_STEP= "/prod-api/api/metro/step/list";

    //生活分类
    public final static String METRO_PRESS= "/prod-api/api/metro/press/";
    public final static String METRO_CARD= "/prod-api/api/metro/card";
    //线路总览图
    public final static String METRO_CITY= "/prod-api/api/metro/city";


    /**
     * @Create 2021/10/13 11:03
     * @Role 电影
     * @Param
     */
    public final static String MOVIE_BANNER= "/prod-api/api/movie/rotation/list";
    public final static String MOVIE_FILM= "/prod-api/api/movie/film/";
    public final static String MOVIE_THEATRE= "/prod-api/api/movie/theatre/";
    public final static String MOVIE_TICKET= "/prod-api/api/movie/ticket";

    public final static String MOVIE_COMMENT= "/prod-api/api/movie/film/comment";
    public final static String MOVIE_PRESS= "/prod-api/api/movie/press/";
    public final static String MOVIE_PRESS_CATEGORY= "/prod-api/api/movie/press/category/list";

    public final static String getUrl(String url, Object condition) {
        String newUrl = MyApplication.IP + url + condition;
        return newUrl;
    }
}
