package WinstonJSleep.JS.model;

import WinstonJSleep.JS.model.Serializable;

import java.util.ArrayList;
import java.util.Date;

/**
 * A class representing a rental room, including its size, owner, name, facilities, price,
 * bed type, city, address, and booked dates.
 * @author Michael Winston Tjahaja
 * @version 10/12/2022
 */
public class Room extends Serializable
{
    /**
     * Size, ID, name, facility, price, bed type, city, and address of the room.
     */
    public int size;

    public int accountId;
    public String name;
    public ArrayList<Facility> facility = new ArrayList<Facility>();
    public Price price;
    public BedType bedtype;
    public City city;
    public String address;

    public ArrayList<Date> booked = new ArrayList<Date>();

    /**
     * Creates a new Room with the specified owner, name, size, price, facilities, city, address, and bed type.
     */

    public Room (int accountId, String name, int size, Price price, ArrayList<Facility> facility, City city, String address, BedType bedtype)
    {
        this.size = size;
        this.name = name;
        this.price = price;
        this.facility = facility;
        this.city = city;
        this.address = address;
        this.bedtype = bedtype;
        this.accountId = accountId;
    }

    /**
     * Returns a string representation of the Room, including its size, name, price, facilities, city, address, and bed type.
     */

    public String toString ()
    {
        return("Size: " + size + " Name: " + name + " Price: " + price + " Facility: " + facility +
                " City: " + city + " Address: " + address + " Bed Type: " + bedtype);
    }

    /**
     * Writes the Room to a file or other persistent storage.
     */

    public Object write(){
        return null;
    }

    /**
     * Reads the Room from a file or other persistent
     */

    public boolean read (String a) {
        return false;
    }

}