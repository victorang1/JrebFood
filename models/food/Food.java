package models.food;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import core.Model;

public class Food extends Model {

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
    
    public Boolean addFood(String name, Integer price, String description) {
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean deleteFood(Integer foodId) {
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean changeStatus(Integer foodId) {
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean validateName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

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
