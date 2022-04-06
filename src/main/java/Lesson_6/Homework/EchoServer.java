package Lesson_6.Homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) {
        try (ServerSocket serverSoket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            Socket socket = serverSoket.accept();
            System.out.println("Клиент подключился");
            final DataInputStream in = new DataInputStream(socket.getInputStream());
            final DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                final String message = in.readUTF();
                if ("/end".equalsIgnoreCase(message)) {
                    out.writeUTF(message);
                    break;
                }
                out.writeUTF("echo: " + message);
            }
        }catch (IOException err) {
            err.printStackTrace();
            throw new RuntimeException(err);
        }
    }
}
