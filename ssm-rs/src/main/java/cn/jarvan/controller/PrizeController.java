package cn.jarvan.controller;

import cn.jarvan.model.Prize;
import cn.jarvan.service.PrizeService;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



/**
 * <b><code>PrizeController</code></b>
 * <p>
 * The controller of prize.
 * <p>
 * <b>Creation Time:</b> 2018年7月20日 下午15:00:10
 *
 * @author liuruojing
 * @version 0.1.0
 * @since ssm-rs 0.1.0
 */

@RestController
@RequestMapping("/v1.0")
@Api(tags = "奖品信息相关接口")
public class PrizeController {
    /**
     * 奖品管理服务层.
     * @since ssm-rs 0.1.0
     */
    @Autowired
    private PrizeService prizeService;
    /**
     * LOG.
     * @since ssm-rs 0.1.0
     */
    private static final Logger LOG = Logger.getLogger(PrizeController.class);

    /**
     * Add a prize.
     *
     * @param prize to add
     * @return ResponseEntity
     * @since ssm-rs 0.1.0
     */
    @PostMapping(value = "/prize", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加奖品", notes = "添加奖品")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "created success"),
            @ApiResponse(code = 500, message = "internal server error"),
            @ApiResponse(code = 400, message = "Bad Request")})
    public final ResponseEntity<Object> insert(@ApiParam(value = "奖品信息", required = true) @RequestBody Prize prize) {
        if (verify(prize)) {
            try {
                prizeService.insert(prize);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (Throwable e) {
                LOG.error("Failed to add a order!", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * delete prize by id.
     *
     * @param id to delete
     * @return ResponseEntity
     * @since ssm-rs 0.1.0
     */
    @DeleteMapping(value = "/prize/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据id删除奖品", notes = "删除奖品")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 500, message = "internal server error"),
            @ApiResponse(code = 404, message = "not found")})
    public final ResponseEntity<Object> delete(@ApiParam(value = "奖品id", required = true) @PathVariable(value = "id") Long id) {
        try {
            boolean bool = prizeService.delete(id);
            if (bool) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Throwable e) {
            LOG.error("failed to delete prize", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * update a prize by id.
     * @return ResponseEntity
     * @since ssm-rs 0.1.0
     */
    @PutMapping(value = "/prize", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "修改奖品信息", notes = "修改对应的奖品信息")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "successful request"),
            @ApiResponse(code = 500, message = "internal server error"),
            @ApiResponse(code = 404, message = "not found"),
            @ApiResponse(code = 400, message = "bad request")})
    public final ResponseEntity<?> update(@ApiParam(value = "奖品信息", required = true)@RequestBody Prize prize) {
        if (prize == null || prize.getPrizeId() == null || prize.getPrizeName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            try {
                boolean bool = prizeService.update(prize);
                if (bool) {
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (Throwable e) {
                LOG.error("failed to update prize", e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    /**
     * get a prize by id.
     *
     * @param id to query
     * @return ResponseEntity
     * @since ssm-rs 0.1.0
     */
    @GetMapping(value = "/prize/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "根据id查询奖品", notes = "查询奖品")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "successful request", response = Prize.class),
            @ApiResponse(code = 500, message = "internal server error"),
            @ApiResponse(code = 404, message = "not found")})
    public final ResponseEntity<Object> get(@ApiParam(value = "奖品id", required = true) @PathVariable(value = "id") Long id) {
        try {
            Prize prize = prizeService.get(id);
            if (prize == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(prize, HttpStatus.OK);
            }
        } catch (Throwable e) {
            LOG.error("faild to get prize", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    /**
     * 验证prize的必要参数是否为空.
     *
     * @param  prize
     * @return Boolean
     * @since ssm-rs 0.1.0
     */
    private Boolean verify(Prize prize) {
        return !(null == prize || prize.getPrizeName() == null);
    }

}

