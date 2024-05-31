package com.example.ass2_beta_mark2.respository;

import com.example.ass2_beta_mark2.entity.model.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface SizeRepository extends CrudRepository<Size, Integer> {
    //ngay1thang6
    @Query("select s from Size s where s.ten like %:ten%")
    ArrayList<Size> getSizeByName(@Param("ten") String ten);
    Page<Size> findByTenContainsIgnoreCase(String ten, Pageable pageable);
}
