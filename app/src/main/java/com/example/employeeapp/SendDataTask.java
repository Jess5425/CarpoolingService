package com.example.employeeapp;
import android.os.AsyncTask;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Clase para enviar datos de usuario y contraseña a un servidor en segundo plano.
 */

public class SendDataTask extends AsyncTask<String, Void, Void> {

    /**
     * Método que se ejecuta en segundo plano para enviar datos al servidor.
     *
     * @param params Un arreglo de strings que contiene el usuario en params[0] y la contraseña en params[1].
     * @return Nulo, porque no se espera un resultado específico.
     */

    @Override
    protected Void doInBackground(String... params) {
        String usuario = params[0];
        String contrasena = params[1];

        try {

            Socket socket = new Socket("Server", 9999);

            // OutputStream del socket
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
