package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

public class SequenceEcritureComptableTest {

    SequenceEcritureComptable seq = new SequenceEcritureComptable();

    @Test
    public void testToString() {
        seq.setDerniereValeur(10);
        seq.setAnnee(2000);
        seq.setJournalCode("code");
        int der = seq.getDerniereValeur();
        int date = seq.getAnnee();
        String code = seq.getJournalCode();

        String tostring = "SequenceEcritureComptable{annee=2000, derniereValeur=10, journalCode=code}";
        Assert.assertEquals(seq.toString(),tostring);
    }

    @Test
    public void testSetAndGet() {seq.setDerniereValeur(1);
        seq.setAnnee(2019);
        int last = seq.getDerniereValeur();
        int date = seq.getAnnee();
        Assert.assertEquals(last,1);
        Assert.assertEquals(date,2019);
    }



}