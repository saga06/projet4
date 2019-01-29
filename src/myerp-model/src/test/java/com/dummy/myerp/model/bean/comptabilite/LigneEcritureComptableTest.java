package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class LigneEcritureComptableTest {

    CompteComptable compteComptable = new CompteComptable();
    LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable();

    @Test
    public void testToString() {
        compteComptable.setNumero(1);
        compteComptable.setLibelle("test");
        ligneEcritureComptable.setCompteComptable(compteComptable);
        int num = ligneEcritureComptable.getCompteComptable().getNumero();
        Assert.assertEquals(num,1);

        ligneEcritureComptable.setLibelle("test2");
        Assert.assertEquals(ligneEcritureComptable.getLibelle(),"test2");

        ligneEcritureComptable.setDebit(new BigDecimal(1));
        ligneEcritureComptable.setCredit(new BigDecimal(2));
        Assert.assertEquals(ligneEcritureComptable.getDebit(),new BigDecimal(1));
        Assert.assertEquals(ligneEcritureComptable.getCredit(),new BigDecimal(2));
    }

    @Test
    public void testSetAndGet() {
        compteComptable.setNumero(1);
        compteComptable.setLibelle("test");
        ligneEcritureComptable.setCompteComptable(compteComptable);
        int num = ligneEcritureComptable.getCompteComptable().getNumero();
        Assert.assertEquals(num, 1);

        ligneEcritureComptable.setLibelle("lib");
        Assert.assertEquals(ligneEcritureComptable.getLibelle(), "lib");

        ligneEcritureComptable.setDebit(new BigDecimal(1));
        ligneEcritureComptable.setCredit(new BigDecimal(2));
        Assert.assertEquals(ligneEcritureComptable.getDebit(), new BigDecimal(1));
        Assert.assertEquals(ligneEcritureComptable.getCredit(), new BigDecimal(2));
    }

}