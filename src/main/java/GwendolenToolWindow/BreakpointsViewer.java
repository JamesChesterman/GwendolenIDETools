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
    ComboBox<String> agentComboBox1;
    ComboBox<String> attributeComboBox;
    JTextField numOfStepsTextField;
    JButton insertStepBreakpointButton;
    private boolean agentsAdded;
    private boolean attributesAdded;
    List<String[]> breakpointsList;
    String[] labelStrings;
    List<String> dropdownStrings;


    public BreakpointsViewer(GwenToolWindowContent gwenToolWindowContent){
        super();
        agentsAdded = false;
        attributesAdded = false;
        breakpointsList = new ArrayList<>();
        dropdownStrings = new ArrayList<>();

        setLayout(new GridBagLayout());

        makeTable();
        makeDeleteButton();

        makeStepBreakpointUI();

        makeAttributeBreakpointUI();
    }

    public List<String[]> getBreakpointsList(){
        return breakpointsList;
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
                        breakpointsList.add(new String[]{agent, "numOfSteps", String.valueOf(numOfSteps)});
                    }
                }catch(NumberFormatException ex){
                    warningLabel.setText("WARNING - please enter a number for step number");
                }
           }
        });
    }

    private void makeAttributeBreakpointUI(){
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        addComponent(this, separator, 0, 7, 2, 1);

        //Agent combobox
        JLabel label = new JLabel("Breakpoint that activates when Agent:");
        agentComboBox1 = new ComboBox<String>();
        agentComboBox1.setSize(200, 10);
        addComponent(this, label, 0, 8, 1, 1);
        addComponent(this, agentComboBox1, 1, 8, 1, 1);

        JLabel label1 = new JLabel("has the following attribute updated: ");
        attributeComboBox = new ComboBox<String>();
        attributeComboBox.setSize(200, 10);
        addComponent(this, label1, 0, 9, 1, 1);
        addComponent(this, attributeComboBox, 1, 9, 1, 1);

        makeInsertAttributeBreakpointButton();

    }

    private void makeInsertAttributeBreakpointButton(){
        JButton insertButton = new JButton("Insert Attribute Breakpoint");
        addComponent(this, insertButton, 0, 10, 1, 1);
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String agent = (String) agentComboBox1.getSelectedItem();
                String attribute = (String) attributeComboBox.getSelectedItem();
                String attributeString = "Has " + attribute + " updated";
                Object[] row = new Object[]{agent, attributeString};
                model.addRow(row);
                //Makes the table show the most recently added row
                table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));

                //Want to add the string with colon at the end so it matches the labels in BGIViewer
                for(int i=0; i<dropdownStrings.size(); i++){
                    if(attribute.equals(dropdownStrings.get(i))){
                        breakpointsList.add(new String[]{agent, "attribute", labelStrings[i]});
                        breakpointsList.add(new String[]{agent, "attribute", labelStrings[i]});
                        break;
                    }
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
                agentComboBox1.addItem(agents[i]);
            }
            agentsAdded = true;
        }
    }

    public void addAttributes(String[] labelStrings){
        if(labelStrings != null && !attributesAdded){
            this.labelStrings = labelStrings;
            for(int i=0; i<labelStrings.length; i++){
                //Remove trailing colon
                int indexOfColon = labelStrings[i].lastIndexOf(':');
                String cleanedLabel = labelStrings[i];
                if(indexOfColon != -1){
                    cleanedLabel = labelStrings[i].substring(0, indexOfColon);
                }
                dropdownStrings.add(cleanedLabel);
                attributeComboBox.addItem(cleanedLabel);
            }
            attributesAdded = true;
        }
    }
}
