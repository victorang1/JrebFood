package models.food;

import java.util.ArrayList;

import models.Model;

public class Food extends Model {

    private Integer foodId;
    private String name;
    private Integer price;
    private String description;
    private String status;

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
        // TODO Auto-generated method stub
        return null;
    }

    public ArrayList<Model> viewAll() {
        // TODO Auto-generated method stub
        return null;
    }
}
