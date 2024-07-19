package com.demo_bank_v1.controllers;


import com.demo_bank_v1.helpers.Token;
import com.demo_bank_v1.models.User;
import com.demo_bank_v1.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public ModelAndView getLogin(){
        System.out.println("In Login Page Controller");
        ModelAndView getLoginPage=new ModelAndView("login");
        //Set token String:
        String token = Token.generateToken();

        //Send Token to View
        getLoginPage.addObject("token",token);
        getLoginPage.addObject("PageTitle","Login");
        return getLoginPage;
    }

    @PostMapping("/login")
    public String login(@RequestParam("email")String email,
                        @RequestParam("password")String password,
                        @RequestParam("_token")String token,
                        Model model,
                        HttpSession session){

        //TODO: VALIDATE INPUT FIELDS / FORM DATA:
         if(email.isEmpty() ||email==null || password.isEmpty() || password==null){
             model.addAttribute("error", "Username or Password Cannot be empty");
             return "login";
          }

        //TODO: CHECK IF EMAIL EXITS:
         String getEmailInDatabase = userRepository.getUserEmail(email);

         //Check if email exist:
        if( getEmailInDatabase !=null){
            //Get Password in database:
            String getPasswordInDatabase = userRepository.getUserPassword(getEmailInDatabase);

            //Validate Password
            if(!BCrypt.checkpw(password,getPasswordInDatabase)){
                model.addAttribute("error", "Incorrect Username or Password");
                return "login";
            }
        }else{
            model.addAttribute("error", "Something went wrong please contact support");
            return "error";
        }
        //TODO: CHECK IF USER ACCOUNT IS VERIFIED:
        int verified= userRepository.isVerified((getEmailInDatabase));
        //Check if account is verified
        if(verified !=1){
            String msg = "This Account is not yet Verified, please check E-mail and Verified Account.";
            model.addAttribute("error",msg);
            return "login";
        }

        //TODO: PROCEED TO LOG THE USER IN:
         User user=userRepository.getUserDetail(getEmailInDatabase);

        //set Session Attribute:
        session.setAttribute("user",user);
        session.setAttribute("token",token);
        session.setAttribute("authenticated",true);


        return "redirect:/app/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes){
        session.invalidate();
        redirectAttributes.addFlashAttribute("logged_out","Logged out successfully");
        return "redirect:/login";
    }

}
