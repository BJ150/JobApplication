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
import android.widget.Toast;

public class addingjobs extends AppCompatActivity {
    Button postjob ,back;
    EditText jobname, jobsum, jobposition, jobsalary, jobcategory, jobskillreq;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addingjobs);
        postjob = (Button) findViewById(R.id.Postjob);
        back = (Button) findViewById(R.id.Back1);
        jobname = (EditText) findViewById(R.id.jobname);
        jobsum = (EditText) findViewById(R.id.jobdesum);
        jobposition = (EditText) findViewById(R.id.jobposition);
        jobsalary = (EditText) findViewById(R.id.jobsalary);
        jobcategory = (EditText) findViewById(R.id.jobcategory);
        jobskillreq = (EditText) findViewById(R.id.jobskillreq);
        DB = new DBHelper(this);

        postjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertbox();
            }
        });

         back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent b=new Intent(addingjobs.this,recuiterdashboard.class);
                 startActivity(b);
             }
         });

    }

    public void alertbox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(addingjobs.this);
        builder.setTitle("Notification");
        builder.setIcon(R.drawable.alert);
        builder.setMessage("Are you sure about this?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                insertjob();
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
   public void insertjob() {

        try {

            String jname=jobname.getText().toString();
            String jsum=jobsum.getText().toString();
            String jpos=jobposition.getText().toString();
            String jsal=jobsalary.getText().toString();
            String jcat=jobcategory.getText().toString();
            String jskill=jobskillreq.getText().toString();



            SQLiteDatabase insertjob= openOrCreateDatabase("job", Context.MODE_PRIVATE,null);
            insertjob.execSQL("CREATE TABLE IF NOT EXISTS posted_job (id INTEGER PRIMARY KEY AUTOINCREMENT, jobdescription VARCHAR, jobsum VARCHAR, jobposition VARCHAR, jobsalary INTEGER, category VARCHAR, jobskill VARCHAR)");

            String sql="insert into posted_job (jobdescription, jobsum, jobposition, jobsalary, category, jobskill) values (?,?,?,?,?,?)";
            SQLiteStatement statement= insertjob.compileStatement(sql);
            statement.bindString(1,jname);
            statement.bindString(2,jsum);
            statement.bindString(3,jpos);
            statement.bindString(4,jsal);
            statement.bindString(5,jcat);
            statement.bindString(6,jskill);
            statement.execute();

            Toast.makeText(addingjobs.this, "Jobposted Succesfully", Toast.LENGTH_SHORT).show();
            Intent g=new Intent(addingjobs.this,recuiterdashboard.class);
            startActivity(g);


        } catch (Exception ex) {
            Toast.makeText(addingjobs.this, "Enter all the field requirementshahahah", Toast.LENGTH_SHORT).show();

    }
}
}