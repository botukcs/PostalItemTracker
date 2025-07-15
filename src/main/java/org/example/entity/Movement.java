package org.example.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "movements")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postal_item_id", nullable = false)
    private Item postalItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_office_id", nullable = false)
    private PostOffice postOffice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType type;

    @Column(nullable = false)
    private java.time.LocalDateTime eventTime;

    public enum MovementType {
        ARRIVAL, DEPARTURE, DELIVERY
    }
}