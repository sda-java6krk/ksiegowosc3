package pl.sdacademy.userInterfaceDisplay;

import pl.sdacademy.enums.State;

import java.util.Scanner;

import static pl.sdacademy.userInterfaceDisplay.DisplayUserInterface.startUserInterface;

public class DisplayCase {
    public static State displayCaseInit(Scanner scanner) {
        State state;
        startUserInterface();

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
}
