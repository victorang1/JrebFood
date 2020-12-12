package models.cart;

import java.util.ArrayList;

import models.Model;

public class CartImpl extends Model implements CartModel{
    
    @Override
    public Boolean addToCart(Integer userId, Integer foodId, Integer qty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean removeFromCart(Integer userId, Integer foodId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeAll(Integer userId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Boolean updateQty(Integer userId, Integer foodId, Integer qty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Cart> viewAll(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }
}
