package controllers;

import core.Controller;
import core.Model;
import core.View;
import models.user.User;
import util.StringUtil;
import views.user.LoginFormView;
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
        User user = new User();
        user.setName(name)
            .setAddress(address)
            .setEmail(email)
            .setPhoneNumber(phoneNumber)
            .setPassword(password);
        return user.createAccount(name, address, email, phoneNumber, password);
    }

    public Boolean login(String name, String password) {
        User user = new User();
        user.setName(name)
            .setPassword(password);
        return user.login(name, password);
    }

    public Model getOne(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean validateUnique(String email, String phoneNumber) {
        User user = new User();
        user.setEmail(email)
            .setPhoneNumber(phoneNumber);
        return user.checkIfUserExists(email, phoneNumber);
    }

    public Boolean validateFields(String email, String phoneNumber) {
        return StringUtil.isNullOrEmpty(email) || StringUtil.isNullOrEmpty(phoneNumber);
    }

    public Boolean validateLoginFields(String email, String password) {
        return StringUtil.isNullOrEmpty(email) || StringUtil.isNullOrEmpty(password);
    }

    public View viewUserInformation() {
        return null;
    }

    public View viewRegistrationForm() {
        return new RegistrationFormView();
    }

    public View viewLoginForm() {
        return new LoginFormView();
    }
}
