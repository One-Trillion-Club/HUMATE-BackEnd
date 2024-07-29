package com.otclub.humate.domain.chat.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChatType {
    TEXT(1),
    IMAGE(2);

    private final Integer type;

    @JsonCreator
    public static ChatType of(Integer chatType){
        return Arrays.stream(ChatType.values())
                .filter(i -> i.getType().equals(chatType))
                .findAny()
                .orElse(null);
    }
}
