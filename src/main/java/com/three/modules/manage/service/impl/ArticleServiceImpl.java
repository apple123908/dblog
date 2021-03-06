package com.three.modules.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.three.common.domain.base.Page;
import com.three.modules.manage.dao.ArticleMapper;
import com.three.modules.manage.domain.Article;
import com.three.modules.manage.domain.Label;
import com.three.modules.manage.service.ArticleService;
import com.three.modules.sys.domain.SysUser;
import org.apache.commons.collections.map.HashedMap;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by three on 2017/9/7.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public int queryAmount() {

        return articleMapper.queryAmount();
    }

    @Override
    public List<Article> queryLast5Article() {
        return articleMapper.queryLast5Article();
    }


    @Transactional
    @Override
    public void add(Article article) {
        SysUser user = new SysUser();
        user.setId(1);
        article.setAuthor(user);
        //新增文章
        articleMapper.addReturnId(article);
        article.getId();
        Set<Label> labels = article.getLabels();
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        Iterator<Label> it = labels.iterator();
        while (it.hasNext()) {
            Label l = it.next();
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("article_id",article.getId());
            map.put("label_id",l.getId());
            list.add(map);
        }
        //类别
        articleMapper.addMiddle(list);

    }

    //分页查询
    @Override
    public PageInfo<Article> queryByPage(Page<Article> articlePage) {

        com.github.pagehelper.Page<Article> myPage = PageHelper.startPage(articlePage.getCurrentPage(),
                articlePage.getPageSize(),articlePage.getSort());
        articleMapper.queryAll();
        return myPage.toPageInfo();
    }

    @Override
    public Article queryById(int articleId) {
        return articleMapper.queryById(articleId);
    }


    @Transactional
    @Override
    public void edit(Article article) {
        //修改文章内容（基本信息，类型，图片）
        articleMapper.edit(article);
        //修改标签
        Integer id = article.getId();
        Set<Label> labels = article.getLabels();
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        for (Label l: labels) {
            Map<String,Object>map=new HashMap<String,Object>();
            map.put("article_id",id);
            map.put("label_id",l.getId());
            list.add(map);
        }
            //删除中间表原来已经存在的
        articleMapper.deleteMiddle(id);
            //再次新增
        articleMapper.addMiddle(list);
    }

    /**
     * 最新五篇，详细除了label，user
     * @return
     */
    @Override
    public List<Article> queryLast5ArticleIncludeImageType() {

        return articleMapper.queryLast5ArticleIncludeImageType();
    }

    @Override
    public List<Article> queryAllIncludeType() {
        return articleMapper.queryAllIncludeType();
    }

    @Override
    public Article queryAllById(Integer articleId) {
        return articleMapper.queryAllById(articleId);
    }

    @Override
    public List<Article> queryMostArticles5() {
        return articleMapper.queryMostArticles5();
    }

    /**
     * 获取上下2篇
     * @param articleId
     * @return
     */
    @Override
    public List<Article> queryNearBy(Integer articleId) {

        return articleMapper.queryNearBy(articleId);
    }

    @Override
    public void addHits(Integer articleId) {
        articleMapper.addHits(articleId);
    }

    @Override
    @Transactional
    public void deleteById(Integer articleId) {
        //删除article_lable中间表信息
        articleMapper.deleteMiddle(articleId);
        //在删除文章
        articleMapper.deleteById(articleId);
    }
}
