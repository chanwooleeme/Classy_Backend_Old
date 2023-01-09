package com.prototype.classyBackEnd.video;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prototype.classyBackEnd.aws.dto.S3PresignedUrlDto;
import com.prototype.classyBackEnd.aws.dto.S3UploadDto;
import com.prototype.classyBackEnd.aws.dto.S3UploadInitiateDto;
import com.prototype.classyBackEnd.aws.dto.S3UploadSignedUrlDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
public class SplitFileTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        String filePath = "/Users/lee/Desktop/classy resource/video/file.mp4";
        File file = new File(filePath);
        List<ByteArrayOutputStream> byteArrayOutputStreams = splitFile(file);
        S3UploadDto init = init();

        int i = 1;
        for (ByteArrayOutputStream stream: byteArrayOutputStreams) {
            S3UploadSignedUrlDto s3UploadSignedUrlDto = new S3UploadSignedUrlDto(init.getUploadId(), init.getFileName(), i++);
            S3PresignedUrlDto s3PresignedUrlDto = uploadUrl(s3UploadSignedUrlDto);

            String split2 = init.getFileName().substring(init.getFileName().length()-4);
            String split1 = init.getFileName().substring(0, init.getFileName().length()-4);
            String s = split1 + "_" + (i-1) + split2;

            s = "/Users/lee/Desktop/classy resource/video/" + s;
            toFile(stream, s);

        }
    }


    public MockMultipartFile getMockMultipartFile(String fileName, String contentType, String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
    }

    public S3UploadDto init() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String content = objectMapper.writeValueAsString(new S3UploadInitiateDto("production ID_4695859.mp4"));

        MvcResult mvcResult = mockMvc.perform(post("/initiate-upload")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        return objectMapper.readValue(response.getContentAsString(), S3UploadDto.class);
    }

    public S3PresignedUrlDto uploadUrl(S3UploadSignedUrlDto dto) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(dto);
        MvcResult mvcResult = mockMvc.perform(post("/upload-signed-url")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        S3PresignedUrlDto s3PresignedUrlDto = objectMapper.readValue(response.getContentAsString(), S3PresignedUrlDto.class);
        return s3PresignedUrlDto;
    }

    public static List<ByteArrayOutputStream> splitFile(File f) {
        List<ByteArrayOutputStream> datalist = new ArrayList<>();
        try {

            int sizeOfFiles = 1024*1024*10;
            byte[] buffer = new byte[sizeOfFiles];

            try (FileInputStream fis = new FileInputStream(f); BufferedInputStream bis = new BufferedInputStream(fis)) {
                int bytesAmount = 0;
                while ((bytesAmount = bis.read(buffer)) > 0) {
                    try (OutputStream out = new ByteArrayOutputStream()) {
                        out.write(buffer, 0, bytesAmount);
                        out.flush();
                        datalist.add((ByteArrayOutputStream) out);
                    }
                }
            }
        } catch (Exception e) {
            //get the error
        }
        return datalist;
    }

    public void toFile(ByteArrayOutputStream stream, String fileName) {
        try(OutputStream outputStream = new FileOutputStream(fileName)){
            stream.writeTo(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
