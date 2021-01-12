package com.zxy.miaosha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxy.miaosha.dao.GoodsDao;
import com.zxy.miaosha.domain.MiaoshaGoods;
import com.zxy.miaosha.vo.GoodsVo;
/**
 * @ClassName GoodsService
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/12 20:18
 * @Version 1.0
 **/
@Service
public class GoodsService {

	@Autowired
	GoodsDao goodsDao;

	/**
	 * 查询商品列表
	 *
	 * @return
	 */
	public List<GoodsVo> listGoodsVo(){
		return goodsDao.listGoodsVo();
	}

	/**
	 * 根据id查询指定商品
	 *
	 * @return
	 */
	public GoodsVo getGoodsVoByGoodsId(long goodsId) {
		return goodsDao.getGoodsVoByGoodsId(goodsId);
	}

	/**
	 * 减少库存，每次减一
	 *
	 * @return
	 */
	public boolean reduceStock(GoodsVo goods) {
		MiaoshaGoods g = new MiaoshaGoods();
		g.setGoodsId(goods.getId());
		int ret = goodsDao.reduceStock(g);
		return ret > 0;
	}

	/**
	 * 重置
	 *
	 * @return
	 */
	public void resetStock(List<GoodsVo> goodsList) {
		for(GoodsVo goods : goodsList ) {
			MiaoshaGoods g = new MiaoshaGoods();
			g.setGoodsId(goods.getId());
			g.setStockCount(goods.getStockCount());
			goodsDao.resetStock(g);
		}
	}




}
