package pl.sdacademy.models;

import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.exceptions.NipAlreadyTakenException;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by marcin on 13.12.2017.
 */
public class CompanyRegistry {
    private static CompanyRegistry instance = null;
    private static final String COMPANYLISTFILEPATCH = "src/resources/companyList.txt";
    private static final String COMPANYLISTTEMPFILEPATCH = "src/resources/companyList.tmp";

    public static CompanyRegistry getInstance() {
        if (instance == null) {
            instance = new CompanyRegistry();
        }
        return instance;
    }


    private ArrayList<Company> companies;

    public CompanyRegistry() {
        this.companies = new ArrayList<>();

        this.companies.add(new Company("Ziutex sp. z o.o.", 1990, 123123));
        this.companies.add(new Company("Krakbud s.j.", 1995, 345345));
    }

    // listing all companies
    public List<Company> getCompanies() {
        return this.companies;
    }

    // adding a specific company to the database. Such method assign no accountants to the company
    public void add(Company company) throws IOException, NipAlreadyTakenException {
        if (findCompanyByNipNumber(company.getNipNumber()) == null) {
            this.companies.add(company);
            writeCompanyToFile(company);
        } else {
            throw new NipAlreadyTakenException();
        }
    }

    // loading data of all companies from file including information about accountants assigned to companies
    public void loadCompanyFromFile(AccountantRegistry accountantRegistry) throws IOException, AccountantNotFoundException, NipAlreadyTakenException {
        File file = new File("src/resources/companyList.txt");
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {

            String line = input.nextLine();
            String[] companyDetails = line.split(";");
            add(new Company(companyDetails[0], Integer.parseInt(companyDetails[1]), Integer.parseInt(companyDetails[2])));
            for (int i = 2; i < companyDetails.length - 1; i++) {
                this.companies.get(this.companies.size() - 1).getCompanyAccountants().add(accountantRegistry.findAccountantByLogin(companyDetails[i]));
            }
        }
        input.close();
    }

    // saving data of all companies to file including information about accountants assigned to companies
    public void writeCompanyToFile(Company company) throws IOException {
        try (FileWriter fw = new FileWriter(COMPANYLISTFILEPATCH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.print(company.getName() + ";" + company.getYearFound() + ";" + company.getNipNumber() + ";");

            for (Accountant accountant : company.getCompanyAccountants()
                    ) {
                out.print(accountant.getLogin() + ";");
            }
            System.out.println();
        }
        System.out.println("Pomyślnie dodano firmę!");
    }

    // removes company and all its data from the database, rewrites db file.
    public void deleteCompany(int nipNumber) throws IOException {
        Company companyToBeRemoved = findCompanyByNipNumber(nipNumber);
        if (companyToBeRemoved != null) {
            this.companies.remove(companyToBeRemoved);
            System.out.println("Firma usunięta z bazy danych!");
            rewriteFile();
        }
    }

    //rewrites database file
    private void rewriteFile() throws IOException {

        try (FileWriter fw = new FileWriter(COMPANYLISTTEMPFILEPATCH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (Company company : companies) {
                out.print(company.getName() + ";" + company.getYearFound() + ";" + company.getNipNumber() + ";");
                for (Accountant accountant : company.getCompanyAccountants()
                        ) {
                    out.print(accountant.getLogin() + ";");
                }
                System.out.println();
            }
        }
        File oldFile = new File(COMPANYLISTFILEPATCH);
        boolean oldFileDeletionStatus = Files.deleteIfExists(oldFile.toPath());
        if (!oldFileDeletionStatus) System.out.println("Błąd przy usuwaniu firmy z bazy danych!");

        File newFile = new File(COMPANYLISTTEMPFILEPATCH);
        boolean newFileCreationStatus = newFile.renameTo(new File(COMPANYLISTFILEPATCH));
        if (!newFileCreationStatus) System.out.println("Błąd przy usuwaniu firmy z bazy danych!");
    }

    // checking if company with given nip numbers already added to the database. Returns Company or null.
    public Company findCompanyByNipNumber(int nipNumber) {
        for (Company company : companies
                ) {
            if (company.getNipNumber() == nipNumber) {
                return company;
            }
        }
        return null;
    }

    public void changeCompanyName(Company company, String newName) throws IOException {
        company.changeName(newName);
        rewriteFile();
    }

    public void changeCompanyNip(Company company, int newNip) throws IOException {
        company.changeNip(newNip);
        rewriteFile();
    }
}
