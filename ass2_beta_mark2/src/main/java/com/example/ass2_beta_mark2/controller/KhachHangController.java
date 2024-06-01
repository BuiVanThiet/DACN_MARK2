package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.model.KhachHang;
import com.example.ass2_beta_mark2.service.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/KhachHang"})
public class KhachHangController extends BaseController {
    Boolean checkNut;
    int pageNumber = 0;
    String tenTim="";

    @GetMapping(value = {"/hien-thi"})
    public String getIndex(ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        int pageMax = (int) this.qlkh.getKHByName(tenTim).size() / 2;
        if(this.qlkh.getKHByName(tenTim).size() % 2 != 0){
            pageMax++;
        }
        model.addAttribute("trang",pageMax);

        model.addAttribute("listKH",qlkh.getPhanTrang(pageNumber,2,tenTim).getContent());
        return "KhachHang/Index";
    }

    @GetMapping("/trang/{trang}")
    public String getPage(@PathVariable("trang") int trang, ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        this.pageNumber = trang - 1;
        return "forward:/KhachHang/hien-thi";
    }
    @GetMapping("/tim-kiem")
    public String getSearch(@RequestParam("tenTim") String search,ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        tenTim = search.trim().toLowerCase();
        pageNumber = 0;
        return "forward:/KhachHang/hien-thi";
    }
    @GetMapping(value = {"/delete/{idKH}"})
    public String getDelete(ModelMap model, @PathVariable(name = "idKH") int idKH){
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        pageNumber = 0;
        this.qlkh.deleteById(idKH);
        return "redirect:/KhachHang/hien-thi";
    }
    @GetMapping(value = {"/detail/{idKH}"})
    public String getDetail(ModelMap model, @PathVariable(name = "idKH") int idKH){
        checkNut = false;
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        KhachHang kh = this.qlkh.findById(idKH).orElse(new KhachHang());
        model.addAttribute("kh",kh);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Sửa");
        model.addAttribute("check",checkNut);
        return "KhachHang/addAndUpdate";
    }
    @GetMapping(value = {"/trang-them"})
    public String getAdd(ModelMap model){
        checkNut = true;
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        KhachHang kh = new KhachHang();
        model.addAttribute("kh",kh);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Thêm");
        model.addAttribute("check",checkNut);
        return "KhachHang/addAndUpdate";
    }
    @PostMapping(value = {"/saveOrUpdate"})
    public String getSaveOrUpdate(ModelMap model, @Valid @ModelAttribute("kh") KhachHang kh, BindingResult bindingResult){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("kh",kh);
            model.addAttribute("action","/saveOrUpdate");
            model.addAttribute("value",checkNut ? "Them" : "Sua");
            model.addAttribute("check",checkNut);
            return "KhachHang/addAndUpdate";
        }
        if(kh.getId() != null){
            KhachHang khUpdate = this.qlkh.findById(kh.getId()).orElse(new KhachHang());
            kh.setNgayTao(khUpdate.getNgayTao());
        }
        this.qlkh.save(kh);
        return "redirect:/KhachHang/hien-thi";
    }
}
