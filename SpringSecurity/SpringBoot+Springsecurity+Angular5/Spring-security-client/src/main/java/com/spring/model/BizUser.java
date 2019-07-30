package com.spring.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class BizUser implements Serializable {
	
	private static final long serialVersionUID = -6151015221448425648L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * 登录id
	 */
	@Column(name = "loginid", length = 30, nullable = false)
	private String loginId;
	/**
	 * 用户名称
	 */
	@Column(name = "username", length = 128, nullable = false)
	private String username;
	/**
	 * 密码
	 */
	@Column(name = "password", length = 128, nullable = false)
	//@JsonIgnore//生成json数据时忽略此字段
	private String password;
	
    @Column(length = 200)
    @JsonIgnore
    private String salt;
    
	@Column(name = "email")
    @Size(min = 4, max = 50)
    private String email;

	/**
	 * 是否禁用
	 */
	@Column(name = "enabled", columnDefinition = "tinyint")
    private Boolean enabled;
    
    /**
     * 是否过期
     */
	@Column(name = "accountnonexpired", columnDefinition = "tinyint")
    private Boolean accountNonExpired;
    
    /**
     * 是否有证书
     */
	@Column(name = "credentialsnonexpired", columnDefinition = "tinyint")
    private Boolean credentialsNonExpired;
    
    /**
     * 是否有锁
     */
    @Column(name = "accountnonlocked", columnDefinition = "tinyint")
    private Boolean accountNonLocked;

    @CreatedDate
    private Date created;
    
    @LastModifiedDate
    private Date updated;
    
    //角色与用户建立多对多关联
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, }, fetch = FetchType.EAGER)
    //@JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "userid", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_user_id"))},inverseJoinColumns = {@JoinColumn(name = "roleid", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_role_id"))})
    @JsonIgnore
    private List<BizRole> roleList = new ArrayList<>();

    
   /**
    * 方案1，还有方案2
    * 将自定义角色转化为springsecurity需要的形式
    * @return
    */
//   public Collection<? extends GrantedAuthority> getAuthorities() {
//       List<GrantedAuthority> authorities = new ArrayList<>();
//       List<BizRole> roles = this.roleList;
//       for (BizRole role : roles) {
//           authorities.add(new SimpleGrantedAuthority(role.getName()));
//       }
//       return authorities;
//   }
  
}
