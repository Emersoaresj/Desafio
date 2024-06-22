package com.example.desafio.controller;

import com.example.desafio.dto.VendedorDto;
import com.example.desafio.model.VendedorModel;
import com.example.desafio.repositories.VendedorRepository;
import com.example.desafio.service.VendedorService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

    @Autowired
    VendedorService vendedorService;
    VendedorRepository vendedorRepository;


    @PostMapping
    public ResponseEntity<VendedorModel> saveVendedor(@RequestBody @Valid VendedorDto vendedorDto) {
        VendedorModel vendedorSalvo = (VendedorModel) vendedorService.criarVendedor(vendedorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendedorSalvo);
    }

    @GetMapping()
    public ResponseEntity<List<VendedorModel>> getAllVendedores(){
        return ResponseEntity.status(HttpStatus.OK).body(vendedorRepository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVendedores(@PathVariable(name = "id") Long id,
                                                @RequestBody @Valid VendedorDto vendedorDto){
        Optional<VendedorModel> vendedorModel = vendedorRepository.findById(id); // buscar um vendedor no banco de dados pelo ID fornecido.
        if(vendedorModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor não encontrado");
        }
        var vendedorModel1 = vendedorModel.get(); //Não instanciando um novo (igual o post) porque já existe. Então precisamos considerar o ID existente e não criar um novo
        BeanUtils.copyProperties(vendedorDto, vendedorModel1); //Fazendo a conversão para o BD
        return ResponseEntity.status(HttpStatus.OK).body(vendedorRepository.save(vendedorModel1));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVendedores(@PathVariable(name = "id") Long id){
        Optional<VendedorModel> vendedorModel = vendedorRepository.findById(id); //Optional porque pode ou não vir vazio (não sabemos se o ID existe)
        if(vendedorModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vendedor não encontrado");
        }
        vendedorRepository.delete(vendedorModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Vendedor deletado com sucesso.");
    }


}
