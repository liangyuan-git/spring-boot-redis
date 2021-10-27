package net.bianchen.spring_boot_redis.model;

import java.io.Serializable;

import net.bianchen.spring_boot_redis.model.bo.TMarketingGoods;

public class CommandParameterVO implements Serializable {
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 存储键.
	 */
	private String key;
	
	/**
	 * 时间.
	 */
	private long time;
	
	/**
	 * 值.
	 */
	private TMarketingGoods value;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * @return the value
	 */
	public TMarketingGoods getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(TMarketingGoods value) {
		this.value = value;
	}
	
}
