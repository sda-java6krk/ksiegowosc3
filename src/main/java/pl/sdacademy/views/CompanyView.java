package pl.sdacademy.views;

import pl.sdacademy.models.Accountant;
import pl.sdacademy.models.Company;
import pl.sdacademy.models.CompanyRegistry;

import java.util.List;

/**
 * Created by marcin on 13.12.2017.
 */
public class CompanyView {
    public static void printCompanies(List<Company> companies) {
        for (Company company : companies) {
            System.out.println(company.getName() + " (rok założenia: " + company.getYearFound() + ", numer NIP: " + company.getNipNumber() + ")");
            System.out.println("Przypisani księgowi: ");

            if (company.getCompanyAccountants().size() > 0) {
                System.out.print("\t");

                for (Accountant accountant : company.getCompanyAccountants()
                        ) {
                    System.out.print(accountant.getLogin() + " ");
                }
                System.out.println();
            } else {
                System.out.println("\tBrak przypisanych księgowych!");
            }
        }
    }

    //Lepsze w tej formie do testowania:
  //  public List<String> listCompanies(List<Company> companies){
  //      for (Company company : companies) {
  //          System.out.println(company.getName() + " (rok założenia: " + company.getYearFound() + ", numer NIP: " + company.getNipNumber() + ")");
  //          String companyDescription = company.getName() + " rok założenia...."
  //      }
  //  }


}
