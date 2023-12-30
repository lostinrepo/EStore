package com.inventoryservice.controller;

import com.inventoryservice.dto.InventoryResponse;
import com.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    @Autowired
    private final InventoryService inventoryService;
    //@GetMapping("/{sku-code}") -> instead of single skuCode pass the list to check multiple units at once
    //(Why to send request just for one unit at a time ....will make the process slow )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam("skuCode") List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }
}
