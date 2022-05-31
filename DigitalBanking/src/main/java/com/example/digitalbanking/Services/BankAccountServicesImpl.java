package com.example.digitalbanking.Services;

import com.example.digitalbanking.Dtos.*;
import com.example.digitalbanking.Entities.*;
import com.example.digitalbanking.Enums.OperationType;
import com.example.digitalbanking.Exceptions.BalanceNotSufficientException;
import com.example.digitalbanking.Exceptions.BankAccountNotFoundException;
import com.example.digitalbanking.Exceptions.CustomerNotFoundException;
import com.example.digitalbanking.Mappers.BankAccountMappersImpl;
import com.example.digitalbanking.Repositories.BankAccountRepo;
import com.example.digitalbanking.Repositories.CustomerRepo;
import com.example.digitalbanking.Repositories.OperationRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional

public class BankAccountServicesImpl implements BankAccountServices{

    private CustomerRepo customerRepo;
    private BankAccountRepo bankAccountRepo;
    private OperationRepo operationRepo;
    private BankAccountMappersImpl mappers;

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        log.info("Saving customer ...");
        Customer customer=mappers.fromCustomerDto(customerDto);
        return mappers.fromCustomer(customerRepo.save(customer));
    }


    @Override
    public BankAccount saveCurrentBankAccount(double initBalance, double overDraft, Long idCustomer) throws CustomerNotFoundException {
        Customer customer=customerRepo.findById(idCustomer).orElse(null);
        if(customer==null) throw new CustomerNotFoundException("Customer not found");

        CurrentAccount currentAccount=new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setBalance(initBalance);
        currentAccount.setCustomer(customer);
        currentAccount.setCreatedAt(new Date());
        currentAccount.setOverDraft(overDraft);

        return bankAccountRepo.save(currentAccount);
    }

    @Override
    public BankAccount saveSavingBankAccount(double initBalance, double interesetRate, Long idCustomer) throws CustomerNotFoundException {
        Customer customer=customerRepo.findById(idCustomer).orElse(null);
        if(customer==null) throw new CustomerNotFoundException("Customer not found");

        SavingAccount savingAccount=new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setBalance(initBalance);
        savingAccount.setCustomer(customer);
        savingAccount.setCreatedAt(new Date());
        savingAccount.setInterestRate(interesetRate);

        return bankAccountRepo.save(savingAccount);
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> customers= customerRepo.findAll();
        List<CustomerDto> customerDtos=customers.stream().map(cust->mappers.fromCustomer(cust)).collect(Collectors.toList());
        return customerDtos;
    }

    @Override
    public BankAccountDto getBankAcountId(String idBankAccount) throws BankAccountNotFoundException {

        BankAccount bankAccount=bankAccountRepo.findById(idBankAccount).orElse(null);
        if(bankAccount==null) throw new BankAccountNotFoundException("BankAccount Not Found");

        if(bankAccount instanceof SavingAccount){
           SavingAccount savingAccount= (SavingAccount) bankAccount;
           return mappers.fromSavingBankAccount(savingAccount);
        }else {
            CurrentAccount currentAccount= (CurrentAccount) bankAccount;
            return mappers.fromCurrentBankAccount(currentAccount);
        }

    }

    @Override
    public void debit(String idAccount, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount=bankAccountRepo.findById(idAccount).orElse(null);
        if(bankAccount==null) throw new BankAccountNotFoundException("BankAccount Not Found");
      if(bankAccount.getBalance()<amount)throw new BalanceNotSufficientException("Balance Not Sufficient");
        Operation operation=new Operation();
        operation.setDate(new Date());
        operation.setOperationType(OperationType.DEBIT);
        operation.setDescription(description);
        operation.setAmount(amount);
        operation.setBankAccount(bankAccount);
        operationRepo.save(operation);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepo.save(bankAccount);
    }

    @Override
    public void credit(String idAccount, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountRepo.findById(idAccount).orElse(null);
        if(bankAccount==null) throw new BankAccountNotFoundException("BankAccount Not Found");
        Operation operation=new Operation();
        operation.setDate(new Date());
        operation.setOperationType(OperationType.CREDIT);
        operation.setDescription(description);
        operation.setAmount(amount);
        operation.setBankAccount(bankAccount);
        operationRepo.save(operation);
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepo.save(bankAccount);
    }

    @Override
    public void transfer(String idAccountSource, String idAccountDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
          debit(idAccountSource,amount,"Transfer to\t"+idAccountDestination);
          credit(idAccountDestination,amount,"Transfer from\t"+idAccountSource);
    }

    @Override
    public List<BankAccountDto> getAllBankAccount(){
        List<BankAccount> bankAccountDtos=bankAccountRepo.findAll();
        List<BankAccountDto> bankAccountDtoList=bankAccountDtos.stream().map(bankAccount -> {
            if(bankAccount instanceof SavingAccount){
                SavingAccount savingAccount= (SavingAccount) bankAccount;
                return mappers.fromSavingBankAccount(savingAccount);
            }else {
                CurrentAccount currentAccount= (CurrentAccount) bankAccount;
                return mappers.fromCurrentBankAccount(currentAccount);
            }
        }).collect(Collectors.toList());
        return bankAccountDtoList;
    }

    @Override
    public CustomerDto getCustomerId(Long id) throws CustomerNotFoundException {
        Customer customer=customerRepo.findById(id).orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
        return mappers.fromCustomer(customer);
    }


    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        log.info("Saving customer ...");
        Customer customer=mappers.fromCustomerDto(customerDto);
        return mappers.fromCustomer(customerRepo.save(customer));
    }

    @Override
    public void deleteCustomer(Long id){
        customerRepo.deleteById(id);
    }

    @Override
    public List<OperationDto> getOperationsAccountHistory(String id){
       List<Operation> operations=operationRepo.findByBankAccountId(id);
        return operations.stream().map(operation -> mappers.fromOperation(operation)).collect(Collectors.toList());
    }

    @Override
    public InfosHistoryAccountDto getInfosAccountHistory(String id, int page, int size) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountRepo.findById(id).orElse(null);
        if(bankAccount==null)throw new BankAccountNotFoundException("Account Not Found");
        Page<Operation> operations=operationRepo.findByBankAccountIdOrderByDateDesc(id, PageRequest.of(page,size));
        InfosHistoryAccountDto infosHistoryAccountDto=new InfosHistoryAccountDto();
        List<OperationDto> operationDtos=operations.stream().map(op->mappers.fromOperation(op)).collect(Collectors.toList());
        infosHistoryAccountDto.setOperations(operationDtos);
        infosHistoryAccountDto.setId(bankAccount.getId());
        infosHistoryAccountDto.setBalance(bankAccount.getBalance());
        infosHistoryAccountDto.setCurrentPage(page);
        infosHistoryAccountDto.setNbrPages(operations.getTotalPages());
        infosHistoryAccountDto.setSize(size);
        if(bankAccount instanceof CurrentAccount)infosHistoryAccountDto.setType("Current Account");
        else infosHistoryAccountDto.setType("Saving Account");

        return infosHistoryAccountDto;
    }

    @Override
    public List<CustomerDto> searchCustomer(String keyword) {
        List<Customer> customers=customerRepo.searchcustomer(keyword);
        return customers.stream().map(customer -> mappers.fromCustomer(customer)).collect(Collectors.toList());
    }

    @Override
    public List<BankAccountDto> getAllAccountCustomer(Long id) {
        List<BankAccount> bankAccounts=bankAccountRepo.findByCustomer_Id(id);
         return bankAccounts.stream().map(bankAccount -> {
            if(bankAccount instanceof SavingAccount){
                SavingAccount savingAccount= (SavingAccount) bankAccount;
                return mappers.fromSavingBankAccount(savingAccount);
            }else {
                CurrentAccount currentAccount= (CurrentAccount) bankAccount;
                return  mappers.fromCurrentBankAccount(currentAccount);
            }
        }).collect(Collectors.toList());
    }


}
