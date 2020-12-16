package models.cart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import core.Model;
import models.food.Food;
import models.user.User;

public class Cart extends Model implements CartModel {

    private Integer cartId;
    private User user;
    private Food food;
    private Integer qty;

    public Cart() {
        this.tableName = "cart";
    }

    public Integer getCartId() {
        return cartId;
    }
    
    public Cart setCartId(Integer cartId) {
        this.cartId = cartId;
        return this;
    }

    public User getUser() {
        return this.user;
    }

    public Cart setUser(User user) {
        this.user = user;
        return this;
    }

    public Food getFood() {
        return this.food;
    }

    public Cart setFood(Food food) {
        this.food = food;
        return this;
    }

    public Integer getQty() {
        return this.qty;
    }

    public Cart setQty(Integer qty) {
        this.qty = qty;
        return this;
    }
    
    @Override
    public Boolean addToCart(Integer userId, Integer foodId, Integer qty) {
        try {
            String rawQuery = String.format("INSERT INTO %s VALUES (default, ?, ?, ?)", tableName);
            PreparedStatement result = execQuery(rawQuery);
            result.setInt(1, userId);
            result.setInt(2, foodId);
            result.setInt(3, qty);
            result.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean removeFromCart(Integer userId, Integer foodId) {
        try {
            String rawQuery = String.format("DELETE FROM %s WHERE userId=? AND foodId=?", tableName);
            PreparedStatement result = execQuery(rawQuery);
            result.setInt(1, userId);
            result.setInt(2, foodId);
            result.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll(Integer userId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Boolean updateQty(Integer userId, Integer foodId, Integer qty) {
        try {
            String rawQuery = String.format("UPDATE %s SET qty=qty+? WHERE userId=? AND foodId=?", tableName);
            PreparedStatement result = execQuery(rawQuery);
            result.setInt(1, qty);
            result.setInt(2, userId);
            result.setInt(3, foodId);
            result.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Vector<Model> viewAll(Integer userId) {
        Vector<Model> data = new Vector<>();
		try {
            String rawQuery = String.format("SELECT * FROM %s a JOIN %s b ON a.foodId = b.foodId WHERE userId=?", tableName, "food");
            PreparedStatement result = execQuery(rawQuery);
            result.setInt(1, userId);
            ResultSet rs = result.executeQuery();
			while(rs.next()) {
				Integer cartId = rs.getInt("cartId");
				Integer foodId = rs.getInt("foodId");
				String name = rs.getString("name");
				Integer price = rs.getInt("price");
                String desc = rs.getString("description");
                Integer qty = rs.getInt("qty");
                
				Food food = new Food();
				food.setFoodId(foodId);
				food.setName(name);
                food.setPrice(price);
                food.setDescription(desc);

                Cart cart = new Cart();
                cart.setCartId(cartId);
                cart.setFood(food);
                cart.setQty(qty);
				
				data.add(cart);
			}
			return data;
		} catch (Exception e) {
            e.printStackTrace();
		}
        return data;
    }
}
