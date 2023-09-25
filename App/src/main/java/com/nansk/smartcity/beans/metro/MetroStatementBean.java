package com.nansk.smartcity.beans.metro;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/11 15:12
 * @description
 */

public class MetroStatementBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"searchValue":null,"createBy":null,"createTime":"2021-04-03 15:20:53","updateBy":null,"updateTime":"2021-05-08 15:11:33","remark":null,"params":{},"id":1,"title":"乘车须知","content":"<p>为保障北京地铁的正常运营秩序，根据2004年12月28日起实施的《北京市地铁运营管理暂行办法》（北京市人民政府令第140号）和2005年8月1日起施行的《城市轨道交通运营管理办法》的规定，北京市地铁有限公司（以下简称\u201c地铁公司\u201d）特制定本须知。 <\/p><p>凡进入地铁范围者均须自觉遵守本须知。<\/p><p>一、地铁实行一人一票制，每位成年乘客可免费携带1名身高1.1米以下的儿童乘车，超过1名的，须按超过人数购买成人全票。 <\/p><p>二、单程票仅限本站当日进站有效，出站时车票由出闸机回收，逾期为废票并予以回收。 <\/p><p>三、乘客进闸后，停留时限为120分钟。超时出站，要补交最高单程票价车费；如既超时又超程，须先补交超程车资后再补交最高单程票价车费。 <\/p><p>四、乘客应妥善保管好车票，若在付费区内遗失车票，乘客出站前须到车站售票问讯处补交车票成本费10元和最高单程票价车费后才允许出站。 <\/p><p>五、老人、离休干部、残疾人凭有效证件可以免费乘坐地铁，办理学生储值票的学生可以享受单程五折扣值优惠。 <\/p><p>六、需索取发票的乘客，若持单程票，请凭车票在出闸前到车站售票问讯处领取等额发票；若持储值票，请在每次充值后凭电脑小票到车站问讯处领取等额发票。 <\/p><p>七、地铁公司不办理\u201c深圳通\u201d储值票退、换票业务，乘客需要办理相关业务时，请到\u201c深圳通\u201d公司客户服务中心或指定网点办理。 <\/p><p>八、已购单程票而未进闸的乘客需办理退票时，须到车站售票问讯处经地铁工作人员验证，车票属于本站当日出售且无进站信息，可按车票面值退票。 <\/p><p>九、因地铁原因需退票时，持单程票乘客可在5日内到地铁任一车站办理退票，持储值票乘客可在下次进站时免费更新。 <\/p><p>十、携带重量超过20公斤以上或体积大于0.06立方米的物品乘车时，需购买与车资等额的行李票；重量超过30公斤或长度超过1.6米或体积超过0.1立方米的物品，一律不得携带进站乘车。 <\/p>","type":"1"}
     */

    public String msg;
    public int code;
    public DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * searchValue : null
         * createBy : null
         * createTime : 2021-04-03 15:20:53
         * updateBy : null
         * updateTime : 2021-05-08 15:11:33
         * remark : null
         * params : {}
         * id : 1
         * title : 乘车须知
         * content : <p>为保障北京地铁的正常运营秩序，根据2004年12月28日起实施的《北京市地铁运营管理暂行办法》（北京市人民政府令第140号）和2005年8月1日起施行的《城市轨道交通运营管理办法》的规定，北京市地铁有限公司（以下简称“地铁公司”）特制定本须知。 </p><p>凡进入地铁范围者均须自觉遵守本须知。</p><p>一、地铁实行一人一票制，每位成年乘客可免费携带1名身高1.1米以下的儿童乘车，超过1名的，须按超过人数购买成人全票。 </p><p>二、单程票仅限本站当日进站有效，出站时车票由出闸机回收，逾期为废票并予以回收。 </p><p>三、乘客进闸后，停留时限为120分钟。超时出站，要补交最高单程票价车费；如既超时又超程，须先补交超程车资后再补交最高单程票价车费。 </p><p>四、乘客应妥善保管好车票，若在付费区内遗失车票，乘客出站前须到车站售票问讯处补交车票成本费10元和最高单程票价车费后才允许出站。 </p><p>五、老人、离休干部、残疾人凭有效证件可以免费乘坐地铁，办理学生储值票的学生可以享受单程五折扣值优惠。 </p><p>六、需索取发票的乘客，若持单程票，请凭车票在出闸前到车站售票问讯处领取等额发票；若持储值票，请在每次充值后凭电脑小票到车站问讯处领取等额发票。 </p><p>七、地铁公司不办理“深圳通”储值票退、换票业务，乘客需要办理相关业务时，请到“深圳通”公司客户服务中心或指定网点办理。 </p><p>八、已购单程票而未进闸的乘客需办理退票时，须到车站售票问讯处经地铁工作人员验证，车票属于本站当日出售且无进站信息，可按车票面值退票。 </p><p>九、因地铁原因需退票时，持单程票乘客可在5日内到地铁任一车站办理退票，持储值票乘客可在下次进站时免费更新。 </p><p>十、携带重量超过20公斤以上或体积大于0.06立方米的物品乘车时，需购买与车资等额的行李票；重量超过30公斤或长度超过1.6米或体积超过0.1立方米的物品，一律不得携带进站乘车。 </p>
         * type : 1
         */

        public Object searchValue;
        public Object createBy;
        public String createTime;
        public Object updateBy;
        public String updateTime;
        public Object remark;
        public int id;
        public String title;
        public String content;
        public String type;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
