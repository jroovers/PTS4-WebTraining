/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoSupportWeb.utility;

import java.io.IOException;
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
    static final Logger logger = Logger.getLogger(Database.class.getName());

    /**
     * Creates a new instance of Database
     */
    public Database() {
        Properties prop = new Properties();
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            prop.load(ec.getResourceAsStream("/WEB-INF/config.properties"));

            ds = new BasicDataSource();
            ds.setDriverClassName(prop.getProperty("driver"));
            ds.setUrl(prop.getProperty("url"));
            ds.setUsername(prop.getProperty("username"));
            ds.setPassword(prop.getProperty("password"));
        } catch (IOException ex) {
            logger.log(Level.WARNING, ex.toString());
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
