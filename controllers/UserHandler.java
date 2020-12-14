package controllers;

import core.Controller;
import core.Model;
import core.View;
import util.StringUtil;
import views.user.RegistrationFormView;

public class UserHandler extends Controller {

    private static UserHandler instance;

    public static UserHandler getInstance() {
        if (instance == null) {
            instance = new UserHandler();
        }
        return instance;
    }
    
    public Boolean createAccount(String name, String address, String email, String phoneNumber,
            String password) {
        // TODO Auto-generated method stub
        return null;
    }

    public Model getOne(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean validateUnique(String email, String phoneNumber) {
        return false;
    }

    public Boolean validateFields(String email, String phoneNumber) {
        return StringUtil.isNullOrEmpty(email) || StringUtil.isNullOrEmpty(phoneNumber);
    }

    public View viewUserInformation() {
        return null;
    }

    public View viewRegistrationForm() {
        return new RegistrationFormView();
    }
}
