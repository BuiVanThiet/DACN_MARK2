package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.model.DanhMuc;
import com.example.ass2_beta_mark2.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/DanhMuc"})
public class DanhMucController extends BaseController {
    @GetMapping(value = {"/hien-thi"})
    public String getIndex(ModelMap model){
        model.addAttribute("listDM",qldm.findAll());
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "DanhMuc/Index";
    }
    @GetMapping(value = {"/trang-them"})
    public String getAdd(ModelMap model){
        DanhMuc dm = new DanhMuc();
        model.addAttribute("dm",dm);
        model.addAttribute("check",true);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Thêm");
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "DanhMuc/addAndUpdate";
    }
    @GetMapping(value = {"/delete/{idDM}"})
    public String getDelete(ModelMap model, @PathVariable(name = "idDM") String idDM){
        System.out.println(idDM);
        int id = Integer.parseInt(idDM);
        this.qldm.deleteById(id);
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "redirect:/DanhMuc/hien-thi";
    }
    @GetMapping(value = {"/detail/{idDM}"})
    public String getDetail(ModelMap model,@PathVariable(name = "idDM") String idDM){
        int id = Integer.parseInt(idDM);
        DanhMuc dm = this.qldm.findById(id).orElse(new DanhMuc());
        model.addAttribute("dm",dm);
        model.addAttribute("check",false);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Sửa");
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "DanhMuc/addAndUpdate";
    }
    @PostMapping(value = {"/saveOrUpdate"})
    public  String getSaveOrUpdate(ModelMap model, @ModelAttribute DanhMuc dm){
        if(dm.getId() != null){
            DanhMuc dmUpdate = this.qldm.findById(dm.getId()).orElse(new DanhMuc());
            dm.setNgayTao(dmUpdate.getNgayTao());
        }
        qldm.save(dm);
        return "redirect:/DanhMuc/hien-thi";
    }
}
