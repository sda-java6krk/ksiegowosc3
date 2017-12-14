package pl.sdacademy.models;

import java.util.ArrayList;

public class AccountantRegistry {
    private static AccountantRegistry instance = null;
    public static AccountantRegistry getInstance() {
        if(instance == null) {
            instance = new AccountantRegistry();
        }
        return instance;
    }
    private ArrayList<Accountant> accountants;

    public AccountantRegistry() {
        this.accountants = new ArrayList<>();
    }

    public void addAccountant(String login, String password) {
        this.accountants.add(new Accountant(login, password));
    }


}
