package Entry.View.Panels.ReservePanel;

import Entry.Constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel used to generate a new reservation
 */
public class ReservePanel extends JPanel {

    /**
     * Button to generate a reservation now
     */
    private final JButton now;

    /**
     * Button to generate a reservation later
     */
    private final JButton later;

    /**
     * How many people in the reservation
     */
    private final TextFieldPanel howMany;

    /**
     * The name of the reservation
     */
    private final TextFieldPanel name;

    /**
     * Creates a new reservation panel
     */
    public ReservePanel(){
        //TODO Make pretty
        setLayout(new BorderLayout());
        now = new JButton("Reservar ahora");
        now.setBackground(Color.ORANGE);
        later = new JButton("Reservar futuro");
        later.setBackground(Color.ORANGE);
        JTextField temp = new JTextField();
        howMany = new TextFieldPanel("Cuantos comensales",200);
        name = new TextFieldPanel("Nombre reserva" ,200);
/*
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel(new BorderLayout());

        centerPanel.add(name, BorderLayout.PAGE_START);
        centerPanel.add(howMany, BorderLayout.PAGE_END);
        bottomPanel.add(now, BorderLayout.LINE_START);
        bottomPanel.add(later, BorderLayout.LINE_END);

        add(centerPanel,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.PAGE_END);
*/
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
        //bottom.add(new JLabel("Nombre reserva"));
        //bottom.add(name);
        bottom.add(name);
        //bottom.add(new JLabel("Cuantas personas"));
        //bottom.add(howMany);
        bottom.add(howMany);

        add(center,BorderLayout.CENTER);
        add(bottom,BorderLayout.PAGE_END);

    }


    /**
     * Relates the controllers needed for a reservation panel
     * @param reserve the action listener for the buttons
     */
    public void relateControllers(ActionListener reserve) {
        now.setActionCommand(Constants.NOW);
        later.setActionCommand(Constants.LATER);
        now.addActionListener(reserve);
        later.addActionListener(reserve);
    }

    /**
     * Returns the name of the reservation
     * @return the name of the reservation
     */
    public String getName(){
        return name.getFieldText();
    }

    /**
     * Checks the data of the reservation to see if it is correct
     * @return 0 if everything is allright, 1 if the number of people is not a number,-1 if one of both fields are empty
     */
    public int notEmpty(){
        boolean validation = name.getFieldText()!=null&&!name.getFieldText().isEmpty()
                                &&howMany.getFieldText()!=null&&!howMany.getFieldText().isEmpty();
        if(validation){
            try{
                int data = Integer.parseInt(howMany.getFieldText());
                if(data<1){
                    return 1;
                }
                return 0;
            }catch (NumberFormatException ex){
                return 1;
            }
        }
        return -1;
    }

    /**
     * @return returns the number of people of the reservation
     */
    public int getNumOfPeople() {
        return Integer.parseInt(howMany.getFieldText());
    }

    /**
     * Clears the textfields of the panel
     */
    public void clear() {
        name.setFieldText("");
        howMany.setFieldText("");
    }
}
