package com.lld.cronparser;

import com.lld.cronparser.main.CronExpression;

public class MainApplication {
    public static void main(String[] args) {

        CronExpression exp4 = CronExpression.Parse("*/15 0 1,15 * 1-5 /usr/bin/find");
        System.out.println(exp4);
    }
}
