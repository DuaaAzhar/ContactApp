package com.example.contactapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private ArrayList<Person> contacts;
    ItemClick myActivity;
    public interface ItemClick{
        void onItemClick(int index);
        void onDelClick(int index);
    }
    public PersonAdapter(Context context, ArrayList<Person> list){
        contacts=list;
        myActivity=(ItemClick) context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivPic;
        TextView tvName, tvEmail;
        FloatingActionButton btnDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPic=itemView.findViewById(R.id.ivPic);
            tvName=itemView.findViewById(R.id.tvName);
            tvEmail=itemView.findViewById(R.id.tvEmail);
            btnDel=itemView.findViewById(R.id.btnDel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myActivity.onItemClick(contacts.indexOf((Person)(itemView.getTag())));
                }
            });
            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int p=(contacts.indexOf((Person)(itemView.getTag())));
                    contacts.get(p).getName();
                    Toast.makeText((Context) myActivity,contacts.get(p).getName() , Toast.LENGTH_SHORT).show();
                    myActivity.onDelClick(p);
                }
            });
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(contacts.get(position));
        holder.tvName.setText(contacts.get(position).getName());
        holder.tvEmail.setText(contacts.get(position).getEmail());
        holder.ivPic.setImageResource(R.drawable.duaapic3);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
