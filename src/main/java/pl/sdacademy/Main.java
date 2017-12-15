package pl.sdacademy;

import pl.sdacademy.controllers.AccountantController;
import pl.sdacademy.controllers.AdminController;
import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.exceptions.AdminNotFoundException;
import pl.sdacademy.models.Accountant;
import pl.sdacademy.models.AccountantRegistry;
import pl.sdacademy.models.Admin;
import pl.sdacademy.models.AdminRegistry;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public enum State {
        INIT,
        LOGGING_IN_AS_ADMIN,
        LOGGING_IN_AS_ACCOUNTANT,
        LOGGED_IN,
        LOGGED_IN_ACCOUNTANT,
        CREATING_COMPANY,
        EXIT,
    }

    public static void main(String[] args) throws IOException {
        State state = State.INIT;
        AdminController.loadExistingAdminsFromFile();
        Scanner scanner = new Scanner(System.in);

        Admin currentAdmin = null;
        Accountant currentAccountant = null;


        while (state != State.EXIT) {
            switch (state) {
                case INIT: {
                    System.out.println("Dzień dobry, co chcesz zrobić?");
                    System.out.println(" 1 - zalogować się jako admin");
                    System.out.println(" 2 - zalogować się jako księgowy");
                    System.out.println(" 0 - wyjść z programu");

                    switch (scanner.nextInt()) {

                        case 1:
                            state = State.LOGGING_IN_AS_ADMIN;
                            scanner.nextLine();
                            break;

                        case 2:
                            state = State.LOGGING_IN_AS_ACCOUNTANT;
                            scanner.nextLine();
                            break;

                        case 0:
                            state = State.EXIT;
                            scanner.nextLine();
                            break;

                        default:
                            System.out.println("Zła odpowiedź");
                            state = State.INIT;
                            scanner.nextLine();
                            break;
                    }
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
                        state = State.LOGGED_IN_ACCOUNTANT;

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
                        state = State.LOGGED_IN;

                    } catch (AdminNotFoundException e) {
                        System.out.println("Zły login lub hasło");
                        state = State.INIT;
                    }
                    break;
                }

                case LOGGED_IN_ACCOUNTANT: {
                    System.out.println("Co chcesz zrobić?");
                    System.out.println(" 1 - wypisać wszystkie firmy");
                    System.out.println(" 0 - wyjść z programu");

                    switch (scanner.nextInt()) {
                        case 1:
                            CompanyController.listCompanies();
                            state = State.LOGGED_IN_ACCOUNTANT;
                            scanner.nextLine();
                            break;

                        case 0:
                            state = State.EXIT;
                            scanner.nextLine();
                            break;

                        default:
                            System.out.println("Zła odpowiedź");
                            state = State.INIT;
                            scanner.nextLine();
                            break;
                    }
                    break;

                }

                case LOGGED_IN: {
                    System.out.println("Co chcesz zrobić?");
                    System.out.println(" 1 - wypisać wszystkie firmy");
                    System.out.println(" 2 - dodać firmę");
                    System.out.println(" 3 - wypisać wszystkich adminów");
                    System.out.println(" 4 - dodać nowego admina");
                    System.out.println(" 5 - usunąć admina");
                    System.out.println(" 6 - wypisać wszyskich księgowych");
                    System.out.println(" 7 - dodać nowego księgowego");
                    System.out.println(" 8 - usunąć księgowego");

                    System.out.println(" 0 - wyjść z programu");

                    switch (scanner.nextInt()) {
                        case 1:
                            CompanyController.listCompanies();
                            state = State.LOGGED_IN;
                            scanner.nextLine();
                            break;

                        case 2:
                            state = State.CREATING_COMPANY;
                            scanner.nextLine();
                            break;

                        case 3:
                            AdminController.listAdmins();
                            state = State.LOGGED_IN;
                            scanner.nextLine();
                            break;

                        case 4:
                            System.out.println("Dodaj nowego admina: \nPodaj login: ");
                            String login = scanner.next();
                            System.out.println("Podaj hasło: ");
                            String password = scanner.next();
                            AdminController.addAdmin(login, password);
                            break;

                        case 5:
                            System.out.println("Podaj login admina, którego chcesz usunąć: ");
                            scanner.nextLine();
                            String adminToBeDeleted = scanner.next();
                            AdminController.removeAdmin(adminToBeDeleted);
                            break;

                        case 6:
                            AccountantController.listAccountant();
                            state = State.LOGGED_IN;
                            scanner.nextLine();
                            break;

                        case 7:
                            System.out.print("Dodaj nowego ksiegowego:\nPodaj login: ");
                            String accountantLogin = scanner.next();
                            System.out.println();
                            System.out.print("Podaj hasło: ");
                            String accountantPassword = scanner.next();
                            AccountantController.addAccountant(accountantLogin, accountantPassword);
                            break;

                        case 8:
                            System.out.println("Podaj login księgowego, którego chcesz usunąć: ");
                            scanner.nextLine();
                            String accountantToBeDeleted = scanner.nextLine();
                            AccountantController.removeAccountant(accountantToBeDeleted);
                            break;

                        case 0:
                            state = State.EXIT;
                            scanner.nextLine();
                            break;

                        default:
                            System.out.println("Zła odpowiedź");
                            state = State.INIT;
                            scanner.nextLine();
                            break;
                    }
                    break;
                }

                case CREATING_COMPANY: {
                    System.out.println("Podaj nazwę nowej firmy:");
                    String name = scanner.nextLine();

                    System.out.println("Podaj rok założenia nowej firmy:");
                    int yearFound = scanner.nextInt();
                    scanner.nextLine();

                    CompanyController.createCompany(name, yearFound);

                    state = State.LOGGED_IN;
                    break;
                }

            }
        }
        // write your code here
    }
}
