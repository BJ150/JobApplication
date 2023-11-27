package com.example.user.jobapplicationportal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class recuiterconfirmation extends AppCompatActivity {
    Button appback,apply,reject;
    TextView appjobname,appjobdessum,appjobskill,appjobcategory,appjobsalary,appjobpositon;
    EditText appemailR,appexperienceR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuiterconfirmation);

        appback=(Button)findViewById(R.id.applyingback);
        apply=(Button)findViewById(R.id.apply);
        reject=(Button)findViewById(R.id.reject);

        appjobname=(TextView)findViewById(R.id.appjobname);
        appjobdessum=(TextView)findViewById(R.id.appjobdessum);
        appjobskill=(TextView)findViewById(R.id.appskill);
        appjobcategory=(TextView)findViewById(R.id.appcategory);
        appjobsalary=(TextView)findViewById(R.id.appsalary);
        appjobpositon=(TextView)findViewById(R.id.appjobposition);

        appemailR=(EditText)findViewById(R.id.applicantEmail);
        appexperienceR=(EditText)findViewById(R.id.appexperience);

        Intent i =getIntent();

        //String t1=i.getStringExtra("id").toString();
        String t2=i.getStringExtra("jobdescription").toString();
        String t3=i.getStringExtra("jobsum").toString();
        String t4=i.getStringExtra("jobposition").toString();
        String t5=i.getStringExtra("jobsalary").toString();
        String t6=i.getStringExtra("category").toString();
        String t7=i.getStringExtra("jobskill").toString();
        String t8=i.getStringExtra("applicantemail").toString();
        String t9=i.getStringExtra("applicantexp").toString();

        //numberofJob.setText(t1);
        appjobname.setText(t2);
        appjobdessum.setText(t3);
        appjobskill.setText(t4);
        appjobsalary.setText(t5);
        appjobcategory.setText(t6);
        appjobpositon.setText(t7);
        appemailR.setText(t8);
        appexperienceR.setText(t9);

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertbox();
            }
        });
        appback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(getApplicationContext(),recuiterdashboard.class);
                startActivity(b);
            }
        });
    }

    public void rejectjob() {

        try {
            String email =appemailR.getText().toString();



            //if(jname.equals(" ")||jsum.equals(" ")||jpos.equals(" ")||jsal.equals(" ")||jcat.equals(" ")||jskill.equals(" "))
            //Toast.makeText(addingjobs.this, "Please Enter all the field requirements", Toast.LENGTH_SHORT).show();

            SQLiteDatabase insertjob= openOrCreateDatabase("job", Context.MODE_PRIVATE,null);


            String sql="delete from applicant where applicantemail=?";
            SQLiteStatement statement= insertjob.compileStatement(sql);
            statement.bindString(1,email);
            statement.execute();

            Toast.makeText(recuiterconfirmation.this, "Application Deleted Succesfully", Toast.LENGTH_SHORT).show();
            Intent g=new Intent(recuiterconfirmation.this,recuiterdashboard.class);
            startActivity(g);


        } catch (Exception ex) {
            Toast.makeText(recuiterconfirmation.this, "Enter all the field requirements", Toast.LENGTH_SHORT).show();

        }
    }

    public void alertbox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(recuiterconfirmation.this);
        builder.setTitle("Notification");
        builder.setIcon(R.drawable.alert);
        builder.setMessage("Are you sure you want to reject this application?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rejectjob();
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
