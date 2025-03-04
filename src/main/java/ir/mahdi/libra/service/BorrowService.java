package ir.mahdi.libra.service;

import ir.mahdi.libra.controller.dto.BorrowAssetDto;

public interface BorrowService {
    void borrowAsset(long assetId, BorrowAssetDto borrowAssetDto);

    void returnAsset(long assetId);
}
