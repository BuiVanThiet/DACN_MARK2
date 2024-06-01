package com.example.ass2_beta_mark2.implement;

import com.example.ass2_beta_mark2.entity.model.CTSP;
import com.example.ass2_beta_mark2.entity.statistical.Statistical;
import com.example.ass2_beta_mark2.respository.CtspRepository;
import com.example.ass2_beta_mark2.service.CtspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CtspImplement implements CtspService {
    @Autowired
    CtspRepository qlctsp;

    @Override
    public CTSP save(CTSP entity) {
        return qlctsp.save(entity);
    }

    @Override
    public ArrayList<CTSP> saveAll(ArrayList<CTSP> entities) {
        return (ArrayList<CTSP>) qlctsp.saveAll(entities);
    }

    @Override
    public Optional<CTSP> findById(Integer integer) {
        return qlctsp.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return qlctsp.existsById(integer);
    }

    @Override
    public ArrayList<CTSP> findAll() {
        return (ArrayList<CTSP>) qlctsp.findAll();
    }

    @Override
    public ArrayList<CTSP> findAllById(ArrayList<Integer> integers) {
        return (ArrayList<CTSP>) qlctsp.findAllById(integers);
    }

    @Override
    public long count() {
        return qlctsp.count();
    }

    @Override
    public void deleteById(Integer integer) {
        qlctsp.deleteById(integer);
    }

    @Override
    public void delete(CTSP entity) {
        qlctsp.delete(entity);
    }

    @Override
    public void deleteAllById(ArrayList<Integer> integers) {
        qlctsp.deleteAllById(integers);
    }

    @Override
    public void deleteAll(ArrayList<CTSP> entities) {
        qlctsp.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        qlctsp.deleteAll();
    }

    @Override
    public ArrayList<CTSP> getCTSPByIdSP(Integer id){
        ArrayList<CTSP> list = (ArrayList<CTSP>) this.qlctsp.getAll(id);
        return list;
    }

    @Override
    public ArrayList<Statistical> getAllProductAndQuantity(){
        ArrayList<Statistical> list = (ArrayList<Statistical>) this.qlctsp.getAllProductAndQuantity();
        return list;
    }

    @Override
    public ArrayList<CTSP> getCTSPExist(){
        return (ArrayList<CTSP>) this.qlctsp.getCTSPExist();
    }

    @Override
    public ArrayList<CTSP> getCTSPByIdSP_IdMS_IdS(Integer idSP, Integer idMS, Integer idS) {
        if(idMS == null && idS == null){
            return this.getCTSPByIdSP(idSP);
        }else {
            return this.qlctsp.getCTSPByIdSP_IdMS_IdS(idSP,idMS,idS);
        }
    }

    @Override
    public ArrayList<CTSP> getCTSPByNameSP_IdMS_IdS(String ten, Integer idMS, Integer idS) {
        if(idMS == null  && idS == null){
            return this.qlctsp.getCTSPByNameSP(ten);
        }else {
            return this.qlctsp.getCTSPByNameSP_IdMS_IdS(ten,idMS,idS);
        }
    }

}
