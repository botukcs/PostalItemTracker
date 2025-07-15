package org.example.dto;

import jakarta.validation.constraints.NotNull;
import org.example.entity.Movement;

public record MovementCreateDto(
        @NotNull Long postalItemId,
        @NotNull Long postOfficeId,
        @NotNull Movement.MovementType type
) {}