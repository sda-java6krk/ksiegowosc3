package pl.sdacademy.userInterfaceDisplay;

import pl.sdacademy.enums.State;
import pl.sdacademy.models.*;

import java.io.IOException;
import java.util.Scanner;

import static pl.sdacademy.userInterfaceDisplay.DisplayAccountantInterface.assignCurrentAccountant;
import static pl.sdacademy.userInterfaceDisplay.DisplayAccountantInterface.displayLoggedAsAccountant;
import static pl.sdacademy.userInterfaceDisplay.DisplayAccountantInterface.loginAsAccountantView;
import static pl.sdacademy.userInterfaceDisplay.DisplayAdminInterface.*;
import static pl.sdacademy.userInterfaceDisplay.DisplayAdminInterface.displayCreatingCompany;
import static pl.sdacademy.userInterfaceDisplay.DisplayAdminInterface.displayManagingCompanies;
import static pl.sdacademy.userInterfaceDisplay.DisplayCase.displayCaseInit;
import static pl.sdacademy.userInterfaceDisplay.DisplayCompanyManagement.displayCompanyManagement;
import static pl.sdacademy.userInterfaceDisplay.DisplayCompanyManagement.displayManagingCompanyInvoicing;
import static pl.sdacademy.userInterfaceDisplay.DisplayInvoice.displayManagingInvoices;
import static pl.sdacademy.userInterfaceDisplay.DisplayInvoice.displayViewingPurchaseInvoice;
import static pl.sdacademy.userInterfaceDisplay.DisplayInvoice.displayViewingSellIncoice;

public class DisplayUserInterface {

    public static void runApp(State state, Scanner scanner, Accountant currentAccountant) throws IOException {
        while (state != State.EXIT) {
            switch (state) {
                case INIT: {
                    state = displayCaseInit(scanner);
                    break;
                }

                case LOGGING_IN_AS_ACCOUNTANT: {
                    currentAccountant = assignCurrentAccountant(scanner);
                    state = loginAsAccountantView(scanner,currentAccountant);
                    break;
                }

                case LOGGING_IN_AS_ADMIN: {
                    state = displayLoggingAsAdmin(scanner);
                    break;
                }

                case LOGGED_IN_AS_ACCOUNTANT: {
                    state = displayLoggedAsAccountant(scanner);
                    break;
                }

                case LOGGING_TO_COMPANY_MANAGEMENT: {
                    state = displayCompanyManagement(scanner, currentAccountant);
                    break;
                }

                case MANAGING_COMPANY_INVOICING: {
                    displayManagingCompanyInvoicing();
                    state = displayManagingInvoices(scanner);
                    break;
                }

                case ADDING_SELL_INVOICE: {
                    break;
                }

                case LOGGED_IN_AS_ADMIN: {
                    state = displayLoggedAsAdmin(scanner);
                    break;
                }

                case MANAGING_ADMINS: {
                    state = displayManagingAdmins(state, scanner);
                    break;
                }

                case MANAGING_ACCOUNTANTS: {
                    state = displayManagingAccountants(state, scanner);
                    break;
                }

                case MANAGING_COMPANIES: {
                    state = displayManagingCompanies(state, scanner);
                    break;
                }

                case CREATING_COMPANY: {
                    state = displayCreatingCompany(scanner);
                    break;
                }

                case VIEWING_SELL_INVOICE: {
                    displayViewingSellIncoice(scanner);
                    break;
                }

                case VIEWING_PURCHASE_INVOICE: {
                    displayViewingPurchaseInvoice(scanner);
                    break;
                }
            }
        }
    }

    public static void startUserInterface() {
        System.out.println("Dzień dobry, co chcesz zrobić?");
        System.out.println(" 1 - zalogować się jako admin");
        System.out.println(" 2 - zalogować się jako księgowy");
        System.out.println(" 0 - wyjść z programu");
    }
}
