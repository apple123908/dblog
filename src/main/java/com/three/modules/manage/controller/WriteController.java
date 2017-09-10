package com.three.modules.manage.controller;

import com.three.common.annotation.LogAction;
import com.three.modules.manage.domain.Label;
import com.three.modules.manage.domain.Type;
import com.three.modules.manage.service.LabelService;
import com.three.modules.manage.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by three on 2017/9/8.
 */
@Controller
@RequestMapping(value = "/manage/write")
public class WriteController {

    @Autowired
    TypeService typeService;

    @Autowired
    LabelService labelService;

    /**
     * 去发布文章
     * @return
     */
    @RequestMapping(value = "/toWrite")
    @LogAction(name="访问 发布文章 页面")
    public String toWrite(Model model){
        //查询所有的类别和标签（select）
        List<Type> types=typeService.queryAll();

        List<Label> labels=labelService.queryAll();

        model.addAttribute("types",types);
        model.addAttribute("label",labels);
        return "modules/sys/manage/write";
    }
}
