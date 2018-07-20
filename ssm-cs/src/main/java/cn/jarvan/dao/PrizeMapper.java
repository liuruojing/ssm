package cn.jarvan.dao;

import cn.jarvan.model.Prize;
/**
 * <b><code>PrizeMapper</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2018/7/20 16:11.
 *
 * @author liuruojing
 * @since ssm 0.1.0
 */
public interface PrizeMapper {

    int deleteByPrimaryKey(Long prizeId);

    int insert(Prize record);

    int insertSelective(Prize record);

    Prize selectByPrimaryKey(Long prizeId);

    int updateByPrimaryKeySelective(Prize record);

    int updateByPrimaryKey(Prize record);
}