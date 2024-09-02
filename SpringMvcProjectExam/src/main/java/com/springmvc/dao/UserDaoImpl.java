package com.springmvc.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.springmvc.entity.User;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
public class UserDaoImpl implements UserDao {

      SessionFactory sessionFactory;

        UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

        @Override
        public String saveUser(User user){

            String res ="";
            Session session = this.sessionFactory.openSession();
            Transaction tx =null;


            try{
                session.beginTransaction();
                session.persist(user);
                tx.commit();
                res="User data successfully saved";
            }
            catch(Exception e){

                System.err.println(e.getMessage());
                tx.rollback();
                res="User data failed to save";
            }
            finally{
                if(session != null){
                    session.close();

                }
            }

        return res;
    }

    public User getUserById(Integer userId) {

            User user = new User();
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;

        try {

            String userQuery = "from User_info u where u.userId = :userId";
            Query query = session.createQuery(userQuery);

            query.setInteger("userId", userId);
            user = (User) query.uniqueResult();
        } catch (Exception e) {
            System.err.println(e.getMessage());

        } finally {
            if (session != null) {
                session.close();
            }


        }
        return user;
    }

        @Override
        public List<User> getUsers() {

            List<User> users = new ArrayList<>();
         Session session =  this.sessionFactory.openSession();

         String userQuery = "from User_info";

         try {
             Query query = session.createQuery(userQuery);

             users = query.list();
         } catch (Exception e) {

             System.err.println(e.getMessage());
         }
         finally{
             if(session != null){
                 session.close();
             }
         }

        return users;
    }

    }
