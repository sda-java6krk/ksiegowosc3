package pl.sdacademy.views;

import pl.sdacademy.models.Company;

import java.util.List;

/**
 * Created by marcin on 13.12.2017.
 */
public class CompanyView {
    public static void printCompanies(List<Company> companies) {
        for(Company company : companies) {
            System.out.println(company.getName() + " (rok założenia: " + company.getYearFound() + ")");
        }
    }
}
