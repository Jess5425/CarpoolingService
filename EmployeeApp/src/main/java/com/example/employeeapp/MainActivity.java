package com.example.employeeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * Clase que representa la actividad principal de la aplicación.
 * Permite al usuario iniciar sesión o registrarse mediante botones.
 */

public class MainActivity extends AppCompatActivity {
    private Button iniciaSesion;
    private Button registrarse;

    /**
     * Método llamado cuando se crea la actividad. Inicializa la interfaz de usuario
     * y configura los botones para redirigir al usuario a las actividades correspondientes.
     *
     * @param savedInstanceState El estado de la instancia guardado.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciaSesion= (Button) findViewById(R.id.btnIniciar);
        registrarse=(Button) findViewById(R.id.btnRegistro);

        iniciaSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,ActivityIniciar.class);
                startActivity(intent);
            }
        });
//Configuracion para el boton que registra
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,ActivityRegistrar.class);
                startActivity(intent);
            }
        });
    }
}