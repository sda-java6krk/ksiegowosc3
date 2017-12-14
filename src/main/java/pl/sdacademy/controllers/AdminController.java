package pl.sdacademy.controllers;

import pl.sdacademy.models.AdminRegistry;

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
        AdminRegistry.getInstance().addAdmin(login, password);
        AdminRegistry.getInstance().writeAdminCredentialsToFile(login, password);
    }

    public static void removeAdmin(String login) throws IOException {
        AdminRegistry.getInstance().removeAdmin(login);
    }
}
