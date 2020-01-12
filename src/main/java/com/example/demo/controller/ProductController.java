package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.ProductSizeEntity;
import com.example.demo.repository.IProductSizeRepository;
import com.example.demo.request.CreateProductRequest;
import com.example.demo.request.UpdateProductRequest;
import com.example.demo.response.CreateProductResponse;
import com.example.demo.response.UpdateProductResponse;
import com.example.demo.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@Api(value = "Products APIs")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private IProductSizeRepository productSizeRepository;

    @ApiOperation(value = "Get list of products", response = ProductDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 404, message="Not found"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @GetMapping()
    public ResponseEntity<?> getAllProducts(){
        List<ProductDto> productDtoList = productService.getAllProducts();
        if(productDtoList == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        else {
            return ResponseEntity.ok(productDtoList);
        }

    }
    @ApiOperation(value = "create a product", response = ProductDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Bad request"),
            @ApiResponse(code = 404, message="Not found"),
            @ApiResponse(code = 500, message="Internal Server Error"),
    })
    @PostMapping()
    public ResponseEntity<?>createProduct(@RequestBody @Valid CreateProductRequest request){

        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(request, productDto);

        ProductDto createdProduct = productService.createProduct(productDto);

        if (null == createdProduct) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed");

        } else {
            CreateProductResponse returnValue = new CreateProductResponse();
            BeanUtils.copyProperties(createdProduct, returnValue);

            return ResponseEntity.status(HttpStatus.CREATED).body("Created");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Delete completely");
    }

    @GetMapping("/{nameProduct}")
    public ResponseEntity<?> getProductByName(@PathVariable String nameProduct){
        ProductDto productDto = productService.getProductByName(nameProduct);
        if(productDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" not found product");
        }
        else {
            return ResponseEntity.ok(productDto);
        }
    }

    @PutMapping("/{nameProduct}")
    public ResponseEntity<?> updateProduct(@RequestBody @Valid UpdateProductRequest request,@PathVariable String nameProduct){
        UpdateProductResponse updateProductResponse = productService.updateProduct(request,nameProduct);
        if(updateProductResponse.getMessage()=="Update product failed"){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updateProductResponse);
        }
        else {
            return ResponseEntity.ok(updateProductResponse);
        }
    }

}
