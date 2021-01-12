package com.zxy.miaosha.vo;

import java.util.Date;

import com.zxy.miaosha.domain.Goods;
/**
 * @ClassName GoodsVo
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2021/1/11 09:41
 * @Version 1.0
 **/
public class GoodsVo extends Goods{
	private Double miaoshaPrice;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;

	public Integer getStockCount() {
		return stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getMiaoshaPrice() {
		return miaoshaPrice;
	}
	public void setMiaoshaPrice(Double miaoshaPrice) {
		this.miaoshaPrice = miaoshaPrice;
	}
}
