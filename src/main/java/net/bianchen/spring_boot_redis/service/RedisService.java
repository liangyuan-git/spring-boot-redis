package net.bianchen.spring_boot_redis.service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.bianchen.spring_boot_redis.context.Pub;
import net.bianchen.spring_boot_redis.context.TMarketingGoodsContext;
import net.bianchen.spring_boot_redis.model.CommandParameterVO;
import net.bianchen.spring_boot_redis.model.GoodsVO;
import net.bianchen.spring_boot_redis.model.bo.TMarketingGoods;
import net.bianchen.spring_boot_redis.tools.RedisUtil;

@RestController
@RequestMapping("/net/bianchen/spring_boot_redis/service/RedisService")
public class RedisService {
	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private Pub pub;
	
	@Autowired
	TMarketingGoodsContext tMarketingGoodsContext;

	@Transactional
	@Scheduled(fixedRate = 100)
	public void scheduledTest_1() {
		final String sKey = "10001_10001";
		final String taskid = (String) redisUtil.rightPopAndLeftPush(sKey, "tmp1-" + sKey);
		if (taskid == null || "".equals(taskid)) {
			return;
		}
		final String goods = redisUtil.rightPop("tmp1-" + sKey, 0, TimeUnit.MINUTES);
		redisUtil.leftPush("sold_goods", goods);
		// System.out.println("任务1："+goods);
	}

	@Transactional
	@Scheduled(fixedRate = 100)
	public void scheduledTest_2() {
		final String sKey = "10001_10001";
		final String taskid = (String) redisUtil.rightPopAndLeftPush(sKey, "tmp2-" + sKey);
		if (taskid == null || "".equals(taskid)) {
			return;
		}
		final String goods = redisUtil.rightPop("tmp2-" + sKey, 0, TimeUnit.MINUTES);
		redisUtil.leftPush("sold_goods", goods);
	}

	@Transactional
	@Scheduled(fixedRate = 100)
	public void scheduledTest_3() {
		final String sKey = "10001_10001";
		final String taskid = (String) redisUtil.rightPopAndLeftPush(sKey, "tmp-3" + sKey);
		if (taskid == null || "".equals(taskid)) {
			return;
		}
		final String goods = redisUtil.rightPop("tmp-3" + sKey, 0, TimeUnit.MINUTES);
		redisUtil.leftPush("sold_goods", goods);
	}
	
	@Transactional
	@Scheduled(fixedRate = 100)
	public void scheduledTest_4() {
		final String sKey = "sold_goods";
		final String taskid = (String) redisUtil.rightPopAndLeftPush(sKey, "tmp1-" + sKey);
		if (taskid == null || "".equals(taskid)) {
			return;
		}
		final String goods = redisUtil.rightPop("tmp1-" + sKey, 0, TimeUnit.MINUTES);
		final TMarketingGoods tMarketingGoods = JSONObject.parseObject(goods, TMarketingGoods.class);
		this.tMarketingGoodsContext.saveMarketingGoods(tMarketingGoods);
	}
	
	@Scheduled(fixedRate = 100)
	public void scheduledTest_5() {
		final String sKey = "sold_goods";
		final String taskid = (String) redisUtil.rightPopAndLeftPush(sKey, "tmp2-" + sKey);
		if (taskid == null || "".equals(taskid)) {
			return;
		}
		final String goods = redisUtil.rightPop("tmp2-" + sKey, 0, TimeUnit.MINUTES);
		final TMarketingGoods tMarketingGoods = JSONObject.parseObject(goods, TMarketingGoods.class);
		this.tMarketingGoodsContext.saveMarketingGoods(tMarketingGoods);
	}
	
	@Scheduled(fixedRate = 100)
	public void scheduledTest_6() {
		final String sKey = "sold_goods";
		final String taskid = (String) redisUtil.rightPopAndLeftPush(sKey, "tmp3-" + sKey);
		if (taskid == null || "".equals(taskid)) {
			return;
		}
		final String goods = redisUtil.rightPop("tmp3-" + sKey, 0, TimeUnit.MINUTES);
		final TMarketingGoods tMarketingGoods = JSONObject.parseObject(goods, TMarketingGoods.class);
		this.tMarketingGoodsContext.saveMarketingGoods(tMarketingGoods);
	}

	/**
	 * 普通缓存放入.
	 * 
	 * @param key
	 *            键.
	 * @param json
	 *            json内容.
	 */
	@ResponseBody
	@PostMapping("/setComment")
	public void setComment(@RequestBody final CommandParameterVO commandParameter) {
		redisUtil.set(commandParameter.getKey(), commandParameter.getValue());
	}

	/**
	 * 添加商品.
	 * 
	 * @param commandParameter
	 *            商品信息.
	 */
	@ResponseBody
	@PostMapping("/addGoods")
	public void addGoods(@RequestBody final CommandParameterVO commandParameter) {
		final TMarketingGoods tMarketingGoods = commandParameter.getValue();
		tMarketingGoods.setGid(UUID.randomUUID().toString());
		redisUtil.leftPush(commandParameter.getKey(), JSON.toJSONString(commandParameter.getValue()));
	}

	/**
	 * 销售商品.
	 * 
	 * @param commandParameter
	 *            商品信息.
	 */
	@ResponseBody
	@PostMapping("/sellGoods")
	public GoodsVO sellGoods(@RequestBody final CommandParameterVO commandParameter) {
		final String sKey = commandParameter.getKey();
		if (sKey == null || "".equals(sKey)) {
			return null;
		}
		final String taskid = (String) redisUtil.rightPopAndLeftPush(sKey, "tmp-" + sKey);
		final String goods = redisUtil.rightPop("tmp-" + sKey, 0, TimeUnit.MINUTES);
		return JSONObject.parseObject(goods, GoodsVO.class);
	}
	
	/**
	 * 录入商品信息.
	 * @param tMarketingGoods 商品信息.
	 */
	@ResponseBody
	@PostMapping("/saveMarketingGoods")
	public void saveMarketingGoods(@RequestBody final TMarketingGoods tMarketingGoods) {
		this.tMarketingGoodsContext.saveMarketingGoods(tMarketingGoods);
	}

}
