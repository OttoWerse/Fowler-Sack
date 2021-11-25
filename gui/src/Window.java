import javax.swing.*;
import java.awt.*;

public class Window {
    // Variables to be displayed
    String CurrentStationID;
    String CurrentDate;
    String CurrentTarget;
    String CurrentActual;
    String CurrentVariance;
    String[] CurrentList = {"ABC", "DEF", "GHI"};

    // GUI Elements
    JFrame JFrame_Main;
    JPanel JPanel_Fields;
    JList JList_Stations;
    JScrollPane JScrollPane_Stations;

    void initialise() {
        this.JFrame_Main = new JFrame();

        this.JFrame_Main.setSize(800,400);
        this.JFrame_Main.setLayout(new GridLayout(1, 2));

        this.JPanel_Fields = new JPanel();
        this.JPanel_Fields.setLayout(new GridLayout(5,2));
        this.JPanel_Fields.setVisible(true);

        this.JList_Stations = new JList(this.CurrentList);

        this.JScrollPane_Stations = new JScrollPane(this.JList_Stations);
        this.JScrollPane_Stations.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.JScrollPane_Stations.setVisible(true);

        this.JPanel_Fields.add(new JLabel("Station ID"));
        this.JPanel_Fields.add(new JTextField(this.CurrentStationID));

        this.JPanel_Fields.add(new JLabel("Date"));
        this.JPanel_Fields.add(new JTextField(this.CurrentDate));

        this.JPanel_Fields.add(new JLabel("Target"));
        this.JPanel_Fields.add(new JTextField(this.CurrentTarget));

        this.JPanel_Fields.add(new JLabel("Actual"));
        this.JPanel_Fields.add(new JTextField(this.CurrentActual));

        this.JPanel_Fields.add(new JLabel("Variance"));
        this.JPanel_Fields.add(new JTextField(this.CurrentVariance));

        this.JFrame_Main.add(this.JScrollPane_Stations);
        this.JFrame_Main.add(this.JPanel_Fields);

        this.JFrame_Main.setVisible(true);
    }


    void update() {

    }
}
