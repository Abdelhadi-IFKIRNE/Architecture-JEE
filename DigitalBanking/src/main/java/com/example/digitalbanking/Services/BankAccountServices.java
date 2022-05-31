package com.example.digitalbanking.Services;

import com.example.digitalbanking.Dtos.BankAccountDto;
import com.example.digitalbanking.Dtos.CustomerDto;
import com.example.digitalbanking.Dtos.InfosHistoryAccountDto;
import com.example.digitalbanking.Dtos.OperationDto;
import com.example.digitalbanking.Entities.BankAccount;
import com.example.digitalbanking.Entities.Customer;
import com.example.digitalbanking.Exceptions.BalanceNotSufficientException;
import com.example.digitalbanking.Exceptions.BankAccountNotFoundException;
import com.example.digitalbanking.Exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountServices {
    CustomerDto saveCustomer(CustomerDto customer);

    BankAccount saveCurrentBankAccount(double initBalance, double overDraft, Long idCustomer) throws CustomerNotFoundException;

    BankAccount saveSavingBankAccount(double initBalance, double interesetRate, Long idCustomer) throws CustomerNotFoundException;

    List<CustomerDto> getAllCustomer();

    BankAccountDto getBankAcountId(String idBankAccount) throws BankAccountNotFoundException;

    void debit(String idAccount,double amount,String description) throws BankAccountNotFoundException, BalanceNotSufficientException;

    void credit(String idAccount,double amount,String description) throws BankAccountNotFoundException;

    void transfer(String idAccountSource,String idAccountDestination,double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccountDto> getAllBankAccount();

    CustomerDto getCustomerId(Long id) throws CustomerNotFoundException;

    CustomerDto updateCustomer(CustomerDto customerDto);

    void deleteCustomer(Long id);

    List<OperationDto> getOperationsAccountHistory(String id);

    InfosHistoryAccountDto getInfosAccountHistory(String id, int page, int size) throws BankAccountNotFoundException;

    List<CustomerDto> searchCustomer(String keyword);

    List<BankAccountDto> getAllAccountCustomer(Long id);
}
