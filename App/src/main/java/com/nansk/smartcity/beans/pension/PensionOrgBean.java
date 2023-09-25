package com.nansk.smartcity.beans.pension;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/19 11:45
 * @description
 */

import java.io.Serializable;

public class PensionOrgBean implements Serializable {

    /**
     * id : 1
     * name : 同辉养老
     * introduce : 同辉养老（集团）是一家有梦想、有强烈社会责任感的医养健康服务型企业，定位于一家连锁化的医养结合型中高端养老服务品牌。我们坚持以“同辉医养，让长者生命更健康”的根本服务宗旨，以打造“中国一流医养服务品牌”为发展愿景，以“代忙碌儿女尽孝，替孤独父母解愁，为党和政府分忧”为己任，致力于“提供高品质、专业化的医养健康服务，履行社会责任”的企业使命，为离退休长者提供无微不至的“医养康护娱居”一站式服务，像爱自己的孩子一样爱长者，替天下忙碌儿女尽一份爱心，为国家社会贡献一份责任。
     人人会变老，家家有老人。同辉养老（集团）崇尚的是天底下最崇高的为老服务事业。做人做事成功的关键，就是从构建长者健康美好幸福新生活开始；服务长者，服务老人，就是在创造美好幸福生活！
     立足重庆主城以夯实基础，逐步拓展构建覆盖全市的服务网络。近期以机构养老为主体，配套建设市级、区级社区养老服务中心，利用杰佳通智慧养老平台为核心平台，连锁开展居家养老服务，适时承接公建民营项目，逐步开办养老职业培训、养老专业咨询服务等业务，逐步实现机构养老社区化、社区养老专业化、连锁养老品质化发展目标。

     */

    public int id;
    public String name;
    public String introduce;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
