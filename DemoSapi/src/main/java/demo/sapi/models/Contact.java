package demo.sapi.models;

// Generated 20 nov. 2014 18:11:23 by Hibernate Tools 3.6.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Contact generated by hbm2java
 */
@Entity
@Table(name = "CONTACT", catalog = "`sis-aga`")
public class Contact implements java.io.Serializable {

    private Integer idcontact;
    private String nom;
    private String commentaires;

    public Contact() {
    }

    public Contact(String nom) {
        this.nom = nom;
    }

    public Contact(String nom, String commentaires) {
        this.nom = nom;
        this.commentaires = commentaires;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "IDCONTACT", unique = true, nullable = false)
    public Integer getIdcontact() {
        return this.idcontact;
    }

    public void setIdcontact(Integer idcontact) {
        this.idcontact = idcontact;
    }

    @Column(name = "NOM", nullable = false, length = 50)
    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Column(name = "COMMENTAIRES", length = 500)
    public String getCommentaires() {
        return this.commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

}
