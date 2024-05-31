package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.model.MauSac;
import com.example.ass2_beta_mark2.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/MauSac")
public class MauSacController extends BaseController {
    @Autowired
    MauSacService qlm;
    @GetMapping(value = {"/hien-thi"})
    public String getIndex(ModelMap model){
        model.addAttribute("listM",qlm.findAll());
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "MauSac/Index";
    }
    @GetMapping(value = {"/trang-them"})
    public String getAdd(ModelMap model){
        MauSac ms = new MauSac();
        model.addAttribute("ms",ms);
        model.addAttribute("value","Thêm mới");
        model.addAttribute("check",true);
        model.addAttribute("action","/saveOrUpdate");
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "MauSac/addAndUpdate";
    }
    @GetMapping(value = {"/delete/{idM}"})
    public String getDelete(ModelMap model, @PathVariable(name = "idM") String idM){
        int id = Integer.parseInt(idM);
        this.qlm.deleteById(id);
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "redirect:/MauSac/hien-thi";
    }
    @GetMapping(value = {"/detail/{idM}"})
    public String getDetail(ModelMap model, @PathVariable(name = "idM") String idM){
        int id = Integer.parseInt(idM);
        MauSac ms = this.qlm.findById(id).orElse(new MauSac());
        model.addAttribute("ms",ms);
        model.addAttribute("value","Sửa");
        model.addAttribute("check",false);
        model.addAttribute("action","/saveOrUpdate");
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "MauSac/addAndUpdate";
    }

    @PostMapping(value = {"/saveOrUpdate"})
    public String getSaveOrUpdate(ModelMap model, @ModelAttribute MauSac ms){
        System.out.println(ms.toString());
        if(ms.getId() != null){
            MauSac msUpdate = this.qlm.findById(ms.getId()).orElse(new MauSac());
            ms.setNgayTao(msUpdate.getNgayTao());
        }
        this.qlm.save(ms);
String check = addAccout(model);
        if(check != null){
            return check;
        }        return "redirect:/MauSac/hien-thi";
    }

}
