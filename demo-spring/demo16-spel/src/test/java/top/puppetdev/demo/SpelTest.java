package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author puppet
 * @since 2022/12/29 13:18
 */
public class SpelTest {
    @Test
    public void testSpelSimpleUsage() {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("('hello' + ' world').concat(#end)");
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("end", "!");
        System.out.println(expression.getValue(context));
    }

    @Test
    public void testParserContext() {
        SpelExpressionParser parser = new SpelExpressionParser();
        ParserContext parserContext = new ParserContext() {
            @Override
            public boolean isTemplate() {
                return true;
            }

            @Override
            public String getExpressionPrefix() {
                return "#{";
            }

            @Override
            public String getExpressionSuffix() {
                return "}";
            }
        };
        String template = "#{'hello '}#{'world!'}";
        Expression expression = parser.parseExpression(template, parserContext);
        System.out.println(expression.getValue());
    }

    @Test
    public void testClassTypeExpression() {
        SpelExpressionParser parser = new SpelExpressionParser();
        // java.lang 包下的类访问
        Class<String> result1 = parser.parseExpression("T(String)").getValue(Class.class);
        System.out.println(result1);

        // 其他包类访问
        String exp = "T(top.puppetdev.demo.SpelTest)";
        Class<SpelTest> result2 = parser.parseExpression(exp).getValue(Class.class);
        System.out.println(result2 == SpelTest.class);

        // 类静态字段访问
        int result3 = parser.parseExpression("T(Integer).MAX_VALUE").getValue(int.class);
        System.out.println(result3 == Integer.MAX_VALUE);

        // 类静态方法调用
        int result4 = parser.parseExpression("T(Integer).parseInt('1')").getValue(int.class);
        System.out.println(result4);
    }
}
