package WinstonJSleep.JS.model;

import WinstonJSleep.JS.model.Serializable;

import java.util.ArrayList;
import java.util.Date;

public class Room extends Serializable
{
    public int size;

    public int accountId;
    public String name;
    public Facility facility;
    public Price price;
    public BedType bedtype;
    public City city;
    public String address;

    public ArrayList<Date> booked = new ArrayList<Date>();

    public Room (String name, int accountId, int size, Price price, Facility facility, City city, String address)
    {
        this.size = size;
        this.name = name;
        this.price = price;
        this.facility = facility;
        this.city = city;
        this.address = address;
        bedtype = BedType.QUEEN;
        this.accountId = accountId;
    }

    public String toString ()
    {
        return("Size: " + size + " Name: " + name + " Price: " + price + " Facility: " + facility +
                " City: " + city + " Address: " + address + " Bed Type: " + bedtype);
    }

    public Object write(){
        return null;
    }

    public boolean read (String a) {
        return false;
    }

}