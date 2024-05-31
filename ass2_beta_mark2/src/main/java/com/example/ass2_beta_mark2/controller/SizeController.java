package com.example.ass2_beta_mark2.controller;

import com.example.ass1_mark_2.controller.base.BaseController;
import com.example.ass1_mark_2.entity.model.Size;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/Size"})
public class SizeController  extends BaseController  {
    boolean checkTrang;
    int pageNumber = 0;
    String tenTim="";
    @GetMapping(value = {"/hien-thi"})
    public String getIndex(ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        int pageMax = (int) this.qls.getSizeByName(tenTim).size()/2;
        if(this.qls.getSizeByName(tenTim).size() % 2 != 0){
            pageMax++;
        }
        model.addAttribute("trang",pageMax);

        model.addAttribute("listS",qls.getPhanTrang(pageNumber,2,tenTim).getContent());
        model.addAttribute("form","../size/Table.jsp");
        return "homePage/Home";
    }

    @GetMapping("/trang/{trang}")
    public String getPage(@PathVariable("trang") int trang,ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        this.pageNumber = trang - 1;
        return "forward:/Size/hien-thi";
    }

    @GetMapping("/tim-kiem")
    public String getSearch(@RequestParam("tenTim") String search,ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        this.tenTim = search.trim().toLowerCase();
        this.pageNumber = 0;
        return "forward:/Size/hien-thi";
    }
    @GetMapping(value = {"/trang-them"})
    public String getThem(ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        checkTrang = true;
        Size s = new Size();
        model.addAttribute("check",checkTrang);
        model.addAttribute("s",s);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Thêm");
        model.addAttribute("form","../size/AddOrUpdate.jsp");

        return "homePage/Home";
    }
    @GetMapping(value = {"/delete/{idS}"})
    public String getDelete(ModelMap model, @PathVariable(name = "idS") String idS){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        int id = Integer.parseInt(idS);
        this.qls.deleteById(id);
        pageNumber = 0;
        return "redirect:/Size/hien-thi";
    }
    @GetMapping(value = {"/detail/{idS}"})
    public String getDetail(ModelMap model, @PathVariable(name = "idS") String idS){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        checkTrang = false;
        int id = Integer.parseInt(idS);
        Size s = this.qls.findById(id).orElse(new Size());
        model.addAttribute("check",checkTrang);
        model.addAttribute("s",s);
        model.addAttribute("action","/saveOrUpdate");
        model.addAttribute("value","Sửa");
        model.addAttribute("form","../size/AddOrUpdate.jsp");

        return "homePage/Home";
    }
    @PostMapping(value = {"/saveOrUpdate"})
    public String getSaveOrUpdate(@Valid @ModelAttribute("s") Size s, BindingResult bindingResult, ModelMap model){
        String check = addAccout(model);
        if(check != null){
            return check;
        }

        if(bindingResult.hasErrors()){
            System.out.println("Loi them do ko du du lieu");
            model.addAttribute("s",s);
            model.addAttribute("check",checkTrang);
            model.addAttribute("action","/saveOrUpdate");
            model.addAttribute("value",checkTrang ? "Thêm" : "Sửa");
            model.addAttribute("form","../size/AddOrUpdate.jsp");
            return "homePage/Home";
        }
        if(s.getId() != null){
            Size sUpdate = this.qls.findById(s.getId()).orElse(new Size());
            s.setNgayTao(sUpdate.getNgayTao());
        }
        qls.save(s);
        return "redirect:/Size/hien-thi";
    }

}
