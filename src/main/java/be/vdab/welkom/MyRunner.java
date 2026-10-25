package be.vdab.welkom;

import be.vdab.welkom.landen.LandRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {
    private final LandRepository landRepository;

    public MyRunner(LandRepository landRepository) {
        this.landRepository = landRepository;
    }

    @Override
    public void run(String... args) {
        try {
            landRepository.findAll()
                    .forEach(land -> IO.println(land.getNaam()));
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        }
    }
}