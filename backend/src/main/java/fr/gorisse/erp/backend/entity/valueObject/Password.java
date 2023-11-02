package fr.gorisse.erp.backend.entity.valueObject;

import fr.gorisse.erp.backend.exceptions.InvalidInput;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password  {
    private final String password;

    private Password(String password) {
        this.password = password; //TODO Add encryption
    }

    public static Password create(String password){
        final int maxSize = 30;
        if (password.length() > maxSize) {
            throw new InvalidInput(" Password must be less than "+maxSize+" characters ");
        }
        else{
            return new Password(password);
        }
    }
    private String encryption(String password)
    {
        MessageDigest m5;
        try {
            m5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        m5.update(password.getBytes());
        byte[] bytes = m5.digest();
        StringBuilder s = new StringBuilder();

        for (byte aByte : bytes) {
            s.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return s.toString();
    }
    public String toString(){
        return this.password;
    }



}
