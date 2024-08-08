package com.otclub.humate.domain.chat.vo;

import com.otclub.humate.domain.chat.dto.MessageRedisDTO;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 채팅메세지 MongoDB Document
 * @author 최유경
 * @since 2024.07.29
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.29  	최유경        최초 생성
 * </pre>
 */

@Document(collection = "chatMessage")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Message {

    @Id
    private String _id;
    @Indexed
    private String chatRoomId;
    private String participateId;
    private String content;
    private String createdAt;
    private int readCount;
    private MessageType messageType; // 채팅 타입 필드 추가('TEXT', 'IMAGE')
    private String imgUrl;

    public static Message of(MessageRedisDTO requestDTO){
        Message message = Message.builder()
                .chatRoomId(requestDTO.getChatRoomId())
                .participateId(requestDTO.getParticipateId())
                .content(requestDTO.getContent())
                .createdAt(getDate())
                .readCount(1)
                .messageType(requestDTO.getMessageType())
                .build();
        return message;
    }

    private static String getDate() {
        Date date = new Date();

        // 날짜 형식을 지정 (예: "hh:mm" - 12시간 형식)
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTime = timeFormat.format(date);

        // Calendar 객체를 사용하여 AM/PM 값 추출
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        int amPm = calendar.get(Calendar.AM_PM);
//        String amPmString = (amPm == Calendar.AM) ? "am" : "pm";

        // 최종 결과를 조합하여 출력
//        String finalFormattedTime = formattedTime + " " + amPmString;
        return formattedTime; // finalFormattedTime
    }
}
