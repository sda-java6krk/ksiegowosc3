package pl.sdacademy.controllers;

import pl.sdacademy.models.AdminRegistry;
import pl.sdacademy.models.CredentialsValidation;
import pl.sdacademy.views.AdminView;

import java.io.*;
import java.util.Scanner;

/**
 * Created by marcin on 13.12.2017.
 */
public class AdminController {
    public static void loadExistingAdminsFromFile() throws IOException {
        AdminRegistry.getInstance().loadExistingAdminsFromFile();
    }

    public static void addAdmin(String login, String password) throws IOException {
        if (!AdminRegistry.getInstance().validateLogin(login)) {
            System.out.println("Nie można utworzyć admina - niepoprawny login!");
            return;
        }
        if (!AdminRegistry.getInstance().validatePassword(password)) {
            System.out.println("Nie można utworzyć admina - niepoprawne hasło!");
            return;
        }

        AdminRegistry.getInstance().addAdmin(login, password);
        AdminRegistry.getInstance().writeAdminCredentialsToFile(login, password);
    }

    public static void removeAdmin(String login) throws IOException {
        AdminRegistry.getInstance().removeAdmin(login);
    }

    public static void listAdmins() throws IOException {
        AdminView.printAdmin(AdminRegistry.getInstance().getAdmins());
    }

}
