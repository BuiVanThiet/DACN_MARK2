package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.account.Account;
import com.example.ass2_beta_mark2.entity.model.SanPham;
import com.example.ass2_beta_mark2.service.DanhMucService;
import com.example.ass2_beta_mark2.service.SanPhamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/SanPham"})
public class SanPhamController extends BaseController {

    Boolean checkNut;
    int pageNumber = 0;
    String tenTim="";

    @GetMapping(value = {"/hien-thi"})
    public String getIndex(ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        int pageMax = (int) this.qlsp.getSPByName(tenTim).size() / 2;
        if(this.qlsp.getSPByName(tenTim).size() % 2 != 0){
            pageMax++;
        }

        model.addAttribute("trang",pageMax);
        model.addAttribute("listSP",qlsp.getPhanTrang(pageNumber,2,tenTim).getContent());
        model.addAttribute("form","../sanPham/Table.jsp");
        return "homePage/Home";
    }
    @GetMapping("/trang/{trang}")
    public String getPage(@PathVariable("trang") int trang,ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        pageNumber = trang -1;
        return "forward:/SanPham/hien-thi";
    }
    @GetMapping("/tim-kiem")
    public String getSearch(@RequestParam("tenTim") String search,ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        tenTim = search.trim().toLowerCase();
        pageNumber = 0;
        return "forward:/SanPham/hien-thi";
    }
    @GetMapping(value = {"/trang-them"})
    public String getAdd(ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        checkNut = true;
        SanPham sp = new SanPham();
        model.addAttribute("listDM",qldm.findAll());
        model.addAttribute("sp",sp);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("check",checkNut);
        model.addAttribute("value","Thêm");

        model.addAttribute("form","../sanPham/AddOrUpdate.jsp");
        return "homePage/Home";
    }

    @GetMapping(value = {"/detail/{idSP}"})
    public String getDetail(ModelMap model, @PathVariable(name = "idSP") Integer idSP){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        checkNut = false;
        SanPham sp = this.qlsp.findById(idSP).orElse(new SanPham());
        model.addAttribute("listDM",qldm.findAll());
        model.addAttribute("sp",sp);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("check",checkNut);
        model.addAttribute("value","Sửa");

        model.addAttribute("form","../sanPham/AddOrUpdate.jsp");
        return "homePage/Home";
    }

    @GetMapping(value = {"/delete/{idSP}"})
    public String getDelete(ModelMap model, @PathVariable(name = "idSP") Integer idSP){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        this.qlsp.deleteById(idSP);
        pageNumber = 0;
        return "redirect:/SanPham/hien-thi";
    }
    @PostMapping(value = {"/saveOrUpdate"})
    public String getSaveOrUpdate(ModelMap model, @Valid @ModelAttribute("sp") SanPham sp, BindingResult bindingResult){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        if(bindingResult.hasErrors()){
            model.addAttribute("listDM",qldm.findAll());
            model.addAttribute("sp",sp);
            model.addAttribute("action","/saveOrUpdate");
            model.addAttribute("check",checkNut);
            model.addAttribute("value",checkNut ? "Them" : "Sua");
            model.addAttribute("form","../sanPham/AddOrUpdate.jsp");
            return "homePage/Home";
        }
        if(sp.getId() != null){
            SanPham spUpdate = this.qlsp.findById(sp.getId()).orElse(new SanPham());
            sp.setNgayTao(spUpdate.getNgayTao());
        }

        this.qlsp.save(sp);
        return "redirect:/SanPham/hien-thi";
    }
}
