package com.digitalTMC.util;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class DynamicEnumUtils {
    public static int getOfficerNumber(String officerName){
        ResourceBundle officerBundle = ResourceBundle.getBundle("officer");
        Enumeration<String> officers = officerBundle.getKeys();
        while (officers.hasMoreElements()){
            String officer = officers.nextElement();
            if(officer.equals(officerName))
                return Integer.parseInt(officerBundle.getString(officer));
        }
        return -1;
    }

    public static int getCatalogNumber(String catalogName){
        ResourceBundle catalogBundle = ResourceBundle.getBundle("catalog");
        Enumeration<String> catalogs = catalogBundle.getKeys();
        while (catalogs.hasMoreElements()){
            String catalog = catalogs.nextElement();
            if(catalog.equals(catalogName))
                return Integer.parseInt(catalogBundle.getString(catalog));
        }
        return -1;
    }
}