package com.example.alumno.telefono;

import android.view.View;
import android.widget.EditText;

/**
 * Created by alumno on 29/06/2017.
 */

public class BotonListener implements View.OnClickListener{

    Conexion c;
    EditText e;
    public BotonListener(Conexion c, EditText e)
    {
        this.c = c;
        this.e = e;
    }

    @Override
    public void onClick(View v) {
        c.escribir(e.getText().toString());
    }
}
