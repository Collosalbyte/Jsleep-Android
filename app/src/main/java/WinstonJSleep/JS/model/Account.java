package WinstonJSleep.JS.model;

import WinstonJSleep.JS.model.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Account berfungsi untuk menginput nama, email, dan password dari penginput data
 *
 * @author Michael Winston Tjahaja
 * @version 27/09/2022
 */

public class Account extends Serializable
{
    public String name;
    public String email;
    public String password;
    public Renter renter;
    public double balance;

    final public static String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z]+([.]?[A-Za-z]+)*\\.[A-Za-z]+$";

    final public static String REGEX_PASSWORD = "^(?=.*a-z)(?=.*A-Z)(?=.*0-9)[a-zA-Z0-9]{8,}$";

    public Account (String name, String email, String password)
    {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String toString ()
    {
        return("Name: " + name + " Email: " + email + " Password: " + password);
    }

    public boolean validate(){
        Pattern namaEmail = Pattern.compile(REGEX_EMAIL);
        Pattern namaPassword = Pattern.compile(REGEX_PASSWORD);

        if(email == null || password == null)
            return false;

        Matcher matchedEmail = namaEmail.matcher(email);
        Matcher matchedPassword = namaPassword.matcher(password);

        return (matchedEmail.matches() && matchedPassword.matches());
    }
}

