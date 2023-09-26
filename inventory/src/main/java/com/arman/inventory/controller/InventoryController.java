package com.arman.inventory.controller;

import com.arman.inventory.dto.InventoryResponse;
import com.arman.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStockOrNot(@RequestParam("skuCode") List<String> skuCode){
        return inventoryService.isInStockOrNot(skuCode);
    }
}
