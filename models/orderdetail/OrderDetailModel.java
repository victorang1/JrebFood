package models.orderdetail;

import java.util.Vector;

import core.Model;

public interface OrderDetailModel {

    public void addDetail(Integer orderId, Integer foodId, Integer qty);
    public Boolean removeOrder(Integer orderId);
    public Vector<Model> viewDetailById(Integer orderId);
}
