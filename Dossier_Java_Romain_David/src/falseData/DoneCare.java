package falseData;

import java.util.Date;

public class DoneCare
{
    private int id;
    private Date date;
    private StandardUser carer;
    private ToDoCare care;

    public Date getDate(){return date;}

    public DoneCare(ToDoCare care,StandardUser carer)
    {
        this.care=care;
        this.carer=carer;
        id=0;
        date= new Date();
    }
}
