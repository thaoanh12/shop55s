package com.example.shop55_be.service.impl;

import com.example.shop55_be.reposistory.StatisticsRepo;
import com.example.shop55_be.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsRepo statisticsRepo;
    @Override
    public Map<String,Double> monthlyRevenueInYear( int year) {
        Map<String,Double> monthlyRevenueInYear = new LinkedHashMap<>();
        int i = 1;
        while (i<=12){
            monthlyRevenueInYear.put("Tháng "+i,statisticsRepo.monthlyRevenueInYear(i,year));
            i++;
        }
        return monthlyRevenueInYear;
    }

    @Override
    public long getDailyInVoiCeCount() {
        return statisticsRepo.dailyInvoiceCount();
    }

    @Override
    public double getDailyRevenue() {
        return statisticsRepo.dailyRevenue();
    }

    @Override
    public double getMonthLyRevenue() {
        return statisticsRepo.monthlyRevenue();
    }

    @Override
    public double getYearLyRevenue() {
        return statisticsRepo.yearlyRevenue();
    }

}
