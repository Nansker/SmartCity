package com.nansk.smartcity.beans;

/*
 * @Author 南山客
 * @Email 2771557108@qq.com
 * @Create 2021/09/30 11:49
 * @Description 生活资讯-天气
 */

import java.io.Serializable;
import java.util.List;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/13 11:15
 * @description
 */

public class WeatherBean {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"today":{"hours":[{"hour":"11:00","weather":"晴","temperature":19},{"hour":"12:00","weather":"晴","temperature":21},{"hour":"13:00","weather":"晴","temperature":19},{"hour":"14:00","weather":"晴","temperature":21},{"hour":"15:00","weather":"晴","temperature":19},{"hour":"16:00","weather":"晴","temperature":21}],"airQuantity":{"no2":33,"pm25":22,"o3":28,"so2":9,"pm10":50,"co":0.3},"comfortLevel":{"uv":1,"dressingIndex":"T恤","humidity":49,"coldIndex":"不易","apparentTemperature":14,"uvIndex":"弱","washIndex":"适宜","sportIndex":"较适宜"},"tempInfo":{"maxTemperature":"12","uv":"弱","minTemperature":"24","temperature":"21","weather":"晴","humidity":"60","air":"无污染","apparentTemperature":"15","label":"今天","day":"9月30日今天"},"updateTime":"11:00","wind":{"windStrength":"2级","windDirection":"西风"}},"weatherList":[{"maxTemperature":"12","uv":"弱","minTemperature":"23","temperature":"10","weather":"晴","humidity":50,"air":"良好","apparentTemperature":"15","label":"昨天","day":"9月29日昨天"},{"maxTemperature":"12","uv":"弱","minTemperature":"24","temperature":"21","weather":"晴","humidity":"60","air":"无污染","apparentTemperature":"15","label":"今天","day":"9月30日今天"},{"maxTemperature":"15","uv":"弱","minTemperature":"25","temperature":"20","weather":"晴","humidity":50,"air":"良好","apparentTemperature":"16","label":"未来第一天","day":"10月01日明天"},{"maxTemperature":"12","uv":"弱","minTemperature":"24","temperature":"20","weather":"多云","humidity":50,"air":"良好","apparentTemperature":"17","label":"未来第二天","day":"10月02日星期六"},{"maxTemperature":"14","uv":"弱","minTemperature":"26","temperature":"20","weather":"晴","humidity":50,"air":"无污染","apparentTemperature":"15","label":"未来第三天","day":"10月03日星期日"},{"maxTemperature":"16","uv":"弱","minTemperature":"25","temperature":"21","weather":"晴","humidity":50,"air":"良好","apparentTemperature":"14","label":"未来第四天","day":"10月04日星期一"},{"maxTemperature":"14","uv":"弱","minTemperature":"26","temperature":"20","weather":"晴","humidity":50,"air":"无污染","apparentTemperature":"20","label":"未来第五天","day":"10月05日星期二"}]}
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

    public static class DataBean implements Serializable {
        /**
         * today : {"hours":[{"hour":"11:00","weather":"晴","temperature":19},{"hour":"12:00","weather":"晴","temperature":21},{"hour":"13:00","weather":"晴","temperature":19},{"hour":"14:00","weather":"晴","temperature":21},{"hour":"15:00","weather":"晴","temperature":19},{"hour":"16:00","weather":"晴","temperature":21}],"airQuantity":{"no2":33,"pm25":22,"o3":28,"so2":9,"pm10":50,"co":0.3},"comfortLevel":{"uv":1,"dressingIndex":"T恤","humidity":49,"coldIndex":"不易","apparentTemperature":14,"uvIndex":"弱","washIndex":"适宜","sportIndex":"较适宜"},"tempInfo":{"maxTemperature":"12","uv":"弱","minTemperature":"24","temperature":"21","weather":"晴","humidity":"60","air":"无污染","apparentTemperature":"15","label":"今天","day":"9月30日今天"},"updateTime":"11:00","wind":{"windStrength":"2级","windDirection":"西风"}}
         * weatherList : [{"maxTemperature":"12","uv":"弱","minTemperature":"23","temperature":"10","weather":"晴","humidity":50,"air":"良好","apparentTemperature":"15","label":"昨天","day":"9月29日昨天"},{"maxTemperature":"12","uv":"弱","minTemperature":"24","temperature":"21","weather":"晴","humidity":"60","air":"无污染","apparentTemperature":"15","label":"今天","day":"9月30日今天"},{"maxTemperature":"15","uv":"弱","minTemperature":"25","temperature":"20","weather":"晴","humidity":50,"air":"良好","apparentTemperature":"16","label":"未来第一天","day":"10月01日明天"},{"maxTemperature":"12","uv":"弱","minTemperature":"24","temperature":"20","weather":"多云","humidity":50,"air":"良好","apparentTemperature":"17","label":"未来第二天","day":"10月02日星期六"},{"maxTemperature":"14","uv":"弱","minTemperature":"26","temperature":"20","weather":"晴","humidity":50,"air":"无污染","apparentTemperature":"15","label":"未来第三天","day":"10月03日星期日"},{"maxTemperature":"16","uv":"弱","minTemperature":"25","temperature":"21","weather":"晴","humidity":50,"air":"良好","apparentTemperature":"14","label":"未来第四天","day":"10月04日星期一"},{"maxTemperature":"14","uv":"弱","minTemperature":"26","temperature":"20","weather":"晴","humidity":50,"air":"无污染","apparentTemperature":"20","label":"未来第五天","day":"10月05日星期二"}]
         */

        public TodayBean today;
        public List<WeatherListBean> weatherList;

        public TodayBean getToday() {
            return today;
        }

        public void setToday(TodayBean today) {
            this.today = today;
        }

        public List<WeatherListBean> getWeatherList() {
            return weatherList;
        }

        public void setWeatherList(List<WeatherListBean> weatherList) {
            this.weatherList = weatherList;
        }

        public static class TodayBean {
            /**
             * hours : [{"hour":"11:00","weather":"晴","temperature":19},{"hour":"12:00","weather":"晴","temperature":21},{"hour":"13:00","weather":"晴","temperature":19},{"hour":"14:00","weather":"晴","temperature":21},{"hour":"15:00","weather":"晴","temperature":19},{"hour":"16:00","weather":"晴","temperature":21}]
             * airQuantity : {"no2":33,"pm25":22,"o3":28,"so2":9,"pm10":50,"co":0.3}
             * comfortLevel : {"uv":1,"dressingIndex":"T恤","humidity":49,"coldIndex":"不易","apparentTemperature":14,"uvIndex":"弱","washIndex":"适宜","sportIndex":"较适宜"}
             * tempInfo : {"maxTemperature":"12","uv":"弱","minTemperature":"24","temperature":"21","weather":"晴","humidity":"60","air":"无污染","apparentTemperature":"15","label":"今天","day":"9月30日今天"}
             * updateTime : 11:00
             * wind : {"windStrength":"2级","windDirection":"西风"}
             */

            public AirQuantityBean airQuantity;
            public ComfortLevelBean comfortLevel;
            public TempInfoBean tempInfo;
            public String updateTime;
            public WindBean wind;
            public List<HoursBean> hours;

            public AirQuantityBean getAirQuantity() {
                return airQuantity;
            }

            public void setAirQuantity(AirQuantityBean airQuantity) {
                this.airQuantity = airQuantity;
            }

            public ComfortLevelBean getComfortLevel() {
                return comfortLevel;
            }

            public void setComfortLevel(ComfortLevelBean comfortLevel) {
                this.comfortLevel = comfortLevel;
            }

            public TempInfoBean getTempInfo() {
                return tempInfo;
            }

            public void setTempInfo(TempInfoBean tempInfo) {
                this.tempInfo = tempInfo;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public List<HoursBean> getHours() {
                return hours;
            }

            public void setHours(List<HoursBean> hours) {
                this.hours = hours;
            }

            public static class AirQuantityBean {
                /**
                 * no2 : 33
                 * pm25 : 22
                 * o3 : 28
                 * so2 : 9
                 * pm10 : 50
                 * co : 0.3
                 */

                public int no2;
                public int pm25;
                public int o3;
                public int so2;
                public int pm10;
                public double co;

                public int getNo2() {
                    return no2;
                }

                public void setNo2(int no2) {
                    this.no2 = no2;
                }

                public int getPm25() {
                    return pm25;
                }

                public void setPm25(int pm25) {
                    this.pm25 = pm25;
                }

                public int getO3() {
                    return o3;
                }

                public void setO3(int o3) {
                    this.o3 = o3;
                }

                public int getSo2() {
                    return so2;
                }

                public void setSo2(int so2) {
                    this.so2 = so2;
                }

                public int getPm10() {
                    return pm10;
                }

                public void setPm10(int pm10) {
                    this.pm10 = pm10;
                }

                public double getCo() {
                    return co;
                }

                public void setCo(double co) {
                    this.co = co;
                }
            }

            public static class ComfortLevelBean {
                /**
                 * uv : 1
                 * dressingIndex : T恤
                 * humidity : 49
                 * coldIndex : 不易
                 * apparentTemperature : 14
                 * uvIndex : 弱
                 * washIndex : 适宜
                 * sportIndex : 较适宜
                 */

                public int uv;
                public String dressingIndex;
                public int humidity;
                public String coldIndex;
                public int apparentTemperature;
                public String uvIndex;
                public String washIndex;
                public String sportIndex;

                public int getUv() {
                    return uv;
                }

                public void setUv(int uv) {
                    this.uv = uv;
                }

                public String getDressingIndex() {
                    return dressingIndex;
                }

                public void setDressingIndex(String dressingIndex) {
                    this.dressingIndex = dressingIndex;
                }

                public int getHumidity() {
                    return humidity;
                }

                public void setHumidity(int humidity) {
                    this.humidity = humidity;
                }

                public String getColdIndex() {
                    return coldIndex;
                }

                public void setColdIndex(String coldIndex) {
                    this.coldIndex = coldIndex;
                }

                public int getApparentTemperature() {
                    return apparentTemperature;
                }

                public void setApparentTemperature(int apparentTemperature) {
                    this.apparentTemperature = apparentTemperature;
                }

                public String getUvIndex() {
                    return uvIndex;
                }

                public void setUvIndex(String uvIndex) {
                    this.uvIndex = uvIndex;
                }

                public String getWashIndex() {
                    return washIndex;
                }

                public void setWashIndex(String washIndex) {
                    this.washIndex = washIndex;
                }

                public String getSportIndex() {
                    return sportIndex;
                }

                public void setSportIndex(String sportIndex) {
                    this.sportIndex = sportIndex;
                }
            }

            public static class TempInfoBean {
                /**
                 * maxTemperature : 12
                 * uv : 弱
                 * minTemperature : 24
                 * temperature : 21
                 * weather : 晴
                 * humidity : 60
                 * air : 无污染
                 * apparentTemperature : 15
                 * label : 今天
                 * day : 9月30日今天
                 */

                public String maxTemperature;
                public String uv;
                public String minTemperature;
                public String temperature;
                public String weather;
                public String humidity;
                public String air;
                public String apparentTemperature;
                public String label;
                public String day;

                public String getMaxTemperature() {
                    return maxTemperature;
                }

                public void setMaxTemperature(String maxTemperature) {
                    this.maxTemperature = maxTemperature;
                }

                public String getUv() {
                    return uv;
                }

                public void setUv(String uv) {
                    this.uv = uv;
                }

                public String getMinTemperature() {
                    return minTemperature;
                }

                public void setMinTemperature(String minTemperature) {
                    this.minTemperature = minTemperature;
                }

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public String getHumidity() {
                    return humidity;
                }

                public void setHumidity(String humidity) {
                    this.humidity = humidity;
                }

                public String getAir() {
                    return air;
                }

                public void setAir(String air) {
                    this.air = air;
                }

                public String getApparentTemperature() {
                    return apparentTemperature;
                }

                public void setApparentTemperature(String apparentTemperature) {
                    this.apparentTemperature = apparentTemperature;
                }

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }
            }

            public static class WindBean {
                /**
                 * windStrength : 2级
                 * windDirection : 西风
                 */

                public String windStrength;
                public String windDirection;

                public String getWindStrength() {
                    return windStrength;
                }

                public void setWindStrength(String windStrength) {
                    this.windStrength = windStrength;
                }

                public String getWindDirection() {
                    return windDirection;
                }

                public void setWindDirection(String windDirection) {
                    this.windDirection = windDirection;
                }
            }

            public static class HoursBean {
                /**
                 * hour : 11:00
                 * weather : 晴
                 * temperature : 19
                 */

                public String hour;
                public String weather;
                public int temperature;

                public String getHour() {
                    return hour;
                }

                public void setHour(String hour) {
                    this.hour = hour;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public int getTemperature() {
                    return temperature;
                }

                public void setTemperature(int temperature) {
                    this.temperature = temperature;
                }
            }
        }

        public static class WeatherListBean {
            /**
             * maxTemperature : 12
             * uv : 弱
             * minTemperature : 23
             * temperature : 10
             * weather : 晴
             * humidity : 50
             * air : 良好
             * apparentTemperature : 15
             * label : 昨天
             * day : 9月29日昨天
             */

            public String maxTemperature;
            public String uv;
            public String minTemperature;
            public String temperature;
            public String weather;
            public int humidity;
            public String air;
            public String apparentTemperature;
            public String label;
            public String day;

            public String getMaxTemperature() {
                return maxTemperature;
            }

            public void setMaxTemperature(String maxTemperature) {
                this.maxTemperature = maxTemperature;
            }

            public String getUv() {
                return uv;
            }

            public void setUv(String uv) {
                this.uv = uv;
            }

            public String getMinTemperature() {
                return minTemperature;
            }

            public void setMinTemperature(String minTemperature) {
                this.minTemperature = minTemperature;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public int getHumidity() {
                return humidity;
            }

            public void setHumidity(int humidity) {
                this.humidity = humidity;
            }

            public String getAir() {
                return air;
            }

            public void setAir(String air) {
                this.air = air;
            }

            public String getApparentTemperature() {
                return apparentTemperature;
            }

            public void setApparentTemperature(String apparentTemperature) {
                this.apparentTemperature = apparentTemperature;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }
        }
    }
}
