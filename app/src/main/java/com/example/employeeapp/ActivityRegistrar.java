package com.example.employeeapp;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


import android.os.Bundle;

public class ActivityRegistrar extends AppCompatActivity {

    private EditText cajaName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        // Obtener referencia al EditText con el ID TxtName
        cajaName = findViewById(R.id.TxtName);

        // Obtener referencia al botón con el ID button2
        Button btnCrearCuenta = findViewById(R.id.button2);

        // Agregar un OnClickListener al botón
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el texto del EditText cuando se presiona el botón
                String usuario = cajaName.getText().toString();

                // Crear un objeto JSON
                JSONObject jsonParam = new JSONObject();
                try {
                    jsonParam.put("nombre", usuario); // Puedes ajustar la clave según las necesidades del servidor
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Realizar la conexión HTTP en un hilo separado
                new SendJsonDataToServer().execute(jsonParam);
            }
        });
    }

    private static class SendJsonDataToServer extends AsyncTask<JSONObject, Void, Void> {
        @Override
        protected Void doInBackground(JSONObject... jsonObjects) {
            try {
                // URL local para el servidor en el mismo proyecto
                URL url = new URL("http://10.0.2.2:8080"); // Ajusta la dirección según tu configuración

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                try {
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setDoOutput(true);

                    // Escribir el JSON en el cuerpo de la solicitud
                    try (OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream())) {
                        byte[] outputBytes = jsonObjects[0].toString().getBytes(StandardCharsets.UTF_8);
                        out.write(outputBytes);
                    }

                    // Leer la respuesta del servidor (si es necesario)
                    // InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}