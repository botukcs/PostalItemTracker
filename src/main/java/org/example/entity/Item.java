package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemType type;

    @Column(nullable = false)
    private String recipientIndex;

    @Column(nullable = false)
    private String recipientAddress;

    @Column(nullable = false)
    private String recipientName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostalStatus status;

    @OneToMany(mappedBy = "postalItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("eventTime ASC")
    private List<Movement> movements;

    public enum ItemType {
        LETTER, PACKAGE, PARCEL, POSTCARD
    }

    public enum PostalStatus {
        REGISTERED, IN_TRANSIT, DEPARTED, DELIVERED
    }
}