package coop4461.pharmacymanagementsystemcoop;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class Display extends AppCompatActivity {
    EditText ETSearch;
    Button Bsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        ActionBar ab =getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setIcon(R.mipmap.ic_launcher);

        String username = getIntent().getStringExtra("Username");
        TextView tv = (TextView) findViewById(R.id.TVusername);
        final ListView lst_drug = (ListView) findViewById(R.id.lst_Drugs);

        tv.setText(username);

        ETSearch = (EditText) findViewById(R.id.Search_db);
        final DBAdapter dbsearch = new DBAdapter(Display.this);
        Bsearch = (Button) findViewById(R.id.button_search);

        Bsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBAdapter.DatabaseHelper dbh = new DBAdapter.DatabaseHelper(Display.this);
                SQLiteDatabase db = dbh.getReadableDatabase();

                String query = "Select email, name , _id,birim from contacts where email like '" + ETSearch.getText().toString() + "%'";
                Cursor cur =  db.rawQuery(query, null);

                DrugListingAdapter adp = new DrugListingAdapter(Display.this, cur);
                lst_drug.setAdapter(adp);
            }
        });

    }
}
