package cn.ktl.lab.springmvc.rule;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

/**
 * @Author lin ho
 * Des: TODO
 */
public class RuleTest {
    public static void main(String[] args) {
        Facts facts = new Facts();
        facts.put("age",2);
        Rules rules = new Rules();
        rules.register(new AgeRule());

        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
    }
}
