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

public class editjob extends AppCompatActivity {
    Button Edit,Delete ,back;
    EditText jobname, jobsum, jobposition, jobsalary, jobcategory, jobskillreq,numberofJob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editjob);
        Edit = (Button) findViewById(R.id.Edit);
        Delete = (Button) findViewById(R.id.Delete);
        back = (Button) findViewById(R.id.appback);
        numberofJob = (EditText) findViewById(R.id.numberofJob);
        jobname = (EditText) findViewById(R.id.jobname);
        jobsum = (EditText) findViewById(R.id.jobdesum);
        jobposition = (EditText) findViewById(R.id.jobposition);
        jobsalary = (EditText) findViewById(R.id.jobsalary);
        jobcategory = (EditText) findViewById(R.id.jobcategory);
        jobskillreq = (EditText) findViewById(R.id.jobskillreq);

        Intent i =getIntent();

        String t1=i.getStringExtra("id").toString();
        String t2=i.getStringExtra("jobdescription").toString();
        String t3=i.getStringExtra("jobsum").toString();
        String t4=i.getStringExtra("jobposition").toString();
        String t5=i.getStringExtra("jobsalary").toString();
        String t6=i.getStringExtra("category").toString();
        String t7=i.getStringExtra("jobskill").toString();

        numberofJob.setText(t1);
        jobname.setText(t2);
        jobsum.setText(t3);
        jobposition.setText(t4);
        jobsalary.setText(t5);
        jobcategory.setText(t6);
        jobskillreq.setText(t7);


        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertbox();


            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertbox1();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(getApplicationContext(),postedjob.class);
                startActivity(b);
            }
        });


    }
    public void alertbox1(){
        AlertDialog.Builder builder = new AlertDialog.Builder(editjob.this);
        builder.setTitle("Notification");
        builder.setIcon(R.drawable.alert);
        builder.setMessage("Are you sure do you want to delete this?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deletejob();
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
    public void alertbox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(editjob.this);
        builder.setTitle("Notification");
        builder.setIcon(R.drawable.alert);
        builder.setMessage("Are you sure about this?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editjob();
                Intent edi=new Intent(getApplicationContext(),postedjob.class);
                startActivity(edi);
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
    public void deletejob() {

        try {
            String id=numberofJob.getText().toString();


            //if(jname.equals(" ")||jsum.equals(" ")||jpos.equals(" ")||jsal.equals(" ")||jcat.equals(" ")||jskill.equals(" "))
            //Toast.makeText(addingjobs.this, "Please Enter all the field requirements", Toast.LENGTH_SHORT).show();

            SQLiteDatabase insertjob= openOrCreateDatabase("job", Context.MODE_PRIVATE,null);
            //insertjob.execSQL("CREATE TABLE IF NOT EXISTS postedjob (id INTEGER PRIMARY KEY AUTOINCREMENT, jobdescription VARCHAR, jobsum VARCHAR, jobposition VARCHAR, jobsalary INTEGER, category VARCHAR, jobskill VARCHAR)");

            String sql="delete from posted_job where id=?";
            SQLiteStatement statement= insertjob.compileStatement(sql);
            statement.bindString(1,id);
            statement.execute();

            Toast.makeText(editjob.this, "Job Deleted Succesfully", Toast.LENGTH_SHORT).show();

            jobname.setText("");
            jobsum.setText("");
            jobposition.setText("");
            jobsalary.setText("");
            jobcategory.setText("");
            jobskillreq.setText("");
            jobname.requestFocus();

        } catch (Exception ex) {
            Toast.makeText(editjob.this, "Enter all the field requirementshahahah", Toast.LENGTH_SHORT).show();

        }
    }
    public void editjob() {

        try {
            String id=numberofJob.getText().toString();
            String jname=jobname.getText().toString();
            String jsum=jobsum.getText().toString();
            String jpos=jobposition.getText().toString();
            String jsal=jobsalary.getText().toString();
            String jcat=jobcategory.getText().toString();
            String jskill=jobskillreq.getText().toString();

            //if(jname.equals(" ")||jsum.equals(" ")||jpos.equals(" ")||jsal.equals(" ")||jcat.equals(" ")||jskill.equals(" "))
            //Toast.makeText(addingjobs.this, "Please Enter all the field requirements", Toast.LENGTH_SHORT).show();

            SQLiteDatabase insertjob= openOrCreateDatabase("job", Context.MODE_PRIVATE,null);
            //insertjob.execSQL("CREATE TABLE IF NOT EXISTS postedjob (id INTEGER PRIMARY KEY AUTOINCREMENT, jobdescription VARCHAR, jobsum VARCHAR, jobposition VARCHAR, jobsalary INTEGER, category VARCHAR, jobskill VARCHAR)");

            String sql="update posted_job set jobdescription=?,jobsum=?,jobposition=?,jobsalary=?,category=?,jobskill=? where id=?";
            SQLiteStatement statement= insertjob.compileStatement(sql);
            statement.bindString(1,jname);
            statement.bindString(2,jsum);
            statement.bindString(3,jpos);
            statement.bindString(4,jsal);
            statement.bindString(5,jcat);
            statement.bindString(6,jskill);
            statement.bindString(7,id);
            statement.execute();

            Toast.makeText(editjob.this, "Job Edit Succesfully", Toast.LENGTH_SHORT).show();

            jobname.setText("");
            jobsum.setText("");
            jobposition.setText("");
            jobsalary.setText("");
            jobcategory.setText("");
            jobskillreq.setText("");
            jobname.requestFocus();

        } catch (Exception ex) {
            Toast.makeText(editjob.this, "Enter all the field requirementshahahah", Toast.LENGTH_SHORT).show();

        }
    }
}
