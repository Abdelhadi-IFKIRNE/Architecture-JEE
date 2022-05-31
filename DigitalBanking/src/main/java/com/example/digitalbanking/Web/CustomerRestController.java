package com.example.digitalbanking.Web;

import com.example.digitalbanking.Dtos.*;
import com.example.digitalbanking.Exceptions.CustomerNotFoundException;
import com.example.digitalbanking.Services.BankAccountServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@CrossOrigin("*")
public class CustomerRestController {
    BankAccountServices bankAccountServices;
    @GetMapping("/customers")
    public List<CustomerDto> customers(){
       return bankAccountServices.getAllCustomer();
    }

    @GetMapping("/customers/search")
    public List<CustomerDto> searchcustomer(@RequestParam(name = "key",defaultValue = "") String keyword){
        return bankAccountServices.searchCustomer("%"+keyword+"%");
    }

    @GetMapping("/customers/{id}")
    public CustomerDto getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return bankAccountServices.getCustomerId(customerId);
    }

    @PostMapping("/customers")
    public CustomerDto saveCustomer(@RequestBody CustomerDto customerDto){
            return bankAccountServices.saveCustomer(customerDto);
    }

    @PutMapping("/customers/{idCustomer}")
    public CustomerDto updateCustomer(@PathVariable Long idCustomer,@RequestBody CustomerDto customerDto){
        customerDto.setId(idCustomer);
        return bankAccountServices.updateCustomer(customerDto);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        bankAccountServices.deleteCustomer(id);
    }

    @GetMapping("/customers/accounts/{id}")
     public List<BankAccountDto> getAllAccountsCust(@PathVariable Long id){
        return bankAccountServices.getAllAccountCustomer(id);
     }


}
