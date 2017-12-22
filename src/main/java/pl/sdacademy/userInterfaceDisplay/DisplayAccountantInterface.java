package pl.sdacademy.userInterfaceDisplay;

import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.models.Accountant;
import pl.sdacademy.models.AccountantRegistry;

import java.util.Scanner;

public class DisplayAccountantInterface {

    public static State loginAsAccountantView(Scanner scanner, Accountant accountant) {
        State state = null;
        if (accountant != null) {
            state = State.LOGGED_IN_AS_ACCOUNTANT;
        } else {
            System.out.println("Zły login lub hasło");
            state = State.INIT;
        }
        return state;
    }

    public static Accountant assignCurrentAccountant(Scanner scanner) {
        System.out.println("Podaj login:");
        String login = scanner.nextLine();

        System.out.println("Podaj hasło:");
        String password = scanner.nextLine();

        Accountant accountant = null;
        try {
            accountant = AccountantRegistry.getInstance().findAccountant(login, password);
            System.out.println("Dzień dobry " + accountant.getLogin());
        } catch (AccountantNotFoundException e) {
            accountant = null;
        }
        return accountant;
    }

    public static State displayLoggedAsAccountant(Scanner scanner) {
        State state;
        System.out.println("Co chcesz zrobić?");
        System.out.println(" 1 - wypisać wszystkie firmy");
        System.out.println(" 2 - przejść do zarządzania firmami");
        System.out.println(" 0 - wyjść z programu");

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
}
