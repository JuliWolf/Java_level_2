package Lesson_6.Homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer implements IIsEndMessage{
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private Thread listenThread;
    private Thread scannerThread;

    public static void main(String[] args) {
        EchoServer server = new EchoServer();
        server.start();
    }

    private void start () {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            startScanner();
            startListener();

            listenThread.join();

        } catch (IOException err) {
            err.printStackTrace();
            throw new RuntimeException(err);
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    private void startListener () {
        listenThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        final String message = in.readUTF();

                        if (isEndMessage(message)) {
                            out.writeUTF(message);
                            break;
                        }
                        out.writeUTF("echo: " + message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        listenThread.start();
    }

    private void startScanner () throws IOException {
        final Scanner scanner = new Scanner(System.in);
        scannerThread = new Thread(new Runnable() {

            @Override
            public void run() {
                final String message = scanner.nextLine();
                try {
                    if (isEndMessage(message)) {
                        out.writeUTF(message);
                        return;
                    }

                    out.writeUTF("New: " + message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        scannerThread.start();
    }
}
