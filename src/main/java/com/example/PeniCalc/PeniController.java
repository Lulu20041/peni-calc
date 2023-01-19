package com.example.PeniCalc;

import com.example.PeniCalc.entities.ClientSum;
import com.example.PeniCalc.entities.Currency;
import com.example.PeniCalc.entities.Peni;
import com.example.PeniCalc.service.ClientSumService;
import com.example.PeniCalc.service.CurrencyService;
import com.example.PeniCalc.service.PeniService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PeniController {
    @Autowired
    ClientSumService clientSumService;
    @Autowired
    PeniService peniService;
    @Autowired
    CurrencyService currencyService;
    @GetMapping("/")
    public String getPage(Model model) {
        try {
            currencyService.loadCurrencies();
            List<ClientSum> clientSum = clientSumService.getSums();
            model.addAttribute("clientSums", clientSum);

            List<Currency> currencies = clientSumService.getCurrencies();
            model.addAttribute("currencies", currencies);

            model.addAttribute("clientSum", new ClientSum());

            model.addAttribute("currencyValue", new Currency());

            List<Peni> penies = peniService.getPenies();
            model.addAttribute("penies", penies);

            double peniSum = peniService.peniSum();
            model.addAttribute("peniSum", peniSum);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @PostMapping("/")
    public String payTax(@ModelAttribute("clientSum") ClientSum clientSum, Model model) {
        try {
            clientSumService.addSum(clientSum);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
    @PostMapping("/calculate")
    public String calculatePeni(HttpServletRequest request) {
        try {
            peniService.deletePenies();
            peniService.calculatePenies(request, clientSumService);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
    @PostMapping("/clear")
    public String deleteRecords(Model model) {
        try {
            peniService.deletePenies();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return getPage(model);
    }
    @GetMapping("/delete/{id}")
    public String deleteSum(@PathVariable(name = "id") int id) {
        clientSumService.deleteSum(id);
        return "redirect:/";
    }
}
