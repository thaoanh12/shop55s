package com.example.shop55_be.reposistory;

import com.example.shop55_be.entity.OrderDetail;
import com.example.shop55_be.entity.Products;
import com.example.shop55_be.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductHomeRepo extends JpaRepository<Product, Long> {
    @Query(value = "SELECT products.*, MIN(product_detail.price) AS min_price\n" +
            "FROM products \n" +
            "JOIN product_detail ON products.id = product_detail.product_id\n" +
            "GROUP BY products.id", nativeQuery = true)
    Page<Product> homeProducAll(Pageable pageable);

    @Query(value = "select pr.* , MIN(prd.price) AS min_price , COUNT(*) AS quantity\n" +
            "from products pr join product_detail prd on prd.product_id = pr.id \n" +
            "join order_detail ord on ord.product_detail_id = prd.id\n" +
            "            GROUP BY ord.product_detail_id\n" +
            "            ORDER BY quantity DESC\n" +
            "            LIMIT 10;", nativeQuery = true)
    List<Product> bestSellingProduct();

    @Query(value = "            SELECT products.*, MIN(product_detail.price) AS min_price\n" +
            "            FROM products \n" +
            "            JOIN product_detail ON products.id = product_detail.product_id\n" +
            "            join categories on categories.id = products.category_id\n" +
            "            WHERE categories.category_name LIKE CONCAT('%', :productName, '%')\n" +
            "            GROUP BY products.id;", nativeQuery = true)
    Page<Product> productMensShirt(Pageable pageable, @Param("productName") String productName);

    @Query(value = "SELECT p.*, MIN(pd.price) AS min_price, SUM(od.quantity) AS total_quantity " +
            "FROM products p " +
            "JOIN product_detail pd ON p.id = pd.product_id " +
            "JOIN order_detail od ON pd.id = od.product_detail_id " +
            "GROUP BY p.id " +
            "ORDER BY total_quantity DESC",
            nativeQuery = true)
    Page<Product> getTu(Pageable pageable);


    @Query(value = "SELECT p.*, MIN(pd.price) AS min_price " +
            "FROM products p JOIN product_detail pd " +
            "ON p.id = pd.product_id GROUP BY p.id " +
            "ORDER BY p.create_date DESC", nativeQuery = true)
    Page<Product> getProductsOrderByCreateDateDesc(Pageable pageable);

    @Query(value = "SELECT products.*, MIN(product_detail.price) AS min_price\n" +
            "            FROM products  \n" +
            "            JOIN product_detail ON products.id = product_detail.product_id\n" +
            "            GROUP BY products.id\n" +
            "\t\t\torder by min_price asc", nativeQuery = true)
    Page<Product> getProductsHomeFilterAscending(Pageable pageable);

    @Query(value = "SELECT products.*, MIN(product_detail.price) AS min_price\n" +
            "            FROM products  \n" +
            "            JOIN product_detail ON products.id = product_detail.product_id\n" +
            "            GROUP BY products.id\n" +
            "\t\t\torder by min_price desc", nativeQuery = true)
    Page<Product> getProductsHomeFilterDecrease(Pageable pageable);

    @Query(value = "SELECT products.*, MIN(product_detail.price) AS min_price\n" +
            "            FROM products  \n" +
            "            JOIN product_detail ON products.id = product_detail.product_id\n" +
            " where product_name like '%Ao%'" +
            "            GROUP BY products.id\n" +
            "\t\t\torder by min_price asc", nativeQuery = true)
    Page<Product> getProductsHomeFilterAscendingShirt(Pageable pageable);

    @Query(value = "SELECT products.*, MIN(product_detail.price) AS min_price\n" +
            "            FROM products  \n" +
            "            JOIN product_detail ON products.id = product_detail.product_id\n" +
            " where product_name like '%Ao%'" +
            "            GROUP BY products.id\n" +
            "\t\t\torder by min_price desc", nativeQuery = true)
    Page<Product> getProductsHomeFilterDecreaseShirt(Pageable pageable);
}
