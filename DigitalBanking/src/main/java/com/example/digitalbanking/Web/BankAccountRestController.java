package com.example.digitalbanking.Web;


import com.example.digitalbanking.Dtos.*;
import com.example.digitalbanking.Entities.Operation;
import com.example.digitalbanking.Exceptions.BalanceNotSufficientException;
import com.example.digitalbanking.Exceptions.BankAccountNotFoundException;
import com.example.digitalbanking.Mappers.BankAccountMappersImpl;
import com.example.digitalbanking.Repositories.OperationRepo;
import com.example.digitalbanking.Services.BankAccountServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class BankAccountRestController {
    private BankAccountServices bankAccountServices;

    @GetMapping("/accounts/{id}")
    public BankAccountDto getAccountId(@PathVariable String id) throws BankAccountNotFoundException {
        return bankAccountServices.getBankAcountId(id);
    }

    @GetMapping("/accounts")
    public List<BankAccountDto> getAccounts(){
        return bankAccountServices.getAllBankAccount();
    }

    @GetMapping("/accounts/{id}/operations")
    public List<OperationDto> getOperationsAccountHistory(@PathVariable String id){
          return bankAccountServices.getOperationsAccountHistory(id);
    }

    @GetMapping("accounts/{id}/infosAccount")
    public InfosHistoryAccountDto getInfosHistoryAccount(@PathVariable String id,
                                                         @RequestParam(name = "page",defaultValue = "0") int page,
                                                         @RequestParam(name = "size", defaultValue = "5") int size) throws BankAccountNotFoundException {
        return bankAccountServices.getInfosAccountHistory(id,page,size);
    }

    @PostMapping("/accounts/credit")
    public CreditDto credit(@RequestBody CreditDto creditDto) throws BankAccountNotFoundException {
        bankAccountServices.credit(creditDto.getIdAccount(),creditDto.getAmount(),creditDto.getDescription());
        return  creditDto;
    }

    @PostMapping("/accounts/debit")
    public DebitDto debit(@RequestBody DebitDto debitDto) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountServices.debit(debitDto.getIdAccount(),debitDto.getAmount(),debitDto.getDescription());
        return  debitDto;
    }

    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferDto transferDto) throws BankAccountNotFoundException, BalanceNotSufficientException {
        bankAccountServices.transfer(transferDto.getIdSource(),transferDto.getIdDest(),transferDto.getAmount());
    }
}
