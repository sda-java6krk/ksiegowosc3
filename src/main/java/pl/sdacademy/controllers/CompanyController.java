package pl.sdacademy.controllers;

import pl.sdacademy.exceptions.AccountantAlreadyAssignedException;
import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.exceptions.NipAlreadyTakenException;
import pl.sdacademy.models.Accountant;
import pl.sdacademy.models.AccountantRegistry;
import pl.sdacademy.models.Company;
import pl.sdacademy.models.CompanyRegistry;
import pl.sdacademy.views.CompanyView;

import java.io.IOException;

/**
 * Created by marcin on 13.12.2017.
 */
public class CompanyController {
    public static void createCompany(String name, int yearFound, String nipNumber) throws IOException, NipAlreadyTakenException {
        CompanyRegistry.getInstance().add(new Company(name, yearFound, nipNumber));
    }


    public static void listCompanies() {
        CompanyView.printCompanies(CompanyRegistry.getInstance().getCompanies());
    }

    public static void assignAccountantToCompany(Company company, Accountant accountant) throws IOException, AccountantAlreadyAssignedException {
        company.addAccountant(accountant);
    }

    public static void loadCompaniesFromFile(AccountantRegistry accountantRegistry) throws IOException, AccountantNotFoundException, NipAlreadyTakenException {
        CompanyRegistry.getInstance().loadCompanyFromFile(accountantRegistry);
    }

    public static void removeCompanyFromDatabase(CompanyRegistry companyRegistry, String nipNumber) throws IOException {
        companyRegistry.deleteCompany(nipNumber);
    }

    public static void changeCompanyName(CompanyRegistry companyRegistry, Company company, String newName) throws IOException {
        companyRegistry.changeCompanyName(company,newName);
    }

    public static void changeCompanyNip(CompanyRegistry companyRegistry, Company company, String newNip) throws IOException {
        companyRegistry.changeCompanyNip(company,newNip);
    }
}
