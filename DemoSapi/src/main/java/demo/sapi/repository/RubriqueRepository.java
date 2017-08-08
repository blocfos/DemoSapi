package demo.sapi.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.socgen.sis.aga.domain.model.Rubrique;

/**
 * 
 * @author Lam NGUYEN
 * 
 */
public interface RubriqueRepository extends JpaRepository<Rubrique, Integer> {

    @Query("select distinct r.rubrique from Rubrique r order by r.rubrique")
    public List<String> getDistinctRubrique();

    public List<Rubrique> findByDescr(String descr);

    public List<Rubrique> findByRubrique(String rubrique);

    public List<Rubrique> findByRubriqueAndDescr(String rubrique, String descr);
}
