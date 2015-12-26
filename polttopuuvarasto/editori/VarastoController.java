package polttopuuvarasto.editori;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import polttopuuvarasto.tiedonsiirto.*;

/**
 *
 * @author Tuoppi
 */
public class VarastoController {
    
    private final List<Tuote> varasto; // Muokkaus kohdistuu tähän       
    private final List<Tuote> varastoBackup; // Alkuperäinen varasto voidaan palauttaa
    
    private final VarastoTableModel tableModel;
    private final VarastoTableEditor tableEditor;
    
    private final FTPhandler ftpHandler;

    public VarastoController(List<Tuote> varasto, VarastoTableEditor tableEditor,
            FTPhandler ftpHandler) {
        
        this.varasto = varasto;
        varastoBackup = new ArrayList<>();
        tableModel = new VarastoTableModel(varasto);
        this.tableEditor = tableEditor;
        this.ftpHandler = ftpHandler;
        makeBackup();
    }
    
    public VarastoTableModel getTableModel() {
        return tableModel;
    }
    
    private void makeBackup() {
        varastoBackup.clear();
        varasto.stream().forEach(t-> {
            varastoBackup.add(new Tuote(
                    t.getNimi(),
                    t.getPituus(),
                    t.isKuivaVarastossa(),
                    t.isTuoreVarastossa(),
                    t.getHinta()
            ));
        });
    }
    
    public void undoChanges() {
        
        varasto.clear();
        varastoBackup.stream().forEach(t-> {
            varasto.add(new Tuote(
                    t.getNimi(),
                    t.getPituus(),
                    t.isKuivaVarastossa(),
                    t.isTuoreVarastossa(),
                    t.getHinta()
            ));
        });
        tableModel.fireTableDataChanged();
    }
    
    public void saveChanges() {
        
        try {
            VarastoCsvParser.makeCsv(varasto, Polttopuuvarasto.CSV_FILE);
            ftpHandler.upload(new File(Polttopuuvarasto.CSV_FILE), "public_html");
            makeBackup();
        } catch (IOException ex) {
            undoChanges();
            Logger.getLogger(VarastoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addTuote(int index) {
        tableModel.addTuote(index);
    }
    
    public void removeTuote(int index) {
        tableModel.removeTuote(index);
    }
    
}