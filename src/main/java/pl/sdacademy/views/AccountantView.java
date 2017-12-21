package pl.sdacademy.views;

import pl.sdacademy.models.Accountant;
import pl.sdacademy.models.Company;
import pl.sdacademy.models.CompanyRegistry;

import java.util.List;

/**
 * Created by marcin on 13.12.2017.
 */
public class AccountantView {
    public static void printAccountants(List<Accountant> accountants){
        for (Accountant accountant : accountants){
            System.out.println("Księgowy: " + accountant.getLogin());
        }
    }
    public static void printCompaniesAssignedToAccountant(Accountant accountant) {
        for (Company company: accountant.getCompaniesAssigned()
                ) {
            System.out.println("Firma: " + company.getName() + ", numer NIP: " + company.getNipNumber() + ".");
        }
    }
}
