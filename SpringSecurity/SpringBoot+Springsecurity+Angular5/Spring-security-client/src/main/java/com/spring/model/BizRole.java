package com.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.spring.util.AuthorityName;

import lombok.Data;

/**
 * 角色
 * @author pengsheng
 *
 */
@Data
@Entity
@Table(name = "Role")
public class BizRole implements Serializable{

	private static final long serialVersionUID = -8667880020723455338L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/**
	 * 角色类型名称 ROLE_USER，ROLE_ADMIN
	 */
	@Column(length = 100)
    private String name;
    
    /**
     * 角色名称
     */
    @Column(length = 200)
    private String note;
    
    @CreatedDate
    private Date created;
    
    @LastModifiedDate
    private Date updated;

		
}
