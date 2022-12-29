package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.expression.Expression;
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
}
