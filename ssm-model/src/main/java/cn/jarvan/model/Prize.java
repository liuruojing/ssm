package cn.jarvan.model;

import java.util.Date;

/**
 * <b><code>Prize</code></b>
 * <p>
 * The model of prize.
 * <p>
 * <b>Creation Time:</b> 2018年7月10日 下午12:00:10
 *
 * @author liuruojing
 * @version 0.1.0
 * @since ssm-model 0.1.0
 */
public class Prize {
    /**
     * prizeId 主键 唯一标识.
     * @since ssm-cs 0.1.0
     */
    private Long prizeId;
    /**
     * prizeName 奖品名称.
     * @since ssm-cs 0.1.0
     */
    private String prizeName;
    /**
     * createdTime 创建时间.
     * @since ssm-cs 0.1.0
     */
    private Date createdTime;
    /**
     * get prizeId.
     * @return prizeId
     * @since ssm-cs 0.1.0
     */
    public final Long getPrizeId() {
        return prizeId;
    }
    /**
     * set prizeId.
     * @param prizeId
     * @since ssm-cs 0.1.0
     */
    public final void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }
    /**
     * get prizeName.
     * @return prizeName
     * @since ssm-cs 0.1.0
     */
    public final String getPrizeName() {
        return prizeName;
    }
    /**
     * set prizeName.
     * @param prizeName
     * @since ssm-cs 0.1.0
     */
    public final void setPrizeName(String prizeName) {
        this.prizeName = prizeName == null ? null : prizeName.trim();
    }
    /**
     * get createdTime.
     * @return createdTime
     * @since ssm-cs 0.1.0
     */
    public final Date getCreatedTime() {
        return createdTime;
    }
    /**
     * set createdTime.
     * @param createdTime
     * @since ssm-cs 0.1.0
     */
    public final void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}