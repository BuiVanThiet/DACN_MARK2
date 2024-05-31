package com.example.ass2_beta_mark2.respository;

import com.example.ass2_beta_mark2.entity.model.HoaDon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HoaDonRepository extends CrudRepository<HoaDon,Integer> {
//    @Query("select hd from HoaDon hd where hd = :idCheck")
//    Optional<HoaDon> getHDByID(@Param("idCheck") Integer id);
    @Query("select hd from HoaDon hd where hd.trangThai = 'Chua thanh toan'")
    Iterable<HoaDon> getALLHDByTT();
}
