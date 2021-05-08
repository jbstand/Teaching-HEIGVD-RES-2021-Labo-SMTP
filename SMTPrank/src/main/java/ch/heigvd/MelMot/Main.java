package ch.heigvd.MelMot;

import ch.heigvd.MelMot.prank.Prank;
import ch.heigvd.MelMot.prank.PrankGenerator;
import ch.heigvd.MelMot.smtp.SMTPClient;

import java.io.IOException;
import java.util.List;

/**
 * @file Main.java
 * @authors Jérémie Melly & Alexandre Mottier
 * @date 05.03.2021
 *
 * @description Main
 */
public class Main {
    public static void main(String[] args) {
        PrankGenerator prankypranky = new PrankGenerator();
        List<Prank> pranks = prankypranky.generatePrank();
        try{
            SMTPClient client = new SMTPClient();
            client.startConnection();
            for(Prank prank : pranks){
                client.sendMail(prank.generateMail());
            }
            client.quit();
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }

    }
}
