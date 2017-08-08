package demo.sapi.services;

import java.util.Date;
import java.util.List;

import fr.socgen.sis.aga.domain.model.Expenses;

/**
 * 
 * @author xiaoyu.xu
 * 
 */
public interface ExpensesService {
    /**
     * 
     * @return
     */
    public List<Expenses> getLatestResult();

    /**
     * 
     * @param reportdate
     * @param userId
     * @param datedemande
     * @param address
     * @return
     */
    public Expenses addExpenses(int numero, String nom, String prenom,
            String matricule);

    public void deleteByYearMonth(Date date);

    public void saveListExpenses(List<Expenses> listexpense);

    public Date getMinDatePaie();

    public Date getMaxDatePaie();

    public void deleteByYearMonthAndIdniveau0(Date date, String niveau0);
}
