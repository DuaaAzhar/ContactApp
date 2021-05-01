package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddData extends AppCompatActivity {


    EditText etName,etNumber,etEmail,etAddress,etDOB;
    Button btnSave, btnCancel;
    FloatingActionButton btnBack;
    public final String File_name = "MyContacts.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        init();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=etName.getText().toString().trim();
                String Number=etNumber.getText().toString().trim();
                String Email=etEmail.getText().toString().trim();
                String Address=etAddress.getText().toString().trim();
                String DOB=etDOB.getText().toString().trim();
                if(validation(Name,Number,Email,Address,DOB)){
                    Intent intent=new Intent();
                    intent.putExtra("Name",Name);
                    intent.putExtra("Number",Number);
                    intent.putExtra("Email",Email);
                    intent.putExtra("Address",Address);
                    intent.putExtra("DOB",DOB);
                    setResult(RESULT_OK,intent);
                   String data=string(Name,Number,Email,Address,DOB);
                   writeToFile(data,File_name);
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(com.example.contactapp.AddData.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean validation(String name, String number, String email, String address, String dob) {
        Boolean flag=true;
        if(name.isEmpty())
        {
            etName.setError("Enter Name");
            flag=false;
        }
        else if(number.isEmpty())
        {
            etNumber.setError("Enter Number");
            flag=false;
        }
        else if(email.isEmpty())
        {
            etEmail.setError("Enter Email Address");
            flag=false;
        }
        else if(address.isEmpty())
        {
            etAddress.setError("Enter Address");
            flag=false;
        }
        else if(dob.isEmpty())
        {
            etDOB.setError("Enter DOB");
            flag=false;
        }
        return flag;
    }


    private String string(String name, String number, String email, String address, String dob) {
        return (name+",,"+number+",,"+email+",,"+address+",,"+dob+"\n");
    }


    private void init() {
        etName=findViewById(R.id.etName);
        etNumber=findViewById(R.id.etNumber);
        etEmail=findViewById(R.id.etEmail);
        etAddress=findViewById(R.id.etAddress);
        etDOB=findViewById(R.id.etDOB);
        btnCancel=findViewById(R.id.btnCancel);
        btnSave=findViewById(R.id.btnSave);
        btnBack=findViewById(R.id.btnBack);
    }
    public void writeToFile(String data, String File_name) {
        try {
            FileOutputStream fos= openFileOutput(File_name,MODE_APPEND);
            fos.write(data.getBytes());
            fos.close();
            Toast.makeText(getApplicationContext(),"Saved to Contacts",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}