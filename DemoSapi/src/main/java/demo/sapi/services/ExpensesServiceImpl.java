package demo.sapi.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fr.socgen.sis.aga.domain.model.Expenses;
import fr.socgen.sis.aga.domain.repository.ExpensesRepository;

/**
 * 
 * @author xiaoyu.xu
 * 
 */
@Component
public class ExpensesServiceImpl implements ExpensesService {

    @Autowired
    private ExpensesRepository expensesrepo;

    @Override
    @Transactional(readOnly = false)
    public Expenses addExpenses(int numero, String nom, String prenom,
            String matricule) {
        final Expenses p = new Expenses();
        p.setExpenseId(numero);
        p.setNom(nom);
        p.setPrenom(prenom);
        p.setMatricule(matricule);
        expensesrepo.save(p);
        return p;
    }

    @Override
    public List<Expenses> getLatestResult() {
        return expensesrepo.findAll(
                new PageRequest(0, 20, Direction.DESC, "datedemande"))
                .getContent();
    }

    @Override
    public void deleteByYearMonth(Date date) {
        expensesrepo.deleteByMonthAndYear(date);

    }

    @Override
    public void saveListExpenses(List<Expenses> listexpense) {
        expensesrepo.save(listexpense);
    }

    @Override
    public Date getMinDatePaie() {
        return expensesrepo.getMinDatePaie();
    }

    @Override
    public Date getMaxDatePaie() {
        return expensesrepo.getMaxDatePaie();
    }

    @Override
    public void deleteByYearMonthAndIdniveau0(Date date, String idniveau0) {
        expensesrepo.deleteByMonthAndYearAndIdniveau0(date, idniveau0);
    }
}
