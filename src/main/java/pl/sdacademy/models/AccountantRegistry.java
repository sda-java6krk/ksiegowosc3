package pl.sdacademy.models;

import pl.sdacademy.exceptions.AccountantNotFoundException;

import java.io.*;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountantRegistry implements CredentialsValidation{
    private static AccountantRegistry instance = null;

    public static AccountantRegistry getInstance() {
        if (instance == null) {
            instance = new AccountantRegistry();
        }
        return instance;
    }

    private ArrayList<Accountant> accountants;

    public AccountantRegistry() {
        this.accountants = new ArrayList<>();

        this.accountants.add(new Accountant("janek", "asd"));
    }

    public Accountant findAccountant(String login, String password) throws AccountantNotFoundException {
        for (Accountant accountant : accountants) {
            if (accountant.getLogin().equals(login) && accountant.getPassword().equals(password)) {
                return accountant;
            }
        }
        throw new AccountantNotFoundException();
    }

    public boolean checkIfAccountantLoginAlreadyExist(String login) {
        for (Accountant accountant : accountants) {
            if (accountant.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public void addAccountant(String login, String password) throws IOException {
        if (checkIfAccountantLoginAlreadyExist(login)) {
            throw new IllegalArgumentException("Księgowy z tym loginem już istnieje!");
        }
        this.accountants.add(new Accountant(login, password));
    }

    public void removeAccountant(String login) throws IOException {
        Accountant accountantToBeRemoved = null;
        for (Accountant accountant : accountants) {
            if (accountant.getLogin().equals(login)) {
                accountantToBeRemoved = accountant;
            }
        }
        if (accountantToBeRemoved != null) {
            this.accountants.remove(accountantToBeRemoved);
            System.out.println("Usunięto Księgowego");
            try (FileWriter fw = new FileWriter("src/resources/accountantListTemp.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                for (Accountant accountant : accountants) {
                    out.println(accountant.getLogin() + ";" + accountant.getPassword());
                }
            }

            File oldFile = new File("src/resources/accountantList.txt");
            boolean oldFileDeletionStatus = Files.deleteIfExists(oldFile.toPath());
            if (!oldFileDeletionStatus) System.out.println("Błąd przy usuwaniu księgowego!");

            File newFile = new File("src/resources/accountantListTemp.txt");
            boolean newFileCreationStatus = newFile.renameTo(new File("src/resources/accountantList.txt"));
            if (!newFileCreationStatus) System.out.println("Błąd przy usuwaniu księgowego!");

        }
    }
    public void writeAccountantCredentialsToFile(String login, String password) throws IOException {
        try (FileWriter fw = new FileWriter("src/resources/AccountantList.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(login + ";" + password);
        }
        System.out.println("Pomyślnie dodano księgowego!");
    }

    public void loadExistingAccountantsFromFile() throws IOException {
        File file = new File("src/resources/accountantList.txt");
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] credentials = line.split(";");
            addAccountant(credentials[0], credentials[1]);
        }
    }

    public ArrayList<Accountant> getAccountants() {
        return accountants;
    }
}
