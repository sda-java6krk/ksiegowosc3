package pl.sdacademy.controllers;

import org.junit.Assert;
import org.junit.*;
import pl.sdacademy.exceptions.NipAlreadyTakenException;
import pl.sdacademy.models.CompanyRegistry;

import java.io.IOException;

public class CompanyControllerTest {

    @Test
    public void shouldThrowExceptionWhenNipIsAlreadyTakenWhenCreateCompany()  {
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
            CompanyController.createCompany(name,yearFound,nipNumber);
            CompanyController.createCompany(name,yearFound,nipNumber);
        } catch (Exception e) {
            Assert.assertTrue(e instanceof NipAlreadyTakenException);
        }
    }

    @Test
    public void shouldAddCompany(){
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
    public void listCompanies() {
    }

    @Test
    public void assignAccountantToCompany() {
    }

    @Test
    public void loadCompaniesFromFile() {
    }

    @Test
    public void removeCompanyFromDatabase() {
    }

    @Test
    public void changeCompanyName() {
    }

    @Test
    public void changeCompanyNip() {
    }

}