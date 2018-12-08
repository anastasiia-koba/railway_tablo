package system.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stations")
public class Station extends BaseEntity {

    @Column(name = "stationname")
    private String name;

    public Station(String name) {
        this.name = name;
    }
}
