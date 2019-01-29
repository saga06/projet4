package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class JournalComptableTest {

    private static JournalComptable vJournal  = new JournalComptable();
    private static List<JournalComptable> vList;


    @Test
    public void getByCode() {
        vJournal.setCode("AC");
        vJournal.setLibelle("Achat");
        vList = new ArrayList<>(0);
        vList.add(vJournal);
        vList.add(new JournalComptable("BQ", "Banque"));
        Assert.assertEquals(JournalComptable.getByCode(vList, "AC"), vJournal);
    }

    @Test
    public void toStringTest(){
        JournalComptable journalComptable = new JournalComptable();
        journalComptable.setCode("code");
        journalComptable.setLibelle("libelle");
        String string = journalComptable.toString();
        Assert.assertEquals(string, "JournalComptable{code='code', libelle='libelle'}");
    }

    @Test
    public void GettersTest() {
        vJournal.setCode("code");
        Assert.assertTrue(vJournal.getCode().equals("code"));

        vJournal.setLibelle("lib");
        Assert.assertTrue(vJournal.getLibelle().equals("lib"));

    }
}