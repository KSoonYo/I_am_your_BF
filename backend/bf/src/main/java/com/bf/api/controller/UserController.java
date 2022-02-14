package com.bf.api.controller;



import com.bf.api.request.*;
import com.bf.api.response.UserFindIdPostRes;
import com.bf.api.response.UserLoginPostRes;
import com.bf.common.util.JwtTokenUtil;
import com.querydsl.core.NonUniqueResultException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bf.api.response.UserRes;
import com.bf.api.service.UserService;
import com.bf.common.auth.SsafyUserDetails;
import com.bf.db.entity.User;
import com.bf.model.response.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/users")
public class UserController {

    //Looger
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserController.class);

    // autowired filed injection -> constructor injection
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService,PasswordEncoder passwordEncoder) {

        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // 비밀번호 변경을 위한 javamailsender
    @Autowired
    private JavaMailSender sender;


    @PostMapping()
    @ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 성공"),
            @ApiResponse(code = 401, message = "회원가입 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> createUser(
            @RequestBody @ApiParam(value = "회원가입 정보", required = true) UserRegisterPostReq registerInfo) {

        String userId = registerInfo.getUserId();
        if(userService.chkDplByUserId(userId)) { //유저 정보가 존재하면
            return ResponseEntity.status(200).body(BaseResponseBody.of(401, "Duplicate userID"));
        }
        //임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
        User user = userService.createUser(registerInfo);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }


    @GetMapping("/{userId}")
    @ApiOperation(value = "회원가입 중복검사", notes = "회원 가입 시 아이디와 이메일 중복검사를 실행한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "존재하는 유저 아님"),
            @ApiResponse(code = 401, message = "이미 존재하는 유저"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> chkDplIdAndEmail(@PathVariable String userId) {

        //Regular Expression
        String regx = "^(.+)@(.+)$";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regx);
        System.out.println(pattern.matcher(userId).matches());

        if (pattern.matcher(userId).matches()) { // condition : email
            LOGGER.info("email");
            if (!userService.chkDplByUserEmail(userId)) //유저 정보가 존재하지 않으면
                return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
        } else {// condition : id
            LOGGER.info("email");
            if (!userService.chkDplByUserId(userId)) //유저 정보가 존재하지 않으면\
                return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
        }
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Duplicate Error"));
    }

    @GetMapping("/profile")
    @ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 프로필 조회 성공"),
            @ApiResponse(code = 401, message = "회원 프로필 조회실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<UserRes> getUser(Authentication authentication) {
        /**
         * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
         * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
         */

        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
//        String userId = userDetails.getUser().getUserId();
//        User user = userService.getUserByUserId(userId);
        System.out.println("getUser");
        User user = userDetails.getUser();
        System.out.println(user);
        return ResponseEntity.status(200).body(UserRes.of(user));
    }

    @PostMapping("/find/userId")
    @ApiOperation(value = "아이디 찾기", notes = "로그인한 회원의 아이디를 찾습니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "아이디 찾기 성공"),
            @ApiResponse(code = 401, message = "아이디 찾기 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> findUserId(
            @RequestBody @ApiParam(value = "아이디 찾기 정보", required = true) UserFindIdPostReq userFindIdPostReq) {

        try {
            User user = userService.getUserByUserNameAndEmail(userFindIdPostReq.getUserName(), userFindIdPostReq.getUserEmail());
            if(user == null)
                return ResponseEntity.status(401).body(UserFindIdPostRes.of(401, "Invalid Name And Password", null));
            else {

                String hiddenId = user.getUserId();
                hiddenId =hiddenId.replaceAll("(?<=.{3})." , "*");

                return ResponseEntity.ok(UserFindIdPostRes.of(200, "Success", hiddenId));
            }
        }catch (NonUniqueResultException e){
            return ResponseEntity.status(401).body(UserFindIdPostRes.of(401, "NonUniqueResultException", null));

        }catch (NoSuchElementException e){
            return ResponseEntity.status(401).body(UserFindIdPostRes.of(401, "NoSuchElementException", null));

        }



    }


    @PostMapping("/find/password")
    @ApiOperation(value = "비밀번호 찾기", notes = "로그인한 회원의 비밀번호를 찾습니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "임시 비밀번호 발급 성공"),
            @ApiResponse(code = 401, message = "임시 비밀번호 발급 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> findPassword(
            @RequestBody @ApiParam(value = "비밀번호 찾기 정보", required = true) UserFindPwPostReq userFindPwPostReq
    ) {
        User user = userService.getUserByUserId(userFindPwPostReq.getUserId());

        System.out.println(user.getUserId() + " | "+ user.getUserName() + " | " + user.getUserEmail());
        if(user == null){
            return ResponseEntity.status(401).body(UserFindIdPostRes.of(401, "Invalid User", null));

        }
        if(!user.getUserId().equals(userFindPwPostReq.getUserId())){
            System.out.println(user.getUserId());
            System.out.println(userFindPwPostReq.getUserId());
            return ResponseEntity.status(401).body(UserFindIdPostRes.of(401, "Invalid ID", null));
        }
        if(!user.getUserName().equals( userFindPwPostReq.getUserName())){
            return ResponseEntity.status(401).body(UserFindIdPostRes.of(401, "Invalid userName", null));
        }
        if(!user.getUserEmail().equals(userFindPwPostReq.getUserEmail())){
            return ResponseEntity.status(401).body(UserFindIdPostRes.of(401, "Invalid userEmail", null));

        }
        //        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
//        User user = userDetails.getUser();

//        Random r = new Random();
//        int num = r.nextInt(999999); // 랜덤난수설정
        String uuid = UUID.randomUUID().toString();
        String setfrom = "hello__world__@naver.com"; // naver
        String tomail = user.getUserEmail();// 받는사람
        String title = "[BF] 임시 비밀번호 이메일 입니다";
        String content =
                System.getProperty("line.separator") + "안녕하세요 회원님"
                        + System.getProperty("line.separator") + "임시 비밀번호는 " + uuid + " 입니다."
                        + System.getProperty("line.separator") + "로그인을 하시고 꼭 비밀번호를 바꿔주세요 :)";

        try {
            SimpleMailMessage simpleMessage = new SimpleMailMessage();
            simpleMessage.setFrom(setfrom); // NAVER, DAUM, NATE일 경우 넣어줘야 함
            simpleMessage.setTo(tomail);
            simpleMessage.setSubject(title);
            simpleMessage.setText(content);
            sender.send(simpleMessage);

            userService.updatePassword(user.getUserId(), uuid);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }


        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Failed to Find the Password"));
    }

//    /**
//     * start
//     **/
//    @GetMapping("/{userId}")
//    @ApiOperation(value = "유저 정보(존재하는 회원 확인용", notes = "존재하는 사용자인지를 응답한다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "존재하는 유저 아님"),
//            @ApiResponse(code = 409, message = "이미 존재하는 유저"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
//    public ResponseEntity<? extends BaseResponseBody> isExist(
//            @PathVariable("userId") String userId) {
//
//        System.out.println("!!!!찍히냥");
//        if (userService.chkDplByUserId(userId))
//
//            return ResponseEntity.status(409).body(BaseResponseBody.of(409, "AlreadyExist"));
//
//        else return ResponseEntity.status(200).body(BaseResponseBody.of(200, "NotAlreadyExist"));
//    }

    @PostMapping("/password")
    @ApiOperation(value = "회원 비밀번호 변경", notes = "임시비밀번호 입력을 통해 비밀번호 변경을 한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 성공"),
            @ApiResponse(code = 401, message = "회원가입 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> updatePassword( Authentication authentication,
            @RequestBody @ApiParam(value = "비밀번호 정보", required = true) UserUpdatePasswordPostReq passwordInfo) {

        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();



        // 비밀번호 변경 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
        if(passwordEncoder.matches(passwordInfo.getCurrentPassword(), user.getPassword())) {
            // 유효한 패스워드가 맞는 경우
            userService.updatePassword(user.getUserId(), passwordInfo.getNewPassword());
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
        }
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "Fail"));

    }


    @PatchMapping("/{userId}")
    @ApiOperation(value = "유저 정보 수정", notes = "유저 정보를 수정 후 응답한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> updateUser(
            @PathVariable("userId") String userId,
            @RequestBody @ApiParam(value = "유저 정보", required = true) UserInfoFetchReq userInfo) {

        userService.updateUser(userId, userInfo);
        // 유저 이메일 중복 체크 필요 -> 후순위


        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));

    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value = "유저 정보 삭제", notes = "유저 정보를 삭제 후 응답한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저 정보 삭제(탈퇴) 성공"),
            @ApiResponse(code = 401, message = "유저 정보 삭제(탈퇴) 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> deleteUser(
            @PathVariable("userId") String userId
    ) {

        userService.deleteUser(userId);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));

    }



    @PostMapping("/report/")
    @ApiOperation(value = "회원 신고", notes = "userId에 따라 회원을 신고한다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "존재하는 유저 아님"),
            @ApiResponse(code = 409, message = "이미 존재하는 유저"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<? extends BaseResponseBody> report(
            ) {

        return null;
    }
}
