package GwendolenToolWindow;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BreakpointsViewer extends JPanel {
    DefaultTableModel model;
    JBTable table;
    JButton deleteButton;


    public BreakpointsViewer(GwenToolWindowContent gwenToolWindowContent){
        super();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        makeTable();
        makeDeleteButton();
    }

    private void makeTable(){
        Object[][] data = new Object[][]{{"Agent1", "Has belief: ..."}, {"Agent2", "Has belief..."}, {"Agent3", "Has had ... steps processed"}, {"G", "1"}, {"H", "2"}, {"l", "3"}, {"p", "1"}, {"1", "l"}};
        String[] colNames = new String[]{"Agent Name", "Condition"};
        model = new DefaultTableModel(data, colNames);
        table = new JBTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JBScrollPane tableScrollPane = new JBScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() / 3));
        add(tableScrollPane);
    }

    private void makeDeleteButton(){
        deleteButton = new JButton("Delete Selected Row");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int rowNum = table.getSelectedRow();
                if(rowNum != 1){
                    model.removeRow(rowNum);
                }
            }
        });
        add(deleteButton);
    }
}
