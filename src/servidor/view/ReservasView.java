package servidor.view;

import servidor.model.Database.InfoResultSetReserva;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ReservasView extends JPanel {

    /**
     * Constantes
     */
    public static final String NOMBRE_RESERVA_TAG = "Nombre de la Reserva";
    public static final String HORA_TAG = "Hora (hh:mm)";
    public static final String FECHA_TAG = "Fecha (dd/mm/aaaa)";

    /**
     * Atributos de la clase
     */
    private ArrayList<ReservaView> reservasView;
    private JPanel jpMain;
    private JPanel jpTitle;
    private JPanel jpReservas;
    private JScrollPane jspReservas;

    /**
     * Constructor sin parámetros
     */
    public ReservasView() {
        setLayout(new BorderLayout());

        reservasView = new ArrayList<>();

        jpReservas = new JPanel();
        jpReservas.setLayout(new BoxLayout(jpReservas, BoxLayout.Y_AXIS));

        jspReservas = new JScrollPane(jpReservas);
        jspReservas.getViewport().setView(jpReservas);
        jspReservas.setBorder(BorderFactory.createEmptyBorder());

        jpTitle = new JPanel();
        jpTitle.setLayout(new GridLayout(1, 3));
        jpTitle.add(new JLabel(NOMBRE_RESERVA_TAG));
        jpTitle.add(new JLabel(FECHA_TAG));
        jpTitle.add(new JLabel(HORA_TAG));

        jpMain = new JPanel(new BorderLayout());
        jpMain.add(jpTitle, BorderLayout.PAGE_START);

        jpMain.add(jspReservas, BorderLayout.CENTER);

        add(jpMain, BorderLayout.CENTER);
    }

    /**
     * Función que inicializa la vista a través de las reservas
     * @param reservas
     */
    public void initReservas(ArrayList<InfoResultSetReserva> reservas) {
        reservasView = new ArrayList<ReservaView>();
        reservasView.clear();
        jpReservas.removeAll();

        if (reservas!= null) {
            for(InfoResultSetReserva reserva : reservas) {
                ReservaView reservaView = new ReservaView(reserva);
                reservasView.add(reservaView);
                jpReservas.add(reservaView);
            }
        }

        jspReservas = new JScrollPane(jpReservas);
        jpMain.add(jspReservas, BorderLayout.CENTER);
    }

    /**
     * Añadir una reserva
     */
    public void addReserva(InfoResultSetReserva reserva) {
        ReservaView reservaView = new ReservaView(reserva);
        reservasView.add(reservaView);
        jpReservas.add(reservaView);
        jpReservas.updateUI();
    }

    /**
     * Función para refrescar las reservas
     * @param reservas
     */
    public void refreshReservas(ArrayList<InfoResultSetReserva> reservas) {
        reservasView.clear();
        jpMain.removeAll();
        updateUI();

        jpMain.add(jpTitle, BorderLayout.PAGE_START);
        jpMain.add(jpReservas, BorderLayout.CENTER);

        initReservas(reservas);
        jpMain.updateUI();
        paintAll(getGraphics());
    }
}
