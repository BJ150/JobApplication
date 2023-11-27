package com.example.user.jobapplicationportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginpageRecuiter extends AppCompatActivity {
    TextView Signup;
    Button Log;
    DBHelper DB;
    EditText LogEmail,LogPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage_recuiter);
        Signup=(TextView)findViewById(R.id.Signup);
        LogEmail=(EditText)findViewById(R.id.RLogEmail);
        LogPass=(EditText)findViewById(R.id.RLogPass);
        DB=new DBHelper(this);
        Log=(Button) findViewById(R.id.RLoginPage);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rsign=new Intent(getApplicationContext(),signupr.class);
                startActivity(rsign);
            }
        });

        Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Logemail=LogEmail.getText().toString();
                String Logpasss=LogPass.getText().toString();
                if (Logemail.equals("")|| Logpasss.equals(""))
                    Toast.makeText(loginpageRecuiter.this, "Input email/Password", Toast.LENGTH_SHORT).show();
                else {
                    //Boolean checkemailpass=DB.checkusernamepassword(Logemail,Logpasss);

                    Boolean admin=DB.adminpass(Logemail,Logpasss);
                   if (admin==true){
                        Toast.makeText(loginpageRecuiter.this, "Sign in succefully", Toast.LENGTH_SHORT).show();
                        Intent admin1=new Intent(getApplicationContext(),recuiterdashboard.class);
                        startActivity(admin1);
                    }
                    /*if (checkemailpass==true){
                        Toast.makeText(loginpageRecuiter.this, "Sign in succefully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),applicantdashboard.class);
                        startActivity(intent);
                    }*/
                    else {
                        Toast.makeText(loginpageRecuiter.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });
    }
}
