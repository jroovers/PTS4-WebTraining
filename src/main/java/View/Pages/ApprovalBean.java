package View.Pages;

import Model.Registration;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Jeroen Roovers
 */
@Named(value = "approvalBean")
@RequestScoped
public class ApprovalBean {

    private Registration selectedReqistration;
    private List<Registration> allRegistrations;

    /**
     * Creates a new instance of ApprovalBean
     */
    public ApprovalBean() {
    }

    public Registration getSelectedReqistration() {
        return selectedReqistration;
    }

    public void setSelectedReqistration(Registration selectedReqistration) {
        this.selectedReqistration = selectedReqistration;
    }

    public List<Registration> getAllRegistrations() {
        return allRegistrations;
    }

    public void setAllRegistrations(List<Registration> allRegistrations) {
        this.allRegistrations = allRegistrations;
    }

    
}
