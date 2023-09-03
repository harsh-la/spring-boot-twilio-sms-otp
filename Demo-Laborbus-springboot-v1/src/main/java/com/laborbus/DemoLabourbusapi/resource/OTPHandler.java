package com.laborbus.DemoLabourbusapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.laborbus.DemoLabourbusapi.dto.UserRegistrationRequestDto;
import com.laborbus.DemoLabourbusapi.service.OTPService;
import reactor.core.publisher.Mono;

@Component
public class OTPHandler {

	@Autowired
	private OTPService service;

	public Mono<ServerResponse> sendOTP(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(UserRegistrationRequestDto.class)
				.flatMap(dto -> service.sendOTPForRegistration(dto))
				.flatMap(dto -> ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(dto)));
	}

	public Mono<ServerResponse> validateOTP(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(UserRegistrationRequestDto.class)
				.flatMap(dto -> service.validateOTP(dto.getOneTimePassword(), dto.getMobileNumber()))
				.flatMap(dto -> ServerResponse.status(HttpStatus.OK).bodyValue(dto));
	}

}