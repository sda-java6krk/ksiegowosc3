package pl.sdacademy.models;

import java.math.BigDecimal;

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

    public Invoice(String contractorNip, Company company, BigDecimal amount, int VAT, boolean isPaid) {

        this.invoiceType = InvoiceType.SALES_INVOICE;
        this.contractor= ContractorRegistry.getInstance().getContractorByNip(contractorNip);
        this.company = company;
        this.amount = amount;
        this.VAT = VAT;
        this.isPaid = isPaid;
        //TODO
        this.invoiceNumber = InvoiceRegistry.generateInvoiceNumber(company.getNipNumber(),123);

    }

    public Invoice(String contractorNip, Company company, String invoiceNumber, BigDecimal amount, int VAT, boolean isPaid) {
        this.invoiceType = InvoiceType.PURCHASE_INVOICE;
        this.contractor = ContractorRegistry.getInstance().getContractorByNip(contractorNip);
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
}
