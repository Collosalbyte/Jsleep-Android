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

    @Override
    public String toString ()
    {
        return"Account{" +
                "balance=" + balance +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", renter=" + renter +
                '}';
    }


}

