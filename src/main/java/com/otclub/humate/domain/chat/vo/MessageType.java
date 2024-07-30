package com.otclub.humate.domain.chat.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageType {
    TEXT(1),
    IMAGE(2),
    ENTER(3),
    EXIT(4);

    private final Integer type;

    @JsonCreator
    public static MessageType of(Integer chatType){
        return Arrays.stream(MessageType.values())
                .filter(i -> i.getType().equals(chatType))
                .findAny()
                .orElse(null);
    }
}
