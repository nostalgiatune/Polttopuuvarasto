package polttopuuvarasto.editori;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.CellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Tuoppi
 */
public class VarastoTableEditor extends JFrame {
    
    private VarastoController controller;
    
    private final JPanel content;
    private JScrollPane tableView;
    private JTable table;
    private JButton saveButton;
    private JButton resetButton;
    private JButton addButton;
    private JButton removeButton;
    
    public VarastoTableEditor() {
        content = new JPanel();
        setContentPane(content);
    }
    
    public void setController(VarastoController controller) {
        
        this.controller = controller;
        
        table = new JTable(controller.getTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(900, 600));
        tableView = new JScrollPane(table);
        content.add(tableView);
        
        saveButton = new JButton("Tallenna muutokset");
        resetButton = new JButton("Peru muutokset");
        addButton = new JButton("Lisää tuote");
        removeButton = new JButton("Poista tuote");
        
        content.add(saveButton);
        content.add(resetButton);
        content.add(addButton);
        content.add(removeButton);
        
        setActionListeners();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setExtendedState(MAXIMIZED_BOTH);
    }
    
    public void setActionListeners() {
        
        saveButton.addActionListener((ActionEvent e) -> {
            CellEditor cellEditor = table.getCellEditor();
            if (cellEditor != null)
                cellEditor.stopCellEditing();
            
            controller.saveChanges();
        });
        
        resetButton.addActionListener((ActionEvent e) -> {
            CellEditor cellEditor = table.getCellEditor();
            if (cellEditor != null)
                cellEditor.stopCellEditing();
            
            controller.undoChanges();
        });
        
        addButton.addActionListener((ActionEvent e) -> {
            CellEditor cellEditor = table.getCellEditor();
            if (cellEditor != null)
                cellEditor.stopCellEditing();
            
            controller.addTuote(table.getSelectedRow()+1);
        });
        
        removeButton.addActionListener((ActionEvent e) -> {
            CellEditor cellEditor = table.getCellEditor();
            if (cellEditor != null)
                cellEditor.stopCellEditing();
            
            int index = table.getSelectedRow();
            if (index != -1)
                controller.removeTuote(index);
        });
    }
}