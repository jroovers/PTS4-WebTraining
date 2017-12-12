package View.Pages;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import Controller.UserService;
import Model.User;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;
import org.primefaces.component.datatable.DataTable;

@ManagedBean(name = "manageAccountBean")
@ViewScoped
public class ManageAccountBean implements Serializable {

    private List<User> users;
    private List<User> filteredUsers;
    private Map<String, String> accountTypes;

    @Inject
    UserService us;

    @PostConstruct
    public void init() {
        users = us.getUsers();
        accountTypes = new LinkedHashMap<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public List<User> getFilteredUsers() {
        return filteredUsers;
    }
    
    public Map<String, String> getAccountTypes() {
        accountTypes = us.getAccountTypes();
        return accountTypes;
    }
    
    public void setAccountTypes(Map<String, String> accountTypes) {
        this.accountTypes = accountTypes;
    }

    public void setFilteredUsers(List<User> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("User Editted", Long.toString(((User) event.getObject()).getUserID()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", Long.toString(((User) event.getObject()).getUserID()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
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
//            case "AccessLevel":
//                accountTypes = getAccountTypes();
//                int accesLevel = Integer.valueOf(accountTypes.get(newValue.toString()));        
//                int oldAccesLevel = Integer.valueOf(accountTypes.get(oldValue.toString()));                  
//                user.setAccesLevel(accesLevel);
//                us.editAccountType(user.getUserID(), accesLevel, oldAccesLevel);
//                break;
            default:
                break;
        }

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Gebruiker aangepast", "Oud: " + oldValue + ", Nieuw:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
