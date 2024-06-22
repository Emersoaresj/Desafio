package com.example.desafio;

import com.example.desafio.controller.VendedorController;
import com.example.desafio.dto.VendedorDto;
import com.example.desafio.model.FilialModel;
import com.example.desafio.model.VendedorModel;
import com.example.desafio.repositories.VendedorRepository;
import com.example.desafio.service.VendedorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@WebMvcTest(VendedorController.class)
public class VendedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendedorService vendedorService;

    @Mock
    private VendedorRepository vendedorRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarVendedor() throws Exception {
        // Preparando dados de entrada para o teste
        VendedorDto vendedorDto = new VendedorDto(
                "Emerson",
                LocalDate.of(1999, 6, 19),
                "12345678900", // CPF válido para Pessoa Física (CLT ou Outsourcing)
                "emerson@emerson.com",
                "CLT",
                1L // ID de uma filial existente
        );

        // Mock do serviço de vendedor para simular o salvamento
        var vendedorSalvo = new VendedorModel();
        vendedorSalvo.setId(1L); // Defina um ID para o vendedor salvo
        vendedorSalvo.setNome(vendedorDto.nome());
        vendedorSalvo.setDataNascimento(vendedorDto.dataNascimento());
        vendedorSalvo.setCpfCnpj(vendedorDto.cpfCnpj());
        vendedorSalvo.setEmail(vendedorDto.email());
        vendedorSalvo.setTipoContratacao(vendedorDto.tipoContratacao());
        vendedorSalvo.setMatricula("1-CLT"); // Exemplo de matrícula gerada
        vendedorSalvo.setFilial(new FilialModel()); // Simulando uma filial

        Mockito.when(vendedorService.criarVendedor(ArgumentMatchers.any(VendedorDto.class))).thenReturn(vendedorSalvo);

        // Executando o teste via requisição HTTP mockada
        mockMvc.perform(MockMvcRequestBuilders.post("/api/vendedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "  \"nome\": \"Emerson\",\n" +
                                "  \"dataNascimento\": \"1999-06-19\",\n" +
                                "  \"cpfCnpj\": \"12345678900\",\n" +
                                "  \"email\": \"emerson@emerson.com\",\n" +
                                "  \"tipoContratacao\": \"CLT\",\n" +
                                "  \"filialId\": 1\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()) // Verifica se o ID está presente na resposta
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(vendedorDto.nome())) // Verifica se o nome está correto na resposta
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpfCnpj").value(vendedorDto.cpfCnpj())) // Verifica se o CPF/CNPJ está correto na resposta
                .andDo(print()); // Imprime o resultado para debug
    }


}
