package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.model.CTSP;
import com.example.ass2_beta_mark2.entity.model.SanPham;
import com.example.ass2_beta_mark2.service.CtspService;
import com.example.ass2_beta_mark2.service.MauSacService;
import com.example.ass2_beta_mark2.service.SanPhamService;
import com.example.ass2_beta_mark2.service.SizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(value = {"/CTSP"})
public class CtspController extends BaseController {
    Boolean nutCheck;
    Integer idSP;
    Integer mauSearch = null;
    Integer sizeSearch = null ;
    String page;
    @GetMapping(value = {"/hien-thi/{idSP}"})
    public String getIndex(ModelMap model,@PathVariable(name = "idSP") Integer SP){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        idSP = SP;

        int pagetNumber = (int) qlctsp.getCTSPByIdSP_IdMS_IdS(idSP,mauSearch,sizeSearch).size() / 2;
        if(qlctsp.getCTSPByIdSP_IdMS_IdS(idSP,mauSearch,sizeSearch).size() % 2 != 0){
            pagetNumber++;
        }

        model.addAttribute("trang",pagetNumber);
        model.addAttribute("listMS",qlm.findAll());
        model.addAttribute("listS",qls.findAll());
//        model.addAttribute("listTSP",qlctsp.getCTSPByIdSP_IdMS_IdS(idSP,mauSearch,sizeSearch));
        model.addAttribute("listTSP",this.getPhanTrang(page));
        return "CTSP/Index";
    }
    @GetMapping("/tim-kiem")
    public String getSearch(@RequestParam("mauTim") String idMau,@RequestParam("sizeTim") String idS){
        System.out.println("id sie la "+idS);
        System.out.println("idMau la " + idMau);
        if (idS.trim().isEmpty()){
            this.sizeSearch = null;
        }else {
            this.sizeSearch = Integer.parseInt(idS);
        }

        if(idMau.trim().isEmpty()){
            this.mauSearch = null;
        }else {
            this.mauSearch = Integer.parseInt(idMau);
        }

        page = null;
        return "forward:/CTSP/hien-thi/"+idSP;
    }

    @GetMapping("/trang/{trang}")
    public String getPage(@PathVariable("trang") String trang){
        page = trang.trim().toLowerCase();
        return "forward:/CTSP/hien-thi/"+idSP;
    }
    @GetMapping(value = {"/trang-them/{idSP}"})
    public String getAdd(ModelMap model,@PathVariable(name = "idSP") Integer idSP){
        nutCheck = true;
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
        nutCheck = false;
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
    public String getSaveOrUpdate(ModelMap model, @Valid @ModelAttribute("ctsp") CTSP ctsp, BindingResult bindingResult){
        String check = addAccout(model);
        if(bindingResult.hasErrors()){
            model.addAttribute("listMS",qlm.findAll());
            model.addAttribute("listS",qls.findAll());
            model.addAttribute("ctsp",ctsp);
            model.addAttribute("action","/saveOrUpdate");
            model.addAttribute("check",nutCheck);
            model.addAttribute("value",nutCheck ? "Them" : "Sua");
            return "CTSP/addAndUpdate";
        }
        SanPham spSave = this.qlsp.getSPByMa(ctsp.getSanPham().getMaSanPham());
        if(ctsp.getId() != null){
            CTSP ctspUpdate = this.qlctsp.findById(ctsp.getId()).orElse(new CTSP());
            ctsp.setNgayTao(ctspUpdate.getNgayTao());
        }
        ctsp.setSanPham(spSave);
        this.qlctsp.save(ctsp);
        return "redirect:/CTSP/hien-thi/"+ctsp.getSanPham().getId();
    }

    //phan trang
    public ArrayList<CTSP> getPhanTrang(String trang){
        int maxPage = (int) Math.ceil((double) this.qlctsp.getCTSPByIdSP_IdMS_IdS(idSP,mauSearch,sizeSearch).size() / 2);
        System.out.println("So trang toi da la " + maxPage);
        int page = 1;
        if(trang != null){
            page = Integer.parseInt(trang);
        }
        int star =(page-1)*2;
        int end =Math.min(star+2,this.qlctsp.getCTSPByIdSP_IdMS_IdS(idSP,mauSearch,sizeSearch).size());
        ArrayList<CTSP> list =new ArrayList<>(this.qlctsp.getCTSPByIdSP_IdMS_IdS(idSP,mauSearch,sizeSearch).subList(star,end));
        return list;
    }

}
