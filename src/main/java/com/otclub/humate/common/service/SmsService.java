package com.otclub.humate.common.service;

import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * SMS 전송 서비스 클래스
 * @author 조영욱
 * @since 2024.08.05
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.05  	조영욱        최초 생성
 * </pre>
 */
@Component
@Slf4j
public class SmsService {

    @Value("${coolsms.apiKey}")
    private String apiKey;
    @Value("${coolsms.apiSecret}")
    private String apiSecret;
    @Value("${coolsms.fromNumber}")
    private String fromNumber;

    /**
     * 휴대전화 인증 메시지 전송
     *
     * @author 조영욱
     * @param phoneNumber 전화번호
     * @param code 인증 코드
     */
    public void sendPhoneVerifySMS(String phoneNumber, String code) {
        DefaultMessageService messageService =
                NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");

        Message message = new Message();
        message.setFrom(fromNumber);
        message.setTo(phoneNumber);
        message.setText("[HuMate] 인증번호: " + code);

        try {
            messageService.send(message);
        } catch (NurigoMessageNotReceivedException exception) {
            log.error(exception.getFailedMessageList().toString());
            log.error(exception.getMessage());
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
    }
}
