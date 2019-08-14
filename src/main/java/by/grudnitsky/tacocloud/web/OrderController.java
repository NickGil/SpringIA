package by.grudnitsky.tacocloud.web;

import by.grudnitsky.tacocloud.Order;
import by.grudnitsky.tacocloud.User;
import by.grudnitsky.tacocloud.data.OrderRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;


@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderProps props;

    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo,
                           OrderProps props) {
        this.orderRepo = orderRepo;
        this.props = props;
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user,
                            @ModelAttribute Order order) {
        if (order.getFullname() == null) {
            order.setFullname(user.getFullname());
        }
        if (order.getStreet() == null) {
            order.setStreet(user.getStreet());
        }
        if (order.getCity() == null) {
            order.setCity(user.getCity());
        }
        if (order.getState() == null) {
            order.setState(user.getState());
        }
        if (order.getZip() == null) {
            order.setZip(user.getZip());
        }

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors
            , SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);
        orderRepo.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal User user, Model model) {

        Pageable pageable = PageRequest.of(0, props.getPageSize());

        model.addAttribute("orders",
                orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));

        return "orderList";
    }

}
