package ch.heigvd.MelMot.mail;

/**
 * @file Person.java
 * @authors Jérémie Melly & Alexandre Mottier
 * @date 05.03.2021
 *
 * @description Cette classe permet la représentation d'une personne
 */
public class Person {
    /* Attributs */
    private String first_name;
    private String last_name;
    private final String address;

    /*  Constructor */
    public Person(String address) {
        this.address = address;
    }


    /* Getters & Setters */
    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }
}
