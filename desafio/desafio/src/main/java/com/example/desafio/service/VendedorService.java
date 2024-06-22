package com.example.desafio.service;

import com.example.desafio.dto.VendedorDto;
import com.example.desafio.model.FilialModel;
import com.example.desafio.model.VendedorModel;
import com.example.desafio.repositories.FilialRepository;
import com.example.desafio.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class VendedorService {

    @Autowired
    VendedorRepository vendedorRepository;

    @Autowired
    FilialRepository filialRepository;


    public Object criarVendedor(VendedorDto vendedorDto){
        // Obter filiais mockadas da API
        RestTemplate restTemplate = new RestTemplate(); // Cria uma instância do RestTemplate para fazer requisições HTTP
        FilialModel[] filiais = restTemplate.getForObject("http://localhost:8080/mock/filiais", FilialModel[].class); // Faz uma requisição GET para a API mockada que retorna as filiais
        List<FilialModel> filialList = Arrays.asList(filiais); // Converte o array de FilialModel para uma lista de FilialModel

// Encontrar a filial pelo ID fornecido no DTO do vendedor
        FilialModel filial = filialList.stream() // Cria um stream para percorrer a lista de filiais
                .filter(f -> f.getIdFilial().equals(vendedorDto.filialId())) // Filtra a filial com o ID correspondente ao informado no DTO do vendedor
                .findFirst() // Retorna o primeiro elemento que satisfaz a condição de filtro
                .orElseThrow(() -> new RuntimeException("Filial não encontrada")); // Lança uma exceção caso a filial não seja encontrada com o ID fornecido

// Converte VendedorDto para VendedorModel
        var vendedor = new VendedorModel(); // Cria uma nova instância de VendedorModel

        vendedor.setMatricula(gerarMatricula(vendedorDto.tipoContratacao())); // Define a matrícula do vendedor com base no método criado
        vendedor.setNome(vendedorDto.nome()); // Define o nome do vendedor com base no DTO recebido

        if (vendedorDto.dataNascimento() != null) {
            vendedor.setDataNascimento(vendedorDto.dataNascimento());
        } // Define a data de nascimento do vendedor com base no DTO recebido (a inserção é opcional)

        if(vendedorDto.tipoContratacao().equals("Pessoa Jurídica")){
            if (vendedorDto.cpfCnpj().matches("\\d{14}")){
                vendedor.setCpfCnpj(vendedorDto.cpfCnpj()); // Define o CNPJ do vendedor com base no DTO recebido
            } else {
                throw new RuntimeException("CNPJ inválido!");
            }
        }
        else if (vendedorDto.tipoContratacao().equals("CLT")|| vendedorDto.tipoContratacao().equals("Outsourcing")){
            if (vendedorDto.cpfCnpj().matches("\\d{11}")){
                vendedor.setCpfCnpj(vendedorDto.cpfCnpj()); // Define o CPF do vendedor com base no DTO recebido
            } else {
                throw new RuntimeException("CPF inválido!");
            }
        }
        else {
            throw new RuntimeException("Tipo de contratação inválido. Tipo de contratação inválido. Valores possíveis: Pessoa Jurídica, CLT ou Outsourcing");
        }

        vendedor.setEmail(vendedorDto.email()); // Define o e-mail do vendedor com base no DTO recebido
        vendedor.setTipoContratacao(vendedorDto.tipoContratacao()); // Define o tipo de contratação do vendedor com base no DTO recebido
        vendedor.setFilial(filial); // Associa a filial encontrada ao vendedor

        return vendedorRepository.save(vendedor); // Salva o vendedor no banco de dados
    }

    // Método para transformar o tipo de contratação
    public String abreviacaoTipoContratacao(String tipoContratacao) {
        switch (tipoContratacao) {
            case "Outsourcing":
                return "OUT";
            case "CLT":
                return "CLT";
            case "Pessoa Jurídica":
                return "PJ";
            default:
                throw new RuntimeException("Tipo de contratação inválido");
        }
    }

    // Método para gerar a matrícula com a abreviação do tipo de contratação
    Long numeroSequencial = 1L; //Iniciando um número para a matrícula
    public String gerarMatricula(String tipoContratacao) {
        var tipoAbreviado = abreviacaoTipoContratacao(tipoContratacao);

        // Concatenando o número sequencial com a abreviação do tipo de contratação
        String matricula = numeroSequencial + "-" + tipoAbreviado;

        // Incrementando o número sequencial para a próxima matrícula
        numeroSequencial++;


        return matricula;
    }


}
