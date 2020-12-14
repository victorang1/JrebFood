import controllers.UserHandler;

public class JrebFood {

    public JrebFood() {
        UserHandler.getInstance().viewRegistrationForm();
    }

    public static void main(String[] args) {
         new JrebFood();
    }
}