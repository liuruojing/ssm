
package cn.jarvan.serviceImpl;


import cn.jarvan.dao.PrizeMapper;
import cn.jarvan.model.Prize;
import cn.jarvan.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * <b>Creation Time:</b> 2018年7月10日 下午4:00:10.
 *
 * @author liuruojing
 * @version 0.1.0
 * @since ssm-cs 0.1.0
 */
@Service
public final class PrizeServiceImpl implements PrizeService {
    /**
     * prizeMapper.
     * @since 0.1.0
     */
    @Autowired
    private PrizeMapper prizeMapper;

    /**
     * Add a prize.
     *
     * @param prize the prize to insert
     * @since ssm-cs 0.1.0
     */
    @Override
    public void insert(Prize prize) {
        prize.setPrizeId(null);
        prize.setCreatedTime(new Date());
        prizeMapper.insertSelective(prize);


    }

    /**
     * Delete a prize.
     *
     * @param id delete prize by id
     * @since ssm-cs 0.1.0
     */
    @Override
    public Boolean delete(final Long id) {

        int count = prizeMapper.deleteByPrimaryKey(id);
        return count > 0;
    }

    /**
     * update prize by id.
     * @param prize
     * @return Boolean
     * @since ssm-cs 0.1.0
     */
    @Override
    public Boolean update(Prize prize) {
        prize.setCreatedTime(null);
        int count = prizeMapper.updateByPrimaryKeySelective(prize);
        return count > 0;
    }

    /**
     * Get a prize.
     *
     * @param id get prize by id
     * @since ssm-cs 0.1.0
     * @return Prize
     */
    @Override
    public Prize get(final Long id) {
        return prizeMapper.selectByPrimaryKey(id);
    }
}
