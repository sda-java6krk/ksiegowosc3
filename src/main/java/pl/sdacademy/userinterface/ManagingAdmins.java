package pl.sdacademy.userinterface;

import pl.sdacademy.controllers.AdminController;
import pl.sdacademy.enums.State;

import java.io.IOException;
import java.util.Scanner;

public class ManagingAdmins {
    public static State managingAdminsUI(State state, Scanner scanner) throws IOException {

        menuManagingAdmins();

        switch (scanner.nextInt()) {

            case 1:
                AdminController.listAdmins();
                state = State.MANAGING_ADMINS;
                scanner.nextLine();
                break;

            case 2:
                System.out.println("Dodaj nowego admina: \nPodaj login: ");
                String login = scanner.next();
                System.out.println("Podaj hasło: ");
                String password = scanner.next();
                AdminController.addAdmin(login, password);
                break;

            case 3:
                System.out.println("Podaj login admina, którego chcesz usunąć: ");
                scanner.nextLine();
                String adminToBeDeleted = scanner.next();
                AdminController.removeAdmin(adminToBeDeleted);
                break;

            case 0:
                state = State.LOGGED_IN_AS_ADMIN;
                scanner.nextLine();
                break;

            default:
                System.out.println("Zła odpowiedź");
                state = State.MANAGING_ADMINS;
                scanner.nextLine();
                break;
        }
        return state;
    }

    private static void menuManagingAdmins() {
        System.out.println("Co chcesz zrobić?");
        System.out.println(" 1 - wypisać wszystkich adminów");
        System.out.println(" 2 - dodać nowego admina");
        System.out.println(" 3 - usunąć admina");

        System.out.println(" 0 - wyjść do menu głównego");
    }
}
