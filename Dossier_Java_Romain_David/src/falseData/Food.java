package falseData;

import sun.java2d.pipe.SpanShapeRenderer;

public class Food extends SimpleCare
{
    private int qte;
    private String unit;

    public Food()
    {
        super("this is food");
        qte=5;
        unit="kg";
    }

    @Override
    public String toString() {
        return super.toString()+"    "+qte+unit;
    }
}
