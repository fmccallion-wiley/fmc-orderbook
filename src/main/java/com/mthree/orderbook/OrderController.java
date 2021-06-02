package com.mthree.orderbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller

public class OrderController {

    private boolean order66 = false;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PortfolioRepository portfolioRepository;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/order66")
    public String order66(Model model){
        order66 = !order66;
        model.addAttribute("order66", Boolean.toString(order66));
        return "order66";
    }

    @GetMapping("/portfolio")
    public String portfolioForm(Model model) {
        model.addAttribute("user", new User());
        return "portfolio_selection";
    }

    @PostMapping("/portfolio")
    public String submitportfolio(@ModelAttribute User user, Model model) {
        ArrayList<Portfolio> portfolios = portfolioRepository.getPortfolio(user.getUserid(), order66);
        model.addAttribute("portfolios", portfolios);
        return "portfolio";
    }


    @GetMapping("/buy")
    public String buyForm(Model model) {
        model.addAttribute("order", new Order());
        return "buy";
    }

    @PostMapping("/buy")
    public String submitBuy(@ModelAttribute Order order, Model model) {
        order.setType("buy");
        String id = order.getUserid()+System.currentTimeMillis();
        order.setOrderid(id);
        model.addAttribute("order", order);
        orderRepository.insertWithQuery(order);
        return "confirmation";
    }
    @GetMapping("/sell")
    public String sellForm(Model model) {
        model.addAttribute("order", new Order());
        return "sell";
    }
    @PostMapping("/sell")
    public String submitSell(@ModelAttribute Order order, Model model) {
        order.setType("sell");
        String id = order.getUserid()+System.currentTimeMillis();
        order.setOrderid(id);
        model.addAttribute("order", order);
        orderRepository.insertWithQuery(order);
        return "confirmation";
    }
    @GetMapping("/history")
    public String history(@RequestParam(name="userid", required=false, defaultValue="Admin") String symbol,Model model){
        ArrayList<Order> orders = new ArrayList<Order>();
        orders= orderRepository.getOrders(order66);
        model.addAttribute("orders", orders);
        return "history";
    }





}
