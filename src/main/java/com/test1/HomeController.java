package com.test1;
import com.test1.reposit.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;


@Controller
public class HomeController {
    @Autowired
    private Repository repository;

    @GetMapping("/home")
    public String home(Model model) {
    Iterable<Article> list = repository.findAll();
    model.addAttribute("list", list);
        return "home";
    }
    @GetMapping("/addPage")
    public String addPage(Model model) {

        return "add-page";
    }
    @PostMapping("/addPage")
    public String add(@RequestParam String article, @RequestParam String text, Model model)  {
        Article art = new Article(article, text);
        repository.save(art);
        Iterable<Article> list = repository.findAll();
        model.addAttribute(list);
        return "redirect:/home";
    }
    @GetMapping ("/home/{id}")
    public String details(@PathVariable(value = "id") int id, Model model)  {
        if (!repository.existsById(id))
            return "redirect:/home";
        Optional<Article> art = repository.findById(id);
        ArrayList<Article> artList = new ArrayList<>();
        art.ifPresent(artList::add);
        model.addAttribute("article", artList);
        return "article-details";
    }
    @GetMapping ("/home/{id}/edit")
    public String edit(@PathVariable(value = "id") int id, Model model)  {
        if (!repository.existsById(id))
            return "redirect:/home";
        Optional<Article> art = repository.findById(id);
        ArrayList<Article> artList = new ArrayList<>();
        art.ifPresent(artList::add);
        model.addAttribute("article", artList);
        return "article-edit";
    }

    @PostMapping("/home/{id}/edit")
    public String edit(@PathVariable(value = "id") int id, @RequestParam String article, @RequestParam String text, Model model)    {
        Article art = repository.findById(id).orElseThrow(RuntimeException::new);
        art.setTitle(article);
        art.setText(text);
        repository.save(art);
        return "redirect:/home";
    }
    @PostMapping("/home/{id}/remove")
    public String remove(@PathVariable(value = "id") int id, Model model)    {
        Article art = repository.findById(id).orElseThrow(RuntimeException::new);

        repository.delete(art);
        return "redirect:/home";
    }
}