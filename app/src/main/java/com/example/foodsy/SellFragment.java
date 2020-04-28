package com.example.foodsy;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellFragment extends Fragment {


   // private static final int MODE_PRIVATE = "";
    FloatingActionButton addItem;
    FragmentManager fragmentManager;
    View view;

    private ArrayList<item> Items;;
    private RecyclerView recyclerView;
    private ExampleAdaptor myAdapter;
    String Name;
    String Price;
    String Quantity;

    int i=0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_sell,container,false);

        recyclerView = view.findViewById(R.id.recyclerView);

        myAdapter = new ExampleAdaptor(Items,view.getContext());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        ItemTouchHelper itemTouchHelper= new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        addItem=view.findViewById(R.id.add_item);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SellItemFragment sellItemFragment =new SellItemFragment();
                fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_container,sellItemFragment).commit();
            }
        });
        Bundle bundle=getArguments();

        if(bundle!=null) {

            Name = bundle.getString("Name");
            Price = bundle.getString("Price");
            Quantity = bundle.getString("Quantity");
            Items.add(new item(Name, Price, Quantity));
            myAdapter.notifyItemInserted(0);
        }

        saveData();

        return view;
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(Items);
        editor.putString("task list", json);
        editor.apply();
    }
    private void loadData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<item>>() {}.getType();
        Items = gson.fromJson(json, type);

        if (Items == null) {
            Items = new ArrayList<>();
        }
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Items.remove(viewHolder.getAdapterPosition());
            myAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            saveData();
        }
    };

}
