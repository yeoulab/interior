package com.suri.customer.naver.controller;

import java.net.URLEncoder;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/naver")
public class NaverApiController {
	
	static Logger logger = LoggerFactory.getLogger(NaverApiController.class);
		
	@GetMapping("/search")
	public String naverSearch(@RequestParam String searchQuery) {
		
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectTimeout(5000); /* 타임아웃 5초 */
		requestFactory.setReadTimeout(5000); /* Read 타임아웃 5초 */
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("X-Naver-Client-Id", "OX_jQXONxs4RTfqFMQOf");
		httpHeaders.set("X-Naver-Client-Secret", "Xh0EbpHmz_");
		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
		
		String encodeSearchQuery = "";
		
		try {
			encodeSearchQuery = URLEncoder.encode(searchQuery, "UTF-8");
		}catch(Exception e) {
			logger.info("e : " + e.getMessage());
			throw new NullPointerException("조회 조건이 입력되지 않았습니다.");
		}
		
		String url = "https://openapi.naver.com/v1/search/local.json";
		url += "?query=" + encodeSearchQuery;
		
		ResponseEntity<Map> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.GET, httpEntity, Map.class);
		
		logger.info("header : " + responseEntity.getHeaders().toString());
		logger.info("body : " + responseEntity.getBody().toString());
		
		/* 서버 테스트용 */
		return "Naver Api Search Return";
	}
}

