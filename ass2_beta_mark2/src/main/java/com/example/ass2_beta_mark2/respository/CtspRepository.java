package com.example.ass2_beta_mark2.respository;

import com.example.ass2_beta_mark2.entity.model.CTSP;
import com.example.ass2_beta_mark2.entity.statistical.Statistical;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface CtspRepository extends CrudRepository<CTSP,Integer> {
    @Query("select ctsp from CTSP ctsp WHERE ctsp.sanPham.id = :idCheck")
    Iterable<CTSP> getAll(@Param("idCheck") Integer idSP);
    @Query("SELECT new com.example.ass2_beta_mark2.entity.statistical.Statistical(sp.tenSanPham, SUM(ct.soLuongTon)) FROM CTSP ct INNER JOIN ct.sanPham sp GROUP BY sp.tenSanPham")
    Iterable<Statistical> getAllProductAndQuantity();
    @Query("select ctsp from  CTSP ctsp where ctsp.trangThai = 'Hoat dong' and ctsp.soLuongTon > 0")
    Iterable<CTSP> getCTSPExist();
}
