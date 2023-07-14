package com.example.springbootdieta.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name="weight")
public class Weight {

    public Weight(Date date, BigDecimal weight) {
        this.date = date;
        this.weight = weight;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name="weight")
    private BigDecimal weight;

    @Column(name = "date")
    private Date date = new Date();

    public static Weight fromDto(WeightDto dto) {
        Weight weight = new Weight(
                dto.getDate()!=null ? dto.getDate() : new Date(),
                new BigDecimal(dto.getWeight())
        );
        if(dto.id!=-1){
            weight.setId(dto.id);
        }
        return weight;
    }

    public WeightDto toDto() {
        WeightDto dto = new WeightDto();
        dto.setId(id);
        dto.setWeight(weight.toString());
        dto.setDate(date);
        return dto;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class WeightDto {
        Integer id;
        String weight; // 34.7
        Date date;
    }
}
