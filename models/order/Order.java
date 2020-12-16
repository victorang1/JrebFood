package models.order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import constants.OrderStatus;
import core.Model;
import models.user.User;

public class Order extends Model implements OrderModel {

	private Integer orderId;
    private Date date;
    private String address;
    private Integer userId;
    private Integer driverId;
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

	public Integer getUserId() {
		return this.userId;
	}

	public Order setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}

	public Integer getDriverId() {
		return this.driverId;
	}

	public Order setDriverId(Integer driverId) {
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
		try {
            String rawQuery = String.format("UPDATE %s SET status=?, driverId=? WHERE orderId=?", tableName);
            PreparedStatement result = execQuery(rawQuery);
			result.setString(1, OrderStatus.ACCEPTED);
			result.setInt(2, driverId);
			result.setInt(3, orderId);
            result.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
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
		Vector<Model> data = new Vector<>();
		try {
            String rawQuery = String.format("SELECT * FROM %s", tableName);
            PreparedStatement result = execQuery(rawQuery);
            ResultSet rs = result.executeQuery();
			while(rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("orderId"));
				order.setDate(rs.getDate("date"));
                order.setAddress(rs.getString("address"));
				order.setDriverId(rs.getInt("driverId"));
				order.setStatus(rs.getString("status"));
				order.setUserId(rs.getInt("userId"));
				
				data.add(order);
			}
			return data;
		} catch (Exception e) {
            e.printStackTrace();
		}
        return data;
	}

	@Override
	public Vector<Model> viewDetailById(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}
}
