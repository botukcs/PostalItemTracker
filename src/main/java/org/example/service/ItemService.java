package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.example.dto.ItemCreateDto;
import org.example.dto.ItemDto;
import org.example.dto.MovementCreateDto;
import org.example.dto.MovementDto;
import org.example.entity.Item;
import org.example.entity.Movement;
import org.example.entity.PostOffice;
import org.example.event.KafkaEventPublisher;
import org.example.repository.ItemRepository;
import org.example.repository.MovementRepository;
import org.example.repository.PostOfficeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
    private final ItemRepository postalItemRepository;
    private final PostOfficeRepository postOfficeRepository;
    private final MovementRepository movementRepository;
    private final KafkaEventPublisher eventPublisher;

    public ItemDto registerPostalItem(ItemCreateDto dto) {
        Item item = Item.builder()
                .type(dto.type())
                .recipientIndex(dto.recipientIndex())
                .recipientAddress(dto.recipientAddress())
                .recipientName(dto.recipientName())
                .status(Item.PostalStatus.REGISTERED)
                .build();

        Item savedItem = postalItemRepository.save(item);

        eventPublisher.publishPostalEvent(savedItem, "REGISTERED");

        return ItemDto.fromEntity(savedItem);
    }

    public MovementDto recordMovement(MovementCreateDto dto) {
        Item item = postalItemRepository.findById(dto.postalItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Postal item not found"));

        PostOffice office = postOfficeRepository.findById(dto.postOfficeId())
                .orElseThrow(() -> new ResourceNotFoundException("Post office not found"));

        switch (dto.type()) {
            case ARRIVAL -> item.setStatus(Item.PostalStatus.IN_TRANSIT);
            case DEPARTURE -> item.setStatus(Item.PostalStatus.DEPARTED);
            case DELIVERY -> item.setStatus(Item.PostalStatus.DELIVERED);
        }

        Movement movement = Movement.builder()
                .postalItem(item)
                .postOffice(office)
                .type(dto.type())
                .eventTime(LocalDateTime.now())
                .build();

        Movement savedMovement = movementRepository.save(movement);

        eventPublisher.publishMovementEvent(savedMovement);

        return MovementDto.fromEntity(savedMovement);
    }

    public ItemDto getPostalItemWithHistory(Long id) {
        Item item = postalItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Postal item not found"));
        return ItemDto.fromEntity(item);
    }
}