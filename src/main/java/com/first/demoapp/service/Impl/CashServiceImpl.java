package com.first.demoapp.service.Impl;

import com.first.demoapp.Repository.CashRepository;
import com.first.demoapp.dto.CashBalanceDto;
import com.first.demoapp.entity.Cash;
import com.first.demoapp.service.CashService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class CashServiceImpl implements CashService {

    private CashRepository cashRepository;

    @Autowired
    public CashServiceImpl(CashRepository cashRepository) {
        this.cashRepository = cashRepository;
    }

    @Override
    public Cash save(Cash cash) {
        return cashRepository.save(cash);
    }

    public List<Cash> findAll() {
        return cashRepository.findAll();
    }

    @Override
    public CashBalanceDto getCashBalance() {
        List<Cash> cashList = cashRepository.findAll();
        //計算台幣與美金總額
        Integer totalTWD = 0;
        double totalUSD = 0.0;
        double currencyRate = fetchUSDTWDRate();
        for (Cash cash : cashList) {
            totalTWD = totalTWD + cash.getTaiwaneseDollars();
            totalUSD = totalUSD + cash.getUsDollars();
        }

        int total = (int) Math.floor(totalTWD + totalUSD * currencyRate);

        return new CashBalanceDto(totalTWD, totalUSD, currencyRate, total);
    }

    @Override
    public void deleteById(int id) {
        cashRepository.deleteById(id);
    }


    private double fetchUSDTWDRate() {
        String apiUrl = "https://tw.rter.info/capi.php";
        double currencyRate = 0.0;

        try {
            // 建立 URL 連接
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // 檢查回應碼
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {

                // 讀取回應內容
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "UTF-8"));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // 解析 JSON
                JsonObject jsonObject = JsonParser.parseString(response.toString()).getAsJsonObject();

                // 提取 USDTWD 匯率
                if (jsonObject.has("USDTWD")) {
                    JsonObject usdtwd = jsonObject.getAsJsonObject("USDTWD");
                    if (usdtwd.has("Exrate")) {
                        currencyRate = usdtwd.get("Exrate").getAsDouble();
                    }
                }

            } else {
                System.err.println("HTTP 請求失敗，回應碼: " + responseCode);
            }

            connection.disconnect();

        } catch (IOException e) {
            System.err.println("網路請求發生錯誤: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("JSON 解析發生錯誤: " + e.getMessage());
        }

        return currencyRate;
    }


}
