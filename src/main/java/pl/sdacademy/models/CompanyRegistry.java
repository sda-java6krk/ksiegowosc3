package pl.sdacademy.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by marcin on 13.12.2017.
 */
public class CompanyRegistry {
    private static CompanyRegistry instance = null;

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


    public List<Company> getCompanies() {
        return this.companies;
    }


    public void add(Company company) {
        this.companies.add(company);
    }

    public void loadCompanyFromFile() throws IOException {
        File file = new File("src/resources/companyList.txt");
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {

            //TODO
//            String line = input.nextLine();
//            String[] credentials = line.split(";");
//            addAdmin(credentials[0], credentials[1]);
        }
        input.close();
    }

    public void writeCompanyToFile(String companyName, int yearFound, int nipNumber, Set<Accountant> accountants) throws IOException {
        try (FileWriter fw = new FileWriter("src/resources/companyList.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(companyName + ";" + yearFound + ";" + nipNumber);
            for (Accountant a : accountants
                    ) {
            //TODO
            }
        }
        System.out.println("Pomyślnie dodano firmę!");
    }

}
