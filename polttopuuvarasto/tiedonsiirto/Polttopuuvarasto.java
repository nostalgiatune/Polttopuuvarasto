package polttopuuvarasto.tiedonsiirto;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import polttopuuvarasto.editori.*;

/**
 *
 * @author Tuoppi
 */
public class Polttopuuvarasto {
    
    public static final String CSV_FILE = "varasto.csv";

    public static void main(String[] args) {
        
        try {
            
            FTPhandler ftpHandler = new FTPhandler(
                    "**************",
                    "**",
                    "*******",
                    "*******"
            );

            File csvFile;
            List<Tuote> varasto;
            VarastoTableEditor tableEditor;
            VarastoController controller;
        
            csvFile = ftpHandler.download("public_html/"+CSV_FILE);
            varasto = VarastoCsvParser.parseCsv(csvFile);
            tableEditor = new VarastoTableEditor();
            controller = new VarastoController(varasto, tableEditor, ftpHandler);
            tableEditor.setController(controller);
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Polttopuuvarasto.class.getName()).log(Level.SEVERE, null, ex);
            clean();
            System.exit(1);
        } catch (IOException | IllegalArgumentException ex) {
            Logger.getLogger(Polttopuuvarasto.class.getName()).log(Level.SEVERE, null, ex);
            clean();
            System.exit(1);
        }
    }
    
    public static void clean() {
        File file = new File(CSV_FILE);
        if (file.exists())
            file.delete();
    }
    
}
