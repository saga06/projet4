package com.dummy.myerp.model.bean.comptabilite;


/**
 * Bean représentant une séquence pour les références d'écriture comptable
 */
public class SequenceEcritureComptable {

    // ==================== Attributs ====================
    /** L'année */
    private Integer annee;
    /** La dernière valeur utilisée */
    private Integer derniereValeur;
    /* Modif ICI*/
    /** Le code journal */
    private String journalCode;
    /* Fin de modif ici*/

    // ==================== Constructeurs ====================
    /**
     * Constructeur
     */
    public SequenceEcritureComptable() {
    }

    /**
     * Constructeur
     *
     * @param pAnnee -
     * @param pDerniereValeur -
     */
    public SequenceEcritureComptable(String pCodeJournal, Integer pAnnee, Integer pDerniereValeur) {
        journalCode = pCodeJournal;
        annee = pAnnee;
        derniereValeur = pDerniereValeur;
    }

    public SequenceEcritureComptable(String pCodeJournal, Integer pAnnee) {
        journalCode = pCodeJournal;
        annee = pAnnee;
    }



    // ==================== Getters/Setters ====================
    public Integer getAnnee() {
        return annee;
    }
    public void setAnnee(Integer pAnnee) {
        annee = pAnnee;
    }
    public Integer getDerniereValeur() {
        return derniereValeur;
    }
    public void setDerniereValeur(Integer pDerniereValeur) {
        derniereValeur = pDerniereValeur;
    }
    /* Modif ICI*/
    public String getJournalCode() { return journalCode; }
    public void setJournalCode(String journalCode) { this.journalCode = journalCode; }
    /* Fin de modif ici*/

    // ==================== Méthodes ====================
    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        final String vSEP = ", ";
        vStB.append("{")
            .append("annee=").append(annee)
            .append(vSEP).append("derniereValeur=").append(derniereValeur)
            .append(vSEP).append("journalCode=").append(journalCode)
            .append("}");
        return vStB.toString();
    }
}
