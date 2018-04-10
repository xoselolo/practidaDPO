package Entry.View.Panels;

import Entry.Constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ReservePanel extends JPanel {

    private final JButton now;
    private final JButton later;
    private final JTextField howMany;
    private final JTextField name;

    public ReservePanel(){
        //TODO Make pretty
        setLayout(new BorderLayout());
        now = new JButton("Reservar ahora");
        later = new JButton("Reservar futuro");
        howMany = new JTextField();
        name = new JTextField();

        JPanel center = new JPanel(new GridLayout(3,2));
        JPanel centerLeft = new JPanel();
        centerLeft.add(now);
        JPanel centerRight = new JPanel();
        centerRight.add(later);
        center.add(new JLabel());
        center.add(new JLabel());
        center.add(centerLeft);
        center.add(centerRight);

        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom,BoxLayout.PAGE_AXIS));
        bottom.add(new JLabel("Nombre reserva"));
        bottom.add(name);
        bottom.add(new JLabel("Cuantas personas"));
        bottom.add(howMany);

        add(center,BorderLayout.CENTER);
        add(bottom,BorderLayout.PAGE_END);

    }

    public void relateControllers(ActionListener reserve) {
        now.setActionCommand(Constants.NOW);
        later.setActionCommand(Constants.LATER);
        now.addActionListener(reserve);
        later.addActionListener(reserve);
    }

    public String getName(){
        return name.getText();
    }

    public int notEmpty(){
        boolean validation = name.getText()!=null&&!name.getText().isEmpty()
                                &&howMany.getText()!=null&&!howMany.getText().isEmpty();
        if(validation){
            try{
                Integer.parseInt(howMany.getText());
                return 0;
            }catch (NumberFormatException ex){
                return 1;
            }
        }
        return -1;
    }

    public int getNumOfPeople() {
        return Integer.parseInt(howMany.getText());
    }

    public void clear() {
        name.setText("");
        howMany.setText("");
    }
}
