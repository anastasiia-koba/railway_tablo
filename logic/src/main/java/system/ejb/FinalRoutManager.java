package system.ejb;

import system.domain.FinalRout;
import system.domain.Rout;
import system.domain.RoutSection;
import system.domain.Station;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalTime;
import java.util.*;

@Stateless
@LocalBean
public class FinalRoutManager {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public List<FinalRout> findByStationAndDate(Station station, Date date) {
        Query q = em.createQuery("SELECT DISTINCT fr FROM FinalRout fr " +
                "JOIN fr.rout.routSections rs WHERE " +
                "(rs.destination = :station OR rs.departure = :station) AND " +
                "fr.date = :date AND fr.deleted = false order by fr.rout.id");
        q.setParameter("station", station);
        q.setParameter("date", date);

        return q.getResultList();
    }

    public Rout getRoutByFinalRoutId(String str) {
        Long id = Long.valueOf(str);
        Query q = em.createQuery("SELECT fr FROM FinalRout fr WHERE fr.id = :id AND fr.deleted=false");
        q.setParameter("id", id);

        List results = q.getResultList();

        if (results.isEmpty()) {
            return null; // handle no-results case
        } else {
            return ((FinalRout) results.get(0)).getRout();
        }
    }

    private RoutSection getRoutSectionByRoutAndDepartureStation(Rout rout, Station departureStation) {
        Query q = em.createQuery("SELECT rs FROM RoutSection rs " +
                "inner join fetch rs.routs r WHERE r = :rout AND rs.departure = :departure AND rs.deleted = false ");
        q.setParameter("rout", rout);
        q.setParameter("departure", departureStation);

        List results = q.getResultList();

        if (results.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (RoutSection) results.get(0);
        }
    }

    private RoutSection getRoutSectionByRoutAndDestinationStation(Rout rout, Station destinationStation) {
        Query q = em.createQuery("SELECT rs FROM RoutSection rs " +
                "inner join fetch rs.routs r WHERE r = :rout AND rs.destination = :destination AND rs.deleted = false");
        q.setParameter("rout", rout);
        q.setParameter("destination", destinationStation);

        List results = q.getResultList();

        if (results.isEmpty()) {
            return null; // handle no-results case
        } else {
            return (RoutSection) results.get(0);
        }
    }

    public LocalTime getTimeDepartureByStation(Rout rout, Station station) {
        LocalTime timeDeparture = getRoutSectionByRoutAndDepartureStation(rout, station) != null ?
                getRoutSectionByRoutAndDepartureStation(rout, station).getDepartureTime() : null;

        return timeDeparture;
    }

    public LocalTime getTimeArrivalByStation(Rout rout, Station station) {
        LocalTime timeArrival = getRoutSectionByRoutAndDestinationStation(rout, station) != null ?
                getRoutSectionByRoutAndDestinationStation(rout, station).getArrivalTime(): null ;
        return timeArrival;
    }
}
