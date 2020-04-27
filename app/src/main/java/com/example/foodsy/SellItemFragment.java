package com.example.foodsy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SellItemFragment extends Fragment {

    private Button addItem;
    private EditText itemName;
    private EditText itemPrice;
    private EditText itemQuantity;
    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sell_item, container, false);

        addItem= view.findViewById(R.id.add_item_button);
        itemName = view.findViewById(R.id.item_name_sec);
        itemPrice = view.findViewById(R.id.item_price_sec);
        itemQuantity = view.findViewById(R.id.item_quantity_sec);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = itemName.getText().toString();
                String Price = itemPrice.getText().toString();
                String Quantity = itemQuantity.getText().toString();

                Bundle bundle= new Bundle();
                bundle.putString("Name",Name);
                bundle.putString("Price",Price);
                bundle.putString("Quantity",Quantity);


                fragmentManager = getActivity().getSupportFragmentManager();
                SellFragment sellFragment = new SellFragment();
                sellFragment.setArguments(bundle);

                fragmentManager.beginTransaction().replace(R.id.fragment_container,sellFragment).commit();

            }
        });


        return view;
    }
}
