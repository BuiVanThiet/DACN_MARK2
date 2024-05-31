package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.model.ChucVu;
import com.example.ass2_beta_mark2.service.ChucVuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/ChucVu"})
public class ChucVuController extends BaseController {
    boolean checkNut;
    @GetMapping(value = {"/hien-thi"})
    public String getIndex(ModelMap model){
        model.addAttribute("listCV",qlcv.findAll());
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "ChucVu/Index";
    }
    @GetMapping(value = {"/trang-them"})
    public String getAdd(ModelMap model){
        checkNut = true;
        ChucVu cv = new ChucVu();
        model.addAttribute("cv",cv);
        model.addAttribute("check",true);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Thêm");
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "ChucVu/addAndUpdate";
    }
    @GetMapping(value = {"/delete/{idCV}"})
    public String getDelete(ModelMap model, @PathVariable(name = "idCV") int idCV){
        this.qlcv.deleteById(idCV);
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "redirect:/ChucVu/hien-thi";
    }
    @GetMapping(value = {"/detail/{idCV}"})
    public String getDetail(ModelMap model, @PathVariable(name = "idCV") int idCV){
        checkNut = false;
        ChucVu cv = this.qlcv.findById(idCV).orElse(new ChucVu());
        model.addAttribute("cv",cv);
        model.addAttribute("check",false);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Sửa");
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "ChucVu/addAndUpdate";
    }
    @PostMapping(value = {"/saveOrUpdate"})
    public String getSaveOrUpdate(ModelMap model, @Valid @ModelAttribute("cv") ChucVu cv, BindingResult bindingResult){
        String check = addAccout(model);
        if(bindingResult.hasErrors()){
            model.addAttribute("cv",cv);
            model.addAttribute("check",checkNut);
            model.addAttribute("action","/saveOrUpdate");
            model.addAttribute("value",checkNut ? "Them" : "Sua");
            return "ChucVu/addAndUpdate";
        }
        if(cv.getId() != null){
            ChucVu cvUpdate = this.qlcv.findById(cv.getId()).orElse(new ChucVu());
            cv.setNgayTao(cvUpdate.getNgayTao());
        }
        this.qlcv.save(cv);
        return "redirect:/ChucVu/hien-thi";
    }
}
