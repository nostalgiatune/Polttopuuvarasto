package polttopuuvarasto.editori;


import java.util.List;
import javax.swing.table.AbstractTableModel;
import polttopuuvarasto.tiedonsiirto.*;

/**
 *
 * @author Tuoppi
 */
public class VarastoTableModel extends AbstractTableModel {
    
    private final List<Tuote> tuotteet;
    private final String[] columnNames = { "TUOTE", "Pituus, cm", "KUIVA/varastossa",
        "TUORE/varastossa", "HINTA/i-m3/p-m3"
    };

    public VarastoTableModel(List<Tuote> tuotteet) {
        this.tuotteet = tuotteet;
    }

    @Override
    public int getRowCount() {
        return tuotteet.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        Tuote tuote = tuotteet.get(rowIndex);
        switch (columnIndex) {
            case 0: return tuote.getNimi();
            case 1: return tuote.getPituus();
            case 2: return tuote.isKuivaVarastossa();
            case 3: return tuote.isTuoreVarastossa();
            case 4: return tuote.getHinta();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return String.class;
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Tuote tuote = tuotteet.get(rowIndex);
        
        switch (columnIndex) {
            case 0: tuote.setNimi((String)aValue); break;
            case 1: tuote.setPituus((String)aValue); break;
            case 2: tuote.setKuivaVarastossa((String)aValue); break;
            case 3: tuote.setTuoreVarastossa((String)aValue); break;
            case 4: tuote.setHinta((String)aValue); break;
            default: break;
        }
    }
    
    public void addTuote(int index) {
        tuotteet.add(index, new Tuote("", "", "", "", ""));
        fireTableDataChanged();
    }
    
    public void removeTuote(int index) {
        tuotteet.remove(index);
        fireTableDataChanged();
    }
}