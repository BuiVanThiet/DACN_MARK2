package com.example.ass2_beta_mark2.implement;

import com.example.ass2_beta_mark2.entity.model.HDCT;
import com.example.ass2_beta_mark2.entity.sumMoney.TotalAmount;
import com.example.ass2_beta_mark2.respository.HDCTRepository;
import com.example.ass2_beta_mark2.service.HDCTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class HDCTImplement implements HDCTService {
    @Autowired
    HDCTRepository qlhdct;

    @Override
    public HDCT save(HDCT entity) {
        return qlhdct.save(entity);
    }

    @Override
    public ArrayList<HDCT> saveAll(ArrayList<HDCT> entities) {
        return (ArrayList<HDCT>) qlhdct.saveAll(entities);
    }

    @Override
    public Optional<HDCT> findById(Integer integer) {
        return qlhdct.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return qlhdct.existsById(integer);
    }

    @Override
    public ArrayList<HDCT> findAll() {
        return (ArrayList<HDCT>) qlhdct.findAll();
    }

    @Override
    public ArrayList<HDCT>findAllById(ArrayList<Integer> integers) {
        return (ArrayList<HDCT>) qlhdct.findAllById(integers);
    }

    @Override
    public long count() {
        return qlhdct.count();
    }

    @Override
    public void deleteById(Integer integer) {
        qlhdct.deleteById(integer);
    }

    @Override
    public void delete(HDCT entity) {
        qlhdct.delete(entity);
    }

    @Override
    public void deleteAllById(ArrayList<Integer> integers) {
        qlhdct.deleteAllById(integers);
    }

    @Override
    public void deleteAll(ArrayList<HDCT> entities) {
        qlhdct.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        qlhdct.deleteAll();
    }

    @Override
    public ArrayList<TotalAmount> getTien(){
        return (ArrayList<TotalAmount>) qlhdct.getTien();
    }
    @Override
    public ArrayList<HDCT> getHDCTByIdHD(Integer id){
        return (ArrayList<HDCT>) qlhdct.getHDCTByIdHD(id);
    }
    @Override
    public Optional<TotalAmount> getSumMoneyById(Integer id){
        return qlhdct.getSumMoneyByID(id);
    }

    @Override
    public Page<HDCT> getHDCTByIdHD_Page(Integer id, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return qlhdct.getHDCTByIdHD_Page(id,pageable);
    }
}
