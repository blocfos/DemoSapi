package demo.sapi.repository;


import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fr.socgen.sis.aga.domain.model.Expenses;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
public interface ExpensesRepository extends JpaRepository<Expenses, Integer> {

    @Override
    Page<Expenses> findAll(Pageable pageable);

    @Modifying
    @Transactional(readOnly = false)
    @Query("delete  from Expenses e where month(e.datePaiement) = month(:dateParam) and year(e.datePaiement) = year(:dateParam)")
    public void deleteByMonthAndYear(@Param("dateParam") Date dateParam);

    @Query("select min(e.datePaiement) from Expenses e")
    public Date getMinDatePaie();

    @Query("select max(e.datePaiement) from Expenses e")
    public Date getMaxDatePaie();

    @Modifying
    @Transactional(readOnly = false)
    @Query("delete  from Expenses e where month(e.datePaiement) = month(:dateParam) and year(e.datePaiement) = year(:dateParam) and idniveau0 = :idniveau0")
    void deleteByMonthAndYearAndIdniveau0(@Param("dateParam") Date date,
            @Param("idniveau0") String idniveau0);
}
