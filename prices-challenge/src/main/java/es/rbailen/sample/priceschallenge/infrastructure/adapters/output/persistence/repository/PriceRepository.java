package es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.repository;

import es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long>, JpaSpecificationExecutor<PriceEntity> {

}
