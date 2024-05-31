package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.account.Account;
import com.example.ass2_beta_mark2.entity.model.SanPham;
import com.example.ass2_beta_mark2.service.DanhMucService;
import com.example.ass2_beta_mark2.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/SanPham"})
public class SanPhamController extends BaseController {

    @GetMapping(value = {"/hien-thi"})
    public String getIndex(ModelMap model){
        model.addAttribute("listSP",qlsp.findAll());
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "SanPham/Index";
    }
    @GetMapping(value = {"/trang-them"})
    public String getAdd(ModelMap model){
        SanPham sp = new SanPham();
        model.addAttribute("listDM",qldm.findAll());
        model.addAttribute("sp",sp);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("check",true);
        model.addAttribute("value","Thêm");
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "SanPham/addAndUpdate";
    }

    @GetMapping(value = {"/detail/{idSP}"})
    public String getDetail(ModelMap model, @PathVariable(name = "idSP") Integer idSP){
        SanPham sp = this.qlsp.findById(idSP).orElse(new SanPham());
        model.addAttribute("listDM",qldm.findAll());
        model.addAttribute("sp",sp);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("check",false);
        model.addAttribute("value","Sửa");
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "SanPham/addAndUpdate";
    }

    @GetMapping(value = {"/delete/{idSP}"})
    public String getDelete(ModelMap model, @PathVariable(name = "idSP") Integer idSP){
        this.qlsp.deleteById(idSP);
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "redirect:/SanPham/hien-thi";
    }
    @PostMapping(value = {"/saveOrUpdate"})
    public String getSaveOrUpdate(ModelMap model, @ModelAttribute SanPham sp){
        if(sp.getId() != null){
            SanPham spUpdate = this.qlsp.findById(sp.getId()).orElse(new SanPham());
            sp.setNgayTao(spUpdate.getNgayTao());
        }
        this.qlsp.save(sp);
        return "redirect:/SanPham/hien-thi";
    }
}
