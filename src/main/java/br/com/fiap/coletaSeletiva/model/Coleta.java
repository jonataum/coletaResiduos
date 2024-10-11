package br.com.fiap.coletaSeletiva.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "tbl_coleta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Coleta {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_COLETA"
    )
    @SequenceGenerator(
            name = "SEQ_COLETA",
            sequenceName = "SEQ_COLETA",
            allocationSize = 1
    )
    @Column(name = "coleta_id")
    private Long coletaId;

    @Column(name = "coleta_data")
    private LocalDate dataColeta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ponto_id")
    private PontoDescarte pontoDescarte;
}
