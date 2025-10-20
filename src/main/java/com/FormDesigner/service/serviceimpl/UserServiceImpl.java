package com.FormDesigner.service.serviceimpl;

import com.FormDesigner.entity.UserDetails;
import com.FormDesigner.logger.FormLogger;
import com.FormDesigner.repository.UserRepository;
import com.FormDesigner.service.UserService;
import com.FormDesigner.util.CommonValidations;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String createUser(JsonNode data) {
        try {
            FormLogger.info("Inside createUser method");
            UserDetails user = objectMapper.treeToValue(data, UserDetails.class);
            FormLogger.info("Creating user: " + user);
            boolean validName = CommonValidations.isValidName(user.getName());
            boolean validEmail = CommonValidations.isValidEmail(user.getEmail());
            boolean validPassword = CommonValidations.isValidPassword(user.getPassword());
            boolean validMobileNumber = CommonValidations.isValidPhoneNumber(user.getMobileNumber());
            if (!validName || !validEmail || !validPassword || !validMobileNumber) {
                FormLogger.error("Invalid user details provided: " + validEmail + ", "
                        + validName + ", " + validPassword + ", " + validMobileNumber);
                throw new IllegalArgumentException("Invalid user details provided");
            }
            UserDetails savedUser = userRepository.save(user);
            return "User created successfully with ID: " + savedUser.getUserId();
        } catch (Exception e) {
            FormLogger.error("Error while creating user: " + e.getMessage());
            return "Error while creating user: " + e.getMessage();
        }
    }

    @Override
    public String deleteUser(JsonNode data) {
        return "";
    }

    @Override
    public String getUserInfo(JsonNode data) {
        try{
            FormLogger.info("Inside getUserInfo method");
            List<UserDetails> getUser =  userRepository.findAll();
            return getUser.toString();
        } catch (IllegalArgumentException e) {
            FormLogger.error("Error while getting user info: " + e.getMessage());
            return "Error while getting user info: " + e.getMessage();
        }
    }

    @Override
    public String getUserDetailsByEmail(JsonNode data) {
        try {
            FormLogger.info("Inside getUserDetailsByEmail method");
            String email = data.get("email").asText();
            FormLogger.info("Fetching user details for email: " + email);
            boolean isValidEmail = CommonValidations.isValidEmail(email);
            if (!isValidEmail) {
                FormLogger.error("Invalid email format provided: " + email);
                throw new IllegalArgumentException("Invalid email format provided");
            }
            UserDetails user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
            FormLogger.info("User details retrieved successfully for email: " + user);
            return user.toString();
        } catch (Exception e) {
            FormLogger.error("Error while retrieving user details: " + e.getMessage());
            return "Error while retrieving user details: " + e.getMessage();
        }
    }
}
