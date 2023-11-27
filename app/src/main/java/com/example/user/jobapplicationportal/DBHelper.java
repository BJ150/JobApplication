package com.example.user.jobapplicationportal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by user on 22/11/2023.
 */
public class DBHelper  extends SQLiteOpenHelper {
    //ArrayList<String> title =new ArrayList<String>();
   // ArrayAdapter arrayAdapter;

    public static final String DBNAME = "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(email TEXT primary key, password TEXT)");
        MyDB.execSQL("create Table admin(email TEXT primary key, password TEXT)");
        //MyDB.execSQL("create Table jobs(jobdescription TEXT , jobsum TEXT, jobposition TEXT, jobsalary TEXT, category TEXT, jobskill TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users ");
        MyDB.execSQL("drop Table if exists admin ");
        // MyDB.execSQL("drop Table if exists jobs ");
    }

    /*public Boolean insertjob(String jobdescription, String jobsum, String jobposition, String jobsalary, String category, String jobskill) {
        SQLiteDatabase MYDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("jobdescription", jobdescription);
        contentValues.put("jobsum", jobsum);
        contentValues.put("jobposition", jobposition);
        contentValues.put("jobsalary", jobsalary);
        contentValues.put("category", category);
        contentValues.put("jobskill", jobskill);

        long job = MYDB.insert("jobs", null, contentValues);
        if (job == 1) return false;
        else
            return true;

    }
    public Boolean viewjob{
        SQLiteDatabase MYDB = this.getWritableDatabase();
        Cursor cursor=MYDB.rawQuery("Select * from jobs", null);
        int jobdes= cursor.getColumnIndex("jobdescription");
        int jobname= cursor.getColumnIndex("jobsum");
        int jobpos= cursor.getColumnIndex("jobposition");
        int jobsal= cursor.getColumnIndex("jobsalary");
        int jobcate= cursor.getColumnIndex("category");
        int jobskil= cursor.getColumnIndex("jobskill");
        title.clear();
        arrayAdapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,title);



    }*/

   /* public void getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM jobs", null);

        StringBuilder data = new StringBuilder();
        while (cursor.moveToNext()) {
            String jobdes = cursor.getString(0);
            String jobsum = cursor.getString(1);
            data.append("Description: ").append(jobdes).append("").append(jobsum).append("/n");
        }
        cursor.close();

    }*/


    public Boolean insertadmin(String email,String password){
        SQLiteDatabase MYDB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result =MYDB.insert("admin",null,contentValues);
        if(result==-1) {return false;}
        else
        {return true;}


    }
    public Boolean checkusernameadmin(String email){
        SQLiteDatabase MYDB = this.getWritableDatabase();
        Cursor cursor= MYDB.rawQuery("Select * from admin where email= ?", new String[] {email});
        if(cursor.getCount()>0)
        {return true;}
        else
        { return false;}


    }
    public Boolean checkusernamepasswordadmin(String email, String password){
        SQLiteDatabase MYDB = this.getWritableDatabase();
        Cursor cursor= MYDB.rawQuery("Select * from admin where email= ? and password=?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;


    }
    public Boolean insertData(String email,String password){
        SQLiteDatabase MYDB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result =MYDB.insert("users",null,contentValues);
        if(result==-1) {return false;}
        else
        {return true;}


    }

    public Boolean checkusername(String email){
        SQLiteDatabase MYDB = this.getWritableDatabase();
        Cursor cursor= MYDB.rawQuery("Select * from users where email= ?", new String[] {email});
        if(cursor.getCount()>0)
        {return true;}
        else
        { return false;}


    }
    public Boolean checkusernamepassword(String email, String password){
        SQLiteDatabase MYDB = this.getWritableDatabase();
        Cursor cursor= MYDB.rawQuery("Select * from users where email= ? and password=?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;


    }
    public Boolean adminpass(String email, String password){
        SQLiteDatabase MYDB = this.getWritableDatabase();
        Cursor cursor= MYDB.rawQuery("Select * from admin where email= ? and password=?", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;


    }


}

