package nl.novi.Backend.emailCustomer;

import nl.novi.Backend.model.AppUser;
import nl.novi.Backend.model.Customer;
import nl.novi.Backend.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import javax.validation.constraints.Email;
import java.util.Optional;

@RestController
@RequestMapping("/messageforCustomer")
public class FeedbackController {
    private EmailConfig emailConfig;
    @Autowired
    private CustomerRepository customerRepository;


    public FeedbackController(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }
    @PostMapping("/{customerId}")
    @PreAuthorize("hasAnyAuthority('USER_FRO','ADMIN')")
    public void sendFeedback(@PathVariable("customerId") Long customerId,
                            @RequestBody AppUser appUser,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback is not valid.");
        }
        Optional<Customer> possibleCustomer = customerRepository.findById(customerId);
        if(possibleCustomer.isPresent()) {
            //Create a mail sender
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(this.emailConfig.getHost());
            mailSender.setPort(this.emailConfig.getPort());
            mailSender.setUsername(this.emailConfig.getUsername());
            mailSender.setPassword(this.emailConfig.getPassword());

            //Create an email instance
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(appUser.getEmail());
            mailMessage.setTo(possibleCustomer.get().getEmail());
            mailMessage.setSubject("New feedback from" + appUser.getUserName());
            mailMessage.setText(appUser.getFeedback());

            //Send mail
            mailSender.send(mailMessage);
        }

    }
}
