package pl.sdacademy.models;

import pl.sdacademy.exceptions.AccountantNotFoundException;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountantRegistry {
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
        }
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
    // finding accountant by login given as String. Returns accountant or throws exception.
    public Accountant findAccountantByLogin(String login) throws AccountantNotFoundException {
        for (Accountant accountant : accountants) {
            if (accountant.getLogin().equals(login)) {
                return accountant;
            }
        }
        throw new AccountantNotFoundException();
    }
}
