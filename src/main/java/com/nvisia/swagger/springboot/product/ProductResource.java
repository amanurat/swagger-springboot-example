package com.nvisia.swagger.springboot.product;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * Product resource
 * 
 * @author Michael Hoffman, NVISIA
 *
 */
@RestController
@RequestMapping("product")
public class ProductResource {

   @RequestMapping(value = "{productId}", method = RequestMethod.GET,
         produces = "application/json")
   @ResponseBody
   public ResponseEntity<Product> getProduct(@RequestParam("productId") int productId) {
      ProductService productService = new DefaultProductService();
      Product product = null;
      try {
         product = productService.getProduct(productId);
      } catch (Exception e) {
         return new ResponseEntity<Product>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
      if (product == null) {
         return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<Product>(product, HttpStatus.OK);
   }
}
