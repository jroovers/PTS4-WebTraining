/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InfoSupportWeb.utility;

import static InfoSupportWeb.utility.CourseDAOUtils.QUERY_REMOVE_COURSE;
import Interfaces.ILocationDAO;
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
    static final String QUERY_REMOVE_LOCATION = "DELETE FROM Location WHERE ID_Location = ?";

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
            System.out.println("SQL Exception code " + ex_sql.getErrorCode());
            System.out.println(ex_sql.getMessage());
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
            Logger.getLogger(CourseDAOUtils.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Failed to add location to db");
            return false;
        }}

    @Override
    public boolean removeLocation(int location_ID) {
        QueryRunner run = new QueryRunner(Database.getInstance().getDataSource());
        ResultSetHandlerImp rsh = new ResultSetHandlerImp();
        Object[] params = new Object[]{location_ID};
        try {
            run.execute(QUERY_REMOVE_LOCATION, rsh, params);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAOUtils.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Failed to remove location from db");
            return false;
        }}

}
