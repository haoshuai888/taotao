package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	/**
	 * 根据内容分类id查询得到指定内容信息
	 * @param categoryId 分类id
	 * @return json数据 包含总记录条数 和 每条记录的json数据
	 */
	EasyUIDataGridResult findContentAll(long categoryId);
	/**
	 * 添加一个cms内容信息
	 * @param tbContent
	 * @return 200则表示成功
	 */
	TaotaoResult addContent(TbContent tbContent);
	/**
	 * 根据分类id查询指定内容 
	 * @param categoryId 分类id
	 * @return 指定分类下面的所有内容
	 */
	List<TbContent> getTbContent(long categoryId);
}
