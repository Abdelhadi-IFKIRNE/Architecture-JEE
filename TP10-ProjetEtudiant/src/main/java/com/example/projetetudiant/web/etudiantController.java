package com.example.projetetudiant.web;

import com.example.projetetudiant.entities.etudiant;
import com.example.projetetudiant.repositories.etudiantRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class etudiantController {
    private etudiantRepository etudiantRepository;

    @GetMapping("/user/home")
    public String home(Model model, @RequestParam(name = "page",defaultValue = "0") int page,
                                    @RequestParam(name = "size",defaultValue = "6") int size,
                                    @RequestParam(name = "key",defaultValue = "") String key
        ){
           Page<etudiant> etudiantPages=etudiantRepository.findAllByNomContains(key,PageRequest.of(page,size));
           model.addAttribute("pages",etudiantPages.getContent());
           model.addAttribute("nbrPages",new int[etudiantPages.getTotalPages()]);
           model.addAttribute("key",key);
           model.addAttribute("pageCurrent",page);
        return "home";
    }
    @GetMapping(value = "/admin/delete")
    public String delete(Long id,int page, String key){

           etudiantRepository.deleteById(id);
        return "redirect:/user/home?page="+page+"&&key="+key;
    }

    @GetMapping("/admin/add")
    public String add(Model model){
        model.addAttribute("etudiant",new etudiant());
        return "add";
    }

    @PostMapping("/admin/save")
    public String saveEtu(Model model, @Valid etudiant etudiant, BindingResult bindingResult){
        if (bindingResult.hasErrors())return "add";
        etudiantRepository.save(etudiant);
        return "redirect:/user/home";
    }

    @GetMapping("/admin/edit")
    public  String edit(Model model, Long id, int page,String key){
        etudiant etudiant=etudiantRepository.findById(id).orElse(null);
        model.addAttribute("etudiant",etudiant);
        model.addAttribute("page",page);
        model.addAttribute("key",key);
        return "edit";
    }

       @PostMapping("/admin/saveEdit")
    public String saveEdit(Model model, @Valid etudiant etudiant, BindingResult bindingResult, @RequestParam(name = "page",defaultValue = "0") int page, @RequestParam(name = "key",defaultValue = "") String key){
        if(bindingResult.hasErrors())return "edit";
        etudiantRepository.save(etudiant);
        return "redirect:/user/home?page="+page+"&key="+key;
    }

    @GetMapping("/")
    public String racine(){
        return "racine";
    }
}
