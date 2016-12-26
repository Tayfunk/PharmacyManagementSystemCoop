package coop4461.pharmacymanagementsystemcoop;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class DrugListingAdapter extends BaseAdapter {

    private Cursor cursor;
    private Context context;

    public DrugListingAdapter(Context context, Cursor cursor) {
        this.cursor = cursor;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        if (cursor.moveToPosition(position))
        {
            return "Drug Name : "+cursor.getString(0)+ "   " + "Manufacturer :  " + cursor.getString(1)+"   " +"Product Number : " +cursor.getString(2)
                    +"   "+"Total Stock :  "+cursor.getString(3);
        }
        else
        {
            return null;

        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView tv = new TextView(context);
        tv.setTextSize(15.0f);
        tv.setText(getItem(position).toString());

        return tv;
    }
}
