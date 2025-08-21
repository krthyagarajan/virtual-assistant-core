package com.trainings.virtual_assistant.cricket;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cricket_players")
public class PlayerEntity {

    public PlayerEntity(UUID uuid){
        this.id = uuid;
    }

    @Id
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "CHAR(36)")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private Boolean canPayMatchFee;

    @Enumerated(EnumType.STRING)
    private PlayerRole playerRole;

}
