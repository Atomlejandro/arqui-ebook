package com.apibooks.apibooks.repository;

import com.apibooks.apibooks.entities.PurchaseOrder;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder,Long> {
}
