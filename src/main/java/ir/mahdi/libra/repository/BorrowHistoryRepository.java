package ir.mahdi.libra.repository;

import ir.mahdi.libra.model.BorrowHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowHistoryRepository extends JpaRepository<BorrowHistory, Long> {
}
