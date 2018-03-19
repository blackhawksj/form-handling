package hello;

import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class GreetingController {

	private static final String GREETINGS="greeting";
	private static final String SUCCESS_GREETINGS="result";
	
	
    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute(GREETINGS, new Greeting());
        return GREETINGS;
    }

    @PostMapping("/greeting")
    public RedirectView greetingSubmit(HttpServletRequest request,
    				@ModelAttribute Greeting greeting,
    				RedirectAttributes redirectArrts) {
    	    
    		redirectArrts.addFlashAttribute(greeting);
    		
        return new RedirectView("/", true);
    }
    
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultView(HttpServletRequest request, Model model) {

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
        		model.addAttribute(GREETINGS,(Greeting) inputFlashMap.get(GREETINGS));
        		return SUCCESS_GREETINGS;
        }else {
        		model.addAttribute(GREETINGS, new Greeting());
            return GREETINGS;
        }
    }
    

}
