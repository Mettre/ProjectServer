package com.example.myproject.controller;

import cn.hutool.core.util.StrUtil;
import com.example.myproject.pojo.Adv;
import com.example.myproject.pojo.AdPosition;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.service.AdvService;
import com.example.myproject.service.AdvPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/shop")
@Api(description = "广告")
public class AdvController {

    @Autowired
    public AdvService advService;

    @Autowired
    public AdvPositionService advPositionService;

    @RequestMapping(value = "/adv/addAdv", method = RequestMethod.POST)
    @ApiOperation(value = "新增广告")
    public Result<Object> addAdv(@ModelAttribute Adv adv) {

        if (StrUtil.isBlank(adv.getAdName())) {
            return new ResultUtil<Object>().setErrorMsg("请输入广告名称");
        }

        if (adv.getAdPositionId() <= 0) {
            return new ResultUtil<Object>().setErrorMsg("请输入广告位id");
        } else {
            List<AdPosition> adPositions = advPositionService.findSpecificAdvPosition(adv.getAdPositionId(), null, null);
            if (adPositions == null || adPositions.size() == 0) {
                return new ResultUtil<Object>().setErrorMsg("广告位不存在");
            } else if (adPositions.size() > 1) {
                return new ResultUtil<Object>().setErrorMsg("搜索到多个重复广告位id");
            }
        }

        int result = advService.addAdv(adv);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("添加广告失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("添加广告成功");
    }

    @RequestMapping(value = "/adv/findSpecificAdv", method = RequestMethod.POST)
    @ApiOperation(value = "搜索广告")
    public Result<Object> findSpecificAdv(Long adPositionId, Long adId, String adName, String adPositionNo) {
        List<Adv> advsd = advService.findSpecificAdv(adPositionId, adId, adName, adPositionNo);
        return new ResultUtil<Object>().setData(advsd);
    }

    @RequestMapping(value = "/adv/addAdvRead", method = RequestMethod.POST)
    @ApiOperation(value = "广告阅读+1")
    public Result<Object> addAdvRead(@RequestParam Long adId) {

        Integer clickCount = 0;
        if (adId <= 0) {
            return new ResultUtil<Object>().setErrorMsg("请输入广告id");
        }
        List<Adv> adPositions = advService.findSpecificAdv(null, adId, null, null);
        if (adPositions == null || adPositions.size() == 0) {
            return new ResultUtil<Object>().setErrorMsg("广告位不存在");
        }
        clickCount = adPositions.get(0).getClickCount();
        clickCount++;
        int result = advService.addRead(adId, clickCount);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("添加阅读失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("添加阅读成功");
    }
}
