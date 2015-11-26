package com.nvisia.swagger.springboot.config;

import static com.google.common.collect.Lists.*;
import static springfox.documentation.schema.AlternateTypeRules.*;

import java.time.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.*;

import com.fasterxml.classmate.*;

import springfox.documentation.builders.*;
import springfox.documentation.schema.*;
import springfox.documentation.spi.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger2.annotations.*;

/**
 * Main class for the Spring Boot application.
 * 
 * @author Michael Hoffman, NVISIA
 *
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "com.nvisia.swagger.springboot")
public class Application {

   @Autowired
   private TypeResolver typeResolver;

   public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
   }

   @Bean
   public Docket customerApi() {
      return new Docket(DocumentationType.SWAGGER_2).groupName("customer").select()
            .apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build()
            .pathMapping("/customer").directModelSubstitute(LocalDate.class, String.class)
            .genericModelSubstitutes(ResponseEntity.class)
            .alternateTypeRules(
                  newRule(
                        typeResolver.resolve(DeferredResult.class,
                              typeResolver.resolve(ResponseEntity.class,
                                    WildcardType.class)),
                  typeResolver.resolve(WildcardType.class)))
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET,
                  newArrayList(new ResponseMessageBuilder().code(500)
                        .message("A generic error has occurred")
                        .responseModel(new ModelRef("Error")).build()))
            .enableUrlTemplating(false);
   }

   @Bean
   public Docket productApi() {
      return new Docket(DocumentationType.SWAGGER_2).groupName("product").select()
            .apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build()
            .pathMapping("/product").directModelSubstitute(LocalDate.class, String.class)
            .genericModelSubstitutes(ResponseEntity.class)
            .alternateTypeRules(
                  newRule(
                        typeResolver.resolve(DeferredResult.class,
                              typeResolver.resolve(ResponseEntity.class,
                                    WildcardType.class)),
                  typeResolver.resolve(WildcardType.class)))
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET,
                  newArrayList(new ResponseMessageBuilder().code(500)
                        .message("A generic error has occurred")
                        .responseModel(new ModelRef("Error")).build()))
            .enableUrlTemplating(false);
   }

   @Bean
   public Docket orderApi() {
      return new Docket(DocumentationType.SWAGGER_2).groupName("order").select()
            .apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build()
            .pathMapping("/order").directModelSubstitute(LocalDate.class, String.class)
            .genericModelSubstitutes(ResponseEntity.class)
            .alternateTypeRules(
                  newRule(
                        typeResolver.resolve(DeferredResult.class,
                              typeResolver.resolve(ResponseEntity.class,
                                    WildcardType.class)),
                  typeResolver.resolve(WildcardType.class)))
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET,
                  newArrayList(new ResponseMessageBuilder().code(500)
                        .message("A generic error has occurred")
                        .responseModel(new ModelRef("Error")).build()))
            .enableUrlTemplating(false);
   }

}
