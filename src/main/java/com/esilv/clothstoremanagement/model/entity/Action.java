package com.esilv.clothstoremanagement.model.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Action {
    private String action;
    private LocalDateTime date;
}
