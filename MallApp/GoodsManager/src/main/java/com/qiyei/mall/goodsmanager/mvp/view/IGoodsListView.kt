package com.qiyei.mall.goodsmanager.mvp.view

import com.qiyei.framework.mvp.view.IBaseView
import com.qiyei.mall.goodsmanager.data.bean.Goods

/**
 * @author Created by qiyei2015 on 2018/10/6.
 * @version: 1.0
 * @email: 1273482124@qq.com
 * @description:
 */
interface IGoodsListView:IBaseView {

    /**
     * 商品列表回调
     */
    fun onGoodsListResult(list:MutableList<Goods>?)


}