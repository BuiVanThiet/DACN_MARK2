package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.model.MauSac;
import com.example.ass2_beta_mark2.service.MauSacService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/MauSac")
public class MauSacController extends BaseController {
    boolean checkNut;
    int pageNumber = 0;
    String tenTim="";

    @GetMapping(value = {"/hien-thi"})
    public String getIndex(ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        int maxPage = (int) this.qlm.getMSByName(tenTim).size() / 2;
        if(this.qlm.getMSByName(tenTim).size() % 2 != 0){
            maxPage++;
        }
        model.addAttribute("trang",maxPage);

        model.addAttribute("listM",qlm.getPhanTrang(pageNumber,2,tenTim).getContent());
        return "MauSac/Index";
    }
    @GetMapping("/trang/{trang}")
    public String getPage(@PathVariable("trang") int trang,ModelMap modelMap){
        String check = addAccout(modelMap);
        if(check != null){
            return check;
        }
        pageNumber = trang -1;
        return "forward:/MauSac/hien-thi";
    }
    @GetMapping("/tim-kiem")
    public String getSearch(@RequestParam("tenTim") String search,ModelMap modelMap){
        String check = addAccout(modelMap);
        if(check != null){
            return check;
        }

        this.tenTim = search.trim().toLowerCase();
        this.pageNumber = 0;
        return "forward:/MauSac/hien-thi";
    }
    @GetMapping(value = {"/trang-them"})
    public String getAdd(ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        checkNut = true;
        MauSac ms = new MauSac();
        model.addAttribute("ms",ms);
        model.addAttribute("value","Thêm mới");
        model.addAttribute("check",checkNut);
        model.addAttribute("action","/saveOrUpdate");
        return "MauSac/addAndUpdate";
    }
    @GetMapping(value = {"/delete/{idM}"})
    public String getDelete(ModelMap model, @PathVariable(name = "idM") String idM){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        int id = Integer.parseInt(idM);
        this.qlm.deleteById(id);
        pageNumber = 0;
        return "redirect:/MauSac/hien-thi";
    }
    @GetMapping(value = {"/detail/{idM}"})
    public String getDetail(ModelMap model, @PathVariable(name = "idM") String idM){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        checkNut = false;
        int id = Integer.parseInt(idM);
        MauSac ms = this.qlm.findById(id).orElse(new MauSac());
        model.addAttribute("ms",ms);
        model.addAttribute("value","Sửa");
        model.addAttribute("check",checkNut);
        model.addAttribute("action","/saveOrUpdate");
        return "MauSac/addAndUpdate";
    }

    @PostMapping(value = {"/saveOrUpdate"})
    public String getSaveOrUpdate(ModelMap model, @Valid @ModelAttribute("ms") MauSac ms, BindingResult bindingResult){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("ms",ms);
            model.addAttribute("value",checkNut ? "Them moi" : "Sua");
            model.addAttribute("check",checkNut);
            model.addAttribute("action","/saveOrUpdate");
            return "MauSac/addAndUpdate";
        }

        if(ms.getId() != null){
            MauSac msUpdate = this.qlm.findById(ms.getId()).orElse(new MauSac());
            ms.setNgayTao(msUpdate.getNgayTao());
        }
        this.qlm.save(ms);

        return "redirect:/MauSac/hien-thi";
    }

}
