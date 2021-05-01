package com.example.contactapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class FavContactsFrag extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter2;
    RecyclerView.LayoutManager layoutManager;
    View v2;

    public FavContactsFrag() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v2=inflater.inflate(R.layout.favcontactsfrag,container,false);
        return v2;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView=v2.findViewById(R.id.RvListFav);
        recyclerView.setHasFixedSize(true);
        layoutManager=new GridLayoutManager(this.getActivity(),1,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter2=new FavPersonAdapter(this.getActivity(), FavArray.Favs);
        recyclerView.setAdapter(myAdapter2);
    }

}