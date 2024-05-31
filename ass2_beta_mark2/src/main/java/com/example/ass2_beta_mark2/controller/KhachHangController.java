package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.model.KhachHang;
import com.example.ass2_beta_mark2.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/KhachHang"})
public class KhachHangController extends BaseController {
    @GetMapping(value = {"/hien-thi"})
    public String getIndex(ModelMap model){
        model.addAttribute("listKH",qlkh.findAll());
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "KhachHang/Index";
    }
    @GetMapping(value = {"/delete/{idKH}"})
    public String getDelete(ModelMap model, @PathVariable(name = "idKH") int idKH){
        this.qlkh.deleteById(idKH);
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "redirect:/KhachHang/hien-thi";
    }
    @GetMapping(value = {"/detail/{idKH}"})
    public String getDetail(ModelMap model, @PathVariable(name = "idKH") int idKH){
        KhachHang kh = this.qlkh.findById(idKH).orElse(new KhachHang());
        model.addAttribute("kh",kh);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Sửa");
        model.addAttribute("check",false);
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "KhachHang/addAndUpdate";
    }
    @GetMapping(value = {"/trang-them"})
    public String getAdd(ModelMap model){
        KhachHang kh = new KhachHang();
        model.addAttribute("kh",kh);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Thêm");
        model.addAttribute("check",true);
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "KhachHang/addAndUpdate";
    }
    @PostMapping(value = {"/saveOrUpdate"})
    public String getSaveOrUpdate(ModelMap model, @ModelAttribute KhachHang kh){
        if(kh.getId() != null){
            KhachHang khUpdate = this.qlkh.findById(kh.getId()).orElse(new KhachHang());
            kh.setNgayTao(khUpdate.getNgayTao());
        }
        this.qlkh.save(kh);
        return "redirect:/KhachHang/hien-thi";
    }
}
