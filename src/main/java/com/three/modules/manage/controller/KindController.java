package com.three.modules.manage.controller;

import com.three.common.domain.R;
import com.three.modules.manage.domain.Label;
import com.three.modules.manage.domain.Type;
import com.three.modules.manage.service.LabelService;
import com.three.modules.manage.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * type和label
 * Created by three on 2017/9/11.
 */
@Controller
@RequestMapping(value = "/manage/kind")
public class KindController {

    @Autowired
    TypeService typeService;

    @Autowired
    LabelService labelService;


    @RequestMapping(value = "/toKindIndex")
    public String toKindIndex(Model model){
        //查询 type
        List<Map<String,Object>> typeMap=typeService.queryArticleOfTypeAmount();
        //查询 label
        List<Map<String,Object>> labelMap=labelService.queryArticleOfLabelAmount();
        model.addAttribute("listType",typeMap);
        model.addAttribute("listLabel",labelMap);
        return "modules/sys/manage/kind";
    }

    /**
     * 删除type
     * @param type
     * @return
     */
    @RequestMapping(value = "/deleteTypeById")
    @ResponseBody
    public R deleteTypeById(@RequestBody Type type){
        typeService.deleteById(type.getId());
        return new R();
    }


    /**
     * 删除label
     * @param label
     * @return
     */
    @RequestMapping(value = "/deleteLabelById")
    @ResponseBody
    public R deleteLabelById(@RequestBody Label label){
        labelService.deleteById(label.getId());
        return new R();
    }

    /**
     * 编辑type
     * @param type
     * @return
     */
    @RequestMapping(value = "/updateType")
    @ResponseBody
    public R updateType(@RequestBody Type type){
        typeService.update(type);
        return new R();
    }

    /**
     * 编辑label
     * @param label
     * @return
     */
    @RequestMapping(value = "/updateLabel")
    @ResponseBody
    public R updateLabel(@RequestBody Label label){
        labelService.update(label);
        return new R();
    }

    /**
     * 新增type
     * @param type
     * @return
     */
    @RequestMapping(value = "/addType")
    @ResponseBody
    public R addType(@RequestBody Type type){
        typeService.addReturnId(type);
        Integer id = type.getId();
        return R.ok(id+"");
    }

    @RequestMapping(value = "/addLabel")
    @ResponseBody
    public R addLabel(@RequestBody Label label){
        labelService.addReturnId(label);
        Integer id = label.getId();
        return R.ok(id+"");
    }
}
