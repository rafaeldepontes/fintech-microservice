package br.rafael.card.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.rafael.card.domain.enums.CardBrand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Card {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    
    @Enumerated(EnumType.STRING)
    private CardBrand brand;

    @Column
    private BigDecimal income;

    @Column
    private BigDecimal incomeLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client_card")
    private Client client;

}
