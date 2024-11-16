package com.example.goit_academy_dev_hw14.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Note {
    private Long id;
    private String title;
    private String content;
}
