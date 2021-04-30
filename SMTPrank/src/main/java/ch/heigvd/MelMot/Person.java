package ch.heigvd.MelMot;

public class Person {
    private String first_name;
    private String last_name;
    private final String address;

    public Person(String address) {
        this.address = address;
    }

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
