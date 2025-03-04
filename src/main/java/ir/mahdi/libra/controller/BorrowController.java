package ir.mahdi.libra.controller;

import ir.mahdi.libra.controller.dto.BorrowAssetDto;
import ir.mahdi.libra.service.BorrowService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow")
public class BorrowController {
    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping("/{assetId}")
    private void borrowAsset(@PathVariable Long assetId, @Valid @RequestBody BorrowAssetDto borrowAssetDto) {
        borrowService.borrowAsset(assetId, borrowAssetDto);
    }

    @PostMapping("/return/{assetId}")
    private void returnAsset(@PathVariable Long assetId) {
        borrowService.returnAsset(assetId);
    }
}
