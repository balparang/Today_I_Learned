package com.example.concurrency.domain.pessimistic;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Stock  s where s.id = :id")
    Optional<Stock> findByIdWithPessimisticWriteLock(@Param(value = "id") Long id);

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("select s from Stock  s where s.id = :id")
    Optional<Stock> findByIdWithPessimisticReadLock(@Param(value = "id") Long id);
}
