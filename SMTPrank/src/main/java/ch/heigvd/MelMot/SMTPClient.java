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
    final private String EOL = "\r\n";

    public SMTPClient() throws IOException {
        ConfigParser config = new ConfigParser();
        this.socket = new Socket(config.getServerAddress(), config.getServerPort());
        this.out = new PrintWriter(this.socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    public void startConnection() throws IOException {
        String msg = "EHLO heig-vd.ch" + EOL;
        this.out.print(msg);
        this.out.flush();
        String response = "";
        while((response = this.in.readLine()) != null){
            System.out.println("Received: " + response);
            if(response.contains("250 Ok"))
                break;
        }
    }

    public void sendMail(Mail mail) throws IOException, InterruptedException {
        String msg = "mail from: <" + mail.getFrom() + ">" + EOL;
        System.out.print("Send : " + msg);
        this.out.print(msg);
        this.out.flush();
        String response = this.in.readLine();
        System.out.println("Recieved: " + response);

        for(String to : mail.getTo()){
            msg = "rcpt to: <" + to + ">" + EOL;
            System.out.print("Send : " + msg);
            this.out.print(msg);
            this.out.flush();
            response = this.in.readLine();
            System.out.println("Received : " + response);
        }



        msg = "data" + EOL;
        System.out.print("Send : " + msg);
        this.out.print(msg);
        this.out.flush();
        response = this.in.readLine();
        System.out.println("Received : " + response);


        StringBuilder message = new StringBuilder();
        message.append("from: <").append(mail.getFrom()).append(">").append(EOL);
        message.append("to: ").append(serialiseTos(mail.getTo())).append(EOL);
        message.append("date: ").append(new Date()).append(EOL);
        message.append("subject: ").append(mail.getSubject()).append(EOL);
        message.append("body: ").append(EOL).append(mail.getBody()).append(EOL);
        message.append(".").append(EOL);

        System.out.print("Send : " + message);
        this.out.print(message);
        this.out.flush();
        response = this.in.readLine();
        System.out.println("Received : " + response);
    }

    public void quit() throws IOException {
        String msg = "QUIT" + EOL;
        this.out.print(msg);
        this.out.flush();
        System.out.println("The connection is now closed...");
    }

    private String serialiseTos(List<String> tos){
        StringBuilder msg = new StringBuilder();
        msg.append("<");
        for(int i = 0; i < tos.size(); ++i){
            msg.append(tos.get(i));
            if(i < tos.size() - 1)
                msg.append(",");
        }
        msg.append(">");
        return msg.toString();
    }


    public static void main(String[] args) {
        try{
            SMTPClient smtp = new SMTPClient();
            smtp.startConnection();

            Mail mail = new Mail();
            List<String> tos = new ArrayList<>();
            tos.add("jeremie.melly@hes-so.ch");
            tos.add("alex_mottier@hotmail.com");

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
