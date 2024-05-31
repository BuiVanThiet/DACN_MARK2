package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.model.NhanVien;
import com.example.ass2_beta_mark2.service.ChucVuService;
import com.example.ass2_beta_mark2.service.NhanVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/NhanVien"})
public class NhanVienController extends BaseController {
    boolean checkNut;
    @GetMapping(value = {"/hien-thi"})
    public String getIndex(ModelMap model){
        model.addAttribute("listNV",qlnv.findAll());
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "NhanVien/Index";
    }
    @GetMapping(value = {"/trang-them"})
    public String getAdd(ModelMap model){
        checkNut = true;
        NhanVien nv = new NhanVien();
        model.addAttribute("listCV",qlcv.findAll());
        model.addAttribute("nv",nv);
        model.addAttribute("check",true);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Thêm");
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "NhanVien/addAndUpdate";
    }
    @GetMapping(value = {"/delete/{idNV}"})
    public String getDelete(ModelMap model, @PathVariable(name = "idNV") Integer idNV){
        this.qlnv.deleteById(idNV);
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "redirect:/NhanVien/hien-thi";
    }
    @GetMapping(value = {"/detail/{idNV}"})
    public String getDetail(ModelMap model, @PathVariable(name = "idNV") int idNV){
        checkNut = true;
        NhanVien nv = this.qlnv.findById(idNV).orElse(new NhanVien());
        model.addAttribute("listCV",qlcv.findAll());
        model.addAttribute("nv",nv);
        model.addAttribute("chucVu",nv.getChucVu().getId());
        model.addAttribute("check",false);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Sửa");
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "NhanVien/addAndUpdate";
    }
    @PostMapping(value = {"/saveOrUpdate"})
    public String getSaveOrUpdate(ModelMap model, @Valid @ModelAttribute("nv") NhanVien nv, BindingResult bindingResult){
        String check = addAccout(model);
        if(bindingResult.hasErrors()){
            System.out.println("Loi day roi");
            model.addAttribute("listCV",qlcv.findAll());
            model.addAttribute("nv",nv);
            model.addAttribute("check",checkNut);
            model.addAttribute("action","/saveOrUpdate");
            model.addAttribute("value",checkNut ? "Them" : "Sua");
            return "NhanVien/addAndUpdate";
        }
        if(nv.getId() != null){
            NhanVien nvUpdate = this.qlnv.findById(nv.getId()).orElse(new NhanVien());
            nv.setNgayTao(nvUpdate.getNgayTao());
        }
        this.qlnv.save(nv);
        if(check != null){
            return check;
        }
        return "redirect:/NhanVien/hien-thi";
    }
}
