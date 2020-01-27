package kbe;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main( String[] args ) throws Exception
    {
        SpringApplication.run(Application.class, args);
        System.out.println( "SpringBoot started!" );
    }
}
