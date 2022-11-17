package WinstonJSleep.JS.model;

public class Price {
    public double price;
    public double discount;

    public Price (double price) {
        this.price = price;
    }

    public Price (double price, double discount) {
        this.price = price;
        this.discount = discount;
    }

    public String toString ()
    {
        return("Price: " + price + " Discount: " + discount);
    }
}
