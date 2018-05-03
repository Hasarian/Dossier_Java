package uIController;

import Business.CareGiverBusiness;

public class CareGiverController
{
    private CareGiverBusiness business;

    public CareGiverController()
    {
        business=new CareGiverBusiness();
    }

    public void setCareGiverConnection(String login)
    {
            business.setCareGiver(business.getCareGiver(login));
    }
}
