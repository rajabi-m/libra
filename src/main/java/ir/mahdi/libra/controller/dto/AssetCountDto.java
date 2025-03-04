package ir.mahdi.libra.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssetCountDto {
    private AssetDto asset;
    private Long count;
}
