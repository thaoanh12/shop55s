package com.example.shop55_be.adminController;

import com.example.shop55_be.entity.Order;
import com.example.shop55_be.entity.OrderDetail;
import com.example.shop55_be.service.EmployeeService;
import com.example.shop55_be.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private OrderService orderService;

    private String toAdminPages(Model model, HttpServletRequest request,
                                String contentFragment) {
        String code = (String) request.getSession().getAttribute("code");
        model.addAttribute("contentFragment", contentFragment);
        model.addAttribute("employee", employeeService.findByCode(code));
        return "admin/adminPage";
    }

    @GetMapping("/order")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String orderPage(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> number) {
        model.addAttribute("page", orderService.getAllOrderPaid(number.orElse(0)));
        return toAdminPages(model, request, "admin/order/orderPage");
    }

    @GetMapping("/order-detail/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String orderDetail(Model model, HttpServletRequest request, @PathVariable("id") Long id) {
        model.addAttribute("orderDetail", orderService.getAllOrderDetail(id));
        model.addAttribute("orderDetailT", orderService.getOrderById(id).get());
        return toAdminPages(model, request, "admin/order/orderDetail");
    }

    @GetMapping("/order-detail-confim/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String orderDetailConfim(Model model, HttpServletRequest request,
                                    @PathVariable("id") Long id) {
        List<OrderDetail> orderDetails = orderService.getAllOrderDetail(id);

        Double sumProduct = 0.0;
        for (OrderDetail or : orderDetails) {
            sumProduct += or.getTotalAmount();
            model.addAttribute("sumProduct", sumProduct);
        }
        model.addAttribute("orderDetail", orderDetails);
        model.addAttribute("orderDetailT", orderService.getOrderById(id).get());

        return toAdminPages(model, request, "admin/order/orderDetailConfim");
    }

    @PostMapping("/order-confim-status/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public String orderConfimStatus(Model model,
                                    @PathVariable("id") Long id,
                                    @RequestParam("ship") double ship,
                                    @RequestParam("orderId") Long orderId ,
                                    RedirectAttributes redirectAttributes
    ) {

        if (ship < 0) {
            redirectAttributes.addFlashAttribute("erorder", "Tiền ship không được nhỏ hơn 0 !");
            return "redirect:/admin/order-detail-confim/"+orderId;
        } else {
            try {
                Order order = orderService.findOrderById(id);
                order.setShip(ship);
                order.setOrderStatus(1);
                orderService.save(order);
                redirectAttributes.addFlashAttribute("success", "Xác nhận thành công!!");
                return "redirect:/admin/order";
            } catch (NumberFormatException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("erorder", "Tiền ship không hợp lệ");
                return "redirect:/admin/order-detail-confim/"+orderId;
            }
        }
    }


}
