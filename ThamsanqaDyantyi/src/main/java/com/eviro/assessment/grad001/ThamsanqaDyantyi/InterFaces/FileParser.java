package com.eviro.assessment.grad001.ThamsanqaDyantyi.InterFaces;

import java.io.File;
import java.net.URI;

public interface FileParser {
    void parseCSV(File csvFile);

    File convertCSVDATAToImage(String base64ImageData);

    URI createImageLink(File fileImage);
}
