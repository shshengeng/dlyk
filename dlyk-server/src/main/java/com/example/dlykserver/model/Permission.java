package com.example.dlykserver.model;

import java.io.Serializable;
import lombok.Data;

/**
 * 权限表
 * @TableName t_permission
 */
@Data
public class Permission implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String code;

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private String type;

    /**
     * 
     */
    private Integer parentId;

    /**
     * 
     */
    private Integer orderNo;

    /**
     * 
     */
    private String icon;

    private static final long serialVersionUID = 1L;
}