package com.example.shop55_be.adminController;
import com.example.shop55_be.entity.Employee;
import com.example.shop55_be.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private StatisticsService statisticsService;


    private String toAdminPages(Model model, HttpServletRequest request,
                                String contentFragment) {
        Principal principal;

        String code = (String) request.getSession().getAttribute("code");
        model.addAttribute("contentFragment", contentFragment);
        model.addAttribute("employee", employeeService.findByCode(code));
        return "admin/adminPage";
    }

    @GetMapping("/")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String home(Model model, HttpServletRequest request) {
        long invoiceCount = statisticsService.getDailyInVoiCeCount();
        double dailyRevenue = statisticsService.getDailyRevenue(),
                monthlyRevenud = statisticsService.getMonthLyRevenue(),
                yearlyRevenud = statisticsService.getYearLyRevenue();

        model.addAttribute("invoiceCount",invoiceCount);
        model.addAttribute("dailyRevenue",dailyRevenue);
        model.addAttribute("monthlyRevenue",monthlyRevenud);
        model.addAttribute("yearlyRevenue",yearlyRevenud);
        model.addAttribute("monthlyRevenueInYear",statisticsService.monthlyRevenueInYear(LocalDate.now().getYear()));
        model.addAttribute("year",LocalDate.now().getYear());
        return toAdminPages(model, request, "admin/HomeAdmin/homeAdmin");
    }

    @PostMapping("/")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String statisticsFindByYear(Model model, HttpServletRequest request,@RequestParam("year")int year) {
        long invoiceCount = statisticsService.getDailyInVoiCeCount();
        double dailyRevenue = statisticsService.getDailyRevenue(),
                monthlyRevenud = statisticsService.getMonthLyRevenue(),
                yearlyRevenud = statisticsService.getYearLyRevenue();

        model.addAttribute("invoiceCount",invoiceCount);
        model.addAttribute("dailyRevenue",dailyRevenue);
        model.addAttribute("monthlyRevenue",monthlyRevenud);
        model.addAttribute("yearlyRevenue",yearlyRevenud);
        model.addAttribute("monthlyRevenueInYear",statisticsService.monthlyRevenueInYear(year));
        model.addAttribute("year",year);
        return toAdminPages(model, request, "admin/HomeAdmin/homeAdmin");
    }
}
