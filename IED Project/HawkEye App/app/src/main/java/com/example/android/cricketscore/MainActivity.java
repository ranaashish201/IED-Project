package com.example.android.cricketscore;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import static android.R.attr.x;

public class MainActivity extends AppCompatActivity {
    private EditText password;
    private  Button mloginbutton;
    private Button mreset;
    private EditText oldp;
    protected EditText newp;
     String passwd  ;
    public int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  AssetManager am = this.getAssets();
     //   InputStream inputStream = am.open("pas.txt");
        mloginbutton = (Button) findViewById(R.id.loginbtn);
        mreset = (Button) findViewById(R.id.resetbtn);
        password = (EditText) findViewById(R.id.editText) ;
        oldp = (EditText) findViewById(R.id.editText2);
        newp = (EditText) findViewById(R.id.editText3);
        passwd = readFromFile(this);
        if (passwd.equals(""))
            passwd = "IED";

/*        EditText text1 = (EditText)findViewById(R.id.team_1_name);
        password = (EditText) findViewById(R.id.toss_id) ;
        String team1name = text1.getText().toString();
        EditText text2 = (EditText)findViewById(R.id.team_2_name);
        String team2name = text2.getText().toString();
        EditText text3 = (EditText)findViewById(R.id.overs);
        String Totalovers = text3.getText().toString();
        Button toss = (Button) findViewById(R.id.toss_id);*/

// Set a click listener on that View
        mloginbutton.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                String pd = password.getText().toString();
                if (pd.equals(passwd))
                { Intent toss = new Intent(MainActivity.this, toss_screen.class);
                startActivity(toss);}
                else
                    Toast.makeText(MainActivity.this,"Wrong password",Toast.LENGTH_LONG).show();


            }
        });
        mreset.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                String op = oldp.getText().toString();
                if (op.equals(passwd))
                {
                    String np = newp.getText().toString();
                    passwd = np;
                    writeToFile(np,MainActivity.this);
                    Toast.makeText(MainActivity.this,"Password changed succesfully",Toast.LENGTH_LONG).show();

                }
                else
                    Toast.makeText(MainActivity.this,"Wrong old password",Toast.LENGTH_LONG).show();


            }
        });

    }
    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("pas.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("pas.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }



}
