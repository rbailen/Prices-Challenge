package es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public class BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "CREATION_DATE", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime creationDate;

}
