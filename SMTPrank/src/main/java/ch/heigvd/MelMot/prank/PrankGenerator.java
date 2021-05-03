package ch.heigvd.MelMot.prank;

import ch.heigvd.MelMot.config.ConfigParser;
import ch.heigvd.MelMot.mail.Group;
import ch.heigvd.MelMot.mail.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @file PrankGenerator.java
 * @authors Jérémie Melly & Alexandre Mottier
 * @date 05.03.2021
 *
 * @description Cette classe permet la génération de prank et de groupes liés aux pranks
 */
public class PrankGenerator {

    /* Attribut */
    ConfigParser config;

    /* Constructor */
    public PrankGenerator(){
        this.config = new ConfigParser();
    }

    /**
     * Génère une liste de pranks
     * @return Une liste de pranks
     */
    public List<Prank> generatePrank(){
        List<Prank> pranks = new ArrayList<>();
        List<String> messages = this.config.getMessages();
        int numberOfGroups = this.config.getNbGroups();
        List<Person> victims = this.config.getVictims();
        int numberOfVictims = victims.size();
        // Contrainte 2 victimes minimum par group
        if((numberOfVictims / numberOfGroups) < 2){
            System.out.print("Not enough victims to generate " + numberOfGroups);
            System.out.print(" Groups, generating : " + numberOfVictims / 2);
            numberOfGroups = numberOfVictims / 2;
        }
        int messageIndex = 0;
        for(Group group : generateGroups(victims, numberOfGroups)){
            Prank prank = new Prank();
            List<Person> members = group.getMembers();
            Collections.shuffle(members);
            prank.setVictimSender(members.remove(0));
            prank.setVictimRecipients(members);
            prank.setWitnessesRecipients(this.config.getWitnesses());
            String message = messages.get(messageIndex);
            messageIndex = (messageIndex + 1) % messages.size();
            prank.setMessage(message);
            pranks.add(prank);
        }
        return pranks;
    }

    /**
     * Génère une liste de groupe de personnes
     * @param victims Listes des victimes
     * @param numberOfGroups Nombre de groupe à créer
     * @return Une liste de groupe
     */
    private List<Group> generateGroups(List<Person> victims, int numberOfGroups){
        List<Person> victimsLeft = new ArrayList<>(victims);
        Collections.shuffle(victimsLeft);
        List<Group> groups = new ArrayList<>();
        for(int i = 0; i < numberOfGroups; i++){
            Group group = new Group();
            groups.add(group);
        }

        int group_id = 0;
        Group currentGroup;
        while(victimsLeft.size() > 0){
            currentGroup = groups.get(group_id);
            group_id = (group_id + 1) % groups.size();
            Person victim = victimsLeft.remove(0);
            currentGroup.addMember(victim);
        }
        return groups;
    }
}
