package com.example.ass2_beta_mark2.respository;

import com.example.ass2_beta_mark2.entity.model.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
@Repository
public interface SanPhamRepository extends CrudRepository<SanPham,Integer>, PagingAndSortingRepository<SanPham,Integer> {
    @Query("select sp from SanPham sp where sp.maSanPham = :maCheck")
    public Optional<SanPham> getSPByMa(@Param("maCheck") String ma);
    //ngay1thang6
    @Query("select sp from SanPham sp where sp.tenSanPham like %:ten%")
    ArrayList<SanPham> getSPByName(@Param("ten") String ten);
    Page<SanPham> findByTenSanPhamContainsIgnoreCase(String ten, Pageable pageable);
}
