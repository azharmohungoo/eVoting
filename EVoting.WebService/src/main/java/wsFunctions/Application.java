package wsFunctions;

/**
 * Created by Andreas on 2016/07/17.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
        }
        catch(Exception e)
        {

        }
    }
}