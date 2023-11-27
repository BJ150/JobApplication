package com.example.user.jobapplicationportal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class recuiterdashboard extends AppCompatActivity {
Button addjobs,logout, postedjob,applicantR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuiter_dash_board);
        addjobs=(Button)findViewById(R.id.addjob);
        logout=(Button)findViewById(R.id.logout);
        postedjob=(Button)findViewById(R.id.viewpostedjob);
        applicantR=(Button)findViewById(R.id.applicantR);

        applicantR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent applicant=new Intent(recuiterdashboard.this,applicants.class);
                startActivity(applicant);
            }
        });

        postedjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent posted=new Intent(recuiterdashboard.this,postedjob.class);
                startActivity(posted);
            }
        });
        addjobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add=new Intent(recuiterdashboard.this,addingjobs.class);
                startActivity(add);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertbox();

            }
        });

    }
    public void alertbox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(recuiterdashboard.this);
        builder.setTitle("Notification");
        builder.setIcon(R.drawable.alert);
        builder.setMessage("Are you sure do you want to logout?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent log=new Intent(recuiterdashboard.this,landingpage.class);
                startActivity(log);
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
