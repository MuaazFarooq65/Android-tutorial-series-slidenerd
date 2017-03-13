package com.example.addfragmentsjava;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //precisamos objecto fragment da outra classe --->
        MyFragment frag = new MyFragment();
        
        //mantem referencia a todos fragmentos na actividade e comeca transaçoes
        FragmentManager manager = getFragmentManager();
        
        //muda UI e permite adicionar, editar e remover
        FragmentTransaction transaction = manager.beginTransaction();
        
        //my_layout = main activity
        transaction.add(R.id.my_layout, frag, "VivzFragment");
        transaction.commit();
        
    }
}