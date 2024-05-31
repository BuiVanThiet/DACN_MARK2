package com.example.ass2_beta_mark2.service;

import com.example.ass2_beta_mark2.entity.model.Size;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Optional;

public interface SizeService {
    Size save(Size entity);

    <S extends Size> Iterable<S> saveAll(Iterable<S> entities);

    Optional<Size> findById(Integer integer);

    boolean existsById(Integer integer);

    ArrayList<Size> findAll();

    ArrayList<Size> findAllById(ArrayList<Integer> integers);

    long count();

    void deleteById(Integer integer);

    void delete(Size entity);

    void deleteAllById(ArrayList<Integer> integers);

    void deleteAll(ArrayList<Size> entities);

    void deleteAll();
    //nagy1thang6
    ArrayList<Size> getSizeByName(String name);
    Page<Size> getPhanTrang(int page, int pageSize, String name);
}
