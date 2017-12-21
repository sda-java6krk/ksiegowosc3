package pl.sdacademy.models;

import pl.sdacademy.exceptions.AccountantAlreadyAssignedException;
import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.exceptions.CompanyNotFoundException;
import pl.sdacademy.exceptions.NipAlreadyTakenException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by marcin on 13.12.2017.
 */
public class CompanyRegistry {
    private static CompanyRegistry instance = null;
    private static final String COMPANY_LIST_FILEPATH = "src/resources/companyList.txt";

    public static CompanyRegistry getInstance() {
        if (instance == null) {
            instance = new CompanyRegistry();
        }
        return instance;
    }


    private ArrayList<Company> companies;

    public CompanyRegistry() {
        this.companies = new ArrayList<>();
//
//        this.companies.add(new Company("Ziutex sp. z o.o.", 1990, "123123"));
//        this.companies.add(new Company("Krakbud s.j.", 1995, "345345"));
    }

    // listing all companies
    public List<Company> getCompanies() {
        return this.companies;
    }

    // adding company loaded from file to the database
    public void addLoadedData(Company company) {
        this.companies.add(company);
    }

    // adding a specific company to the database. Such method assign no accountants to the company
    public void add(Company company) throws IOException, NipAlreadyTakenException {
        if (getCompanyByNipNumber(company.getNipNumber()) == null) {
            this.companies.add(company);
            writeCompanyToFile(company);
        } else {
            throw new NipAlreadyTakenException();
        }
    }

    // loading data of all companies from file including information about accountants assigned to companies
    public void loadCompaniesFromFile(AccountantRegistry accountantRegistry) throws IOException, AccountantNotFoundException, NipAlreadyTakenException {
        File file = new File(COMPANY_LIST_FILEPATH);
        //if file does not exist method stops at this point.
        if (!file.exists()) {
            return;
        }
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {

            String line = input.nextLine();
            String[] companyDetails = line.split(";");
            addLoadedData(new Company(companyDetails[0], Integer.parseInt(companyDetails[1]), companyDetails[2]));
            for (int i = 3; i < companyDetails.length; i++) {
                this.companies.get(this.companies.size() - 1).getCompanyAccountants().add(AccountantRegistry.findAccountantByLogin(companyDetails[i]));
            }
        }
        input.close();
    }

    // saving data of all companies to file including information about accountants assigned to companies
    public void writeCompanyToFile(Company company) throws IOException {
        try (FileWriter fw = new FileWriter(COMPANY_LIST_FILEPATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.print(company.getName() + ";" + company.getYearFound() + ";" + company.getNipNumber());

            for (Accountant accountant : company.getCompanyAccountants()
                    ) {
                out.print(";" + accountant.getLogin());
            }
            out.println();
        }
    }

    // removes company and all its data from the database, rewrites db file.
    public void deleteCompany(String nipNumber) throws IOException, CompanyNotFoundException {
        Company companyToBeRemoved = getCompanyByNipNumber(nipNumber);
        if (companyToBeRemoved != null) {
            this.companies.remove(companyToBeRemoved);
            rewriteFile();
        } else {
            throw new CompanyNotFoundException();
        }
    }

    //rewrites database file
    private void rewriteFile() throws IOException {

        try (FileWriter fw = new FileWriter(COMPANY_LIST_FILEPATH, false);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (Company company : companies) {
                out.print(company.getName() + ";" + company.getYearFound() + ";" + company.getNipNumber());
                for (Accountant accountant : company.getCompanyAccountants()
                        ) {
                    out.print(";" + accountant.getLogin());
                }
                out.println();
            }
        }
    }

    // checking if company with given nip numbers already added to the database. Returns Company or null.
    public Company getCompanyByNipNumber(String nipNumber) {
        for (Company company : companies
                ) {
            if (company.getNipNumber().equals(nipNumber)) {
                return company;
            }
        }
        return null;
    }

    public void changeCompanyName(String nipNumber, String newName) throws IOException, CompanyNotFoundException {
        getCompanyByNipNumber(nipNumber).changeName(newName);
        rewriteFile();
    }

    public void changeCompanyNip(String nipNumber, String newNip) throws IOException, CompanyNotFoundException, NipAlreadyTakenException {
        Company editedCompany = getCompanyByNipNumber(nipNumber);
        if (editedCompany == null) {
            throw new CompanyNotFoundException();
        }

        if (getCompanyByNipNumber(newNip) != null) {
            throw new NipAlreadyTakenException();
        }
        editedCompany.changeNip(newNip);
        rewriteFile();
    }

    public void assignAccountantToCompany(String companyNip, String accountantLogin) throws CompanyNotFoundException, AccountantAlreadyAssignedException, AccountantNotFoundException, IOException {
        Company company = getCompanyByNipNumber(companyNip);
        if (company == null) {
            throw new CompanyNotFoundException();
        }
        company.assignAccountant(accountantLogin);
        AccountantRegistry.findAccountantByLogin(accountantLogin).assignToCompany(company);
        AccountantRegistry.getInstance().writeDataToFile();
        rewriteFile();
    }

    public void unassignAccountantAfterDeletion(Accountant accountantToBeRemoved) throws IOException {
        for (Company company : this.companies
                ) {
            if (company.getCompanyAccountants().size() == 0) {
                continue;
            }
            for (Accountant accountant : company.getCompanyAccountants()
                    ) {
                if (accountant.equals(accountantToBeRemoved)) {
                    company.unassignAccountant(accountant);
                    break;
                }
            }
        }
        rewriteFile();
    }

}
