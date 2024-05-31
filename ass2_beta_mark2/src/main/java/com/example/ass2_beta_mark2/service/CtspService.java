package com.example.ass2_beta_mark2.service;

import com.example.ass2_beta_mark2.entity.model.CTSP;
import com.example.ass2_beta_mark2.entity.statistical.Statistical;

import java.util.ArrayList;
import java.util.Optional;

public interface CtspService {
    CTSP save(CTSP entity);

    ArrayList<CTSP> saveAll(ArrayList<CTSP> entities);

    Optional<CTSP> findById(Integer integer);

    boolean existsById(Integer integer);

    ArrayList<CTSP> findAll();

    ArrayList<CTSP> findAllById(ArrayList<Integer> integers);

    long count();

    void deleteById(Integer integer);

    void delete(CTSP entity);

    void deleteAllById(ArrayList<Integer> integers);

    void deleteAll(ArrayList<CTSP> entities);

    void deleteAll();

    ArrayList<CTSP> getCTSPByIdSP(Integer id);

    ArrayList<Statistical> getAllProductAndQuantity();

    ArrayList<CTSP> getCTSPExist();
}
