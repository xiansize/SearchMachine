package com.tcsoft.searchmachinary.model.network;

import android.util.Log;

import com.tcsoft.searchmachinary.config.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;



public class SocketUtil {

    private static final String TAG = "SocketUtil";
    ;

    public static void TCPClient(String msg) {

        Socket socket = null;
        OutputStream os = null;
        PrintWriter pw = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            socket = new Socket(Config.IP_SOCKET, Config.PORT_SOCKET);
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            pw.write(msg);
            pw.flush();

            socket.shutdownOutput();

            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String data;
            while ((data = br.readLine()) != null) {
                Log.d(TAG, data);
            }

            socket.shutdownInput();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (pw != null) {
                pw.close();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
