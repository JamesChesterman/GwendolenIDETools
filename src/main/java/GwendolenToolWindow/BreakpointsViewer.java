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

public class BreakpointsViewer extends JPanel {
    DefaultTableModel model;
    JBTable table;
    JButton deleteButton;
    ComboBox<String> agentComboBox;
    JTextField numOfStepsTextField;
    JButton insertStepBreakpointButton;
    private boolean agentsAdded;


    public BreakpointsViewer(GwenToolWindowContent gwenToolWindowContent){
        super();
        agentsAdded = false;

        setLayout(new GridBagLayout());

        makeTable();
        makeDeleteButton();

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(this.getWidth(), 1));
        addComponent(this, separator, 0, 2, 2, 1);

        makeStepBreakpointUI();
    }

    private void makeTable(){
        Object[][] data = new Object[][]{{"Agent1", "Has belief: ..."}, {"Agent2", "Has belief..."}, {"Agent3", "Has had ... steps processed"}, {"G", "1"}, {"H", "2"}, {"l", "3"}, {"p", "1"}, {"1", "l"}};
        String[] colNames = new String[]{"Agent Name", "Condition"};
        model = new DefaultTableModel(data, colNames);
        table = new JBTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JBScrollPane tableScrollPane = new JBScrollPane(table);
        //tableScrollPane.setPreferredSize(new Dimension(this.getWidth(), this.getHeight() / 3));
        addComponent(this, tableScrollPane, 0, 0, 2, 2);
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
        addComponent(this, deleteButton, 0, 1, 1, 1);
    }

    //UI for inserting a step breakpoint for an agent
    //Will be the number of steps that have been processed on an agent.
    private void makeStepBreakpointUI(){
        JLabel agentComboBoxLabel = new JLabel("Breakpoint that activates when Agent: ");
        addComponent(this, agentComboBoxLabel, 0, 3, 1, 1);

        agentComboBox = new ComboBox<String>();
        agentComboBox.setSize(200, 10);
        addComponent(this, agentComboBox, 1, 3, 1, 1);

        JLabel stepNumLabel = new JLabel("has number of steps: ");
        addComponent(this, stepNumLabel, 0, 4, 1, 1);

        numOfStepsTextField = new JTextField(5);
        addComponent(this, numOfStepsTextField, 1, 4, 1, 1);

        makeInsertStepBreakpointButton();
    }

    private void makeInsertStepBreakpointButton(){
        insertStepBreakpointButton = new JButton("Insert Step Number Breakpoint");
        addComponent(this, insertStepBreakpointButton, 0, 5, 1, 1);

        JLabel warningLabel = new JLabel("");
        addComponent(this, warningLabel, 1, 5, 1, 1);
        insertStepBreakpointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String agent = (String) agentComboBox.getSelectedItem();
                String numOfStepsString = null;
                try{
                    int numOfSteps = Integer.parseInt(numOfStepsTextField.getText());
                    numOfStepsString = "Has " + String.valueOf(numOfSteps) + " steps done";
                    Object[] row = new Object[]{agent, numOfStepsString};
                    model.addRow(row);
                    warningLabel.setText("");
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
