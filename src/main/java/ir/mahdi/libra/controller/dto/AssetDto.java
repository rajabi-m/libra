package ir.mahdi.libra.controller.dto;

import ir.mahdi.libra.model.Asset;
import ir.mahdi.libra.model.Book;

public interface AssetDto {

    static AssetDto of(Asset asset) {
        switch (asset) {
            case Book book:
                return BookDto.of(book);
            default:
                throw new IllegalArgumentException("Unknown asset type: " + asset.getClass().getName());
        }
    }
}
