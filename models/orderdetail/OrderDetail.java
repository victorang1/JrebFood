package models.orderdetail;

import java.util.ArrayList;

import core.Model;
import models.food.Food;

public class OrderDetail extends Model {

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

	public Boolean removeOrder(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Model> viewDetailById(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}
}
