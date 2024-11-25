package com.example.shop55_be.adminController;


import com.example.shop55_be.entity.Employee;
import com.example.shop55_be.model.NewPassword;
import com.example.shop55_be.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    private String toAdminPages(Model model, HttpServletRequest request,
                                String contentFragment){
        String code = (String) request.getSession().getAttribute("code");
        model.addAttribute("contentFragment", contentFragment);
        model.addAttribute("employee",employeeService.findByCode(code));
        return "admin/adminPage";
    }

    @GetMapping("/employee")
    @PreAuthorize(" hasRole('ADMIN')")
    public String employeePage(Model model,HttpServletRequest request
            ,@RequestParam("p") Optional<Integer> number){
        Pageable pageable = PageRequest.of(number.orElse(0),5);
        Page<Employee> page = employeeService.findEmployee(pageable);
        model.addAttribute("page",page);
        model.addAttribute("size",page.getContent().size());
        model.addAttribute("url","/admin/employee");
        return toAdminPages(model,request,"admin/employee/employeePage");
    }
    @GetMapping("/employee-filter-by-status")
    @PreAuthorize(" hasRole('ADMIN')")
    public String employeeFillterByStatus(Model model,HttpServletRequest request
            ,@RequestParam("p") Optional<Integer> number,@RequestParam("status")Integer status){
        Pageable pageable = PageRequest.of(number.orElse(0),5);
        Page<Employee> page = employeeService.findEmployeeByStatus(pageable,status);
        model.addAttribute("page",page);
        model.addAttribute("size",page.getContent().size());
        model.addAttribute("url","/admin/employee-filter-by-status?status="+status);
        return toAdminPages(model,request,"admin/employee/employeePage");
    }
    @GetMapping("/employee-filter-by-keyword")
    @PreAuthorize(" hasRole('ADMIN')")
    public String employeeFillterByKeyword(Model model,HttpServletRequest request
            ,@RequestParam("p") Optional<Integer> number,@RequestParam("keyword")String keyword){
        Pageable pageable = PageRequest.of(number.orElse(0),5);
        Page<Employee> page = employeeService.findByKeyWord(pageable,keyword);
        model.addAttribute("page",page);
        model.addAttribute("size",page.getContent().size());
        model.addAttribute("url","/admin/employee-filter-by-keyword?keyword="+keyword);
        return toAdminPages(model,request,"admin/employee/employeePage");
    }
    @GetMapping("/employee-detail/{id}")
    @PreAuthorize(" hasRole('ADMIN')")
    public String employeeDetail(@PathVariable("id")long id,Model model,
                                 HttpServletRequest request
    ){
        model.addAttribute("employeeDetail",employeeService.findById(id));
        return toAdminPages(model,request,"/admin/employee/employeeDetail");
    }
    @GetMapping("/employee-view-add")
    @PreAuthorize(" hasRole('ADMIN')")
    public String employeeViewAdd(Model model,
                                  HttpServletRequest request)throws Exception{
        model.addAttribute("newEmployee",new Employee());
        return toAdminPages(model,request,"/admin/employee/employeeFormAdd");
    }

    @PostMapping("/employee-create")
    @PreAuthorize("hasRole('ADMIN')")
    public String create(@Valid @ModelAttribute("newEmployee") Employee employee, BindingResult bindingResult, Model model,HttpServletRequest request) throws Exception {
        if(bindingResult.hasErrors()){
            String code = (String) request.getSession().getAttribute("code");
            model.addAttribute("contentFragment","/admin/employee/employeeFormAdd");
            model.addAttribute("employee",employeeService.findByCode(code));
            model.addAttribute("isError",true);
            model.addAttribute("newEmpolyee",employee);
            return "admin/adminPage";
        }
        employeeService.create(employee);
        return "redirect:/admin/employee";
    }

    @GetMapping("/employee-find-by-mail/{mail}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<?> findByMail(@PathVariable("mail") String mail){
        return new ResponseEntity<>(employeeService.findByMail(mail),HttpStatus.OK);
    }

    @GetMapping("/employee-find-by-code/{code}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<?> findByCode(@PathVariable("code") String code){
        return new ResponseEntity<>(employeeService.findByCode(code),HttpStatus.OK);
    }

    @GetMapping("/employee-layoff/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String layOff(@PathVariable(value = "id") long id){
        employeeService.layOff(id);
        return "redirect:/admin/employee";
    }


    @GetMapping("/employee-profile/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String profile(@PathVariable(value = "id") long id,Model model,HttpServletRequest request){
        model.addAttribute("employeeUpdate",employeeService.findById(id));
        return toAdminPages(model,request,"/admin/employee/employeeFormUpdate");
    }

    @PostMapping("/employee-update/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String update(@Valid @ModelAttribute("employeeUpdate") Employee employee, BindingResult bindingResult,
                         Model model,HttpServletRequest request,@PathVariable("id")long id){

        if(bindingResult.hasErrors()){
            String code = (String) request.getSession().getAttribute("code");
            model.addAttribute("contentFragment","/admin/employee/employeeFormUpdate");
            model.addAttribute("employee",employeeService.findByCode(code));
            model.addAttribute("isError",true);
            model.addAttribute("employeeUpdate",employee);
            return "admin/adminPage";
        }
        employeeService.update(id,employee);
        return "redirect:/admin/employee-profile/"+id;
    }
    @GetMapping("/employee-view-change-password/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String viewChangePassword(@PathVariable(value = "id") long id,
                                     Model model,HttpServletRequest request){
        model.addAttribute("newPassword",new NewPassword());
        return toAdminPages(model,request,"/admin/employee/employeeChangePassword");
    }
    @PostMapping("/employee-change-password/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String changePassword(@Valid @ModelAttribute("newPassword") NewPassword newPassword, BindingResult bindingResult,@PathVariable(value = "id") long id,
                                     Model model,HttpServletRequest request){
        String code = (String) request.getSession().getAttribute("code");
        Employee employee = employeeService.findByCode(code);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(bindingResult.hasErrors()) {
            model.addAttribute("contentFragment", "/admin/employee/employeeChangePassword");
            model.addAttribute("employee", employee);
            model.addAttribute("isError", true);
            model.addAttribute("newPassword", newPassword);
            return "admin/adminPage";
        }else if(!passwordEncoder.matches(newPassword.getOldPassword(),employee.getPasscode())){
            model.addAttribute("contentFragment","/admin/employee/employeeChangePassword");
            model.addAttribute("employee",employee);
            model.addAttribute("isError",true);
            model.addAttribute("error",true);
            model.addAttribute("message","Mật khẩu không đúng");
            model.addAttribute("newPassword",newPassword);
            return "admin/adminPage";
        }
        employeeService.changePassword(id,newPassword.getNewPassword());
        return "redirect:/logout";
    }
}
