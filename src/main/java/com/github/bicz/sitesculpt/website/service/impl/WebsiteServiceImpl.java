package com.github.bicz.sitesculpt.website.service.impl;

import com.github.bicz.sitesculpt.exception.RequestNotCorrectException;
import com.github.bicz.sitesculpt.exception.ResourceNotFoundException;
import com.github.bicz.sitesculpt.security.service.JwtService;
import com.github.bicz.sitesculpt.user.model.User;
import com.github.bicz.sitesculpt.user.model.UserRole;
import com.github.bicz.sitesculpt.user.repository.UserRepository;
import com.github.bicz.sitesculpt.website.dto.AddAdminRequest;
import com.github.bicz.sitesculpt.website.dto.WebsiteRequest;
import com.github.bicz.sitesculpt.website.dto.WebsiteResponse;
import com.github.bicz.sitesculpt.website.model.Website;
import com.github.bicz.sitesculpt.website.repository.WebsiteRepository;
import com.github.bicz.sitesculpt.website.service.WebsiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebsiteServiceImpl implements WebsiteService {
    private final WebsiteRepository websiteRepository;
    private final UserRepository userRepository;

    @Override
    public List<WebsiteResponse> getAllWebsitesByUsername(String username) {
        if (Objects.isNull(username)) {
            throw new RequestNotCorrectException("Provided username is empty");
        }

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User with username %s does not exist", username));
        }
        User user = optionalUser.get();

        if (user.getRole() != UserRole.ADMIN) {
            return null;
        }

        ArrayList<WebsiteResponse> response = new ArrayList<>();
        for(Website website : user.getWebsites()) {
            if (Objects.nonNull(website)) {
                response.add(new WebsiteResponse(
                        website.getWebsiteId(),
                        website.getTitle(),
                        website.getDescription(),
                        website.getFaviconPath()
                ));
            }
        }

        return response;
    }

    @Override
    public WebsiteResponse getWebsiteById(Long websiteId) {
        Optional<Website> optionalWebsite = websiteRepository.findById(websiteId);
        if (optionalWebsite.isEmpty()) {
            System.out.printf("website with id %d is empty\n", websiteId);
            return null;
        }
        Website website = optionalWebsite.get();

        return new WebsiteResponse(
                website.getWebsiteId(),
                website.getTitle(),
                website.getDescription(),
                website.getFaviconPath()
        );
    }

    @Override
    public Long createWebsite(WebsiteRequest request) {
        if (Objects.isNull(request)) {
            throw new RequestNotCorrectException("Provided request is empty");
        }

        String username = request.getUsername();
        if (Objects.isNull(username)) {
            throw new RequestNotCorrectException("Provided username is empty");
        }

        Website website = new Website();
        website.setTitle(request.getTitle());
        website.setDescription(request.getDescription());
        website.setFaviconPath(request.getFaviconPath());

        Optional<User> optionalAdmin = userRepository.findByUsername(username);
        if (optionalAdmin.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User with username %s does not exist", username));
        }
        User admin = optionalAdmin.get();

        List<User> adminsList = new ArrayList<>();
        if (Objects.nonNull(website.getAdmins())) {
            adminsList = website.getAdmins();
        }
        adminsList.add(admin);
        website.setAdmins(adminsList);

        List<Website> websitesList = new ArrayList<>();
        if (Objects.nonNull(website.getAdmins())) {
            websitesList = admin.getWebsites();
        }
        websitesList.add(website);
        admin.setWebsites(websitesList);

        userRepository.save(admin);
        return websiteRepository.save(website).getWebsiteId();
    }

    @Override
    public void addAdminToWebsite(AddAdminRequest request) throws RequestNotCorrectException, ResourceNotFoundException {
        if (Objects.isNull(request)) {
            throw new RequestNotCorrectException("Provided request is empty");
        }
        Long websiteId = request.getWebsiteId();
        if (Objects.isNull(websiteId)) {
            throw new RequestNotCorrectException("Provided website id is empty");
        }
        String username = request.getUsername();
        if (Objects.isNull(username)) {
            throw new RequestNotCorrectException("Provided username is empty");
        }

        Optional<Website> optionalWebsite = websiteRepository.findById(websiteId);
        if (optionalWebsite.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Website with id %d does not exist", websiteId));
        }
        Website website = optionalWebsite.get();

        Optional<User> optionalAdmin = userRepository.findByUsername(username);
        if (optionalAdmin.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User with username %s does not exist", username));
        }
        User admin = optionalAdmin.get();

        if (admin.getRole() != UserRole.ADMIN) {
            System.out.printf("User with username %s does not have role admin%n", admin.getUsername());
            return;
        }

        ArrayList<User> websiteAdmins = (ArrayList<User>) website.getAdmins();
        if (Objects.isNull(websiteAdmins)) {
            websiteAdmins = new ArrayList<>();
        }
        boolean adminAlreadyExist = false;
        for (User websiteAdmin : websiteAdmins) {
            if (websiteAdmin.getUsername().equals(admin.getUsername())) {
                adminAlreadyExist = true;
                break;
            }
        }

        if (!adminAlreadyExist) {
            websiteAdmins.add(admin);
            website.setAdmins(websiteAdmins);
            websiteRepository.save(website);

            List<Website> websitesList = new ArrayList<>();
            if (Objects.nonNull(admin.getWebsites())) {
                websitesList = admin.getWebsites();
            }
            websitesList.add(website);
            admin.setWebsites(websitesList);
            userRepository.save(admin);
        } else {
            System.out.printf("User with username %s is already an admin for website with id %d%n", admin.getUsername(), website.getWebsiteId());
        }
    }
}
