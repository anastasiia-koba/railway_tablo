package system.ejb;

import system.domain.JsonFromServer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryStorage {
    private static final List<JsonFromServer> storage = new ArrayList<>();

    private InMemoryStorage() { }

    public static void add(JsonFromServer value) {
        for(JsonFromServer json: storage) {
            if (json.getStationId().equals(value.getStationId()) &&
                    json.getFinalRoutId().equals(value.getFinalRoutId()) &&
                    json.getDate().equals(value.getDate())) {
                storage.remove(json);
                break;
            }
        }
        storage.add(value);
    }

    public static int getStatus(Long station, Long rout, Date date) {
        for(JsonFromServer json: storage) {
            if (json.getStationId() == station && json.getFinalRoutId() == rout && json.getDate().equals(date)) {
                return json.getStatus();
            }
        }
        return 0;
    }
}
