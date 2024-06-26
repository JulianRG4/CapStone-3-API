package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@CrossOrigin
public class ProfileController
{
    UserDao userDao;
    private ProfileDao profileDao;

    @Autowired
    public ProfileController(ProfileDao profileDao, UserDao userDao)
    {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @GetMapping("/profile")
    public Profile getProfiles(Principal principal)
    {
       String PrincipalName = principal.getName();
       User user = userDao.getByUserName(PrincipalName);
       int userId = user.getId();
       Profile profile = profileDao.getById(userId);
       if(profile == null)
       {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);
       }
       return profile;
    }

    @PutMapping("/profile")
    public Profile updateProfile(Principal principal,@RequestBody Profile profileBody)
    {

        String PrincipalName = principal.getName();
        User user = userDao.getByUserName(PrincipalName);
        int userId = user.getId();
        Profile profile = profileDao.updateProfile(userId, profileBody);
        if(profile == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return profile;
    }

}
