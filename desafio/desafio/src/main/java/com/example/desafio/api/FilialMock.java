package com.example.desafio.api;

import com.example.desafio.model.FilialModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/mock/filiais")
public class FilialMock {



    @GetMapping
    public List<FilialModel> getMockFiliais(){
        List<FilialModel> filiais = new ArrayList<>();

        //Criando duas filiais fictícias
        FilialModel filial1 = new FilialModel();
        filial1.setIdFilial(1L);
        filial1.setNomeFilial("Filial A");
        filial1.setCnpjFilial("12.345.678/0001-99");
        filial1.setCidadeFilial("São Paulo");
        filial1.setUfFilial("SP");
        filial1.setTipoFilial("Principal");
        filial1.setAtivoFilial(true);
        filial1.setDataCadastroFilial(LocalDate.of(2024, 6, 21));
        filial1.setUltimaAtualizacaoFilial(LocalDate.of(2024, 6, 21));


        FilialModel filial2 = new FilialModel();
        filial2.setIdFilial(2L);
        filial2.setNomeFilial("Filial B");
        filial2.setCnpjFilial("98.765.432/0001-99");
        filial2.setCidadeFilial("Rio de Janeiro");
        filial2.setUfFilial("RJ");
        filial2.setTipoFilial("Secundária");
        filial2.setAtivoFilial(true);
        filial2.setDataCadastroFilial(LocalDate.of(2024, 6, 22));
        filial2.setUltimaAtualizacaoFilial(LocalDate.of(2024, 6, 22));

        filiais.add(filial1);
        filiais.add(filial2);

        return filiais;

    }
}
