package models.order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import core.Model;
import models.user.User;

public class Order extends Model implements OrderModel {

	private Integer orderId;
    private Date date;
    private String address;
    private String userId;
    private String driverId;
	private String status;

	public Order() {
		this.tableName = "`order`";
	}

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
	
	@Override
    public Integer addOrder(User user, Date date) {
        try {
            String rawQuery = String.format("INSERT INTO %s VALUES (default, ?, ?, ?, ?, ?)", tableName);
            PreparedStatement result = execQueryWithKeys(rawQuery);
            result.setTimestamp(1, new java.sql.Timestamp(date.getTime()));
            result.setString(2, user.getAddress());
            result.setInt(3, user.getUserId());
            result.setString(4, null);
            result.setString(5, "Not Accepted");
			result.executeUpdate();
			ResultSet rs = result.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
			throw new Exception();
        } catch(Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

	@Override
    public void addDetail(Integer orderId, Integer foodId, Integer qty) {
        // TODO Auto-generated method stub
        
    }

	@Override
	public Model getOne(Integer orderId) {
		try {
            String rawQuery = String.format("SELECT * FROM %s WHERE orderId=?", tableName);
            PreparedStatement result = execQuery(rawQuery);
            result.setInt(1, orderId);
            ResultSet rs = result.executeQuery();
            if (rs.next()) {
                Order order = new Order();
				order.orderId = rs.getInt("orderId");
				order.status = rs.getString("status");
                return order;
            }
            throw new Exception();
        } catch(Exception e) {
            return null;
        }
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
		try {
            String rawQuery = String.format("DELETE FROM %s WHERE orderId=?", tableName);
            PreparedStatement result = execQuery(rawQuery);
            result.setInt(1, orderId);
			result.executeUpdate();
			return true;
        } catch(Exception e) {
			e.printStackTrace();
			return false;
        }
	}

	@Override
	public void removeDetail(Integer orderId) {
		try {
            String rawQuery = String.format("DELETE FROM %s WHERE orderId=?", "orderDetail");
            PreparedStatement result = execQuery(rawQuery);
            result.setInt(1, orderId);
            result.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Model> viewDetailById(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}
}
