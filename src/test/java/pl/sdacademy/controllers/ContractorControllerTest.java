package pl.sdacademy.controllers;

import org.junit.*;

public class ContractorControllerTest {

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenNipHasNotEnoughDigits() {
        // given
        String name;
        String nip;

        // when
        name = "zenek benek";
        nip = "6";

        // then
        ContractorController.addContractor(name, nip);
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenNipHasTooManyDigits() {
        // given
        String name;
        String nip;

        // when
        name = "zenek franek";
        nip = "6666666666666666666666";

        // then
        ContractorController.addContractor(name, nip);
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenNipContainsCharactersOtherThanDigits() {
        // given
        String name;
        String nip;

        // when
        name = "zenek franek";
        nip = "dziesliter";

        // then
        ContractorController.addContractor(name, nip);
    }

    @Test
    public void shouldCreateContractorWhenNipIsValid() {
        //given
        String name = "Test Name";
        String nip;

        //when
        nip = "8681940575";
        ContractorController.addContractor(name, nip);

        //then
        Assert.assertTrue(ContractorController.isContractorAlreadyCreated(nip));


        //when
        nip = "5261005022";
        ContractorController.addContractor(name, nip);

        //then
        Assert.assertTrue(ContractorController.isContractorAlreadyCreated(nip));
    }

    @Test
    public void shouldCreateContractorWhenNipIsInWeirdFormat() {
        //given
        String name = "Test Name";
        String nip;

        //when
        nip = "526-16-45.000";
        ContractorController.addContractor(name, nip);

        //then
        Assert.assertTrue(ContractorController.isContractorAlreadyCreated(nip));
    }

}
