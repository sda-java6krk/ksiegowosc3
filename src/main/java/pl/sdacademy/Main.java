package pl.sdacademy;

import pl.sdacademy.controllers.AccountantController;
import pl.sdacademy.controllers.AdminController;
import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.exceptions.AdminNotFoundException;
import pl.sdacademy.models.Admin;
import pl.sdacademy.models.AdminRegistry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {

    public enum State {
        INIT,
        LOGGING_IN_AS_ADMIN,
        LOGGED_IN,
        CREATING_COMPANY,
        EXIT,
    }

    public static void main(String[] args) throws IOException {
	    State state = State.INIT;
	    AdminController.loadExistingAdminsFromFile();
        Scanner scanner = new Scanner(System.in);

        Admin currentAdmin = null;



        while (state != State.EXIT) {
            switch(state) {
                case INIT: {
                    System.out.println("Dzień dobry, co chcesz zrobić?");
                    System.out.println(" 1 - zalogować się jako admin");
                    System.out.println(" 0 - wyjść z programu");

                    switch (scanner.nextInt()) {
                        case 1:
                            state = State.LOGGING_IN_AS_ADMIN;
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

                case LOGGED_IN: {
                    System.out.println("Co chcesz zrobić?");
                    System.out.println(" 1 - wypisać wszystkie firmy");
                    System.out.println(" 2 - dodać firmę");
                    System.out.println(" 3 - dodać nowego admina");
                    System.out.println(" 4 - dodać nowego księgowego");
                    System.out.println(" 5 - usunąć admina");
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
                            System.out.println("Dodaj nowego admina: \nPodaj login: ");
                            String login = scanner.next();
                            System.out.println("Podaj hasło: ");
                            String password = scanner.next();
                            AdminController.addAdmin(login, password);
                            break;

                        case 4:
                            System.out.print("Dodaj nowego ksiegowego:\nPodaj login: ");
                            String accountantLogin = scanner.next();
                            System.out.println();
                            System.out.print("Podaj hasło: ");
                            String accountantPassword = scanner.next();
                            AccountantController.addAccountant(accountantLogin,accountantPassword);
                            break;

                        case 5:
                            System.out.println("Podaj login admina, którego chcesz usunąć: ");
                            scanner.nextLine();
                            String adminToBeDeleted = scanner.next();
                            AdminController.removeAdmin(adminToBeDeleted);
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
