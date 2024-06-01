package com.example.ass2_beta_mark2.controller;

import com.example.ass2_beta_mark2.controller.base.BaseController;
import com.example.ass2_beta_mark2.entity.model.DanhMuc;
import com.example.ass2_beta_mark2.service.DanhMucService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/DanhMuc"})
public class DanhMucController extends BaseController {
    Boolean checkNut;
    int pageNumber = 0;
    String tenTim="";
    @GetMapping(value = {"/hien-thi"})
    public String getIndex(ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        int maxPage = (int) this.qldm.getDanhMucByName(tenTim).size()/2;
        if(this.qldm.getDanhMucByName(tenTim).size() % 2 != 0){
            maxPage++;
        }
        model.addAttribute("trang",maxPage);

        model.addAttribute("listDM",qldm.getPhanTrang(pageNumber,2,tenTim).getContent());
        model.addAttribute("form","../danhMuc/Table.jsp");
        return "homePage/Home";
    }

    @GetMapping("/trang/{trang}")
    public String getPage(@PathVariable("trang") int trang,ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        this.pageNumber = trang -1;
        return "forward:/DanhMuc/hien-thi";
    }
    @GetMapping("/tim-kiem")
    public String getSearch(@RequestParam("tenTim")String timKiem,ModelMap modelMap){
        String check = addAccout(modelMap);
        if(check != null){
            return check;
        }

        tenTim = timKiem.trim().toLowerCase();
        pageNumber = 0;
        return "forward:/DanhMuc/hien-thi";
    }
    @GetMapping(value = {"/trang-them"})
    public String getAdd(ModelMap model){
        checkNut = true;
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        DanhMuc dm = new DanhMuc();
        model.addAttribute("dm",dm);
        model.addAttribute("check",checkNut);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Thêm");

        model.addAttribute("form","../danhMuc/AddOrUpdate.jsp");
        return "homePage/Home";
    }
    @GetMapping(value = {"/delete/{idDM}"})
    public String getDelete(ModelMap model, @PathVariable(name = "idDM") String idDM){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        int id = Integer.parseInt(idDM);
        this.qldm.deleteById(id);
        pageNumber = 0;
        return "redirect:/DanhMuc/hien-thi";
    }
    @GetMapping(value = {"/detail/{idDM}"})
    public String getDetail(ModelMap model,@PathVariable(name = "idDM") String idDM){
        checkNut = false;
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        int id = Integer.parseInt(idDM);
        DanhMuc dm = this.qldm.findById(id).orElse(new DanhMuc());
        model.addAttribute("dm",dm);
        model.addAttribute("check",false);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Sửa");

        model.addAttribute("form","../danhMuc/AddOrUpdate.jsp");
        return "homePage/Home";
    }
    @PostMapping(value = {"/saveOrUpdate"})
    public  String getSaveOrUpdate(ModelMap model, @Valid @ModelAttribute("dm") DanhMuc dm, BindingResult bindingResult){
        String check = addAccout(model);
        if(check != null){
            return check;
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("dm",dm);
            model.addAttribute("check",checkNut);
            model.addAttribute("action","/saveOrUpdate");
            model.addAttribute("value",checkNut ? "Them" : "Sua");
            model.addAttribute("form","../danhMuc/AddOrUpdate.jsp");
            return "homePage/Home";
        }
        if(dm.getId() != null){
            DanhMuc dmUpdate = this.qldm.findById(dm.getId()).orElse(new DanhMuc());
            dm.setNgayTao(dmUpdate.getNgayTao());
        }
        qldm.save(dm);
        return "redirect:/DanhMuc/hien-thi";
    }
}
