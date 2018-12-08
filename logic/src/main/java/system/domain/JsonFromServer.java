package system.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class JsonFromServer {
    private Long stationId;
    private Long finalRoutId;
    private Date date;
    private int status;
}
