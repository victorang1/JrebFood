package models.order;

import java.util.ArrayList;
import java.util.Date;

import models.orderdetail.*;
import models.user.User;

public interface OrderModel {
    
    public Boolean addOrder(User user, Date date);
    public void addDetail(Integer orderId, Integer foodId, Integer qty);
    public Order getOne(Integer orderId);
    // Todo jadiin status jadi class tersendiri
    public Boolean updateStatus(Integer orderId, String status);
    public Boolean takeOrder(Integer orderId, Integer driverId);
    public Boolean removeOrder(Integer orderId);
    public void removeDetail(Integer orderId);
    public ArrayList<Order> getAll();
    public ArrayList<OrderDetail> viewDetailById(Integer orderId);
}
