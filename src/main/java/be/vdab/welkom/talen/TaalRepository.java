package be.vdab.welkom.talen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class TaalRepository {
    public List<Taal> findAll() {
        try (var regels = Files.lines(Path.of("D:/opleding/Spring/SpringCoreMateriaal/talen.csv"))) {
            return regels
                    .map(regel -> regel.split(","))
                    .map(regelOnderdelen -> new Taal(
                            regelOnderdelen[0],
                            regelOnderdelen[1]))
                    .toList();
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new IllegalArgumentException("Talenbestand bevat verkeerde data", ex);
        }
    }
}