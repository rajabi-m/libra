package ir.mahdi.libra.repository;

import ir.mahdi.libra.model.BorrowableAsset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowableAssetRepository extends JpaRepository<BorrowableAsset, Long> {
}
