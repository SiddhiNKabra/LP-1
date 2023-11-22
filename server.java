import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class server{
    public static void main(String[] args){
        int port = 8888;
        try(ServerSocket s = new ServerSocket(port)){
            System.out.println("Server started and running at port: "+ port);
            while(true){
                Socket client = s.accept();
                System.out.println(client.getInetAddress());
                ClientThread c = new ClientThread(client);
                c.start();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
class ClientThread extends Thread{
    private Socket clientsocket;
    public ClientThread(Socket clientsocket){
        this.clientsocket = clientsocket;
    }
    @Override
    public void run(){
        try{
            BufferedReader r = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
            PrintWriter w = new PrintWriter(clientsocket.getOutputStream(), true);
            String inputline = r.readLine();
            System.out.println("Received from cleint" + inputline);
            System.out.println(" ");
            w.println(inputline);
            System.out.println("Client Disconnected");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}