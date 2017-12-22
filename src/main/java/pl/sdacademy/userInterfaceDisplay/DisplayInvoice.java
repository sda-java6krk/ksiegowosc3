package pl.sdacademy.userInterfaceDisplay;

import pl.sdacademy.controllers.InvoiceController;
import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.CompanyNotFoundException;

import java.time.DateTimeException;
import java.time.Month;
import java.util.Scanner;

public class DisplayInvoice {

    public static void displayViewingSellIncoice(Scanner scanner) {
        System.out.println("Podaj nazwę firmy: ");
        String companyName = scanner.nextLine();
        System.out.println("Podaj numer miesiąca (1-12): ");
        int month = scanner.nextInt();
        scanner.nextLine();

        try {
            Month temp = Month.of(month);
            System.out.println("Faktury sprzedaży");
            InvoiceController.listSalesInvoice(companyName, temp);
        } catch (CompanyNotFoundException e) {
            System.out.println("Firma o podanej nazwie nie istnieje!");
        } catch (DateTimeException e) {
            System.out.println("Taki miesiąc nie istnieje!");
        }
        return;
    }

    public static void displayViewingPurchaseInvoice(Scanner scanner) {
        System.out.println("Podaj nazwę firmy: ");
        String companyName = scanner.nextLine();
        System.out.println("Podaj numer miesiąca (1-12): ");
        int month = scanner.nextInt();
        scanner.nextLine();

        try {
            Month temp = Month.of(month);
            System.out.println("Faktury kupna");
            InvoiceController.listPurchaseInvoice(companyName, temp);
        } catch (CompanyNotFoundException e) {
            System.out.println("Firma o podanej nazwie nie istnieje!");
        } catch (DateTimeException e) {
            System.out.println("Taki miesiąc nie istnieje!");
        }
        return;
    }

    public static State displayManagingInvoices(Scanner scanner) {
        State state;
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
