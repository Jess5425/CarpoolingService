package com.example.employeeapp;
import android.os.AsyncTask;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendDataTask extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        String usuario = params[0];
        String contrasena = params[1];

        try {
            // Reemplaza "tu_servidor" y "tu_puerto" con la dirección y puerto del servidor
            Socket socket = new Socket("Server", 9999);

            // Obtén el OutputStream del socket
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            // Envía el usuario y la contraseña al servidor
            dataOutputStream.writeUTF(usuario);
            dataOutputStream.writeUTF(contrasena);

            // Cierra la conexión
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
