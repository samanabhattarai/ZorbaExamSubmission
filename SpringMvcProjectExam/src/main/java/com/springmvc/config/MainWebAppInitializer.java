package com.springmvc.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class MainWebAppInitializer implements WebApplicationInitializer {

    private static final String TMP_FOLDER = "c:/tmp";
    private static final int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        ServletRegistration.Dynamic appServlet = sc.addServlet("mvc", new DispatcherServlet (
                new GenericWebApplicationContext ()));

        appServlet.setLoadOnStartup(1);

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER,
                MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2L, MAX_UPLOAD_SIZE / 2);

        appServlet.setMultipartConfig(multipartConfigElement);
    }
}