package com.dummy.myerp.model.bean.comptabilite;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;


public class EcritureComptableTest {

    EcritureComptable vEcriture = new EcritureComptable();

    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                                     .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
        LigneEcritureComptable vRetour = new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
                                                                    vLibelle,
                                                                    vDebit, vCredit);
        return vRetour;
    }

    @Test
    public void getReference() {
        vEcriture.setReference("BQ-2018/00005");
        Assert.assertTrue("La référence ne respecte pas le format \"XX-AAAA/#####\"", vEcriture.getReference().matches("[A-Z]{1,5}-\\d{4}/\\d{5}"));
    }

    @Test
    public void toStringTest(){
        EcritureComptable vEcritureComptable = new EcritureComptable();

        vEcritureComptable.setId(1);
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achats"));
        Date dateNew = new Date();
        vEcritureComptable.setDate(dateNew);
        vEcritureComptable.setReference("AC-2018/00001");
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                null, new BigDecimal(123),
                null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                null, null,
                new BigDecimal(123)));

        String tostring =    "EcritureComptable{id=1, " +
                "journal=JournalComptable{code='AC'" +
                ", libelle='Achats'}, reference='AC-2018/00001', date="+dateNew+
                ", libelle='Libelle', totalDebit=123, totalCredit=123" +
                ", listLigneEcriture=[\n" +
                "LigneEcritureComptable{compteComptable=CompteComptable{numero=1, libelle='null'}, libelle='null', debit=123, credit=null}\n" +
                "LigneEcritureComptable{compteComptable=CompteComptable{numero=2, libelle='null'}, libelle='null', debit=null, credit=123}\n" +
                "]}";

        Assert.assertTrue(vEcritureComptable.toString().equals(tostring));
    }

    @Test
    public void getTotalCredit() {
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "30"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "1", "2"));
        Assert.assertEquals(33, vEcriture.getTotalCredit().intValue());
    }

    @Test
    public void referenceCodeEqualJournalCode() {
        vEcriture.setJournal(new JournalComptable("BQ", "Banque"));
        vEcriture.setReference("BQ-2016/00003");
        assertEquals(vEcriture.getReference().substring(0, 2), vEcriture.getJournal().getCode());
    }

    @Test
    public void getTotalDebit() {
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "40", "7"));
        Assert.assertEquals(341, vEcriture.getTotalDebit().intValue());
    }

    @Test
    public void isEquilibree() {
        vEcriture = new EcritureComptable();
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "40", "7"));
        Assert.assertTrue(vEcriture.toString(), vEcriture.isEquilibree());

        vEcriture.getListLigneEcriture().clear();
        vEcriture.setLibelle("Non équilibrée");
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "30"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "1", "2"));
        Assert.assertFalse(vEcriture.toString(), vEcriture.isEquilibree());
    }

    @Test
    public void GettersTest() {
        String ref = "reference";
        vEcriture.setLibelle(ref);
        Assert.assertTrue(vEcriture.getLibelle().equals("reference"));
        vEcriture.setDate(new Date());
        Assert.assertTrue(vEcriture.getDate().equals(new Date()));
        vEcriture.setReference("reference 1");
        Assert.assertTrue(vEcriture.getReference().equals("reference 1"));
        vEcriture.setId(1);
        Assert.assertTrue(vEcriture.getId().equals(1));
        vEcriture.setLibelle("libellé");
        Assert.assertTrue(vEcriture.getLibelle().equals("libellé"));
    }





}
