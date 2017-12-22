package pl.sdacademy.userinterface;

import pl.sdacademy.enums.State;
import java.util.Scanner;

public class UserInterfaceInit {
    public static State userInterfaceInit(Scanner scanner) {
        State state;
        menuInit();
        switch (scanner.nextInt()) {

            case 1:
                state = State.LOGGING_IN_AS_ADMIN;
                scanner.nextLine();
                break;

            case 2:
                state = State.LOGGING_IN_AS_ACCOUNTANT;
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

    private static void menuInit() {
        System.out.println("Dzień dobry, co chcesz zrobić?");
        System.out.println(" 1 - zalogować się jako admin");
        System.out.println(" 2 - zalogować się jako księgowy");
        System.out.println(" 0 - wyjść z programu");
    }
}
