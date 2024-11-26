package cn.ktl.lab.springmvc.rule;


import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

/**
 * @Author lin ho
 * Des: 规则引擎 规则定义
 */
@Rule(name = "Age check rule", description = "Check if person's age is > 18")
public class AgeRule {

    @Condition
    public boolean when(Facts facts) {
        Integer age = facts.get("age");
        return age > 18;
    }

    @Action
    public void then(Facts facts) {
        System.out.println("Person is over 18");
    }
}

