/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoSupportWeb.utility;

import java.sql.*;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
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

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://77.174.23.144:306/db_dev_infosupport";
    private static String username = "infosupport";
    private static String password = "q!p^Fxyg6l3N'@n'a!f&#Jn3Y.Kwh.3!:gy&EU/cE&$JWoa+Z#L7sGyGKG//|yiv";

    /**
     * Creates a new instance of Database
     */
    public Database() {
        ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
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
    
    public BasicDataSource getDataSource(){
        return this.ds;
    }
}
