package GwendolenToolWindow;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.table.JBTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

public class BreakpointsViewer extends JPanel {
    DefaultTableModel model;
    JBTable table;
    JBScrollPane tableScrollPane;
    JButton deleteButton;
    ComboBox<String> agentComboBox;
    JTextField numOfStepsTextField;
    JButton insertStepBreakpointButton;
    private boolean agentsAdded;
    List<String[]> breakpointsList;


    public BreakpointsViewer(GwenToolWindowContent gwenToolWindowContent){
        super();
        agentsAdded = false;
        breakpointsList = new ArrayList<>();

        setLayout(new GridBagLayout());

        makeTable();
        makeDeleteButton();

        makeStepBreakpointUI();
    }

    private void makeTable(){
        Object[][] data = new Object[][]{};
        String[] colNames = new String[]{"Agent Name", "Condition"};
        model = new DefaultTableModel(data, colNames);
        table = new JBTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tableScrollPane = new JBScrollPane(table);
        //tableScrollPane.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() / 3));
        addComponent(this, tableScrollPane, 0, 0, 2, 2);
    }

    private void makeDeleteButton(){
        deleteButton = new JButton("Delete Selected Row");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int rowNum = table.getSelectedRow();
                if(rowNum != -1){
                    model.removeRow(rowNum);
                    breakpointsList.remove(rowNum);
                }
            }
        });
        addComponent(this, deleteButton, 0, 2, 1, 1);
    }

    //UI for inserting a step breakpoint for an agent
    //Will be the number of steps that have been processed on an agent.
    private void makeStepBreakpointUI(){
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        addComponent(this, separator, 0, 3, 2, 1);

        JLabel agentComboBoxLabel = new JLabel("Breakpoint that activates when Agent: ");
        addComponent(this, agentComboBoxLabel, 0, 4, 1, 1);

        agentComboBox = new ComboBox<String>();
        agentComboBox.setSize(200, 10);
        addComponent(this, agentComboBox, 1, 4, 1, 1);

        JLabel stepNumLabel = new JLabel("has number of steps: ");
        addComponent(this, stepNumLabel, 0, 5, 1, 1);

        numOfStepsTextField = new JTextField(5);
        addComponent(this, numOfStepsTextField, 1, 5, 1, 1);

        makeInsertStepBreakpointButton();
    }

    private void makeInsertStepBreakpointButton(){
        insertStepBreakpointButton = new JButton("Insert Step Number Breakpoint");
        addComponent(this, insertStepBreakpointButton, 0, 6, 1, 1);

        JLabel warningLabel = new JLabel("");
        addComponent(this, warningLabel, 1, 6, 1, 1);
        insertStepBreakpointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String agent = (String) agentComboBox.getSelectedItem();
                String numOfStepsString = null;
                try{
                    int numOfSteps = Integer.parseInt(numOfStepsTextField.getText());
                    if(numOfSteps <= 0){
                        warningLabel.setText("WARNING - please enter step number above 0");
                    }else{
                        numOfStepsString = "Has " + String.valueOf(numOfSteps) + " steps done";
                        Object[] row = new Object[]{agent, numOfStepsString};
                        model.addRow(row);
                        //Makes the table show the most recently added row
                        table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
                        warningLabel.setText("");

                        //Also want to add to list of breakpoints
                        breakpointsList.add(new String[]{agent, "stepNum", numOfStepsString});
                    }
                }catch(NumberFormatException ex){
                    warningLabel.setText("WARNING - please enter a number for step number");
                }
           }
        });
    }

    //Add component to grid bag layout.
    private void addComponent(Container container, Component component, int column, int row, int width, int height) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);
        container.add(component, constraints);
    }

    public void addAgents(String[] agents){
        if(agents != null && !agentsAdded){
            for(int i=0; i<agents.length; i++){
                agentComboBox.addItem(agents[i]);
            }
            agentsAdded = true;
        }
    }
}
