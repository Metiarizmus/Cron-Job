package connect_service;

import java.io.*;
import java.util.Properties;

public class PropertyInf {

    public Properties getConnectData() {
        InputStream fis;
        Properties properties = new Properties();

        try {
            fis = PropertyInf.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public Properties getPath() {
        InputStream fis;
        Properties properties = new Properties();

        try {
            fis = PropertyInf.class.getClassLoader().getResourceAsStream("path.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public Properties getSqlQuery() {
        InputStream fis;
        Properties property = new Properties();
        try {
            fis = PropertyInf.class.getClassLoader().getResourceAsStream("sql.properties");
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }
}
