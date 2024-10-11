package br.com.fiap.coletaSeletiva.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_ponto_descarte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PontoDescarte {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_PONTO_DESCARTE"
    )
    @SequenceGenerator(
            name = "SEQ_PONTO_DESCARTE",
            sequenceName = "SEQ_PONTO_DESCARTE",
            allocationSize = 1
    )
    @Column(name = "ponto_id")
    private Long pontoId;

    @Column(name = "ponto_nome")
    @NotBlank(message = "O nome do ponto de descarte é obrigatório!")
    private String nome;

    @Column(name = "ponto_endereco")
    @NotBlank(message = "O endereço do ponto de descarte é obrigatório!")
    private String endereco;

    @Column(name = "tipo_residuo")
    @NotBlank(message = "O tipo de resíduo aceito no ponto de descarte é obrigatório!")
    private String tipoResiduo;

    @Column(name = "ponto_capacidade_max")
    private Double capacidadeMax;

    @Column(name = "ponto_quant_atual")
    private Double quantidadeAtual = 0.0;

}