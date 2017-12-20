/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoSupportWeb.utility;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Jeroen Roovers
 */
@ManagedBean
@ApplicationScoped
public class Database {

    private static Database db;
    private BasicDataSource ds;
    static final Logger LOGGER = Logger.getLogger(Database.class.getName());

    /**
     * Creates a new instance of Database
     */
    public Database() {
        String filePath = "config.properties";
        Properties prop = new Properties();
        try (final InputStream stream = this.getClass().getClassLoader().getResourceAsStream(filePath)) {
            prop.load(stream);
            ds = new BasicDataSource();
            ds.setDriverClassName(prop.getProperty("driver"));
            ds.setUrl(prop.getProperty("url"));
            ds.setUsername(prop.getProperty("username"));
            ds.setPassword(prop.getProperty("password"));

        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Database getInstance() {
        if (db == null) {
            db = new Database();
            return db;
        } else {
            return db;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }

    public BasicDataSource getDataSource() {
        return this.ds;
    }
}
