package models.user;

import core.Model;

public interface UserModel {

    public Boolean createAccount(String name, String address, String email, String phoneNumber, String password);
    public Model login(String email, String password);
    public Model loginAsEmployee(String email, String password);
    public Model getOne(Integer userId);
    public Boolean checkIfUserExists(String email, String phoneNumber);
}
