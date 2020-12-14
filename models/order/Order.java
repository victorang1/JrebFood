package models.order;

import java.util.ArrayList;
import java.util.Date;

import core.Model;
import models.user.User;

public class Order extends Model {

	private Integer orderId;
    private Date date;
    private String address;
    private String userId;
    private String driverId;
	private String status;

	public Integer getOrderId() {
		return this.orderId;
	}

	public Order setOrderId(Integer orderId) {
		this.orderId = orderId;
		return this;
	}

	public Date getDate() {
		return this.date;
	}

	public Order setDate(Date date) {
		this.date = date;
		return this;
	}

	public String getAddress() {
		return this.address;
	}

	public Order setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getUserId() {
		return this.userId;
	}

	public Order setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getDriverId() {
		return this.driverId;
	}

	public Order setDriverId(String driverId) {
		this.driverId = driverId;
		return this;
	}

	public String getStatus() {
		return this.status;
	}

	public Order setStatus(String status) {
		this.status = status;
		return this;
	}
    
    public Boolean addOrder(User user, Date date) {
        // TODO Auto-generated method stub
        return null;
    }

    public void addDetail(Integer orderId, Integer foodId, Integer qty) {
        // TODO Auto-generated method stub
        
    }

	public Model getOne(Integer orderId) {
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

	public Boolean removeOrder(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeDetail(Integer orderId) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Model> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Model> viewDetailById(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}
}
