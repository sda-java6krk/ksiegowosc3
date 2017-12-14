package pl.sdacademy.controllers;

import pl.sdacademy.models.AccountantRegistry;

import java.io.IOException;

/**
 * Created by marcin on 13.12.2017.
 */
public class AccountantController {
    public static void addAccountant(String login, String password) throws IOException {
        AccountantRegistry.getInstance().addAccountant(login, password);
    }
}
