package ch.heigvd.MelMot.smtp;

import ch.heigvd.MelMot.config.ConfigParser;
import ch.heigvd.MelMot.mail.Mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.List;

/**
 * @file SMTPClient.java
 * @authors Jérémie Melly & Alexandre Mottier
 * @date 05.03.2021
 *
 * @description Cette classe permet la gestion du protocole SMTP
 */
public class SMTPClient {
    /* Attributs */
    private final PrintWriter out;
    private final BufferedReader in;
    final private String EOL = "\r\n";

    /* Constructor */
    public SMTPClient() throws IOException {
        ConfigParser config = new ConfigParser();
        Socket socket = new Socket(config.getServerAddress(), config.getServerPort());
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * Start connection with the server
     * @throws IOException
     */
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

    /**
     * Send mail trough SMTP server
     * @param mail mail to send
     * @throws IOException
     * @throws InterruptedException
     */
    public void sendMail(Mail mail) throws IOException, InterruptedException {
        /* Header FROM */
        String msg = "mail from: <" + mail.getFrom() + ">" + EOL;
        System.out.print("Send : " + msg);
        this.out.print(msg);
        this.out.flush();
        String response = this.in.readLine();
        System.out.println("Recieved: " + response);

        /* Header TO */
        for(String to : mail.getTo()){
            msg = "rcpt to: <" + to + ">" + EOL;
            System.out.print("Send : " + msg);
            this.out.print(msg);
            this.out.flush();
            response = this.in.readLine();
            System.out.println("Received : " + response);
        }

        /* Message itself */
        msg = "data" + EOL;
        System.out.print("Send : " + msg);
        this.out.print(msg);
        this.out.flush();
        response = this.in.readLine();
        System.out.println("Received : " + response);

        /* Message parameters */
        StringBuilder message = new StringBuilder();
        message.append("Date: ").append(new Date()).append(EOL);
        message.append("From: <").append(mail.getFrom()).append(">").append(EOL);
        message.append("Subject: ").append(mail.getSubject()).append(EOL);
        message.append("To: ").append(serialiseList(mail.getTo())).append(EOL);
        if(mail.getCc() != null)
            message.append("Cc: ").append(serialiseList(mail.getCc())).append(EOL);
        if(mail.getBcc() != null)
            message.append("Bcc: ").append(serialiseList(mail.getBcc())).append(EOL);

        /* Message body */
        message.append("Body: ").append(EOL).append(mail.getBody()).append(EOL);
        message.append(".").append(EOL); // End of message for SMTP server
        System.out.print("Send : " + message);
        this.out.print(message);
        this.out.flush();
        response = this.in.readLine();
        System.out.println("Received : " + response);
    }

    /**
     * End connection with SMTP server
     * @throws IOException
     */
    public void quit() throws IOException {
        String msg = "QUIT" + EOL;
        this.out.print(msg);
        this.out.flush();
        System.out.println("The connection is now closed...");
    }

    /**
     * Transform a list to a string
     * @param list list to transform
     * @return String with all elements of the list
     */
    private String serialiseList(List<String> list){
        StringBuilder msg = new StringBuilder();
        for(int i = 0; i < list.size(); ++i){
            msg.append("<").append(list.get(i)).append(">");
            if(i < list.size() - 1)
                msg.append(", ");
        }
        return msg.toString();
    }
}
