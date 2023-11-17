package com.example.employeeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Clase que representa la actividad de inicio en la aplicación.
 * Permite al usuario acceder a la lista de amigos mediante un botón.
 */
public class ActivityIniciar extends AppCompatActivity {
    private Button amigos;
    /**
     * Método llamado cuando se crea la actividad. Inicializa la interfaz de usuario
     * y configura el botón para redirigir al usuario a la lista de amigos.
     *
     * @param savedInstanceState El estado de la instancia guardado.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);
        amigos= (Button) findViewById(R.id.btnAmigos);

        amigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ActivityIniciar.this,MisAmigos.class);
                startActivity(intent);
            }
        });
    }
}


