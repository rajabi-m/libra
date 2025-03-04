package ir.mahdi.libra.service;

import ir.mahdi.libra.controller.dto.BorrowAssetDto;
import ir.mahdi.libra.exception.NotAvailableException;
import ir.mahdi.libra.exception.NotFoundException;
import ir.mahdi.libra.model.Asset;
import ir.mahdi.libra.model.BorrowHistory;
import ir.mahdi.libra.model.BorrowableAsset;
import ir.mahdi.libra.model.User;
import ir.mahdi.libra.repository.BorrowHistoryRepository;
import ir.mahdi.libra.repository.BorrowableAssetRepository;
import ir.mahdi.libra.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DefaultBorrowService implements BorrowService {
    private final BorrowableAssetRepository borrowableAssetRepository;
    private final UserRepository userRepository;
    private final BorrowHistoryRepository borrowHistoryRepository;

    public DefaultBorrowService(BorrowableAssetRepository borrowableAssetRepository, UserRepository userRepository, BorrowHistoryRepository borrowHistoryRepository) {
        this.borrowableAssetRepository = borrowableAssetRepository;
        this.userRepository = userRepository;
        this.borrowHistoryRepository = borrowHistoryRepository;
    }

    @Override
    public void borrowAsset(long assetId, BorrowAssetDto borrowAssetDto) {
        Optional<BorrowableAsset> asset = borrowableAssetRepository.findById(assetId);
        if (asset.isEmpty()) {
            throw new NotFoundException("Cannot find asset with id: " + assetId);
        }

        if (asset.get().getStatus() != Asset.Status.AVAILABLE) {
            throw new NotAvailableException("Asset is not available for borrowing");
        }

        Optional<User> user = userRepository.findById(borrowAssetDto.getUserId());
        if (user.isEmpty()) {
            throw new NotFoundException("Cannot find user with id: " + borrowAssetDto.getUserId());
        }

        asset.get().setBorrower(user.get());
        asset.get().setStatus(BorrowableAsset.Status.BORROWED);
        asset.get().setBorrowDate(LocalDate.now());
        asset.get().setReturnDate(borrowAssetDto.getReturnDate());
        borrowableAssetRepository.save(asset.get());
    }

    @Override
    public void returnAsset(long assetId) {
        Optional<BorrowableAsset> asset = borrowableAssetRepository.findById(assetId);
        if (asset.isEmpty()) {
            throw new NotFoundException("Cannot find asset with id: " + assetId);
        }

        if (asset.get().getBorrower() == null) {
            throw new NotFoundException("Asset is not borrowed");
        }

        BorrowHistory history = new BorrowHistory(
                asset.get().getBorrowDate(),
                LocalDate.now(),
                asset.get().getBorrower(),
                asset.get()
        );

        borrowHistoryRepository.save(history);
        asset.get().setBorrower(null);
        asset.get().setStatus(BorrowableAsset.Status.AVAILABLE);
        asset.get().setBorrowDate(null);
        asset.get().setReturnDate(null);
        borrowableAssetRepository.save(asset.get());
    }
}
