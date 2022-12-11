package WinstonJSleep.JS.model;

import WinstonJSleep.JS.model.Serializable;

import java.util.regex.*;

/**
 * A class representing a renter, including their username, phone number, and address.
 * @author Michael Winston Tjahaja
 * @version 10/12/2022
 */

public class Renter extends Serializable
{
    /**
     * Renter's phone number, address, phone number
     */

    public String phoneNumber;
    public String address;
    public String username;

    /**
     * The regular expression pattern for a valid username.
     */
    final public static String REGEX_NAME = "^[A-Z][A-Za-z0-9_]{4,20}$";
    final public static String REGEX_PHONE = "^\\d{9,12}$";

    /**
     * The regular expression pattern for a valid phone number.
     */

    public Renter(String username, String phoneNumber, String address)
    {
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Validates the renter's username and phone number against the regular expression patterns.
     */

    public boolean validate(){
        Pattern namaPengguna = Pattern.compile(REGEX_NAME);
        Pattern nomorHp = Pattern.compile(REGEX_PHONE);

        if(username == null || phoneNumber == null)
            return false;

        Matcher matchedName = namaPengguna.matcher(username);
        Matcher matchedPhone = nomorHp.matcher(phoneNumber);

        return (matchedName.matches() && matchedPhone.matches());
    }
}
