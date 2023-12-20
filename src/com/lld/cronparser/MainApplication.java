package com.lld.cronparser;

import com.lld.cronparser.model.CronExpression;

import java.util.ArrayList;

public class MainApplication {
    public static void main(String[] args) {
        CronExpression exp1 = CronExpression.Parse("1-30/5 0 1,15 * 1-5");
        System.out.println(exp1);

        CronExpression exp2 = CronExpression.Parse("5,10 0 2 * *");
        System.out.println(exp2);

        CronExpression exp3 = CronExpression.Parse("1 0 2 1 1");
        System.out.println(exp3);

//        CronExpression exp4 = CronExpression.Parse("1 0 2 1 15");
//        System.out.println(exp4);
    }
}
