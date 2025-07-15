package org.example.dto;

import org.example.entity.Movement;

import java.time.LocalDateTime;

public record MovementDto(
        Long id,
        Long postalItemId,
        Long postOfficeId,
        String postOfficeName,
        Movement.MovementType type,
        LocalDateTime eventTime
) {
    public static MovementDto fromEntity(Movement movement) {
        return new MovementDto(
                movement.getId(),
                movement.getPostalItem().getId(),
                movement.getPostOffice().getId(),
                movement.getPostOffice().getName(),
                movement.getType(),
                movement.getEventTime()
        );
    }
}