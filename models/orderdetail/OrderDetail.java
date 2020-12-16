package models.orderdetail;

import java.util.ArrayList;
import java.util.Vector;

import core.Model;
import models.food.Food;

public class OrderDetail extends Model implements OrderDetailModel {

	private Food food;
	private Integer qty;

	public Food getFood() {
		return this.food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public Integer getQty() {
		return this.qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public void addDetail(Integer orderId, Integer foodId, Integer qty) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean removeOrder(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Model> viewDetailById(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}
}
