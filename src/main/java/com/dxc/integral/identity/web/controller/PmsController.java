package com.dxc.integral.identity.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for Password Management System (PMS)
 */
@Controller("pmsController")
@RequestMapping("/pms")
public class PmsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsController.class);
    
    @RequestMapping(method = RequestMethod.GET)
    public String view(Model model) throws Exception {
        LOGGER.debug("Account management system get view.");
        return "casPmsView";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String ams(Model model) throws Exception {
    	 LOGGER.debug("Account management system post data.");
         return "casPmsView";
    }

}
