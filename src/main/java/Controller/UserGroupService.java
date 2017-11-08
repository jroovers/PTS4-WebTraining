/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.UserGroup;
import Persistance.DbUtilsImpl.UserGroupDAODbUtilsImpl;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Jeroen Roovers
 */
@Stateless
public class UserGroupService {

    public UserGroupService() {
    }

    public boolean addUserGroup(String name) {
        UserGroupDAODbUtilsImpl UserGroupDAO = new UserGroupDAODbUtilsImpl();
        return UserGroupDAO.addUserGroup(name);
    }

    public List<UserGroup> getAllUserGroups() {
        UserGroupDAODbUtilsImpl UserGroupDAO = new UserGroupDAODbUtilsImpl();
        return UserGroupDAO.getAllUserGroups();
    }

    public boolean updateUserGroup(UserGroup to_change) {
        UserGroupDAODbUtilsImpl UserGroupDAO = new UserGroupDAODbUtilsImpl();
        return UserGroupDAO.updateUserGroup(to_change);
    }

    public boolean removeUserGroup(UserGroup to_delete) {
        UserGroupDAODbUtilsImpl UserGroupDAO = new UserGroupDAODbUtilsImpl();
        return UserGroupDAO.removeUseGroup(to_delete);
    }
}
