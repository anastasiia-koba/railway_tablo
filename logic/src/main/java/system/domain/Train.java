package system.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * Simple JavaBean domain object that represents a Train.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "trains")
public class Train extends BaseEntity {

    @Column(name = "trainname")
    private String trainName;

    @NotNull(message = "This field is required.")
    @Digits(integer=4, fraction = 0, message = "This field must be four-digit number or less.")
    @Column(name = "places_number")
    private Integer placesNumber;
}
