package com.fa.spring.elastic.springelasticdemo.entity;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;

import static org.springframework.data.elasticsearch.annotations.FieldType.Text;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Author {
    @Field(type = Text)
    private String name;

}
