package br.com.banco.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "CONTA")
public class Count {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_conta")
    @SequenceGenerator(name = "sequence_conta", sequenceName = "sequence_conta", initialValue = 3, allocationSize = 1)
    @Column(name = "id_conta", nullable = false)
    private Integer id;
    @Column(name = "nome_responsavel", nullable = false, length = 50)
    private String responsibleName;
}
