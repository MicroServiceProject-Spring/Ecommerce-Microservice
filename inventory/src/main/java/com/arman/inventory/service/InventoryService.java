package com.arman.inventory.service;


import com.arman.inventory.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> isInStockOrNot(List<String> skuCode);
}
