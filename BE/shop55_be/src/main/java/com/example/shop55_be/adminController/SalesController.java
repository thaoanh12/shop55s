package com.example.shop55_be.adminController;

import com.example.shop55_be.entity.Employee;
import com.example.shop55_be.entity.Order;
import com.example.shop55_be.entity.OrderDetail;
import com.example.shop55_be.entity.ProductDetail;
import com.example.shop55_be.model.CustomOrder;
import com.example.shop55_be.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
@RequestMapping("/admin")
public class SalesController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ColorsService colorsService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private OrderDetailService orderDetailService;

    private boolean isOpen = false;

    private String toAdminPages(Model model, HttpServletRequest request,
                                String contentFragment) {
        String code = (String) request.getSession().getAttribute("code");
        model.addAttribute("contentFragment", contentFragment);
        model.addAttribute("employee", employeeService.findByCode(code));
        return "admin/adminPage";
    }

    @GetMapping("/sales")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String salesPage(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p) {
        model.addAttribute("orders", orderService.getAll());
        model.addAttribute("customers", customerService.getAll());
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<ProductDetail> page = productDetailService.sortAndFilter(pageable, null, null, null, null, null);
        model.addAttribute("orderProduct", page);
        model.addAttribute("url", "/admin/sales");
        model.addAttribute("page", page);
        model.addAttribute("colors", colorsService.getAll());
        model.addAttribute("sizes", sizeService.getAll());
        return toAdminPages(model, request, "admin/sales/salesPage");
    }

    @GetMapping("/sales/order/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String selectedOrder(Model model,
                                HttpServletRequest request, @PathVariable("id") long id,
                                @RequestParam("p") Optional<Integer> p) {
        CustomOrder order = orderService.findCustomOrderById(id);
        model.addAttribute("orders", orderService.getAll());
        model.addAttribute("order", order);
        model.addAttribute("customers", customerService.getAll());
        List<OrderDetail> orderDetails = orderDetailService.findOrderDetailByOrderId(id);
        model.addAttribute("oderDetail", orderDetails);
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<ProductDetail> page = productDetailService.sortAndFilter(pageable, null, null, null, null, null);
        model.addAttribute("orderProduct", page);
        model.addAttribute("url", "/admin/sales/order/" + id);
        model.addAttribute("page", page);
        isOpen = p.isEmpty() ? false : true;
        model.addAttribute("isOpen", isOpen);
        model.addAttribute("orderId", id);
        model.addAttribute("colors", colorsService.getAll());
        model.addAttribute("sizes", sizeService.getAll());
        return toAdminPages(model, request, "admin/sales/salesPage");
    }

    @PostMapping("/sales/order-pay")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String selectedOrder(Model model,
                                HttpServletRequest request,
                                @ModelAttribute("order") CustomOrder customOrder,
                                @RequestParam("customerId") Long customerId,
                                @RequestParam("employeeId") Long employeeId) {
        Order order = orderService.findOrderById(customOrder.getId());
        order.setEmployee(employeeService.findById(employeeId));
        order.setCustomer(customerId == 0 ? null : customerService.details(customerId));
        order.setPayDate(new Date());
        order.setOrderStatus(1);
        orderService.save(order);
        return "redirect:/admin/sales";
    }

    @GetMapping("/sales/order-delete-product-detail/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String delete(@PathVariable("id") Long id,
                         @RequestParam("productCodes") String productCodes,
                         @RequestParam("quantity") int quantity
    ) {
        OrderDetail orderDetail = orderDetailService.findOrderDetailById(id);
        ProductDetail productDetail = productDetailService.findByCode(productCodes);
        ProductDetail productDetailUpdate = orderDetail.getProductDetail();
        productDetail.setQuantity(productDetailUpdate.getQuantity() + quantity);
        productDetailService.update(productDetailUpdate);
        long orderId = orderDetailService.findOrderDetailById(id).getOrder().getId();
        orderDetailService.deleteById(id);
        return "redirect:/admin/sales/order/" + orderId;
    }

    @PostMapping("/sales/order-update-product-detail/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String update(@PathVariable("id") Long id,
                         @RequestParam("quantity") long quantity,
                         @RequestParam("code") String code, Model model, RedirectAttributes redirectAttributes
    ) {
        OrderDetail orderDetail = orderDetailService.findOrderDetailById(id);
        ProductDetail productDetail = productDetailService.findByCode(code);
        long orderId = orderDetail.getOrder().getId();
        long sumk = quantity - orderDetail.getQuantity();
        if (quantity < 1) {
            redirectAttributes.addFlashAttribute("hihi1", "số lượng phải lớn hơn 0 !!");
            return "redirect:/admin/sales/order/" + orderId;
        } else if (sumk > productDetail.getQuantity()) {
            redirectAttributes.addFlashAttribute("hihi2", "số lượng sản phẩm không đủ 1!");
            return "redirect:/admin/sales/order/" + orderId;
        } else {
            if (quantity > orderDetail.getQuantity()) {
                long sum = quantity - orderDetail.getQuantity();
                int sums = (int) sum;
                ProductDetail productDetailUpdate = orderDetail.getProductDetail();
                productDetail.setQuantity(productDetailUpdate.getQuantity() - sums);
                productDetailService.update(productDetailUpdate);
            } else if (quantity < orderDetail.getQuantity()) {
                long sum = orderDetail.getQuantity() - quantity;
                int sums = (int) sum;
                ProductDetail productDetailUpdate = orderDetail.getProductDetail();
                productDetail.setQuantity(productDetailUpdate.getQuantity() + sums);
                productDetailService.update(productDetailUpdate);
            }
            orderDetail.setQuantity(quantity);
            orderDetail.setTotalAmount(quantity * orderDetail.getProductDetail().getPrice());
            orderDetailService.save(orderDetail);
            redirectAttributes.addFlashAttribute("hihi", "sửa số lượng thành công ");

        }
        return "redirect:/admin/sales/order/" + orderId;
    }

    @GetMapping("/sales/order-add")
    @PreAuthorize("hasRole('ADMIN')")
    public String salesSave(Model model, HttpServletRequest request) {
        String code = (String) request.getSession().getAttribute("code");
        Employee employee = employeeService.findByCode(code);
        Order order = Order.builder()
                .orderCode("HD" + new Random().nextInt(100000))
                .createDate(new Date())
                .employee(employee)
                .build();
        orderService.save(order);
        return "redirect:/admin/sales";
    }

    @GetMapping("/sales/order-remove/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable("id") Long id,

                         Model model) {
        orderService.deleteById(id);
        return "redirect:/admin/sales";
    }

    @GetMapping("/sales/order-detail-create/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String createOrderDtail(Model model,
                                   HttpServletRequest request,
                                   @PathVariable("id") long id,
                                   @RequestParam(value = "code") String code, RedirectAttributes redirectAttributes
    ) {
        Order order = orderService.findOrderById(id);
        ProductDetail productDetail = productDetailService.findByCode(code);
        model.addAttribute("quantitycontroller", productDetail.getQuantity());
        OrderDetail orderDetail = orderDetailService.getOrderDetailByOrderIDAndProductDetailId(id, code);
        if (productDetail.getQuantity() == 0) {
            redirectAttributes.addFlashAttribute("erCreateProduct", "sản phẩm đã hết !");
            return "redirect:/admin/sales/order/" + id;
        } else if (orderDetail == null) {

            orderDetailService.save(OrderDetail.builder()
                    .order(order)
                    .productDetail(productDetail)
                    .createDate(new Date())
                    .quantity(1)
                    .totalAmount(productDetail.getPrice() * 1)
                    .build());
        } else {
            orderDetail.setQuantity(orderDetail.getQuantity() + 1);
            orderDetail.setTotalAmount(orderDetail.getQuantity() * orderDetail.getProductDetail().getPrice());
            ProductDetail productDetailUpdate = orderDetail.getProductDetail();
            productDetail.setQuantity(productDetailUpdate.getQuantity() - 1);
            productDetailService.update(productDetailUpdate);
            orderDetailService.save(orderDetail);
        }

        return "redirect:/admin/sales/order/" + id;
    }

    @GetMapping("/sales/isClick")
    @ResponseBody
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public void g(@RequestParam("isOpen") boolean data) {
        isOpen = data;
    }

    @GetMapping("/admin/sales/product-search/{Id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public ResponseEntity<Page<ProductDetail>> searchProduct(
            Model model,
            @PathVariable("id") Long idOrder ,
            @RequestParam("p") Optional<Integer> p ,
            @RequestParam("colorid") Long colorId,
            @RequestParam("sizeId") Long sizeId,
            @RequestParam("kywordText") String  kywordText
    ) {
        // Your logic to search for products based on color, size, and keyword
        // Thực hiện tìm kiếm sản phẩm dựa trên màu sắc, kích cỡ và từ khóa

        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<ProductDetail> page = productDetailService.sortAndFilter(pageable, colorId, sizeId,
                null, kywordText, kywordText);
        model.addAttribute("orderProduct", page);
        model.addAttribute("url", "/admin/sales");
        model.addAttribute("page", page);
        model.addAttribute("colors", colorsService.getAll());
        model.addAttribute("sizes", sizeService.getAll());

        return new ResponseEntity<>(page, HttpStatus.OK);
    }
//    @GetMapping("/sales/product-search/{id}")
//    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
//    public String productSearch(
//            Model model,
//            @PathVariable("id") Long idOrder ,
//            @RequestParam("p") Optional<Integer> p ,
//            @RequestParam("colorid") Long colorId,
//            @RequestParam("sizeId") Long sizeId,
//            @RequestParam("kywordText") String  kywordText
//    ) {
//        Pageable pageable = PageRequest.of(p.orElse(0), 5);
//        Page<ProductDetail> page = productDetailService.sortAndFilter(pageable, colorId, sizeId,
//                null, kywordText, kywordText);
//        model.addAttribute("orderProduct", page);
//        model.addAttribute("url", "/admin/sales");
//        model.addAttribute("page", page);
//        model.addAttribute("colors", colorsService.getAll());
//        model.addAttribute("sizes", sizeService.getAll());
//        return "redirect:/admin/sales/order/" + idOrder;
//    }
}

