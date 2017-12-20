package View.Pages;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import Controller.UserService;
import Model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import javax.inject.Inject;
import org.primefaces.component.datatable.DataTable;

@ManagedBean(name = "manageAccountBean")
@ViewScoped
public class ManageAccountBean implements Serializable {

    /**
     * Map of the users and a list of their permissions, in boolean format. 
     * e.g. admin has true,true,true,true
     */
    private final transient Map<User, List<Boolean>> permissions = new HashMap<>();
    private transient List<User> users = new ArrayList<>();
    @Inject
    UserService us;

    //amount of different accesslevels in the database.
    private final long accessLevelAmount = 4;

    @PostConstruct
    public void init() {
        users = us.getUsers();

        //convert the user accesslevels to the boolean format, so we can bind it to checkboxes
        for (User u : users) {
            List<Boolean> accessLevels = new ArrayList<>();
            for (long i = 1; i <= accessLevelAmount; i++) {
                accessLevels.add(u.getAccessLevels().contains(i));
            }
            permissions.put(u, accessLevels);
        }
    }

    public Map<User, List<Boolean>> getPermissions() {
        return permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        DataTable dataTable = (DataTable) event.getSource();
        User user = (User) dataTable.getRowData();

        String column_name;
        column_name = event.getColumn().getHeaderText();

        switch (column_name) {
            case "Naam":
                user.setName(newValue.toString());
                us.editUser(user);
                break;
            case "Achternaam":
                user.setSurname(newValue.toString());
                us.editUser(user);
                break;
            case "Username":
                user.setUsername(newValue.toString());
                us.editUser(user);
                break;
            case "TelNr":
                user.setPhoneNr(newValue.toString());
                us.editUser(user);
                break;
            case "Email":
                user.setEmail(newValue.toString());
                us.editUser(user);
                break;
            default:
                break;
        }
        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Gebruiker aangepast", "Oud: " + oldValue + ", Nieuw:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onCheckBoxEdit(User user) {
        //us.editAccountType(user);
        user.setAccessLevels(new HashSet<>());

        //convert the boolean list back to the user accesslevel list
        for (long i = 0; i < accessLevelAmount; i++) {
            if (permissions.get(user).get((int) i)) { //if the users permissionlist [3] = true, add permission 4 to the accesslevel list.
                user.addAccessLevel(i + 1); //accesslevels start at 1, the booleanlist starts at 0
            }
        }
        us.editAccountType(user);
    }
}
