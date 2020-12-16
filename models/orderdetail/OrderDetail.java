package models.orderdetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import core.Model;
import models.food.Food;

public class OrderDetail extends Model implements OrderDetailModel {

	private Food food;
	private Integer qty;

	public OrderDetail() {
		this.tableName = "orderdetail";
	}

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
		try {
            String rawQuery = String.format("INSERT INTO %s VALUES (?, ?, ?)", tableName);
            PreparedStatement result = execQuery(rawQuery);
            result.setInt(1, orderId);
            result.setInt(2, foodId);
            result.setInt(3, qty);
			result.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public Boolean removeOrder(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Model> viewDetailById(Integer orderId) {
		Vector<Model> data = new Vector<>();
		try {
            String rawQuery = String.format("SELECT * FROM %s a JOIN %s b ON a.foodId = b.foodId WHERE orderId=?", tableName, "food");
            PreparedStatement result = execQuery(rawQuery);
            result.setInt(1, orderId);
            ResultSet rs = result.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				Integer price = rs.getInt("price");
                String desc = rs.getString("description");
                Integer qty = rs.getInt("qty");
                
				Food food = new Food();
				food.setName(name);
                food.setPrice(price);
                food.setDescription(desc);

				OrderDetail detail = new OrderDetail();
                detail.setFood(food);
                detail.setQty(qty);
				
				data.add(detail);
			}
			return data;
		} catch (Exception e) {
            e.printStackTrace();
		}
        return data;
	}
}
