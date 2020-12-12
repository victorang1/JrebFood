package models.food;

import java.util.ArrayList;

public interface FoodModel {
    
    public Boolean addFood(String name, Integer price, String description);
    public Boolean deleteFood(Integer foodId);
    public Boolean changeStatus(Integer foodId);
    public Boolean validateName(String name);
    public Food getFood(Integer foodId);
    public ArrayList<Food> viewAll();
}
