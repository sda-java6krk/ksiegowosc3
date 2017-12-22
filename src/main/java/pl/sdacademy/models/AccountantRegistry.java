package pl.sdacademy.models;

import pl.sdacademy.exceptions.AccountantAlreadyExistsException;
import pl.sdacademy.exceptions.AccountantNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountantRegistry implements CredentialsValidation, Serializable {
    private static AccountantRegistry instance = null;
    private static final String ACCOUNTANT_LIST_FILEPATH = "src/resources/accountantList.txt";

    public static AccountantRegistry getInstance() {
        if (instance == null) {
            instance = new AccountantRegistry();
        }
        return instance;
    }

    private List<Accountant> accountants;

    public AccountantRegistry() {
        this.accountants = new ArrayList<>();

//        this.accountants.add(new Accountant("janek", "asd"));
//        this.accountants.add(new Accountant("adam", "123"));
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

    public void addAccountant(String login, String password) throws IOException, AccountantAlreadyExistsException {
        if (checkIfAccountantLoginAlreadyExist(login)) {
            throw new AccountantAlreadyExistsException();
        }
        this.accountants.add(new Accountant(login, password));
        writeDataToFile();
    }

    public void removeAccountant(String login) throws IOException, AccountantNotFoundException {
        Accountant accountantToBeRemoved = null;
        for (Accountant accountant : accountants) {
            if (accountant.getLogin().equals(login)) {
                accountantToBeRemoved = accountant;
            }
        }
        if (accountantToBeRemoved != null) {
            this.accountants.remove(accountantToBeRemoved);
            writeDataToFile();
            CompanyRegistry.getInstance().unassignAccountantAfterDeletion(accountantToBeRemoved);
        } else {
            throw new AccountantNotFoundException();
        }
    }


    public void writeDataToFile() throws IOException {
        try (FileOutputStream fs = new FileOutputStream(ACCOUNTANT_LIST_FILEPATH, false);
             ObjectOutputStream os = new ObjectOutputStream(fs)) {
            os.writeObject(this.accountants);
        }
    }

    public void loadExistingAccountantsFromFile() throws IOException {
        List<Accountant> accountants = null;

        try (
                FileInputStream fis = new FileInputStream(ACCOUNTANT_LIST_FILEPATH);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            accountants = (List<Accountant>) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (accountants != null) {
            this.accountants = accountants;
        }
    }

    public List<Accountant> getAccountants() {
        return accountants;
    }

    // finding accountant by login given as String. Returns accountant or throws exception.
    public static Accountant findAccountantByLogin(String login) throws AccountantNotFoundException {
        List<Accountant> accountantsAssigned = AccountantRegistry.getInstance().getAccountants();

        for (Accountant accountant : accountantsAssigned) {
            if (accountant.getLogin().equals(login)) {
                return accountant;
            }
        }
        throw new AccountantNotFoundException();
    }
}
