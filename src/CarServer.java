import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CarServer {
	protected int car1a, car1b, car2a, car2b;
	public static void main(String[] args) throws Exception{
        System.out.println("The game server is running.");
        int clientNo = 1;
        ServerSocket listener = new ServerSocket(1234);
        try {
            while (true) {
                new carComm(listener.accept(), clientNo++).start();
            }
        } finally {
            listener.close();
        }
    }
	
	private static class carComm extends Thread {
        private Socket s;
        private int clientNum;

        public carComm(Socket s, int clientNum) {
            this.s = s;
            this.clientNum = clientNum;
            log("New connection with players# " + clientNum + " at " + s);
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);

                // To client
                out.println("You are#" + clientNum + " player.");

                // From client
                while (true) {
                    //Get the input
                    String input = in.readLine();
                    log("Received " + input);

                    //Get the input, change the velocity & position of the car
                    if (clientNum == 1) {
                        // car1a = input;
                        // car1b = input;
                    }
                    if (clientNum == 2) {
                        // car2a = input;
                        // car2b = input;
                    }
                }
            } catch (Exception e) {
                log("Error handling client# " + clientNum + ": " + e);
            } finally {
                try {
                    s.close();
                } catch (IOException e) {
                    log("Socket couldn't close!");
                }
                log("Connection with client# " + clientNum + " is closed.");
            }
        }
        private void log(String message) {
            System.out.println(message);
        }
    }
}
