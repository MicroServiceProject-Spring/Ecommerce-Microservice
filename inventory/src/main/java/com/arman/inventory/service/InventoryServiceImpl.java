package com.arman.inventory.service;


import com.arman.inventory.dto.InventoryResponse;
import com.arman.inventory.model.Inventory;
import com.arman.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    @Override
    public List<InventoryResponse> isInStockOrNot(List<String> skuCode) {
       return inventoryRepository.findBySkuCodeIn(skuCode).stream().map(this::mapToInventoryResponse).toList();
    }

    private InventoryResponse mapToInventoryResponse(Inventory inventory){
        return InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0)
                .build();
    }

}
