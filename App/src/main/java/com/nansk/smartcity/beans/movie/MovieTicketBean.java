package com.nansk.smartcity.beans.movie;

/*
 * @author 南山客
 * @email 2771557108@qq.com
 * @create 2021/10/14 15:20
 * @description 购票
 */

import java.io.Serializable;
import java.util.List;

public class MovieTicketBean implements Serializable {

    /**
     * endTime : 06:00
     * movieId : 5
     * orderItems : [{"price":36,"seatCol":"5","seatId":6,"seatRow":"7"}]
     * playDate : 2021-09-10
     * price : 50.0
     * roomId : 1
     * startTime : 04:30
     * theaterId : 16
     * timesId : 21
     */

    public String endTime;
    public int movieId;
    public String playDate;
    public Number price;
    public int roomId;
    public String startTime;
    public int theaterId;
    public int timesId;
    public List<OrderItemsBean> orderItems;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getPlayDate() {
        return playDate;
    }

    public void setPlayDate(String playDate) {
        this.playDate = playDate;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public int getTimesId() {
        return timesId;
    }

    public void setTimesId(int timesId) {
        this.timesId = timesId;
    }

    public List<OrderItemsBean> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemsBean> orderItems) {
        this.orderItems = orderItems;
    }

    public static class OrderItemsBean implements Serializable{
        /**
         * price : 36.0
         * seatCol : 5
         * seatId : 6
         * seatRow : 7
         */

        public Number price;
        public String seatCol;
        public int seatId;
        public String seatRow;

        public Number getPrice() {
            return price;
        }

        public void setPrice(Number price) {
            this.price = price;
        }

        public String getSeatCol() {
            return seatCol;
        }

        public void setSeatCol(String seatCol) {
            this.seatCol = seatCol;
        }

        public int getSeatId() {
            return seatId;
        }

        public void setSeatId(int seatId) {
            this.seatId = seatId;
        }

        public String getSeatRow() {
            return seatRow;
        }

        public void setSeatRow(String seatRow) {
            this.seatRow = seatRow;
        }
    }
}
