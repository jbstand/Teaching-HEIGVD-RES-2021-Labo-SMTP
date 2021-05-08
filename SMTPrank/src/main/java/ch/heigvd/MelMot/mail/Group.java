package ch.heigvd.MelMot.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * @file Group.java
 * @authors Jérémie Melly & Alexandre Mottier
 * @date 05.03.2021
 *
 * @description Cette classe permet la représentation d'un groupe de personne
 */
public class Group {

    /* Attribut */
    private final List<Person> members = new ArrayList<>();

    /**
     * Ajoute une personne au groupe
     * @param person Personne à ajouter au groupe
     */
    public void addMember(Person person){
        members.add(person);
    }

    /**
     * @return Retourne la liste des membres du groupe
     */
    public List<Person> getMembers() {
        return new ArrayList<>(members);
    }
}
