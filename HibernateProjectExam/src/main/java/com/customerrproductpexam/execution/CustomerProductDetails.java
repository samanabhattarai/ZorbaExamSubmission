package com.customerrproductpexam.execution;

import com.customerrproductpexam.entity.Customer;
import com.customerrproductpexam.entity.Product;
import com.customerrproductpexam.entity.ProductType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class CustomerProductDetails {

    public static void main(String[] args) throws CustomException {


        Scanner scanner = new Scanner(System.in);

        System.out.println("enter customer c01 ");
        String customerId = scanner.next();

        if (customerId.length() != 6) {
            throw new CustomException("Invalid customer id length");
        }

        System.out.println("enter yor customer name: ");
        String customerName = scanner.next();

        System.out.println("enter your customer email: ");
        String customerEmail = scanner.next();

        System.out.println("enter  your mobile number: ");
        long customerMobile = scanner.nextLong();
        if (String.valueOf(customerMobile).length() != 10) {
            throw new CustomException("Invalid mobile number length");
        }
        System.out.println("enter your product Id P01: ");
        String productId = scanner.next();
        if (productId.length() != 6) {
            throw new CustomException("invalid customer id length");
        }

        System.out.println("enter your product name: ");
        String productName = scanner.next();

        System.out.println("enter your product quantity: ");
        double productQuantity = scanner.nextDouble();

        System.out.println("enter product type,grocery and cosmetics and ordairyProduct): ");
        String productType = scanner.next();

        ProductType productTypeEntity = getSessionFactory().openSession()
                .createQuery("from ProductType where productType = :productType", ProductType.class)
                .setParameter("productType", productType)
                .getSingleResult();
        double rate = productTypeEntity.getRate();


        double finalProductPrice = productQuantity * rate;


        Customer customer = new Customer();
        customer.setCustId(customerId);
        customer.setCustName(customerName);
        customer.setCustEmail(customerEmail);
        customer.setCustMobile(customerMobile);
        customer.setFinalProductPrice(finalProductPrice);

        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductQuantity(productQuantity);
        product.setCustomer(customer);
        product.setProductType(productTypeEntity);


        Session session = getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            //customer.setFinalProductPrice(finalProductPrice);
            session.persist(customer);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }


        try {
            tx = session.beginTransaction();

            session.save(product);
            tx.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public static SessionFactory getSessionFactory() {
        //Reading Configuration file
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        //Added Annotated entity Class
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(ProductType.class);

        //Create SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }
}
    class CustomException extends Exception {
        public CustomException(String message) {
            super(message);
        }
    }
