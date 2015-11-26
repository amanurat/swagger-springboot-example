package com.nvisia.swagger.springboot.customer;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * Customer resource
 * 
 * @author Michael Hoffman, NVISIA
 *
 */
@RestController
@RequestMapping("customer")
public class CustomerResource {

   @RequestMapping(value = "{customerId}", method = RequestMethod.GET,
         produces = "application/json")
   @ResponseBody
   public ResponseEntity<Customer> getCustomer(
         @RequestParam("customerId") int customerId) {
      CustomerService customerService = new DefaultCustomerService();
      Customer customer = null;
      try {
         customer = customerService.getCustomer(customerId);
      } catch (Exception e) {
         return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
      if (customer == null) {
         return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<Customer>(customer, HttpStatus.OK);
   }

}
