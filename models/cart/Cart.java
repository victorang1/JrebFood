package models.cart;

import java.util.ArrayList;

import core.Model;
import models.food.Food;
import models.user.User;

public class Cart extends Model {

    private User user;
    private Food food;
    private Integer qty;

    public User getUser() {
        return this.user;
    }

    public Cart setUser(User user) {
        this.user = user;
        return this;
    }

    public Food getFood() {
        return this.food;
    }

    public Cart setFood(Food food) {
        this.food = food;
        return this;
    }

    public Integer getQty() {
        return this.qty;
    }

    public Cart setQty(Integer qty) {
        this.qty = qty;
        return this;
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

    public Boolean updateQty(Integer userId, Integer foodId, Integer qty) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArrayList<Model> viewAll(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }
}
