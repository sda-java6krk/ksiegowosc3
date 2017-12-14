package pl.sdacademy.models;

import pl.sdacademy.exceptions.AccountantNotFoundException;

import java.io.IOException;
import java.util.ArrayList;

public class AccountantRegistry {
    private static AccountantRegistry instance = null;
    public static AccountantRegistry getInstance() {
        if(instance == null) {
            instance = new AccountantRegistry();
        }
        return instance;
    }
    private ArrayList<Accountant> accountants;

    public AccountantRegistry() {
        this.accountants = new ArrayList<>();
    }

    public Accountant findAccountant(String login, String password) throws AccountantNotFoundException{
        for (Accountant accountant : accountants){
            if (accountant.getLogin().equals(login) && accountant.getPassword().equals(password)){
                return accountant;
            }
        }
        throw new AccountantNotFoundException();
    }

    public boolean checkIfAcountantLoginAlreadyExist(String login){
        for (Accountant accountant : accountants){
            if (accountant.getLogin().equals(login)){
                return true;
            }
            return false;
        }
    }

    public void addAccountant(String login, String password) throws IOException {
        if (checkIfAcountantLoginAlreadyExist(login)){
            throw new IllegalArgumentException("Księgowy z tym loginem już istnieje!");
        }
        this.accountants.add(new Accountant(login, password));
    }

    public void removeAccountant(){

    }



}
