package com.lemeng.lecloud.netdisk.web;

import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.netdisk.service.FileDirService;
import com.lemeng.lecloud.utils.server.ServerInteractionsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/fileDir/")
public class FileDirController {

    private final static Logger LOGGER = LoggerFactory.getLogger(FileDirController.class);

    private FileDirService fileDirService;

    public ResponseData getFileServiceList(){
        try {
            return fileDirService.getFileServiceList();
        } catch (Exception e) {
            LOGGER.error("用户登录错误：" + e.getMessage(), e);
            return ServerInteractionsUtils.getFailReturn(null, e.getMessage());
        }
    }



}
