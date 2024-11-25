package com.example.shop55_be.reposistory;

import com.example.shop55_be.entity.Colors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorsRepo extends JpaRepository<Colors,Long> {
    @Query(value = "SELECT c FROM Colors c")
    Page<Colors> findColor(Pageable pageable);

    @Query("select color  from Colors color " +
            "where color.colorName like %:colorName% or " +
            "color.colorCode like %:colorCode%")
    Page<Colors> findCustomerByKeyWord(Pageable pageable,@Param("colorName")String colorName,
                                        @Param("colorCode")String colorCode);
    @Query(value = "select * from colors where (color_status = :status or :status is null)", nativeQuery = true)
    Page<Colors> findColorBystatus(Pageable pageable, @Param("status") Long status);

}
