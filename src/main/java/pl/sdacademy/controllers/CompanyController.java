package pl.sdacademy.controllers;

import pl.sdacademy.exceptions.AccountantAlreadyAssignedException;
import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.exceptions.CompanyNotFoundException;
import pl.sdacademy.exceptions.NipAlreadyTakenException;
import pl.sdacademy.models.Accountant;
import pl.sdacademy.models.Company;
import pl.sdacademy.models.CompanyRegistry;
import pl.sdacademy.views.CompanyView;
import pl.sdacademy.views.AccountantView;

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


    public static void listCompaniesAssignedToAccountant(Accountant accountant) {
        AccountantView.printCompaniesAssignedToAccountant(accountant);
    }

    public static void removeCompanyFromDatabase(String nipNumber) throws IOException, CompanyNotFoundException {
        CompanyRegistry.getInstance().deleteCompany(nipNumber);
    }

    public static void changeCompanyName(String companyNip, String newName) throws IOException, CompanyNotFoundException {
        CompanyRegistry.getInstance().changeCompanyName(companyNip, newName);
    }

    public static void changeCompanyNip(String companyNip, String newNip) throws IOException, CompanyNotFoundException, NipAlreadyTakenException {
        CompanyRegistry.getInstance().changeCompanyNip(companyNip, newNip);
    }

    public static void assignAccountantToCompany(String companyNip, String accountantLogin) throws CompanyNotFoundException, AccountantAlreadyAssignedException, AccountantNotFoundException, IOException {
        CompanyRegistry.getInstance().assignAccountantToCompany(companyNip, accountantLogin);
    }
}
