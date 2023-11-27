package com.example.user.jobapplicationportal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class searchjob extends AppCompatActivity {
    ListView listView1;
    ArrayList<String> title =new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchjob);
        back=(Button)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent(searchjob.this,applicantdashboard.class);
                startActivity(back);


            }
        });

        SQLiteDatabase insertjob= openOrCreateDatabase("job", Context.MODE_PRIVATE,null);

        listView1=(ListView)findViewById(R.id.listview1);

        final Cursor c = insertjob.rawQuery("select * from posted_job",null);
        int id=c.getColumnIndex("id");
        int jobname= c.getColumnIndex("jobdescription");
        int jobdes= c.getColumnIndex("jobsum");
        int jobpos= c.getColumnIndex("jobposition");
        int jobsal= c.getColumnIndex("jobsalary");
        int jobcate= c.getColumnIndex("category");
        int jobskil= c.getColumnIndex("jobskill");
        title.clear();

        arrayAdapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,title);
        listView1.setAdapter(arrayAdapter);

        final ArrayList<JobpostedArray> jobposted =new ArrayList<JobpostedArray>();

        if(c.moveToFirst())
        {
            do{

                JobpostedArray Jobarray=new JobpostedArray();
                Jobarray.id=c.getString(id);
                Jobarray.jobdescription=c.getString(jobname);
                Jobarray.jobsum=c.getString(jobdes);
                Jobarray.jobposition=c.getString(jobpos);
                Jobarray.jobsalary=c.getString(jobsal);
                Jobarray.category=c.getString(jobcate);
                Jobarray.jobskill=c.getString(jobskil);

                jobposted.add(Jobarray);

                //title.add(c.getString(id)+ " \t " + c.getString(jobname)+ " \t " + c.getString(jobdes) + " \t "+ c.getString(jobpos) + " \t " + c.getString(jobsal) + " \t " + c.getString(jobcate) + " \t " + c.getString(jobskil) + " \t " );
                title.add(c.getString(id) +". \t Job Description:" +c.getString(jobname)+" \n " + " Job Salary:"+c.getString(jobsal));


            }
            while(c.moveToNext());
                arrayAdapter.notifyDataSetChanged();
                listView1.invalidateViews();


        }

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String aa=title.get(position).toString();

                JobpostedArray Jobarray1 = jobposted.get(position);
                Intent i =new Intent(getApplicationContext(),applyingjob.class);
                i.putExtra("id",Jobarray1.id);
                i.putExtra("jobdescription",Jobarray1.jobdescription);
                i.putExtra("jobsum",Jobarray1.jobsum);
                i.putExtra("jobposition",Jobarray1.jobposition);
                i.putExtra("jobsalary",Jobarray1.jobsalary);
                i.putExtra("category",Jobarray1.category);
                i.putExtra("jobskill",Jobarray1.jobskill);
                startActivity(i);



            }
        });

    }
}
