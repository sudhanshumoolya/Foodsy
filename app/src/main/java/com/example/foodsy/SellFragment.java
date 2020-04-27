package com.example.foodsy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellFragment extends Fragment {

    FloatingActionButton addItem;
    FragmentManager fragmentManager;

    private ArrayList<item> Items=new ArrayList<>();;
    private RecyclerView recyclerView;
    private ExampleAdaptor myAdapter;
    String Name;
    String Price;
    String Quantity;

    int i=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sell,container,false);

        recyclerView = view.findViewById(R.id.recyclerView);

        myAdapter = new ExampleAdaptor(Items,view.getContext());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
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
        }

        Items.add(new item(Name, Price, Quantity));
        myAdapter.notifyItemInserted(i++);
        addItem=view.findViewById(R.id.add_item);

        return view;
    }

}
