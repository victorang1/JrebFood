package controllers;

import java.util.Vector;

import core.Controller;
import core.Model;
import core.View;
import models.food.Food;
import models.food.FoodModel;
import util.StringUtil;
import views.food.ManageFoodFormView;
import views.food.MenuView;

public class FoodHandler extends Controller {

    private static FoodHandler instance;
    private FoodModel model;

    private FoodHandler() {
        model = new Food();
    }

    public static FoodHandler getInstance() {
        if (instance == null) {
            instance = new FoodHandler();
        }
        return instance;
    }
    
    public Boolean addFood(String name, Integer price, String description) {
        if (validateInput(name, price, description)) {
            return model.addFood(name, price, description);
        }
        return false;
    }

    public Boolean deleteFood(Integer foodId) {
        return model.deleteFood(foodId);
    }

    public Boolean changeStatus(Integer foodId) {
        return model.changeStatus(foodId);
    }

    public Boolean validateInput(String name, Integer price, String description) {
        if (StringUtil.isNullOrEmpty(name) || StringUtil.isNullOrEmpty(price.toString()) || StringUtil.isNullOrEmpty(description)) {
            setErrorMessage("All field must be input!");
            return false;
        }
        if (model.validateName(name)) {
            setErrorMessage("Food name already exists");
            return false;
        }
        return true;
    }

    public Boolean checkStatus(Food food) {
        return food.getStatus().equals("true");
    }

    public Model getFood(Integer foodId) {
        return model.getFood(foodId);
    }

    public Vector<Model> viewAll() {
        return model.viewAll();
    }

    public View viewMenu() {
        return new MenuView();
    }

    public View viewManageFoodForm() {
        return new ManageFoodFormView();
    }
}
