package system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Simple JavaBean domain object that represents a date for Rout and Train.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "trains_routs")
@SQLDelete(sql="UPDATE trains_routs SET deleted = true WHERE id = ?")
@Where(clause="deleted = false")
public class FinalRout extends BaseEntity {

    @NotNull(message = "This field is required.")
    @ManyToOne
    @JoinColumn(name = "train_id", referencedColumnName = "id", nullable = false)
    private Train train;

    @NotNull(message = "This field is required.")
    @ManyToOne
    @JoinColumn(name = "rout_id", referencedColumnName = "id", nullable = false)
    private Rout rout;

    @NotNull(message = "This field is required.")
    @Column(name = "date")
    private LocalDate date;
}
