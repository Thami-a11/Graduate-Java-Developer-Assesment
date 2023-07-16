package com.eviro.assessment.grad001.ThamsanqaDyantyi.Service;

import com.eviro.assessment.grad001.ThamsanqaDyantyi.InterFaces.FileParser;
import com.eviro.assessment.grad001.ThamsanqaDyantyi.InterFaces.IAccountProfileService;
import com.eviro.assessment.grad001.ThamsanqaDyantyi.Models.AccountProfile;
import com.eviro.assessment.grad001.ThamsanqaDyantyi.Repository.AccountProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

@Service
public class AccountProfileService implements IAccountProfileService {

    @Autowired
    private AccountProfileRepository repo;

    private FileParser fileParser;

    public AccountProfileService(AccountProfileRepository repo, FileParser fileParser) {
        this.repo = repo;
        this.fileParser = fileParser;
    }

    @Override
    public void UploadFile(File file) {
        fileParser.parseCSV(file);
    }

    @Override
    public List<AccountProfile> GetAll() {
        return (List<AccountProfile>) this.repo.findAll();
    }

    @Override
    public URI getHttpLink(String name, String Surname) {
        AccountProfile a = this.repo.findByNameAndSurname(name, Surname);
        if (a != null) {
            return URI.create(a.getImage());
        }

        return null;
    }
}
