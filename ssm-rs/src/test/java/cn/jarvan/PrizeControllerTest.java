package cn.jarvan;

import cn.jarvan.controller.PrizeController;
import cn.jarvan.model.Prize;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;

/**
 * <b><code>PrizeControllerTest</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2018/7/23 9:07.
 *
 * @author liuruojing
 * @since ssm 0.1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config/spring-mvc.xml","classpath:spring-config/spring-service.xml","classpath:spring-config/spring-dao.xml"})
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class PrizeControllerTest {
   /**
   *
   *jdbcTemplate.
   *
   *@since ${PROJECT_NAME} 0.1.0
   */
    @Autowired
    JdbcTemplate jdbcTemplate;
    /**
    *
    *prizeController target of test.
    *
    *@since ${PROJECT_NAME} 0.1.0
    */
    @Autowired
    PrizeController prizeController;

    /**
     * before.
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    @Before
    public void setUp() throws Exception {
        jdbcTemplate.update("DELETE FROM tra_prizes");
    }

    /**
     * After.
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    @After
    public void tearDown() throws Exception {
        jdbcTemplate.update("DELETE FROM tra_prizes");
    }
    /**
     * Test method for  PrizeController#insert(Prize).
     *
     * @since ${PROJECT_NAME} 0.1.0
     */
    @Test
    public void testAdd() {
        Prize prize = new Prize();
        prize.setPrizeName("liuruojing");
        ResponseEntity response = prizeController.insert(prize);
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Map<String, Object> insertedPrize = jdbcTemplate.queryForMap("SELECT * FROM tra_prizes");
        Assert.assertEquals(prize.getPrizeName(), insertedPrize.get("prize_name"));
    }
    /**
     * 测试新增一个prize_name为null的奖品.
     *
     * @param
     * @return
     * @author liuruojing
     * @since ssm-rs 0.1.0
     */

    @Test
    public void testAddWithNull() {
        Prize prize = new Prize();
        prize.setPrizeName(null);
        ResponseEntity response = prizeController.insert(prize);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        List<Map<String, Object>> prizes = jdbcTemplate.queryForList("SELECT * FROM tra_prizes");
        Assert.assertEquals(0, prizes.size());
    }

    /**
     * Test method for PrizeController#delete(id).
     *
     * @since ssm-rs 0.1.0
     */
    @Test
    public void testDeletePrize() {
        jdbcTemplate.update("INSERT INTO tra_prizes(prize_id,prize_name,created_time) VALUES (1,'test',now())");
        ResponseEntity responseEntity = prizeController.delete(1L);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<Map<String, Object>> prizes = jdbcTemplate.queryForList("SELECT * FROM tra_prizes WHERE prize_id='1'");
        Assert.assertEquals(0, prizes.size());
    }

    /**
     * 测试删除一个不存在的Prize.
     * @author liuruojing
     * @since ssm-rs 0.1.0
     */
    @Test
    public void testDeletePrizeWithNoExistId() {
        jdbcTemplate.update("INSERT INTO tra_prizes(prize_id,prize_name,created_time) VALUES (1,'test',now())");
        ResponseEntity responseEntity = prizeController.delete(2L);
        Assert.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        List<Map<String, Object>> prizes = jdbcTemplate.queryForList("SELECT * FROM tra_prizes WHERE prize_id='1'");
        Assert.assertEquals(1, prizes.size());
    }
    /**
     * Test method for PrizeController#update(Prize).
     *  @since ssm-rs 0.1.0
     */
    @Test
    public void testUpdatePrize(){
        jdbcTemplate.update("INSERT INTO tra_prizes(prize_id,prize_name,created_time) VALUES (1,'test',now())");
        Prize prize=new Prize();
        prize.setPrizeId(1L);
        prize.setPrizeName("aotoman");
        ResponseEntity responseEntity = prizeController.update(prize);
        Assert.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Map<String, Object> updatePrize = jdbcTemplate.queryForMap("SELECT * FROM tra_prizes");
        Assert.assertEquals(prize.getPrizeName(),updatePrize.get("prize_name"));
    }

    /**
     * 测试修改一个不存在的prize奖品信息.
     *
     * @param
     * @return
     * @author liuruojing
     * @since ssm-rs 0.1.0
     */
    @Test
    public void testUpdateNoExitsPrize(){

        Prize prize=new Prize();
        prize.setPrizeId(2L);
        prize.setPrizeName("aotoman");
        ResponseEntity responseEntity = prizeController.update(prize);
        Assert.assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());


    }

    /**
     * 测试传入的Prize部分属性为null.
     *  @since ssm-rs 0.1.0
     */
    @Test
    public void testUpdatePrizeWithNull(){
        Prize prize=new Prize();
        prize.setPrizeId(null);
        ResponseEntity responseEntity = prizeController.update(prize);
        Assert.assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());

    }
    /**
     * Test method for PrizeController#get(id).
     *
     * @since ssm-rs 0.1.0
     */
    @Test
    public void testGetPrizeById() {
        jdbcTemplate.update("INSERT INTO tra_prizes (prize_id,prize_name,created_time) VALUES (1,'test',now())");
        ResponseEntity response = prizeController.get(1L);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Prize prize=(Prize)response.getBody();
        Assert.assertNotNull(prize);
        Assert.assertEquals(Long.valueOf(1), prize.getPrizeId());
        Assert.assertEquals("test", prize.getPrizeName());
    }
    /**
     * 测试获取一个不存在的prize
     *
     * @since ssm-rs 0.1.0
     */
    @Test
    public void testGetPrizeByNoExistId() {

        ResponseEntity response = prizeController.get(1L);
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Prize prize=(Prize)response.getBody();
        Assert.assertNull(prize);

    }

}
