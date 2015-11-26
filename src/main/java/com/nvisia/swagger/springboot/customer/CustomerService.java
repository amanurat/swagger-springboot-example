package com.nvisia.swagger.springboot.customer;

/**
 * Service interface for customer entity.
 * 
 * @author Michael Hoffman, NVISIA
 *
 */
public interface CustomerService {

   /**
    * Selects a customer by customer ID.
    * 
    * @param customerId
    * @return Customer
    * @throws Exception
    */
   Customer getCustomer(int customerId) throws Exception;

}
