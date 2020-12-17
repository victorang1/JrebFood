package controllers;

import java.util.Date;
import java.util.Vector;

import constants.OrderStatus;
import core.Controller;
import core.Model;
import core.View;
import models.Session;
import models.cart.Cart;
import models.order.Order;
import models.order.OrderModel;
import models.orderdetail.OrderDetail;
import models.orderdetail.OrderDetailModel;
import models.user.User;
import util.DateUtil;
import views.order.AvailableOrderView;
import views.order.DetailsView;
import views.order.HistoryView;
import views.order.OrdersView;
import views.order.TakenOrderView;
import views.order.UserOrdersView;

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
                setErrorMessage("Cancel Order Success");
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
        Vector<Model> userOrderHistories = new Vector<>();
        for (Model m : model.getAll()) {
            Order order = (Order) m;
            if (order.getUserId().equals(id) && order.getStatus().equals(OrderStatus.FINISHED)) {
                userOrderHistories.add(order);
            }
        }
        return userOrderHistories;
     }

    public Vector<Model> viewById(Integer orderId) {
        return null;
    }

    public Vector<Model> viewOrderList(String status) {
        Vector<Model> orders = new Vector<>();
        for (Model m : model.getAll()) {
            Order order = (Order) m;
            if (order.getStatus().equals(status)) {
                orders.add(order);
            }
        }
        return orders;
    }

    public Vector<Model> getUserOrders() {
        Vector<Model> orders = new Vector<>();
        for (Model m : model.getAll()) {
            Order order = (Order) m;
            if (order.getUserId().equals(Session.getInstance().getUserId()) && !order.getStatus().equals(OrderStatus.FINISHED)) {
                orders.add(order);
            }
        }
        return orders;
    }

    public Vector<Model> viewOrderList(Integer driverId) {
        Vector<Model> orders = new Vector<>();
        for (Model m : model.getAll()) {
            Order order = (Order) m;
            if (order.getStatus().equals(OrderStatus.ACCEPTED) && order.getDriverId() == driverId) {
                orders.add(order);
            }
        }
        return orders;
    }

    public Vector<Model> viewDetailById(Integer orderId) {
		return detailModel.viewDetailById(orderId);
    }

    public Boolean updateStatus(Integer orderId, String status) {
        // Diliat dari sequence diagram disini mesti validate status, cmn kita bingung sih
        // apa yang perlu divalidasi, karena di activity diagram juga tidak digambarkan...
        // jadi asumsi saya ngecek apakah ordernya sudah accept/blom
		Order order = (Order) model.getOne(orderId);
        if (validateStatus(order.getStatus())) {
            setErrorMessage("This order is not taken yet");
            return false;
        }
        else {
            return model.updateStatus(orderId, status);
        }
	}

	public Boolean takeOrder(Integer orderId, Integer driverId) {
		return model.takeOrder(orderId, driverId);
    }

    private Boolean validateStatus(String status) {
        return status.equalsIgnoreCase(OrderStatus.NOT_ACCEPTED);
    }
    
    public View viewTakenOrder() {
        return new TakenOrderView();
    }

    public View viewOrders() {
        return new OrdersView();
    }

    public View viewUserOrders() {
        return new UserOrdersView();
    }

    public View viewDetails() {
        return new DetailsView();
    }

    public View viewHistory() {
        return new HistoryView();
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
        return new AvailableOrderView();
    }
}
