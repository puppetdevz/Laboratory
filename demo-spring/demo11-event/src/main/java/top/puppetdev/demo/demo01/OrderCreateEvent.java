package top.puppetdev.demo.demo01;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 订单创建事件
 * @author puppet
 * @since 2022-11-17 下午 01:23
 */
@Getter
@Setter
public class OrderCreateEvent extends ApplicationEvent {
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * @param source  事件源
     * @param orderId 订单id
     */
    public OrderCreateEvent(Object source, Long orderId) {
        super(source);
        this.orderId = orderId;
    }
}
