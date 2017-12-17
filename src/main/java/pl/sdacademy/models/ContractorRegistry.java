package pl.sdacademy.models;

import java.util.ArrayList;

public class ContractorRegistry {

    private static ContractorRegistry instance = null;
    public static ContractorRegistry getInstance() {
        if(instance == null) {
            instance = new ContractorRegistry();
        }
        return instance;
    }

    private ArrayList<Contractor> contractors;

    private ContractorRegistry() {
        this.contractors = new ArrayList<>();
    }

    public void addContractor(String name, String nip) {
        this.contractors.add(new Contractor(name, nip));
    }

    public boolean isValidNip(String nip) {
        if (nip.contains("[a-zA-Z]+") || nip.length() != 10) return false;
        int[] weights = {6, 5, 7, 2, 3, 4, 5, 6, 7};
        int sum = 0;
        for (int i = 0; i<9; ++i) sum += weights[i] * (nip.charAt(i) - '0');
        return ((sum%11)==(nip.charAt(9) - '0'));
    }


    public boolean isContractorAlreadyCreated(String nip) {
        return (getContractorByNip(nip) != null);
    }

    public Contractor getContractorByNip(String nip) {
        for (Contractor c : contractors) {
            if (c.getNip().equals(nip)) return c;
        }
        return null;
    }

    public String normalizeNip(String nip) {
        return nip.replaceAll("[^0-9]","");
    }
}
