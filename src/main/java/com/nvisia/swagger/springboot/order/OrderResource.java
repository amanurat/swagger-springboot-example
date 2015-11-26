package com.nvisia.swagger.springboot.order;

import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * Order resource
 * 
 * @author Michael Hoffman, NVISIA
 *
 */
@RestController
@RequestMapping("order")
public class OrderResource {

   @RequestMapping(value = "{orderId}", method = RequestMethod.GET,
         produces = "application/json")
   @ResponseBody
   public ResponseEntity<Order> getOrder(@RequestParam("orderId") int orderId) {
      OrderService orderService = new DefaultOrderService();
      Order order = null;
      try {
         order = orderService.getOrder(orderId);
      } catch (Exception e) {
         return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
      if (order == null) {
         return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<Order>(order, HttpStatus.OK);
   }

   @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
   @ResponseBody
   public ResponseEntity<List<Order>> getOrders() {
      OrderService orderService = new DefaultOrderService();
      List<Order> orders = null;
      try {
         orders = orderService.getAllOrders();
      } catch (Exception e) {
         return new ResponseEntity<List<Order>>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
      if (orders == null) {
         return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
   }

   @RequestMapping(value = "submitOrder", method = RequestMethod.POST,
         consumes = { "application/json", "application/x-www-form-urlencoded" },
         produces = "application/json")
   @ResponseBody
   public ResponseEntity<Order> submitOrder(OrderForm orderForm) {
      if (orderForm.getOrderItems() == null || orderForm.getOrderItems().isEmpty()) {
         return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
      }

      OrderService orderService = new DefaultOrderService();
      Order order = null;
      try {
         order = orderService.submitOrder(orderForm.getCustomerId(),
               orderForm.getOrderItems(), new Date(System.currentTimeMillis()));
      } catch (Exception e) {
         return new ResponseEntity<Order>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

      if (order == null) {
         return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<Order>(order, HttpStatus.OK);
   }

}
