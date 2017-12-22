package pl.sdacademy.userinterface;

import pl.sdacademy.controllers.AccountantController;
import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.AccountantAlreadyExistsException;
import pl.sdacademy.exceptions.AccountantNotFoundException;

import java.io.IOException;
import java.util.Scanner;

public class ManagingAccountants {
    public static State managingAccountantsUI(State state, Scanner scanner) throws IOException {

        menuManagingAccountants();

        switch (scanner.nextInt()) {

            case 1:
                AccountantController.listAccountant();
                state = State.MANAGING_ACCOUNTANTS;
                scanner.nextLine();
                break;

            case 2:
                System.out.print("Dodaj nowego ksiegowego:\nPodaj login: ");
                String accountantLogin = scanner.next();
                System.out.println();
                System.out.print("Podaj hasło: ");
                String accountantPassword = scanner.next();
                try {
                    AccountantController.addAccountant(accountantLogin, accountantPassword);
                } catch (AccountantAlreadyExistsException e) {
                    System.out.println("Księgowy już istnieje w bazie danych!");
                }
                break;

            case 3:
                System.out.println("Podaj login księgowego, którego chcesz usunąć: ");
                scanner.nextLine();
                String accountantToBeDeleted = scanner.nextLine();
                try {
                    AccountantController.removeAccountant(accountantToBeDeleted);
                } catch (AccountantNotFoundException e) {
                    System.out.println("Nie ma takiego księgowego w bazie danych!");
                }
                break;

            case 0:
                state = State.LOGGED_IN_AS_ADMIN;
                scanner.nextLine();
                break;

            default:
                System.out.println("Zła odpowiedź");
                state = State.MANAGING_ACCOUNTANTS;
                scanner.nextLine();
                break;
        }
        return state;
    }

    private static void menuManagingAccountants() {
        System.out.println("Co chcesz zrobić?");
        System.out.println(" 1 - wypisać księgowych");
        System.out.println(" 2 - dodać nowego księgowego");
        System.out.println(" 3 - usunąć księgowego");

        System.out.println(" 0 - wyjść do menu głównego");
    }
}
