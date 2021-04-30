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
        mail.setBody(this.message + "\r\n" + this.victimSender.getFirstName());
        List<String> addrList = new ArrayList<>();
        for(Person p : victimRecipients)
            addrList.add(p.getAddress());
        mail.setTo(addrList);
        addrList = new ArrayList<>();
        for(Person p : witnessesRecipients){
            addrList.add(p.getAddress());
        }
        mail.setCc(addrList);
        mail.setFrom(victimSender.getAddress());
        return mail;
    }

    public Person getVictimSender() {
        return victimSender;
    }

    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    public List<Person> getVictimRecipients() {
        return new ArrayList<>(victimRecipients);
    }

    public void setVictimRecipients(Person victimRecipient) {
        this.victimRecipients.add(victimRecipient);
    }

    public List<Person> getWitnessesRecipients() {
        return new ArrayList<>(witnessesRecipients);
    }

    public void setWitnessesRecipients(Person witnessesRecipient) {
        this.witnessesRecipients.add(witnessesRecipient);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
