package pl.sdacademy;

import pl.sdacademy.controllers.AccountantController;
import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.controllers.InvoiceController;
import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.*;
import pl.sdacademy.models.*;
import pl.sdacademy.userinterface.*;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.Month;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        State state = State.INIT;

        AdminRegistry.getInstance().loadExistingAdminsFromFile();
        AccountantRegistry.getInstance().loadExistingAccountantsFromFile();
        try {
            CompanyRegistry.getInstance().loadCompaniesFromFile(AccountantRegistry.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Błąd odczytu z pliku.");
        }

        Scanner scanner = new Scanner(System.in);
        Admin currentAdmin;
        Accountant currentAccountant = null;
        Company currentCompany = null;

        while (state != State.EXIT) {
            switch (state) {
                case INIT: {
                    state = UserInterfaceInit.userInterfaceInit(scanner);
                    break;
                }

                case LOGGING_IN_AS_ACCOUNTANT: {
                    System.out.println("Podaj login:");
                    String login = scanner.nextLine();

                    System.out.println("Podaj hasło:");
                    String password = scanner.nextLine();

                    try {
                        currentAccountant = AccountantRegistry.getInstance().findAccountant(login, password);
                        System.out.println("Dzień dobry " + currentAccountant.getLogin());
                        state = State.LOGGED_IN_AS_ACCOUNTANT;

                    } catch (AccountantNotFoundException e) {
                        System.out.println("Zły login lub hasło");
                        state = State.INIT;
                    }
                    break;

                }

                case LOGGING_IN_AS_ADMIN: {
                    System.out.println("Podaj login:");
                    String login = scanner.nextLine();
                    System.out.println("Podaj hasło:");
                    String password = scanner.nextLine();

                    try {
                        currentAdmin = AdminRegistry.getInstance().findAdmin(login, password);
                        System.out.println("Dzień dobry " + currentAdmin.getLogin());
                        state = State.LOGGED_IN_AS_ADMIN;

                    } catch (AdminNotFoundException e) {
                        System.out.println("Zły login lub hasło");
                        state = State.INIT;
                    }
                    break;
                }

                case LOGGED_IN_AS_ACCOUNTANT: {
                    state = LoggedIn.loggedInAsAccountantUI(scanner);
                    break;
                }

                case LOGGING_TO_COMPANY_MANAGEMENT: {
                    state = LoggingToCompanyManagement.loggingToCompanyManagementUI(scanner, currentAccountant);
                    break;
                }

                case MANAGING_COMPANY_INVOICING: {
                    state = ManagingCompanyInvoice.companyInvoiceManagement(scanner);
                    break;

                }

                case ADDING_SELL_INVOICE: {
//                    Contractor contractor, Company company, BigDecimal amount, int VAT, boolean isPaid
                    System.out.println("");
                    break;
                }

                case LOGGED_IN_AS_ADMIN: {
                    state = LoggedIn.loggedInAsAdmin(scanner);
                    break;
                }

                case MANAGING_ADMINS: {
                    state = ManagingAdmins.managingAdminsUI(state, scanner);
                    break;
                }

                case MANAGING_ACCOUNTANTS: {
                    state = ManagingAccountants.managingAccountantsUI(state, scanner);
                    break;
                }

                case MANAGING_COMPANIES: {
                    state = ManagingCompanies.managingCompaniesUI(state, scanner);
                    break;
                }

                case CREATING_COMPANY: {
                    state = CreatingCompany.creatingCompanyUI(scanner);
                    break;
                }

                case VIEWING_SELL_INVOICE: {
                    ViewingSellInvoice.viewingSellInvoiceUI(scanner);
                    break;
                }

                case VIEWING_PURCHASE_INVOICE: {
                    ViewingPurchaseInvoice.viewingPurchaseInvoiceUI(scanner);
                    break;
                }
            }
        }
    }

}
