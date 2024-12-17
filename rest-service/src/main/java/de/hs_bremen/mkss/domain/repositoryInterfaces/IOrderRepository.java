package de.hs_bremen.mkss.domain.repositoryInterfaces;

import de.hs_bremen.mkss.domain.order.Order;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {

}
