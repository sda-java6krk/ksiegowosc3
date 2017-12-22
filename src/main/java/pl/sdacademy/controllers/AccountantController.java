package pl.sdacademy.controllers;


import pl.sdacademy.exceptions.AccountantAlreadyExistsException;
import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.models.AccountantRegistry;
import pl.sdacademy.views.AccountantView;

import java.io.IOException;

/**
 * Created by marcin on 13.12.2017.
 */
public class AccountantController {
    public static void addAccountant(String login, String password) throws IOException, AccountantAlreadyExistsException {

        if (!AccountantRegistry.getInstance().validateLogin(login)) {
            System.out.println("Nie można utworzyć księgowego - niepoprawny login!");
            return;
        }
        if (!AccountantRegistry.getInstance().validatePassword(password)) {
            System.out.println("Nie można utworzyć księgowego niepoprawne hasło!");
        }


        AccountantRegistry.getInstance().addAccountant(login, password);
    }

    public static void removeAccountant(String login) throws IOException, AccountantNotFoundException {
        AccountantRegistry.getInstance().removeAccountant(login);
    }

    public static void listAccountant() throws IOException {
        AccountantView.printAccountants(AccountantRegistry.getInstance().getAccountants());
    }

}
