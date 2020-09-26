import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception{

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .endpoint("https://fengqing.blob.core.windows.net/;SharedAccessSignature=sv=2019-12-12&ss=b&srt=sco&sp=rwdlacx&se=2020-10-03T16:44:27Z&st=2020-09-26T08:44:27Z&spr=https,http&sig=OOwSe%2Bnqdnj0iRtZcLCEgk%2Bolr%2BaceENGHltUznzN4o%3D")
                .sasToken("?sv=2019-12-12&ss=b&srt=sco&sp=rwdlacx&se=2020-10-03T16:44:27Z&st=2020-09-26T08:44:27Z&spr=https,http&sig=OOwSe%2Bnqdnj0iRtZcLCEgk%2Bolr%2BaceENGHltUznzN4o%3D")
                .buildClient();

        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient("tou");

        BlobClient blobClient = blobContainerClient.getBlobClient("lyrics.txt");

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        blobClient.download(outStream);

        List<String> lines = new ArrayList<>();
        String line;
        ByteArrayInputStream inStream = new ByteArrayInputStream(outStream.toByteArray());
        BufferedReader br = new BufferedReader(new InputStreamReader(inStream));
        System.out.println("The content text in the file is:\n");
        while((line = br.readLine()) != null) {
            lines.add(line);
            System.out.println(line);
        }
        System.out.println("\nThere are " + lines.size() + " lines in this file.");

    }
}
