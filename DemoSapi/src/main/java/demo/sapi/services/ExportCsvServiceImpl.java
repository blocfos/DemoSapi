package demo.sapi.services;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import au.com.bytecode.opencsv.CSVWriter;

import com.google.common.base.Throwables;

import fr.socgen.sis.aga.security.AgaUserDetails;
import fr.socgen.sis.common.domain.service.AbstractSisService;
import fr.socgen.sis.common.util.spreadsheet.CsvSpreadsheetWriter;
import fr.socgen.sis.common.util.spreadsheet.SpreadsheetWriter;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
@Service
@Component("ExportCsvService")
public class ExportCsvServiceImpl extends AbstractSisService implements
        ExportCsvService {

    private static final String DATE_TO_STR = "dd/MM/yyyy";
    private static final String currency = "€";

    @Override
    public void exportCsv(List<List<String>> lines, OutputStream outputStream) {
        try (SpreadsheetWriter writer = new CsvSpreadsheetWriter(outputStream,
                CsvSpreadsheetWriter.WINDOWS_1252,
                CsvSpreadsheetWriter.SEMICOLON_SEPARATOR_CHARACTERE,
                CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CsvSpreadsheetWriter.WINDOWS_LINE_END)) {

            for (List<String> line : lines) {

                writer.writeLine(line.toArray());
            }
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public void exportCsvTelMobile(List<Object[]> telMobiles,
            OutputStream outputStream) {
        boolean anonymiser = true;
        if (((AgaUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getAgaUser() != null) {
            anonymiser = ((AgaUserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal()).getAgaUser()
                    .isAnonymiser();
        }
        try (SpreadsheetWriter writer = new CsvSpreadsheetWriter(outputStream,
                CsvSpreadsheetWriter.WINDOWS_1252,
                CsvSpreadsheetWriter.SEMICOLON_SEPARATOR_CHARACTERE,
                CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CsvSpreadsheetWriter.WINDOWS_LINE_END)) {
            writer.writeLine(getTitreCosomation());
            final List<Object> line = new ArrayList<Object>();
            for (Object[] telmobile : telMobiles) {
                setLineCosomation(line, telmobile, anonymiser);
                writer.writeLine(line);
                line.clear();
            }
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public void exportCsvExpense(List<Object[]> expenses,
            OutputStream outputStream) {
        boolean anonymiser = true;
        if (((AgaUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getAgaUser() != null) {
            anonymiser = ((AgaUserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal()).getAgaUser()
                    .isAnonymiser();
        }
        try (SpreadsheetWriter writer = new CsvSpreadsheetWriter(outputStream,
                CsvSpreadsheetWriter.WINDOWS_1252,
                CsvSpreadsheetWriter.SEMICOLON_SEPARATOR_CHARACTERE,
                CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CsvSpreadsheetWriter.WINDOWS_LINE_END)) {
            writer.writeLine(getTitreExpense());
            final List<Object> line = new ArrayList<Object>();
            for (Object[] expense : expenses) {
                setLineExpense(line, expense, anonymiser);
                writer.writeLine(line);
                line.clear();
            }
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    public void setLineCosomation(List<Object> line, Object[] telmobile,
            boolean anonymiser) {
        // CA refact_Guichet
        if (telmobile[0] != null) {
            String ca = telmobile[0].toString().trim();
            if (ca.length() == 10 && ca.startsWith("30003")) {
                ca = ca.substring(5, 10);
            }
            line.add(ca);
        } else {
            line.add("");
        }
        // CA refact_Guichet
        if (telmobile[1] != null) {
            line.add(telmobile[1].toString());
        } else {
            line.add("");
        }
        // CB_SG
        if (telmobile[2] != null) {
            line.add(telmobile[2].toString());
        } else {
            line.add("");
        }
        // Niv2_G
        if (telmobile[3] != null) {
            line.add(telmobile[3].toString());
        } else {
            line.add("");
        }
        // niv3_Délég
        if (telmobile[4] != null) {
            line.add(telmobile[4].toString());
        } else {
            line.add("");
        }
        // Domaine

        if (telmobile[5] != null) {
            line.add(telmobile[5].toString());
        } else {
            line.add("");
        }
        // Branche
        if (telmobile[6] != null) {
            line.add(telmobile[6].toString());
        } else {
            line.add("");
        }

        // SG
        line.add("SIEGE");
        // Utilisateur
        if (anonymiser) {
            line.add("");
        } else {
            if (telmobile[45] != null) {
                line.add(telmobile[45].toString());
            } else {
                line.add("");
            }
        }

        // Etat
        if (telmobile[9] != null) {
            line.add(telmobile[9].toString());
        } else {
            line.add("");
        }
        // Abonnement
        if (telmobile[10] != null) {
            line.add(telmobile[10].toString());
        } else {
            line.add("");
        }
        // Centre de facturation
        if (telmobile[11] != null) {
            line.add(telmobile[11].toString());
        } else {
            line.add("");
        }
        // Opérateur
        if (telmobile[12] != null) {
            line.add(telmobile[12].toString());
        } else {
            line.add("");
        }
        // Typologie
        if (telmobile[15] != null) {
            line.add(telmobile[15].toString());
        } else {
            line.add("");
        }
        // Typo Effco
        if (telmobile[43] != null) {
            line.add(telmobile[43].toString());
        } else {
            line.add("");
        }
        // Modèle
        if (telmobile[13] != null) {
            line.add(telmobile[13].toString());
        } else {
            line.add("");
        }
        // N° série
        if (telmobile[14] != null) {
            line.add(telmobile[14].toString());
        } else {
            line.add("");
        }
        // Typologie
        if (telmobile[44] != null) {
            line.add(telmobile[44].toString());
        } else {
            line.add("");
        }
        // Imei acheté (SFR)
        if (telmobile[16] != null) {
            line.add(telmobile[16].toString());
        } else {
            line.add("");
        }
        // Terminal acheté (SFR)
        if (telmobile[17] != null) {
            line.add(telmobile[17].toString());
        } else {
            line.add("");
        }
        // N° appel
        if (anonymiser) {
            line.add("");
        } else {
            if (telmobile[46] != null) {
                line.add(telmobile[46].toString());
            } else {
                line.add("");
            }
        }

        // Total
        if (telmobile[19] != null) {

            line.add(formatDecimalEuro(telmobile[19]));
        } else {
            line.add("");
        }
        // Abonnement
        if (telmobile[20] != null) {
            line.add(formatDecimalEuro(telmobile[20]));
        } else {
            line.add("");
        }
        // Consommations
        if (telmobile[21] != null) {
            line.add(formatDecimalEuro(telmobile[21]));
        } else {
            line.add("");
        }
        // Services + Remises
        if (telmobile[22] != null) {
            line.add(formatDecimalEuro(telmobile[22]));
        } else {
            line.add("");
        }
        // National
        if (telmobile[23] != null) {
            line.add(formatDecimalEuro(telmobile[23]));
        } else {
            line.add("");
        }
        // N° spéciaux
        if (telmobile[24] != null) {
            line.add(formatDecimalEuro(telmobile[24]));
        } else {
            line.add("");
        }
        // Vers international
        if (telmobile[25] != null) {
            line.add(formatDecimalEuro(telmobile[25]));
        } else {
            line.add("");
        }
        // Roaming sortant
        if (telmobile[26] != null) {
            line.add(formatDecimalEuro(telmobile[26]));
        } else {
            line.add("");
        }
        // Roaming entrant
        if (telmobile[27] != null) {
            line.add(formatDecimalEuro(telmobile[27]));
        } else {
            line.add("");
        }
        // SMS - National
        if (telmobile[39] != null) {
            line.add(formatDecimalEuro(telmobile[39]));
        } else {
            line.add("");
        }
        // SMS - International
        if (telmobile[40] != null) {
            line.add(formatDecimalEuro(telmobile[40]));
        } else {
            line.add("");
        }
        // Data
        if (telmobile[29] != null) {
            line.add(formatDecimalEuro(telmobile[29]));
        } else {
            line.add("");
        }
        // National
        if (telmobile[30] != null) {
            line.add(formatTime(telmobile[30]));
        } else {
            line.add("");
        }
        // Vers international
        if (telmobile[31] != null) {
            line.add(formatTime(telmobile[31]));
        } else {
            line.add("");
        }
        // Roaming sortant
        if (telmobile[32] != null) {
            line.add(formatTime(telmobile[32]));
        } else {
            line.add("");
        }
        // Roaming entrant
        if (telmobile[33] != null) {
            line.add(formatTime(telmobile[33]));
        } else {
            line.add("");
        }
        // National (Ko)
        if (telmobile[34] != null) {
            line.add(telmobile[34].toString());
        } else {
            line.add("");
        }
        // International (Ko)
        if (telmobile[35] != null) {
            line.add(telmobile[35].toString());
        } else {
            line.add("");
        }
        // Nb SMS - National
        if (telmobile[41] != null) {
            line.add(telmobile[41].toString());
        } else {
            line.add("");
        }
        // Nb SMS - International
        if (telmobile[42] != null) {
            line.add(telmobile[42].toString());
        } else {
            line.add("");
        }
    }

    public List<Object> getTitreCosomation() {
        final List<Object> line = new ArrayList<>();
        line.add("CA refact_Guichet");
        line.add("CA refact_Guichet");
        line.add("CB_SG");
        line.add("Niv2_G");
        line.add("niv3_Délég");
        line.add("Domaine");
        line.add("Branche");
        line.add("SG");
        line.add("Utilisateur");
        line.add("Etat");
        line.add("Abonnement");
        line.add("Centre de facturation");
        line.add("Opérateur");
        line.add("Typologie");
        line.add("Typo Effco");
        line.add("Modèle");
        line.add("N° série");
        line.add("Typologie");
        line.add("Imei acheté (SFR)");
        line.add("Terminal acheté (SFR)");
        line.add("N° appel");
        line.add("Total");
        line.add("Abonnement");
        line.add("Consommations");
        line.add("Services + Remises");
        line.add("National");
        line.add("N° spéciaux");
        line.add("Vers international");
        line.add("Roaming sortant");
        line.add("Roaming entrant");
        line.add("SMS - National");
        line.add("SMS - International");
        line.add("Data");
        line.add("National");
        line.add("Vers international");
        line.add("Roaming sortant");
        line.add("Roaming entrant");
        line.add("National (Ko)");
        line.add("International (Ko)");
        line.add("Nb SMS - National");
        line.add("Nb SMS - International");
        return line;
    }

    public List<Object> getTitreExpense() {
        final List<Object> line = new ArrayList<>();
        line.add("Nom");
        line.add("Prénom");
        line.add("Matricule");
        line.add("CA employé");
        line.add("Desc.");
        line.add("NDF saisie par");
        line.add("N° NDF");
        line.add("Motif");
        line.add("Desc. NdF");
        line.add("Comment.");
        line.add("Statut");
        line.add("N° ligne");
        line.add("CA payeur");
        line.add("Descr CA payeur");
        line.add("Guichet");
        line.add("Dépense perso");
        line.add("Rubrique");
        line.add("Descr rubrique");
        line.add("Compte");
        line.add("Hors politique");
        line.add("Presta référencé");
        line.add("Nbr de nuités");
        line.add("Rejet");
        line.add("Localisation");
        line.add("Date transaction");
        line.add("Date paiement");
        line.add("Mode de paiement");
        line.add("Montant TTC");
        line.add("Montant HT");
        line.add("Montant TVA");
        line.add("Montant HTR");
        line.add("Date de soumission");
        line.add("Date imputation comptable");
        line.add("Directions");
        line.add("Départements");
        line.add("Pôles");
        line.add("Mois");
        line.add("Rubrique2");
        line.add("Libellé emploi");
        line.add("Statut Agent");
        line.add("Nombre Collaborateurs");
        line.add("BDDF");
        return line;
    }

    public void setLineExpense(List<Object> line, Object[] expense,
            boolean anonymiser) {

        // Nom
        if (anonymiser) {
            line.add("");
        } else {
            line.add(expense[42] != null ? expense[42].toString().trim() : "");
        }
        // Prénom
        if (anonymiser) {
            line.add("");
        } else {
            line.add(expense[43] != null ? expense[43].toString().trim() : "");
        }

        // Matricule
        if (anonymiser) {
            line.add("");
        } else {
            line.add(expense[44] != null ? expense[44].toString().trim() : "");
        }

        // CA employé
        if (expense[4] != null) {
            String caem = expense[4].toString().trim();
            if (caem.length() == 10 && caem.startsWith("30003")) {
                caem = caem.substring(5, 10);
            }
            line.add(caem);
        } else {
            line.add("");
        }
        // Desc. sigle
        line.add(expense[40] != null ? expense[40] : "");
        // NDF saisie par
        if (anonymiser) {
            line.add("");
        } else {
            line.add(expense[45] != null ? expense[45].toString().trim() : "");
        }
        // N° NDF
        line.add(expense[6] != null ? expense[6] : "");
        // Motif
        line.add(expense[33] != null ? expense[33] : "");
        // Desc. NdF
        line.add(expense[7] != null ? expense[7] : "");
        // Comment
        line.add(expense[8] != null ? expense[8] : "");
        // Statut
        line.add(expense[9] != null ? expense[9] : "");
        // N° ligne
        line.add(expense[10] != null ? expense[10] : "");
        // CA payeur
        if (expense[11] != null) {
            String capayeur = expense[11].toString().trim();
            if (capayeur.length() == 10 && capayeur.startsWith("30003")) {
                capayeur = capayeur.substring(5, 10);
            }
            line.add(capayeur);
        } else {
            line.add("");
        }
        // Descr CA payeur sigle
        line.add(expense[41] != null ? expense[41] : "");
        // Guichet
        line.add(expense[12] != null ? expense[12] : "");
        // Dépense perso
        line.add(expense[13] != null ? expense[13] : "");
        // Rubrique
        line.add(expense[14] != null ? expense[14] : "");
        // Descr rubrique sigle
        line.add(expense[35] != null ? expense[35] : "");
        // Compte
        line.add(expense[16] != null ? expense[16] : "");
        // Hors politique
        line.add(expense[17] != null ? expense[17] : "");
        // Presta référencé
        line.add(expense[18] != null ? expense[18] : "");
        // Nbr de nuités
        line.add(expense[19] != null ? expense[19] : "");
        // Rejet
        line.add(expense[20] != null ? expense[20] : "");
        // Localisation
        line.add(expense[21] != null ? expense[21] : "");
        // Date transaction
        line.add(expense[22] != null ? formatDate(expense[22]) : "");
        // Date paiement
        line.add(expense[23] != null ? formatDate(expense[23]) : "");
        // Mode de paiement
        line.add(expense[24] != null ? expense[24] : "");
        // Montant TTC
        line.add(expense[25] != null ? decimalToStr(expense[25]) : "");
        // Montant HT
        line.add(expense[26] != null ? decimalToStr(expense[26]) : "");
        // Montant TVA
        line.add(expense[27] != null ? decimalToStr(expense[27]) : "");
        // Montant HTR
        line.add(expense[28] != null ? decimalToStr(expense[28]) : "");
        // Date de soumission
        line.add(expense[29] != null ? formatDate(expense[29]) : "");
        // Date imputation comptable
        line.add(expense[30] != null ? formatDate(expense[30]) : "");
        // Directions
        line.add(expense[37] != null ? expense[37] : "");
        // Départements
        line.add(expense[38] != null ? expense[38] : "");
        // Pôles
        line.add(expense[39] != null ? expense[39] : "");
        // Mois datepayment
        line.add("");
        // Rubrique
        line.add(expense[34] != null ? expense[34] : "");
        // Libellé emploi
        line.add(expense[31] != null ? expense[31] : "");
        // Statut Agent
        line.add(expense[32] != null ? expense[32] : "");
        // Nombre Collaborateurs
        line.add("");
        // BDDF Branche
        line.add(expense[36] != null ? expense[36] : "");
    }

    private String decimalToStr(Object param) {
        BigDecimal bigDecimal = (BigDecimal) param;
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        final DecimalFormat decFormat = new DecimalFormat();
        decFormat.applyPattern("#0.00");
        decFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(
                Locale.FRANCE));
        return decFormat.format(bigDecimal) + " ";
    }

    private String formatDecimalEuro(Object param) {
        BigDecimal bigDecimal = (BigDecimal) param;
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return decimalToStr(param).trim() + currency;
    }

    private String formatDate(Object param) {
        final SimpleDateFormat format = new SimpleDateFormat(DATE_TO_STR);
        final Date date = (Date) param;
        return format.format(date);

    }

    private String formatTime(Object param) {
        final long time = ((BigInteger) param).longValue();
        final DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(time);

    }

}
