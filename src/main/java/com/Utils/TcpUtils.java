package com.Utils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpUtils {
    public static void send(String[] args) throws UnknownHostException, IOException {
        //创建发送端Socket对象
        Socket s = new Socket(InetAddress.getLocalHost(), 9999);
        //获取输出流对象
        OutputStream os = s.getOutputStream();
        //发送数据
        String str = "how are you";
        //byte[] bt = str.getBytes();
        os.write(str.getBytes());
        //释放资源
        //os.close();
        s.close();
    }

    /*
     * 发送报文
     */
    public static String send(String url, int port, byte[] request) {

        Socket socket = null;
        OutputStream os = null;
        try {
            socket = new Socket(url, port);
            os = socket.getOutputStream();
            os.write(request);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


        InputStream in = null;
        OutputStream out = null;
        BufferedReader br = null;
        try {
            // in代表服务器到客户端的流
            in = socket.getInputStream();
            // 代表客户端到服务器的流
            out = socket.getOutputStream();

            // 输入执行命令
            PrintWriter printWriter = new PrintWriter(out, true);
//            printWriter.println(cmd);
            printWriter.flush();

            // 接收执行命令结果
            br = new BufferedReader(new InputStreamReader(in));
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        close(socket);
        return null;
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

}
