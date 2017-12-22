package pl.sdacademy.controllers;

import org.junit.Assert;
import org.junit.*;
import pl.sdacademy.exceptions.*;
import pl.sdacademy.models.AccountantRegistry;
import pl.sdacademy.models.Company;
import pl.sdacademy.models.CompanyRegistry;

import java.io.IOException;

import static org.mockito.Mockito.mock;

public class CompanyControllerTest {

    @Test
    public void shouldThrowExceptionWhenNipIsAlreadyTakenWhenCreateCompany() {
        //given
        String name;
        int yearFound;
        String nipNumber;

        //when
        name = "Drutex";
        yearFound = 2000;
        nipNumber = "123456789";

        //then
        try {
            CompanyController.createCompany(name, yearFound, nipNumber);
            CompanyController.createCompany(name, yearFound, nipNumber);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NipAlreadyTakenException);
        }
    }

    @Test
    public void shouldAddCompany() {
        //given
        int startSize = CompanyRegistry.getInstance().getCompanies().size();

        //when
        try {
            CompanyController.createCompany("Drutex", 2000, "234325353");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NipAlreadyTakenException e) {
            e.printStackTrace();
        }
        int endSize = CompanyRegistry.getInstance().getCompanies().size();

        //then
        Assert.assertEquals(startSize + 1, endSize);
    }

    @Test
    public void shouldThrowAccountantNotFoundExceptionWhenAssignAccountantToCompany() {
        //given
        String nipNumber = "1235Test";
        String accountantLogin = "FranekTest";

        //when
        try {
            CompanyController.createCompany("SDA", 2017, nipNumber);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NipAlreadyTakenException e) {
            e.printStackTrace();
        }

        //then
        try {
            CompanyController.assignAccountantToCompany(nipNumber, accountantLogin);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof AccountantNotFoundException);
        }

    }

    @Test
    public void shouldThrowAccountantAlreadyAssignedExceptionWhenAssignAccountantToCompany() throws AccountantAlreadyExistsException {
        //given
        String nipNumber = "1235Test2";
        String accountantLogin = "FranekTest2";

        //when
        try {
            AccountantRegistry.getInstance().addAccountant(accountantLogin, "123");
            CompanyController.createCompany("SDA", 2017, nipNumber);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NipAlreadyTakenException e) {
            e.printStackTrace();
        }

        //then
        try {
            CompanyController.assignAccountantToCompany(nipNumber, accountantLogin);
            CompanyController.assignAccountantToCompany(nipNumber, accountantLogin);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof AccountantAlreadyAssignedException);
        }

    }


    @Test
    public void removeCompanyFromDatabase() {
        //given
        int startSize = CompanyRegistry.getInstance().getCompanies().size();

        //when
        try {
            CompanyController.createCompany("Drutex", 2000, "012");
            CompanyController.removeCompanyFromDatabase("012");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NipAlreadyTakenException);
        }
        int endSize = CompanyRegistry.getInstance().getCompanies().size();

        //then
        Assert.assertEquals(startSize, endSize);
    }

    @Test
    public void shoudThrowCompanyNotFoundExceptionWhenRemoveCompany() {
        try {
            CompanyController.createCompany("Drutex", 2000, "012");
            CompanyController.removeCompanyFromDatabase("012");
            CompanyController.removeCompanyFromDatabase("012");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof CompanyNotFoundException);
        }
    }

    @Test
    public void changeCompanyName() {
        //given
        String nipNumber = "123";
        String newName;
        try {
            CompanyController.createCompany("Drutex", 2000, nipNumber);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NipAlreadyTakenException e) {
            e.printStackTrace();
        }

        //when
        newName = "Srutex";
        try {
            CompanyController.changeCompanyName(nipNumber, newName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CompanyNotFoundException e) {
            e.printStackTrace();
        }
        Company company = CompanyRegistry.getInstance().getCompanyByNipNumber(nipNumber);

        //then
        Assert.assertEquals(company.getName(), newName);
    }

    @Test
    public void changeCompanyNip() {
        //given
        String oldNipNumber = "123";
        String newNipNumber = "321";
        try {
            CompanyController.createCompany("Drutex", 2000, oldNipNumber);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NipAlreadyTakenException e) {
            e.printStackTrace();
        }

        //when
        try {
            CompanyController.changeCompanyNip(oldNipNumber, newNipNumber);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CompanyNotFoundException e) {
            e.printStackTrace();
        } catch (NipAlreadyTakenException e) {
            e.printStackTrace();
        }
        Company company = CompanyRegistry.getInstance().getCompanyByNipNumber(newNipNumber);

        //then
        Assert.assertEquals(company.getNipNumber(), newNipNumber);

    }

}