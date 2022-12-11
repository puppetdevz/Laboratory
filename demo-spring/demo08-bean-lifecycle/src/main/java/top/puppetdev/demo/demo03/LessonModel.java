package top.puppetdev.demo.demo03;

import lombok.Data;

/**
 * @author puppet
 * @since 2022-11-12 上午 01:25
 */
@Data
public class LessonModel {
    // 课程名称
    private String name;
    // 课时
    private int lessonCount;
    // 描述信息
    private String description;
}
