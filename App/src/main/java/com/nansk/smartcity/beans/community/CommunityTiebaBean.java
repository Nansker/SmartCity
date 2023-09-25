package com.nansk.smartcity.beans.community;

import android.net.Uri;

import java.io.Serializable;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/18 09:22
 * @description
 */
public class CommunityTiebaBean implements Serializable {

    /**
     * nickName : 六眼飞鱼
     * title : 贴吧01，回收
     * content : 专业回收电子元器件，集成电路，手机IC，二三极管，电解电容，胆电容，，字库，晶振，我们高价回收手机配件，手机屏，液晶屏，液晶总成，手机喇叭，手机尾插，手机连接器，FLash系列闪存，内存，单片机，模块,显卡，网卡，芯片，家电IC、电脑IC、通讯IC、电源IC、数码IC、安防IC、军工IC,K9F系列、南北桥、手机IC、电脑周边IC、电视机IC、ATMEL/PIC系列单片机、SAA系列、XC系列、RT系列、TDA系列、TA系列，手机主控IC，内存卡、字库、蓝牙芯片,功放IC、电解电容、钽电容、贴片电容、晶振、变压器、LED发光管、继电器...电脑配件.手机配件）等一切电子料等。
     * time : 2021-12-11
     * likeNum : 30
     * readNum : 12
     */

    public String nickName;
    public String title;
    public String content;
    public String time;
    public int likeNum;
    public int readNum;
    private List<String> images;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
