package com.example.digitalbanking;

import com.example.digitalbanking.Dtos.CurrentBankAccountDto;
import com.example.digitalbanking.Dtos.CustomerDto;
import com.example.digitalbanking.Dtos.SavingBankAccountDto;
import com.example.digitalbanking.Entities.BankAccount;
import com.example.digitalbanking.Entities.Customer;
import com.example.digitalbanking.Entities.Operation;
import com.example.digitalbanking.Entities.SavingAccount;
import com.example.digitalbanking.Enums.OperationType;
import com.example.digitalbanking.Exceptions.BalanceNotSufficientException;
import com.example.digitalbanking.Exceptions.BankAccountNotFoundException;
import com.example.digitalbanking.Exceptions.CustomerNotFoundException;
import com.example.digitalbanking.Services.BankAccountServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }

    @Bean
     CommandLineRunner start(BankAccountServices bankAccountServices){
        return args -> {
            Stream.of("abdelhadi","abderrahim","moustapha","belkacem","imrane","zakaria").forEach(name->{
                CustomerDto customer=new CustomerDto();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                bankAccountServices.saveCustomer(customer);
            });

            bankAccountServices.getAllCustomer().forEach(costumer->{
                try {
                    bankAccountServices.saveCurrentBankAccount(6000+Math.random()*1000,200,costumer.getId());
                    bankAccountServices.saveSavingBankAccount(90000+Math.random()*80000,5.5,costumer.getId());
                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });
           bankAccountServices.getAllBankAccount().forEach(bank->{
               String id;
               if(bank instanceof SavingBankAccountDto){
                   id=((SavingBankAccountDto) bank).getId();
               }else {
                   id=((CurrentBankAccountDto) bank).getId();
               }
               try {
                   for (int i = 0; i <10 ; i++) {
                       bankAccountServices.credit(id,6000+Math.random()+80000,"CREDIT");
                   }
               } catch (BankAccountNotFoundException e) {
                   e.printStackTrace();
               }
               try {
                   for (int i = 0; i <10 ; i++) {
                       bankAccountServices.debit(id,4000+Math.random()+500,"DEBIT");
                   }
               } catch (BankAccountNotFoundException | BalanceNotSufficientException e) {
                   e.printStackTrace();
               }
           });

        };

     }

}
