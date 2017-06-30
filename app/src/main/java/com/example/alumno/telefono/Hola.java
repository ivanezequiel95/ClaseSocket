package com.example.alumno.telefono;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.net.Socket;

public class Hola extends AppCompatActivity implements Handler.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hola);

        Handler handler = new Handler();
        Conexion c = new Conexion(new Socket(), handler);

        Thread t = new Thread(c);
        t.start();

        Button b = (Button)this.findViewById(R.id.boton1);
        EditText e = (EditText)this.findViewById(R.id.edit_text1);
        b.setOnClickListener(new BotonListener(c, e));


    }

    @Override
    public boolean handleMessage(Message msg) {



        return false;
    }
}
