package com.a608.modac.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
	// String 타입의 게시글 내용을 전달받아 MultipartFile 객체 생성
	MultipartFile createMultipartFile(String content);

	// MultipartFile 객체를 S3서버에 저장
	String save(MultipartFile multipartFile);

	// S3서버에 저장된 파일 내용 읽기
	String read(String filePath);

	// S3서버에 저장된 파일 삭제
	void delete(String filePath);

}