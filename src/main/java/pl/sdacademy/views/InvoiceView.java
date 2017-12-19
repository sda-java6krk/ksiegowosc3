package pl.sdacademy.views;

import pl.sdacademy.models.Invoice;

import java.util.List;

/**
 * Created by marcin on 13.12.2017.
 */
public class InvoiceView {
    public static void printSalesInvioces(List<Invoice> salesInvoicesList){
        for (Invoice invoice : salesInvoicesList){
            System.out.println("Faktura sprzedarzowa: " + invoice.toString());
        }
    }

    public static void printPurchaseInvioces(List<Invoice> purchaseInvoicesList){
        for (Invoice invoice : purchaseInvoicesList){
            System.out.println("Faktura zakupowa: " + invoice.toString());
        }
    }
}