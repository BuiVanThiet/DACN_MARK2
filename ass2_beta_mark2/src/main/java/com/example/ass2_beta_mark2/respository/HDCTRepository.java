package com.example.ass2_beta_mark2.respository;

import com.example.ass2_beta_mark2.entity.model.HDCT;
import com.example.ass2_beta_mark2.entity.sumMoney.TotalAmount;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Optional;

public interface HDCTRepository extends CrudRepository<HDCT,Integer> {
    @Query("select new com.example.ass2_beta_mark2.entity.sumMoney.TotalAmount(hdct.hoaDon.id,SUM(hdct.tongTien)) from HDCT hdct group by hdct.hoaDon.id")
//@Query("SELECT new com.example.ass2_beta_mark2.entity.sumMoney.TotalAmount(hdct.hoaDon.id, COALESCE(SUM(CAST(hdct.tongTien AS java.math.BigDecimal)), 0)) " +
//        "FROM HDCT hdct" +
//        " RIGHT JOIN hdct.hoaDon hoaDon " +
//        " GROUP BY hoaDon.id")
    Iterable<TotalAmount> getTien();


    @Query("select hdct from HDCT hdct where hdct.hoaDon.id = :idCheck")
    Iterable<HDCT> getHDCTByIdHD(@Param("idCheck") Integer id);

    @Query("select new com.example.ass2_beta_mark2.entity.sumMoney.TotalAmount(hdct.hoaDon.id,sum(hdct.tongTien)) from HDCT hdct where hdct.hoaDon.id = :idCheck group by hdct.hoaDon.id")
    Optional<TotalAmount> getSumMoneyByID(@Param("idCheck") Integer id);
    //ngay1thang6
    @Query("select hdct from HDCT hdct where hdct.hoaDon.id = :idCheck")
    Page<HDCT> getHDCTByIdHD_Page(@Param("idCheck") Integer id, Pageable pageable);
}
