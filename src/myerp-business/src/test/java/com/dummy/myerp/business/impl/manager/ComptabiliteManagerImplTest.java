package com.dummy.myerp.business.impl.manager;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.dummy.myerp.business.contrat.manager.ComptabiliteManager;
import com.dummy.myerp.technical.exception.NotFoundException;
import com.dummy.myerp.testbusiness.business.BusinessTestCase;
import org.junit.Assert;
import org.junit.Test;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static junit.framework.TestCase.assertTrue;


public class ComptabiliteManagerImplTest extends BusinessTestCase {

    EcritureComptable ec = new EcritureComptable();
    private ComptabiliteManagerImpl manager = new ComptabiliteManagerImpl();
    private ComptabiliteManager comptabiliteManager = getBusinessProxy().getComptabiliteManager();


    @Test
    public void getListCompteComptable() {
        List<CompteComptable> vList = comptabiliteManager.getListCompteComptable();
        Assert.assertEquals(7, vList.size());
    }
    @Test
    public void getListJournalComptable() {
        List<JournalComptable> vList = comptabiliteManager.getListJournalComptable();
        Assert.assertEquals(4, vList.size());
    }
    @Test
    public void getListEcritureComptable() {
        List<EcritureComptable> vList = comptabiliteManager.getListEcritureComptable();
        Assert.assertEquals(5, vList.size());
    }


    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testAddReference() throws Exception{
        EcritureComptable vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(512),
                null, null,
                new BigDecimal(123)));
        manager.addReference(vEcritureComptable);
    }

    @Test
    public void checkEcritureComptableUnit() throws Exception {
        EcritureComptable vEcritureComptable = new EcritureComptable();
        Date vCurrentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String vCurrentYear = sdf.format(vCurrentDate);
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(vCurrentDate);
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.setReference("AC-" + vCurrentYear + "/00001");
        vEcritureComptable.getListLigneEcriture()
                .add(new LigneEcritureComptable(new CompteComptable(1), null, new BigDecimal(123), null));
        vEcritureComptable.getListLigneEcriture()
                .add(new LigneEcritureComptable(new CompteComptable(2), null, null, new BigDecimal(123)));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitViolation() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG2() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(1234)));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void checkEcritureComptableUnitRG3() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test
    public void testCheckEcritureContext() throws Exception{
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(512),
                null, null,
                new BigDecimal(123)));
        manager.checkEcritureComptableContext(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void testInsertEcritureComptable() throws Exception{
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setId(2);
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(512),
                null, null,
                new BigDecimal(123)));
        manager.insertEcritureComptable(vEcritureComptable);
    }

    @Test(expected = FunctionalException.class)
    public void testInsertEcritureComptableViolation() throws Exception{
        EcritureComptable vEcritureComptable = new EcritureComptable();
        manager.insertEcritureComptable(vEcritureComptable);
    }



    @Test(expected = FunctionalException.class)
    public void testUpdateEcritureComptable() throws Exception {
        EcritureComptable vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setId(2);
        manager.updateEcritureComptable(vEcritureComptable);
    }

    // pour la soutenance
    @Test
    public void TestSucceed() {
        boolean test = true;
        assertTrue(test);
    }

    // pour la soutenance
   /* @Test
    public void TestFail() {
        boolean test = false;
        assertTrue(test);
    }*/

    @Test
    public void testDeleteEcritureComptasble(){
        manager.deleteEcritureComptable(2);
    }





}
