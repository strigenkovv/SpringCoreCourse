package be.vdab.welkom;

import be.vdab.welkom.landen.LandRepository;
import be.vdab.welkom.talen.TaalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {
    private final LandRepository landRepository;
    private final TaalRepository taalRepository;

    public MyRunner(LandRepository landRepository, TaalRepository taalRepository) {
        this.landRepository = landRepository;
        this.taalRepository = taalRepository;
    }

    @Override
    public void run(String... args) {
        try {
            landRepository.findAll()
                    .forEach(land -> IO.println(land.getNaam()));
            IO.println();
            taalRepository.findAll()
                    .forEach(taal -> IO.println(taal.getNaam()));
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        }
    }
}