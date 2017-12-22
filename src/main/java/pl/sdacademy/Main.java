package pl.sdacademy;

import pl.sdacademy.controllers.AccountantController;
import pl.sdacademy.controllers.AdminController;
import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.controllers.InvoiceController;
import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.*;
import pl.sdacademy.models.*;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.Month;
import java.util.Scanner;

import static pl.sdacademy.userInterfaceDisplay.DisplayAccountantInterface.assignCurrentAccountant;
import static pl.sdacademy.userInterfaceDisplay.DisplayAccountantInterface.loginAsAccountantView;
import static pl.sdacademy.userInterfaceDisplay.DisplayAdminInterface.*;
import static pl.sdacademy.userInterfaceDisplay.DisplayCase.displayCaseInit;
import static pl.sdacademy.userInterfaceDisplay.DisplayInvoice.displayViewingPurchaseInvoice;
import static pl.sdacademy.userInterfaceDisplay.DisplayInvoice.displayViewingSellIncoice;
import static pl.sdacademy.userInterfaceDisplay.DisplayUserInterface.*;

public class Main {

    public static void main(String[] args) throws IOException, AccountantNotFoundException {
        State state = State.INIT;
        getAdminFromFile();
        Scanner scanner = new Scanner(System.in);
        Admin currentAdmin;
        Accountant currentAccountant = null;
        Company currentCompany = null;

        runApp(state, scanner, currentAccountant);
    }




}
