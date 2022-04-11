package com.example.tp_patient.Web;

import com.example.tp_patient.entities.etudiant;
import com.example.tp_patient.repository.etudiantRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class etudiantController {
       etudiantRepository etudiantRepository;
    @GetMapping("/home")
    public String home(Model model,
                       @RequestParam(name = "page",defaultValue = "0") int page,
                       @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name="key",defaultValue = "")String key){
        Page<etudiant> etudiantPages=etudiantRepository.findAllByNomContains(key,PageRequest.of(page,size));
        model.addAttribute("pages",etudiantPages);
        model.addAttribute("pagination",new int[etudiantPages.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("key",key);
        return "home";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable(value = "ido") int id){
        etudiantRepository.deleteById(id);
        return "redirect:home";
    }
}
