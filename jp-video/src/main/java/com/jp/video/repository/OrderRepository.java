package com.jp.video.repository;

import com.jp.video.entity.OrderTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : xh
 * @date : Created in 2023/1/16 15:44
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderTbl,Integer> {
}
