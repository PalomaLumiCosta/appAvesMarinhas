package br.edu.ifrs.osorio.tads.palomalumi.model;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

/**
 *Esta classe encapsula aos dados coletados  nos censos realizados a cada uma hora durante a navegação
 * Sao tomados dados oceanograficose parametros de navegação, e após são realizados os censos para identificação e contagem das aves.
 * NAO IMPLEMENTEI , MAS DEPOIS PENSEI MELHOR E DADOS DE NAVEGAÇÃO DEVEM SER UMA CLASSE SEPARADA
 * @author lumi
 */

@Entity
@Table(name = "censos")
public class Censo implements Serializable {
    /**
     * Contador estático para garantir que cada novo censo receba um código único.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate data;
    @Column(nullable = false)
    private LocalTime hora;
    @Column(nullable = false)
    private double direcaoNavegacao;
    @Column(nullable = false)
    private double velocidadeNavegacao;
    @Column(nullable = false)
    private double velocidadeVento;
    @Column(nullable = false)
    private double direcaoVento;
    @Column(nullable = false)
    private double pressao;
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
    private double longitude;
    @Column(nullable = false)
    private double profundidade;

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "censo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Leitura> leiturasSeguidoras = new ArrayList<>();
    @OneToMany(mappedBy = "censo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Leitura> leiturasContinuo = new ArrayList<>();
//    @OneToMany(mappedBy = "censo", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<LeituraInstantanea> leiturasInstantaneas = new ArrayList<>();

    //A classe usuario é automaticamente o observador, quem loga , é o observador 1
    @ManyToOne
    @JoinColumn(name = "observador_id", nullable = false)
    private Usuario observador;
    ///  COMO INCLUIR O PROJETO ?
   // @ManyToOne
   // @JoinColumn(name = "projeto_id", nullable = false) // Assumindo que todo censo pertence a um projeto
   // private Projeto projeto;


    /**
     * Construtor principal para criar um objeto Censo completo.
     * O código do censo é gerado automaticamente de forma sequencial.     *
     * @param direcaoNavegacao A direção da navegação em graus (0-360°).
     * @param velocidadeNavegacao A velocidade da embarcação em nós.
     * @param velocidadeVento A velocidade do vento em nós.
     * @param direcaoVento A direção de origem do vento em graus(0-360°).
     * @param pressao A pressão atmosférica.
     * @param latitude A coordenada de latitude (ex: -31.4567).
     * @param longitude A coordenada de longitude (ex: -41.1234).
     * @param profundidade A profundidade local em metros.
     * @param observador O objeto Usuario que representa o observador logado.
     * @param leiturasSeguidoras Uma lista de leituras de aves seguidoras.
     * @param leiturasContinuo Uma lista de leituras do censo contínuo.
     //* @param leiturasInstantaneas Uma lista de listas, onde cada lista interna representa uma das 10 sessões de leitura instantânea.
     */
    public Censo(double direcaoNavegacao, double velocidadeNavegacao, double velocidadeVento,
                 double direcaoVento, double pressao, double latitude, double longitude,
                 double profundidade, Usuario observador, List<Leitura> leiturasSeguidoras, List<Leitura> leiturasContinuo) {
        // O ID será gerado automaticamente pelo JPA

        // dados coletados na cabine
        this.data = LocalDate.now();
        this.hora = LocalTime.now();
        this.direcaoNavegacao = direcaoNavegacao;
        this.velocidadeNavegacao = velocidadeNavegacao;
        this.velocidadeVento = velocidadeVento;
        this.direcaoVento = direcaoVento;
        this.pressao = pressao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.profundidade = profundidade;
        this.observador = observador;
        // dados coletados do tijupa
        if (leiturasSeguidoras != null) {
            leiturasSeguidoras.forEach(leitura -> leitura.setCenso(this));
            this.leiturasSeguidoras.addAll(leiturasSeguidoras);
        }
        if (leiturasContinuo != null) {
            leiturasContinuo.forEach(leitura -> leitura.setCenso(this));
            this.leiturasContinuo.addAll(leiturasContinuo);
        }
//        if (leiturasInstantaneas != null) {
//            leiturasInstantaneas.forEach(leitura -> leitura.setCenso(this));
//            this.leiturasInstantaneas.addAll(leiturasInstantaneas);
//        }
    }

