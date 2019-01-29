package com.dummy.myerp.consumer.dao.impl.db.dao;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import testconsumer.ConsumerTestCase;

import com.dummy.myerp.model.bean.comptabilite.*;
import com.dummy.myerp.technical.exception.NotFoundException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ComptabiliteDaoImplTest extends ConsumerTestCase{

    private static ComptabiliteDaoImpl dao;
    private static EcritureComptable vEcritureComptable;
    private static Date cd;
    private static Integer cy;

    @BeforeClass
    public static void initAll() {
        dao = new ComptabiliteDaoImpl();
        cd = new Date();
        cy = LocalDateTime.ofInstant(cd.toInstant(), ZoneId.systemDefault()).toLocalDate().getYear();
    }

    @Before
    public void init() {
        vEcritureComptable = new EcritureComptable();
    }

    @AfterClass
    public static void tearDownAll() {
        vEcritureComptable = null;
    }


    // ==================== CompteComptable - GET ====================

    @Test
    public void testGetListCompteComptable() {
        List<CompteComptable> vList = dao.getListCompteComptable();
        assertEquals(7, vList.size());
    }


    // ==================== JournalComptable - GET ====================

    @Test
    public void testGetListJournalComptable() {
        List<JournalComptable> vList = dao.getListJournalComptable();
        assertEquals(4, vList.size());
    }


    // ==================== EcritureComptable - GET ====================

    @Test
    public void testGetListEcritureComptable() {
        List<EcritureComptable> vList = dao.getListEcritureComptable();
        assertNotNull(vList.size());
    }

    @Test(expected = NotFoundException.class)
    public void testGetEcritureComptable() throws NotFoundException {
        EcritureComptable vEcritureComptable = dao.getEcritureComptable(-3);
        assertEquals("BQ-2016/00003", vEcritureComptable.getReference());

        dao.getEcritureComptable(0);
    }

    @Test
    public void testGetEcritureComptableByRef() throws NotFoundException {

        EcritureComptable vEcritureComptable = dao.getEcritureComptableByRef("BQ-2016/00003");
        assertEquals("BQ", vEcritureComptable.getJournal().getCode());
        String vEcritureYear = new SimpleDateFormat("yyyy").format(vEcritureComptable.getDate());
        assertEquals("2016", vEcritureYear);
        assertEquals(-3, vEcritureComptable.getId().intValue());

    }

    @Test(expected = NotFoundException.class)
    public void testGetEcritureComptableByRefException() throws NotFoundException {

        dao.getEcritureComptableByRef("BQ-2016/33333");
    }

    @Test
    public void testLoadListLigneEcriture() {
        vEcritureComptable.setId(-5);
        dao.loadListLigneEcriture(vEcritureComptable);
        assertEquals(2, vEcritureComptable.getListLigneEcriture().size());
    }


    // ==================== EcritureComptable - INSERT ====================

    @Test
    public void testInsertEcritureComptable() {
        vEcritureComptable.setJournal(new JournalComptable("OD", "Opérations Diverses"));
        vEcritureComptable.setReference("OD-" + cy + "/00200");
        vEcritureComptable.setDate(cd);
        vEcritureComptable.setLibelle("Sandwichs");

        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(606),
                "Club saumon", new BigDecimal(10),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(4456),
                "TVA 20%", new BigDecimal(2),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                "Facture S110001", null,
                new BigDecimal(12)));

        dao.insertEcritureComptable(vEcritureComptable);
    }


    // ==================== EcritureComptable - UPDATE ====================

    @Test
    public void testUpdateEcritureComptable() {
        vEcritureComptable.setId(-4);
        vEcritureComptable.setJournal(new JournalComptable("OD", "Opérations Diverses"));
        vEcritureComptable.setReference("OD-" + cy + "/00200");
        vEcritureComptable.setDate(cd);
        vEcritureComptable.setLibelle("Sandwichs");

        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(606),
                "Club saumon", new BigDecimal(10),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(4456),
                "TVA 20%", new BigDecimal(2),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(401),
                "Facture S110001", null,
                new BigDecimal(12)));

        dao.updateEcritureComptable(vEcritureComptable);

        assertEquals(vEcritureComptable.getLibelle(), "Sandwichs");
    }


    // ==================== EcritureComptable - DELETE ====================

    @Test
    public void testDeleteEcritureComptable() {
        dao.deleteEcritureComptable(1);
    }


    // ==================== SequenceEcritureComptable ====================

    /*@Test
    public void testGetSequenceByCodeAndAnneeCourante() throws NotFoundException {
        SequenceEcritureComptable vRechercheSequence = new SequenceEcritureComptable();
        vRechercheSequence.setJournalCode("OD");
        vRechercheSequence.setAnnee(2016);
        vRechercheSequence.setDerniereValeur(88);

        SequenceEcritureComptable vExistingSequence = dao.getSequenceByCurrentYearAndCode(vRechercheSequence);

        assertEquals("OD", vExistingSequence.getJournalCode());
        assertEquals(2016, vExistingSequence.getAnnee().intValue());
        assertEquals(88, vExistingSequence.getDerniereValeur().intValue());


    }*/

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void testGetSequenceByCurrentYearAndCodeExcption() throws NotFoundException {
        SequenceEcritureComptable vRechercheSequence = new SequenceEcritureComptable();
        vRechercheSequence.setJournalCode("test");
        vRechercheSequence.setAnnee(2016);
        vRechercheSequence.setDerniereValeur(88);

        dao.getSequenceByCurrentYearAndCode(vRechercheSequence);

    }

    @Test
    public void testInsertOrUpdateSequenceEcritureComptable() {
        SequenceEcritureComptable vSequenceEcritureComptable = new SequenceEcritureComptable();
        vSequenceEcritureComptable.setJournalCode("VE");
        vSequenceEcritureComptable.setAnnee(1963);
        vSequenceEcritureComptable.setDerniereValeur(42);

        dao.insertSequenceEcritureComptable(vSequenceEcritureComptable);
    }
}