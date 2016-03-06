package pl.kamrar.zarcie;

import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.kamrar.zarcie.example.complex.ComplexStaticServer;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    @Autowired
    private ComplexStaticServer staticServer;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void deployVerticle() {
        Vertx.vertx().deployVerticle(staticServer);
    }

}
