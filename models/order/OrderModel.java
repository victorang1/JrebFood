package models.order;

import java.util.Date;
import java.util.Vector;

import core.Model;
import models.user.User;

public interface OrderModel {
    
    public Integer addOrder(User user, Date date);
    public void addDetail(Integer orderId, Integer foodId, Integer qty);
    public Model getOne(Integer orderId);
    // Todo jadiin status jadi class tersendiri
    public Boolean updateStatus(Integer orderId, String status);
    public Boolean takeOrder(Integer orderId, Integer driverId);
    public Boolean removeOrder(Integer orderId);
    public void removeDetail(Integer orderId);
    public Vector<Model> getAll();
    public Vector<Model> viewDetailById(Integer orderId);
}
