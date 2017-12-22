package pl.sdacademy.userinterface;

import pl.sdacademy.enums.State;

import java.util.Scanner;

public class ManagingCompanyInvoice {
    public static State companyInvoiceManagement(Scanner scanner) {
        State state;
        System.out.println("Co chcesz zrobić?");
        System.out.println(" 1 - dodać fakturę sprzedażową / not implemented");
        System.out.println(" 2 - dodać fakturę zakupową / not implemented" );
        System.out.println(" 3 - wyświetlić faktury sprzedażowe z danego miesiąca / not implemented");
        System.out.println(" 4 - wyświetlić faktury zakupowe z danego miesiąca / not implemented");

        System.out.println(" 0 - wyjść do menu głównego");

        switch (scanner.nextInt()) {
            case 1:
                state = State.ADDING_SELL_INVOICE;
                scanner.nextLine();
                break;

            case 2:
                state = State.ADDING_PURCHASE_INVOICE;
                scanner.nextLine();
                break;

            case 3:
//                            state = State.VIEWING_SELL_INVOICE;
                state = State.LOGGING_TO_COMPANY_MANAGEMENT;
                scanner.nextLine();
                break;

            case 4:
//                            state = State.VIEWING_PURCHASE_INVOICE;
                state = State.LOGGING_TO_COMPANY_MANAGEMENT;
                scanner.nextLine();
                break;

            case 0:
                state = State.LOGGING_TO_COMPANY_MANAGEMENT;
                scanner.nextLine();
                break;

            default:
                System.out.println("Zła odpowiedź");
                state = State.MANAGING_COMPANY_INVOICING;
                scanner.nextLine();
                break;
        }
        return state;
    }
}
