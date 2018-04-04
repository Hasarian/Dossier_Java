package falseData;

public class ToDo
{
    private CareFolder careFolder;
    private boolean done;
    public boolean isDone(){return done;}
    public void setDone(boolean done){this.done=done;}

    public CareFolder getCareFolder() {return careFolder;}

    public ToDo()
    {
        done=false;
    }
}
