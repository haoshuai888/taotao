package com.taotao.mapper;

import java.util.List;

import com.taotao.pojo.TbContent;

public interface TbContentMapper {
	/**
	 * 查询指定内容分类下的所有内容
	 * @param categoryId 内容分类id
	 * @return 当前指定分类下的所有内容
	 */
	List<TbContent> findTbContentAll(long categoryId);
	/**
	 * 添加一个cms内容到数据库
	 * @param tbContent 需要添加的内容对象
	 */ 
	void addTbContent(TbContent tbContent);
}