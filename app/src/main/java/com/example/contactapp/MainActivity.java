package com.example.contactapp;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity implements PersonAdapter.ItemClick , FavPersonAdapter.FavClick {


FloatingActionButton btnAdd, btnBack, btnDel,btnFavDel,btnEdit;
RecyclerView RvList;
RecyclerView.LayoutManager layoutManager;
RecyclerView.Adapter myAdapter;
ArrayList<Person> contacts;
ArrayList<FavPerson>Favs;
TextView tvName,tvNumber, tvEmail, tvAddress, tvDOB;
LinearLayout mainActiviy;
ImageView ivDetailContact, ivCall, ivAddress;
int selected;
final static String FileName="MyContacts.txt";
final static int Add_RECORD=1;
FavPersonAdapter myAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        FragmentManager manager=this.getSupportFragmentManager();
        manager.beginTransaction().hide(manager.findFragmentById(R.id.detailFrag));
        AddToContacts();

        myAdapter=new PersonAdapter(this,contacts);
        RvList.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(com.example.contactapp.MainActivity.this,AddData.class);
                startActivityForResult(intent,Add_RECORD);

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActiviy.setVisibility(View.VISIBLE);
                manager.beginTransaction().hide(manager.findFragmentById(R.id.detailFrag));
            }
        });
        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contacts.get(selected).getNumber()==null) {
                    Intent intent1 = new Intent(Intent.ACTION_DIAL);
                    startActivity(intent1);
                }
                else
                {
                    Intent intent1=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+contacts.get(selected).getNumber()));
                    startActivity(intent1);
                }
            }
        });
        ivAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contacts.get(selected).getAddress() == null) {
                    Intent intent1 = new Intent(Intent.ACTION_DIAL);
                    startActivity(intent1);
                } else {
                    Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("geo: 0,0?q=" + contacts.get(selected).getAddress()));
                    startActivity(intent1);
                }
            }
        });



    }

    private void AddToContacts() {
        String data= null;
        try {
            data = readFromFile(FileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] lines=data.split("\n");
        for(String l:lines) {
            String[] words = l.split(",,");
            if (words.length!=1) {
                contacts.add(new Person(words[0], words[1], words[2], words[3], words[4]));
            }
        }
    }

    private void init() {
        RvList= findViewById(R.id.RvList);
        RvList.setHasFixedSize(true);
        btnAdd=findViewById(R.id.btnAdd);
        layoutManager=new LinearLayoutManager(this);
        RvList.setLayoutManager(layoutManager);
        contacts=new ArrayList<>();
        tvName=findViewById(R.id.tvName);
        tvNumber=findViewById(R.id.tvNumber);
        tvEmail=findViewById(R.id.tvEmail);
        tvAddress=findViewById(R.id.tvAddress);
        tvDOB=findViewById(R.id.tvDOB);
        mainActiviy=(LinearLayout) findViewById(R.id.mainActivity);
        ivDetailContact=findViewById(R.id.ivDetailContact);
        btnBack=findViewById(R.id.btnBack);
        ivCall=findViewById(R.id.ivCall);
        ivAddress=findViewById(R.id.ivAddress);
        btnDel=findViewById(R.id.btnDel);
        btnFavDel=findViewById(R.id.btnDelFav);
        btnEdit=findViewById(R.id.btnEdit);
    }


    @Override
    public void onItemClick(int index) {
        mainActiviy.setVisibility(View.GONE);
        FragmentManager manager=this.getSupportFragmentManager();
        manager.beginTransaction().show(manager.findFragmentById(R.id.detailFrag));
        tvName.setText(contacts.get(index).getName());
        tvNumber.setText(contacts.get(index).getNumber());
        tvEmail.setText(contacts.get(index).getEmail());
        tvAddress.setText(contacts.get(index).getAddress());
        tvDOB.setText(contacts.get(index).getDOB());
        ivDetailContact.setImageResource(R.drawable.duaapic3);
        selected=index;
    }

    @Override
    public void onDelClick(int index) {
        String name=contacts.get(index).getName();
        try {
            String data=readFromFile(FileName);
            String[] lines=data.split("\n");

            for(String l:lines) {
                String[] words = l.split(",,");
                if (words[0].equals(name)) {
                    Toast.makeText(this, l, Toast.LENGTH_SHORT).show();
                    data=data.replaceAll(l,"");
                    writeToFile(data,FileName);
                    Toast.makeText(this, "Rewrite Done", Toast.LENGTH_SHORT).show();
                    myAdapter.notifyDataSetChanged();
                   }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Add_RECORD)
        {
            if(resultCode==RESULT_OK)
            {
                Person p=new Person(
                        data.getStringExtra("Name"),
                        data.getStringExtra("Email"),
                        data.getStringExtra("Phone"),
                        data.getStringExtra("Address"),
                        data.getStringExtra("CarPlate"));
                contacts.add(p);
                Toast.makeText(this, "Record Entered", Toast.LENGTH_SHORT).show();


            }
            else if(resultCode==RESULT_CANCELED)
            {
                Toast.makeText(this, "Record not Entered", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private String readFromFile(String File_name) throws IOException {

        String text = "";
        try {

            FileInputStream fis = openFileInput(File_name);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  text;
    }
    public void writeToFile(String data, String File_name) {
        try {
            FileOutputStream fos= openFileOutput(File_name,MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
            Toast.makeText(getApplicationContext(),"Saved to Contacts",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFavClick(int index) {
        mainActiviy.setVisibility(View.GONE);
        FragmentManager manager=this.getSupportFragmentManager();
        manager.beginTransaction().show(manager.findFragmentById(R.id.detailFrag));
        tvName.setText(FavArray.Favs.get(index).getName());
        tvNumber.setText(FavArray.Favs.get(index).getNumber());
        tvEmail.setText(FavArray.Favs.get(index).getEmail());
        tvAddress.setText(FavArray.Favs.get(index).getAddress());
        tvDOB.setText(FavArray.Favs.get(index).getDOB());
        ivDetailContact.setImageResource(R.drawable.duaapic3);
        selected=index;
    }




}