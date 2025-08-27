package com.first.demoapp;

import com.first.demoapp.Repository.CashRepository;
import com.first.demoapp.Repository.StockRepository;
import com.first.demoapp.entity.Cash;
import com.first.demoapp.entity.Stock;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class DemoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoappApplication.class, args);
	}

	/**
	 * 在應用啟動時執行的 CommandLineRunner，用於驗證 Cash 實體。
	 * 這裡可以添加任何需要在應用啟動時執行的邏輯，例如初始化數據庫或驗證實體。
	 *
	 * @param cashRepository CashRepository 用於操作 Cash 實體
	 * @return CommandLineRunner 實例
	 */
//	@Bean
//	public CommandLineRunner validateCashEntity(CashRepository cashRepository) {
//		return args -> {
//			// 在這裡加入你的驗證邏輯，例如：
//			System.out.println("--- 啟動時執行現金實體驗證 ---");
//			//table cash (transaction_id integer primary key, taiwanese_dollars integer, us_dollars real, note varchar(30), date_info date);
//			// 例如，新增一筆測試資料到 cash 表中：
//			Cash savedCash = cashRepository.save(new Cash(null,1000, 3333.5, "Test Note444", LocalDate.now()));
//			// 這裡可以根據需要添加更多的
//			System.out.println("成功存入資料: " + savedCash.getTransactionId());
//		};
//	}

//	@Bean
//	public CommandLineRunner validateStockEntity(StockRepository stockRepository) {
//		return args -> {
//			// 在這裡加入你的驗證邏輯，例如：
//			System.out.println("--- 啟動時執行現金實體驗證 ---");
//			//table cash (transaction_id integer primary key, taiwanese_dollars integer, us_dollars real, note varchar(30), date_info date);
//			// 例如，新增一筆測試資料到 cash 表中：
//			Stock savedStock = stockRepository.save(new Stock(null, "0050", 3, 100.0, 100, 10, LocalDate.now()));
//			// 這裡可以根據需要添加更多的
//			System.out.println("成功存入資料: " + savedStock.getTransactionId());
//		};
//	}

}
