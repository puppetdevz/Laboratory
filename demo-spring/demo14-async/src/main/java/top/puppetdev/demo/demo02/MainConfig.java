package top.puppetdev.demo.demo02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author puppet
 * @since 2022-12-28 下午 11:06
 */
@ComponentScan
@EnableAsync
public class MainConfig {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();
        GoodsService goodsService = context.getBean(GoodsService.class);

        long starTime = System.currentTimeMillis();
        System.out.println("开始获取商品的各种信息");

        long goodsId = 1L;
        Future<String> goodsInfoFuture = goodsService.getGoodsInfo(goodsId);
        Future<String> goodsDescFuture = goodsService.getGoodsDesc(goodsId);
        Future<List<String>> goodsCommentsFuture = goodsService.getGoodsComments(goodsId);

        System.out.println(goodsInfoFuture.get());
        System.out.println(goodsDescFuture.get());
        System.out.println(goodsCommentsFuture.get());

        System.out.println("商品信息获取完毕，总耗时(ms)：" + (System.currentTimeMillis() - starTime));

        // 休眠一下，防止程序退出
        TimeUnit.SECONDS.sleep(3);
    }
}
