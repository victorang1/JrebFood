package models.user;

public interface UserModel {
    
    public Boolean createAccount(Integer userId, String name, String address, String email, String phoneNumber, String password);
    public User getOne(Integer userId);
}
