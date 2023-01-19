package com.yam.funteer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class Controller {
    private final UserRepository userRepository;

    @GetMapping("/{name}")
    public User test (@PathVariable String name) {
        User user = new User();
        user.setName(name);
        User user1 = userRepository.save(user);
        return user1;
    }
}
