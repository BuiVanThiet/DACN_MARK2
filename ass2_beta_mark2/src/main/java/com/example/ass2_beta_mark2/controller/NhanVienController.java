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
    int pageNumber = 0;
    String tenTim="";

    @GetMapping(value = {"/hien-thi"})
    public String getIndex(ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        int pageMax = (int) this.qlnv.getNVByName(tenTim).size() / 2;
        if(this.qlnv.getNVByName(tenTim).size() % 2 != 0){
            pageMax++;
        }
        model.addAttribute("trang",pageMax);

        model.addAttribute("listNV",qlnv.getPhanTrang(pageNumber,2,tenTim).getContent());
        return "NhanVien/Index";
    }
    @GetMapping("/trang/{trang}")
    public String getPage(@PathVariable("trang") int trang,ModelMap modelMap){
        String check = addAccout(modelMap);
        if(check != null){
            return check;
        }

        pageNumber = trang -1;
        return "forward:/NhanVien/hien-thi";
    }
    @GetMapping("/tim-kiem")
    public String getSearch(@RequestParam("tenTim") String search,ModelMap modelMap){
        String check = addAccout(modelMap);
        if(check != null){
            return check;
        }

        this.tenTim = search.trim().toLowerCase();
        this.pageNumber = 0;
        return "forward:/NhanVien/hien-thi";
    }
    @GetMapping(value = {"/trang-them"})
    public String getAdd(ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        checkNut = true;
        NhanVien nv = new NhanVien();
        model.addAttribute("listCV",qlcv.findAll());
        model.addAttribute("nv",nv);
        model.addAttribute("check",checkNut);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Thêm");
        return "NhanVien/addAndUpdate";
    }
    @GetMapping(value = {"/delete/{idNV}"})
    public String getDelete(ModelMap model, @PathVariable(name = "idNV") Integer idNV){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        this.qlnv.deleteById(idNV);
        pageNumber = 0;
        return "redirect:/NhanVien/hien-thi";
    }
    @GetMapping(value = {"/detail/{idNV}"})
    public String getDetail(ModelMap model, @PathVariable(name = "idNV") int idNV){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        checkNut = true;
        NhanVien nv = this.qlnv.findById(idNV).orElse(new NhanVien());
        model.addAttribute("listCV",qlcv.findAll());
        model.addAttribute("nv",nv);
        model.addAttribute("chucVu",nv.getChucVu().getId());
        model.addAttribute("check",checkNut);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Sửa");
        return "NhanVien/addAndUpdate";
    }
    @PostMapping(value = {"/saveOrUpdate"})
    public String getSaveOrUpdate(ModelMap model, @Valid @ModelAttribute("nv") NhanVien nv, BindingResult bindingResult){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        if(bindingResult.hasErrors()){
            System.out.println("Loi day roi");
            model.addAttribute("listCV",qlcv.findAll());
            model.addAttribute("nv",nv);
            model.addAttribute("check",checkNut);
            model.addAttribute("action","/saveOrUpdate");
            model.addAttribute("value",checkNut ? "Them" : "Sua");
            model.addAttribute("form","../nhanVien/AddOrUpdate.jsp");
            return "NhanVien/addAndUpdate";
        }
        if(nv.getId() != null){
            NhanVien nvUpdate = this.qlnv.findById(nv.getId()).orElse(new NhanVien());
            nv.setNgayTao(nvUpdate.getNgayTao());
        }

        this.qlnv.save(nv);
        return "redirect:/NhanVien/hien-thi";
    }
}
