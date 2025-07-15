package org.example.dto;

import org.example.entity.Item;

public record ItemDto(
        Long id,
        Item.ItemType type,
        String recipientIndex,
        String recipientAddress,
        String recipientName,
        Item.PostalStatus status
) {
    public static ItemDto fromEntity(Item item) {
        return new ItemDto(
                item.getId(),
                item.getType(),
                item.getRecipientIndex(),
                item.getRecipientAddress(),
                item.getRecipientName(),
                item.getStatus()
        );
    }
}