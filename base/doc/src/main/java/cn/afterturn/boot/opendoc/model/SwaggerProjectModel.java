package cn.afterturn.boot.opendoc.model;

import lombok.Data;

/**
 * 项目级别文档描述
 * @author by jueyue on 19-1-16.
 */
@Data
public class SwaggerProjectModel {

    /**
     * location: "/v2/api-docs?group=系统"
     * name: "系统"
     * swaggerVersion: "2.0"
     * url: "/v2/api-docs?group=系统"
     */

    private String location;
    private String name;
    private String swaggerVersion;
    private String url;
    private String basePath;
    /**
     * 前缀
     */
    private String prefix;
}
