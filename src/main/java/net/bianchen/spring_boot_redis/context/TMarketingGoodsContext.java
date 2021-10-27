package net.bianchen.spring_boot_redis.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bianchen.spring_boot_redis.dao.TMarketingGoodsDao;
import net.bianchen.spring_boot_redis.model.bo.TMarketingGoods;

@Service
@Transactional
public class TMarketingGoodsContext {

	@Autowired
	TMarketingGoodsDao tMarketingGoodsDao;
	
	/**
	 * 录入商品信息.
	 * @param tMarketingGoods 商品信息.
	 */
	@Transactional
	public void saveMarketingGoods(final TMarketingGoods tMarketingGoods) {
		this.tMarketingGoodsDao.save(tMarketingGoods);
	}
}
