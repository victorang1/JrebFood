package models.orderdetail;

import java.util.ArrayList;

public interface OrderDetailModel {
    
    public void addDetail(Integer orderId, Integer foodId, Integer qty);
    public Boolean removeOrder(Integer orderId);
    public ArrayList<OrderDetail> viewDetailById(Integer orderId);
}
