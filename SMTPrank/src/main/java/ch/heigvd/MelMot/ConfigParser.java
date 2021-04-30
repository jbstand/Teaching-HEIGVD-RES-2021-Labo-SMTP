package ch.heigvd.MelMot;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class ConfigParser {
    Properties props;
    public ConfigParser(String file_name){
        try{
            String path = this.getClass().getClassLoader().getResource(file_name).getPath();
            System.out.println(path);
            InputStream file = new FileInputStream(path);
            this.props = new Properties();
            this.props.load(file);
        }catch(FileNotFoundException e){
            System.out.println("File not found...");
        }catch(IOException e){
            System.out.println("File not readable");
        }
    }

    public static void main(String[] args) {
        ConfigParser parser = new ConfigParser("config.properties");
        parser.getProperties("witnessesToCC");
    }

    public void getProperties(String properties_name){
        System.out.println(this.props.get(properties_name));
    }
}
