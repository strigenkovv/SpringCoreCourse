package be.vdab.welkom.talen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Qualifier("CSV")
public class CsvTaalRepository implements TaalRepository {
    private final String pad;

    public CsvTaalRepository(@Value("${talenCsvPad}") String pad) {
        this.pad = pad;
    }
    @Override
    public List<Taal> findAll() {
        try (var regels = Files.lines(Path.of(pad))) {
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