package com.trainings.virtual_assistant.cricket;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cricket_matches")
public class MatchEntity {


    public MatchEntity(UUID uuid){
        this.id = uuid;
    }

    @Id
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "CHAR(36)")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String date;

    private Byte overs;

    private String location;

    private BigDecimal fees;

    private String additionalInfo;

    private String opponent;
}
