package com.example.springBoot.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@ToString
@Accessors(chain = true) // 可以链式调用 setter
@Entity(name = "checking")
@EntityListeners(AuditingEntityListener.class)
public class Checking {
    @Id
    @GeneratedValue
    private Long id;        // 主键 id

    @Column(name = "name", length = 20)
    private String name;    // 名称

    @Column(name = "url", length = 20)
    private String url;     // 下载地址

    @CreatedDate
    @Column(name = "created_at", length = 20)
    private Date createdAt; // 创建时间
}
