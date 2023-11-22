import java.io.*; import java.net.Socket;
public class client{
    public static void main(String[] args){
    String SerevrAdd = "localhost"; int port = 8888;
    try{
        Socket s = new Socket(SerevrAdd, port);
        System.out.println("Connected to server on port: " + port);
        System.out.println(" ");
        String message = "Hello I am client";
        BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter w = new PrintWriter(s.getOutputStream(), true);
        w.println(message);
        String response = r.readLine();
        System.out.println("Server response: " + response);
        s.close();
    }catch(Exception e){
        System.out.println(e);
    }
}
}