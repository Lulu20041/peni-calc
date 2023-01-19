package com.example.PeniCalc.service;

import com.example.PeniCalc.entities.ClientSum;
import com.example.PeniCalc.entities.Peni;
import com.example.PeniCalc.entities.RefinancingRate;
import com.example.PeniCalc.repositories.PeniRepo;
import com.example.PeniCalc.repositories.RefinancingRateRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class PeniService {
    @Autowired
    PeniRepo peniRepo;
    @Autowired
    RefinancingRateRepo refinancingRateRepo;
    public void calculatePenies(HttpServletRequest request, ClientSumService clientSumService) throws Exception {
        //Дата возникновения налога
        String taxDateStr = request.getParameter("taxDate");
        //Сумма налога
        String taxSumStr = request.getParameter("taxSum");
        //Даа расчёта пени
        String peniDateStr = request.getParameter("peniDate");

        Map<Date, ClientSum> treeMap = new TreeMap<Date, ClientSum>();
        for (ClientSum clientSum : clientSumService.getSums()) {
            treeMap.put(clientSum.getDate(),clientSum);
        }

        double peni = 0;
        //Парсинг дат и суммы налогового обязательства
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date taxDate = formatter.parse(taxDateStr);
        double taxSum = Double.parseDouble(taxSumStr);
        Date peniDate = formatter.parse(peniDateStr);

        //Ставки рефинансирования
        List<RefinancingRate> refinancingRates = refinancingRateRepo.findAll();
        RefinancingRate refinancingRate = refinancingRates.iterator().next();
        double rate = refinancingRate.getRate();

        boolean payDateBeforeEndDate = false;

        //Записи пеней
        List<Peni> peniList = peniRepo.findAll();

        for (Map.Entry<Date, ClientSum> entry : treeMap.entrySet()) {
            Date payDate = entry.getKey();

            Peni payment = new Peni();
            payment.setTaxSum(taxSum);

            peni = taxSum;
            peni *= rate;

            payment.setRefinancingRate(refinancingRate);

            ClientSum ins = entry.getValue();

            double paymentSum = ins.getSum();
            taxSum -= paymentSum;
            if (peniDate.before(payDate)) {
                payDate = peniDate;
                payDateBeforeEndDate = true;
            }
            payment.setPeriodBegin(taxDate);
            payment.setPeriodEnd(payDate);

            long taxDateInMins = taxDate.getTime();
            long payDateInMins = payDate.getTime();

            long timeDiff = Math.abs(payDateInMins - taxDateInMins);

            int days = (int) TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
            payment.setAmountOfDays(days);

            peni *= days;
            peni /= (360 * 100);

            BigDecimal bd = new BigDecimal(peni);
            bd = bd.setScale(6, RoundingMode.HALF_UP);
            peni = bd.doubleValue();

            taxDate = new Date(taxDate.getYear(), taxDate.getMonth(),taxDate.getDate() + 1);

            payment.setPeniAmount(peni);

            if (payDateBeforeEndDate)
                break;

            peniRepo.save(payment);
        }
    }
    public List<Peni> getPenies() {
        return peniRepo.findAll();
    }
    public void deletePenies() {
        peniRepo.deleteAll();
    }
    public double peniSum() {
        List<Peni> penies = peniRepo.findAll();
        double sum = 0;
        for (Peni peni : penies) {
            sum += peni.getPeniAmount();
        }
        BigDecimal bd = new BigDecimal(sum);
        bd = bd.setScale(6, RoundingMode.HALF_UP);
        sum = bd.doubleValue();
        return sum;
    }
}
