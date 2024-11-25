package com.example.shop55_be.reposistory;

import com.example.shop55_be.entity.ProductDetail;
import com.example.shop55_be.entity.Products;
import com.example.shop55_be.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface ProductRepo extends JpaRepository<Products,Long> {
    @Query(value = "select * from products ",nativeQuery = true)
    Page<Products> page(Pageable pageable);
    @Query(value = "select * from products where products.id not in (select product_detail.product_id\n" +
            "from discount_detail join product_detail on discount_detail.product_detail_id = product_detail.id)",nativeQuery = true)
    Page<Products> getNonDiscountedProducts(Pageable pageable);//sản phẩm không được giảm gía
    @Query("select pd from Product pd where (:productCode IS NULL OR pd.productCode LIKE CONCAT('%', :productCode, '%')" +
            " or :productName IS NULL OR pd.productName LIKE CONCAT('%', :productName, '%'))")
    Page<Products> searchProduct(Pageable pageable,@Param("productName") String productName,
                                 @Param("productCode") String productCode);
    @Query(value = "select * from products where product_name like \"%Ao%\"",nativeQuery = true)
    List<Products> productShirt();
    @Query(value = "select * from products where product_name like \"%Quan%\"",nativeQuery = true)
    List<Products> ProductPants();
    @Query("SELECT p FROM Product p")
    List<Products> getAllProducts();

    @Query(value = "SELECT p.*, MAX(pd.price) AS max_price " +
            "FROM products p " +
            "JOIN product_detail pd ON p.id = pd.product_id " +
            "JOIN order_detail od ON pd.id = od.product_detail_id " +
            "GROUP BY p.id " +
            "ORDER BY COUNT(od.product_detail_id) DESC", nativeQuery = true)
    Page<Products> getProduct(Pageable pageable);

}
