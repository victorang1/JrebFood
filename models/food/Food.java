package models.food;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import core.Model;

public class Food extends Model implements FoodModel {

    private Integer foodId;
    private String name;
    private Integer price;
    private String description;
    private String status;

    public Food() {
        this.tableName = "food";
    }

    public Integer getFoodId() {
        return this.foodId;
    }

    public Food setFoodId(Integer foodId) {
        this.foodId = foodId;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Food setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPrice() {
        return this.price;
    }

    public Food setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public Food setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public Food setStatus(String status) {
        this.status = status;
        return this;
    }
    
    @Override
    public Boolean addFood(String name, Integer price, String description) {
        try {
            String rawQuery = String.format("INSERT INTO %s VALUES (default, ?, ?, ?, ?)", tableName);
            PreparedStatement result = execQuery(rawQuery);
            result.setString(1, name);
            result.setInt(2, price);
            result.setString(3, description);
            result.setString(4, "true");
            result.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteFood(Integer foodId) {
        try {
            String rawQuery = String.format("DELETE FROM %s WHERE foodId=?", tableName);
            PreparedStatement result = execQuery(rawQuery);
            result.setInt(1, foodId);
            result.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean changeStatus(Integer foodId) {
        try {
            String rawQuery = String.format("UPDATE %s SET status='false' WHERE foodId=?", tableName);
            PreparedStatement result = execQuery(rawQuery);
			result.setInt(1, foodId);
            result.executeUpdate();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean validateName(String name) {
        try {
            String rawQuery = String.format("SELECT * FROM %s WHERE name=?", tableName);
            PreparedStatement result = execQuery(rawQuery);
            result.setString(1, name);
            return result.executeQuery().isBeforeFirst();
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Model getFood(Integer foodId) {
        try {
            String rawQuery = String.format("SELECT * FROM %s WHERE foodId=?", tableName);
            PreparedStatement result = execQuery(rawQuery);
            result.setInt(1, foodId);
            ResultSet rs = result.executeQuery();
            if (rs.next()) {
                Food food = new Food();
                food.foodId = rs.getInt("foodId");
                food.name = rs.getString("name");
                food.status = rs.getString("status");
                return food;
            }
            throw new Exception();
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public Vector<Model> viewAll() {
        Vector<Model> data = new Vector<>();
        
		try {
            String rawQuery = String.format("SELECT * FROM %s", tableName);
            ResultSet rs = execQuery(rawQuery).executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("foodId");
				String name = rs.getString("name");
				Integer price = rs.getInt("price");
				String desc = rs.getString("description");
				String status = rs.getString("status");
				
				Food food = new Food();
				food.setFoodId(id);
				food.setName(name);
                food.setPrice(price);
                food.setDescription(desc);
                food.setStatus(status);
				
				data.add(food);
			}
			return data;
		} catch (Exception e) {
            e.printStackTrace();
		}
        return data;
    }
}
