package pl.sdacademy.userInterfaceDisplay;

import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.enums.State;
import pl.sdacademy.models.Accountant;
import pl.sdacademy.models.Company;
import pl.sdacademy.models.CompanyRegistry;

import java.util.Scanner;

public class DisplayCompanyManagement {

    public static State displayCompanyManagement(Scanner scanner, Accountant currentAccountant) {
        State state;
        Company currentCompany;
        System.out.println("Lista firm przypisanych do twojego konta:");
        CompanyController.listCompaniesAssignedToAccountant(currentAccountant);

        System.out.println("\nCo chcesz zrobić?");
        System.out.println(" Numer NIP firmy - Zarządzanie firmą");

        System.out.println(" 0 - Wyjście do głównego menu");
        String tempImput = scanner.nextLine();
        if (tempImput.equals("0")) {
            state = State.LOGGED_IN_AS_ACCOUNTANT;
        } else {
            //TODO dodać sprawdzenie, czy admin ma prawo dostępu (jest przypisany) do danych wpisanej firmy
            if ((currentCompany = CompanyRegistry.getInstance().getCompanyByNipNumber(tempImput)) == null) {
                System.out.println("Nie ma takiej firmy w bazie danych!");
                state = State.LOGGED_IN_AS_ACCOUNTANT;
            } else {
                state = State.MANAGING_COMPANY_INVOICING;
            }
        }
        return state;
    }

    public static void displayManagingCompanyInvoicing() {
        System.out.println("Co chcesz zrobić?");
        System.out.println(" 1 - dodać fakturę sprzedażową / not implemented");
        System.out.println(" 2 - dodać fakturę zakupową / not implemented");
        System.out.println(" 3 - wyświetlić faktury sprzedażowe z danego miesiąca / not implemented");
        System.out.println(" 4 - wyświetlić faktury zakupowe z danego miesiąca / not implemented");
        System.out.println(" 0 - wyjść do menu głównego");
    }
}
