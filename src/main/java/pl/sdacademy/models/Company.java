package pl.sdacademy.models;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by marcin on 13.12.2017.
 */
public class Company {
    private String name;
    private int yearFound;
    private int nipNumber;
    private Set<Accountant> companyAccountants;

    public Company(String name, int yearFound, int nipNumber) {
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

    public int getNipNumber() {
        return nipNumber;
    }

    public Set<Accountant> getCompanyAccountants() {
        return companyAccountants;
    }
}
