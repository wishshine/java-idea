package com.lemeng.lecloud.netdisk.service;

import com.lemeng.lecloud.model.common.ResponseData;
import com.lemeng.lecloud.utils.server.ServerInteractionsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public interface FileDirService {

    /**
     * 获取文件服务列表
     * @return
     */
   ResponseData getFileServiceList();

}
