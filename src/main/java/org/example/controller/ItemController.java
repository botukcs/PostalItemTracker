package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.ItemCreateDto;
import org.example.dto.ItemDto;
import org.example.dto.MovementCreateDto;
import org.example.dto.MovementDto;
import org.example.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/postal-items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService postalItemService;

    @PostMapping
    public ResponseEntity<ItemDto> registerPostalItem(
            @Valid @RequestBody ItemCreateDto dto) {
        ItemDto created = postalItemService.registerPostalItem(dto);
        return ResponseEntity.created(URI.create("/api/postal-items/" + created.id())).body(created);
    }

    @PostMapping("/movements")
    public ResponseEntity<MovementDto> recordMovement(
            @Valid @RequestBody MovementCreateDto dto) {
        MovementDto movement = postalItemService.recordMovement(dto);
        return ResponseEntity.ok(movement);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getPostalItemWithHistory(@PathVariable Long id) {
        return ResponseEntity.ok(postalItemService.getPostalItemWithHistory(id));
    }
}