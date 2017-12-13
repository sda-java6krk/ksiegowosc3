package pl.sdacademy.models;

/**
 * Created by marcin on 13.12.2017.
 */
public class Admin {
    private String login;
    private String password;

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
