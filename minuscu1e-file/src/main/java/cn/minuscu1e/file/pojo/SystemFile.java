package cn.minuscu1e.file.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName system_file
 */
@TableName(value = "system_file")
public class SystemFile implements Serializable {
    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     * 文件名
     */
    private String filename;

    /**
     * 系统文件名
     */
    private String systemFilename;
    /**
     * 文件类型
     */
    private String type;

    /**
     * 扩展名
     */
    private String ext;

    /**
     * 文件大小 byte
     */
    private Long size;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 用于 minio 存储
     */
    private String bucketName;

    /**
     * 是否删除
     * 0：no
     * 1：yes
     */
    private Boolean isDelete;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 创建用户编号
     */
    private String createUserId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 文件名
     */
    public String getFilename() {
        return filename;
    }

    /**
     * 文件名
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * 文件类型
     */
    public String getType() {
        return type;
    }

    /**
     * 文件类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 扩展名
     */
    public String getExt() {
        return ext;
    }

    /**
     * 扩展名
     */
    public void setExt(String ext) {
        this.ext = ext;
    }

    /**
     * 文件大小 byte
     */
    public Long getSize() {
        return size;
    }

    /**
     * 文件大小 byte
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * 文件路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 文件路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 用于 minio 存储
     */
    public String getBucketName() {
        return bucketName;
    }

    /**
     * 用于 minio 存储
     */
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * 是否删除
     * 0：no
     * 1：yes
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除
     * 0：no
     * 1：yes
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建用户编号
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建用户编号
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getSystemFilename() {
        return systemFilename;
    }

    public void setSystemFilename(String systemFilename) {
        this.systemFilename = systemFilename;
    }


    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SystemFile other = (SystemFile) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getFilename() == null ? other.getFilename() == null : this.getFilename().equals(other.getFilename()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getExt() == null ? other.getExt() == null : this.getExt().equals(other.getExt()))
                && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()))
                && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
                && (this.getBucketName() == null ? other.getBucketName() == null : this.getBucketName().equals(other.getBucketName()))
                && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFilename() == null) ? 0 : getFilename().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getExt() == null) ? 0 : getExt().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getBucketName() == null) ? 0 : getBucketName().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", filename=").append(filename);
        sb.append(", type=").append(type);
        sb.append(", ext=").append(ext);
        sb.append(", size=").append(size);
        sb.append(", path=").append(path);
        sb.append(", bucketName=").append(bucketName);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}