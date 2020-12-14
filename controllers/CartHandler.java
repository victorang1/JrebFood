package controllers;

import java.util.ArrayList;

import core.Controller;
import core.Model;
import core.View;

public class CartHandler extends Controller {

    private static CartHandler instance;

    public static CartHandler getInstance() {
        if (instance != null) {
            instance = new CartHandler();
        }
        return instance;
    }

    public Boolean addToCart(Integer userId, Integer foodId, Integer qty) {
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean removeFromCart(Integer userId, Integer foodId) {
        // TODO Auto-generated method stub
        return null;
    }

    public void removeAll(Integer userId) {
        // TODO Auto-generated method stub
        
    }

    public Boolean isFoodExists() {
        return null;
    }

    public Boolean updateQty(Integer userId, Integer foodId, Integer qty) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArrayList<Model> viewAll(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }

    public View viewAddCart() {
        return null;
    }
    
    public View viewManageCartForm() {
        return null;
    }
}
