package com.stackroute.MuzixApplicationTask.config;

import com.stackroute.MuzixApplicationTask.domain.Track;
import com.stackroute.MuzixApplicationTask.exception.TrackAlreadyExistsException;
import com.stackroute.MuzixApplicationTask.services.TrackServices;
import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Configuration
public class WebConfiguration {

        //create trackservice object
        TrackServices trackServices;
          public WebConfiguration(TrackServices trackServices)
          {
              this.trackServices=trackServices;
          }
          //we use ContextRefreshedEvent
          @EventListener
          public void seedData(ContextRefreshedEvent contextRefreshedEvent) {
              try {
                  trackServices.saveTrack(new Track(1, "chainsmokers", "Excellent"));
                  trackServices.saveTrack(new Track(2, "one direction", "Excellent"));
                  trackServices.saveTrack(new Track(3, "vidyavox", "Excellent"));
              }
              catch (TrackAlreadyExistsException ex) {
                  ex.printStackTrace();
              }
          }
    @Bean
    //bean for h2 console
    ServletRegistrationBean h2servletRegistration()
    {
        ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(new WebServlet());
        servletRegistrationBean.addUrlMappings("/console/*");
        return servletRegistrationBean;
    }
}
