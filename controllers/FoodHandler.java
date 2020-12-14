package controllers;

import java.util.ArrayList;

import core.Controller;
import core.Model;
import core.View;
import models.food.Food;

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

    public View viewMenu() {
        return null;
    }

    public View viewManageFoodForm() {
        return null;
    }
}
