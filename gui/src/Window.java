import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;

public class Window {
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    // Variables to be displayed
    String CurrentStationID;
    String CurrentDate;
    String CurrentTarget;
    String CurrentActual;
    String CurrentVariance;
    LinkedList CurrentList = new LinkedList();
    Color highlight;

    // GUI Elements
    JFrame JFrame_Main;
    JPanel JPanel_Fields;
    JList JList_Stations;
    JScrollPane JScrollPane_Stations;
    JTextField JTextField_CurrentStationID, JTextField_CurrentDate, JTextField_CurrentTarget, JTextField_CurrentActual, JTextField_CurrentVariance;

    public void setCurrentStationID(String currentStationID) {
        CurrentStationID = currentStationID;
    }

    public void setCurrentDate(String currentDate) {
        CurrentDate = currentDate;
    }

    public void setCurrentTarget(String currentTarget) {
        CurrentTarget = currentTarget;
    }

    public void setCurrentActual(String currentActual) {
        CurrentActual = currentActual;
    }

    public void setCurrentVariance(String currentVariance) {
        CurrentVariance = currentVariance;
    }

    public void setCurrentList(LinkedList currentList) {
        CurrentList = currentList;
    }

    public void setHighlight(Color highlight) {
        this.highlight = highlight;
    }

    public String  getCurrentStationID()
    {
        return this.CurrentStationID;
    }

    public void updateCurrentStationID()
    {
        changes.firePropertyChange("CurrentStationID", CurrentStationID, CurrentStationID);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    void updateWindow() {
        this.JTextField_CurrentStationID.setText(this.CurrentStationID);
        this.JTextField_CurrentDate.setText(this.CurrentDate);
        this.JTextField_CurrentTarget.setText(this.CurrentTarget);
        this.JTextField_CurrentActual.setText(this.CurrentActual);
        this.JTextField_CurrentVariance.setText(this.CurrentVariance);
    }

    void initialiseWindow() {
        this.JFrame_Main = new JFrame();

        this.JFrame_Main.setSize(800, 400);
        this.JFrame_Main.setLayout(new GridLayout(1, 2));

        this.JPanel_Fields = new JPanel();
        this.JPanel_Fields.setLayout(new GridLayout(5, 2));
        this.JPanel_Fields.setVisible(true);

        this.JList_Stations = new JList(this.CurrentList.toArray());

        this.JScrollPane_Stations = new JScrollPane(this.JList_Stations);
        this.JScrollPane_Stations.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.JScrollPane_Stations.setVisible(true);

        // Station ID
        this.JPanel_Fields.add(new JLabel("Station ID"));
        this.JTextField_CurrentStationID = new JTextField(this.CurrentStationID);
        ActionListener ActionListener_CurrentStationID = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentStationID = JTextField_CurrentStationID.getText();
            }
        };
        this.JTextField_CurrentStationID.addActionListener(ActionListener_CurrentStationID);
        this.JPanel_Fields.add(this.JTextField_CurrentStationID);

        this.JPanel_Fields.add(new JLabel("Date"));
        this.JTextField_CurrentDate = new JTextField(this.CurrentDate);
        ActionListener ActionListener_CurrentDate = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentDate = JTextField_CurrentDate.getText();
            }
        };
        this.JTextField_CurrentDate.addActionListener(ActionListener_CurrentDate);
        this.JPanel_Fields.add(this.JTextField_CurrentDate);

        // Target
        this.JPanel_Fields.add(new JLabel("Target"));
        this.JTextField_CurrentTarget = new JTextField(this.CurrentTarget);
        this.JTextField_CurrentTarget.setEditable(false);
        this.JPanel_Fields.add(this.JTextField_CurrentTarget);

        // Actual
        this.JPanel_Fields.add(new JLabel("Actual"));
        this.JTextField_CurrentActual = new JTextField(this.CurrentActual);
        ActionListener ActionListener_CurrentActual = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CurrentActual = JTextField_CurrentActual.getText();
            }
        };
        this.JTextField_CurrentActual.addActionListener(ActionListener_CurrentActual);
        this.JPanel_Fields.add(this.JTextField_CurrentActual);

        // Variance
        this.JPanel_Fields.add(new JLabel("Variance"));
        this.JTextField_CurrentVariance = new JTextField(this.CurrentVariance);
        this.JTextField_CurrentVariance.setForeground(highlight);
        this.JTextField_CurrentVariance.setEditable(false);
        this.JPanel_Fields.add(this.JTextField_CurrentVariance);

        this.JFrame_Main.add(this.JScrollPane_Stations);
        this.JFrame_Main.add(this.JPanel_Fields);

        JButton TestButton = new JButton("Example Button");
        ActionListener TestListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWindow();
            }
        };
        TestButton.addActionListener(TestListener);
        this.JFrame_Main.add(TestButton);

        this.JFrame_Main.setVisible(true);
    }
}
