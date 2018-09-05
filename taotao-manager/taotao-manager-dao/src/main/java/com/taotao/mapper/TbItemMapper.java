package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

public interface TbItemMapper {
	/**
	 * 根据商品id查询指定商品信息
	 * @param itemId 商品id
	 * @return 查询指定商品信息
	 */
	TbItem getItemById(long itemId);
	/**
	 * 分页查询商品信息
	 * @return 每页显示的商品数量
	 */
	List<TbItem> getTbItem();
	/**
	 * 添加商品基本信息
	 * @param item 商品基本信息对象
	 */
	void addTbItem(TbItem item);
	/**
	 * 添加商品描述信息
	 * @param itemDesc 商品描述信息对象
	 */
	void addTbItemDesc(TbItemDesc itemDesc);
}