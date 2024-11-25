package com.example.shop55_be.service;

import java.util.Map;

public interface StatisticsService {
    Map<String,Double> monthlyRevenueInYear(int year);
    long getDailyInVoiCeCount();
    double getDailyRevenue();
    double getMonthLyRevenue();
    double getYearLyRevenue();
}
