package com.example.user.jobapplicationportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class landingpage extends AppCompatActivity {
Button User1,Recuiter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

         User1 =(Button)findViewById(R.id.User);
         Recuiter1 =(Button)findViewById(R.id.Recuiter);

        User1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent u=new Intent(landingpage.this, loginpage.class);
                startActivity(u);
            }
        });
        Recuiter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r=new Intent(landingpage.this, loginpageRecuiter.class);
                startActivity(r);
            }
        });

    }
}
