package coop4461.pharmacymanagementsystemcoop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUp extends AppCompatActivity {
    Button MainButton;

    DataBaseHelper helper = new DataBaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        MainButton =(Button) findViewById(R.id.MainButton);
        MainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent zl=new Intent(SignUp.this,MainActivity.class);
                startActivity(zl);
            }
        });
    }

    public void onSignUpClick(View v)
    {
        if(v.getId()== R.id.Bsignupbutton)
        {
            EditText name = (EditText)findViewById(R.id.TFname);
            EditText email = (EditText)findViewById(R.id.TFemail);
            EditText uname = (EditText)findViewById(R.id.TFuname);
            EditText pass1 = (EditText)findViewById(R.id.TFpass1);
            EditText pass2 = (EditText)findViewById(R.id.TFpass2);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            //---Kayıt Alanında Boş Bırakılan Yerleri Sorgulama---
            if(namestr.equals(""))
            {
                Toast tname = Toast.makeText(SignUp.this, "Name Empty !", Toast.LENGTH_SHORT);
                tname.show();
            }

            else if(emailstr.equals(""))
            {
                Toast temail = Toast.makeText(SignUp.this, "E-mail Empty !", Toast.LENGTH_SHORT);
                temail.show();
            }

            else if(unamestr.equals(""))
            {
                Toast tuse = Toast.makeText(SignUp.this, "Username Empty !", Toast.LENGTH_SHORT);
                tuse.show();
            }

            else if(pass1str.equals(""))
            {
                Toast tpass = Toast.makeText(SignUp.this, "Password Empty!", Toast.LENGTH_SHORT);
                tpass.show();
            }

            else if(!pass1str.equals(pass2str))
            {
                //popup msg
                Toast pass = Toast.makeText(SignUp.this, "Passwords don't match!", Toast.LENGTH_SHORT);
                pass.show();
            }

            else
            {
                //---Database Verileri Kaydetme---
                Contact c = new Contact();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUname(unamestr);
                c.setPass(pass1str);
                helper.insertContact(c);

                //---Popup Mesaj Göstermek---
                Toast success = Toast.makeText(SignUp.this, "Success", Toast.LENGTH_SHORT);
                success.show();
            }
        }
    }
}
