package ch.heigvd.MelMot.config;

import ch.heigvd.MelMot.mail.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @file ConfigParser.java
 * @authors Jérémie Melly & Alexandre Mottier
 * @date 05.03.2021
 *
 * @description Cette classe permet de parser les fichiers de config et générer les datas que nous souhaitons avoir
 */
public class ConfigParser {

    /* Attributs */
    Properties props;
    private static final String config_file = "config.properties";
    private static final String messages_file = "messages.utf8";
    private static final String victims_file = "victims.utf8";
    private final List<String> messages = new ArrayList<>();
    private final List<String> victims = new ArrayList<>();

    /* Constructor */
    public ConfigParser(){
        String path;
        InputStream file;

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

        generateMessagesFromFile();
        generateVictimsFromFile();

    }
     /* Getters & Setters */
    public String getServerAddress(){
        return this.props.get("smtpServerAddress").toString();
    }

    public int getServerPort(){
        return Integer.parseInt(this.props.get("smtpServerPort").toString());
    }

    public int getNbGroups() {
        return Integer.parseInt(this.props.get("nbOfGroups").toString());
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

    /**
     * Création des objets Person grâce à des Strings
     * @param strPerson Liste de string ayant les noms des personnes
     * @return La liste avec les objets personnes
     */
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

    /**
     * Génère les victimes grâce au fichier de config
     */
    private void generateVictimsFromFile() {
        InputStream file = generateInputStream(victims_file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(file));
        String line;
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

    /**
     * Fonction utilitaire générant un inputstream pour le nom du fichier voulu
     * @param filename Nom du fichier à générer
     * @return Un inputStream
     */
    private InputStream generateInputStream(String filename){
        String path;
        InputStream file = null;
        try {
            path = this.getClass().getClassLoader().getResource(filename).getPath();
            file = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }


    /**
     * Génére les messages depuis le fichier de messages
     */
    private void generateMessagesFromFile(){
        InputStream file = generateInputStream(messages_file);
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
    }
}
