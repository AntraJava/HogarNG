package com.hogarcontrols.hogarcloud.fleet.fleetmanager;

import com.hogarcontrols.hogarcloud.fleet.fleetmanager.cert.X509CertGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FleetManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FleetManagerApplication.class, args);
    }

}
