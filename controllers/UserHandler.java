package controllers;

import core.Controller;
import core.Model;
import core.View;
import models.Session;
import models.user.User;
import models.user.UserModel;
import util.StringUtil;
import views.HomeView;
import views.LandingView;
import views.user.LoginFormView;
import views.user.RegistrationFormView;

public class UserHandler extends Controller {

    private static UserHandler instance;
    private UserModel model;

    public static final Integer TYPE_USER = 0;
    public static final Integer TYPE_EMPLOYEE = 1;

    private UserHandler() {
        model = new User();
    }

    public static UserHandler getInstance() {
        if (instance == null) {
            instance = new UserHandler();
        }
        return instance;
    }
    
    public Boolean createAccount(String name, String address, String email, String phoneNumber,
            String password) {
        return model.createAccount(name, address, email, phoneNumber, password);
    }

    public Boolean login(String email, String password) {
        User result = (User) model.login(email, password);
        if (result != null) {
            Session.getInstance().createLoginSession(result);
        }
        return result != null;
    }

    public Model getOne(Integer userId) {
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean validateUnique(String email, String phoneNumber) {
        return model.checkIfUserExists(email, phoneNumber);
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
        return new LoginFormView(TYPE_USER);
    }
    
    public View viewLoginEmployeeFrom() {
        return new LoginFormView(TYPE_EMPLOYEE);
    }

    public View viewLandingView() {
        return new LandingView();
    }

    public View homeView() {
        return new HomeView();
    }
}
