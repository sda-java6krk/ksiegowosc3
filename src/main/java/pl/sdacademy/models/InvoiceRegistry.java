package pl.sdacademy.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class InvoiceRegistry {

    private static InvoiceRegistry instance = null;

    public static InvoiceRegistry getInstance() {
        if (instance == null) {
            instance = new InvoiceRegistry();
        }
        return instance;
    }

    private ArrayList<Invoice> salesInvoicesList;
    private ArrayList<Invoice> purchaseInvoicesList;

    public InvoiceRegistry() {
            this.salesInvoicesList = new ArrayList<>();
            this.purchaseInvoicesList = new ArrayList<>();
    }


    public ArrayList<Invoice> getPurchaseInvoicesList() {
        return purchaseInvoicesList;
    }

    public ArrayList<Invoice> getSalesInvoicesList() {
        return salesInvoicesList;
    }


    public void addSalesInvoice(Invoice invoice) {
        this.salesInvoicesList.add(invoice);
    }

    public void addPurchaseInvoice(Invoice invoice) {
        this.purchaseInvoicesList.add(invoice);
    }


    public String generateInvoiceNumber(String nipNumber, int counter) {
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();

        String temp="FV/"+nipNumber +"/" + String.valueOf(year)+ "/" + String.valueOf(month)+"/" +String.valueOf(counter);

        return temp;
    }
}
