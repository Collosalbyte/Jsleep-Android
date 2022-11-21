package WinstonJSleep.JS;

import android.content.Context;
import android.icu.text.CaseMap;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import WinstonJSleep.JS.model.Room;

public class itemList extends ArrayAdapter<Room> {

    public itemList (Context context, ArrayList<Room> room) {
        super(context, 0, room);
    }

}
