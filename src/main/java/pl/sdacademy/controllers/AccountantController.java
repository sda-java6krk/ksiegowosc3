package pl.sdacademy.controllers;

import pl.sdacademy.models.AccountantRegistry;

/**
 * Created by marcin on 13.12.2017.
 */
public class AccountantController {
    public static void addAccountant(String login, String password) {
        AccountantRegistry.getInstance().addAccountant(login, password);
    }
}
