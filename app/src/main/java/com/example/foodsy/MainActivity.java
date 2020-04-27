package com.example.foodsy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {
    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav=findViewById(R.id.bottom_nav);

        if(savedInstanceState==null)
        {
            bottomNav.setItemSelected(R.id.shop,true);
            fragmentManager= getSupportFragmentManager();
            ShopFragment shopFragment = new ShopFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,shopFragment).commit();
        }


        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment =null;
                switch (id)
                {
                    case R.id.sell:
                        fragment = new SellFragment();
                        break;
                    case R.id.shop:
                        fragment = new ShopFragment();
                        break;
                    case R.id.account:
                        fragment = new AccountFragment();
                }

                if(fragment!=null)
                {
                    fragmentManager= getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                }
            }
        });

    }
}
