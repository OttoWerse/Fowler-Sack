import javax.swing.*;
import java.awt.*;

public class Window {
    public static void main(String[] args) {
        JFrame f=new JFrame();

        f.setSize(800,400);
        f.setLayout(new GridLayout(1, 2));

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5,2));
        p.setVisible(true);

        JScrollPane s = new JScrollPane();

        p.add(new JLabel("Station ID"));
        p.add(new JTextField());

        p.add(new JLabel("Date"));
        p.add(new JTextField());

        p.add(new JLabel("Target"));
        p.add(new JTextField());

        p.add(new JLabel("Actual"));
        p.add(new JTextField());

        p.add(new JLabel("Variance"));
        p.add(new JTextField());

        f.add(s);
        f.add(p);

        f.setVisible(true);
    }
}
