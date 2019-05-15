package com.Utils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpUtils {


    public static String sendMessage(String url, int port, byte[] request) {
        byte[] res = null;
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            socket = new Socket(url, port);
            os = socket.getOutputStream();
            os.write(request);
            os.flush();
            is = socket.getInputStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            do {
                count = is.read(buffer);
                bos.write(buffer, 0, count);
            } while (is.available() != 0);
            res = bos.toByteArray();
            os.close();
            is.close();
            socket.close();
        } catch (Exception ex) {
            try {
                if (is != null) {
                    is.close();
                }
                if (socket != null)
                    socket.close();
            } catch (Exception e) {
            }
        }
        return new String(res);
    }

    /*
     * 关闭socket连接
     */
    public static void close(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /*
     * 获取socket连接
     */
    public static Socket getConnection(String ip, int port) {
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
            socket.setSoTimeout(30000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }





}
