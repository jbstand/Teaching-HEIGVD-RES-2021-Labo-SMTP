package ch.heigvd.MelMot;

import java.io.IOException;
import java.util.List;

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

        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }

    }
}
