package userInterface;

import model.Localite;
import erreurs.erreurFormat.EmailRegexErreur;
import erreurs.erreurFormat.MauvaiseTailleString;
import erreurs.erreurFormat.NombreExpection;
import org.junit.Assert;
import org.junit.Test;


import java.util.GregorianCalendar;

public class RegexFormSoignantTest {
    private FormulaireInscriptionSoignantPanel form;
    // public Integer getNumTel() throws NombreExpection public Integer getHouseNumberInfo() throws NombreExpection 	public String getMailInfo() throws EmailRegexErreur
    @org.junit.Before
    public void setUp() throws Exception {
            form=new FormulaireInscriptionSoignantPanel(null);
            form.setInfos("jean-jacques","goldman","yolo","grande vue","je ne le dirai pas",
                    "0458621","",true,new Localite(),new GregorianCalendar());
    }

    @Test
    public void getNameInfo()throws MauvaiseTailleString {
        Assert.assertEquals("jean-jacques",form.getNameInfo());
    }

    @org.junit.Test
    public void getLastNameInfo() throws MauvaiseTailleString {
        Assert.assertNotEquals("goldmanneuh",form.getLastNameInfo());
    }

    @org.junit.Test (expected = EmailRegexErreur.class)
    public void getMailInfo() throws EmailRegexErreur, MauvaiseTailleString {
        Assert.assertNotEquals("",form.getMailInfo());
    }

    @org.junit.Test
    public void getStreetInfo() throws MauvaiseTailleString {
        Assert.assertEquals("grande vue",form.getStreetInfo());
    }

    @org.junit.Test (expected = NombreExpection.class)
    public void getHouseNumberInfo() throws NombreExpection {
        Assert.assertNotEquals("",form.getHouseNumberInfo());
    }

    @org.junit.Test
    public void getNumTel() throws NombreExpection {
        Assert.assertEquals(new Integer(458621),form.getNumTel());
    }
    @Test
    public void getNote() throws MauvaiseTailleString
    {
        Assert.assertEquals(null,form.getNoteText());
    }
}
