package com.example.alumno.telefono;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketAddress;


/**
 * Created by alumno on 29/06/2017.
 */

public class Conexion implements Runnable{
    private Socket clienteSocket;
    private Handler handler;
    private BufferedWriter bw;
    private BufferedReader br;
    private String msgStr;

    public Conexion(Socket clienteSocket, Handler handler)
    {
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            this.clienteSocket = new Socket("192.168.2.163", 4097);

            InputStream is = clienteSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            this.br = new BufferedReader(isr);

            while (true) {
                this.msgStr = br.readLine();
                if (msgStr == null)
                    break;
            }


            Message message = new Message();
            message.obj = msgStr;
            this.handler.sendMessage(message);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void escribir(String mensaje)
    {
        try{
            OutputStream os = clienteSocket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            this.bw = new BufferedWriter(osw);
            this.bw.write(mensaje+"/n"); //Si no tengo el /n no termina la linea y del otro lado no se lee como una línea
            this.bw.flush();
        }catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}
