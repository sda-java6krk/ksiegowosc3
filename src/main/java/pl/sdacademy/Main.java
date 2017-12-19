package pl.sdacademy;

import pl.sdacademy.controllers.AccountantController;
import pl.sdacademy.controllers.AdminController;
import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.exceptions.*;
import pl.sdacademy.models.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public enum State {
        INIT,
        LOGGING_IN_AS_ADMIN,
        LOGGING_IN_AS_ACCOUNTANT,
        LOGGED_IN_AS_ADMIN,
        LOGGED_IN_AS_ACCOUNTANT,
        MANAGING_COMPANIES,
        MANAGING_ADMINS,
        MANAGING_ACCOUNTANTS,
        CREATING_COMPANY,
        EXIT,
    }

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
        Accountant currentAccountant;


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
                    System.out.println("Co chcesz zrobić?");
                    System.out.println(" 1 - wypisać wszystkie firmy");
                    System.out.println(" 0 - wyjść z programu");

                    switch (scanner.nextInt()) {
                        case 1:
                            CompanyController.listCompanies();
                            state = State.LOGGED_IN_AS_ACCOUNTANT;
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

                case LOGGED_IN_AS_ADMIN: {
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
                    break;
                }

                case MANAGING_ADMINS: {
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
                    break;
                }

                case MANAGING_ACCOUNTANTS: {
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
                            AccountantController.addAccountant(accountantLogin, accountantPassword);
                            break;

                        case 3:
                            System.out.println("Podaj login księgowego, którego chcesz usunąć: ");
                            scanner.nextLine();
                            String accountantToBeDeleted = scanner.nextLine();
                            AccountantController.removeAccountant(accountantToBeDeleted);
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
                    break;
                }

                case MANAGING_COMPANIES: {
                    System.out.println("Co chcesz zrobić?");
                    System.out.println(" 1 - wypisać wszystkie firmy");
                    System.out.println(" 2 - dodać nową firmę do bazy danych");
                    System.out.println(" 3 - usunąć firmę z bazy danych");
                    System.out.println(" 4 - zmienić nazwę firmy / not implemented");
                    System.out.println(" 5 - zmienić numer NIP firmy / not implemented");
                    System.out.println(" 6 - przypisać księgowego do firmy / not implemented");

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
                                System.out.println("");
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
                    break;
                }

                case CREATING_COMPANY: {
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

                    state = State.LOGGED_IN_AS_ADMIN;
                    break;
                }
            }
        }
    }
}
