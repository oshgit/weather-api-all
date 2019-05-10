package com.app.weatherapi.app;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

@RunWith(SpringRunner.class)
@EnableConfigurationProperties
@ActiveProfiles("test")
public abstract class BaseTest {
	
	@ClassRule
	public static WireMockRule wireMockRule = new WireMockRule(8089);
	
	@Value("${service.constants.pathToTest}")
	public String path;
	@Value("${service.constants.pathToApi}")
	public String pathToApi;
	@Value("${service.constants.cityIdTest}")
	public String cityIdTest;
	@Value("${service.constants.cityExpectedPass}")
	public String cityExpectedPass;
	@Value("${service.constants.cityExpectedFail}")
	public String cityExpectedFail;
	@Value("${service.constants.bodyTest}")
	public String bodyTest;
	@Value("${service.constants.notFound}")
	public String notFound;
	@Value("${service.constants.internalServerError}")
	public String internalServerError;
	@Value("${service.constants.badRequest}")
	public String badRequest;
	@Value("${service.constants.patternReplace}")
	public String patternReplace;
	
	public abstract void testOk();
	
	public abstract void testNotFoundException();
	
	public abstract void testFailInternalServerError();
	
	public void stubResponseOk() {
		stubFor(get(urlEqualTo(path))
			    .willReturn(aResponse()
			                        .withStatus(HttpStatus.OK.value())
			                        .withHeader("Content-Type", "application/json")
			                        .withBody(bodyTest)));
	}

}
