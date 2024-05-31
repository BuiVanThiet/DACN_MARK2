package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.model.CTSP;
import com.example.ass2_beta_mark2.entity.model.SanPham;
import com.example.ass2_beta_mark2.service.CtspService;
import com.example.ass2_beta_mark2.service.MauSacService;
import com.example.ass2_beta_mark2.service.SanPhamService;
import com.example.ass2_beta_mark2.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/CTSP"})
public class CtspController extends BaseController {
    @GetMapping(value = {"/hien-thi/{idSP}"})
    public String getIndex(ModelMap model,@PathVariable(name = "idSP") Integer idSP){
        model.addAttribute("listTSP",qlctsp.getCTSPByIdSP(idSP));
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "CTSP/Index";
    }
    @GetMapping(value = {"/trang-them/{idSP}"})
    public String getAdd(ModelMap model,@PathVariable(name = "idSP") Integer idSP){
        SanPham sp = this.qlsp.findById(idSP).orElse(new SanPham());
        CTSP ctsp = new CTSP();
        ctsp.setSanPham(sp);
        model.addAttribute("listMS",qlm.findAll());
        model.addAttribute("listS",qls.findAll());
        model.addAttribute("ctsp",ctsp);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("check",true);
        model.addAttribute("value","Thêm");
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "CTSP/addAndUpdate";
    }
    @GetMapping(value = {"/delete/{idCTSP}/{idSP}"})
    public String getDelete(ModelMap model, @PathVariable(name = "idCTSP") Integer idCTSP,@PathVariable(name = "idSP") Integer idSP){
        this.qlctsp.deleteById(idCTSP);
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "redirect:/CTSP/hien-thi/"+idSP;
    }
    @GetMapping(value = {"/detail/{idCTSP}"})
    public String getDetail(ModelMap model, @PathVariable(name = "idCTSP") Integer idCTSP){
        CTSP ctsp = this.qlctsp.findById(idCTSP).orElse(new CTSP());
        model.addAttribute("listMS",qlm.findAll());
        model.addAttribute("listS",qls.findAll());
        model.addAttribute("ctsp",ctsp);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("check",false);
        model.addAttribute("value","Sửa");
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        return "CTSP/addAndUpdate";
    }
    @PostMapping(value = {"/saveOrUpdate"})
    public String getSaveOrUpdate(ModelMap model, @ModelAttribute CTSP ctsp){
        SanPham spSave = this.qlsp.getSPByMa(ctsp.getSanPham().getMaSanPham());
        if(ctsp.getId() != null){
            CTSP ctspUpdate = this.qlctsp.findById(ctsp.getId()).orElse(new CTSP());
            ctsp.setNgayTao(ctspUpdate.getNgayTao());
        }
        ctsp.setSanPham(spSave);
        this.qlctsp.save(ctsp);
        return "redirect:/CTSP/hien-thi/"+ctsp.getSanPham().getId();
    }

}
