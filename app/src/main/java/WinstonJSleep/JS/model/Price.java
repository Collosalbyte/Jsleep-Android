package WinstonJSleep.JS.model;

/**
 * A class representing a price, including the base price and a discount amount.
 * @author Michael Winston Tjahaja
 * @version 10/12/2022
 */

public class Price {
    /**
     * The base price, and discounts that are applicable
     */
    public double price;
    public double discount;

    /**
     * Creates a new Price with the specified base price.
     */

    public Price (double price) {
        this.price = price;
    }

    /**
     * Creates a new Price with the specified base price and discount amount.
     */

    public Price (double price, double discount) {
        this.price = price;
        this.discount = discount;
    }

    /**
     * Returns a string representation of the price, including the base price and discount amount.
     */
    public String toString ()
    {
        return("Price: " + price + " Discount: " + discount);
    }
}
