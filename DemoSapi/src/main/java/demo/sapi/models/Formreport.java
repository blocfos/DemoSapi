package demo.sapi.models;

// Generated 20 nov. 2014 18:11:23 by Hibernate Tools 3.6.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Formreport generated by hbm2java
 */
@Entity
@Table(name = "FORMREPORT", catalog = "`sis-aga`")
public class Formreport implements java.io.Serializable {

    private Integer id;
    private int niveau5;
    private int niveau4;
    private int niveau3;
    private int niveau0;
    private String statutline;
    private int typemateriel;
    private int operateur;
    private int annee;
    private int mois;

    public Formreport() {
    }

    public Formreport(int niveau5, int niveau4, int niveau3, int niveau0,
            String statutline, int typemateriel, int operateur, int annee,
            int mois) {
        this.niveau5 = niveau5;
        this.niveau4 = niveau4;
        this.niveau3 = niveau3;
        this.niveau0 = niveau0;
        this.statutline = statutline;
        this.typemateriel = typemateriel;
        this.operateur = operateur;
        this.annee = annee;
        this.mois = mois;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "NIVEAU5", nullable = false)
    public int getNiveau5() {
        return this.niveau5;
    }

    public void setNiveau5(int niveau5) {
        this.niveau5 = niveau5;
    }

    @Column(name = "NIVEAU4", nullable = false)
    public int getNiveau4() {
        return this.niveau4;
    }

    public void setNiveau4(int niveau4) {
        this.niveau4 = niveau4;
    }

    @Column(name = "NIVEAU3", nullable = false)
    public int getNiveau3() {
        return this.niveau3;
    }

    public void setNiveau3(int niveau3) {
        this.niveau3 = niveau3;
    }

    @Column(name = "NIVEAU0", nullable = false)
    public int getNiveau0() {
        return this.niveau0;
    }

    public void setNiveau0(int niveau0) {
        this.niveau0 = niveau0;
    }

    @Column(name = "STATUTLINE", nullable = false, length = 25)
    public String getStatutline() {
        return this.statutline;
    }

    public void setStatutline(String statutline) {
        this.statutline = statutline;
    }

    @Column(name = "TYPEMATERIEL", nullable = false)
    public int getTypemateriel() {
        return this.typemateriel;
    }

    public void setTypemateriel(int typemateriel) {
        this.typemateriel = typemateriel;
    }

    @Column(name = "OPERATEUR", nullable = false)
    public int getOperateur() {
        return this.operateur;
    }

    public void setOperateur(int operateur) {
        this.operateur = operateur;
    }

    @Column(name = "ANNEE", nullable = false)
    public int getAnnee() {
        return this.annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    @Column(name = "MOIS", nullable = false)
    public int getMois() {
        return this.mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

}
