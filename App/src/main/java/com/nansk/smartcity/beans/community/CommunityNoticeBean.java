package com.nansk.smartcity.beans.community;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/17 21:25
 * @description
 */

public class CommunityNoticeBean {

    /**
     * id : 1
     * title : 深圳市龙岗区南湾街道办事处通知
     * content : 近日，南湾街道厦村社区恒大国香山花园业委会将一面“为民办事关怀至，携手共建业委会”的锦旗赠送给厦村社区党委，以感谢党委社区力排万难推进业委会成立及常态指导业委会开展工作。据悉，2019年初，南湾厦村社区国香山花园首届业委会筹备工作开始启动，但推进过程中遇到重重阻力。对此，南湾街道和社区联动通过党建引领破除阻力。2020年4月，针对因新旧物业管理条例过渡而引发业主投诉，导致业委会筹备工作被多次暂停的问题，厦村社区党委通过面对面约谈、电话联系等方式全面了解业主诉求，同步与街道物管中心迅速研究方案，及时推动问题解决。
     　　为了解决物业对新业委会成立的抵触情绪，对业委会筹备工作不配合的问题，厦村社区党委联合街道物管中心先后多次约谈物业负责人，分别从属地管理、行业监管角度对物业进行约谈教育，消除物业顾虑，由“不信任”到“听党话、跟党走”，为首届业委会选举顺利开展破除阻力。
     历时两年有余，在厦村社区党委的强力推动下，恒大国香山花园首届业委会成立。为感谢厦村社区党委的热情帮助，业主特意送来了锦旗。厦村社区党委将始终遵循“为人民服务”的原则，持续致力通过党建引领，引导业委会积极参与小区治理，构建小区共建共治共享格局。
     * time : 2021-03-14
     */

    public int id;
    public String title;
    public String content;
    public String time;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
