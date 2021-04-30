package ch.heigvd.MelMot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SMTPClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String commonSeparator = " ";
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
        String response = "";
        while((response = this.in.readLine()) != null){
//            String[] message = response.split(commonSeparator);
            System.out.println("Recieved: " + response);
            switch(response){
                case "250 Ok":
                    System.out.print("Connection opened...");

            }

        }
    }

    public void sendMail(Mail mail) throws IOException, InterruptedException {
        String msg = "mail from: <" + mail.getFrom() + ">" + EOL;
        System.out.print("Send : " + msg);
        this.out.print(msg);
        this.out.flush();
        String response = this.in.readLine();
        System.out.println("Recieved: " + response);

        msg = "rcpt to: <" + serialiseTos(mail.getTo()) + ">" + EOL;
        System.out.print("Send : " + msg);
        this.out.print(msg);
        this.out.flush();
        response = this.in.readLine();
        System.out.println("Received : " + response);


        msg = "data" + EOL;
        System.out.print("Send : " + msg);
        this.out.print(msg);
        this.out.flush();
        response = this.in.readLine();
        System.out.println("Received : " + response);


        msg =  "from: <" + mail.getFrom() + ">" + EOL;
        msg += "to: " + serialiseTos(mail.getTo()) + EOL;
        msg += "date: " + new Date() + EOL;
        msg += "subject: " + mail.getSubject() + EOL;
        msg += "body: " + mail.getBody() + EOL;
        msg += "." + EOL;

        System.out.print("Send : " + msg);
        this.out.print(msg);
        this.out.flush();
        response = this.in.readLine();
        System.out.println("Received : " + response);
    }

    private String serialiseTos(List<String> tos){
        StringBuilder msg = new StringBuilder();
        for(String to : tos)
            msg.append("<").append(to).append(">");
        return msg.toString();
    }

    public static void main(String[] args) {
        try{
            SMTPClient smtp = new SMTPClient("localhost", 8888);
            smtp.startConnection();

            Mail mail = new Mail();
            List<String> tos = new ArrayList<>();
            tos.add("jeremie.melly@hes-so.ch");
            mail.setFrom("alexandre.mottier@hes-so.ch");
            mail.setTo(tos);
            mail.setSubject("Test3");
            mail.setBody("On aime faire des tests");
            smtp.sendMail(mail);

        }
        catch (IOException | InterruptedException e){
            System.out.println(e);
        }


    }

}
