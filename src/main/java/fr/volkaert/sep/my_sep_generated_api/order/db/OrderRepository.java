package fr.volkaert.sep.my_sep_generated_api.order.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    @Query(value = "SELECT o FROM OrderEntity o WHERE " +
            "(o.createdAt >= COALESCE(:fromCreatedAt, o.createdAt)) AND " +
            "(o.createdAt <= COALESCE(:toCreatedAt, o.createdAt)) AND " +
            "(o.updatedAt >= COALESCE(:fromUpdatedAt, o.updatedAt)) AND " +
            "(o.updatedAt <= COALESCE(:toUpdatedAt, o.updatedAt))")
    Page<OrderEntity> findBy(OffsetDateTime fromCreatedAt, OffsetDateTime toCreatedAt, OffsetDateTime fromUpdatedAt, OffsetDateTime toUpdatedAt, Pageable pageable);
}
