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
    private String CurrentStationID, CurrentDate, CurrentActual;
    private LinkedList CurrentList = new LinkedList();
    private Color highlight;

    // GUI Elements
    private JFrame JFrame_Main;
    private JPanel JPanel_Fields;
    private JList JList_Stations;
    private JScrollPane JScrollPane_Stations;
    private JTextField JTextField_CurrentStationID, JTextField_CurrentDate, JTextField_CurrentTarget, JTextField_CurrentActual, JTextField_CurrentVariance;

    public void setStationID(String currentStationID) {
        this.JTextField_CurrentStationID.setText(currentStationID);
    }

    public void setDate(String currentDate) {
        this.JTextField_CurrentDate.setText(currentDate);
    }

    public void setTarget(String currentTarget) {
        this.JTextField_CurrentTarget.setText(currentTarget);
    }

    public void setActual(String currentActual) {
        this.JTextField_CurrentActual.setText(currentActual);
    }

    public void setVariance(String currentVariance) {
        this.JTextField_CurrentVariance.setText(currentVariance);
    }

    public void setCurrentList(LinkedList currentList) {
        CurrentList = currentList;
    }

    public void setHighlight(Color highlight) {
        this.highlight = highlight;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    public Window(LinkedList list, String stationID, String date, String target, String actual, String variance, Color highlight) {
        this.setCurrentList(list);
        this.initialise();
        this.setStationID(stationID);
        this.setDate(date);
        this.setTarget(target);
        this.setActual(actual);
        this.setVariance(variance);
        this.setHighlight(highlight);
    }

    private void initialise() {
        // Create and format main JFrame
        this.JFrame_Main = new JFrame();
        this.JFrame_Main.setSize(800, 400);
        this.JFrame_Main.setLayout(new GridLayout(1, 2));

        // Create and format JPanel for form fields
        this.JPanel_Fields = new JPanel();
        this.JPanel_Fields.setLayout(new GridLayout(5, 2));
        this.JPanel_Fields.setVisible(true);

        // Create and fill JList for Stations
        this.JList_Stations = new JList(this.CurrentList.toArray());

        // Create and format JScrollPane for Stations JList
        this.JScrollPane_Stations = new JScrollPane(this.JList_Stations);
        this.JScrollPane_Stations.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.JScrollPane_Stations.setVisible(true);

        // Create form for Station ID
        this.JPanel_Fields.add(new JLabel("Station ID"));
        this.JTextField_CurrentStationID = new JTextField();
        ActionListener ActionListener_CurrentStationID = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NewStationID = JTextField_CurrentStationID.getText();
                changes.firePropertyChange("StationID", CurrentStationID, NewStationID);
                CurrentStationID = NewStationID;
            }
        };
        this.JTextField_CurrentStationID.addActionListener(ActionListener_CurrentStationID);
        this.JPanel_Fields.add(this.JTextField_CurrentStationID);

        // Create form for Date
        this.JPanel_Fields.add(new JLabel("Date"));
        this.JTextField_CurrentDate = new JTextField();
        ActionListener ActionListener_CurrentDate = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NewDate = JTextField_CurrentDate.getText();
                changes.firePropertyChange("Date", CurrentDate, NewDate);
                CurrentDate = NewDate;
            }
        };
        this.JTextField_CurrentDate.addActionListener(ActionListener_CurrentDate);
        this.JPanel_Fields.add(this.JTextField_CurrentDate);

        // Create form for Target
        this.JPanel_Fields.add(new JLabel("Target"));
        this.JTextField_CurrentTarget = new JTextField();
        this.JTextField_CurrentTarget.setEditable(false);
        this.JPanel_Fields.add(this.JTextField_CurrentTarget);

        // Create form for Actual
        this.JPanel_Fields.add(new JLabel("Actual"));
        this.JTextField_CurrentActual = new JTextField();
        ActionListener ActionListener_CurrentActual = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NewActual = JTextField_CurrentActual.getText();
                changes.firePropertyChange("Actual", CurrentActual, NewActual);
                CurrentActual = NewActual;
            }
        };
        this.JTextField_CurrentActual.addActionListener(ActionListener_CurrentActual);
        this.JPanel_Fields.add(this.JTextField_CurrentActual);

        // Create form for Variance
        this.JPanel_Fields.add(new JLabel("Variance"));
        this.JTextField_CurrentVariance = new JTextField();
        this.JTextField_CurrentVariance.setForeground(highlight);
        this.JTextField_CurrentVariance.setEditable(false);
        this.JPanel_Fields.add(this.JTextField_CurrentVariance);

        this.JFrame_Main.add(this.JScrollPane_Stations);
        this.JFrame_Main.add(this.JPanel_Fields);

        this.JFrame_Main.setVisible(true);
    }
}
