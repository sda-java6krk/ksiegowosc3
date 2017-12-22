package pl.sdacademy.userinterface;

import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.NipAlreadyTakenException;

import java.io.IOException;
import java.util.Scanner;

public class CreatingCompany {
    public static State creatingCompanyUI(Scanner scanner) throws IOException {
        State state;
        System.out.println("Podaj nazwę nowej firmy:");
        String name = scanner.nextLine();

        System.out.println("Podaj rok założenia nowej firmy:");
        int yearFound = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Podaj numer NIP firmy:");
        String nipNumber = scanner.nextLine();

        try {
            CompanyController.createCompany(name, yearFound, nipNumber);
            System.out.println("Pomyślnie dodano firmę do bazy danych");
        } catch (NipAlreadyTakenException e) {
            System.out.println("Firma o podanym numerze NIP już istnieje w bazie danych!");
        }
        return State.MANAGING_COMPANIES;
    }
}
