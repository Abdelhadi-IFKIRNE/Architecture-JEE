package com.example.gestionproduit.Web;

import com.example.gestionproduit.Entities.Produit;
import com.example.gestionproduit.Repository.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@Controller @AllArgsConstructor
public class ProduitController {
    private ProduitRepository produitRepository;

    @GetMapping(path = "/index")
   public String pagesProduct(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name= "size",defaultValue = "5") int size,
                        @RequestParam(name = "MC", defaultValue = "") String MC){
        Page<Produit> pages=produitRepository.findAllByNomContains(MC,PageRequest.of(page,size));
        model.addAttribute("pageProduits",pages);
        model.addAttribute("pageCurrent",page);
        model.addAttribute("motcle",MC);
        model.addAttribute("pages",new int[pages.getTotalPages()]);
        return "listproduits";
    }

        @GetMapping(path = "/delete")
    public String Delete( @RequestParam(name = "id") int id, int page, String MC){
        produitRepository.deleteById(id);
        return "redirect:/index?page="+page+"&MC="+MC;
    }

     @GetMapping(path = "/")
    public String home(){
         return "listproduits";
    }

    @GetMapping("/formAddProduit")
    public  String formAddProduit(Model model){
        Produit p=new Produit();
        model.addAttribute("produit",p);
        return "formAddProduit";
    }

        @PostMapping("/save")
    public String save(Model model, @Valid Produit pr, BindingResult bindingResult){
        if(bindingResult.hasErrors())return "formAddProduit";
          produitRepository.save(pr);
          return "redirect:/formAddProduit";
    }
         @GetMapping("/Edform")
    public  String editForm(Model model, int id){
         Produit produit=produitRepository.findById(id).orElse(null);
         if(produit==null) throw  new RuntimeException("introvable");
         model.addAttribute("produitEdit",produit);
        return "formEdit";
    }

    @PostMapping(path = "/saveEdit")
    public String saveEdit(Model model,@Valid Produit produit, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formEdit";
        produitRepository.save(produit);
        return "redirect:/index";
    }
}
