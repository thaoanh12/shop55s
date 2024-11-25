package com.example.shop55_be.adminController;

import com.example.shop55_be.dto.SizeDto;
import com.example.shop55_be.entity.Size;
import com.example.shop55_be.service.EmployeeService;
import com.example.shop55_be.service.SizeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/admin")
public class SizeController {
    @Autowired
    SizeService  sizeService;
    @Autowired
    private EmployeeService employeeService;

    private String toAdminPages(Model model, HttpServletRequest request,
                                String contentFragment){
        String code = (String) request.getSession().getAttribute("code");
        model.addAttribute("contentFragment", contentFragment);
        model.addAttribute("employee",employeeService.findByCode(code));
        return "admin/adminPage";
    }

    @GetMapping("hien-thi")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(sizeService.getAll());
    }

    @PostMapping("/size-add")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<?> add(@RequestBody SizeDto sizeDto){
        Size size = sizeService.addSize(sizeDto);
        return new ResponseEntity<>(size != null ?
                size : "failure", size != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/size-update/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<Size> updateColor(@PathVariable Long id, @RequestBody SizeDto sizeDto) {
        Size updatedSize = sizeService.updateSize(id, sizeDto);
        if (updatedSize != null) {
            return new ResponseEntity<>(updatedSize, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<?> deleteSize(@PathVariable("id")Long id){
        return ResponseEntity.ok(sizeService.deleteSize(id));
    }

}
