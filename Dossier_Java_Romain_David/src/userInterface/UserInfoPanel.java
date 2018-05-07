package userInterface;

import Model.CareGiver;
import erreurs.BDConnexionError;
import erreurs.ErrorNull;
import uIController.CareGiverController;

public class UserInfoPanel extends RegistrationFormCareGiver
{
    public UserInfoPanel(MainFrame frame, CareGiverController user) throws BDConnexionError, ErrorNull
    {
        super(frame);

    }
}
