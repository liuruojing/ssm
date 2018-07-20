
package cn.jarvan.service;


import cn.jarvan.model.Prize;

/**
 *
 * The service of prize.
 *
 * <b>Creation Time:</b> 2018年7月10日 下午3:58:10
 *
 * @author liu ruojing
 * @version 0.1.0
 * @since ssm-cs 0.1.0
 */
public interface PrizeService {
  /**
   * Add a prize.
   *
   * @param prize the prize to insert.
   * @since ssm-cs 0.1.0
   */
  void insert(Prize prize);
  /**
   * Delete a prize.
   *
   * @param id delete prize by id.
   * @return Boolean
   * @since ssm-cs 0.1.0
   */
  Boolean delete(Long id);

  /**
   * update prize by id.
   * @param prize
   * @return Boolean
   * @since ssm-cs 0.1.0
   */
  Boolean update(Prize prize);
  /**
   * Get a prize.
   *
   * @param id get prize by id.
   * @return Prize
   * @since ssm-cs 0.1.0
   */
  Prize get(Long id);
}
