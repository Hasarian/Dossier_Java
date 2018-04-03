package falseData;

import java.util.ArrayList;
import java.util.Date;

public class StandardUser
{
    private String mail,name,firstname,street,note,cityName;
    private int houseNumber,localCode;
    private boolean isFreeOfCharge;
    private Date hireDate;
    private ArrayList<DoneCare> caringHistory;

    public String getMail(){return mail;}
    public String getName(){return name;}
    public String getFirstname(){return firstname; }
    public String getAdress()
    {return street+" n°"+houseNumber+" "+localCode+" "+cityName;}
    public boolean isFreeOfCharge(){return isFreeOfCharge;}
    public Date getHireDate(){return hireDate;}
    public String getStringHireDate()
    {return hireDate.toString();}
    public ArrayList<DoneCare>getCaringHistory()
    {return caringHistory;}
    public ArrayList<DoneCare>getCareByDate(Date date)
    {
        ArrayList<DoneCare> careByDate=new ArrayList<DoneCare>();
        for (DoneCare care:caringHistory)
        {
            if(care.getDate().compareTo(date)==0) careByDate.add(care);
        }
        return careByDate;
    }
}
