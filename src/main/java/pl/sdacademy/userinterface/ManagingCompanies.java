package pl.sdacademy.userinterface;

import pl.sdacademy.controllers.CompanyController;
import pl.sdacademy.enums.State;
import pl.sdacademy.exceptions.AccountantAlreadyAssignedException;
import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.exceptions.CompanyNotFoundException;
import pl.sdacademy.exceptions.NipAlreadyTakenException;

import java.io.IOException;
import java.util.Scanner;

public class ManagingCompanies {
    public static State managingCompaniesUI(State state, Scanner scanner) throws IOException {
        menuManagingCompanies();

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

    private static void menuManagingCompanies() {
        System.out.println("Co chcesz zrobić?");
        System.out.println(" 1 - wypisać wszystkie firmy");
        System.out.println(" 2 - dodać nową firmę do bazy danych");
        System.out.println(" 3 - usunąć firmę z bazy danych");
        System.out.println(" 4 - zmienić nazwę firmy");
        System.out.println(" 5 - zmienić numer NIP firmy");
        System.out.println(" 6 - przypisać księgowego do firmy");

        System.out.println(" 0 - wyjść do menu głównego");
    }
}