    // Getters---embora nao usado neste momento, acredito que é util ter apenas o getters destes dados .... futuramente para busca ?
    /**
     * Retorna o código único deste censo.
     * @return o código do censo.
     */
    public Censo() {
        // Construtor padrão exigido pelo JPA
    }

    public Long getId() {
        return id;
    }

    /**
     * Retorna o observador que registrou este censo.
     * @return o objeto Usuario do observador.
     */
    public Usuario getObservador() {
        return observador;
    }

    /**
     * Retorna a data que o censo foi realizado.
     * @return a data da orealização do censo
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Retorna horario que o censo foi realizado.
     * @return a hora da realização do censo
     */
    public LocalTime getHora() {
        return hora;
    }

    public double getDirecaoNavegacao() {
        return direcaoNavegacao;
    }

    public double getVelocidadeNavegacao() {
        return velocidadeNavegacao;
    }

    public double getVelocidadeVento() {
        return velocidadeVento;
    }

    public double getDirecaoVento() {
        return direcaoVento;
    }

    public double getPressao() {
        return pressao;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getProfundidade() {
        return profundidade;
    }

    // Getters para as listas de especies
    public List<Leitura> getLeiturasSeguidoras() {
        return leiturasSeguidoras;
    }
    public List<Leitura> getLeiturasContinuo() {
        return leiturasContinuo;
    }

//    public List<LeituraInstantanea> getLeiturasInstantaneas() {
//        return leiturasInstantaneas;
//    }

    //SETERS, mantive, mas nao tem tbm aplicação neste sistema, no caso poderia futuramente ter aplicaçãoo para ajustes de dados pelo ADMIN, em caso de anotaçoes equivocadas???
    public void setDirecaoNavegacao(double direcaoNavegacao) {
        this.direcaoNavegacao = direcaoNavegacao;
    }

    public void setVelocidadeNavegacao(double velocidadeNavegacao) {
        this.velocidadeNavegacao = velocidadeNavegacao;
    }

    public void setVelocidadeVento(double velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    public void setDirecaoVento(double direcaoVento) {
        this.direcaoVento = direcaoVento;
    }

    public void setPressao(double pressao) {
        this.pressao = pressao;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setProfundidade(double profundidade) {
        this.profundidade = profundidade;
    }

    /**
     * Atualiza os dados deste Censo com base nas informações de outro objeto Censo.
     * Metodo pra edição pois preserva o código, data,hora e
     * o status e o observador originais.     *
     * @param novosDados O objeto Censo contendo os novos dados a serem copiados.
     */
    public void setCenso(Censo novosDados) {
        // ... (setters de campos primitivos)

        this.leiturasSeguidoras.clear();
        if (novosDados.getLeiturasSeguidoras() != null) {
            novosDados.getLeiturasSeguidoras().forEach(leitura -> leitura.setCenso(this)); // <--- ADICIONE ESTA LINHA
            this.leiturasSeguidoras.addAll(novosDados.getLeiturasSeguidoras());
        }

        this.leiturasContinuo.clear();
        if (novosDados.getLeiturasContinuo() != null) {
            novosDados.getLeiturasContinuo().forEach(leitura -> leitura.setCenso(this)); // <--- ADICIONE ESTA LINHA
            this.leiturasContinuo.addAll(novosDados.getLeiturasContinuo());
        }
        // ... (parte instantaneas)
    }
    /**
     * Retorna uma representação em String completa e formatada do objeto Censo
     *
     *  @return Uma String multi-linha com todos os detalhes do censo.
     */


    @Override
    public int hashCode() {
        return Objects.hash(id);    }

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
        final Censo other = (Censo) obj;
        if (this.id == null || other.id == null) return false;
        return this.id.equals(other.id);
    }

    public void setObservador(Usuario observadorFixo) {
    }

    public void setHora(LocalTime now) {
    }

    public void setData(LocalDate now) {
    }
}