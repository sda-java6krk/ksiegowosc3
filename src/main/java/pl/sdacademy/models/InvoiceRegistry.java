package pl.sdacademy.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
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


    public void addSalesInvoice(Contractor contractor, Company currentCompany, BigDecimal amount, int vat, boolean isPaid) {
        this.salesInvoicesList.add(new Invoice(contractor, currentCompany, amount, vat, isPaid));
    }

    public void addPurchaseInvoice(Contractor contractor, Company company, String invoiceNumber, BigDecimal amount, int VAT, boolean isPaid) {
        this.purchaseInvoicesList.add(new Invoice(contractor, company, invoiceNumber, amount, VAT, isPaid));
    }


    public static String generateInvoiceNumber(String nipNumber, int counter) {
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();

        return "FV/" + nipNumber + "/" + String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(counter);
    }

    public static Month getDate() { //temporary
        return LocalDate.now().getMonth();
    }

}
