package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.model.Size;
import com.example.ass2_beta_mark2.service.SizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/Size"})
public class SizeController  extends BaseController  {
    boolean checkTrang;
    @GetMapping(value = {"/hien-thi"})
    public String getIndex(ModelMap model){
        model.addAttribute("listS",qls.findAll());
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "Size/Index";
    }
    @GetMapping(value = {"/trang-them"})
    public String getThem(ModelMap model){
        checkTrang = true;
        Size s = new Size();
        model.addAttribute("check",true);
        model.addAttribute("s",s);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Thêm");
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "Size/addAndUpdate";
    }
    @GetMapping(value = {"/delete/{idS}"})
    public String getDelete(ModelMap model, @PathVariable(name = "idS") String idS){
        int id = Integer.parseInt(idS);
        this.qls.deleteById(id);
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "redirect:/Size/hien-thi";
    }
    @GetMapping(value = {"/detail/{idS}"})
    public String getDetail(ModelMap model, @PathVariable(name = "idS") String idS){
        checkTrang = false;
        int id = Integer.parseInt(idS);
        Size s = this.qls.findById(id).orElse(new Size());
        model.addAttribute("check",false);
        model.addAttribute("s",s);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Sửa");
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "Size/addAndUpdate";
    }
    @PostMapping(value = {"/saveOrUpdate"})
    public String getSaveOrUpdate(@Valid @ModelAttribute("s") Size s, BindingResult bindingResult, ModelMap model){
        String check = addAccout(model);
        if(bindingResult.hasErrors()){
            System.out.println("Loi them do ko du du lieu");
            model.addAttribute("s",s);
            model.addAttribute("check",checkTrang);
            model.addAttribute("action","/saveOrUpdate");
            model.addAttribute("value",checkTrang ? "Thêm" : "Sửa");
            return "Size/addAndUpdate";
        }
        if(s.getId() != null){
            Size sUpdate = this.qls.findById(s.getId()).orElse(new Size());
            s.setNgayTao(sUpdate.getNgayTao());
        }
        qls.save(s);
        return "redirect:/Size/hien-thi";
    }
}
