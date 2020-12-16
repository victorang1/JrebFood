package models.food;

import java.util.Vector;

import core.Model;

public interface FoodModel {

    public Boolean addFood(String name, Integer price, String description);
    public Boolean deleteFood(Integer foodId);
    public Boolean changeStatus(Integer foodId);
    public Boolean validateName(String name);
    public Model getFood(Integer foodId);
    public Vector<Model> viewAll();
}
