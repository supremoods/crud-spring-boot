package com.j_lappay.finals.languages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LanguageController {
    @Autowired
    private LanguageService languageService;

    @GetMapping("/languages")
    public String showLanguagesList(Model model) {
        List<Languages> listLanguages = languageService.listAll();
        System.out.println(listLanguages);
        model.addAttribute("listLanguages", listLanguages);
        return "languages";
    }

    @GetMapping("/languages/new")
    public String showNewForm(Model model){
        model.addAttribute("languages", new Languages());
        model.addAttribute("pageTitle", "Add New Language");
        return "language_form";
    }

    @PostMapping("/languages/save")
    public String savedUser(Languages language, RedirectAttributes ra){
        languageService.save(language);

        ra.addFlashAttribute("message","The user has been saved successfully.");

        return "redirect:/languages";
    }

    @GetMapping("/languages/edit/{id}")
    public String showEditLanguage(Model model, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            Languages language = languageService.get(id);
            model.addAttribute("languages", language);
            model.addAttribute("pageTitle", "Edit Language (ID: " + id + ")");
            return "language_form";
        } catch (LanguageNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/languages";
        }
    }

    @GetMapping("/languages/delete/{id}")
    public String deleteLanguage(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            languageService.delete(id);
            redirectAttributes.addFlashAttribute("message", "The language with id of " + id + " has been deleted successfully");
        } catch (LanguageNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/languages";
    }

}
