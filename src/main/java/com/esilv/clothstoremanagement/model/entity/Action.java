package com.esilv.clothstoremanagement.model.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * This class represents an action
 * author: Stefan Radovanovic
 * author: Yannick li
 */
@Builder
@Getter
public class Action {
    private String action;
    private LocalDateTime date;
}
