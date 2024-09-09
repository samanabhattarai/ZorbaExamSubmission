package com.springmvc.dao;

import com.springmvc.entity.Role;
import com.springmvc.model.RoleModel;
import com.springmvc.model.UserModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.springmvc.entity.User;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    UserDaoImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String saveUser (UserModel userModel) {
        String res = "";
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            User user = new User (userModel.getName (), userModel.getEmail (), userModel.getMobile (), userModel.getUserName (), userModel.getPassword ());
            session.persist (user);
            tx.commit ();
        } catch (Exception e) {
            System.err.println (e.getMessage ());
            rollBack (tx);
            res = "User data failed to save";
        } finally {
            closeSession (session);
        }

        return "User data successfully saved";
    }


    public UserModel getUserById (int userId) {
        UserModel userModel = null;
        Session session = null;
        try {
            session = sessionFactory.openSession ();
            String userQuery = "FROM User u where u.userId = :userId";
            Query query = session.createQuery (userQuery);
            query.setInteger ("userId", userId);
            User user = (User) query.uniqueResult ();
            userModel = getUserModel (user);
        } catch (Exception e) {
            System.err.println (e.getMessage ());
        } finally {
            closeSession (session);
        }
        return userModel;
    }

    @Override
    public List<UserModel> getUsers () {
        Session session = null;
        List<UserModel> users = new ArrayList<> ();
        String userQuery = "FROM  User u";

        try {
            session = sessionFactory.openSession ();
            Query query = session.createQuery (userQuery);
            List<User> usersRow = (List<User>) query.list ();
            for (User user : usersRow) {
                UserModel userModel = getUserModel (user);
                users.add (userModel);
            }

        } catch (Exception e) {
            System.err.println (e.getMessage ());
        } finally {
            closeSession (session);
        }
        return users;
    }


    private static UserModel getUserModel (User user) {
        UserModel userModel = new UserModel ();
        userModel.setUserName (user.getUserName ());
        userModel.setEmail (user.getEmail ());
        userModel.setMobile (user.getMobile ());
        userModel.setPassword (user.getPassword ());
        userModel.setUserId (user.getUserId ());
        userModel.setName (user.getName ());
        Set<RoleModel> roles = new HashSet<> ();
        for (Role role : user.getRoles ()) {
            RoleModel roleModel = new RoleModel ();
            roleModel.setRoleName (role.getRoleName ());
            roleModel.setRoleId (role.getRoleId ());
            roles.add (roleModel);
        }
        List<RoleModel> roleModels = new ArrayList<> (roles);
        userModel.setRoles (roleModels);
        return userModel;
    }

    @Override
    public String updateUserRoles (int userId, String[] roleNames) {
        String res = "";
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            String userQuery = "FROM User u where u.userId = :userId";
            Query query = session.createQuery (userQuery);
            query.setInteger ("userId", userId);
            User user = (User) query.uniqueResult ();
            Set<Role> roles = new HashSet<> ();
            for (String roleName : roleNames) {
                System.out.println ("Getting role based on role name : " + roleName);
                Query roleQuery = session.createQuery ("FROM Role r where r.roleName = :roleName");
                roleQuery.setString ("roleName", roleName);
                Role role = (Role) roleQuery.uniqueResult ();
                if (role != null) {
                    roles.add (role);
                }
            }
            user.setRoles (roles);
            session.merge (user);
            tx.commit ();
            res = "User data successfully updated";
        } catch (Exception e) {
            System.err.println (e.getMessage ());
            rollBack (tx);
            res = "User data failed to update";
        } finally {
            closeSession (session);
        }

        return res;
    }

    @Override
    public String removeRolesFromUser (int userId, String[] roleNames) {
        String res = "";
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession ();
            tx = session.beginTransaction ();
            String userQuery = "FROM User u where u.userId = :userId";
            Query query = session.createQuery (userQuery);
            query.setInteger ("userId", userId);
            User user = (User) query.uniqueResult ();
            for (String roleName : roleNames) {
                for(Role role : user.getRoles()) {
                    if(role.getRoleName().equals(roleName)) {
                        user.getRoles().remove(role);
                    }
                }
            }
            session.merge(user);
            tx.commit ();
            res = "<h5>User roles successfully removed!</h5>";
        } catch (Exception e) {
            System.err.println (e.getMessage ());
            rollBack (tx);
            res = "<h5>User roles failed to remove!</h5>";
        } finally {
            closeSession (session);
        }

        return res;
    }

    @Override
    public UserModel getUserByUserNameAndPassword (String userName, String password) {
        UserModel userModel = null;
        Session session = null;
        try {
            session = sessionFactory.openSession ();
            String userQuery = "FROM User u where u.userName = :userName and u.password = :password";
            Query query = session.createQuery (userQuery);
            query.setString("userName", userName);
            query.setString("password", password);
            User user = (User) query.uniqueResult();
            userModel = getUserModel (user);
        } catch (Exception e) {
            System.err.println (e.getMessage ());
        } finally {
            closeSession (session);
        }
        return userModel;
    }

    public void rollBack (Transaction tx) {
        if (tx != null) {
            try {
                tx.rollback ();
            } catch (Exception e1) {
                System.err.println (e1.getMessage ());
            }
        }
    }



    public void closeSession (Session session) {
        if (session != null) {
            try {
                session.close ();
            } catch (Exception e) {
                System.err.println (e.getMessage ());
            }
        }
    }


}


