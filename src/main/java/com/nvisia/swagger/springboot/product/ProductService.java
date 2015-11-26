package com.nvisia.swagger.springboot.product;

/**
 * Service interface for products.
 * 
 * @author Michael Hoffman, NVISIA
 *
 */
public interface ProductService {

   /**
    * Select product by ID
    * 
    * @param productId
    * @return
    * @throws Exception
    */
   Product getProduct(int productId) throws Exception;

}
