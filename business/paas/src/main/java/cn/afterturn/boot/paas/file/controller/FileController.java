package cn.afterturn.boot.paas.file.controller;

import cn.afterturn.boot.bussiness.response.Response;
import cn.afterturn.boot.bussiness.response.SuccessResponse;
import cn.afterturn.boot.facade.paas.file.IFileInfoFacade;
import cn.afterturn.boot.paas.file.service.IFileInfoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件相关服务
 *
 * @author jueyue on 19-11-14.
 */
@Slf4j
@Api("文件信息")
@RestController
@RequestMapping("/file")
public class FileController implements IFileInfoFacade {


    @Autowired
    private IFileInfoService fileInfoService;

    @Override
    @ApiOperation(value = "上传文件[Base64]")
    @PostMapping("/base64/upload")
    public Response upload(@RequestParam String file, @RequestParam String fileName, @RequestParam String tenantId) {
        return new SuccessResponse(fileInfoService.upload(file, tenantId));
    }


    @ApiImplicitParams({
            @ApiImplicitParam(value = "客户ID",name = "tenantId",required = true),
            @ApiImplicitParam(value = "上传文件",name = "file_data",required = true,allowMultiple = true,dataType = "MultipartFile")
    })
    @ApiOperation(value = "上传文件")
    @PostMapping(value = "/upload/{tenantId}", headers = "content-type=multipart/form-data")
    public Response upload(@RequestParam(value = "file_data", required = true) MultipartFile file, @PathVariable String tenantId) throws IOException {
        return new SuccessResponse(fileInfoService.upload(file.getInputStream(), tenantId));
    }


}