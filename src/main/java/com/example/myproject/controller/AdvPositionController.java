package com.example.myproject.controller;

import cn.hutool.core.util.StrUtil;
import com.example.myproject.pojo.*;
import com.example.myproject.service.AdvPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/shop")
@Api(description = "广告位")
public class AdvPositionController {

    @Autowired
    public AdvPositionService advPositionService;

    @RequestMapping(value = "/adv/addAdvPosition", method = RequestMethod.POST)
    @ApiOperation(value = "新增广告位")
    public Result<Object> addAdvPosition(@Validated @ModelAttribute AdPosition adPosition) {

        List<AdPosition> adPositions2 = advPositionService.findSpecificAdvPosition(null, null, adPosition.getAdPositionNo());
        if (adPositions2 != null && adPositions2.size() > 0) {
            return new ResultUtil<Object>().setErrorMsg("已存在的广告位编码");
        }
        int result = advPositionService.addAdvPosition(adPosition);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("添加广告位失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("添加广告位成功");
    }

    @RequestMapping(value = "/adv/findAllAdvPosition", method = RequestMethod.GET)
    @ApiOperation(value = "查找所有广告位--可显示的")
    public Result<Object> findAllAdvPosition() {

        List<AdPosition> adPositions = advPositionService.findAllAdvPosition();
        return new ResultUtil<Object>().setData(adPositions);
    }

    @RequestMapping(value = "/adv/findSpecificAdvPosition", method = RequestMethod.POST)
    @ApiOperation(value = "搜索广告位")
    public Result<Object> findSpecificAdvPosition(Long adPositionId, String adPositionName, String adPositionNo) {

        List<AdPosition> adPositions = advPositionService.findSpecificAdvPosition(adPositionId, adPositionName, adPositionNo);
        return new ResultUtil<Object>().setData(adPositions);
    }

    @RequestMapping(value = "/adv/updateAdvPosition", method = RequestMethod.POST)
    @ApiOperation(value = "修改广告位")
    public Result<Object> updateAdvPosition(@RequestParam Long adPositionId, @ModelAttribute AdPosition adPosition) {

        if (adPositionId <= 0) {
            return new ResultUtil<Object>().setErrorMsg("请输入广告位id");
        }
        List<AdPosition> adPositions = advPositionService.findSpecificAdvPosition(adPositionId, null, null);
        if (adPositions == null || adPositions.size() == 0) {
            return new ResultUtil<Object>().setErrorMsg("没有查到该广告位");
        }

        if (adPositions.size() > 1) {
            return new ResultUtil<Object>().setErrorMsg("广告位id重复");
        }
        if (StrUtil.isNotBlank(adPosition.getAdPositionNo())) {
            List<AdPosition> adPositions2 = advPositionService.findSpecificAdvPosition(null, null, adPosition.getAdPositionNo());
            if (adPositions2 != null && adPositions2.size() > 0) {
                if (adPositions2.get(0).getAdPositionId() != adPositionId) {
                    return new ResultUtil<Object>().setErrorMsg("广告位编码已存在");
                }
            }
        }

        adPosition.setAdPositionId(adPositionId);
        int result = advPositionService.updateAdvPosition(adPosition);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("修改广告位失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("修改广告位成功");
    }

    @RequestMapping(value = "/adv/deleteAdvPosition", method = RequestMethod.POST)
    @ApiOperation(value = "删除广告位")
    public Result<Object> deleteAdvPosition(Long adPositionId) {

        int result = advPositionService.deleteAdvPosition(adPositionId);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("删除广告位失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("删除广告位成功");
    }

}
