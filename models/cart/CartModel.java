package models.cart;

import java.util.Vector;

import core.Model;

public interface CartModel {
    
    public Boolean addToCart(Integer userId, Integer foodId, Integer qty);
    public Boolean removeFromCart(Integer userId, Integer foodId);
    public void removeAll(Integer userId);
    public Boolean updateQty(Integer userId, Integer foodId, Integer qty);
    public Vector<Model> viewAll(Integer userId);
}
