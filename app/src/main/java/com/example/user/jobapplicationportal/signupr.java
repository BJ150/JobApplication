package com.example.user.jobapplicationportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signupr extends AppCompatActivity {
    TextView Log,singup;
    EditText Fname,Lname,SignupEmail,password,repassword;
    CheckBox checkBox1;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupr);
        Log=(TextView)findViewById(R.id.SPlogin);
        singup=(Button)findViewById(R.id.BTSingup);
        Fname=(EditText)findViewById(R.id.Fname);
        Lname=(EditText)findViewById(R.id.Lname);
        SignupEmail=(EditText)findViewById(R.id.SingupEmail);
        password=(EditText)findViewById(R.id.password);
        repassword=(EditText)findViewById(R.id.repassword);
        checkBox1=(CheckBox)findViewById(R.id.Terms);
        DB=new DBHelper(this);
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=SignupEmail.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();
                String Fname1=Fname.getText().toString();
                String Lname1=Lname.getText().toString();
                //String chk=checkBox1.getText().toString();

                if (user.equals("")||pass.equals("")||repass.equals("")||Fname1.equals("")||Lname1.equals("")|| !checkBox1.isChecked())
                {Toast.makeText(signupr.this, "Please Enter all the field requirements", Toast.LENGTH_SHORT).show();}

                else{
                    if (pass.equals(repass)){
                        Boolean checkuser=DB.checkusernameadmin(user);

                        if (checkuser==false){
                            Boolean insert=DB.insertadmin(user,pass);


                            if (insert==true){
                                Toast.makeText(signupr.this, "Registered succesfully", Toast.LENGTH_SHORT).show();
                                Intent intent2=new Intent(getApplicationContext(),loginpageRecuiter.class);
                                startActivity(intent2);
                            }

                            else {
                                Toast.makeText(signupr.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(signupr.this, "Email already Registered", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(signupr.this, "Password not match?", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

        Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lg=new Intent(signupr.this,loginpageRecuiter.class);
                startActivity(lg);
            }
        });
    }
}
