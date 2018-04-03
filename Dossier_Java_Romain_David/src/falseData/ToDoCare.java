package falseData;

public class ToDoCare extends ToDo
{
    StandardUser systemUser;

    public DoneCare done()
    {
        return new DoneCare(this,systemUser);
    }
}
