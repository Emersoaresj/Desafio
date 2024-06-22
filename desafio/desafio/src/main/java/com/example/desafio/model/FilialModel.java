package com.example.desafio.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "TB_FILIAL")
public class FilialModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idFilial;

    @Column(nullable = false)
    private String nomeFilial;

    @Column(nullable = false, unique = true)
    private String cnpjFilial;

    @Column(nullable = false)
    private String cidadeFilial;

    @Column(nullable = false)
    private String ufFilial;

    @Column(nullable = false)
    private String tipoFilial;

    @Column(nullable = false)
    private boolean ativoFilial;

    @Column(nullable = false)
    private LocalDate dataCadastroFilial;

    @Column(nullable = false)
    private LocalDate ultimaAtualizacaoFilial;

    @OneToMany(mappedBy = "filial", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //cascata do tipo ALL para um acontecimento acontecer com todos os elementos
    private List<VendedorModel> vendedorModel;

    public Long getIdFilial() {
        return idFilial;
    }

    // Getters e Setters

    public void setIdFilial(Long idFilial) {
        this.idFilial = idFilial;
    }

    public String getNomeFilial() {
        return nomeFilial;
    }

    public void setNomeFilial(String nomeFilial) {
        this.nomeFilial = nomeFilial;
    }

    public String getCnpjFilial() {
        return cnpjFilial;
    }

    public void setCnpjFilial(String cnpjFilial) {
        this.cnpjFilial = cnpjFilial;
    }

    public String getCidadeFilial() {
        return cidadeFilial;
    }

    public void setCidadeFilial(String cidadeFilial) {
        this.cidadeFilial = cidadeFilial;
    }

    public String getUfFilial() {
        return ufFilial;
    }

    public void setUfFilial(String ufFilial) {
        this.ufFilial = ufFilial;
    }

    public String getTipoFilial() {
        return tipoFilial;
    }

    public void setTipoFilial(String tipoFilial) {
        this.tipoFilial = tipoFilial;
    }

    public boolean isAtivoFilial() {
        return ativoFilial;
    }

    public void setAtivoFilial(boolean ativoFilial) {
        this.ativoFilial = ativoFilial;
    }

    public LocalDate getDataCadastroFilial() {
        return dataCadastroFilial;
    }

    public void setDataCadastroFilial(LocalDate dataCadastroFilial) {
        this.dataCadastroFilial = dataCadastroFilial;
    }

    public LocalDate getUltimaAtualizacaoFilial() {
        return ultimaAtualizacaoFilial;
    }

    public void setUltimaAtualizacaoFilial(LocalDate ultimaAtualizacaoFilial) {
        this.ultimaAtualizacaoFilial = ultimaAtualizacaoFilial;
    }

    public List<VendedorModel> getVendedorModel() {
        return vendedorModel;
    }

    public void setVendedorModel(List<VendedorModel> vendedorModel) {
        this.vendedorModel = vendedorModel;
    }
}
