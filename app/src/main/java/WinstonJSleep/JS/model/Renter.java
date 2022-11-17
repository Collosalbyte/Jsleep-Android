package WinstonJSleep.JS.model;

import WinstonJSleep.JS.model.Serializable;

import java.util.regex.*;

public class Renter extends Serializable
{
    public String phoneNumber;
    public String address;
    public String username;
    final public static String REGEX_NAME = "^[A-Z][A-Za-z0-9_]{4,20}$";
    final public static String REGEX_PHONE = "^\\d{9,12}$";

    public Renter(String username, String phoneNumber, String address)
    {
        this.username = username;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

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
