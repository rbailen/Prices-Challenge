package es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.spec;

import es.rbailen.sample.priceschallenge.infrastructure.adapters.output.persistence.entity.BaseEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.Objects;

public abstract class PriceSpec<E extends BaseEntity> {

    protected Specification<E> equals(String field, Object value) {
        return (root, cq, cb) -> check(value, cb.equal(root.get(field), value));
    }

    protected <V extends Comparable<? super V>> Specification<E> greaterThanOrEqualTo(String field, V value) {
        return (root, cq, cb) -> check(value, cb.greaterThanOrEqualTo(root.get(field), value));
    }

    protected <V extends Comparable<? super V>> Specification<E> lessThanOrEqualTo(String field, V value) {
        return (root, cq, cb) -> check(value, cb.lessThanOrEqualTo(root.get(field), value));
    }

    private static Predicate check(Object value, Predicate predicate) {
        return Objects.isNull(value) ? null : predicate;
    }

}
