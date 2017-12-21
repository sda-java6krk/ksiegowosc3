package pl.sdacademy.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcin on 13.12.2017.
 */
public class Accountant implements Serializable {
    private String login;
    private String password;
    private List<Company> companiesAssigned;

    public Accountant(String login, String password) {
        this.login = login;
        this.password = password;
        this.companiesAssigned = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void assignToCompany(Company company) {
        this.companiesAssigned.add(company);
    }

    public List<Company> getCompaniesAssigned() {
        return companiesAssigned;
    }
}
