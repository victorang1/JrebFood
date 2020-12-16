package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import core.Controller;
import core.Model;
import core.View;
import models.cart.Cart;
import models.order.Order;
import models.order.OrderModel;
import models.orderdetail.OrderDetail;
import models.orderdetail.OrderDetailModel;
import models.user.User;
import util.DateUtil;

public class OrderHandler extends Controller {

    private static OrderHandler instance;
    private OrderModel model;
    private OrderDetailModel detailModel;
    public Integer lastCheckoutOrderId = -1;
    
    private OrderHandler() {
        model = new Order();
        detailModel = new OrderDetail();
    }

    public static OrderHandler getInstance() {
        if (instance == null) {
            instance = new OrderHandler();
        }
        return instance;
    }
    
    public Boolean addOrder(User user) {
        Date currentDate = DateUtil.getCurrentDateWithTimezone();
        int lastInsertedOrderId = model.addOrder(user, currentDate);
        if (lastInsertedOrderId != -1) {
            lastCheckoutOrderId = lastInsertedOrderId;
            Vector<Model> userCarts = CartHandler.getInstance().viewAll(user.getUserId());
            for (Model model: userCarts) {
                Cart cart = (Cart) model;
                detailModel.addDetail(lastInsertedOrderId, cart.getFood().getFoodId(), cart.getQty());
            }
            CartHandler.getInstance().removeAll(user.getUserId());
            return true;
        }
        return false;
    }

    public void addDetail(Integer orderId, Integer foodId, Integer qty) {
        // TODO Auto-generated method stub
        
    }

    public Boolean removeOrder(Integer orderId) {
        Order order = (Order) model.getOne(orderId);
        if (order != null) {
            if (validateStatus(order.getStatus())) {
                model.removeOrder(orderId);
                model.removeDetail(orderId);
                setErrorMessage("Cancel Success");
                return true;
            }
            else {
                setErrorMessage("Driver has taken the order");
                return false;
            }
        }
        else {
            setErrorMessage("Error occured");
            return false;
        }
	}

	public void removeDetail(Integer orderId) {
		model.removeDetail(orderId);
	}

	public Model getOne(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}
    
    public Vector<Model> viewAllHistory(Integer id) {
        return null;
    }

    public Vector<Model> viewById(Integer orderId) {
        return null;
    }

    public Vector<Model> viewOrderList(Integer orderId) {
        return null;
    }

    public Vector<Model> viewDetailById(Integer orderId) {
		return model.viewDetailById(orderId);
    }

    public Boolean updateStatus(Integer orderId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean takeOrder(Integer orderId, Integer driverId) {
		// TODO Auto-generated method stub
		return null;
    }

    private Boolean validateStatus(String status) {
        return status.equalsIgnoreCase("Not Accepted");
    }
    
    public View viewTakenOrder() {
        return null;
    }

    public View viewDetails() {
        return null;
    }

    public View viewHistory() {
        return null;
    }

    public View viewManageStatusForm() {
        return null;
    }

    public View viewProfit() {
        return null;
    }

    public View filterDriver() {
        return null;
    }

    public View viewOrderQueue() {
        return null;
    }

    public View viewAvailableOrder() {
        return null;
    }
}
