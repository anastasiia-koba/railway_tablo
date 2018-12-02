package system.ejb;

import system.domain.Station;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@LocalBean
public class StationManager {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public List<Station> findAll() {
        TypedQuery<Station> typedQuery = em.createQuery("select s from Station s where s.deleted=false",Station.class);
        return typedQuery.getResultList();
    }
}
