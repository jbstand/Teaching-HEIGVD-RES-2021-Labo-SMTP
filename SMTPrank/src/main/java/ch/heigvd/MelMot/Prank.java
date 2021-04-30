package ch.heigvd.MelMot;

import java.util.ArrayList;
import java.util.List;

public class Prank {
    private Person victimSender;
    private List<Person> victimRecipients = new ArrayList<>();
    private List<Person> witnessesRecipients = new ArrayList<>();
    private String message;

    public Mail generateMail(){
        Mail mail = new Mail();
        return mail;
    }

    public Person getVictimSender() {
        return victimSender;
    }

    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    public List<Person> getVictimRecipients() {
        return victimRecipients;
    }

    public void setVictimRecipients(List<Person> victimRecipients) {
        this.victimRecipients = victimRecipients;
    }

    public List<Person> getWitnessesRecipients() {
        return witnessesRecipients;
    }

    public void setWitnessesRecipients(List<Person> witnessesRecipients) {
        this.witnessesRecipients = witnessesRecipients;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
