package pl.sdacademy.models;

import pl.sdacademy.exceptions.AdminNotFoundException;
import pl.sdacademy.views.CompanyView;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by marcin on 13.12.2017.
 */
public class AdminRegistry implements CredentialsValidation {
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
        input.close();
    }


    public void addAdmin(String login, String password) throws IOException {
        for (Admin admin : admins) {
            if (admin.getLogin().equals(login)) {
                System.out.println("Admin z takim loginem (" + login + ") już istnieje!");
                return;
            }
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

    public void removeAdmin(String login) throws IOException {
        Admin adminToBeRemoved = null;
        for (Admin admin : admins) {
            if (admin.getLogin().equals(login)) {
                adminToBeRemoved = admin;
            }
        }
        if (adminToBeRemoved != null) {
            this.admins.remove(adminToBeRemoved);
            System.out.println("Usunięto admina");
            try (FileWriter fw = new FileWriter("src/resources/adminListTemp.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                 for (Admin a : admins) {
                     out.println(a.getLogin() + ";" + a.getPassword());
                 }
            }
            File oldFile = new File("src/resources/adminList.txt");
            boolean oldFileDeletionStatus = Files.deleteIfExists(oldFile.toPath());
            if (!oldFileDeletionStatus) System.out.println("Błąd przy usuwaniu admina!");

            File newFile = new File("src/resources/adminListTemp.txt");
            boolean newFileCreationStatus = newFile.renameTo(new File("src/resources/adminList.txt"));
            if (!newFileCreationStatus) System.out.println("Błąd przy usuwaniu admina!");
        }
    }

}
