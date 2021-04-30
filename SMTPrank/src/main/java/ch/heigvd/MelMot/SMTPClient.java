package ch.heigvd.MelMot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class SMTPClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    final private String splitChar = " ";
    final private String EOL = " \r\n";

    public SMTPClient(String url, int port) throws IOException {
        this.socket = new Socket(url,port);
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    public void startConnection() throws IOException {
        String msg = "EHLO heig-vd.ch" + EOL;
        this.out.print(msg);
        this.out.flush();
    }

    public void sendMail(Mail mail) {
        String msg = "mail from: <" + mail.getFrom() + ">" + EOL;
        this.out.print(msg);
        this.out.flush();

        msg = "rcpt to: <" + Arrays.toString(mail.getTo()) + ">" + EOL;
        this.out.print(msg);
        this.out.flush();

        msg = "data" + EOL;
        this.out.print(msg);
        this.out.flush();

        msg = "from: <" + mail.getFrom() + ">" + EOL;


        this.out.print(msg);
        this.out.flush();
    }

    private String serialiseTos()

    public static void main(String[] args) {
        try{
            SMTPClient smtp = new SMTPClient("localhost", 8888);
            smtp.startConnection();

        }
        catch (IOException e){
            System.out.println(e);
        }


    }

}
