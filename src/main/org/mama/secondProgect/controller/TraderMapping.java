package org.mama.secondProgect.controller;

import org.mama.secondProgect.DAO.Entity.Trader;
import org.mama.secondProgect.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TraderMapping {

    @Autowired
    private TraderService traderService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allTraders() {
        List<Trader> traders = traderService.allTraders();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("traders");
        modelAndView.addObject("traderList", traders);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Trader trader = traderService.getTrader(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editTraderPage");
        modelAndView.addObject("trader", trader);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editTrader(@ModelAttribute("trader") Trader trader) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        traderService.updateTrader(trader);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editTraderPage");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addTrader(@ModelAttribute("trader") Trader trader) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        traderService.addTrader(trader);
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteTrader(@PathVariable("id") int id){
        Trader trader = traderService.getTrader(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        traderService.delTrader(trader);
        return modelAndView;
    }

}
