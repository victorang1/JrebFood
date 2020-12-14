package controllers;

import java.util.ArrayList;

import core.Controller;
import core.Model;
import core.View;
import models.user.User;

public class OrderHandler extends Controller {

    private static OrderHandler instance;

    public static OrderHandler getInstance() {
        if (instance == null) {
            instance = new OrderHandler();
        }
        return instance;
    }
    
    public Boolean addOrder(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    public void addDetail(Integer orderId, Integer foodId, Integer qty) {
        // TODO Auto-generated method stub
        
    }

    public Boolean removeOrder(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeDetail(Integer orderId) {
		// TODO Auto-generated method stub
		
	}

	public Model getOne(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}
    
    public ArrayList<Model> viewAllHistory(Integer id) {
        return null;
    }

    public ArrayList<Model> viewById(Integer orderId) {
        return null;
    }

    public ArrayList<Model> viewOrderList(Integer orderId) {
        return null;
    }

    public ArrayList<Model> viewDetailById(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
    }

    public Boolean updateStatus(Integer orderId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean takeOrder(Integer orderId, Integer driverId) {
		// TODO Auto-generated method stub
		return null;
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
