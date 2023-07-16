package com.eviro.assessment.grad001.ThamsanqaDyantyi.Service;

import com.eviro.assessment.grad001.ThamsanqaDyantyi.InterFaces.FileParser;
import com.eviro.assessment.grad001.ThamsanqaDyantyi.Models.AccountProfile;
import com.eviro.assessment.grad001.ThamsanqaDyantyi.Repository.AccountProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class FileParserService implements FileParser {

    @Autowired
    private AccountProfileRepository accountProfileRepository;


    @Override
    public void parseCSV(File csvFile) {
        try(BufferedReader r = new BufferedReader(new FileReader(csvFile))){
            // Read first line to remove csv header
            String Header = r.readLine();
            //iterate through the csv data and set to new entity
            String DataLine;
            while ((DataLine = r.readLine()) != null){
                String[] data = DataLine.split(",");

                AccountProfile accountProfile = new AccountProfile();
                accountProfile.setName(data[0]);
                accountProfile.setSurname(data[1]);
                accountProfile.setImage(createImageLink(convertCSVDATAToImage(DataLine)).toString());

                accountProfileRepository.save(accountProfile);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public File convertCSVDATAToImage(String base64ImageData) {
        //Split the string Image data
        String[] line = base64ImageData.split(",");

        try {
            byte[] ImageData = Base64.getDecoder().decode(line[3]);

            File Temp = new File(line[0]+" "+line[1]+"."+line[2].split("/")[1]);

            //File Temp = File.createTempFile(line[0]+" "+line[1],"."+line[2].split("/")[1]);

            try (FileOutputStream f = new FileOutputStream(Temp)) {
                f.write(ImageData);
            }

            return Temp;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public URI createImageLink(File fileImage) {
        URI uri = null;
        try {
            Path path = Paths.get(fileImage.getAbsolutePath());

            uri = path.toUri();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uri;
    }
}
