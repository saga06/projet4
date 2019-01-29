package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CompteComptableTest {

    CompteComptable compteComptable = new CompteComptable();

    @Test
    public void GettersTest() {
        compteComptable.setLibelle("libelle");
        Assert.assertTrue(compteComptable.getLibelle().equals("libelle"));

        compteComptable.setNumero(1);
        Assert.assertTrue(compteComptable.getNumero().equals(1));

    }


    @Test
    public void getByNumero() {
        compteComptable.setNumero(10);
        compteComptable.setLibelle("test");
        CompteComptable compteComptable2 = new CompteComptable();
        compteComptable2.setNumero(11);
        compteComptable2.setLibelle("ZE");
        List<CompteComptable> liste =new ArrayList<>();
        liste.add(compteComptable);
        liste.add(compteComptable2);

        Assert.assertEquals(CompteComptable.getByNumero(liste,10).getLibelle(),"test");
    }

    @Test
    public void testToString() {
//        getByNumero();
//        String tostring = "CompteComptable{"+
//                "numero=1"+", libelle='test'}";
//        Assert.assertEquals(compteComptable.toString(),tostring);

    }

}