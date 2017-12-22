package pl.sdacademy.views;

import pl.sdacademy.models.Company;
import pl.sdacademy.models.Invoice;
import pl.sdacademy.models.InvoiceRegistry;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcin on 13.12.2017.
 */
public class InvoiceView {

    public static void printInvoice(ArrayList<Invoice> invoices, String companyName, Month month){

        for (Invoice invoice : invoices) {
            if(invoice.getCompanyName().equals(companyName)){
                for (Invoice invoice1 : invoices){
                    if(invoice.getMonth().equals(month)){
                        System.out.println(invoice1.getInvoiceType() + " " + invoice1.getCompany() + " " + invoice1.getContractor()
                                + " " + invoice1.getInvoiceNumber()+ invoice1.getYear() +"/" + invoice1.getMonth() +" "
                                + invoice1.isPaid() + " " + invoice1.getAmount() + " " + invoice1.getVAT());
                    }
                }

            }
        }

    }

}