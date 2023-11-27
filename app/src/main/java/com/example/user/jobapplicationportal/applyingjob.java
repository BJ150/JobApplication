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

public class applyingjob extends AppCompatActivity {
 Button appback,apply;
    TextView appjobname,appjobdessum,appjobskill,appjobcategory,appjobsalary,appjobpositon;
    EditText appemail,appexperience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applyingjob);

        appback=(Button)findViewById(R.id.applyingback);
        apply=(Button)findViewById(R.id.apply);

        appjobname=(TextView)findViewById(R.id.appjobname);
        appjobdessum=(TextView)findViewById(R.id.appjobdessum);
        appjobskill=(TextView)findViewById(R.id.appskill);
        appjobcategory=(TextView)findViewById(R.id.appcategory);
        appjobsalary=(TextView)findViewById(R.id.appsalary);
        appjobpositon=(TextView)findViewById(R.id.appjobposition);

        appemail=(EditText)findViewById(R.id.applicantEmail);
        appexperience=(EditText)findViewById(R.id.appexperience);

        Intent i =getIntent();

        //String t1=i.getStringExtra("id").toString();
        String t2=i.getStringExtra("jobdescription").toString();
        String t3=i.getStringExtra("jobsum").toString();
        String t4=i.getStringExtra("jobposition").toString();
        String t5=i.getStringExtra("jobsalary").toString();
        String t6=i.getStringExtra("category").toString();
        String t7=i.getStringExtra("jobskill").toString();

        //numberofJob.setText(t1);
        appjobname.setText(t2);
        appjobdessum.setText(t3);
        appjobpositon.setText(t4);
        appjobsalary.setText(t5);
        appjobcategory.setText(t6);
        appjobskill.setText(t7);


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertbox();

            }
        });
        appback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(getApplicationContext(),searchjob.class);
                startActivity(b);
            }
        });

    }

    public void applyjob()  {

        try {

            String apemail=appemail.getText().toString();
            String apexp=appexperience.getText().toString();


            //if(jname.equals(" ")||jsum.equals(" ")||jpos.equals(" ")||jsal.equals(" ")||jcat.equals(" ")||jskill.equals(" "))
            //Toast.makeText(addingjobs.this, "Please Enter all the field requirements", Toast.LENGTH_SHORT).show();

            SQLiteDatabase insertjob= openOrCreateDatabase("job", Context.MODE_PRIVATE,null);
            insertjob.execSQL("CREATE TABLE IF NOT EXISTS applicant (id INTEGER PRIMARY KEY AUTOINCREMENT,applicantemail,applicantexp )");

            String sql="insert into applicant (applicantemail,applicantexp) values (?,?)";
            SQLiteStatement statement= insertjob.compileStatement(sql);
            statement.bindString(1,apemail);
            statement.bindString(2,apexp);
            statement.execute();

            Toast.makeText(applyingjob.this, "Successfully applied", Toast.LENGTH_SHORT).show();



        } catch (Exception ex) {
            Toast.makeText(applyingjob.this, "Enter all the field requirementss", Toast.LENGTH_SHORT).show();

        }
    }
    public void alertbox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(applyingjob.this);
        builder.setTitle("Notification");
        builder.setIcon(R.drawable.alert);
        builder.setMessage("Are you sure do you want to apply this job?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                applyjob();
                Intent b=new Intent(getApplicationContext(),searchjob.class);
                startActivity(b);
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
