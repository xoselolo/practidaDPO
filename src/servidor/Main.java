package servidor;

import servidor.controller.*;
import servidor.model.*;
import servidor.model.Database.BBDDManager;
import servidor.network.MainServer;
import servidor.view.MainView;

import javax.swing.*;

public class Main {

    public static final String BBDD = "Restaurant";
    public static final String USERNAME = "RestaurantUser";
    public static final String PASSWORD = "ResUser";

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                BBDDManager.setUsername(USERNAME);
                BBDDManager.setPassword(PASSWORD);
                BBDDManager.getInstance(BBDD);

                PlatosManager platosManager = new PlatosManager();
                platosManager.loadPlatos();
                ReservasManager reservasManager = new ReservasManager();
                PedidosManager pedidosManager = new PedidosManager();
                MesasManager mesasManager = new MesasManager();

                MainView mainView = new MainView();
                mainView.initMainView(platosManager.getPlatos(), mesasManager.getMesas(), pedidosManager.getPedidos());

                SelectorViewListener selectorViewListener = new SelectorViewListener(mainView);

                GestionCartaViewListener gestionCartaViewListener = new GestionCartaViewListener(
                                                                  mainView.getGestionCartaView().getPlatosOptionsView(),
                                                                  mainView.getGestionCartaView().getPlatosView(),
                                                                  platosManager);

                GestionTop5ViewListener gestionTop5ViewListener = new GestionTop5ViewListener(
                                                                      mainView, BBDDManager.getInstance(BBDD),
                                                                      mainView.getGestionTop5View());

                MesasViewListener mesasViewListener = new MesasViewListener(mainView, mesasManager);

                MesasOptionsViewListener mesasOptionsViewListener = new MesasOptionsViewListener(mainView,
                                                                                                 mesasManager,
                                                                                                 mesasViewListener);

                PlatosPendientesController platosPendientesController = new PlatosPendientesController(mainView,
                        mainView.getGestionPedidosView().getPlatosPendientes(),pedidosManager);

                PedidosListListener pedidosListListener = new PedidosListListener(mainView, pedidosManager,
                                                                                  platosPendientesController);

                mainView.registerControllers(selectorViewListener, gestionCartaViewListener, gestionTop5ViewListener,
                                             mesasOptionsViewListener, mesasViewListener);

                MainServer mainServer = new MainServer(mainView, platosManager, reservasManager, pedidosManager,
                                                       pedidosListListener, gestionCartaViewListener);

                gestionCartaViewListener.registerServer(mainServer.getReservaServer());
                platosPendientesController.registerServer(mainServer.getReservaServer());
                mainServer.initServers();

            }
        });
    }
}
