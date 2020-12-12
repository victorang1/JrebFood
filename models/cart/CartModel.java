package models.cart;

import java.util.ArrayList;

public interface CartModel {
    
    public Boolean addToCart(Integer userId, Integer foodId, Integer qty);
    public Boolean removeFromCart(Integer userId, Integer foodId);
    public void removeAll(Integer userId);
    public Boolean updateQty(Integer userId, Integer foodId, Integer qty);
    public ArrayList<Cart> viewAll(Integer userId);
}
