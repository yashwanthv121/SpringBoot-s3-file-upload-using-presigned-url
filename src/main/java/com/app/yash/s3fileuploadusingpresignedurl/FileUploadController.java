package com.app.yash.s3fileuploadusingpresignedurl;

import com.amazonaws.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class FileUploadController {

    @Autowired
    FileUploadService awsS3Service;
    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.s3.folder}")
    private String folder;
    
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList("csv", "xls", "xlsx");


    @GetMapping("/generate-presigned-url")
    public ResponseEntity<ApiResponse<String>> generatePresignedUrl(@RequestParam String fileType) {
        if (!ALLOWED_EXTENSIONS.contains(fileType.toLowerCase())) {
            String errorMessage = "Invalid file extension. Only CSV and Excel files are allowed.";
            ApiResponse<String> errorResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), errorMessage, null, null);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            long epochMilli = System.currentTimeMillis();
            String fileName = "bulk_immediate_notification" + epochMilli + "." + fileType;
            String presignedUrl = awsS3Service.generatePreSignedUrl(folder + '/' + fileName, bucketName, HttpMethod.PUT);
            ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK.value(), "Presigned URL generated successfully", fileName, presignedUrl);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
//             Handling the exception if occurs
//            Exception e = new Exception();
            String errorMessage = "AWS S3 Dependency issue while generating presigned url: " + e.getMessage();
            ApiResponse<String> errorResponse = new ApiResponse<>(HttpStatus.FAILED_DEPENDENCY.value(), errorMessage, null, null);
            return new ResponseEntity<>(errorResponse, HttpStatus.FAILED_DEPENDENCY);
        }
    }

}
