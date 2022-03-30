package com.example.springmvc_part1.web;

import com.example.springmvc_part1.Repository.PateintRepository;
import com.example.springmvc_part1.entities.Patient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;


@Controller
@AllArgsConstructor
public class patientController {

    private PateintRepository pateintRepository;

    @GetMapping(path = "/index")
    public String patients(Model model,
                           @RequestParam(name ="page",defaultValue ="0") int page,
                           @RequestParam(name = "size",defaultValue = "5") int size,
                           @RequestParam(name = "key", defaultValue = "")String ky){
        Page<Patient> Pagepatient=pateintRepository.findByNameContains(ky,PageRequest.of(page,size));
        model.addAttribute("listpatients",Pagepatient.getContent());
        model.addAttribute("pages", new int[Pagepatient.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("key",ky);
       return "patients";
    }
       @GetMapping(path="/delete")
    public String delete(
             Long id,String key, int page
    ){
        pateintRepository.deleteById(id);
        return "redirect:/index?page="+page+"&key="+key;
    }
      @GetMapping(path= "/")
    public String def(){
        return  "redirect:/index";
    }

    @GetMapping("/form")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatient";
    }
       @PostMapping(path = "/save")
    public String savePatient(Model model, @Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formPatient";
        pateintRepository.save(patient);
        return "redirect:form";
    }
}
