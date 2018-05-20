package userInterface;

import erreurs.BDConnexionErreur;
import erreurs.ErreurrNull;
import erreurs.SoignantInexistant;
import uIController.SoignantController;
import uIController.ListesAnimauxController;
import uIController.TacheController;

import javax.swing.*;

public class InfoTachePanel extends JPanel
{
    private TacheController controller;
    private ListesAnimauxController listControl;
    public InfoTachePanel(Integer id, SoignantController user) throws BDConnexionErreur, ErreurrNull, SoignantInexistant
    {
        listControl= ListesAnimauxController.obtenirListController(user);
        controller=new TacheController(id);
    }
}
