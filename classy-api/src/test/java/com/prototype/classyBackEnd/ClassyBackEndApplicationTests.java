package com.prototype.classyBackEnd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

class ClassyBackEndApplicationTests {

/*	@Autowired
	ResourceLoader resourceLoader;

	@Value("${cloud.aws.cloudFrontUrl}")
	private String cloudFrontUrl;

	@Value("${cloud.aws.s3.inputMovOrMp4Bucket}")
	public String inputMovOrMp4Bucket;  // S3 버킷 이름

	@Test
	void contextLoads() {
		URL r = this.getClass().getResource("");
		String path = r.getPath();
		System.out.println("path = " + path);
	}

//	@Test
//	void staticPath(){
//		Resource resource = resourceLoader.getResource("classpath:static/img.jpg");
//		String path = resource.getFilename();
//		System.out.println("path = " + path);
//	}

	@Test
	void 메모리해제테스트(){
		System.out.println("start");
		doSomeThing();
		System.out.println("End");
	}

	@Test
	void test(){
		System.out.println(cloudFrontUrl);
		System.out.println(inputMovOrMp4Bucket);
	}

	void doSomeThing(){
		Map<String, String> map = new LinkedHashMap();
		map.put("ok","ok");
		System.out.println("map = " + map);
	}*/
}
