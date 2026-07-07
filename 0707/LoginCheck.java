public class LoginCheck {
    public static void main(String[] args) {
       String username = "admin";
       String password = "1234";

       String inputUsername = "admin";
       String inputPassword = "1234";

        boolean isValidLogin = inputUsername.equals("admin") && inputPassword.equals("1234");

        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Valid Login: " + isValidLogin);
    }
}