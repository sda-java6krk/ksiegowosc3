package pl.sdacademy.models;

import pl.sdacademy.exceptions.AccountantAlreadyAssignedException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by marcin on 13.12.2017.
 */
public class Company {
    private String name;
    private int yearFound;
    private String nipNumber;
    private Set<Accountant> companyAccountants;

    public Company(String name, int yearFound, String nipNumber) {
        this.name = name;
        this.yearFound = yearFound;
        this.nipNumber = nipNumber;
        this.companyAccountants = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public int getYearFound() {
        return yearFound;
    }

    public String getNipNumber() {
        return nipNumber;
    }

    public Set<Accountant> getCompanyAccountants() {
        return companyAccountants;
    }

    public void addAccountant(Accountant accountant) throws AccountantAlreadyAssignedException {
        if (this.companyAccountants.contains(accountant)) {
            throw new AccountantAlreadyAssignedException();
        }
        this.companyAccountants.add(accountant);
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    public void changeNip(String newNip) {
        this.nipNumber = newNip;
    }
}
