package com.example.contactapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FavPersonAdapter extends RecyclerView.Adapter<FavPersonAdapter.ViewHolder2> {
    private ArrayList<FavPerson> Favs;
    FavClick myActivity;
    RecyclerView.Adapter myAdapter2;

    public interface FavClick {
        void onFavClick(int index);
    }
    public FavPersonAdapter(Context context, ArrayList<FavPerson> list) {
        Favs = list;
        myActivity=(FavClick) context;
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView tvFavName;
        ImageView ivFav;
        FloatingActionButton btnDelFav;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            ivFav=itemView.findViewById(R.id.ivFav);
            tvFavName = itemView.findViewById(R.id.tvFavName);
            btnDelFav=itemView.findViewById(R.id.btnDelFav);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   myActivity.onFavClick(Favs.indexOf((FavPerson)itemView.getTag()));
                }
            });
            btnDelFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FavPerson f=Favs.get(Favs.indexOf((FavPerson)itemView.getTag()));
                    Toast.makeText((Context) myActivity, f.getName(), Toast.LENGTH_SHORT).show();
                    Favs.remove(f);
                    Toast.makeText((Context) myActivity, f.getName()+" Removed from favourites", Toast.LENGTH_SHORT).show();

                }
            });
        }


    }

    @NonNull
    @Override

    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favlist, parent, false);
        return (new ViewHolder2(v));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
        holder.tvFavName.setText(Favs.get(position).getName());
        holder.itemView.setTag(Favs.get(position));
    }

    @Override
    public int getItemCount() {
        return Favs.size();
    }
}


