package pl.sdacademy.models;

public interface CredentialsValidation {
    default boolean validatePassword(String password) {
        if (password.length() < 1) return false;
        if (password.trim().equals("")) return false; // Check for case where length>1, but password is spaces-only
        if (password.contains(";")) return false; // Not the best idea; ideally password would be hashed somehow

        return true;
    }

    default boolean validateLogin(String login) {
        if (login.length() < 1) return false;
        if (login.trim().equals("")) return false; // Check for case where length>1, but password is spaces-only
        if (login.contains(";")) return false;
        return true;
    }
}
