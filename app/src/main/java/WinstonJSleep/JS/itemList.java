package WinstonJSleep.JS;

import android.content.Context;
import android.icu.text.CaseMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import WinstonJSleep.JS.model.Room;

public class itemList extends ArrayAdapter<Room> {

    public itemList (Context context, ArrayList<Room> room) {
        super(context, 0, room);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Room room = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.thisitemlist, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.topLeft);
        name.setText(room.name);
        return convertView;
    }
}
