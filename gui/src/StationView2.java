import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
/**
 * View for a station and the station list
 *
 * @author Otto Werse
 * @version 0.1
 * @date 2021-12-05
 */
public class StationView2 implements IStationView {
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);

    // Variables to be displayed
    private String CurrentStationID, CurrentDate, CurrentActual;
    private LinkedList CurrentList = new LinkedList();
    private Color highlight;

    private JFrame JFrame_Main = new JFrame();
    private JList JList_Stations;
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
        if (!this.JTextField_CurrentActual.hasFocus()) {
            this.JTextField_CurrentActual.setText(currentActual);
        }
    }

    public void setVariance(String currentVariance) {
        this.JTextField_CurrentVariance.setText(currentVariance);
    }

    public void setCurrentList(LinkedList currentList) {
        this.CurrentList = currentList;
        int index = this.JList_Stations.getSelectedIndex();
        this.JList_Stations.setListData(this.CurrentList.toArray());
        this.JList_Stations.setSelectedIndex(index);
        this.updateUI();
    }

    public void setHighlight(Color highlight) {
        this.highlight = highlight;
        this.JTextField_CurrentVariance.setForeground(this.highlight);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    public StationView2() {
        this.initialise();
        this.updateUI();
    }

    private void updateUI() {
        this.JTextField_CurrentActual.repaint();
        this.JTextField_CurrentDate.repaint();
        this.JTextField_CurrentStationID.repaint();
        this.JTextField_CurrentTarget.repaint();
        this.JTextField_CurrentVariance.repaint();
        this.JList_Stations.repaint();
    }

    private void initialise() {
        // Create and format main JFrame
        JFrame_Main.setSize(400, 800);
        JFrame_Main.setLayout(new GridLayout(2, 1));

        // Create and format JPanel for form fields
        JPanel JPanel_Fields = new JPanel();
        JPanel_Fields.setLayout(new GridLayout(5, 2));
        JPanel_Fields.setVisible(true);

        // Create and fill JList for Stations
        this.JList_Stations = new JList(this.CurrentList.toArray());
        ListSelectionListener Selector_Stations = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int index = JList_Stations.getSelectedIndex();
                    if (index != -1) {
                        changes.firePropertyChange("StationID", CurrentStationID, CurrentList.toArray()[index]);
                    }
                }
            }
        };
        this.JList_Stations.addListSelectionListener(Selector_Stations);

        // Create and format JScrollPane for Stations JList
        JScrollPane JScrollPane_Stations = new JScrollPane(this.JList_Stations);
        JScrollPane_Stations.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollPane_Stations.setVisible(true);

        // Create form for Station ID
        JPanel_Fields.add(new JLabel("Station ID"));
        this.JTextField_CurrentStationID = new JTextField();
        this.JTextField_CurrentStationID.setEditable(false);
        JPanel_Fields.add(this.JTextField_CurrentStationID);

        // Create form for Date
        JPanel_Fields.add(new JLabel("Date"));
        this.JTextField_CurrentDate = new JTextField();
        this.JTextField_CurrentDate.setEditable(false);
        JPanel_Fields.add(this.JTextField_CurrentDate);

        // Create form for Target
        JPanel_Fields.add(new JLabel("Target"));
        this.JTextField_CurrentTarget = new JTextField();
        this.JTextField_CurrentTarget.setEditable(false);
        JPanel_Fields.add(this.JTextField_CurrentTarget);

        // Create form for Actual
        JPanel_Fields.add(new JLabel("Actual"));
        this.JTextField_CurrentActual = new JTextField();
        ActionListener ActionListener_CurrentActual = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NewActual = JTextField_CurrentActual.getText();
                if (isNumericInput(NewActual)) {
                    changes.firePropertyChange("Actual", CurrentActual, NewActual);
                    CurrentActual = NewActual;
                } else {
                    showError("Keine gültige Zahl!");
                }
            }
        };
        this.JTextField_CurrentActual.addActionListener(ActionListener_CurrentActual);
        JPanel_Fields.add(this.JTextField_CurrentActual);

        // Create form for Variance
        JPanel_Fields.add(new JLabel("Variance"));
        this.JTextField_CurrentVariance = new JTextField();
        this.JTextField_CurrentVariance.setForeground(highlight);
        this.JTextField_CurrentVariance.setEditable(false);
        JPanel_Fields.add(this.JTextField_CurrentVariance);

        JFrame_Main.add(JScrollPane_Stations);
        JFrame_Main.add(JPanel_Fields);

        JFrame_Main.setVisible(true);
    }

    public void showError(String s) {
        JOptionPane.showMessageDialog(this.JFrame_Main, s);
    }

    //Check if it is a numeric input
    public static boolean isNumericInput(String string) {
        int intValue;

        //Check for the simplest errors
        if (string == null || string.equals("")) {
            return false;
        }

        //Check if it is a number by trying to parse it
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }
}
