package models.order;

import java.util.ArrayList;
import java.util.Date;

import models.Model;
import models.orderdetail.OrderDetail;
import models.user.User;

public class OrderImpl extends Model implements OrderModel {
    
    @Override
    public Boolean addOrder(User user, Date date) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addDetail(Integer orderId, Integer foodId, Integer qty) {
        // TODO Auto-generated method stub
        
    }

	@Override
	public Order getOne(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateStatus(Integer orderId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean takeOrder(Integer orderId, Integer driverId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean removeOrder(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeDetail(Integer orderId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Order> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<OrderDetail> viewDetailById(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}
}
