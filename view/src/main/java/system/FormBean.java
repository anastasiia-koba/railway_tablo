package system;

import system.domain.FinalRout;
import system.domain.Rout;
import system.domain.Station;
import system.ejb.FinalRoutManager;
import system.ejb.InMemoryStorage;
import system.ejb.StationManager;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Named
@SessionScoped
@ManagedBean
public class FormBean implements Serializable {
    private static final int STATUS_ON_TIME = 1;
    private static final int STATUS_DELAYED = 2;
    private static final int STATUS_CANCELED = 3;

    @EJB
    private StationManager stationManager;

    @EJB
    private FinalRoutManager finalRoutManager;

    private Date currentDate = new Date();
    private String station;

    public List<Station> getStations() {
        return stationManager.findAll();
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Set<FinalRout> getFinalRouts() {
        Station st = stationManager.findByName(station);
        return finalRoutManager.findByStationAndDate(st, currentDate);
    }

    public LocalTime getDepartureTime(String finalRoutId) {
        Station st = stationManager.findByName(station);
        Rout rt = finalRoutManager.getRoutByFinalRoutId(finalRoutId);
        return finalRoutManager.getTimeDepartureByStation(rt, st);
    }

    public LocalTime getArrivalTime(String finalRoutId) {
        Station st = stationManager.findByName(station);
        Rout rt = finalRoutManager.getRoutByFinalRoutId(finalRoutId);
        return finalRoutManager.getTimeArrivalByStation(rt, st);
    }

    public String getStatus(String finalRoutId) {
        Station st = stationManager.findByName(station);
        Rout rt = finalRoutManager.getRoutByFinalRoutId(finalRoutId);

        int status = InMemoryStorage.getStatus(st.getId(), rt.getId(), currentDate);
        switch (status) {
            case (STATUS_ON_TIME):
                return "on time";
            case (STATUS_DELAYED):
                return "delayed";
            case (STATUS_CANCELED):
                return "canceled";
            default:
                return "on time";
        }
    }
}
