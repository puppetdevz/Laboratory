package top.puppetdev.demo.demo02;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author puppet
 * @since 2022-12-28 下午 11:00
 */
@Async
@Component
public class GoodsService {
    // 模拟获取商品基本信息，内部耗时 500 毫秒
    public Future<String> getGoodsInfo(long goodsId) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);
        return AsyncResult.forValue(String.format("the base info of goods %s", goodsId));
    }

    // 模拟获取商品描述信息，内部耗时 500 毫秒
    public Future<String> getGoodsDesc(long goodsId) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);
        return AsyncResult.forValue(String.format("the desc of goods %s", goodsId));
    }

    // 模拟获取商品评论信息列表，内部耗时 500 毫秒
    public Future<List<String>> getGoodsComments(long goodsId) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);
        List<String> comments = List.of("comments1", "comments2");
        return AsyncResult.forValue(comments);
    }
}
