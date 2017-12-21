package pl.sdacademy.controllers;

import pl.sdacademy.exceptions.CompanyNotFoundException;

import pl.sdacademy.models.*;
import pl.sdacademy.views.InvoiceView;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.Month;

/**
 * Created by marcin on 13.12.2017.
 */
public class InvoiceController {

    public static void createPurchaseInvoice(Contractor contractor, Company company, String invoiceNumber, BigDecimal amount, int VAT, boolean isPaid){
        InvoiceRegistry.getInstance().addSalesInvoice(new Invoice(contractor, company, invoiceNumber, amount, VAT, isPaid));
    }

    public static void createSalesInvoice(Contractor contractor, Company company, BigDecimal amount, int VAT, boolean isPaid){
        InvoiceRegistry.getInstance().addPurchaseInvoice(new Invoice(contractor, company, amount, VAT, isPaid));
    }

    public static void listSalesInvoice(String companyName, Month month) throws CompanyNotFoundException, DateTimeException {
        InvoiceView.printInvoice(InvoiceRegistry.getInstance().getSalesInvoicesList(), companyName, month);
    }
    public static void listPurchaseInvoice(String companyName, Month month) throws CompanyNotFoundException, DateTimeException{
        InvoiceView.printInvoice(InvoiceRegistry.getInstance().getPurchaseInvoicesList(), companyName, month);
    }



}
