package ch.heigvd.MelMot;

import java.util.ArrayList;
import java.util.List;

public class PrankGenerator {
    ConfigParser config;

    public PrankGenerator(){
        this.config = new ConfigParser();
    }

    public List<Prank> generatePrank(){
        List<Prank> pranks = new ArrayList<>();
        List<String> messages = this.config.getMessages();
        int numberOfGroups = this.config.getNbGroups();
        List<String> victims = this.config.getVictims();
        int numberOfVictims = victims.size();
        // Contrainte 2 victimes minimum par group
        if(numberOfVictims / numberOfGroups > 2){
            System.out.print("Not enough victims to generate " + numberOfGroups);
            System.out.print(" Groups, generating : " + numberOfVictims / 2);
            numberOfGroups = numberOfVictims / 2;
        }

//        List<Group> groups = generatePrank();
        return pranks;
    }

//    public List<Group> generateGroups(List<Person> victims, int numberOfGroups){
//        List<Person>
    }
}
