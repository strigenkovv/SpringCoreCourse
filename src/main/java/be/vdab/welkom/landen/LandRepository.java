package be.vdab.welkom.landen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LandRepository {
    public List<Land> findAll() {
        try (var regels = Files.lines(Path.of("D:/opleding/Spring/SpringCoreMateriaal/landen.csv"))) {
            return regels
                    .map(regel -> regel.split(","))
                    .map(regelOnderdelen -> new Land(
                            regelOnderdelen[0],
                            regelOnderdelen[1],
                            Integer.parseInt(regelOnderdelen[2])))
                    .toList();
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new IllegalArgumentException("Landenbestand bevat verkeerde data", ex);
        }
    }
}