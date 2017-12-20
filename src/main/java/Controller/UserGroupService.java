/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Course;
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

    public boolean addUserGroup(String name) {
        UserGroupDAODbUtilsImpl UserGroupDAO = new UserGroupDAODbUtilsImpl();
        return UserGroupDAO.addUserGroup(name);
    }

    public List<UserGroup> getAllUserGroups() {
        UserGroupDAODbUtilsImpl UserGroupDAO = new UserGroupDAODbUtilsImpl();
        return UserGroupDAO.getAllUserGroups();
    }

    public boolean removeUserGroup(UserGroup to_delete) {
        UserGroupDAODbUtilsImpl UserGroupDAO = new UserGroupDAODbUtilsImpl();
        return UserGroupDAO.removeUseGroup(to_delete);
    }

    public boolean removeUserGroup_ByName(String name) {
        UserGroupDAODbUtilsImpl UserGroupDAO = new UserGroupDAODbUtilsImpl();
        return UserGroupDAO.removeUseGroup_ByName(name);
    }

    public List<UserGroup> getUserGroupsFromCourse(Course course) {
        return getUserGroupsFromCourse(course.getId());
    }

    public List<UserGroup> getUserGroupsFromCourse(long course_id) {
        UserGroupDAODbUtilsImpl UserGroupDAO = new UserGroupDAODbUtilsImpl();
        return UserGroupDAO.getUsergroupsByCourse(course_id);
    }

    public boolean addUserGroupToCourse(long userGroup_id, long course_id) {
        UserGroupDAODbUtilsImpl UserGroupDAO = new UserGroupDAODbUtilsImpl();
        return UserGroupDAO.addUsergroupToCourse(userGroup_id, course_id);
    }
    
    public boolean removeUserGroupFromCourse(long userGroup_id, long course_id) {
        UserGroupDAODbUtilsImpl UserGroupDAO = new UserGroupDAODbUtilsImpl();
        return UserGroupDAO.removeUsergroupFromCourse(userGroup_id, course_id);
    }
}
