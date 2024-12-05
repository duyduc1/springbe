package com.twd.SpringSecurity.JWT.service;

import com.twd.SpringSecurity.JWT.Mapper.OrdersMapper;
import com.twd.SpringSecurity.JWT.dto.OrdersRequest;
import com.twd.SpringSecurity.JWT.entity.Orders;
import com.twd.SpringSecurity.JWT.entity.OurUsers;
import com.twd.SpringSecurity.JWT.reponsitory.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DataUserService dataUserService;

    @Autowired
    private OrdersMapper ordersMapper;

    public List<OrdersRequest> getListOders() {
        List<Orders> orders = orderRepository.findAll();
        return orders.stream()
                .map(ordersMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<OrdersRequest> getOrderByUserId(Long userId) {
        List<Orders> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(ordersMapper::toDto)
                .collect(Collectors.toList());
    }

    public Orders addOrders(OrdersRequest ordersRequest) {
        Orders orders = new Orders();
        orders.setOrderId(ordersRequest.getOrderId());
        orders.setOrderNumber(ordersRequest.getOrderNumber());
        orders.setOrderStatus(ordersRequest.getOrderStatus());
        orders.setOrderPrice(ordersRequest.getOrderPrice());
        orders.setLocaltion(ordersRequest.getLocaltion());
        OurUsers ourUsers = dataUserService.findById(ordersRequest.getUserId());
        orders.setUser(ourUsers);
        return orderRepository.save(orders);
    }

    public boolean updateStatusOder(Long orderId, OrdersRequest ordersRequest) {
        Orders orders = orderRepository.findById(orderId).orElse(null);
        orders.setOrderStatus(ordersRequest.getOrderStatus());
        orderRepository.save(orders);
        return true;
    }

    public boolean deleteOder(Long orderId) {
        Orders orders = orderRepository.findById(orderId).orElse(null);
        orderRepository.delete(orders);
        return true;
    }

}
