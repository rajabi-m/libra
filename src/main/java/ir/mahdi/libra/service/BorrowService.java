package ir.mahdi.libra.service;

import ir.mahdi.libra.controller.dto.AssetCountDto;
import ir.mahdi.libra.controller.dto.BorrowAssetDto;

import java.util.List;

public interface BorrowService {
    void borrowAsset(long assetId, BorrowAssetDto borrowAssetDto);

    void returnAsset(long assetId);

    List<AssetCountDto> getMostBorrowedAssetsSorted();

    Double getAverageBorrowCountPerUser();
}
