package controllers;

import java.util.Vector;

import core.Controller;
import core.Model;
import core.View;
import models.food.Food;
import views.food.MenuView;

public class FoodHandler extends Controller {

    private static FoodHandler instance;

    public static FoodHandler getInstance() {
        if (instance == null) {
            instance = new FoodHandler();
        }
        return instance;
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

    public Boolean validateInput(String name, Integer price, String description) {
        return null;
    }

    public Boolean checkStatus(Food food) {
        return food.getStatus().equals("true");
    }

    public Model getFood(Integer foodId) {
        Food food = new Food();
        return food.getFood(foodId);
    }

    public Vector<Model> viewAll() {
        return new Food().viewAll();
    }

    public View viewMenu() {
        return new MenuView();
    }

    public View viewManageFoodForm() {
        return null;
    }
}
