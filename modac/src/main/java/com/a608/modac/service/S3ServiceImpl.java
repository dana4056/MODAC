package com.a608.modac.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Service
public class S3ServiceImpl implements S3Service {

	private final AmazonS3Client amazonS3Client;
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	// @Value("${cloud.aws.s3.credentials.accessKey}")
	// private String accessKey;

	// @Value("${cloud.aws.s3.credentials.secretKey}")
	// private String secretKey;
	// private final AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
	// @Value("${cloud.aws.region.static}")
	// private String region;
	// private final AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard()
	// 	.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
	// 	.withRegion(region)
	// 	.build();

	// String 타입의 게시글 내용을 전달받아 MultipartFile 객체 생성
	@Override
	public MultipartFile createMultipartFile(String content) {
		File file = new File("temp.md");
		try {
			// 임시 파일에 게시글 내용을 저장
			FileWriter fw = new FileWriter(file, false);
			fw.write(content);
			fw.flush();
			fw.close();
			// 임시 파일을 MultipartFile 객체로 변환
			FileItem fileItem = new DiskFileItem("mainFile", Files.probeContentType(file.toPath()), false,
				file.getName(), (int)file.length(), file.getParentFile());
			InputStream inputStream = new FileInputStream(file);
			OutputStream outputStream = fileItem.getOutputStream();
			IOUtils.copy(inputStream, outputStream);
			return new CommonsMultipartFile(fileItem);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			file.delete();
		}
		return null;
	}

	// MultipartFile 객체를 S3서버에 저장
	@Override
	public String save(MultipartFile multipartFile) {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(MediaType.TEXT_MARKDOWN_VALUE);
		metadata.setContentLength(multipartFile.getSize());
		String objectKey = "articles/" + new SimpleDateFormat("yyyyMMdd-hhmmssSSS").format(new Date()) + ".md";
		try {
			amazonS3Client.putObject(new PutObjectRequest(bucket, objectKey, multipartFile.getInputStream(), metadata)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return objectKey;
	}

	// S3서버에 저장된 파일 내용 읽기
	@Override
	public String read(String filePath) {
		S3Object object = amazonS3Client.getObject(new GetObjectRequest(bucket, filePath));
		S3ObjectInputStream inputStream = object.getObjectContent();
		String content = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			String line = "";
			while ((line = br.readLine()) != null) {
				content += line + "\n";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	// S3서버에 저장된 파일 삭제
	@Override
	public void delete(String filePath) {
		amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, filePath));
	}
}