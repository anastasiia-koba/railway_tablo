package system.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Common 'id' part of all entities.
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "deleted")
    private Boolean deleted = false;

}
