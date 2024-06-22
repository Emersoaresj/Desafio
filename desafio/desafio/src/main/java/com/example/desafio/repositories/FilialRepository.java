package com.example.desafio.repositories;

import com.example.desafio.model.FilialModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilialRepository extends JpaRepository <FilialModel, Long>{
}
