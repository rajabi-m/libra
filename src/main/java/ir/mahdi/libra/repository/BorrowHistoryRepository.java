package ir.mahdi.libra.repository;

import ir.mahdi.libra.model.BorrowHistory;
import ir.mahdi.libra.model.BorrowableAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Pair;

import java.util.List;

public interface BorrowHistoryRepository extends JpaRepository<BorrowHistory, Long> {
    @Query("""
            SELECT NEW org.springframework.data.util.Pair(b.asset, COUNT(b))
            FROM BorrowHistory b
            GROUP BY b.asset
            ORDER BY COUNT(b) DESC
            """)
    List<Pair<BorrowableAsset, Long>> findMostBorrowedAssetsSorted();


    @Query("""
            SELECT AVG(bc)
            FROM (
                SELECT COUNT(b) AS bc
                FROM BorrowHistory b
                RIGHT JOIN User u
                ON u.id = b.user.id
                GROUP BY u.id
            ) AS borrowCounts
            """)
    Double findAverageBorrowCountPerUser();
}
