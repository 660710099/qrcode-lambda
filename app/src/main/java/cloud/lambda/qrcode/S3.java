package cloud.lambda.qrcode;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

public class S3 {
    S3Client client;
    String bucketName;
    Region region;
    public S3(Region region, String bucketName) {
        this.client = S3Client.builder().region(region).build();
        this.bucketName = bucketName;
        this.region = region;
    }
    
    public PutObjectResponse uploadQRCode(byte[] image, String key) throws Exception { 
        PutObjectRequest por = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .build();

        return client.putObject(por, RequestBody.fromBytes(image));
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public Region getRegion() {
        return this.region;
    }
}
    
