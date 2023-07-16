package com.eviro.assessment.grad001.ThamsanqaDyantyi.InterFaces;

import com.eviro.assessment.grad001.ThamsanqaDyantyi.Models.AccountProfile;

import java.io.File;
import java.net.URI;
import java.util.List;

public interface IAccountProfileService {

    void UploadFile(File file);

    List<AccountProfile> GetAll();

    URI getHttpLink(String name, String Surname);

}
