package com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config;

public class Order {

    private String orderid;
    private String date;
    private String startTime;
    private String finishTime;
    private String cancelTime;
    private String departure;
    private String destination;
    private String itemType;
    private String receiverID;
    private String senderID;
    private String status;
    //private String orderTime;  maybe later

    //private String status;  Sage maybe use it ?


//   private int IgSuccessFailImg for history Order;
//   private String arrived for current Order;

    public Order(){
        this.orderid="";
        this.date="";
        this.startTime="";
        this.finishTime="";
        this.cancelTime="";
        this.departure="";
        this.destination="";
        this.itemType="";
        this.receiverID="";
        this.senderID="";
        this.status="";
    }
    public Order(String orderid ,String cancelTime,String date,String departure, String destination, String finishTime , String itemType,String receiverID,String senderID,String startTime,String status){
        this.orderid=orderid;
        this.date=date;
        this.startTime=startTime;
        this.finishTime=finishTime;
        this.cancelTime=cancelTime;
        this.departure=departure;
        this.destination=destination;
        this.itemType=itemType;
        this.receiverID=receiverID;
        this.senderID=senderID;
        this.status=status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getOrderid() {
        return orderid;
    }

    public String getDate() {
        return date;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getItemType() {
        return itemType;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }


}
