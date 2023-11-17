package com.example.employeeapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Esta clase representa la actividad de registro de usuarios en la aplicación.
 * Los usuarios pueden ingresar su nombre, ubicación y número de carnet para crear una cuenta.
 */

public class ActivityRegistrar extends AppCompatActivity {

    private EditText cajaName;
    private EditText cajaCarnet;

    /**
     * Método llamado cuando se crea la actividad. Inicializa la interfaz de usuario y configura
     * el botón de creación de cuenta para procesar la información del usuario.
     *
     * @param savedInstanceState El estado de la instancia guardado.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        cajaName = findViewById(R.id.TxtName);
        cajaCarnet = findViewById(R.id.TxtCarnet);
        Button btnCrearCuenta = findViewById(R.id.button2);

        // Configuración del listener para el botón de Crear Cuenta

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el texto del EditText cuando se presiona el botón
                String usuario = cajaName.getText().toString();
                String contrasena = cajaCarnet.getText().toString();

                // Ejecutar el hilo para enviar datos
                new SendDataTask().execute(usuario, contrasena);

            }
        });
    }

}
