package nl.novi.Backend.controller;

import nl.novi.Backend.model.AppUser;
import nl.novi.Backend.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/resetpassword")
public class ResetPasswordController {

    @Autowired
    private ResetPasswordService resetPasswordService;

    @PostMapping("/{userid}")
    public ResponseEntity<?> resetPassword(@PathVariable("userid") Long User_id, @RequestBody AppUser appUser) {
        return resetPasswordService.resetPassword(User_id, appUser);

    }
}
