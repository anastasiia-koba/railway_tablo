package system;

import system.domain.Station;
import system.ejb.StationManager;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named
@SessionScoped
public class FormBean implements Serializable {

    @EJB
    private StationManager stationManager;

    private LocalDate currentDate;
    private Station station;

    public List<Station> getStations() {
        return stationManager.findAll();
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }
}
