package com.app.yash.s3fileuploadusingpresignedurl;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSS3Config {
    @Value("${amazon.aws.accessKey}")
    private String accessKeyId;

    @Value("${amazon.aws.secretKey}")
    private String accessKeySecret;

    @Value("IQoJb3JpZ2luX2VjEIL//////////wEaCXVzLWVhc3QtMSJHMEUCIQDyiE2SlnZD+fCgg0Pf/xtNaPU4t8molAgtuI8NoOzTVQIgKSikGehpoLP/KAwldcwb1UwdDUSp/l8aJDjdnYkoW54qvAMI+///////////ARADGgw2NTEyMzM1NDEwMjciDDTd/BHrUhNjIeF+YiqQA7z84H73Fl9v8OQ7eEXsEfEfPFzBfjYXX8g2/i3IaM1c0V9fpxb+wwZ6NbqakeBHLbvUiIN2TH7SzZvylNjvTlwevkyFDdT4D9xVvGTNPF1x1mgYgHqz5f3mTj+JVrbYe+qbwol+bFHBTii0ZmvvvXSGhf0wRiewi1VQxXVPMCGQKtAf563vVgj7KAoZmvWUacuOYYFRXqsHniHPdm7h8YPUju3QnAK75HzOtFFoxdVLXNGivUYPXMj8TsG7ezIp582H5jkEZ2VIc/RZIeCor8+GdyE+C/PmGvwXFJXUXs4Id1b1trKvXOpn4nsCj1+uzsKCARE+aA4kRJ20WfU8trn8M/TIUFxxnSZREFtzmH4Y40FJKI3x+U+78GiImStZseGj2hnvJbmuSyKEx1m8u3mWEY3E/EmUJef5UST8369jwYQ9R8DcSNrXs/IhjTWLgiafvRYGtrvl8nHh9Pw5Tk8KOoNYv/xG2zXC7hVrCE00XK8843foWdiG5LFdEEor1txb+pWYXL+psBeBa42Kd0owgIvasgY6pgH2ITpi/Nm82Jef1xRt7uh3mD7gf4sZGcZn+FO6SrAV/Nx5cPlqjZT9IO6X0EtF69hkDHQp0fAaP4ivH8l8VHbosbffn4SEZkiFoCW98X9s/RoSSA37W/6vr+Ugtopv/XquJeGgzCMzf7iekgu3tPQ6V12MAGIeDZ0onjdoF6yegbosUToyiPHkvHviLQJahruaIGUQA/PS99VqT4mVrvsZ4p+AxJ4T")
    private String sessionToken;

    @Value("${amazon.aws.region}")
    private String s3RegionName;

    @Bean
    public AmazonS3 getAmazonS3Client() {
//        final BasicAWSCredentials basicAwsCredentials = new BasicAWSCredentials()
        final BasicSessionCredentials basicSessionCredentials = new BasicSessionCredentials(accessKeyId, accessKeySecret, sessionToken);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicSessionCredentials))
                .withRegion(s3RegionName)
                .build();
    }
}
