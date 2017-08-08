package demo.sapi.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fr.socgen.sis.aga.domain.model.TelMobile;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
public interface TelMobileRepository extends JpaRepository<TelMobile, Integer> {

    @Override
    Page<TelMobile> findAll(Pageable pageable);

    @Query("select distinct t.operateur from TelMobile t order by t.operateur asc")
    List<String> getOperateur();

    @Query("select distinct t.etatLignetel  from TelMobile t order by t.etatLignetel asc")
    List<String> getEtatLignetel();

    @Query("select distinct t.typoEffco from TelMobile t order by t.typoEffco asc")
    List<String> getTypeEffco();

    @Query("select max(dateFac) from TelMobile")
    Date getMaxDateFac();

    @Query("select min(dateFac) from TelMobile")
    Date getMinDateFac();

    @Modifying
    @Transactional(readOnly = false)
    @Query("delete  from TelMobile t where month(t.dateFac) = month(:dateParam) and year(t.dateFac) = year(:dateParam)")
    public void deleteByMonthAndYear(@Param("dateParam") Date dateParam);

    @Modifying
    @Transactional(readOnly = false)
    @Query("delete  from TelMobile t where month(t.dateFac) = month(:dateParam) and year(t.dateFac) = year(:dateParam) and t.idniveau0 = :idniveau0")
    public void deleteByMonthAndYearAndIdniveau0(
            @Param("dateParam") Date dateParam,
            @Param("idniveau0") String idniveau0);
}
