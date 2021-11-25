import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Window {
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

        this.JPanel_Fields.add(new JLabel("Station ID"));
        this.JTextField_CurrentStationID = new JTextField(this.CurrentStationID);
        this.JPanel_Fields.add(this.JTextField_CurrentStationID);

        this.JPanel_Fields.add(new JLabel("Date"));
        this.JTextField_CurrentDate = new JTextField(this.CurrentDate);
        this.JPanel_Fields.add(this.JTextField_CurrentDate);

        this.JPanel_Fields.add(new JLabel("Target"));
        this.JTextField_CurrentTarget = new JTextField(this.CurrentTarget);
        this.JPanel_Fields.add(this.JTextField_CurrentTarget);

        this.JPanel_Fields.add(new JLabel("Actual"));
        this.JTextField_CurrentActual = new JTextField(this.CurrentActual);
        this.JPanel_Fields.add(this.JTextField_CurrentActual);

        this.JPanel_Fields.add(new JLabel("Variance"));
        this.JTextField_CurrentVariance = new JTextField(this.CurrentVariance);
        this.JTextField_CurrentVariance.setForeground(highlight);
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
