/*
 * package com.laborbus.DemoLabourbusapi.resource;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController; import
 * com.laborbus.DemoLabourbusapi.service.TwilioOTPService;
 * 
 * import reactor.core.publisher.Mono;
 * 
 * 
 * 
 * @RestController
 * 
 * @RequestMapping("/router/api") public class UserController {
 * 
 * @Autowired private TwilioOTPService service;
 * 
 * @PostMapping("/sendOTP") public ResponseEntity<String>
 * registerUser(@RequestParam String mobileNumber, @RequestParam String
 * password) { service.sendOTPForRegistration(mobileNumber, password); return
 * ResponseEntity.ok("OTP send for user registration..."); }
 * 
 * // @PostMapping("/validateOTP") // public ResponseEntity<String>
 * validateOtp(@RequestParam String mobileNumber, @RequestParam String
 * enteredOtp) { // if (service.validateOTP(mobileNumber, enteredOtp) != null) {
 * // return ResponseEntity.ok("OTP validation successful."); // } // return
 * ResponseEntity.badRequest().body("Invalid OTP"); //    }
 * 
 * @PostMapping("/validateOTP") public ResponseEntity<String>
 * validateOtp(@RequestParam String userInputOtp, @RequestParam String
 * mobileNumber) { Mono<String> result = service.validateOTP(userInputOtp,
 * mobileNumber);
 * 
 * if (result != null) { return ResponseEntity.ok("OTP validation successful.");
 * } return ResponseEntity.badRequest().body("Invalid OTP"); }
 * 
 * }
 * 
 * 
 * 
 * //@PostMapping("/sendOTP") //public Mono<Object> registerUser(@RequestParam
 * String mobileNumber, @RequestParam String password) { // //
 * Mono<UserRegistrationResponseDto> responseMono =
 * service.sendOTPForRegistration(mobileNumber, password); // // return
 * responseMono.flatMap(responseDto -> { // if (responseDto.getStatus() ==
 * OtpStatus.DELIVERED) { // return
 * Mono.just(ResponseEntity.ok("OTP sent for user registration")); // } else {
 * // return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
 * body("Failed to send OTP")); // } // }); //} //
 * 
 * 
 * body("Failed to send OTP")); //.block(); // Blocking here to get the result,
 * consider using reactive approach in a real // application
 * 
 * 
 * 
 */