package de.itstimetoforget.backend.rest.resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PDFResource {


    @RequestMapping("orderSummary")
    public String orderSummary(Model model) {
        model.addAttribute("to", "asdasd");
        return "order_summary";
    }

}
