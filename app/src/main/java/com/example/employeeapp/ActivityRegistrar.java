package com.example.employeeapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;



public class ActivityRegistrar extends AppCompatActivity {

    private EditText cajaName;
    private EditText cajaCarnet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        cajaName = findViewById(R.id.TxtName);
        cajaCarnet = findViewById(R.id.TxtCarnet);
        Button btnCrearCuenta = findViewById(R.id.button2);

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el texto del EditText cuando se presiona el botón
                String usuario = cajaName.getText().toString();
                String contrasena = cajaCarnet.getText().toString();

                new SendDataTask().execute(usuario, contrasena);

            }
        });
    }

}
