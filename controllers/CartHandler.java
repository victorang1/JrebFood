package controllers;

import core.Controller;
import core.View;

public class CartHandler extends Controller {

    private static CartHandler instance;

    public static CartHandler getInstance() {
        if (instance != null) {
            instance = new CartHandler();
        }
        return instance;
    }
    
    @Override
    public View getView() {
        return null;
    }
}
