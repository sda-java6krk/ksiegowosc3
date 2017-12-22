package pl.sdacademy.userinterface;

import pl.sdacademy.controllers.InvoiceController;
import pl.sdacademy.exceptions.CompanyNotFoundException;

import java.time.DateTimeException;
import java.time.Month;
import java.util.Scanner;

public class ViewingPurchaseInvoice {
    public static void viewingPurchaseInvoiceUI(Scanner scanner) {
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
    }
}
