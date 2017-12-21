package pl.sdacademy.controllers;

import pl.sdacademy.models.Company;
import pl.sdacademy.models.Contractor;
import pl.sdacademy.models.Invoice;
import pl.sdacademy.models.InvoiceRegistry;

import java.math.BigDecimal;

/**
 * Created by marcin on 13.12.2017.
 */
public class InvoiceController {

    public static void createPurchaseInvoice(String contractorNip, Company company, String invoiceNumber, BigDecimal amount, int VAT, boolean isPaid){
        InvoiceRegistry.getInstance().addSalesInvoice(new Invoice(contractorNip, company, invoiceNumber, amount, VAT, isPaid));
    }

    public static void createSalesInvoice(String contractorNip, Company company, BigDecimal amount, int VAT, boolean isPaid){
        InvoiceRegistry.getInstance().addPurchaseInvoice(new Invoice(contractorNip, company, amount, VAT, isPaid));
    }

}
