package com.inigoserrano.docker.aplicacion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Propiedades {
	
	public static String getPropiedad(final String propiedad) {
		try (InputStream input = new FileInputStream("/properties/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            System.out.println("El valor para "+propiedad+" es: "+prop.getProperty(propiedad));
            return prop.getProperty(propiedad);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }	
	}

	public static String getSecreto(final String propiedad) {
		try (InputStream input = new FileInputStream("/secretos/secretos.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            System.out.println("El valor para "+propiedad+" es: "+prop.getProperty(propiedad));
            return prop.getProperty(propiedad);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }	
	}


}
