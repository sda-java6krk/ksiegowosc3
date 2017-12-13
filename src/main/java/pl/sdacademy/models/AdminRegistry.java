package pl.sdacademy.models;

import pl.sdacademy.exceptions.AdminNotFoundException;

import java.util.ArrayList;

/**
 * Created by marcin on 13.12.2017.
 */
public class AdminRegistry {
    private static AdminRegistry instance = null;

    public static AdminRegistry getInstance() {
        if(instance == null) {
            instance = new AdminRegistry();
        }
        return instance;
    }


    private ArrayList<Admin> admins;

    public AdminRegistry() {
        this.admins = new ArrayList<>();

        this.admins.add(new Admin("adam", "123"));
        this.admins.add(new Admin("ziutek", "456"));
    }


    public Admin findAdmin(String login, String password) throws AdminNotFoundException {
        for(Admin admin : admins) {
            if(admin.getLogin().equals(login) && admin.getPassword().equals(password)) {
                return admin;
            }
        }

        throw new AdminNotFoundException();
    }
}
