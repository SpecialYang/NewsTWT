package com.special.newsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.special.newsdemo.Fragment.MenuFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*MenuFragment menuFragment = (MenuFragment) getSupportFragmentManager()
                .findFragmentById(R.id.menu_item_fragment);*/
    }
}
