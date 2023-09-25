package com.nansk.smartcity.beans.support;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/20 15:11
 * @description
 */

import java.io.Serializable;

public class SupportPressBean implements Serializable {


    /**
     * id : 1
     * title : 致力于农村基础设施建设，瑞因智控获百万级别种子轮投资
     * content : 农业装备研发新星黑龙江瑞因智控科技有限公司完成种子轮融资，投资额度数百万元投资方系杭州某电子商务有限公司。
     瑞因智控科技是一家致力于农村基础设施建设，提供科技创新型节能减排产品的创业公司。其产品研发与各地方政府政策相适应，以“精准扶贫”作为核心内容，基于电子信息智控技术，帮助农村中小型养殖中心达到低耗、低污染的生产效果。在科技创新方面，据查，公司拥有软件著作权一项，专利受理两项，其研发产品曾获节能减排大赛二等奖，具有长期研发价值。
     根据官方提供的数据，瑞因科技已在浙江省获得第一批机器订单，其产品本身直接响应当地“五水共治”号召，为养殖业污物处理提供了对应的解决方案。“农业的发展离不开政府的支持。”在农业发达的美国、日本，政府补贴达到40%以上。相比之下，我国的农业补贴虽只占20%左右，但仍不断上涨。1月29日，国务院办公厅印发《关于推进农业高新技术产业示范区建设发展的指导意见》，首次以农业高新技术产业为主题,明确到2025年,要布局建设一批国家农业高新技术产业示范区。
     * redNum : 1231
     * data : 2021-12-13
     * author : 人民日报
     */

    public int id;
    public String title;
    public String content;
    public int redNum;
    public String data;
    public String author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRedNum() {
        return redNum;
    }

    public void setRedNum(int redNum) {
        this.redNum = redNum;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
