package pl.sdacademy.models;

public class Contractor {
    private String name;
    private String nip;

    public String getName() {
        return name;
    }

    public String getNip() {
        return nip;
    }

    public Contractor(String name, String nip) {
        this.name = name;
        this.nip = nip;
    }

}
