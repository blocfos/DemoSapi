package demo.sapi.services;

import java.io.OutputStream;
import java.util.List;

import fr.socgen.sis.common.domain.service.SisService;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
public interface ExportCsvService extends SisService {
    void exportCsv(List<List<String>> lines, OutputStream outputStream);

    void exportCsvTelMobile(List<Object[]> lines, OutputStream outputStream);

    public void exportCsvExpense(List<Object[]> expenses,
            OutputStream outputStream);
}
