package controllers;

import java.util.Vector;

import core.Controller;
import core.Model;
import core.View;
import models.Session;
import models.cart.Cart;
import models.cart.CartModel;
import models.food.Food;
import views.cart.AddCartView;
import views.cart.ManageCartFormView;

public class CartHandler extends Controller {

    private static CartHandler instance;
    private CartModel model;

    private CartHandler() {
        model = new Cart();
    }

    public static CartHandler getInstance() {
        if (instance == null) {
            instance = new CartHandler();
        }
        return instance;
    }

    public Boolean addToCart(Integer userId, Integer foodId, Integer qty) {
        Food food = (Food) FoodHandler.getInstance().getFood(foodId);
        if (food == null) {
            setErrorMessage("Food not found");
            return false;
        }
        if (!FoodHandler.getInstance().checkStatus(food)) {
            setErrorMessage("Food is not available");
            return false;
        }
        if (isFoodExists(food.getName()))
            return updateQty(userId, foodId, qty);
        else
            return model.addToCart(userId, foodId, qty);
    }

    public Boolean removeFromCart(Integer userId, Integer foodId) {
        return model.removeFromCart(userId, foodId);
    }

    public void removeAll(Integer userId) {
        model.removeAll(userId);
    }

    public Boolean isFoodExists(String name) {
        Vector<Model> carts = viewAll(Session.getInstance().getUserId());
        for (Model model: carts) {
            Cart cart = (Cart) model;
            if (cart.getFood().getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Boolean updateQty(Integer userId, Integer foodId, Integer qty) {
        return model.updateQty(userId, foodId, qty);
    }

    public Vector<Model> viewAll(Integer userId) {
        return model.viewAll(userId);
    }

    public View viewAddCart() {
        return new AddCartView();
    }
    
    public View viewManageCartForm() {
        return new ManageCartFormView();
    }
}
