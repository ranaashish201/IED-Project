package com.example.android.cricketscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class toss_screen extends AppCompatActivity {

    private Button mFirebaseBtn;
    private DatabaseReference mDatabase;
    private EditText mNamefield;
    private EditText mEmailfield;
    private String systemstatus = "";
    private Button  on;
    private Button off;
    String Systemon = "",Systemoff = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toss_screen);
        mFirebaseBtn = (Button) findViewById(R.id.firebase_btn);
        mDatabase = FirebaseDatabase.getInstance().getReference();
     //   mNamefield = (EditText) findViewById(R.id.editText);
        mEmailfield = (EditText) findViewById(R.id.editText);
        on = (Button) findViewById(R.id.button2);
        off = (Button) findViewById(R.id.button3);
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Systemon = "true";
                Systemoff = "false";
                TextView tv = (TextView)findViewById(R.id.status);
                tv.setText("Activate selected, please click GO button");
            }
        });
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Systemoff = "true";
                Systemon = "false";
                TextView tv = (TextView)findViewById(R.id.status);
                tv.setText("Deactivate selected, please click GO button");
            }
        });
      //  mstatus.setOnClickListener();
        mFirebaseBtn.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v){
                // create child in root and then add some value (object )
              //  String name = mNamefield.getText().toString().trim();
                String Email = mEmailfield.getText().toString().trim();


                // to store the object with attributes name and Email we need to create a hashmap
                HashMap<String,String> datamap =  new HashMap<String,String>();
                datamap.put("Systemstatus",Systemon);
                datamap.put("Email",Email);
             //   datamap.put("Systemoff",Systemoff);
                //  mDatabase.child("Name").setValue(name);  //  will reset if i put another value here
                //       mDatabase.push().setValue(datamap);   // will append value and not reset but the key attribute will be some random name
                mDatabase .setValue(datamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Toast.makeText(toss_screen.this,"You are added",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(toss_screen.this,"Failed :(",Toast.LENGTH_LONG).show();
                    }

                });  // just to cofirm that firebase is completed or not
            }
        });
    }/*
    public void buttonclick1(View v)
    {
        TextView tv = (TextView)findViewById(R.id.status);
        tv.setText("Activate selected, please click GO button");
    }
    public void buttonclick2(View v)
    {
        TextView tv = (TextView)findViewById(R.id.status);
        tv.setText("Deactivate selected, please click GO button");
    }*/

    //    Button next = (Button) findViewById(R.id.Next);
      /*  next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextScreen = new Intent(toss_screen.this, afternext.class);
                startActivity(nextScreen);
            }
        });*/

    }
 /*   public void simulation(View view){
    double a = Math.random();
        if (a<=0.5){display("Congratulations! \nYou won the toss");

        }
        else display("Sorry :(\nYou lose the toss");
    }*/
/*
    private void display(String message) {
        TextView toss_result = (TextView) findViewById(R.id.results);
        toss_result.setText(message);}
}
*/
