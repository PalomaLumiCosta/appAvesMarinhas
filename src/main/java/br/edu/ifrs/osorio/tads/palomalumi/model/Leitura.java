package br.edu.ifrs.osorio.tads.palomalumi.model;
/**
 * Esta classe esta dentro de censo, e somente  pode ser manipulada dentro do censo .
 * @author lumi
 */

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "leituras")
public class Leitura implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String especie;
    @Column(nullable = false)
    private int contagem;

    // Construtor principal
    public Leitura() {
        // Construtor padrão exigido pelo JPA
    }

    public Leitura(String especie, int contagem) {
        this.especie = especie;
        this.contagem = contagem;
    }
    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public int getContagem() { return contagem; }
    public void setContagem(int contagem) { this.contagem = contagem; }

    @Override
    public String toString() {
        return "Leitura{" +
                "id=" + id +
                ", especie='" + especie +  ", contagem=" + contagem +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "censo_id")
    private Censo censo;

    public Censo getCenso() {
        return censo;
    }

    public void setCenso(Censo censo) {
        this.censo = censo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Leitura other = (Leitura) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
