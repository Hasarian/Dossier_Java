package userInterface;

import Model.Localite;
import erreurs.EmailRegexErreur;
import erreurs.NumberExpection;
import org.junit.Assert;
import org.junit.Test;


import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class RegexFormCareGiverTest {
    private RegistrationFormCareGiver form;
    // public Integer getNumTel() throws NumberExpection public Integer getHouseNumberInfo() throws NumberExpection 	public String getMailInfo() throws EmailRegexErreur
    @org.junit.Before
    public void setUp() throws Exception {
            form=new RegistrationFormCareGiver(null);
            form.setInfos("jean-jacques","goldman","yolo","grande vue","je ne le dirai pas",
                    "0458621","",true,new Localite(),new GregorianCalendar());
    }

    @Test
    public void getNameInfo() {
        Assert.assertEquals("jean-jacques",form.getNameInfo());
    }

    @org.junit.Test
    public void getLastNameInfo() {
        Assert.assertNotEquals("goldmanneuh",form.getLastNameInfo());
    }

    @org.junit.Test (expected = EmailRegexErreur.class)
    public void getMailInfo() throws EmailRegexErreur {
        Assert.assertNotEquals("",form.getMailInfo());
    }

    @org.junit.Test
    public void getStreetInfo() {
        Assert.assertEquals("grande vue",form.getStreetInfo());
    }

    @org.junit.Test (expected = NumberExpection.class)
    public void getHouseNumberInfo() throws NumberExpection {
        Assert.assertNotEquals("",form.getHouseNumberInfo());
    }

    @org.junit.Test
    public void getNumTel() throws NumberExpection {
        Assert.assertEquals(new Integer(458621),form.getNumTel());
    }
    @Test
    public void getNote()
    {
        Assert.assertEquals(null,form.getNoteText());
    }
}
