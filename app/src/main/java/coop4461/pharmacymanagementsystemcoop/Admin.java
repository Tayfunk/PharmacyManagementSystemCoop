package coop4461.pharmacymanagementsystemcoop;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        ActionBar ab =getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setIcon(R.mipmap.ic_launcher);

        final Button b1=(Button) findViewById(R.id.button1);
        final Button b2=(Button) findViewById(R.id.button2);
        final Button b3=(Button) findViewById(R.id.button3);
        final Button b4=(Button) findViewById(R.id.button4);
        final Button b5=(Button) findViewById(R.id.button5);
        final Button b6=(Button) findViewById(R.id.button6);
        final Button b7=(Button) findViewById(R.id.button7);

        final EditText et1=(EditText)findViewById(R.id.editText1);
        final EditText et2=(EditText)findViewById(R.id.editText2);
        final EditText et3=(EditText)findViewById(R.id.editText3);
        final EditText et4=(EditText)findViewById(R.id.editText4);
        final EditText et5=(EditText)findViewById(R.id.editText5);

        final DBAdapter db = new DBAdapter(Admin.this);

        //---Ekleme---
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                db.open();
                db.insertContact(et1.getText().toString(), et2.getText().toString(),
                        et3.getText().toString(),et4.getText().toString());
                db.close();
                Toast.makeText(getBaseContext(), "Inserted",
                        Toast.LENGTH_SHORT).show();


            }
        });

        //---Dataları Gösterme---
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                db.open();
                Cursor c = db.getAllContacts();
                if (c.moveToFirst())
                {
                    do {
                        DisplayContact(c);
                    } while (c.moveToNext());
                }
                db.close();
            }

            private void DisplayContact(Cursor c)
            {
                // TODO Auto-generated method stub
                Toast.makeText(getBaseContext(), "Product Number: " + c.getString(0) + "\n" + "Manufacturer: " + c.getString(1) + "\n" +
                                "Drug Name: " + c.getString(2) + "\n" + "Total Stock: " + c.getString(3),
                        Toast.LENGTH_LONG).show();
            }
        });

        //---Primary Key'e Göre Data Gösterme---
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                db.open();
                Cursor c = db.getContact(Integer.parseInt
                        (et1.getText().toString()));
                if (c.moveToFirst())
                    DisplayContact(c);

                else
                    Toast.makeText(getBaseContext(), "No drug found",
                            Toast.LENGTH_LONG).show();
                db.close();
            }

            private void DisplayContact(Cursor c) {
                // TODO Auto-generated method stub
                Toast.makeText(getBaseContext(), "Product Number: " + c.getString(0) +
                                "\n" + "Manufacturer: " + c.getString(1) + "\n" +
                                "Drug Name: " + c.getString(2) + "\n" + "Total Stock: " + c.getString(3),
                        Toast.LENGTH_LONG).show();
            }
        }) ;

        //---Güncelleme---
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                db.open();
                if (db.updateContact
                        (Integer.parseInt(et1.getText().toString()),
                                et2.getText().toString(), et3.getText().toString(),et4.getText().toString()))

                    Toast.makeText(getBaseContext(), "Update successful.", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getBaseContext(), "Update failed.",
                            Toast.LENGTH_LONG).show();
                db.close();
            }
        });



        //---Silme---
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                db.open();
                db.deleteContact(Integer.parseInt(et1.getText().toString()));
                db.close();
                Toast.makeText(getBaseContext(), "Deleted",
                        Toast.LENGTH_SHORT).show();
            }
        });


        //---Stok Güncelleme---
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            
            db.open();
                db.updateBirim(Long.parseLong(et1.getText().toString()), et4.getText().toString());
                db.close();
                Toast.makeText(getBaseContext(), "Total Stock Updated",
                        Toast.LENGTH_SHORT).show();
            }
        });



        //---Stok Düşürme---
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sayi1= Integer.parseInt(et4.getText().toString());
                int sayii= Integer.parseInt(et5.getText().toString());

                db.open();
                db.updateAzalan(Long.parseLong(et1.getText().toString()),sayi1,sayii );
                db.close();
                Toast.makeText(getBaseContext(), "Stock Decreased",
                        Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}