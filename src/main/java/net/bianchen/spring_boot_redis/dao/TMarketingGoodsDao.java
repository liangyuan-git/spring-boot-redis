package net.bianchen.spring_boot_redis.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.bianchen.spring_boot_redis.model.bo.TMarketingGoods;

@Repository
public interface TMarketingGoodsDao extends JpaRepository<TMarketingGoods, String> {

}
