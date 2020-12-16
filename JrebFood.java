import controllers.UserHandler;

public class JrebFood {

    public JrebFood() {
        UserHandler.getInstance().viewLoginForm();
    }

    public static void main(String[] args) {
         new JrebFood();
    }
}