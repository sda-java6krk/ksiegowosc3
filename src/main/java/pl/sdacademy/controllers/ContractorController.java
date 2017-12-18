package pl.sdacademy.controllers;

import pl.sdacademy.models.Contractor;
import pl.sdacademy.models.ContractorRegistry;

public class ContractorController {
    public static void addContractor(String name, String nip) {
        nip = ContractorRegistry.getInstance().normalizeNip(nip);
        if (!ContractorRegistry.getInstance().isValidNip(nip)) {
            throw new IllegalArgumentException("Niepoprawny NIP!");
        }
        ContractorRegistry.getInstance().addContractor(name, nip);
    }

    public static Contractor getContractorByNip(String nip) {
        nip = ContractorRegistry.getInstance().normalizeNip(nip);
        return ContractorRegistry.getInstance().getContractorByNip(nip);
    }

    public static boolean isContractorAlreadyCreated(String nip) {
        nip = ContractorRegistry.getInstance().normalizeNip(nip);
        return ContractorRegistry.getInstance().isContractorAlreadyCreated(nip);
    }


}
