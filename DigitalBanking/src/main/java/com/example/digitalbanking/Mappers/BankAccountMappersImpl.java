package com.example.digitalbanking.Mappers;


import com.example.digitalbanking.Dtos.CurrentBankAccountDto;
import com.example.digitalbanking.Dtos.CustomerDto;
import com.example.digitalbanking.Dtos.OperationDto;
import com.example.digitalbanking.Dtos.SavingBankAccountDto;
import com.example.digitalbanking.Entities.CurrentAccount;
import com.example.digitalbanking.Entities.Customer;
import com.example.digitalbanking.Entities.Operation;
import com.example.digitalbanking.Entities.SavingAccount;
import com.sun.org.apache.xml.internal.security.utils.JavaUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.security.PublicKey;

@Service
public class BankAccountMappersImpl {

    public CustomerDto fromCustomer(Customer customer){
        CustomerDto customerDto=new CustomerDto();
        BeanUtils.copyProperties(customer,customerDto);
        return customerDto;
    }

    public Customer fromCustomerDto(CustomerDto customerDto){
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerDto,customer);
        return customer;
    }

    public SavingAccount fromSavingBankAccountDto(SavingBankAccountDto savingBankAccountDto){
        SavingAccount savingAccount=new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDto,savingAccount);
        savingAccount.setCustomer(fromCustomerDto(savingBankAccountDto.getCustomer()));
        return savingAccount;
    }

    public SavingBankAccountDto fromSavingBankAccount(SavingAccount savingAccount){
        SavingBankAccountDto savingBankAccountDto=new SavingBankAccountDto();
        BeanUtils.copyProperties(savingAccount,savingBankAccountDto);
        savingBankAccountDto.setCustomer(fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDto.setAccountType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDto;
    }

    public CurrentAccount fromCurrentBankAccountDto(CurrentBankAccountDto currentBankAccountDto){
        CurrentAccount currentAccount=new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDto,currentAccount);
        currentAccount.setCustomer(fromCustomerDto(currentBankAccountDto.getCustomer()));
        return currentAccount;
    }

    public CurrentBankAccountDto fromCurrentBankAccount(CurrentAccount currentAccount){
       CurrentBankAccountDto currentBankAccountDto=new CurrentBankAccountDto();
        BeanUtils.copyProperties(currentAccount,currentBankAccountDto);
        currentBankAccountDto.setCustomer(fromCustomer(currentAccount.getCustomer()));
        currentBankAccountDto.setAccountType(currentAccount.getClass().getSimpleName());
        return currentBankAccountDto;
    }

    public OperationDto fromOperation(Operation operation){
        OperationDto operationDto=new OperationDto();
        BeanUtils.copyProperties(operation,operationDto);
        return operationDto;
    }

}
