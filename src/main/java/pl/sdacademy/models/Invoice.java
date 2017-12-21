package pl.sdacademy.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;


/**
 * Created by marcin on 13.12.2017.
 */
public class Invoice {

    public enum InvoiceType {
        SALES_INVOICE,
        PURCHASE_INVOICE,
    }

    private InvoiceType invoiceType;
    private String invoiceNumber;
    private BigDecimal amount;
    private int VAT;
    private boolean isPaid;
    private Contractor contractor;
    private Company company;
    private Month month;
    private int year;



    public Invoice(Contractor contractor, Company company, BigDecimal amount, int VAT, boolean isPaid) {
        this.invoiceType = InvoiceType.SALES_INVOICE;
        this.contractor= contractor;
        this.company = company;
        this.amount = amount;
        this.VAT = VAT;
        this.isPaid = isPaid;
        this.year = LocalDate.now().getYear();

        //TODO
        this.invoiceNumber = InvoiceRegistry.generateInvoiceNumber(company.getNipNumber(),123);

    }

    public Invoice(Contractor contractor, Company company, String invoiceNumber, BigDecimal amount, int VAT, boolean isPaid) {
        this.invoiceType = InvoiceType.PURCHASE_INVOICE;
        this.contractor = contractor;
        this.company = company;
        this.invoiceNumber = invoiceNumber;
        this.amount = amount;
        this.VAT = VAT;
        this.isPaid = isPaid;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getVAT() {
        return VAT;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public Company getCompany() {
        return company;
    }

    public String getCompanyName(){
        return company.getName();
    }

    public Month getMonth() { //temporary
        return InvoiceRegistry.getDate();
    }

    public int getYear() {
        return year;
    }
}
