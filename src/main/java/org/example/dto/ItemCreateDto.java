package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.entity.Item;

public record ItemCreateDto(
        @NotNull Item.ItemType type,
        @NotBlank String recipientIndex,
        @NotBlank String recipientAddress,
        @NotBlank String recipientName
) {}