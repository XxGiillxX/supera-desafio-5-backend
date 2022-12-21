package br.com.banco.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@DynamicUpdate
@Table(name = "TRANSFERENCIA")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_transferencia")
    @SequenceGenerator(name = "sequence_transferencia", sequenceName = "sequence_transferencia", initialValue = 7, allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "data_transferencia", nullable = false)
    private LocalDateTime transferDate;
    @Column(name = "valor", nullable = false)
    private Double value;
    @Column(name = "tipo", nullable = false, length = 15)
    private String type;
    @Column(name = "nome_operador_transacao", length = 50)
    private String transactionOperator;
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE }, targetEntity = Count.class)
    @JoinColumn(name = "conta_id", referencedColumnName = "id_conta")
    private Count count;
}
