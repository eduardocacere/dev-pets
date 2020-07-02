package br.com.pets.api.model;

import br.com.pets.api.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "pet")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Long idBreed;

    @Column()
    private Long idSpecie;

    @NotEmpty
    @Size(max=80)
    private String name;

    @Max(99)
    private Integer age;

    @Enumerated(EnumType.ORDINAL)
    private GenderEnum gender;
}
