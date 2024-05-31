package com.example.ass2_beta_mark2.respository;

import com.example.ass2_beta_mark2.entity.model.CTSP;
import com.example.ass2_beta_mark2.entity.model.MauSac;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface MauSacRepository extends CrudRepository<MauSac,Integer> {

}
