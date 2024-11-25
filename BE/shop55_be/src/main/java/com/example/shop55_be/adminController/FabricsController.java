package com.example.shop55_be.adminController;

import com.example.shop55_be.entity.Fabrics;
import com.example.shop55_be.service.EmployeeService;
import com.example.shop55_be.service.FabricsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/admin")

public class FabricsController {
    @Autowired
    private FabricsService fabricsService;
    @Autowired
    private EmployeeService employeeService;

    private String toAdminPages(Model model, HttpServletRequest request,
                                String contentFragment){
        String code = (String) request.getSession().getAttribute("code");
        model.addAttribute("contentFragment", contentFragment);
        model.addAttribute("employee",employeeService.findByCode(code));
        return "admin/adminPage";
    }

    @GetMapping("/fabric")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public String getAll(Model model,HttpServletRequest request,@RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Fabrics> cattegoriesPage = fabricsService.findFabrics(pageable);
        model.addAttribute("page", cattegoriesPage);
        return toAdminPages(model,request,"/admin/product/fabricPage");
    }

    @GetMapping("/fabric-seach")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public String seach(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p,
                        @RequestParam("keyword")String keyword){
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Fabrics> fabricsPage = fabricsService.findFabricsByKeyWords(pageable,keyword);
        model.addAttribute("page",fabricsPage);
        model.addAttribute("url","/admin/fabric?keyword=" +keyword);
        return toAdminPages(model,request,"/admin/product/fabricPage");
    }

    @GetMapping("/fabrics-add")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public String fabricsViewAdd(Model model,
                               HttpServletRequest request) throws Exception {

        model.addAttribute("newFabric", new Fabrics());
        return toAdminPages(model, request, "/admin/product/fabricFormAdd");
    }
    @PostMapping("/fabrics-add")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public String createFabrics(@Valid  @ModelAttribute("newFabric") Fabrics fabricsAdd, BindingResult result, Model model, HttpServletRequest request) {
        Date date = new Date();

        if (result.hasErrors()) {
            return toAdminPages(model, request, "admin/product/fabricFormAdd");
        }
        if (fabricsAdd.getId() ==null){

            fabricsAdd.setCreateDate(date);
            fabricsAdd.setFabricStatus(0);
            fabricsService.add(fabricsAdd);
            model.addAttribute("success","Thành Công");
            return "redirect:/admin/fabric";
        }
        Fabrics fabrics = fabricsService.detail(fabricsAdd.getId());
        fabricsAdd.setCreateDate(fabrics.getCreateDate());
        fabricsService.add(fabricsAdd);
        return "redirect:/admin/fabric";
    }

    @PostMapping("/fabrics-update")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public String updateFabrics(@Valid  @ModelAttribute("newFabric") Fabrics fabricsAdd, BindingResult result, Model model, HttpServletRequest request) {
        Date date = new Date();

        if (result.hasErrors()) {
            return toAdminPages(model, request, "admin/product/fabricUpdate");
        }
        if (fabricsAdd.getId() ==null){

            fabricsAdd.setCreateDate(date);
            fabricsAdd.setFabricStatus(0);
            fabricsService.add(fabricsAdd);
            model.addAttribute("success","Thành Công");
            return "redirect:/admin/fabric";
        }
        Fabrics fabrics = fabricsService.detail(fabricsAdd.getId());
        fabricsAdd.setCreateDate(fabrics.getCreateDate());
        fabricsService.add(fabricsAdd);
        return "redirect:/admin/fabric";
    }

    @GetMapping("/view-update-fabric/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String detailFabric(Model model, HttpServletRequest request, @PathVariable("id") Long id) {
       Fabrics fabrics = fabricsService.detail(id);
        model.addAttribute("newFabric", fabrics);
        return toAdminPages(model, request, "admin/product/fabricUpdate");
    }
    @GetMapping("/view-fabricDetail/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String FabricDetail(@PathVariable("id")long id,Model model,
                         HttpServletRequest request
    ){
        model.addAttribute("DetailFC",fabricsService.finbyid(id));
        return toAdminPages(model,request,"/admin/product/fabricDetail");
    }
    @GetMapping("/status-fabric")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String fiter(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p
            , @RequestParam("status") Long status ) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Fabrics> fabricsPage = fabricsService.findFabricsBystatus(pageable,status);
        model.addAttribute("fabric", fabricsPage.getContent());
        model.addAttribute("page",fabricsPage);
        model.addAttribute("url","/admin/fabric-seach?status="+status);
        return toAdminPages(model, request, "admin/product/fabricPage");
    }
}
