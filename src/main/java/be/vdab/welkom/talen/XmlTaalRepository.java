package be.vdab.welkom.talen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("XML")
public class XmlTaalRepository implements TaalRepository {
    private final XMLInputFactory factory = XMLInputFactory.newInstance();

    @Override
    public List<Taal> findAll() {
        var talen = new ArrayList<Taal>();
        try (var bufferedReader = Files.newBufferedReader(
                Path.of("D:/opleding/Spring/SpringCoreMateriaal/talen.xml"))) {
            var reader = factory.createXMLStreamReader(bufferedReader);
            while (reader.hasNext()) {
                reader.next();
                if (reader.isStartElement() && "taal".equals(reader.getLocalName())) {
                    var taalCode = reader.getAttributeValue(0);
                    var naam = reader.getAttributeValue(1);
                    talen.add(new Taal(taalCode, naam));
                }
            }
            return talen;
        } catch (IOException | XMLStreamException ex) {
            throw new IllegalArgumentException("Talenbestand bevat verkeerde data", ex);
        }
    }
}