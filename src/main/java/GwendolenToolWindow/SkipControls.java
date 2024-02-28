package GwendolenToolWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkipControls extends JPanel {
    JTextField stepTextField;
    JButton skipButton;

    GwenToolWindowContent gwenToolWindow;

    public SkipControls(GwenToolWindowContent gwenToolWindow){
        super();
        setLayout(new GridBagLayout());

        this.gwenToolWindow = gwenToolWindow;

        makeSkipControlsUI();
        makeSkipButton();
    }

    private void makeSkipControlsUI(){
        JLabel skipNumOfSteps = new JLabel("Number of steps to skip: ");
        addComponent(this, skipNumOfSteps, 0, 0, 1, 1);

        stepTextField = new JTextField(5);
        addComponent(this, stepTextField, 1, 0, 1, 1);
    }

    private void makeSkipButton(){
        skipButton = new JButton("Skip");
        addComponent(this, skipButton, 0, 1, 1, 1);
        skipButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int numOfSteps = Integer.parseInt(stepTextField.getText());
                gwenToolWindow.skip(numOfSteps);
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
}
