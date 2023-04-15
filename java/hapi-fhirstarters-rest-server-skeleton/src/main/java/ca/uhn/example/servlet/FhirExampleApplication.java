package ca.uhn.example.servlet;

package com.example.fhirexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FhirExampleApplication{

   @Autowired
   private ApplicationContext context;


   public static void main(String[] args) {

      SpringApplication.run(FhirExampleApplication.class, args);
   }

   @Bean
   public ServletRegistrationBean ServletRegistrationBean() {
      ServletRegistrationBean registration= new ServletRegistrationBean(new ExampleRestfulServlet(context),"/*");
      registration.setName("FhirServlet");
      return registration;
   }
}
