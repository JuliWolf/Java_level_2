package Lesson_6.Homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient implements IIsEndMessage{
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private Thread listenThread;
    private Thread scannerThread;

    public static void main(String[] args) {
        final EchoClient client = new EchoClient();
        client.start();
    }

    private void start() {
        try {
            openConnection();
            startScanner();

            listenThread.join();
            scannerThread.join();
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
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

        if (socket != null) {
            try {
                socket.close();
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }

    private void startScanner () {
        final Scanner scanner = new Scanner(System.in);
        scannerThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    while (true) {
                        final String message = scanner.nextLine();

                        out.writeUTF(message);
                        if (isEndMessage(message)) {
                            break;
                        }
                    }
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        });

        scannerThread.start();
    }

    private void openConnection() throws IOException {
        socket = new Socket("localhost", 8189);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        listenThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    while (true) {
                        final String message = in.readUTF();
                        if (isEndMessage(message)) {
                            break;
                        }
                        System.out.println("Сообщение от сервера: " + message);
                    }
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        });

        listenThread.start();
    }
}
