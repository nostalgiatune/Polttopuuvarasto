package polttopuuvarasto.tiedonsiirto;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;
import static java.util.stream.Collectors.*;

/**
 *
 * @author Tuoppi
 */
public class VarastoCsvParser {
    
    public static final String ENCODING = "Cp1252";
    
    public synchronized static List<Tuote> parseCsv(File file) throws FileNotFoundException,
            IOException, IllegalArgumentException {
        
        try //(BufferedReader lineReader = new BufferedReader(new FileReader(file)))
        (BufferedReader lineReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), ENCODING)))
        {
            return lineReader.lines()
                .map(l->l.split(";"))
                .map(t->new Tuote(
                        t[0], // nimi
                        t[1], // pituus
                        t[2], // kuiva
                        t[3], // tuore
                        t[4] // pituus
                    ))
                .collect(toList());
        }
    }
    
    public synchronized static File makeCsv(List<Tuote> tuotteet, String filename) throws
            IOException {
        
        File csvFile = new File(filename);
        csvFile.createNewFile();
           
        try //(PrintStream lineWriter = new PrintStream(csvFile))
        (PrintStream lineWriter = new PrintStream(csvFile, ENCODING))
        {
            tuotteet.stream()
                    .forEach(t-> {
                        lineWriter.print(
                                t.getNimi() + ";" +
                                t.getPituus() + ";" +
                                t.isKuivaVarastossa() + ";" +
                                t.isTuoreVarastossa() + ";" +
                                t.getHinta()
                        );
                        if (tuotteet.indexOf(t) < tuotteet.size()-1)
                            lineWriter.println();
                    });
            lineWriter.flush();
            return csvFile;
        }
    }
    
}