package nc.unc.gl.borne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MilleBornesApplication {

    public static void main(String[] args) {
        Application app = new Application();
        app.jouer();
    }
}
