package WinstonJSleep.JS.model;

import java.util.HashMap;

/**
 * A base class that can be serialized to a file.
 * @author Michael Winston Tjahaja
 * @version 10/12/2022
 */

public class Serializable implements Comparable<Serializable>
{
    /**
     * The unique ID of this object
     */
    public final int id;

    /**
     * The map counters for each class that extends Serializable
     */

    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();

    /**
     * Creates a new Serializable object
     */

    protected Serializable()
    {
        Integer temp = mapCounter.get(getClass());
        if (temp == null) temp = 0;
        else temp += 1;

        id = temp;
        System.out.print ("ID: " + id);
        mapCounter.put(getClass(), temp);
    }

    /**
     * Returns whether the given `Serializable` object is equal to this `Serializable` object.
     */

    public boolean equals (Object obj){
        if (obj instanceof Serializable) {
            Serializable objVar = (Serializable) obj;
            return objVar.id == id;
        }
        return false;
    }

    /**
     * Compares this `Serializable` object to another `Serializable` object.
     */

    public int compareTo (Serializable otherVar){
        return Integer.compare (id, otherVar.id);
    }

    /**
     * Returns the closing identifier for the specified class.
     */

    public static <T> Integer getClosingId (Class<T> klas){
        return mapCounter.get(klas);
    }

    /**
     * Sets the closing identifier for the specified class.
     */

    public static <T> Integer setClosingId (Class<T> klas, int id){
        return mapCounter.put(klas, id);
    }

}
