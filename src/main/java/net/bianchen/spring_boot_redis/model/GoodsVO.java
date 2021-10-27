package net.bianchen.spring_boot_redis.model;

import java.io.Serializable;
import java.util.UUID;

public class GoodsVO implements Serializable  {
	
	/**
	 * 记录唯一标识.
	 */
	private String gid = UUID.randomUUID().toString();
	
	/**
	 * 商品编号.
	 */
	private String goodscode;
	
	/**
	 * 商品名称.
	 */
	private String goodsname;

	/**
	 * @return the gid
	 */
	public String getGid() {
		return gid;
	}

	/**
	 * @param gid the gid to set
	 */
	public void setGid(String gid) {
		this.gid = gid;
	}

	/**
	 * @return the goodscode
	 */
	public String getGoodscode() {
		return goodscode;
	}

	/**
	 * @param goodscode the goodscode to set
	 */
	public void setGoodscode(String goodscode) {
		this.goodscode = goodscode;
	}

	/**
	 * @return the goodsname
	 */
	public String getGoodsname() {
		return goodsname;
	}

	/**
	 * @param goodsname the goodsname to set
	 */
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	
	
	
}
