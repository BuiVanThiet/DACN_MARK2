package com.example.ass2_beta_mark2.respository;

import com.example.ass2_beta_mark2.entity.model.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface HoaDonRepository extends CrudRepository<HoaDon,Integer>, PagingAndSortingRepository<HoaDon,Integer> {
//    @Query("select hd from HoaDon hd where hd = :idCheck")
//    Optional<HoaDon> getHDByID(@Param("idCheck") Integer id);
    @Query("select hd from HoaDon hd where hd.trangThai = 'Chua thanh toan'")
    Iterable<HoaDon> getALLHDByTT();
    //ngay1thang6
    @Query("select hd from HoaDon hd where hd.trangThai = 'Chua thanh toan'")
    Page<HoaDon> getHDByTT_Page(Pageable pageable);
}
