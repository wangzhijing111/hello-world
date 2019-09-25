package com.cyh.sfxt.entirty;


import com.cyh.sfxt.util.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Description //TODO 上传图片的实体类
 * @Param
 * @return
 * @Author wangzhijing
 * @Date  2019-09-25
**/
@Table(name = "photo")
public class Photo {
    @Id
    @KeySql(genId = UUIdGenId.class)
    @Column(name = "id")
    private String id;
    @Column(name = "case_id")
    private String caseId;
    @Column(name = "case_clazz")
    private String caseClazz;
    @Column(name = "images")
    private byte[] images;
    @Column(name = "size")
    private double size;
    @Column(name = "srcName")
    private String srcName;
    @Column(name = "unit")
    private String unit;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "is_delete")
    private String isDelete;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }


    public String getCaseClazz() {
        return caseClazz;
    }

    public void setCaseClazz(String caseClazz) {
        this.caseClazz = caseClazz;
    }


    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }


    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }


    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
}
