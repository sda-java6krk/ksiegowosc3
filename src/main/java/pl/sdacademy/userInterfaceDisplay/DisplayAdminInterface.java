package pl.sdacademy.userInterfaceDisplay;

import pl.sdacademy.controllers.AccountantController;
import pl.sdacademy.controllers.AdminController;
import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.*;
import pl.sdacademy.models.AccountantRegistry;
import pl.sdacademy.models.Admin;
import pl.sdacademy.models.AdminRegistry;
import pl.sdacademy.models.CompanyRegistry;

import java.io.IOException;
import java.util.Scanner;

public class DisplayAdminInterface {

    public static void getAdminFromFile() throws IOException {
        AdminRegistry.getInstance().loadExistingAdminsFromFile();
        AccountantRegistry.getInstance().loadExistingAccountantsFromFile();
        try {
            CompanyRegistry.getInstance().loadCompaniesFromFile(AccountantRegistry.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Błąd odczytu z pliku.");
        }
    }

    public static State displayManagingAdmins(State state, Scanner scanner) throws IOException {
        System.out.println("Co chcesz zrobić?");
        System.out.println(" 1 - wypisać wszystkich adminów");
        System.out.println(" 2 - dodać nowego admina");
        System.out.println(" 3 - usunąć admina");
        System.out.println(" 0 - wyjść do menu głównego");

        switch (scanner.nextInt()) {

            case 1:
                AdminController.listAdmins();
                state = State.MANAGING_ADMINS;
                scanner.nextLine();
                break;

            case 2:
                System.out.println("Dodaj nowego admina: \nPodaj login: ");
                String login = scanner.next();
                System.out.println("Podaj hasło: ");
                String password = scanner.next();
                AdminController.addAdmin(login, password);
                break;

            case 3:
                System.out.println("Podaj login admina, którego chcesz usunąć: ");
                scanner.nextLine();
                String adminToBeDeleted = scanner.next();
                AdminController.removeAdmin(adminToBeDeleted);
                break;

            case 0:
                state = State.LOGGED_IN_AS_ADMIN;
                scanner.nextLine();
                break;

            default:
                System.out.println("Zła odpowiedź");
                state = State.MANAGING_ADMINS;
                scanner.nextLine();
                break;
        }
        return state;
    }

    public static State displayManagingAccountants(State state, Scanner scanner) throws IOException {
        System.out.println("Co chcesz zrobić?");
        System.out.println(" 1 - wypisać księgowych");
        System.out.println(" 2 - dodać nowego księgowego");
        System.out.println(" 3 - usunąć księgowego");
        System.out.println(" 0 - wyjść do menu głównego");

        switch (scanner.nextInt()) {

            case 1:
                AccountantController.listAccountant();
                state = State.MANAGING_ACCOUNTANTS;
                scanner.nextLine();
                break;

            case 2:
                System.out.print("Dodaj nowego ksiegowego:\nPodaj login: ");
                String accountantLogin = scanner.next();
                System.out.println();
                System.out.print("Podaj hasło: ");
                String accountantPassword = scanner.next();
                try {
                    AccountantController.addAccountant(accountantLogin, accountantPassword);
                } catch (AccountantAlreadyExistsException e) {
                    System.out.println("Księgowy już istnieje w bazie danych!");
                }
                break;

            case 3:
                System.out.println("Podaj login księgowego, którego chcesz usunąć: ");
                scanner.nextLine();
                String accountantToBeDeleted = scanner.nextLine();
                try {
                    AccountantController.removeAccountant(accountantToBeDeleted);
                } catch (AccountantNotFoundException e) {
                    System.out.println("Nie ma takiego księgowego w bazie danych!");
                }
                break;

            case 0:
                state = State.LOGGED_IN_AS_ADMIN;
                scanner.nextLine();
                break;

            default:
                System.out.println("Zła odpowiedź");
                state = State.MANAGING_ACCOUNTANTS;
                scanner.nextLine();
                break;
        }
        return state;
    }

    public static State displayManagingCompanies(State state, Scanner scanner) throws IOException {
        System.out.println("Co chcesz zrobić?");
        System.out.println(" 1 - wypisać wszystkie firmy");
        System.out.println(" 2 - dodać nową firmę do bazy danych");
        System.out.println(" 3 - usunąć firmę z bazy danych");
        System.out.println(" 4 - zmienić nazwę firmy");
        System.out.println(" 5 - zmienić numer NIP firmy");
        System.out.println(" 6 - przypisać księgowego do firmy");
        System.out.println(" 0 - wyjść do menu głównego");

        switch (scanner.nextInt()) {
            case 1:
                CompanyController.listCompanies();
                state = State.MANAGING_COMPANIES;
                scanner.nextLine();
                break;

            case 2:
                state = State.CREATING_COMPANY;
                scanner.nextLine();
                break;

            case 3:
                scanner.nextLine();
                System.out.println("Podaj numer NIP firmy, którą chcesz usunąć: ");
                String companyToBeDeleted = scanner.nextLine();
                try {
                    CompanyController.removeCompanyFromDatabase(companyToBeDeleted);
                    System.out.println("Firma pomyślnie usunięta z bazy danych!");
                } catch (CompanyNotFoundException e) {
                    System.out.println("Firma o podanym numerze NIP nie została odnaleziona w bazie danych!");
                }
                break;

            case 4: {
                scanner.nextLine();
                System.out.println("Podaj numer NIP firmy, której nazwę chcesz zmienić: ");
                String companyNip = scanner.nextLine();
                System.out.println("Podaj nową nazwę: ");
                String newName = scanner.nextLine();
                try {
                    CompanyController.changeCompanyName(companyNip, newName);
                    System.out.println("Pomyślnie zmieniono nazwę firmy");
                } catch (CompanyNotFoundException e) {
                    System.out.println("Firma o podanym numerze NIP nie została odnaleziona w bazie danych!");
                }
            }
            break;

            case 5: {
                scanner.nextLine();
                System.out.println("Podaj numer NIP firmy, której chcesz zmienić numer NIP: ");
                String companyNip = scanner.nextLine();
                System.out.println("Podaj nowy numer NIP: ");
                String newNip = scanner.nextLine();
                try {
                    CompanyController.changeCompanyNip(companyNip, newNip);
                    System.out.println("Pomyślnie zmieniono nazwę firmy");
                } catch (CompanyNotFoundException e) {
                    System.out.println("Firma o podanym numerze NIP nie została odnaleziona w bazie danych!");
                } catch (NipAlreadyTakenException e) {
                    System.out.println("Podany numer NIP jest przypisany do innej firmy! Zmiana niemożliwa!");
                }
            }
            break;

            case 6: {
                scanner.nextLine();
                System.out.println("Podaj numer NIP firmy, której chcesz przydzielić księgowego: ");
                String companyNip = scanner.nextLine();
                System.out.println("Podaj login księgowego, którego chcesz przypisać do firmy: ");
                String accountantLogin = scanner.nextLine();
                try {
                    CompanyController.assignAccountantToCompany(companyNip, accountantLogin);
                    System.out.println("Pomyślnie przypisano księgowego.");
                } catch (CompanyNotFoundException e) {
                    System.out.println("Firma o podanym numerze NIP nie została odnaleziona w bazie danych!");
                } catch (AccountantNotFoundException | AccountantAlreadyAssignedException e) {
                    System.out.println("Księgowy o podanym loginie nie istnieje, lub jest już przypisany!");
                }
                break;
            }

            case 0:
                state = State.LOGGED_IN_AS_ADMIN;
                scanner.nextLine();
                break;

            default:
                System.out.println("Zła odpowiedź");
                state = State.MANAGING_COMPANIES;
                scanner.nextLine();
                break;
        }
        return state;
    }

    public static State displayCreatingCompany(Scanner scanner) throws IOException {
        State state;
        System.out.println("Podaj nazwę nowej firmy:");
        String name = scanner.nextLine();

        System.out.println("Podaj rok założenia nowej firmy:");
        int yearFound = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Podaj numer NIP firmy:");
        String nipNumber = scanner.nextLine();

        try {
            CompanyController.createCompany(name, yearFound, nipNumber);
            System.out.println("Pomyślnie dodano firmę do bazy danych");
        } catch (NipAlreadyTakenException e) {
            System.out.println("Firma o podanym numerze NIP już istnieje w bazie danych!");
        }

        state = State.MANAGING_COMPANIES;
        return state;
    }

    public static State displayLoggingAsAdmin(Scanner scanner) {
        Admin currentAdmin;
        State state;
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
        return state;
    }

    public static State displayLoggedAsAdmin(Scanner scanner) {
        State state;
        System.out.println("Co chcesz zrobić?");
        System.out.println(" 1 - Zarządzanie administratorami");
        System.out.println(" 2 - Zarządzanie księgowymi");
        System.out.println(" 3 - Zarządzanie firmami");
        System.out.println(" 0 - Wyjście z programu");

        switch (scanner.nextInt()) {
            case 1:
                state = State.MANAGING_ADMINS;
                scanner.nextLine();
                break;

            case 2:
                state = State.MANAGING_ACCOUNTANTS;
                scanner.nextLine();
                break;

            case 3:
                state = State.MANAGING_COMPANIES;
                scanner.nextLine();
                break;

            case 0:
                state = State.EXIT;
                scanner.nextLine();
                break;

            default:
                System.out.println("Zła odpowiedź");
                state = State.LOGGED_IN_AS_ADMIN;
                scanner.nextLine();
                break;
        }
        return state;
    }
}
