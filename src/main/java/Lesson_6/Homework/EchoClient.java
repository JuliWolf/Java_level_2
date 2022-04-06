package Lesson_6.Homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {
        final EchoClient client = new EchoClient();
        client.start();
    }

    private void start() {
        try {
          openConnection();
          final Scanner scanner = new Scanner(System.in);
          while (true) {
              final String message = scanner.nextLine();
              out.writeUTF(message);

              if ("/end".equalsIgnoreCase(message)) {
                  break;
              }
          }
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException err) {
                err.printStackTrace();
            }
        }

        if (in != null) {
            try {
                in.close();
            } catch (IOException err) {
                err.printStackTrace();
            }
        }

        if (out != null) {
            try {
                out.close();
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }

    private void openConnection() throws IOException {
        socket = new Socket("localhost", 8189);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        final Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    while (true) {
                        final String message = in.readUTF();
                        System.out.println("Сообщуние от сервера: " + message);
                    }
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
