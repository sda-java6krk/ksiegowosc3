package pl.sdacademy.models;

import pl.sdacademy.exceptions.AdminNotFoundException;
import pl.sdacademy.views.CompanyView;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by marcin on 13.12.2017.
 */
public class AdminRegistry {
    private static AdminRegistry instance = null;

    public static AdminRegistry getInstance() {
        if(instance == null) {
            instance = new AdminRegistry();
        }
        return instance;
    }


    private ArrayList<Admin> admins;

    public AdminRegistry() {
        this.admins = new ArrayList<>();

        this.admins.add(new Admin("adam", "123"));
        this.admins.add(new Admin("ziutek", "456"));
    }


    public Admin findAdmin(String login, String password) throws AdminNotFoundException {
        for(Admin admin : admins) {
            if(admin.getLogin().equals(login) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        throw new AdminNotFoundException();
    }


    public void loadExistingAdminsFromFile() throws IOException {
        File file = new File("src/resources/adminList.txt");
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] credentials = line.split(";");
            addAdmin(credentials[0], credentials[1]);
        }
    }


    public boolean checkIfAdminLoginAlreadyExists(String login) {
        for (Admin admin : admins) {
            if (admin.getLogin().equals(login)) return true;
        }
        return false;
    }

    public void addAdmin(String login, String password) throws IOException {
        if (checkIfAdminLoginAlreadyExists(login)) {
            throw new IllegalArgumentException("Admin z tym loginem już istnieje!");
        }
        this.admins.add(new Admin(login, password));
    }

    public void writeAdminCredentialsToFile(String login, String password) throws IOException {
        try (FileWriter fw = new FileWriter("src/resources/adminList.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(login + ";" + password);
        }
        System.out.println("Pomyślnie dodano admina!");
    }
}
