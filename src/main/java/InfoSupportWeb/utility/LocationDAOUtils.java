/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoSupportWeb.utility;

import Persistance.Interfaces.ILocationDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

/**
 *
 * @author Jorian
 */
public class LocationDAOUtils implements ILocationDAO {

    static final String QUERY_GET_LOCATIONS = "SELECT * FROM Location";
    static final String QUERY_INSERT_LOCATION = "INSERT INTO Location" + "(Name) VALUES" + "(?)";
    static final String QUERY_REMOVE_LOCATION = "DELETE FROM Location WHERE Name = ?";

    //Error handling
    private static final String SQLERROR = "SQL Exception code ";
    private final static Logger LOGGER = Logger.getLogger(LocationDAOUtils.class.getName());

    @Override
    public List<String> getLocations() {

        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ArrayListHandler alh = new ArrayListHandler();
        List<String> locations = new ArrayList<>();
        try {
            List<Object[]> result = run.query(QUERY_GET_LOCATIONS, alh);
            for (Object[] o : result) {

                locations.add(o[1] == null ? null : o[1].toString());
            }
        } catch (SQLException ex_sql) {
            LOGGER.log(Level.SEVERE, SQLERROR + ex_sql.getErrorCode(), ex_sql);
            LOGGER.log(Level.SEVERE, ex_sql.getMessage(), ex_sql);
        }
        return locations;
    }

    @Override
    public boolean addLocation(String locationName) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{locationName};
        try {
            run.insert(QUERY_INSERT_LOCATION, rsh, params);
            return true;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to add location in DB - errorCode: " + ex.getErrorCode(), ex);
            return false;
        }
    }

    @Override
    public boolean removeLocation(String locationName) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{locationName};
        try {
            run.execute(QUERY_REMOVE_LOCATION, rsh, params);
            return true;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to remove location from DB - errorCode: " + ex.getErrorCode(), ex);
            return false;
        }
    }

}
