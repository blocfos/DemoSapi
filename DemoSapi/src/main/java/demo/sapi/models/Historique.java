package demo.sapi.models;

// Generated 20 nov. 2014 18:11:23 by Hibernate Tools 3.6.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Historique generated by hbm2java
 */
@Entity
@Table(name = "HISTORIQUE", catalog = "`sis-aga`")
public class Historique implements java.io.Serializable {

    private long numero;
    private Date tempsModif;
    private String fichier;
    private String type;
    private String remplacement;
    private Short definitif;
    private String nom;

    public Historique() {
    }

    public Historique(long numero) {
        this.numero = numero;
    }

    public Historique(long numero, Date tempsModif, String fichier,
            String type, String remplacement, Short definitif, String nom) {
        this.numero = numero;
        this.tempsModif = tempsModif;
        this.fichier = fichier;
        this.type = type;
        this.remplacement = remplacement;
        this.definitif = definitif;
        this.nom = nom;
    }

    @Id
    @Column(name = "NUMERO", unique = true, nullable = false, precision = 10, scale = 0, columnDefinition = "decimal")
    public long getNumero() {
        return this.numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "TEMPS_MODIF", length = 0)
    public Date getTempsModif() {
        return this.tempsModif;
    }

    public void setTempsModif(Date tempsModif) {
        this.tempsModif = tempsModif;
    }

    @Column(name = "FICHIER", length = 50)
    public String getFichier() {
        return this.fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    @Column(name = "TYPE", length = 50)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "REMPLACEMENT", length = 70)
    public String getRemplacement() {
        return this.remplacement;
    }

    public void setRemplacement(String remplacement) {
        this.remplacement = remplacement;
    }

    @Column(name = "DEFINITIF", precision = 3, scale = 0, columnDefinition = "decimal")
    public Short getDefinitif() {
        return this.definitif;
    }

    public void setDefinitif(Short definitif) {
        this.definitif = definitif;
    }

    @Column(name = "NOM", length = 16)
    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
