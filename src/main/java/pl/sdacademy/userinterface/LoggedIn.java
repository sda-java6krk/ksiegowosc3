package pl.sdacademy.userinterface;

import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.enums.State;
import java.util.Scanner;

public class LoggedIn {

    public static State loggedInAsAccountantUI(Scanner scanner) {
        State state;
        menuLoggedInAsAccountant();

        switch (scanner.nextInt()) {
            case 1:
                CompanyController.listCompanies();
                state = State.LOGGED_IN_AS_ACCOUNTANT;
                scanner.nextLine();
                break;

            case 2:
                state = State.LOGGING_TO_COMPANY_MANAGEMENT;
                scanner.nextLine();
                break;

            case 0:
                state = State.EXIT;
                scanner.nextLine();
                break;

            default:
                System.out.println("Zła odpowiedź");
                state = State.INIT;
                scanner.nextLine();
                break;
        }
        return state;
    }

    public static State loggedInAsAdmin(Scanner scanner) {
        State state;
        menuLoggedInAsAdmin();

        switch (scanner.nextInt()) {
            case 1:
                state = State.MANAGING_ADMINS;
                scanner.nextLine();
                break;

            case 2:
                state = State.MANAGING_ACCOUNTANTS;
                scanner.nextLine();
                break;

            case 3:
                state = State.MANAGING_COMPANIES;
                scanner.nextLine();
                break;

            case 0:
                state = State.EXIT;
                scanner.nextLine();
                break;

            default:
                System.out.println("Zła odpowiedź");
                state = State.LOGGED_IN_AS_ADMIN;
                scanner.nextLine();
                break;
        }
        return state;
    }

    private static void menuLoggedInAsAccountant() {
        System.out.println("Co chcesz zrobić?");
        System.out.println(" 1 - wypisać wszystkie firmy");
        System.out.println(" 2 - przejść do zarządzania firmami");

        System.out.println(" 0 - wyjść z programu");
    }

    private static void menuLoggedInAsAdmin() {
        System.out.println("Co chcesz zrobić?");
        System.out.println(" 1 - Zarządzanie administratorami");
        System.out.println(" 2 - Zarządzanie księgowymi");
        System.out.println(" 3 - Zarządzanie firmami");

        System.out.println(" 0 - Wyjście z programu");
    }


}
