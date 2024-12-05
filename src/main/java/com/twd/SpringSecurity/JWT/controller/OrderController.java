package com.twd.SpringSecurity.JWT.controller;

import com.twd.SpringSecurity.JWT.dto.OrdersRequest;
import com.twd.SpringSecurity.JWT.entity.Orders;
import com.twd.SpringSecurity.JWT.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrdersRequest>> getListOrders(){
        List<OrdersRequest> ordersRequests = orderService.getListOders();
        return new ResponseEntity<>(ordersRequests, HttpStatus.OK);
    }

    @GetMapping("/useroder/{userId}")
    public ResponseEntity<List<OrdersRequest>> getOrdersByUserId(@PathVariable Long userId) {
        List<OrdersRequest> orders = orderService.getOrderByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody OrdersRequest ordersRequest){
        Orders orders = orderService.addOrders(ordersRequest);
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Orders> updateOrder(@PathVariable Long orderId, @RequestBody OrdersRequest ordersRequest){
        try {
            boolean updateOrderStatus = orderService.updateStatusOder(orderId, ordersRequest);
            if (updateOrderStatus){
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId){
        orderService.deleteOder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
