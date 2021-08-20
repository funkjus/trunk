package org.mama.secondProgect.controller;

import org.mama.secondProgect.DAO.Entity.Good;
import org.mama.secondProgect.DAO.Entity.Trader;
import org.mama.secondProgect.service.GoodService;
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
public class GoodMapping {

    @Autowired
    GoodService goodService;

    @Autowired
    TraderService traderService;

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.GET)
    public ModelAndView getAllTraderGoods(@PathVariable("id") long id){
        List<Good> goodList = goodService.getTraderGoods(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("goods");
        modelAndView.addObject("traderId", id);
        modelAndView.addObject("goodList",goodList);
        return modelAndView;

    }
    @RequestMapping(value = "/editGood/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id){
        Good good = goodService.getGood(id);
        Trader trader = good.trader;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editGoodPage");
        modelAndView.addObject("good", good);
        modelAndView.addObject("trader", trader);
        return modelAndView;
    }

    @RequestMapping(value = "/editGood/", method = RequestMethod.POST)
    public ModelAndView editGood(@ModelAttribute("good") Good good){
        good.trader = goodService.getGood(good.id).trader;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/goods/"+ good.trader.id);
        goodService.update(good);
        return modelAndView;
    }

    @RequestMapping(value = "/addGood/{id}", method = RequestMethod.GET)
    public ModelAndView addPage(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editGoodPage");
        modelAndView.addObject("traderId",id);
        return modelAndView;
    }

    @RequestMapping(value = "/addGood/", method = RequestMethod.POST)
    public ModelAndView addGood(@ModelAttribute("good") Good good,@ModelAttribute("traderId") int id){
        good.trader = traderService.getTrader(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/goods/"+ good.trader.id);
        goodService.add(good);
        return modelAndView;
    }
    @RequestMapping(value = "/deleteGood/{id}", method = RequestMethod.GET)
    public ModelAndView deleteGood(@PathVariable("id") int id){
        Good good = goodService.getGood(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/goods/" + good.trader.id);
        goodService.delete(good.id);
        return modelAndView;
    }
}
