package demo.sapi.services;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

@Component
public class BirtExpenseService {
    @PersistenceContext(unitName = "modulePersistenceUnit")
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Object[]> getRepartitionDepensesMetier(String aes_key,
            final List<String> listNiveau5, final List<String> listNiveau4,
            final List<String> listNiveau3, Date dateEnd, Date dateStart,
            String niveau0, String niveau1, String niveau2, String rubriquePm) {
        String sql = "select p.libelleemploi, SUM(expen.MONTANT_TTC) as total "
                + "from `sis-aga`.`EXPENSES` expen "
                + "inner join `sis-common`.`StructureBudgetaire` bud0 on expen.CA_PAYEUR = bud0.CodeES "
                + "inner join `sis-common`.`StructureBudgetaire` bud1 on bud0.centreBudgetaire = bud1.CodeES "
                + "inner join `sis-common`.`StructureBudgetaire` bud2 on bud2.CodeES = bud1.niveau2 "
                + "inner join `sis-common`.`StructureBudgetaire` bud3 on bud2.niveau3 = bud3.CodeES "
                + "inner join `sis-common`.`StructureBudgetaire` bud4 on bud4.CodeES = bud3.domaine "
                + "inner join `sis-common`.`StructureBudgetaire` bud5 on bud4.branche = bud5.CodeES "
                + "inner join `sis-aga`.`RUBRIQUE` r on r.rubrique_id = expen.rubrique_id,  "
                + "`sis-common`.`People` p  "
                + "where bud5.branche is not null and  (bud5.dateFinValidite IS NULL OR bud5.dateFinValidite >= CURDATE()) and  bud5.domaine is null "
                + "and  bud4.domaine is not null and  (bud4.dateFinValidite IS NULL OR bud4.dateFinValidite >= CURDATE()) and  bud4.niveau3 is null "
                + "and  bud3.niveau3 is not null and  (bud3.dateFinValidite IS NULL OR bud3.dateFinValidite >= CURDATE()) and  bud3.niveau2 is null "
                + "and  bud2.niveau2 is not null and  (bud2.dateFinValidite IS NULL OR bud2.dateFinValidite >= CURDATE()) and  bud2.centreBudgetaire is null "
                + "and  bud1.centreBudgetaire is not null and  (bud1.dateFinValidite IS NULL OR bud1.dateFinValidite >= CURDATE()) and  bud1.CENTREACTIVITE is null "
                + "and  bud0.CodeES is not null and  (bud0.dateFinValidite IS NULL OR bud0.dateFinValidite >= CURDATE()) "
                + "and bud5.branche in (?1 ) "
                + "and bud4.domaine in (?2 ) "
                + "and bud3.niveau3 in (?3 ) "
                + "and bud2.niveau2 like ?4 "
                + "and bud1.centreBudgetaire like ?5 "
                + "and bud0.centreActivite like ?6 "
                + "and r.rubrique like ?7 "
                + "and expen.date_paiement >= ?8 and expen.date_paiement <= ?9 "
                + "and p.idRhLocal = CONVERT(AES_DECRYPT(expen.matricule, ?10 ) USING utf8) "
                // +
                // "and p.dateDebutValidite = (select max(dateDebutValidite) from `sis-common`.`People` p2 where p2.idRhLocal = p.idRhLocal)"
                + "and ((p.dateDebutValidite <= ?11 and (p.dateFinValidite >= ?12 or p.dateFinValidite is null)) or (p.dateDebutValidite >= ?13 and p.dateDebutValidite <= ?14)) " // lam
                + "and expen.date_paiement >= p.dateDebutValidite and (expen.date_paiement <= p.dateFinValidite or p.dateFinValidite is null)  "// lam
                + "and p.idcnxrtfe = (select idcnxrtfe from `sis-common`.`People` p3 where p3.idRhLocal = p.idRhLocal LIMIT 1)"
                + "GROUP BY p.codeEmploi ORDER BY total DESC";

        return entityManager.createNativeQuery(sql).setParameter(10, aes_key)
                .setParameter(1, listNiveau5).setParameter(2, listNiveau4)
                .setParameter(3, listNiveau3).setParameter(4, niveau2)
                .setParameter(5, niveau1).setParameter(6, niveau0)
                .setParameter(7, rubriquePm)
                .setParameter(8, dateStart, TemporalType.DATE)
                .setParameter(9, dateEnd, TemporalType.DATE)
                .setParameter(11, dateStart, TemporalType.DATE)
                .setParameter(12, dateStart, TemporalType.DATE)
                .setParameter(13, dateStart, TemporalType.DATE)
                .setParameter(14, dateEnd, TemporalType.DATE).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getTop10ConsumParMetier(String aes_key,
            final List<String> listNiveau5, final List<String> listNiveau4,
            final List<String> listNiveau3, Date dateEnd, Date dateStart,
            String niveau0, String niveau1, String niveau2, String rubriquePm) {
        String sql = "select p.libelleemploi, SUM(expen.MONTANT_TTC) as total, bud0.sigle as CA_PAYER, CONCAT(CAST(AES_DECRYPT(expen.NOM, :aes_key) as CHAR(50)), ' ',"
                + " CAST(AES_DECRYPT(expen.PRENOM, :aes_key) as CHAR(50))) as CONSOMMATEUR  "
                + "from `sis-common`.`StructureBudgetaire` bud4 "
                + "inner join `sis-common`.`StructureBudgetaire` bud5 on bud4.branche = bud5.CodeES "
                + "inner join `sis-common`.`StructureBudgetaire` bud3 on bud4.CodeES = bud3.domaine "
                + "inner join `sis-common`.`StructureBudgetaire` bud2 on bud2.niveau3 = bud3.CodeES "
                + "inner join `sis-common`.`StructureBudgetaire` bud1 on bud2.CodeES = bud1.niveau2 "
                + "inner join `sis-common`.`StructureBudgetaire` bud0 on bud0.centreBudgetaire = bud1.CodeES "
                + "inner join `sis-aga`.`EXPENSES` expen on expen.CA_PAYEUR = bud0.CodeES "
                + "inner join `sis-aga`.`RUBRIQUE` r on r.rubrique_id = expen.rubrique_id "
                + "inner join `sis-common`.`People` p on p.idRhLocal = CAST(AES_DECRYPT(expen.matricule, :aes_key) AS CHAR(50)) "
                + "right join "
                + "(select p3.codeEmploi as metier, SUM(expen3.MONTANT_TTC) as total "
                + "from `sis-common`.`StructureBudgetaire` bud44 "
                + "inner join `sis-common`.`StructureBudgetaire` bud55 on bud44.branche = bud55.CodeES "
                + "inner join `sis-common`.`StructureBudgetaire` bud33 on bud44.CodeES = bud33.domaine "
                + "inner join `sis-common`.`StructureBudgetaire` bud22 on bud22.niveau3 = bud33.CodeES "
                + "inner join `sis-common`.`StructureBudgetaire` bud11 on bud22.CodeES = bud11.niveau2 "
                + "inner join `sis-common`.`StructureBudgetaire` bud00 on bud00.centreBudgetaire = bud11.CodeES "
                + "inner join `sis-aga`.`EXPENSES` expen3 on expen3.CA_PAYEUR = bud00.CodeES "
                + "inner join `sis-aga`.`RUBRIQUE` r3 on r3.rubrique_id = expen3.rubrique_id "
                + "inner join `sis-common`.`People` p3 on p3.idRhLocal = CAST(AES_DECRYPT(expen3.matricule, :aes_key) AS CHAR(50)) "
                + "where bud55.branche is not null and  (bud55.dateFinValidite IS NULL OR bud55.dateFinValidite >= CURDATE()) and  bud55.domaine is null "
                + "and  bud44.domaine is not null and  (bud44.dateFinValidite IS NULL OR bud44.dateFinValidite >= CURDATE()) and  bud44.niveau3 is null "
                + "and  bud33.niveau3 is not null and  (bud33.dateFinValidite IS NULL OR bud33.dateFinValidite >= CURDATE()) and  bud33.niveau2 is null "
                + "and  bud22.niveau2 is not null and  (bud22.dateFinValidite IS NULL OR bud22.dateFinValidite >= CURDATE()) and  bud22.centreBudgetaire is null "
                + "and  bud11.centreBudgetaire is not null and  (bud11.dateFinValidite IS NULL OR bud11.dateFinValidite >= CURDATE()) and  bud11.CENTREACTIVITE is null "
                + "and  bud00.CodeES is not null and  (bud00.dateFinValidite IS NULL OR bud00.dateFinValidite >= CURDATE()) "
                + "and bud55.branche in ( :listNiveau5 ) "
                + "and bud44.domaine in ( :listNiveau4 ) "
                + "and bud33.niveau3 in ( :listNiveau3) "
                + "and bud22.niveau2 like :niveau2 "
                + "and bud11.centreBudgetaire like :niveau1 "
                + "and bud00.centreActivite like :niveau0 "
                + "and r3.rubrique like  :rubriquePm "
                + "and expen3.date_paiement >= :dateStart and expen3.date_paiement <= :dateEnd "
                // +
                // "and p3.dateDebutValidite = (select max(dateDebutValidite) from `sis-common`.`People` p33 where p33.idRhLocal = p3.idRhLocal) "
                + "and ((p3.dateDebutValidite <= :dateStart and (p3.dateFinValidite >= :dateStart or p3.dateFinValidite is null)) or (p3.dateDebutValidite >= :dateStart and p3.dateDebutValidite <= :dateEnd)) " // lam
                + "and expen3.date_paiement >= p3.dateDebutValidite and (expen3.date_paiement <= p3.dateFinValidite or p3.dateFinValidite is null) "// lam
                + "and p3.idcnxrtfe = (select idcnxrtfe from `sis-common`.`People` p333 where p3.idRhLocal = p333.idRhLocal LIMIT 1)"
                + "GROUP BY p3.codeEmploi ORDER BY total DESC limit 10)  topMetier "
                + "on p.codeEmploi = topMetier.metier "
                + "where bud5.branche is not null and  (bud5.dateFinValidite IS NULL OR bud5.dateFinValidite >= CURDATE()) and  bud5.domaine is null "
                + "and  bud4.domaine is not null and  (bud4.dateFinValidite IS NULL OR bud4.dateFinValidite >= CURDATE()) and  bud4.niveau3 is null "
                + "and  bud3.niveau3 is not null and  (bud3.dateFinValidite IS NULL OR bud3.dateFinValidite >= CURDATE()) and  bud3.niveau2 is null "
                + "and  bud2.niveau2 is not null and  (bud2.dateFinValidite IS NULL OR bud2.dateFinValidite >= CURDATE()) and  bud2.centreBudgetaire is null "
                + "and  bud1.centreBudgetaire is not null and  (bud1.dateFinValidite IS NULL OR bud1.dateFinValidite >= CURDATE()) and  bud1.CENTREACTIVITE is null "
                + "and  bud0.CodeES is not null and  (bud0.dateFinValidite IS NULL OR bud0.dateFinValidite >= CURDATE()) "
                + "and bud5.branche in ( :listNiveau5  ) "
                + "and bud4.domaine in ( :listNiveau4 ) "
                + "and bud3.niveau3 in ( :listNiveau3 ) "
                + "and bud2.niveau2 like :niveau2 "
                + "and bud1.centreBudgetaire like :niveau1 "
                + "and bud0.centreActivite like :niveau0 "
                + "and r.rubrique  like :rubriquePm "
                + "and expen.date_paiement >= :dateStart and expen.date_paiement <= :dateEnd "
                // +
                // "and p.dateDebutValidite = (select max(dateDebutValidite) from `sis-common`.`People` p4 where p4.idRhLocal = p.idRhLocal)"
                + "and ((p.dateDebutValidite <= :dateStart and (p.dateFinValidite >= :dateStart or p.dateFinValidite is null)) or (p.dateDebutValidite >= :dateStart and p.dateDebutValidite <= :dateEnd)) " // lam
                + "and expen.date_paiement >= p.dateDebutValidite and (expen.date_paiement <= p.dateFinValidite or p.dateFinValidite is null) " // lam
                + "and p.idcnxrtfe = (select idcnxrtfe from `sis-common`.`People` p444 where p444.idRhLocal = p.idRhLocal LIMIT 1) "
                + "GROUP BY p.codeEmploi, CA_PAYER,CONSOMMATEUR ORDER BY p.libelleemploi, total DESC";

        return entityManager.createNativeQuery(sql)
                .setParameter("aes_key", aes_key)
                .setParameter("listNiveau5", listNiveau5)
                .setParameter("listNiveau4", listNiveau4)
                .setParameter("listNiveau3", listNiveau3)
                .setParameter("niveau2", niveau2)
                .setParameter("niveau1", niveau1)
                .setParameter("niveau0", niveau0)
                .setParameter("dateStart", dateStart, TemporalType.DATE)
                .setParameter("dateEnd", dateEnd, TemporalType.DATE)
                .setParameter("rubriquePm", rubriquePm).getResultList();

    }

    @SuppressWarnings("unchecked")
    public List<Object[]> getResultExpenseRepatitionParMotif(String aes_key,
            final List<String> listNiveau5, final List<String> listNiveau4,
            final List<String> listNiveau3, Date dateEnd, Date dateStart,
            String niveau0, String niveau1, String niveau2) {
        String sql = "select  p.libelleEmploi,"
                + " sum(case "
                + "   when expen.motif like 'B%' then expen.MONTANT_TTC"
                + "   else 0"
                + " end) as SumBusiness,"
                + " sum(case "
                + "   when expen.motif like 'F%' then expen.MONTANT_TTC"
                + "   else 0"
                + " end) as SumFormation,"
                + " Sum(case "
                + "   when expen.motif like 'M%' then expen.MONTANT_TTC"
                + "   else 0"
                + " end) as SumMutation"
                + " from `sis-common`.`StructureBudgetaire` bud4"
                + " inner join `sis-common`.`StructureBudgetaire` bud5 on bud4.branche = bud5.CodeES"
                + " inner join `sis-common`.`StructureBudgetaire` bud3 on bud4.CodeES = bud3.domaine"
                + " inner join `sis-common`.`StructureBudgetaire` bud2 on bud2.niveau3 = bud3.CodeES"
                + " inner join `sis-common`.`StructureBudgetaire` bud1 on bud2.CodeES = bud1.niveau2"
                + " inner join `sis-common`.`StructureBudgetaire` bud0 on bud0.centreBudgetaire = bud1.CodeES"
                + " inner join `sis-aga`.`EXPENSES` expen on expen.CA_PAYEUR = bud0.CodeES"
                + " inner join `sis-common`.`People` p on p.idRhLocal = CAST(AES_DECRYPT(expen.matricule, :aes_key) AS CHAR(50))"
                + " where bud5.branche is not null and  (bud5.dateFinValidite IS NULL OR bud5.dateFinValidite >= CURDATE()) and  bud5.domaine is null "
                + " and  bud4.domaine is not null and  (bud4.dateFinValidite IS NULL OR bud4.dateFinValidite >= CURDATE()) and  bud4.niveau3 is null"
                + " and  bud3.niveau3 is not null and  (bud3.dateFinValidite IS NULL OR bud3.dateFinValidite >= CURDATE()) and  bud3.niveau2 is null"
                + " and  bud2.niveau2 is not null and  (bud2.dateFinValidite IS NULL OR bud2.dateFinValidite >= CURDATE()) and  bud2.centreBudgetaire is null"
                + " and  bud1.centreBudgetaire is not null and  (bud1.dateFinValidite IS NULL OR bud1.dateFinValidite >= CURDATE()) and  bud1.CENTREACTIVITE is null"
                + " and  bud0.CodeES is not null and  (bud0.dateFinValidite IS NULL OR bud0.dateFinValidite >= CURDATE()) "
                + " and bud5.branche in (:listNiveau5 ) "
                + " and bud4.domaine in (:listNiveau4)"
                + " and bud3.niveau3 in (:listNiveau3 ) "
                + " and bud2.niveau2 like :niveau2"
                + " and bud1.centreBudgetaire like :niveau1 "
                + " and bud0.centreActivite like :niveau0"
                + " and expen.date_paiement >= :dateStart and expen.date_paiement <= :dateEnd "
                // +
                // " and p.dateDebutValidite = (select max(dateDebutValidite) from `sis-common`.`People` p2 where p2.idRhLocal = p.idRhLocal)"
                + " and ((p.dateDebutValidite <= :dateStart and (p.dateFinValidite >= :dateStart or p.dateFinValidite is null)) or (p.dateDebutValidite >= :dateStart and p.dateDebutValidite <= :dateEnd))" // lam
                + " and expen.date_paiement >= p.dateDebutValidite and (expen.date_paiement <= p.dateFinValidite or p.dateFinValidite is null) " // lam
                + " and p.idcnxrtfe = (select idcnxrtfe from `sis-common`.`People` p3 where p3.idRhLocal = p.idRhLocal LIMIT 1)"
                + " GROUP BY p.codeEmploi";
        return entityManager.createNativeQuery(sql)
                .setParameter("aes_key", aes_key)
                .setParameter("listNiveau5", listNiveau5)
                .setParameter("listNiveau4", listNiveau4)
                .setParameter("listNiveau3", listNiveau3)
                .setParameter("niveau2", niveau2)
                .setParameter("niveau1", niveau1)
                .setParameter("niveau0", niveau0)
                .setParameter("dateStart", dateStart, TemporalType.DATE)
                .setParameter("dateEnd", dateEnd, TemporalType.DATE)
                .getResultList();
    }
}
