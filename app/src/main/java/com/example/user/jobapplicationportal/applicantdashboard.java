package com.example.user.jobapplicationportal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class applicantdashboard extends AppCompatActivity {
Button applicantLogout, applicantsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_dashboard);

        applicantLogout=(Button)findViewById(R.id.logoutA);
        applicantsearch =(Button)findViewById(R.id.applicantsearch);
        applicantsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search=new Intent(applicantdashboard.this,searchjob.class);
                startActivity(search);
            }
        });
        applicantLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertbox();
            }
        });

    }
    public void alertbox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(applicantdashboard.this);
        builder.setTitle("Notification");
        builder.setIcon(R.drawable.alert);
        builder.setMessage("Are you sure do you want to Log out?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent logout= new Intent(applicantdashboard.this,landingpage.class);
                startActivity(logout);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
    }
}
