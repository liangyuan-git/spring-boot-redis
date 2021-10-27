package net.bianchen.spring_boot_redis.model.bo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_marketing_goods database table.
 * 
 */
@Entity
@Table(name="t_marketing_goods")
//@NamedQuery(name="TMarketingGoods.findAll", query="SELECT t FROM TMarketingGoods t")
public class TMarketingGoods implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String gid;

	private String biz;

	/**
	 * 商品编号.
	 */
	private String gcode;

	/**
	 * 商品名称.
	 */
	private String gname;

	public TMarketingGoods() {
	}

	public String getGid() {
		return this.gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getBiz() {
		return this.biz;
	}

	public void setBiz(String biz) {
		this.biz = biz;
	}

	public String getGcode() {
		return this.gcode;
	}

	public void setGcode(String gcode) {
		this.gcode = gcode;
	}

	public String getGname() {
		return this.gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

}