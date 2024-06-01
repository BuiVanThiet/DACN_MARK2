package com.example.ass2_beta_mark2.implement;

import com.example.ass2_beta_mark2.entity.model.HoaDon;
import com.example.ass2_beta_mark2.respository.HoaDonRepository;
import com.example.ass2_beta_mark2.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class HoaDonImplement implements HoaDonService {
    @Autowired
    HoaDonRepository qlhd;

    @Override
    public HoaDon save(HoaDon entity) {
        return qlhd.save(entity);
    }

    @Override
    public ArrayList<HoaDon> saveAll(ArrayList<HoaDon> entities) {
        return (ArrayList<HoaDon>) qlhd.saveAll(entities);
    }

    @Override
    public Optional<HoaDon> findById(Integer integer) {
        return qlhd.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return qlhd.existsById(integer);
    }

    @Override
    public ArrayList<HoaDon> findAll() {
        return (ArrayList<HoaDon>)qlhd.findAll();
    }

    @Override
    public ArrayList<HoaDon>  findAllById(ArrayList<Integer> integers) {
        return (ArrayList<HoaDon>) qlhd.findAllById(integers);
    }

    @Override
    public long count() {
        return qlhd.count();
    }

    @Override
    public void deleteById(Integer integer) {
        qlhd.deleteById(integer);
    }

    @Override
    public void delete(HoaDon entity) {
        qlhd.delete(entity);
    }

    @Override
    public void deleteAllById(ArrayList<Integer> integers) {
        qlhd.deleteAllById(integers);
    }

    @Override
    public void deleteAll(ArrayList<HoaDon> entities) {
        qlhd.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        qlhd.deleteAll();
    }
//    @Override
//    public Optional<HoaDon> getHDByID(Integer id){
//        return qlhd.getHDByID(id);
//    }

    @Override
    public ArrayList<HoaDon> getALLHDByTT(){
        return (ArrayList<HoaDon>) this.qlhd.getALLHDByTT();
    }

    @Override
    public Page<HoaDon> getPhanTrang(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.qlhd.getHDByTT_Page(pageable);
    }
}
