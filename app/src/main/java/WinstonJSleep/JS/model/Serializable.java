package WinstonJSleep.JS.model;

import java.util.HashMap;

public class Serializable implements Comparable<Serializable>
{
    public final int id;
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();

    protected Serializable()
    {
        Integer temp = mapCounter.get(getClass());
        if (temp == null) temp = 0;
        else temp += 1;

        id = temp;
        System.out.print ("ID: " + id);
        mapCounter.put(getClass(), temp);
    }

    public boolean equals (Object obj){
        if (obj instanceof Serializable) {
            Serializable objVar = (Serializable) obj;
            return objVar.id == id;
        }
        return false;
    }

    public boolean equals (Serializable serial){
        return serial.id == id;
    }

    public int compareTo (Serializable otherVar){
        return Integer.compare (id, otherVar.id);
    }


    public static <T> Integer getClosingId (Class<T> klas){
        return mapCounter.get(klas);
    }

    public static <T> Integer setClosingId (Class<T> klas, int id){
        return mapCounter.put(klas, id);
    }

}
