package com.laborbus.DemoLabourbusapi.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.laborbus.DemoLabourbusapi.Model.UserEntity;
import com.laborbus.DemoLabourbusapi.config.TwilioConfig;
import com.laborbus.DemoLabourbusapi.dto.OtpStatus;
import com.laborbus.DemoLabourbusapi.dto.UserRegistrationRequestDto;
import com.laborbus.DemoLabourbusapi.dto.UserRegistrationResponseDto;
import com.laborbus.DemoLabourbusapi.repository.UserRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import reactor.core.publisher.Mono;

@Service
public class OTPService {

	@Autowired
	private TwilioConfig twilioConfig;

	@Autowired
	private UserRepository userRepository;

	private Map<String, UserRegistrationRequestDto> cache = new HashMap<>();

	public Mono<UserRegistrationResponseDto> sendOTPForRegistration(
			UserRegistrationRequestDto userRegistrationRequestDto) {

		UserRegistrationResponseDto userRegistrationResponseDto = null;
		try {
			PhoneNumber to = new PhoneNumber(userRegistrationRequestDto.getMobileNumber());
			PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());

			// calling mthod - > generateOTP()
			String otp = generateOTP();
			// String otpMessage = "Dear Customer , Your OTP is ##" + otp
			// + "##. Use this Passcode to complete your transaction. Thank You.";
			String otpMessage = "Demo OTP testing for Laborbus, Your OTP is ##" + otp
					+ "##. Use this Passcode to complete your transaction. Thank You.";
			Message message = Message.creator(to, from, otpMessage).create();

			UserRegistrationRequestDto userRegistrationRequestDto1 = new UserRegistrationRequestDto();
			userRegistrationRequestDto1.setMobileNumber(userRegistrationRequestDto.getMobileNumber());
			userRegistrationRequestDto1.setPassword(userRegistrationRequestDto.getPassword());
			userRegistrationRequestDto1.setOneTimePassword(otp);
			
			// save data - > cache
			cache.put(userRegistrationRequestDto.getMobileNumber(), userRegistrationRequestDto1);

			userRegistrationResponseDto = new UserRegistrationResponseDto(OtpStatus.DELIVERED, otpMessage);
		} catch (Exception ex) {
			userRegistrationResponseDto = new UserRegistrationResponseDto(OtpStatus.FAILED, ex.getMessage());
		}
		return Mono.just(userRegistrationResponseDto);
	}

	public Mono<String> validateOTP(String userInputOtp, String mobileNumber) {
		// get otp from cache
		UserRegistrationRequestDto retrievedUser = cache.get(mobileNumber);
		String cachedOtp = retrievedUser.getOneTimePassword(); // Retrieve the stored OTP from the cache

		if (cachedOtp != null && cachedOtp.equals(userInputOtp)) {

			// Valid OTP, proceed with saving the DTO to the database
			UserRegistrationRequestDto dtoToSave = cache.get(mobileNumber);

			// calling method - saveDtoToDatabase
			saveDtoToDatabase(dtoToSave);

			return Mono.just("Valid OTP, please proceed with your transaction!");
		} else {
			return Mono.error(new IllegalArgumentException("Invalid OTP, please retry!"));
		}
	}

	// 4 digit otp generation
	private String generateOTP() {
		return new DecimalFormat("0000").format(new Random().nextInt(9999));
	}

	// save DTO in the database
	private void saveDtoToDatabase(UserRegistrationRequestDto dto) {

		UserEntity userEntity = new UserEntity();
		userEntity.setMobileNumber(dto.getMobileNumber());
		userEntity.setPassword(dto.getPassword());
		userEntity.setOtp(dto.getOneTimePassword());

		userRepository.save(userEntity);
	}

}