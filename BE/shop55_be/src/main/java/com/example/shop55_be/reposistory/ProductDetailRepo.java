package com.example.shop55_be.reposistory;

import com.example.shop55_be.entity.ProductDetail;
import com.example.shop55_be.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDetailRepo extends JpaRepository<ProductDetail,Long> {
    @Query(value = "SELECT * FROM product_detail where product_id = :id ",nativeQuery = true)
    List<ProductDetail> findProductDetailByProductId(Long id) ;
    @Query("SELECT pd FROM ProductDetail pd " +
        "WHERE (:id IS NULL OR pd.products.id = :id) " +
        "AND (:sizeId IS NULL OR pd.size.id = :sizeId) " +
        "AND (:colorId IS NULL OR pd.color.id = :colorId) " +
        "AND (:productDetailCode IS NULL OR pd.productDetailCode LIKE CONCAT('%', :productDetailCode, '%')" +
            "or :productName IS NULL OR pd.products.productName LIKE CONCAT('%', :productName, '%'))")
    Page<ProductDetail> sortAndFilter(Pageable pageable,
                                  @Param("colorId") Long colorId,
                                  @Param("sizeId") Long sizeId,
                                  @Param("id") Long id,
                                  @Param("productName") String productName,
                                  @Param("productDetailCode") String productDetailCode);
    ProductDetail findByProductDetailCode(String code);
    @Query(value = "SELECT * FROM product_detail  WHERE product_id  = :id AND color_id  = :colorId AND size_id  = :sizeId",nativeQuery = true)
    ProductDetail findProductDetailBySizeAndAndColor(@Param("id") Long id, @Param("colorId") Long colorId, @Param("sizeId") Long sizeId);

    @Query(value = "SELECT pd.* " +
            "FROM product_detail pd " +
            "JOIN order_detail od ON od.product_detail_id = pd.id " +
            "GROUP BY pd.id " +
            "ORDER BY SUM(od.quantity) DESC, pd.id",
            countQuery = "SELECT COUNT(DISTINCT pd.id) " +
                    "FROM product_detail pd " +
                    "JOIN order_detail od ON od.product_detail_id = pd.id",
            nativeQuery = true)
    Page<ProductDetail> getBestSellingProductDetails(Pageable pageable);

    @Query("SELECT pd FROM ProductDetail pd JOIN pd.products p GROUP BY pd.id ORDER BY MAX(p.createDate) DESC")
    Page<ProductDetail> getProductNew(Pageable pageable);





}
