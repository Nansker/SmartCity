package com.nansk.smartcity.beans.metro;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/06 13:52
 * @description
 */

import java.util.List;

public class MetroListBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : [{"lineId":31,"lineName":"2号线","preStep":{"name":"朝阳门","lines":[{"lineId":21,"lineName":"6号线"},{"lineId":24,"lineName":"6号线"},{"lineId":31,"lineName":"2号线"},{"lineId":38,"lineName":"2号线"}]},"nextStep":{"name":"北京站","lines":[{"lineId":31,"lineName":"2号线"},{"lineId":38,"lineName":"2号线"}]},"currentName":"建国门","reachTime":1},{"lineId":33,"lineName":"1号线","preStep":{"name":"东单","lines":[{"lineId":33,"lineName":"1号线"},{"lineId":34,"lineName":"5号线"},{"lineId":35,"lineName":"1号线"},{"lineId":39,"lineName":"5号线"}]},"nextStep":{"name":"永安里","lines":[{"lineId":33,"lineName":"1号线"},{"lineId":35,"lineName":"1号线"}]},"currentName":"建国门","reachTime":1},{"lineId":35,"lineName":"1号线","preStep":{"name":"永安里","lines":[{"lineId":33,"lineName":"1号线"},{"lineId":35,"lineName":"1号线"}]},"nextStep":{"name":"东单","lines":[{"lineId":33,"lineName":"1号线"},{"lineId":34,"lineName":"5号线"},{"lineId":35,"lineName":"1号线"},{"lineId":39,"lineName":"5号线"}]},"currentName":"建国门","reachTime":7},{"lineId":38,"lineName":"2号线","preStep":{"name":"北京站","lines":[{"lineId":31,"lineName":"2号线"},{"lineId":38,"lineName":"2号线"}]},"nextStep":{"name":"朝阳门","lines":[{"lineId":21,"lineName":"6号线"},{"lineId":24,"lineName":"6号线"},{"lineId":31,"lineName":"2号线"},{"lineId":38,"lineName":"2号线"}]},"currentName":"建国门","reachTime":7}]
     */

    public String msg;
    public int code;
    public List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * lineId : 31
         * lineName : 2号线
         * preStep : {"name":"朝阳门","lines":[{"lineId":21,"lineName":"6号线"},{"lineId":24,"lineName":"6号线"},{"lineId":31,"lineName":"2号线"},{"lineId":38,"lineName":"2号线"}]}
         * nextStep : {"name":"北京站","lines":[{"lineId":31,"lineName":"2号线"},{"lineId":38,"lineName":"2号线"}]}
         * currentName : 建国门
         * reachTime : 1
         */

        public int lineId;
        public String lineName;
        public PreStepBean preStep;
        public NextStepBean nextStep;
        public String currentName;
        public int reachTime;

        public int getLineId() {
            return lineId;
        }

        public void setLineId(int lineId) {
            this.lineId = lineId;
        }

        public String getLineName() {
            return lineName;
        }

        public void setLineName(String lineName) {
            this.lineName = lineName;
        }

        public PreStepBean getPreStep() {
            return preStep;
        }

        public void setPreStep(PreStepBean preStep) {
            this.preStep = preStep;
        }

        public NextStepBean getNextStep() {
            return nextStep;
        }

        public void setNextStep(NextStepBean nextStep) {
            this.nextStep = nextStep;
        }

        public String getCurrentName() {
            return currentName;
        }

        public void setCurrentName(String currentName) {
            this.currentName = currentName;
        }

        public int getReachTime() {
            return reachTime;
        }

        public void setReachTime(int reachTime) {
            this.reachTime = reachTime;
        }

        public static class PreStepBean {
            /**
             * name : 朝阳门
             * lines : [{"lineId":21,"lineName":"6号线"},{"lineId":24,"lineName":"6号线"},{"lineId":31,"lineName":"2号线"},{"lineId":38,"lineName":"2号线"}]
             */

            public String name;
            public List<LinesBean> lines;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<LinesBean> getLines() {
                return lines;
            }

            public void setLines(List<LinesBean> lines) {
                this.lines = lines;
            }

            public static class LinesBean {
                /**
                 * lineId : 21
                 * lineName : 6号线
                 */

                public int lineId;
                public String lineName;

                public int getLineId() {
                    return lineId;
                }

                public void setLineId(int lineId) {
                    this.lineId = lineId;
                }

                public String getLineName() {
                    return lineName;
                }

                public void setLineName(String lineName) {
                    this.lineName = lineName;
                }
            }
        }

        public static class NextStepBean {
            /**
             * name : 北京站
             * lines : [{"lineId":31,"lineName":"2号线"},{"lineId":38,"lineName":"2号线"}]
             */

            public String name;
            public List<LinesBeanX> lines;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<LinesBeanX> getLines() {
                return lines;
            }

            public void setLines(List<LinesBeanX> lines) {
                this.lines = lines;
            }

            public static class LinesBeanX {
                /**
                 * lineId : 31
                 * lineName : 2号线
                 */

                public int lineId;
                public String lineName;

                public int getLineId() {
                    return lineId;
                }

                public void setLineId(int lineId) {
                    this.lineId = lineId;
                }

                public String getLineName() {
                    return lineName;
                }

                public void setLineName(String lineName) {
                    this.lineName = lineName;
                }
            }
        }
    }
}
