package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<EasyUITreeNode> getContentCategoryList(long parentId) {
		List<TbContentCategory> contentCategorys = tbContentCategoryMapper.getTbContentCategoryByParentId(parentId);
		List<EasyUITreeNode> result = new ArrayList<EasyUITreeNode>();
		for (TbContentCategory tbContentCategory : contentCategorys) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			result.add(node);
		}
		
		return result;
	}

	@Override
	public TaotaoResult addContentCategory(long parentId, String name) {
		TbContentCategory tbContentCategory = new TbContentCategory();
		//我们添加的节点一定是一个 子节点 但是他的父节点 是否是 子节点 不好说 所以要判断
		tbContentCategory.setIsParent(false);
		tbContentCategory.setName(name);
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setStatus(1);
		Date date = new Date();
		tbContentCategory.setCreated(date);
		tbContentCategory.setUpdated(date);
		
		//插入数据到数据库
		tbContentCategoryMapper.addTbContentCategory(tbContentCategory);
		
		/**
		 * 修改父级目录
		 *  页面传递过来的数据 有两个 parentId 和name  其中的parentId为 当前内容分类的id  name 就是当前内容分类的名称
		 *  select * form tbcontentcategory where parentId = id;
		 *  
		 */
		TbContentCategory category = tbContentCategoryMapper.getTbContentCategoryById(parentId);
		//则表示 当前节点为父节点
		if(!category.getIsParent()){
			category.setId(parentId);
			category.setIsParent(true);
			tbContentCategoryMapper.updateTbContentCategory(tbContentCategory);
		}
		
		
		//回传给页面显示 
		return TaotaoResult.ok(tbContentCategory);
	}

}
