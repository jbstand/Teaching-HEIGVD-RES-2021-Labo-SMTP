package ch.heigvd.MelMot;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConfigParser {
    Properties props;
    private static final String config_file = "config.properties";
    private static final String messages_file = "messages.utf8";
    private static final String victims_file = "victims.utf8";
    private List<String> messages = new ArrayList<>();
    private List<String> victims = new ArrayList<>();

    public ConfigParser(){
        // Config File
        String path;
        InputStream file = null;
        try{
            path = this.getClass().getClassLoader().getResource(config_file).getPath();
            file = new FileInputStream(path);
            this.props = new Properties();
            this.props.load(file);
        }catch(FileNotFoundException e){
            System.out.println("File not found...");
        }catch(IOException e){
            System.out.println("File not readable");
        }
        // Messages File
        try {
            path = this.getClass().getClassLoader().getResource(messages_file).getPath();
            file = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        String line;
        String message = "";
        try {
            while ((line = reader.readLine()) != null) {
                if(line.contains("===")){
                    messages.add(message);
                    message = "";
                }else {
                    message += line + "\r\n";
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Victims File
        try {
            path = this.getClass().getClassLoader().getResource(victims_file).getPath();
            file = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        reader = new BufferedReader(new InputStreamReader(file));
        try {
            while ((line = reader.readLine()) != null) {
                if(line.contains("@")){
                    victims.add(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ConfigParser();
    }

    public String getServerAddress(){
        return this.props.get("smtpServerAddress").toString();
    }

    public int getServerPort(){
        return (int)this.props.get("smtpServerPort");
    }

    public int getNbGroups() {
        return (int) this.props.get("nbOfGroups");
    }

    public List<Person> getWitnesses(){
        return stringToPerson(Arrays.asList(this.props.get("witnessesToCC").toString().split(",")));
    }

    public List<String> getMessages(){
        return messages;
    }

    public List<Person> getVictims(){
        return stringToPerson(this.victims);
    }

    private static List<Person> stringToPerson(List<String> strPerson){
        List<Person> result = new ArrayList<>();
        for(String person : strPerson) {
            Person p = new Person(person);
            String prefix = person.split("@")[0];
            if (prefix.contains(".")) {
                String[] prefix_split = prefix.split("\\.");
                p.setFirstName(prefix_split[0]);
                p.setLastName(prefix_split[1]);
            } else {
                p.setFirstName(prefix);
            }
            result.add(p);
        }
        return result;
    }
}
