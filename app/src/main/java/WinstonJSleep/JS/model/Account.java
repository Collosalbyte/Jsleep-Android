package WinstonJSleep.JS.model;

import WinstonJSleep.JS.model.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class representing an account, including the user's name, email, password, renter information, and balance.
 *
 * @author Michael Winston Tjahaja
 * @version 10/12/2022
 */

public class Account extends Serializable
{
    /**
     * The user's name, email address, password, renter information if available, and balance.
     */

    public String name;
    public String email;
    public String password;
    public Renter renter;
    public double balance;

    /**
     * Returns a string representation of the account, including the user's name, email, password, renter information, and balance.
     */
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

